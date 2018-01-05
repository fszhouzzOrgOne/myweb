package karina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cangjie.java.util.IOUtils;
import unicode.UnicodeHanziUtil;

/**
 * 日語假名編碼
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月4日下午2:24:21
 */
public class KarinaTest {

    /** 片假名的長音 */
    private static String changyin = "ー";
    /** 平假名和片假名的對應，都有88個 */
    private static String[] karinas = {
            "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんゔゕゖゝゞ",
            "ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶヽヾ" };

    private static List<String> hiraganaList = new ArrayList<String>();
    private static List<String> katakanaList = new ArrayList<String>();

    static {
        char[] hiraganas = karinas[0].toCharArray();
        char[] katakanas = karinas[1].toCharArray();
        for (Character cha : hiraganas) {
            hiraganaList.add(cha.toString());
        }
        for (Character cha : katakanas) {
            katakanaList.add(cha.toString());
        }
    }

    private static String mbsBaseDir = "src\\java\\karina\\mb\\";

    public static void main(String[] args) {
        List<String> dictKanji = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-漢字部分2待編碼.txt");
        for (String ji : dictKanji) {
            String part[] = ji.split(" ");
            System.out.println(part[0] + " " + encodeRomaji(part[0]));
        }
    }

    /**
     * 生成對應假名的羅馬字編碼
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月5日下午2:10:36
     * @param parm
     *            假名串
     * @return
     */
    private static String encodeRomaji(String parm) {
        // 全是平假名
        boolean isAllkatakana = parm.matches("[ァ-ヾ]+");
        String hiraStr = katakana2Hiragana(parm);

        // か行、さ行、た行、ぱ行前可能有促音。
        String tsuPtn = ".+(ッ|っ.)+";
        String roma = "";
        for (int i = 0; i < hiraStr.length(); i++) {
            String st = ((Character) hiraStr.charAt(i)).toString();
            String roma1 = getgetRomajiForOneKarina(st);
            if ("っ".equals(st)) {
                if (hiraStr.length() > i + 1) {
                    roma1 = getRomaji4PreTsu(((Character) hiraStr.charAt(i + 1)).toString());
                }
            } else if ("きぎしじちぢにひびぴみりキギシジチヂニヒビピミリ".contains(st) && (hiraStr.length() > i + 1)) {
                String next = ((Character) hiraStr.charAt(i + 1)).toString();
                if ("ゃゅょャュョ".contains(next)) {
                    roma1 = roma1.substring(0, roma1.length() - 1);
                    roma1 += getgetRomajiForOneKarina(next);
                    i++;
                }
            }
            roma += roma1;
        }
        return roma;
    }

