package karina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;

/**
 * 把英文碼表，改回假名碼表
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月13日下午3:54:03
 */
public class KarinaBackTest {

    private static final String karinaPtn = "[ぁ-ヾ]+";

    private static String mbsBaseDir = "src\\java\\karina\\mb\\";

    public static void main(String[] args) throws Exception {
        convertEn2Karina(Cj00AllInOneTest.mbnipponKanji2000, mbsBaseDir + "kanji200res.txt");
        
        // 合併
    }

    private static void convertEn2Karina(String srcFile, String destFile) throws Exception {
        List<String> src = IOUtils.readLines(srcFile);
        Map<String, List<String>> enMb = new HashMap<String, List<String>>();
        for (String line : src) {
            if (line.contains(" ")) {
                String[] parts = line.split(" ");
                List<String> vals = enMb.get(parts[0]);
                if (null == vals) {
                    vals = new ArrayList<String>();
                }
                vals.add(parts[1]);
                enMb.put(parts[0], vals);
            } else {
                System.out.println("沒有空格：" + line);
            }
        }
        Set<String> res = new LinkedHashSet<String>();
        for (String key : enMb.keySet()) {
            List<String> vals = enMb.get(key);
            if (null != vals && !vals.isEmpty()) {
                List<String> kanas = new ArrayList<String>();
                for (String val : vals) {
                    if (val.matches(karinaPtn)) {
                        kanas.add(val);
                    }
                }
                if (!kanas.isEmpty()) {
                    for (String kana : kanas) {
                        for (String val : vals) {
                            String one = kana + " " + val;
                            if (!res.contains(one) && !kana.equals(val)) {
                                res.add(one);
                            }
                        }
                    }
                } else {
                    System.out.println("沒有假名：" + key + vals);
                }
            }
        }
        IOUtils.writeFile(destFile, new ArrayList<String>(res));
        IOUtils.uniqueCodeFile(destFile);
        IOUtils.orderCodeFile(destFile);
    }
}
