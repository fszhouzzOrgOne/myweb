package cangjie.java.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//詞組的編碼工具
public class PhraseUtils {
    private static String mbBaseDir = "src\\java\\cangjie\\mb\\";
    // 倉頡六所有字符 //
    private static String cj6file = "sghm-20000.txt";

    // 詞組中可以有的符號，但不編碼
    private static String marks = "，˙；（）";

    public static void main(String[] args) {
        Set<String> cj6Set = new LinkedHashSet<String>(
                IOUtils.readLines(mbBaseDir + cj6file));
        Map<String, List<String>> charCodesMap = PhraseUtils
                .getMbSetMapByChar(cj6Set);

        String phrases[] = { "亦爲", "約翰˙克利斯多夫", "泡泡", "暗暗", "一心一意", "暑熱", "而已" };
        for (String phrase : phrases) {
            System.out.println(phrase + "："
                    + getCjcodesByPhrase(phrase, charCodesMap));
            System.out.println(phrase + "："
                    + getPhraseCode(phrase, charCodesMap));
        }
    }

    // 按碼表得到詞組的編碼
    //
    // 參數 mapByChar 單字的碼表做成的映射，字符為鍵，編碼列表爲值，getMbSetMapByChar()可以生成
    // 參數 phrase 詞組，如暑熱
    public static List<String> getPhraseCode(String phrase,
            Map<String, List<String>> mapByChar) {
        List<String> codes = getCjcodesByPhrase(phrase, mapByChar);
        if (!isCodesCorrect(codes, phrase)) {
            return null;
        }
        return getPhraseCodeByCjCodes(codes);
    }

    /**
     * 驗證是否正確
     * 
     * @author t
     * @time 2016-12-16上午12:05:51
     */
    private static boolean isCodesCorrect(List<String> codes, String phrase) {
        String tempPhrase = new String(phrase);
        for (int i = tempPhrase.length() - 1; i >= 0; i--) {
            Character c = tempPhrase.charAt(i);
            if (marks.contains(c.toString())) {
                tempPhrase = tempPhrase.replaceAll(c.toString(), "");
                i = tempPhrase.length(); // 不減1，因为要--
            } else {
                // 如果某字沒有編碼，返回false
                boolean coded = false;
                for (String code : codes) {
                    if (code.indexOf(c.toString()) != -1) {
                        coded = true;
                    }
                }
                if (!coded) {
                    return false;
                }
            }
        }
        // 如果得到的編碼個數比詞組長度都短，説明使用的編碼集不足，返回false
        return codes.size() >= tempPhrase.length();
    }

