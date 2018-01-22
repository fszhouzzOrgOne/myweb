package karina;

import java.util.ArrayList;
import java.util.List;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;

/**
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月13日下午3:54:03
 */
public class KarinaBackTest {

    public static void main(String[] args) throws Exception {
        System.out.println("あからめる".matches(Romaji2KarinaTest.normalKarinaPtn));

        List<String> dict = IOUtils.readLines(Cj00AllInOneTest.mbnipponMoreSymbol);
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
        IOUtils.writeFile(Cj00AllInOneTest.mbnipponMoreSymbol, res);
        IOUtils.uniqueCodeFile(Cj00AllInOneTest.mbnipponMoreSymbol);
    }

}
