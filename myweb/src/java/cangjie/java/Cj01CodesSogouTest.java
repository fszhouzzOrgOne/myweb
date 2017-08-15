package cangjie.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cangjie.java.util.IOUtils;

/**
 * 生成搜狗輸入法的短語表<br />
 * 先要執行Cj00AllInOneTest生成各個碼表
 */
public class Cj01CodesSogouTest {

    private static String mbBaseDir = "src\\java\\cangjie\\mb\\";

    /** 詞組編碼後的目標文件 */
    private static String destPhrasesFile = null;

    /** 常用版字集碼表 */
    private static String mbfile = null;
    private static String mbMoreFile = null;

    /** 简化字集 */
    private static String simfile = null;

    /** 搜狗碼表，可以用於導入短語 */
    private static String destFileSogou = null;

    public static void main(String[] args) throws Exception {
        setCj6();
        getCodesForSogou();
        
        setCj5();
        getCodesForSogou();
    }

    private static void setCj6() {
        destPhrasesFile = "cj6-phrase.txt";
        mbfile = "cj6-20902.txt";
        mbMoreFile = "cj6-more.txt";
        simfile = "cj6-8300.txt";
        destFileSogou = "allInOneSogou-cj6.txt";
    }

    private static void setCj5() {
        destPhrasesFile = "cj5-phrase.txt";
        mbfile = "cj5-20902.txt";
        mbMoreFile = "cj5-more.txt";
        simfile = "cj5-8300.txt";
        destFileSogou = "allInOneSogou-cj5.txt";
    }

    /**
     * 生成搜狗码表，搜狗碼表只支持10萬個以下的自定短語，所以要刪除一些
     * 
     * */
    private static void getCodesForSogou() throws Exception {
        System.out.println(destFileSogou);
        Set<String> cj6Set = new LinkedHashSet<String>(
                IOUtils.readLines(mbBaseDir + mbfile));
        Set<String> mbMoreSet = new LinkedHashSet<String>(
                IOUtils.readLines(mbBaseDir + mbMoreFile));
        ;
        List<String> cj6phrases = new ArrayList<String>(
                IOUtils.readLines(mbBaseDir + destPhrasesFile));
        List<String> cj6sims = IOUtils.readLines(mbBaseDir + simfile);
        Set<String> cj6simChar = new HashSet<String>();
        for (String ss : cj6sims) {
            if (ss.contains(" ")) {
                String[] keyVal = ss.split(" +");
                String val = keyVal[1];
                cj6simChar.add(val);
            }
        }
        Set<String> theRemoved = new HashSet<String>();
        System.out.println("詞組總數：" + cj6phrases.size());
        for (int i = cj6phrases.size() - 1; i >= 0; i--) {
            String phrase = cj6phrases.get(i);
            String phr = null;
            if (phrase.contains(" ")) {
                String[] keyVal = phrase.split(" +");
                phr = keyVal[1];
                for (int j = 0; j < phr.length(); j++) {
                    Character c = phr.charAt(j);
                    String s = c.toString();
                    // 留下简化字
                    if (!cj6simChar.contains(s)) {
                        theRemoved.add(phrase);
                    }
                }
            }
        }
        cj6phrases.removeAll(theRemoved); // 删除
        System.out.println("词组馀数：" + cj6phrases.size());

        Set<String> cj6All = new LinkedHashSet<String>(cj6Set);
        cj6All.addAll(mbMoreSet);
        cj6All.addAll(cj6phrases.subList(0, 99999 - cj6All.size()));
        System.out.println("詞組加上单字後總數：" + cj6All.size());
        IOUtils.writeFile(mbBaseDir + destFileSogou,
                convertToSogouFormat(cj6All));
    }

    /**
     * 转换成搜狗格式碼表
     */
    public static List<String> convertToSogouFormat(Set<String> mbSet) {
        // 按编码加入字符列表
        Map<String, List<String>> codeListMap = new HashMap<String, List<String>>();
        for (String s : mbSet) {
            String key = null;
            String val = null;
            if (s.contains(" ")) {
                String[] keyVal = s.split(" +");
                key = keyVal[0];
                val = keyVal[1];
            } else {
                key = s.trim();
                // val 不设置
            }
            List<String> vals = codeListMap.get(key);
            if (null == vals) {
                vals = new ArrayList<String>();
            }
            if (null == val) {
                if (vals.size() == 0) {
                    vals.add("");
                }
            } else {
                vals.add(val);
            }
            codeListMap.put(key, vals);
        }
        System.out.println("合并重复编码后的个数: " + codeListMap.size());
        // 用于搜狗的结果：键值对以"aa,1=昌"的格式存入, 其中数字是重码的排列顺序
        List<String> results = new ArrayList<String>();
        // 排序
        Set<String> set = codeListMap.keySet();
        List<String> resultCodes = new ArrayList<String>();
        for (String code : set) {
            resultCodes.add(code);
        }
        Collections.sort(resultCodes);
        for (String s : resultCodes) {
            List<String> vals = codeListMap.get(s);
            Collections.sort(vals, new Comparator<String>() {
                @Override
                public int compare(String arg0, String arg1) {
                    if (null == arg0) {
                        return (null == arg1) ? 0 : -1;
                    }
                    if (null == arg1) {
                        return 1;
                    }
                    return arg0.length() - arg1.length();
                }
            });
            for (int i = 0; i < vals.size(); i++) {
                String tempVal = vals.get(i);
                results.add(s + "," + (i + 1) + "=" + tempVal);
            }
        }
        System.out.println("编码编号后: " + results.size());
        return results;
    }

}
