package karina;

import java.util.ArrayList;
import java.util.List;

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

    private static String mbsBaseDir = "src\\java\\karina\\mb\\";

    public static void main(String[] args) {
        List<String> dictKanji = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-漢字部分2待編碼.txt");
        for (String ji : dictKanji) {
        }

        // printAllKarina();

        String str = "ほったてごや";
        List<String> ends = encodeRomaji(str);
        System.out.println(ends);
    }

    /**
     * 生成對應假名的羅馬字編碼
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月5日下午2:10:36
     * @param str
     * @return
     */
    private static List<String> encodeRomaji(String str) {
        // か行、さ行、た行、ぱ行前可能有促音。
        String tsuPtn = ".+ッ|っ.+";
        if (str.matches(tsuPtn)) {
            str = str.replaceAll("", "");
        }
        return null;
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

}
