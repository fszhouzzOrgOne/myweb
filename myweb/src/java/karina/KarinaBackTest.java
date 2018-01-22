package karina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cangjie.java.util.IOUtils;

/**
 * 把英文碼表，改回假名碼表
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月13日下午3:54:03
 */
@Deprecated
public class KarinaBackTest {

    private static final String karinaPtn = "[ぁ-ヾ]+";

    private static String mbsBaseDir = "src\\java\\karina\\mb\\";

    public static void main(String[] args) throws Exception {
        String dest = mbsBaseDir + "res.txt";

        List<String> dictKanji1 = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-漢字部分2.txt");
        List<String> dictKanji2 = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-漢字部分2待編碼.txt");
        List<String> dictKarina = IOUtils.readLines(mbsBaseDir + "新日漢大辭典-假名部分2.txt");
        List<String> dictMore = IOUtils.readLines(mbsBaseDir + "更多漢字補充.txt");

        Set<String> dict = new HashSet<String>();
        dict.addAll(dictKanji1);
        dict.addAll(dictKanji2);
        dict.addAll(dictKarina);

        List<String> res = new ArrayList<String>();
        for (String line : dictMore) {
            if (!dict.contains(line)) {
                res.add(line);
            }
        }
        IOUtils.writeFile(dest, res);
    }

    public static void convertEn2Karina(String srcFile, String destFile) throws Exception {
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
