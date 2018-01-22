package karina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 日語羅馬字轉換成假名
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月22日上午10:59:55
 */
public class Romaji2KarinaTest {

    public static void main(String[] args) {
        System.out.println(getKarinaFromRomaji("YUKKURI"));
    }

    /** 假名和羅馬字的映射，排除了變化形式，所以能一對二 */
    public static Map<String, String> cleanKarinaRomaMap = new HashMap<String, String>();

    static {
        cleanKarinaRomaMap.put("あ", "a");
        cleanKarinaRomaMap.put("ア", "a");
        cleanKarinaRomaMap.put("い", "i");
        cleanKarinaRomaMap.put("イ", "i");
        cleanKarinaRomaMap.put("う", "u");
        cleanKarinaRomaMap.put("ウ", "u");
        cleanKarinaRomaMap.put("え", "e");
        cleanKarinaRomaMap.put("エ", "e");
        cleanKarinaRomaMap.put("お", "o");
        cleanKarinaRomaMap.put("オ", "o");
        cleanKarinaRomaMap.put("か", "ka");
        cleanKarinaRomaMap.put("カ", "ka");
        cleanKarinaRomaMap.put("が", "ga");
        cleanKarinaRomaMap.put("ガ", "ga");
        cleanKarinaRomaMap.put("き", "ki");
        cleanKarinaRomaMap.put("キ", "ki");
        cleanKarinaRomaMap.put("ぎ", "gi");
        cleanKarinaRomaMap.put("ギ", "gi");
        cleanKarinaRomaMap.put("く", "ku");
        cleanKarinaRomaMap.put("ク", "ku");
        cleanKarinaRomaMap.put("ぐ", "gu");
        cleanKarinaRomaMap.put("グ", "gu");
        cleanKarinaRomaMap.put("け", "ke");
        cleanKarinaRomaMap.put("ケ", "ke");
        cleanKarinaRomaMap.put("げ", "ge");
        cleanKarinaRomaMap.put("ゲ", "ge");
        cleanKarinaRomaMap.put("こ", "ko");
        cleanKarinaRomaMap.put("コ", "ko");
        cleanKarinaRomaMap.put("ご", "go");
        cleanKarinaRomaMap.put("ゴ", "go");
        cleanKarinaRomaMap.put("さ", "sa");
        cleanKarinaRomaMap.put("サ", "sa");
        cleanKarinaRomaMap.put("ざ", "za");
        cleanKarinaRomaMap.put("ザ", "za");
        cleanKarinaRomaMap.put("し", "si");
        cleanKarinaRomaMap.put("シ", "si");
        cleanKarinaRomaMap.put("じ", "zi");
        cleanKarinaRomaMap.put("ジ", "zi");
        cleanKarinaRomaMap.put("す", "su");
        cleanKarinaRomaMap.put("ス", "su");
        cleanKarinaRomaMap.put("ず", "zu");
        cleanKarinaRomaMap.put("ズ", "zu");
        cleanKarinaRomaMap.put("せ", "se");
        cleanKarinaRomaMap.put("セ", "se");
        cleanKarinaRomaMap.put("ぜ", "ze");
        cleanKarinaRomaMap.put("ゼ", "ze");
        cleanKarinaRomaMap.put("そ", "so");
        cleanKarinaRomaMap.put("ソ", "so");
        cleanKarinaRomaMap.put("ぞ", "zo");
        cleanKarinaRomaMap.put("ゾ", "zo");
        cleanKarinaRomaMap.put("た", "ta");
        cleanKarinaRomaMap.put("タ", "ta");
        cleanKarinaRomaMap.put("だ", "da");
        cleanKarinaRomaMap.put("ダ", "da");
        cleanKarinaRomaMap.put("ち", "ti");
        cleanKarinaRomaMap.put("チ", "ti");
        cleanKarinaRomaMap.put("ぢ", "di");
        cleanKarinaRomaMap.put("ヂ", "di");
        cleanKarinaRomaMap.put("つ", "tu");
        cleanKarinaRomaMap.put("ツ", "tu");
        cleanKarinaRomaMap.put("づ", "du");
        cleanKarinaRomaMap.put("ヅ", "du");
        cleanKarinaRomaMap.put("て", "te");
        cleanKarinaRomaMap.put("テ", "te");
        cleanKarinaRomaMap.put("で", "de");
        cleanKarinaRomaMap.put("デ", "de");
        cleanKarinaRomaMap.put("と", "to");
        cleanKarinaRomaMap.put("ト", "to");
        cleanKarinaRomaMap.put("ど", "do");
        cleanKarinaRomaMap.put("ド", "do");
        cleanKarinaRomaMap.put("な", "na");
        cleanKarinaRomaMap.put("ナ", "na");
        cleanKarinaRomaMap.put("に", "ni");
        cleanKarinaRomaMap.put("ニ", "ni");
        cleanKarinaRomaMap.put("ぬ", "nu");
        cleanKarinaRomaMap.put("ヌ", "nu");
        cleanKarinaRomaMap.put("ね", "ne");
        cleanKarinaRomaMap.put("ネ", "ne");
        cleanKarinaRomaMap.put("の", "no");
        cleanKarinaRomaMap.put("ノ", "no");
        cleanKarinaRomaMap.put("は", "ha");
        cleanKarinaRomaMap.put("ハ", "ha");
        cleanKarinaRomaMap.put("ば", "ba");
        cleanKarinaRomaMap.put("バ", "ba");
        cleanKarinaRomaMap.put("ぱ", "pa");
        cleanKarinaRomaMap.put("パ", "pa");
        cleanKarinaRomaMap.put("ひ", "hi");
        cleanKarinaRomaMap.put("ヒ", "hi");
        cleanKarinaRomaMap.put("び", "bi");
        cleanKarinaRomaMap.put("ビ", "bi");
        cleanKarinaRomaMap.put("ぴ", "pi");
        cleanKarinaRomaMap.put("ピ", "pi");
        cleanKarinaRomaMap.put("ふ", "hu");
        cleanKarinaRomaMap.put("フ", "hu");
        cleanKarinaRomaMap.put("ぶ", "bu");
        cleanKarinaRomaMap.put("ブ", "bu");
        cleanKarinaRomaMap.put("ぷ", "pu");
        cleanKarinaRomaMap.put("プ", "pu");
        cleanKarinaRomaMap.put("へ", "he");
        cleanKarinaRomaMap.put("ヘ", "he");
        cleanKarinaRomaMap.put("べ", "be");
        cleanKarinaRomaMap.put("ベ", "be");
        cleanKarinaRomaMap.put("ぺ", "pe");
        cleanKarinaRomaMap.put("ペ", "pe");
        cleanKarinaRomaMap.put("ほ", "ho");
        cleanKarinaRomaMap.put("ホ", "ho");
        cleanKarinaRomaMap.put("ぼ", "bo");
        cleanKarinaRomaMap.put("ボ", "bo");
        cleanKarinaRomaMap.put("ぽ", "po");
        cleanKarinaRomaMap.put("ポ", "po");
        cleanKarinaRomaMap.put("ま", "ma");
        cleanKarinaRomaMap.put("マ", "ma");
        cleanKarinaRomaMap.put("み", "mi");
        cleanKarinaRomaMap.put("ミ", "mi");
        cleanKarinaRomaMap.put("む", "mu");
        cleanKarinaRomaMap.put("ム", "mu");
        cleanKarinaRomaMap.put("め", "me");
        cleanKarinaRomaMap.put("メ", "me");
        cleanKarinaRomaMap.put("も", "mo");
        cleanKarinaRomaMap.put("モ", "mo");
        cleanKarinaRomaMap.put("や", "ya");
        cleanKarinaRomaMap.put("ヤ", "ya");
        cleanKarinaRomaMap.put("ゆ", "yu");
        cleanKarinaRomaMap.put("ユ", "yu");
        cleanKarinaRomaMap.put("よ", "yo");
        cleanKarinaRomaMap.put("ヨ", "yo");
        cleanKarinaRomaMap.put("ら", "ra");
        cleanKarinaRomaMap.put("ラ", "ra");
        cleanKarinaRomaMap.put("り", "ri");
        cleanKarinaRomaMap.put("リ", "ri");
        cleanKarinaRomaMap.put("る", "ru");
        cleanKarinaRomaMap.put("ル", "ru");
        cleanKarinaRomaMap.put("れ", "re");
        cleanKarinaRomaMap.put("レ", "re");
        cleanKarinaRomaMap.put("ろ", "ro");
        cleanKarinaRomaMap.put("ロ", "ro");
        cleanKarinaRomaMap.put("わ", "wa");
        cleanKarinaRomaMap.put("ワ", "wa");
        cleanKarinaRomaMap.put("ゐ", "wi");
        cleanKarinaRomaMap.put("ヰ", "wi");
        cleanKarinaRomaMap.put("ゑ", "we");
        cleanKarinaRomaMap.put("ヱ", "we");
        cleanKarinaRomaMap.put("を", "wo");
        cleanKarinaRomaMap.put("ヲ", "wo");
        cleanKarinaRomaMap.put("ん", "n");
        cleanKarinaRomaMap.put("ン", "n");
    }

