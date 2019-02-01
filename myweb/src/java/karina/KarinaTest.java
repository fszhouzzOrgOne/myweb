package karina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;
import unicode.UnicodeConvertUtil;

/**
 * 日語假名編碼
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月4日下午2:24:21
 */
public class KarinaTest {

    private static String mbsBaseDir = "src\\java\\karina\\mb\\";

    // 三字以下
    // 單字 48611
    private static String singleCharPtn = "^.* .{1,1}$";;

    public static void main(String[] args) throws Exception {
        List<String> dictKanji1 = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-漢字部分2.txt");
        List<String> dictKanji2 = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-漢字部分2待編碼.txt");
        List<String> dictKarina = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-假名部分2.txt");
        List<String> dictMore = IOUtils.readLines(mbsBaseDir + "更多漢字補充.txt");

        // 編碼
        Set<String> dict = new HashSet<String>();
        dict.addAll(dictKanji1);
        dict.addAll(dictKanji2);
        dict.addAll(dictKarina);
        dict.addAll(dictMore);
        List<String> res = new ArrayList<String>();
        for (String ji : dict) {
            List<String> one = encodeRomaji(ji);
            res.addAll(one);
        }

        // 排除結果中的全假名詞
        List<String> res2 = new ArrayList<String>();
        for (String re : res) {
            if (re.contains(" ")) {
                String[] parts = re.split(" ");
                if (!parts[1].matches(Romaji2KarinaTest.normalKarinaPtn)) {
                    res2.add(re);
                }
            }
        }
        res = res2;

        // 寫結果到文件
        String file = mbsBaseDir + "新日漢大辭典純漢字詞編碼.txt";
        IOUtils.writeFile(file, res);
        IOUtils.uniqueCodeFile(file);
        IOUtils.orderCodeFile(file);

        Set<String> resSinCodes = new HashSet<String>();
        List<String> resSins = new ArrayList<String>();
        String sinPtn = singleCharPtn;
        for (String ji : res) {
            if (ji.matches(sinPtn)) {
                resSinCodes.add(ji.split(" ")[0]);
            }
        }
        for (String ji : res) {
            if (resSinCodes.contains(ji.split(" ")[0])) {
                resSins.add(ji);
            }
        }
        String fileSin = Cj00AllInOneTest.mbnipponKanjiMore;
        IOUtils.writeFile(fileSin, resSins);
        IOUtils.uniqueCodeFile(fileSin);
        IOUtils.orderCodeFile(fileSin);

        Set<String> cntSet = new HashSet<String>();
        for (String ji : res) {
            String suffix = ji.split(" ")[1];
            if (!cntSet.contains(suffix)) {
                cntSet.add(suffix);
            }
        }
        System.out.println("cntSet.size(): " + cntSet.size());
    }

    /** 片假名的長音 */
    public static String changyin = "ー";
    /** 平假名和片假名的對應，都有88個 */
    public static String[] karinas = {
            "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんゔゕゖゝゞ",
            "ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶヽヾ" };

    /** 平假名列表 */
    public static List<String> hiraganaList = new ArrayList<String>();
    /** 片假名列表 */
    public static List<String> katakanaList = new ArrayList<String>();
    /** 假名和羅馬字的映射，注意さ行和ざ行 */
    public static Map<String, String> karinaRomaMap = new HashMap<String, String>();
    /** 羅馬字兼容拼法的映射，只用兼容常用寫法 */
    public static Map<String, String> romaCompatMap = new HashMap<String, String>();

