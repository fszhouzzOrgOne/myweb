package thai;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;

import java.util.List;

/**
 * 泰文輸入法
 */
public class ThaiTest {

    public static String mbsBaseDir = "myweb\\src\\main\\java\\thai\\";
    // 原碼表
    private static String thaiAllFile = mbsBaseDir + "allThaiCode.txt";

    public static void main(String[] args) throws Exception {
        List<String> mb = IOUtils.readLines(thaiAllFile, true);
        System.out.println(mb.size());
        // res
        IOUtils.writeFile(Cj00AllInOneTest.thai, mb);
    }
}
