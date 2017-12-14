package jyutping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cangjie.java.util.IOUtils;

public class Test {

    public static String mbsBaseDir = "src\\java\\jyutping\\";

    public static void main(String[] args) throws Exception {
        // List<String> res = readMbCode_char_char(mbsBaseDir + "粤语发声字典081217.txt");
        // IOUtils.writeFile(mbsBaseDir + "粤语发声字典081217_2.txt", res);

        mergeMbs();
    }

    public static List<String> readMbCode_char_char(String filename) {
        List<String> list = IOUtils.readLines(filename);
        List<String> res = new ArrayList<String>();

        for (String line : list) {
            String[] parts = line.split(" ");
            for (int i = 1; i < parts.length; i++) {
                res.add(parts[0] + " " + parts[i]);
            }
        }
        return res;
    }

    public static void mergeMbs() throws Exception {
        // 合併
        List<String> list1 = IOUtils.readLines(mbsBaseDir + "jyutping-dict.txt");
        List<String> list2 = IOUtils.readLines(mbsBaseDir + "jyutping-github.txt");
        List<String> list3 = new ArrayList<String>();
        list3.add("ling4 〇");
        List<String> listall = new ArrayList<String>(list1);
        listall.addAll(list2);
        listall.addAll(list3);
        Collections.sort(listall);
        IOUtils.writeFile(mbsBaseDir + "jyutping-all.txt", listall);
        IOUtils.uniqueCodeFile(mbsBaseDir + "jyutping-all.txt");
    }
}
