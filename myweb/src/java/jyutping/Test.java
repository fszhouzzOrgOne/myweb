package jyutping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.PhraseTest;
import cangjie.java.util.IOUtils;

public class Test {

    public static String mbsBaseDir = "src\\java\\jyutping\\";

    public static void main(String[] args) throws Exception {
        // List<String> res = readMbCode_char_char(mbsBaseDir +
        // "粤语发声字典081217.txt");
        // IOUtils.writeFile(mbsBaseDir + "粤语发声字典081217_2.txt", res);

        mergeMbs();

        // 編碼粵拼的詞組
        String mbFile = mbsBaseDir + "jyutping-all.txt";
        String jyutPhrase = mbsBaseDir + "jyutping-phrases.txt";
        
        String srcPhraseFile = Cj00AllInOneTest.phraseOriginFile;
        PhraseTest.uniqueOrderPhraseFile();
        List<String> phrases = getJyutpingPhrase(mbFile, srcPhraseFile);
        IOUtils.writeFile(jyutPhrase, phrases);

        // 粵拼碼表整合
        // 用Q的個數表示聲調
        List<String> mb = IOUtils.readLines(mbFile);
        List<String> jyutPhrases = IOUtils.readLines(jyutPhrase);
        List<String> jyutAllInOne = new ArrayList<String>();
        for (String str : mb) {
            if (null != str && str.trim().length() > 0) {
                jyutAllInOne.add(str.replace("1", "q").replace("2", "qq").replace("3", "qqq").replace("4", "qqqq")
                        .replace("5", "qqqqq").replace("6", "qqqqqq"));
            }
        }
        // jyutAllInOne.addAll(jyutPhrases);
        IOUtils.writeFile(mbsBaseDir + "jyutping-allInOne.txt", jyutAllInOne);
    }

    /**
     * 編碼粵拼的詞組
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月14日 下午9:39:08
     * @param mbFile
     * @param srcPhraseFile
     * @return
     * @throws Exception
     */
    private static List<String> getJyutpingPhrase(String mbFile, String srcPhraseFile) throws Exception {
        List<String> mb = IOUtils.readLines(mbFile);
        Map<String, List<String>> charJyutpMap = new HashMap<String, List<String>>();
        for (String line : mb) {
            String[] part = line.split(" ");
            String code = part[0];
            String cha = part[1];
            List<String> list = charJyutpMap.get(cha);
            if (null == list) {
                list = new ArrayList<String>();
            }
            String codeSim = code.replaceAll("[0-9]+", "");
            if (!list.contains(codeSim)) {
                list.add(codeSim);
            }
            charJyutpMap.put(cha, list);
        }

        // 編碼詞組
        System.out.println("開始編碼詞組：");
        List<String> phrs = IOUtils.readLines(srcPhraseFile);
        List<String> res = new ArrayList<String>();
        outerFor: for (String phr : phrs) {
            if (null != phr && phr.trim().length() > 0) {
                char[] chars = phr.trim().toCharArray();
                List<List<String>> lists = new ArrayList<List<String>>();
                for (Character cha : chars) {
                    List<String> charCodes = charJyutpMap.get(cha.toString());
                    if (null == charCodes) {
                        continue outerFor;
                    } else {
                        lists.add(charCodes);
                    }
                }

                // 保存生成的所有編碼
                List<String> tempRes = new ArrayList<String>();
                for (List<String> oneList : lists) {
                    if (tempRes.isEmpty()) {
                        tempRes.addAll(oneList);
                    } else if (oneList.size() > 1) {
                        // 加倍，oneList有幾個元素就加多少倍
                        List<List<String>> tempRes2 = new ArrayList<List<String>>();
                        for (int i = 0; i < oneList.size(); i++) {
                            tempRes2.add(new ArrayList<String>(tempRes));
                        }
                        // 第一份，都加上第一個編碼；第N份，都加上第N個編碼……
                        for (int i = 0; i < oneList.size(); i++) {
                            List<String> temp = tempRes2.get(i);
                            List<String> tempNew = new ArrayList<String>();
                            for (String str : temp) {
                                tempNew.add(str + oneList.get(i));
                            }
                            tempRes2.set(i, tempNew);
                        }
                        tempRes.clear();
                        for (List<String> ls : tempRes2) {
                            tempRes.addAll(ls);
                        }
                    } else {
                        List<String> tempRes2 = new ArrayList<String>();
                        for (String t : tempRes) {
                            tempRes2.add(t + oneList.get(0));
                        }
                        tempRes = tempRes2;
                    }
                }
                for (String code : tempRes) {
                    res.add(code + " " + phr);
                }
            }
        }
        System.out.println("結束編碼詞組。");
        return res;
    }

    /**
     * 讀取碼表：編碼 空格 文字 空格 文字 空格 文字……
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月14日 下午10:27:18
     * @param filename
     * @return
     */
    public static List<String> readMbCode_char_char(String filename) {
        List<String> list = IOUtils.readLines(filename);
        List<String> res = new ArrayList<String>();

        for (String line : list) {
            String[] parts = line.split(" ");
            for (int i = 1; i < parts.length; i++) {
                res.add(parts[0] + " " + parts[i]);
            }
        }
        return res;
    }

    /**
     * 合併兩個粵拼的碼表
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月14日 下午10:26:45
     * @throws Exception
     */
    public static void mergeMbs() throws Exception {
        // 合併
        List<String> list1 = IOUtils.readLines(mbsBaseDir + "jyutping-dict.txt");
        List<String> list2 = IOUtils.readLines(mbsBaseDir + "jyutping-github.txt");
        List<String> list3 = new ArrayList<String>();
        list3.add("ling4 〇");
        list3.add("q  ˥˧");
        list3.add("q  ˥");
        list3.add("qq ˧˥");
        list3.add("qqq ˧˧");
        list3.add("qqq ˧");
        list3.add("qqqq ˨˩");
        list3.add("qqqqq ˨˧");
        list3.add("qqqqqq ˨˨");
        list3.add("qqqqqq ˨");
        List<String> listall = new ArrayList<String>(list1);
        listall.addAll(list2);
        listall.addAll(list3);
        Collections.sort(listall);
        IOUtils.writeFile(mbsBaseDir + "jyutping-all.txt", listall);
        IOUtils.uniqueCodeFile(mbsBaseDir + "jyutping-all.txt");
    }
}