    static {
        karinaRomaMap.put("ぁ", "a");
        karinaRomaMap.put("ァ", "a");
        karinaRomaMap.put("あ", "a");
        karinaRomaMap.put("ア", "a");
        karinaRomaMap.put("ぃ", "i");
        karinaRomaMap.put("ィ", "i");
        karinaRomaMap.put("い", "i");
        karinaRomaMap.put("イ", "i");
        karinaRomaMap.put("ぅ", "u");
        karinaRomaMap.put("ゥ", "u");
        karinaRomaMap.put("う", "u");
        karinaRomaMap.put("ウ", "u");
        karinaRomaMap.put("ぇ", "e");
        karinaRomaMap.put("ェ", "e");
        karinaRomaMap.put("え", "e");
        karinaRomaMap.put("エ", "e");
        karinaRomaMap.put("ぉ", "o");
        karinaRomaMap.put("ォ", "o");
        karinaRomaMap.put("お", "o");
        karinaRomaMap.put("オ", "o");
        karinaRomaMap.put("か", "ka");
        karinaRomaMap.put("カ", "ka");
        karinaRomaMap.put("が", "ga");
        karinaRomaMap.put("ガ", "ga");
        karinaRomaMap.put("き", "ki");
        karinaRomaMap.put("キ", "ki");
        karinaRomaMap.put("ぎ", "gi");
        karinaRomaMap.put("ギ", "gi");
        karinaRomaMap.put("く", "ku");
        karinaRomaMap.put("ク", "ku");
        karinaRomaMap.put("ぐ", "gu");
        karinaRomaMap.put("グ", "gu");
        karinaRomaMap.put("け", "ke");
        karinaRomaMap.put("ケ", "ke");
        karinaRomaMap.put("げ", "ge");
        karinaRomaMap.put("ゲ", "ge");
        karinaRomaMap.put("こ", "ko");
        karinaRomaMap.put("コ", "ko");
        karinaRomaMap.put("ご", "go");
        karinaRomaMap.put("ゴ", "go");
        karinaRomaMap.put("さ", "sa");
        karinaRomaMap.put("サ", "sa");
        karinaRomaMap.put("ざ", "za");
        karinaRomaMap.put("ザ", "za");
        karinaRomaMap.put("し", "si");
        karinaRomaMap.put("シ", "si");
        karinaRomaMap.put("じ", "zi");
        karinaRomaMap.put("ジ", "zi");
        karinaRomaMap.put("す", "su");
        karinaRomaMap.put("ス", "su");
        karinaRomaMap.put("ず", "zu");
        karinaRomaMap.put("ズ", "zu");
        karinaRomaMap.put("せ", "se");
        karinaRomaMap.put("セ", "se");
        karinaRomaMap.put("ぜ", "ze");
        karinaRomaMap.put("ゼ", "ze");
        karinaRomaMap.put("そ", "so");
        karinaRomaMap.put("ソ", "so");
        karinaRomaMap.put("ぞ", "zo");
        karinaRomaMap.put("ゾ", "zo");
        karinaRomaMap.put("た", "ta");
        karinaRomaMap.put("タ", "ta");
        karinaRomaMap.put("だ", "da");
        karinaRomaMap.put("ダ", "da");
        karinaRomaMap.put("ち", "ti");
        karinaRomaMap.put("チ", "ti");
        karinaRomaMap.put("ぢ", "di");
        karinaRomaMap.put("ヂ", "di");
        karinaRomaMap.put("っ", "tu");
        karinaRomaMap.put("ッ", "tu");
        karinaRomaMap.put("つ", "tu");
        karinaRomaMap.put("ツ", "tu");
        karinaRomaMap.put("づ", "du");
        karinaRomaMap.put("ヅ", "du");
        karinaRomaMap.put("て", "te");
        karinaRomaMap.put("テ", "te");
        karinaRomaMap.put("で", "de");
        karinaRomaMap.put("デ", "de");
        karinaRomaMap.put("と", "to");
        karinaRomaMap.put("ト", "to");
        karinaRomaMap.put("ど", "do");
        karinaRomaMap.put("ド", "do");
        karinaRomaMap.put("な", "na");
        karinaRomaMap.put("ナ", "na");
        karinaRomaMap.put("に", "ni");
        karinaRomaMap.put("ニ", "ni");
        karinaRomaMap.put("ぬ", "nu");
        karinaRomaMap.put("ヌ", "nu");
        karinaRomaMap.put("ね", "ne");
        karinaRomaMap.put("ネ", "ne");
        karinaRomaMap.put("の", "no");
        karinaRomaMap.put("ノ", "no");
        karinaRomaMap.put("は", "ha");
        karinaRomaMap.put("ハ", "ha");
        karinaRomaMap.put("ば", "ba");
        karinaRomaMap.put("バ", "ba");
        karinaRomaMap.put("ぱ", "pa");
        karinaRomaMap.put("パ", "pa");
        karinaRomaMap.put("ひ", "hi");
        karinaRomaMap.put("ヒ", "hi");
        karinaRomaMap.put("び", "bi");
        karinaRomaMap.put("ビ", "bi");
        karinaRomaMap.put("ぴ", "pi");
        karinaRomaMap.put("ピ", "pi");
        karinaRomaMap.put("ふ", "hu");
        karinaRomaMap.put("フ", "hu");
        karinaRomaMap.put("ぶ", "bu");
        karinaRomaMap.put("ブ", "bu");
        karinaRomaMap.put("ぷ", "pu");
        karinaRomaMap.put("プ", "pu");
        karinaRomaMap.put("へ", "he");
        karinaRomaMap.put("ヘ", "he");
        karinaRomaMap.put("べ", "be");
        karinaRomaMap.put("ベ", "be");
        karinaRomaMap.put("ぺ", "pe");
        karinaRomaMap.put("ペ", "pe");
        karinaRomaMap.put("ほ", "ho");
        karinaRomaMap.put("ホ", "ho");
        karinaRomaMap.put("ぼ", "bo");
        karinaRomaMap.put("ボ", "bo");
        karinaRomaMap.put("ぽ", "po");
        karinaRomaMap.put("ポ", "po");
        karinaRomaMap.put("ま", "ma");
        karinaRomaMap.put("マ", "ma");
        karinaRomaMap.put("み", "mi");
        karinaRomaMap.put("ミ", "mi");
        karinaRomaMap.put("む", "mu");
        karinaRomaMap.put("ム", "mu");
        karinaRomaMap.put("め", "me");
        karinaRomaMap.put("メ", "me");
        karinaRomaMap.put("も", "mo");
        karinaRomaMap.put("モ", "mo");
        karinaRomaMap.put("ゃ", "ya");
        karinaRomaMap.put("ャ", "ya");
        karinaRomaMap.put("や", "ya");
        karinaRomaMap.put("ヤ", "ya");
        karinaRomaMap.put("ゅ", "yu");
        karinaRomaMap.put("ュ", "yu");
        karinaRomaMap.put("ゆ", "yu");
        karinaRomaMap.put("ユ", "yu");
        karinaRomaMap.put("ょ", "yo");
        karinaRomaMap.put("ョ", "yo");
        karinaRomaMap.put("よ", "yo");
        karinaRomaMap.put("ヨ", "yo");
        karinaRomaMap.put("ら", "ra");
        karinaRomaMap.put("ラ", "ra");
        karinaRomaMap.put("り", "ri");
        karinaRomaMap.put("リ", "ri");
        karinaRomaMap.put("る", "ru");
        karinaRomaMap.put("ル", "ru");
        karinaRomaMap.put("れ", "re");
        karinaRomaMap.put("レ", "re");
        karinaRomaMap.put("ろ", "ro");
        karinaRomaMap.put("ロ", "ro");
        karinaRomaMap.put("ゎ", "wa");
        karinaRomaMap.put("ヮ", "wa");
        karinaRomaMap.put("わ", "wa");
        karinaRomaMap.put("ワ", "wa");
        karinaRomaMap.put("ゐ", "wi");
        karinaRomaMap.put("ヰ", "wi");
        karinaRomaMap.put("ゑ", "we");
        karinaRomaMap.put("ヱ", "we");
        karinaRomaMap.put("を", "wo");
        karinaRomaMap.put("ヲ", "wo");
        karinaRomaMap.put("ん", "n");
        karinaRomaMap.put("ン", "n");
        karinaRomaMap.put("ヴ", "v");
        karinaRomaMap.put("ヵ", "ka");
        karinaRomaMap.put("ヶ", "ke");

        // 不兼容了
        romaCompatMap.put("si", "shi");
        romaCompatMap.put("zi", "ji");
        romaCompatMap.put("ti", "chi");
        romaCompatMap.put("tu", "tsu");
        romaCompatMap.put("di", "ji");
        romaCompatMap.put("du", "zu");
        romaCompatMap.put("sy", "sh"); // -shy
        romaCompatMap.put("ty", "ch"); // -chy
        romaCompatMap.put("zy", "j"); // -jy
        romaCompatMap.put("dy", "j"); // -jy

        char[] hiraganas = karinas[0].toCharArray();
        char[] katakanas = karinas[1].toCharArray();
        for (Character cha : hiraganas) {
            hiraganaList.add(cha.toString());
        }
        for (Character cha : katakanas) {
            katakanaList.add(cha.toString());
        }
    }

