package guhan;

import java.util.List;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;

/**
 * 中古漢語拼音碼表整理
 */
public class MedievalChineseTest {
    public static String mbsBaseDir = "src\\java\\guhan\\mb\\";
    private static String koxHanhAllFile = mbsBaseDir + "中古漢語拼音.txt";

    public static void main(String[] args) throws Exception {
        List<String> lines = IOUtils.readLines(koxHanhAllFile);
        System.out.println(lines.size());

        IOUtils.writeFile(Cj00AllInOneTest.koxhanh30000, lines);
    }
}
