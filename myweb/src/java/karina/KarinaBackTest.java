package karina;

import java.util.ArrayList;
import java.util.List;

import cangjie.java.util.IOUtils;

/**
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月13日下午3:54:03
 */
public class KarinaBackTest {

    private static String mbsBaseDir = "src\\java\\karina\\mb\\";

    public static void main(String[] args) throws Exception {
        System.out.println("あからめる".matches(Romaji2KarinaTest.normalKarinaPtn));

        String file = mbsBaseDir + "更多漢字補充.txt";
        List<String> dict = IOUtils.readLines(file, true);
        List<String> res = new ArrayList<String>();
        for (String line : dict) {
            if (line.contains(" ")) {
                String[] parts = line.split(" ");
                if (parts[1].matches(Romaji2KarinaTest.normalKarinaPtn)) {
                    System.out.println(line);
                } else {
                    res.add(line);
                }
            }
        }
        IOUtils.writeFile(file, res);
        IOUtils.uniqueCodeFile(file);
    }

}
