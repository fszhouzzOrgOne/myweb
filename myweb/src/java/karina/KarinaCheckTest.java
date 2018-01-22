package karina;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cangjie.java.util.IOUtils;

/**
 * 檢查一下碼表<br />
 * 其他字典沒有的，才留在《更多漢字補充.txt》
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月15日上午9:37:20
 */
public class KarinaCheckTest {

    private static final String karinaPtn = "[ぁ-ヾ]+";

    private static String mbsBaseDir = "src\\java\\karina\\mb\\";

    public static void main(String[] args) throws Exception {
        List<String> dictKanji1 = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-漢字部分2.txt");
        List<String> dictKanji2 = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-漢字部分2待編碼.txt");
        List<String> dictKarina = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-假名部分2.txt");

        Set<String> dict = new HashSet<String>();
        // 片假名轉成平假名
        Set<String> dict2 = new HashSet<String>();
        dict.addAll(dictKanji1);
        dict.addAll(dictKanji2);
        dict.addAll(dictKarina);
        for (String str : dict) {
            if (str.contains(" ")) {
                String[] parts = str.split(" ");
                dict2.add(KarinaTest.katakana2Hiragana(parts[0]) + " " + parts[1]);
            }
        }

        List<String> dictMore = IOUtils.readLines(mbsBaseDir + "更多漢字補充.txt");

        List<String> res = new ArrayList<String>();
        for (String str : dictMore) {
            if (str.contains(" ")) {
                String[] parts = str.split(" ");
                // 不相等，又前後全是假名的
                if (!parts[0].equals(parts[1]) && parts[0].matches(karinaPtn) && parts[1].matches(karinaPtn)) {
                    continue;
                }
                // 不是已有的
                if (dict.contains(str) || dict2.contains(str)) {
                    continue;
                }
                res.add(str);
            }
        }
        String destFile = mbsBaseDir + "更多漢字補充2222.txt";
        IOUtils.writeFile(destFile, res);
        IOUtils.uniqueCodeFile(destFile);
        IOUtils.orderCodeFile(destFile);
    }
}