    // 把單字的碼表做成映射，字符為鍵，編碼列表爲值
    // 參數 set 單字的碼表，如：[a 日, a 曰, aa 昌, aa 昍, aaa 晶, aaa 晿,...
    public static Map<String, List<String>> getMbSetMapByChar(Set<String> set) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String codes : set) {
            if (codes.contains(" ")) {
                String[] keyVal = codes.split(" +");
                String cod = keyVal[0];
                String val = keyVal[1];
                if (!map.keySet().contains(val)) {
                    map.put(val, new ArrayList<String>());
                }
                map.get(val).add(cod);
            } else {
                System.out.println("碼表不對，如：" + codes);
            }
        }
        return map;
    }

    // 按字符的編碼，得到他們所組成的詞組編碼
    //
    // 參數 codes 詞組中每個字可能的編碼，卽getCjcodesByPhrase()返回的格式
    // 返回值 可能的詞組編碼，如：[aagif, aagkf]
    private static List<String> getPhraseCodeByCjCodes(List<String> codes) {
        List<ArrayList<String>> splitCodes = splitCodeParts(codes);
        return generateTempPhraseCode(splitCodes);
    }

    // 按一個词組，得到各個漢字的編碼，漢字不去重
    //
    // 參數 mapByChar 把單字的碼表做成映射，字符為鍵，編碼列表爲值
    // 參數 phrase 詞組，如暑熱
    // 返回值 返回如：[aqa 暑, aqia 暑, gif 熱, gkf 熱]
    private static List<String> getCjcodesByPhrase(String phrase,
            Map<String, List<String>> mapByChar) {
        List<String> codes = new ArrayList<String>();
        for (int i = 0; i < phrase.length(); i++) {
            Character c = phrase.charAt(i);
            List<String> charCodes = mapByChar.get(c.toString());
            // 不能是符號
            if (!marks.contains(c.toString()) && null != charCodes
                    && !charCodes.isEmpty()) {
                for (String code : charCodes) {
                    codes.add(code + " " + c.toString());
                }
            }
        }
        return codes;
    }

    // 如果字有多个编码，会生成列表的多个元素
    private static List<ArrayList<String>> splitCodeParts(List<String> codes) {
        List<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
        List<String> charList = new ArrayList<String>();
        List<ArrayList<String>> codeLists = new ArrayList<ArrayList<String>>();
        int resIndex = 0;
        for (int n = 0; n < codes.size(); n++) {
            String s = codes.get(n);
            String[] keyVal = s.split(" +");
            String cod = keyVal[0];
            String val = keyVal[1];

            // 與前一字一樣，但是新編碼
            if (n != 0 && charList.get(resIndex - 1).equals(val)
                    && !codeLists.get(resIndex - 1).contains(cod)) {
                codeLists.get(resIndex - 1).add(cod);
            } else {
                charList.add(val);
                ArrayList<String> codeList = new ArrayList<String>();
                codeList.add(cod);
                codeLists.add(codeList);

                resIndex++;
            }
        }
        lists.add(new ArrayList<String>());
        for (ArrayList<String> codeList : codeLists) {
            for (int index = 0; index < codeList.size(); index++) {
                String code = codeList.get(index);

                if (index == 0) {
                    for (ArrayList<String> list : lists) {
                        list.add(code);
                    }
                } else { // 加倍
                    List<ArrayList<String>> tempLists = new ArrayList<ArrayList<String>>();
                    for (ArrayList<String> list : lists) {
                        tempLists.add(new ArrayList<String>(list.subList(0,
                                list.size() - 1)));
                    }
                    for (ArrayList<String> list : tempLists) {
                        list.add(code);
                    }
                    lists.addAll(tempLists);
                }
            }
        }
        return lists;
    }

    // 按各字編码生成詞組編碼
    private static List<String> generateTempPhraseCode(
            List<ArrayList<String>> list) {
        // 不用set就可能重複編碼同一个词
        Set<String> codes = new LinkedHashSet<String>();
        String tempcode = null;
        for (ArrayList<String> one : list) {
            tempcode = generateTempPhraseCodeOne(one);
            if (!codes.contains(tempcode)) {
                codes.add(tempcode);
            }
        }
        codes.add(tempcode);
        return new ArrayList<String>(codes);
    }

    // 按各字編码生成詞組編碼，方法一
    // 一個字，直接返回。
    // 兩個字：首尾+一二尾，沒有就不取。
    // 三個字：正好作爲三部分；
    // 四個字：一二、三、四字，作爲三部分；
    // 五個字及以上：一二字、三至倒數第二字、末字，作爲三部分。
    // 三部分的取碼：首尾+首尾+尾，或首尾+全+首尾；
    // 第二部分只有一個碼時，第三部分就可以取兩碼，否则第三部分只能取尾碼
    private static String generateTempPhraseCodeOne(List<String> list) {
        String code = "";
        if (list.size() == 1) {
            // 一個字，直接返回
            code = list.get(0).split(" +")[0];
        } else if (list.size() == 2) {
            // 兩個字：首尾+一二尾
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];

            code += code1.charAt(0);
            if (code1.length() > 1) {
                code += code1.charAt(code1.length() - 1);
            }
            code += code2.charAt(0);
            if (code2.length() > 1) {
                code += code2.charAt(1);
            }
            if (code2.length() > 2) {
                code += code2.charAt(code2.length() - 1);
            }
        } else if (list.size() >= 3) {
            // 三個字
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];
            if (list.size() >= 4) {
                code1 += code2;
                code2 = list.get(2).split(" +")[0];
                if (list.size() >= 5) {
                    code2 += list.get(list.size() - 2).split(" +")[0];
                }
            }
            String code3 = list.get(list.size() - 1).split(" +")[0];

            // code1
            code += code1.charAt(0);
            if (code1.length() > 1) {
                code += code1.charAt(code1.length() - 1);
            }
            // code2
            boolean code3has2 = true; // 第三部分要取兩碼
            code += code2.charAt(0);
            if (code2.length() > 1) {
                code += code2.charAt(code2.length() - 1);
                code3has2 = false;
            }
            // code3
            if (code3has2 && code3.length() > 1) {
                code += code3.charAt(0);
            }
            code += code3.charAt(code3.length() - 1);
        }
        return code;
    }

    // 按各字編码生成詞組編碼，方法一
    // 一個字，直接返回。
    // 兩個字：首尾+一二尾，沒有就不取。
    // 三個字：正好作爲三部分；
    // 四個字：一二、三、四字，作爲三部分；
    // 五個字及以上：一二字、三至倒數第二字、末字，作爲三部分。
    // 三部分的取碼：首尾+首尾+尾，沒有就算了
    private static String generateTempPhraseCodeOneSimple(List<String> list) {
        String code = "";
        if (list.size() == 1) {
            // 一個字，直接返回
            code = list.get(0).split(" +")[0];
        } else if (list.size() == 2) {
            // 兩個字：首尾+一二尾
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];

            code += code1.charAt(0);
            if (code1.length() > 1) {
                code += code1.charAt(code1.length() - 1);
            }
            code += code2.charAt(0);
            if (code2.length() > 1) {
                code += code2.charAt(1);
            }
            if (code2.length() > 2) {
                code += code2.charAt(code2.length() - 1);
            }
        } else if (list.size() >= 3) {
            // 三個字
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];
            if (list.size() >= 4) {
                code1 += code2;
                code2 = list.get(2).split(" +")[0];
                if (list.size() >= 5) {
                    code2 += list.get(list.size() - 2).split(" +")[0];
                }
            }
            String code3 = list.get(list.size() - 1).split(" +")[0];

            // code1
            code += code1.charAt(0);
            if (code1.length() > 1) {
                code += code1.charAt(code1.length() - 1);
            }
            // code2
            code += code2.charAt(0);
            if (code2.length() > 1) {
                code += code2.charAt(code2.length() - 1);
            }
            // code3
            code += code3.charAt(code3.length() - 1);
        }
        return code;
    }

    // 按各字編码生成詞組編碼方法二，都拼出五碼，沒有就算了
    // 一個字，直接返回
    // 兩個字，5碼，每字取1首尾、2首二尾碼
    // 三個字，5碼，1首、2首、3首二尾
    // 四個字，5碼，1首、2首、3首、4首尾
    // 五字以上，5碼，1首、2首、3首、4首、-1尾
    private static String generateTempPhraseCodeOne5555light(List<String> list) {
        String code = "";
        if (list.size() == 1) {
            // 一個字，直接返回
            code = list.get(0).split(" +")[0];
        } else if (list.size() == 2) {
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];

            code += code1.charAt(0);
            if (code1.length() > 1) {
                code += code1.charAt(code1.length() - 1);
            }
            code += code2.charAt(0);
            if (code2.length() > 1) {
                code += code2.charAt(1);
            }
            if (code2.length() > 2) {
                code += code2.charAt(code2.length() - 1);
            }
        } else if (list.size() == 3) {
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];
            String code3 = list.get(2).split(" +")[0];

            code += code1.charAt(0);
            code += code2.charAt(0);
            code += code3.charAt(0);
            if (code3.length() > 1) {
                code += code3.charAt(1);
            }
            if (code3.length() > 2) {
                code += code3.charAt(code3.length() - 1);
            }
        } else if (list.size() == 4) {
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];
            String code3 = list.get(2).split(" +")[0];
            String code4 = list.get(3).split(" +")[0];

            code += code1.charAt(0);
            code += code2.charAt(0);
            code += code3.charAt(0);
            code += code4.charAt(0);
            if (code4.length() > 1) {
                code += code4.charAt(code4.length() - 1);
            }
        } else {
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];
            String code3 = list.get(2).split(" +")[0];
            String code4 = list.get(3).split(" +")[0];
            String code5 = list.get(list.size() - 1).split(" +")[0];

            code += code1.charAt(0);
            code += code2.charAt(0);
            code += code3.charAt(0);
            code += code4.charAt(0);
            code += code5.charAt(code5.length() - 1);
        }
        return code;
    }

    // 按各字編码生成詞組編碼方法二
    // 一個字，直接返回
    // 兩個字，4碼，每字取首尾兩碼，只一碼的，算首又算尾，所以重複它就得了
    // 三個字，4碼，首、首、首尾
    // 四個字，5碼，首、首、首、首尾
    // 五字以上，5碼，首、首、首、首、尾
    private static String generateTempPhraseCodeOne4455(List<String> list) {
        String code = "";
        if (list.size() == 1) {
            // 一個字，直接返回
            code = list.get(0).split(" +")[0];
        } else if (list.size() == 2) {
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];

            code += code1.charAt(0);
            code += code1.charAt(code1.length() - 1);
            code += code2.charAt(0);
            code += code2.charAt(code2.length() - 1);
        } else if (list.size() == 3) {
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];
            String code3 = list.get(2).split(" +")[0];

            code += code1.charAt(0);
            code += code2.charAt(0);
            code += code3.charAt(0);
            code += code3.charAt(code3.length() - 1);
        } else if (list.size() == 4) {
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];
            String code3 = list.get(2).split(" +")[0];
            String code4 = list.get(3).split(" +")[0];

            code += code1.charAt(0);
            code += code2.charAt(0);
            code += code3.charAt(0);
            code += code4.charAt(0);
            code += code4.charAt(code4.length() - 1);
        } else {
            String code1 = list.get(0).split(" +")[0];
            String code2 = list.get(1).split(" +")[0];
            String code3 = list.get(2).split(" +")[0];
            String code4 = list.get(3).split(" +")[0];
            String code5 = list.get(list.size() - 1).split(" +")[0];

            code += code1.charAt(0);
            code += code2.charAt(0);
            code += code3.charAt(0);
            code += code4.charAt(0);
            code += code5.charAt(code5.length() - 1);
        }
        return code;
    }

}