    /**
     * 生成對應假名的羅馬字編碼
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月5日下午2:10:36
     * @param parms
     *            假名+空格+漢字串
     * @return
     */
    private static List<String> encodeRomaji(String parms) {
        String karina = null;
        String kanji = null;
        if (parms.contains(" ")) {
            String[] part = parms.split(" ");
            karina = part[0];
            kanji = part[1];
        } else {
            karina = parms;
        }
        if (null == karina) {
            return null;
        }

        // 全是片假名
        boolean isAllkatakana = karina.matches("[ァ-ヾ]+");
        String hiraStr = katakana2Hiragana(karina);

        // か行、さ行、た行、ぱ行前可能有促音。
        String roma = "";
        for (int i = 0; i < hiraStr.length(); i++) {
            String st = ((Character) hiraStr.charAt(i)).toString();
            String roma1 = karinaRomaMap.get(st);
            if ("っ".equals(st)) {
                if (hiraStr.length() > i + 1) {
                    roma1 = getRomaji4PreTsu(((Character) hiraStr.charAt(i + 1)).toString());
                }
            } else if ("きぎしじちぢにひびぴみりキギシジチヂニヒビピミリ".contains(st) && (hiraStr.length() > i + 1)) {
                String next = ((Character) hiraStr.charAt(i + 1)).toString();
                if ("ゃゅょャュョ".contains(next)) {
                    roma1 = roma1.substring(0, roma1.length() - 1);
                    roma1 += karinaRomaMap.get(next);
                    i++;
                }
            }
            roma += roma1;
        }
        // 兼容拼法
        List<String> codes = new ArrayList<String>();
        codes.add(roma);
        List<String> codes2 = new ArrayList<String>();
        for (String code : codes) {
            String tempCode = code;
            boolean compated = false;
            for (String key : romaCompatMap.keySet()) {
                if (roma.contains(key)) {
                    if (!compated) {
                        compated = true;
                    }
                    String compat = romaCompatMap.get(key);
                    tempCode = tempCode.replaceAll(key, compat);
                }
            }
            if (compated) {
                codes2.add(tempCode);
            }
        }
        codes.addAll(codes2);

        List<String> res = new ArrayList<String>();
        for (String code : codes) {
            res.add(code + " " + karina);
            if (isAllkatakana) {
                res.add(code + " " + hiraStr);
            }
            if (null != kanji) {
                res.add(code + " " + kanji);
            }
        }
        return res;
    }

