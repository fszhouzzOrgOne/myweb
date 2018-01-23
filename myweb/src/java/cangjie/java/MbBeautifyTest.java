package cangjie.java;

import java.util.ArrayList;
import java.util.List;

import cangjie.java.util.IOUtils;

/**
 * 碼表格式化一下
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月23日上午11:10:32
 */
public class MbBeautifyTest {

    public static String mbsBaseDir = Cj00AllInOneTest.mbsBaseDir;

    public static void main(String[] args) throws Exception {
        String file = mbsBaseDir + "korea-other.txt";
        doBeautify(file);
    }

    private static void doBeautify(String file) throws Exception {
        List<String> lines = IOUtils.readLines(file);
        List<String> res = new ArrayList<String>();
        int maxCodeLen = 0;

        for (String line : lines) {
            if (line.contains(" ")) {
                String[] parts = line.split(" ");
                if (parts[0].length() > maxCodeLen) {
                    maxCodeLen = parts[0].length();
                }
            }
        }

        for (String line : lines) {
            if (line.contains(" ")) {
                String[] parts = line.split(" ");
                String spaces = " ";
                for (int i = parts[0].length(); i < maxCodeLen; i++) {
                    spaces += " ";
                }
                res.add(parts[0] + spaces + parts[1]);
            }
        }
        IOUtils.writeFile(file, res);
    }
}
