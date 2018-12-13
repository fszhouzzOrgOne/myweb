package jyutping;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;

public class Test {

    public static String mbsBaseDir = "src\\java\\jyutping\\";
    /** 粵拼碼表 */
    private static String jyutpAllFile = mbsBaseDir + "jyutping-all.txt";
    /** 編碼粵拼的詞組 */
    private static String jyutPhrase = mbsBaseDir + "jyutping-phrases.txt";

    public static void main(String[] args) throws Exception {
        mergeMbs();

        List<String> phrases = getJyutpingPhrase();
        IOUtils.writeFile(jyutPhrase, phrases);

        // 粵拼碼表整合
        // 用Q的個數表示聲調
        List<String> mb = IOUtils.readLines(jyutpAllFile);
        List<String> jyutAllInOne = new ArrayList<String>();
        jyutAllInOne.addAll(PingJamTest.getPingjamList());
        for (String str : mb) {
            if (null != str && str.trim().length() > 0) {
                jyutAllInOne.add(str.replace("1", "q").replace("2", "qq")
                        .replace("3", "qqq").replace("4", "qqqq")
                        .replace("5", "qqqqq").replace("6", "qqqqqq"));
            }
        }
        List<String> jyutPhrases = IOUtils.readLines(jyutPhrase);
        Set<String> jyutPhrasesRes = new LinkedHashSet<String>(); // 去褈用
        for (String phr : jyutPhrases) {
            if (phr.contains(" ")) {
                String parts[] = phr.split(" ");

                String line = parts[0].replaceAll("[0-9]", "") + " " + parts[1];
                if (!jyutPhrasesRes.contains(line)) {
                    jyutPhrasesRes.add(line);
                    jyutAllInOne.add(line);
                }
            }
        }
        IOUtils.writeFile(Cj00AllInOneTest.jyutping20000, jyutAllInOne);
    }

    /**
     * 編碼粵拼的詞組
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月14日 下午9:39:08
     * @param mbFile
     * @param srcPhraseFile
     * @return
     * @throws Exception
     */
    private static List<String> getJyutpingPhrase() throws Exception {
        String path = mbsBaseDir + File.separator + "praseGet" + File.separator;
        List<String> phrs = new ArrayList<String>();
        phrs.addAll(IOUtils.readLines(path + "粤语发声字典中所有的字音詞3结果.txt"));
        phrs.addAll(IOUtils.readLines(path + "粵語詞彙1.txt"));
        phrs.addAll(IOUtils.readLines(path + "粵語詞彙2.txt"));
        // 粵語詞彙-補充.txt
        phrs.addAll(IOUtils.readLines(path + "粵語詞彙-補充.txt"));

        Set<String> res = new HashSet<String>();
        for (String phr : phrs) {
            if (phr.contains(" ")) {
                String parts[] = phr.split(" ");
                String line = parts[1] + " " + parts[0];

                // 輸出美化一下
                int codeLen = 12;
                if (parts[1].length() < codeLen) {
                    String blank = "";
                    for (int i = parts[1].length(); i < codeLen; i++) {
                        blank += " ";
                    }
                    line = line.replace(" ", blank);
                }

                if (!res.contains(line)) {
                    res.add(line);
                }
            }
        }
        List<String> resList = new ArrayList<String>(res);
        Collections.sort(resList);
        return resList;
    }

    /**
     * 讀取碼表：編碼 空格 文字 空格 文字 空格 文字……
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月14日 下午10:27:18
     * @param filename
     * @return
     */
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

    /**
     * 合併兩個粵拼的碼表：jyutping-github.txt、jyutping-dict.txt
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月14日 下午10:26:45
     * @throws Exception
     */
    public static void mergeMbs() throws Exception {
        // 合併
        List<String> list1 = IOUtils
                .readLines(mbsBaseDir + "jyutping-dict.txt");
        List<String> list2 = IOUtils
                .readLines(mbsBaseDir + "jyutping-github.txt");
        List<String> list4 = IOUtils
                .readLines(mbsBaseDir + "jyutping-more.txt");
        List<String> listall = new ArrayList<String>(list1);
        listall.addAll(list2);
        listall.addAll(list4);
        Collections.sort(listall);

        List<String> res = new ArrayList<String>();
        res.add("ling4 〇");
        res.add("q  ˥˧");
        res.add("q  ˥");
        res.add("qq ˧˥");
        res.add("qqq ˧˧");
        res.add("qqq ˧");
        res.add("qqqq ˨˩");
        res.add("qqqqq ˨˧");
        res.add("qqqqqq ˨˨");
        res.add("qqqqqq ˨");
        res.add("q ⁵⁵");
        res.add("q ⁵");
        res.add("qq ³⁵");
        res.add("qqq ³³");
        res.add("qqq ³");
        res.add("qqqq ¹¹");
        res.add("qqqqq ¹³");
        res.add("qqqqqq ²²");
        res.add("qqqqqq ²");
        res.addAll(listall);
        IOUtils.writeFile(jyutpAllFile, res);
        IOUtils.uniqueCodeFile(jyutpAllFile);
    }
}