    /**
     * 促音用甚麼羅馬字表示<br/>
     * か行、さ行、た行、ぱ行、だ行前可能有促音。
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月5日 下午10:43:51
     * @param string
     */
    private static String getRomaji4PreTsu(String string) {
        String suffix = string.substring(string.length() - 1);
        if ("かきくけこカキクケコ".contains(suffix)) {
            return "k";
        } else if ("さしすせそサシスセソ".contains(suffix)) {
            return "s";
        } else if ("たちつてとタチツテト".contains(suffix)) {
            return "t";
        } else if ("ぱぴぷぺぽパピプペポ".contains(suffix)) {
            return "p";
        } else if ("だぢづでどダヂヅデド".contains(suffix)) {
            return "d";
        }
        return "";
    }

    /**
     * 片假名轉成平假名
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月5日下午4:11:03
     * @param str
     *            片假名
     * @return
     */
    public static String katakana2Hiragana(String str) {
        String res = "";
        int i = 0;
        for (; i < str.length(); i++) {
            String st = str.charAt(i) + "";
            if (hiraganaList.contains(st)) {
                res += st;
            } else if (changyin.equals(st)) {
                Character pre = str.charAt(i - 1);
                String changyinCha = getChangyin4karina(pre + "");
                res += hiraganaList.get(katakanaList.indexOf(changyinCha));
            } else {
                res += hiraganaList.get(katakanaList.indexOf(st));
            }
        }
        return res;
    }

    /**
     * 得到假名對應的長音
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月5日 下午9:36:45
     * @param pre
     * @return
     */
    private static String getChangyin4karina(String pre) {
        pre = pre.substring(pre.length() - 1);
        // 是平假名
        boolean isHiragana = karinas[0].contains(pre);
        if (isHiragana) {
            pre = katakanaList.get(hiraganaList.indexOf(pre));
        }

        String res = null;
        if ("ァアカガサザタダナハバパマラャヤヮワヵ".contains(pre)) {
            res = "ア";
        } else if ("ゥウクグスズツヅヌフブプムルュユッヴ".contains(pre) || "ォオコゴソゾトドノホボポモロョヨヲ".contains(pre)) {
            res = "ウ";
        } else if ("ィイキギシジチヂニヒビピミリヰ".contains(pre) || "ェエケゲセゼテデネヘベペメレヶヱ".contains(pre)) {
            res = "イ";
        }
        if (isHiragana) {
            res = hiraganaList.get(katakanaList.indexOf(res));
        }
        return res;
    }

    /**
     * 打印所有的假名區字
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月5日上午11:46:57
     */
    @SuppressWarnings("unused")
    private static void printAllKarina() {
        String all = "";
        List<String> excepts = new ArrayList<String>();
        excepts.add("぀");
        excepts.add("゗");
        excepts.add("゘");
        for (int i = 0x3040; i < 0x30FF; i++) {
            String one = UnicodeConvertUtil.getStringByUnicode(i);
            System.out.println(one);
            if (!excepts.contains(one)) {
                all += one;
            }
        }
        System.out.println(all);

    }
}
