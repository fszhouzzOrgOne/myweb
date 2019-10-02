package squareenglish;

import java.util.ArrayList;
import java.util.List;

import cangjie.java.DuoDuoFormatTest;
import cangjie.java.util.IOUtils;

/**
 * 英方書碼表製作<br/>
 * 多碼表：DuoDuoFormatTest.writeDuoDuoMb
 * 
 * 
 * @author fszhouzz@qq.com
 * @time 2019年10月2日 上午11:13:38
 */
public class YingFangShuMBTest {
    private static String mbsBaseDir = "src\\java\\squareenglish\\";
    private static String srcFile = "英方書海綿寶寶字體碼表.txt";
    private static String destFile = "英方書海綿寶寶字體碼表-duoduo.txt";

    public static void main(String[] args) throws Exception {
        List<String> lines = IOUtils.readLines(mbsBaseDir + srcFile);
        List<String> linesNew = new ArrayList<String>();
        String space = " ";
        for (String str : lines) {
            if (str.contains(space)) {
                String[] splits = str.split(space + "+");
                String cod = splits[1];
                String cha = splits[0];

                linesNew.add(cod + space + cha);
            }
        }
        DuoDuoFormatTest.writeDuoDuoMb(mbsBaseDir, destFile, linesNew);
    }

}
