package jyutping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cangjie.java.util.IOUtils;

public class Test {

    public static String mbsBaseDir = "src\\java\\jyutping\\";

    public static void main(String[] args) throws Exception {
        List<String> list = IOUtils.readLines(mbsBaseDir + "粤语发声字典081217.txt");
        List<String> res = new ArrayList<String>();

        for (String line : list) {
            String[] parts = line.split(" ");
            for (int i = 1; i < parts.length; i++) {
                res.add(parts[0] + " " + parts[i]);
            }
        }

        IOUtils.writeFile(mbsBaseDir + "粤语发声字典081217_2.txt", res);

        // 合併
        List<String> list1 = IOUtils.readLines(mbsBaseDir + "jyutping-dict.txt");
        List<String> list2 = IOUtils.readLines(mbsBaseDir + "jyutping-github.txt");
        List<String> listall = new ArrayList<String>(list1);
        listall.addAll(list2);
        Collections.sort(listall);
        IOUtils.writeFile(mbsBaseDir + "jyutping-all.txt", listall);
        IOUtils.uniqueCodeFile(mbsBaseDir + "jyutping-all.txt");
    }
}