    /**
     * 促音用甚麼羅馬字表示
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
    private static String katakana2Hiragana(String str) {
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
    private static void printAllKarina() {
        String all = "";
        List<String> excepts = new ArrayList<String>();
        excepts.add("぀");
        excepts.add("゗");
        excepts.add("゘");
        for (int i = 0x3040; i < 0x30FF; i++) {
            String one = UnicodeHanziUtil.getStringByUnicode(i);
            System.out.println(one);
            if (!excepts.contains(one)) {
                all += one;
            }
        }
        System.out.println(all);

    }

    /**
     * 按一個假名取得羅馬字
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月5日 下午11:08:31
     * @param kana
     * @return
     */
    private static String getgetRomajiForOneKarina(String kana) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ぁ", "a");
        map.put("ァ", "a");
        map.put("あ", "a");
        map.put("ア", "a");
        map.put("ぃ", "i");
        map.put("ィ", "i");
        map.put("い", "i");
        map.put("イ", "i");
        map.put("ぅ", "u");
        map.put("ゥ", "u");
        map.put("う", "u");
        map.put("ウ", "u");
        map.put("ぇ", "e");
        map.put("ェ", "e");
        map.put("え", "e");
        map.put("エ", "e");
        map.put("ぉ", "o");
        map.put("ォ", "o");
        map.put("お", "o");
        map.put("オ", "o");
        map.put("か", "ka");
        map.put("カ", "ka");
        map.put("が", "ga");
        map.put("ガ", "ga");
        map.put("き", "ki");
        map.put("キ", "ki");
        map.put("ぎ", "gi");
        map.put("ギ", "gi");
        map.put("く", "ku");
        map.put("ク", "ku");
        map.put("ぐ", "gu");
        map.put("グ", "gu");
        map.put("け", "ke");
        map.put("ケ", "ke");
        map.put("げ", "ge");
        map.put("ゲ", "ge");
        map.put("こ", "ko");
        map.put("コ", "ko");
        map.put("ご", "go");
        map.put("ゴ", "go");
        map.put("さ", "sa");
        map.put("サ", "sa");
        map.put("ざ", "za");
        map.put("ザ", "za");
        map.put("し", "si");
        map.put("シ", "si");
        map.put("じ", "zi");
        map.put("ジ", "zi");
        map.put("す", "su");
        map.put("ス", "su");
        map.put("ず", "zu");
        map.put("ズ", "zu");
        map.put("せ", "se");
        map.put("セ", "se");
        map.put("ぜ", "ze");
        map.put("ゼ", "ze");
        map.put("そ", "so");
        map.put("ソ", "so");
        map.put("ぞ", "zo");
        map.put("ゾ", "zo");
        map.put("た", "ta");
        map.put("タ", "ta");
        map.put("だ", "da");
        map.put("ダ", "da");
        map.put("ち", "ti");
        map.put("チ", "ti");
        map.put("ぢ", "di");
        map.put("ヂ", "di");
        map.put("っ", "tu");
        map.put("ッ", "tu");
        map.put("つ", "tu");
        map.put("ツ", "tu");
        map.put("づ", "zu");
        map.put("ヅ", "zu");
        map.put("て", "te");
        map.put("テ", "te");
        map.put("で", "de");
        map.put("デ", "de");
        map.put("と", "to");
        map.put("ト", "to");
        map.put("ど", "do");
        map.put("ド", "do");
        map.put("な", "na");
        map.put("ナ", "na");
        map.put("に", "ni");
        map.put("ニ", "ni");
        map.put("ぬ", "nu");
        map.put("ヌ", "nu");
        map.put("ね", "ne");
        map.put("ネ", "ne");
        map.put("の", "no");
        map.put("ノ", "no");
        map.put("は", "ha");
        map.put("ハ", "ha");
        map.put("ば", "ba");
        map.put("バ", "ba");
        map.put("ぱ", "pa");
        map.put("パ", "pa");
        map.put("ひ", "hi");
        map.put("ヒ", "hi");
        map.put("び", "bi");
        map.put("ビ", "bi");
        map.put("ぴ", "pi");
        map.put("ピ", "pi");
        map.put("ふ", "hu");
        map.put("フ", "hu");
        map.put("ぶ", "bu");
        map.put("ブ", "bu");
        map.put("ぷ", "pu");
        map.put("プ", "pu");
        map.put("へ", "he");
        map.put("ヘ", "he");
        map.put("べ", "be");
        map.put("ベ", "be");
        map.put("ぺ", "pe");
        map.put("ペ", "pe");
        map.put("ほ", "ho");
        map.put("ホ", "ho");
        map.put("ぼ", "bo");
        map.put("ボ", "bo");
        map.put("ぽ", "po");
        map.put("ポ", "po");
        map.put("ま", "ma");
        map.put("マ", "ma");
        map.put("み", "mi");
        map.put("ミ", "mi");
        map.put("む", "mu");
        map.put("ム", "mu");
        map.put("め", "me");
        map.put("メ", "me");
        map.put("も", "mo");
        map.put("モ", "mo");
        map.put("ゃ", "ya");
        map.put("ャ", "ya");
        map.put("や", "ya");
        map.put("ヤ", "ya");
        map.put("ゅ", "yu");
        map.put("ュ", "yu");
        map.put("ゆ", "yu");
        map.put("ユ", "yu");
        map.put("ょ", "yo");
        map.put("ョ", "yo");
        map.put("よ", "yo");
        map.put("ヨ", "yo");
        map.put("ら", "ra");
        map.put("ラ", "ra");
        map.put("り", "ri");
        map.put("リ", "ri");
        map.put("る", "ru");
        map.put("ル", "ru");
        map.put("れ", "re");
        map.put("レ", "re");
        map.put("ろ", "ro");
        map.put("ロ", "ro");
        map.put("ゎ", "wa");
        map.put("ヮ", "wa");
        map.put("わ", "wa");
        map.put("ワ", "wa");
        map.put("ゐ", "wi");
        map.put("ヰ", "wi");
        map.put("ゑ", "we");
        map.put("ヱ", "we");
        map.put("を", "wo");
        map.put("ヲ", "wo");
        map.put("ん", "n");
        map.put("ン", "n");
        map.put("ヴ", "v");
        map.put("ヵ", "ka");
        map.put("ヶ", "ke");
        return map.get(kana);
    }
}
