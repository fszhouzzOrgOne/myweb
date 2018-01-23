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

/**
 * 滿文輸入工具
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月23日下午3:13:55
 */
public class ManjuTypingTest {

    private static Map<String, List<String>> baseMbMap = null;
    private static int maxCodeLen = 0; // 7
    private static int minCodeLen = 3; // 1

    private static String theSimKey = "q";
    private static List<String> theSimList = null;

    public static void main(String[] args) {
        init();

        System.out.println(baseMbMap.keySet().size() + " " + maxCodeLen + " " + minCodeLen);

        System.out.println(getManjuFromRoman("hargaxame"));
    }

    public static void init() {
        try {
            // context = con;
            // InputStream is =
            // context.getResources().getAssets().open("database" +
            // File.separator + "korea-12000.txt");
            InputStream is = new FileInputStream("src\\java\\manju\\mb\\manju-100.txt");
            List<String> res = IOUtils.readLines(is);
            // 符號的碼表
            is = new FileInputStream("src\\java\\manju\\mb\\manju-more.txt");
            List<String> resSim = IOUtils.readLines(is);
            if (null != resSim && !resSim.isEmpty()) {
                theSimList = new ArrayList<String>();
                for (String sim : resSim) {
                    if (sim.contains(" ")) {
                        String[] parts = sim.split(" ");
                        theSimList.add(parts[1]);
                    }
                }
            }

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
            if (theSimKey.equals(roman)) {
                return theSimList;
            } else {
                return baseMbMap.get(roman);
            }
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