    /**
     * 羅馬字轉換成假名列表
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月22日上午11:02:17
     * @param romaStr
     *            羅馬字串
     * @return 假名列表
     */
    private static List<String> getKarinaFromRomaji(String romaStr) {
        if (null == romaStr || romaStr.trim().length() == 0) {
            return null;
        }
        romaStr = romaStr.trim().toLowerCase();
        List<String> res = new ArrayList<String>();
        List<String> hiras = getHiraganaFromRomaji(romaStr);
        if (null != hiras && !hiras.isEmpty()) {
            res.addAll(hiras);
        }
        return res;
    }

    /**
     * 羅馬字轉換成平假名列表
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月22日上午11:02:17
     * @param romaParam
     *            羅馬字串
     * @return 平假名列表
     */
    private static List<String> getHiraganaFromRomaji(String romaParam) {
        String romaStr = romaParam;
        List<String> res = new ArrayList<String>();
        List<String> resTmp = new ArrayList<String>();
        // 促音か行、さ行、た行、ぱ行前
        // kk ss ssh tt tch pp
        romaStr = romaStr.replaceAll("kk", "っk").replaceAll("ss", "っs");
        romaStr = romaStr.replaceAll("tt", "っt").replaceAll("tch", "っch");
        romaStr = romaStr.replaceAll("pp", "っp");

        // 拗音
        // ky sy shy sh ty chy ch ny hy my ry
        // gy j jy zy dy by py
        // 只有j jy要加倍
        romaStr = romaStr.replaceAll("kya", "きゃ").replaceAll("kyu", "きゅ").replaceAll("kyo", "きょ");
        romaStr = romaStr.replaceAll("sya|shya|sha", "しゃ");
        romaStr = romaStr.replaceAll("syu|shyu|shu", "しゅ");
        romaStr = romaStr.replaceAll("syo|shyo|sho", "しょ");
        romaStr = romaStr.replaceAll("tya|chya|cha", "ちゃ");
        romaStr = romaStr.replaceAll("tyu|chyu|chu", "ちゅ");
        romaStr = romaStr.replaceAll("tyo|chyo|cho", "ちょ");
        romaStr = romaStr.replaceAll("nya", "にゃ").replaceAll("nyu", "にゅ").replaceAll("nyo", "にょ");
        romaStr = romaStr.replaceAll("hya", "ひゃ").replaceAll("hyu", "ひゅ").replaceAll("hyo", "ひょ");
        romaStr = romaStr.replaceAll("mya", "みゃ").replaceAll("myu", "みゅ").replaceAll("myo", "みょ");
        romaStr = romaStr.replaceAll("rya", "りゃ").replaceAll("ryu", "りゅ").replaceAll("ryo", "りょ");
        romaStr = romaStr.replaceAll("gya", "ぎゃ").replaceAll("gyu", "ぎゅ").replaceAll("gyo", "ぎょ");
        romaStr = romaStr.replaceAll("zya", "じゃ").replaceAll("zyu", "じゅ").replaceAll("zyo", "じょ");
        romaStr = romaStr.replaceAll("dya", "ぢゃ").replaceAll("dyu", "ぢゅ").replaceAll("dyo", "ぢょ");
        romaStr = romaStr.replaceAll("bya", "びゃ").replaceAll("byu", "びゅ").replaceAll("byo", "びょ");
        romaStr = romaStr.replaceAll("pya", "ぴゃ").replaceAll("pyu", "ぴゅ").replaceAll("pyo", "ぴょ");
        romaStr = romaStr.replaceAll("jy", "j");
        // 加倍
        if (romaStr.contains("j")) {
            resTmp.add(romaStr.replaceAll("ja", "じゃ").replaceAll("ju", "じゅ").replaceAll("jo", "じょ"));
            resTmp.add(new String(romaStr).replaceAll("ja", "ぢゃ").replaceAll("ju", "ぢゅ").replaceAll("jo", "ぢょ"));
        } else {
            resTmp.add(romaStr);
        }

        // 長音可以不管

        // 其他音
        // 先看長音爲2的
        for (Entry<String, String> en : cleanKarinaRomaMap.entrySet()) {
            // 是平假名
            boolean isHiragana = KarinaTest.karinas[0].contains(en.getKey());
            if (isHiragana && en.getValue().length() == 2) {
                List<String> resTmp2 = new ArrayList<String>();
                for (String str : resTmp) {
                    resTmp2.add(str.replaceAll(en.getValue(), en.getKey()));
                }
                resTmp = resTmp2;
            }
        }
        // 再看長度爲1的
        for (Entry<String, String> en : cleanKarinaRomaMap.entrySet()) {
            // 是平假名
            boolean isHiragana = KarinaTest.karinas[0].contains(en.getKey());
            if (isHiragana && en.getValue().length() == 1) {
                List<String> resTmp2 = new ArrayList<String>();
                for (String str : resTmp) {
                    resTmp2.add(str.replaceAll(en.getValue(), en.getKey()));
                }
                resTmp = resTmp2;
            }
        }

        res.addAll(resTmp);
        return res;
    }
}
