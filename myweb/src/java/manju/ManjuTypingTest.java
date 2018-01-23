package manju;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import cangjie.java.util.IOUtils;
import hangul.TypingFromRomanUtils;

public class ManjuTypingTest {

    private static Map<String, List<String>> baseMbMap = null;
    private static int maxCodeLen = 0; // 7
    private static int minCodeLen = 3; // 1

    public static void main(String[] args) {
        init();

        System.out.println(baseMbMap.keySet().size() + " " + maxCodeLen + " " + minCodeLen);

        System.out.println(getManjuFromRoman("gvsa"));
    }

    public static void init() {
        try {
            // context = con;
            // InputStream is =
            // context.getResources().getAssets().open("database" +
            // File.separator + "korea-12000.txt");
            InputStream is = new FileInputStream("src\\java\\manju\\mb\\manju-100.txt");
            List<String> res = IOUtils.readLines(is);
            is = new FileInputStream("src\\java\\manju\\mb\\manju-more.txt");
            List<String> res2 = IOUtils.readLines(is);
            res.addAll(res2);

            baseMbMap = new HashMap<String, List<String>>();
            for (String line : res) {
                if (line.contains(" ")) {
                    String[] parts = line.split(" ");
                    List<String> chars = baseMbMap.get(parts[0]);
                    if (null == chars) {
                        chars = new ArrayList<String>();
                    }
                    chars.add(parts[1]);
                    baseMbMap.put(parts[0], chars);

                    if (parts[0].length() > maxCodeLen) {
                        maxCodeLen = parts[0].length();
                    }
                    if (parts[0].length() < minCodeLen) {
                        minCodeLen = parts[0].length();
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private static List<String> getManjuFromRoman(String roman) {
        if (null == roman || roman.trim().length() == 0) {
            return null;
        }
        roman = roman.trim().toLowerCase();

        if (roman.length() == 1) {
            return baseMbMap.get(roman);
        }

        List<Integer> lens = TypingFromRomanUtils.getPartsLen(roman, baseMbMap, minCodeLen, maxCodeLen);
        List<String> res = TypingFromRomanUtils.getResByPartsLen(roman, baseMbMap, lens);

        // 去褈
        res = new ArrayList<String>(new HashSet<String>(res));

        // 排序
        TypingFromRomanUtils.sortListByLengthAndSo(res);
        return res;
    }
}
