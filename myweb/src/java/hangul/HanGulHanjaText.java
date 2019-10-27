package hangul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;

/**
 * 解析《标准韩国语词典.txt》和《韩文汉字词典.txt》
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月3日上午11:19:06
 */
public class HanGulHanjaText {

    private static String hanziPtn = "[\\u4e00-\\u9fff\\u3400-\\u4dbf\\uF900-\\uFAFF]+";

    private static String yanwenPtn = "[\\uAC00-\\uD7AF\\u1100-\\u11FF\\u3130-\\u318F\\uA960-\\uA97F\\uD7B0-\\uD7FF}]+";

    /** 保留的漢字詞條中各詞字數 2 */
    private static String singleCharPtn = "^.* [\\u4e00-\\u9fff\\u3400-\\u4dbf\\uF900-\\uFAFF]{1,1}$";

    private static String mbsBaseDir = "src\\java\\hangul\\file\\";

    public static void main(String[] args) throws Exception {
        List<String> dictHanwen = getDictHanwen();
        writeDictHanwen2(dictHanwen);

        List<String> changyongHanzi = getHanyuChangyongHanzi();
        writeHanyuChangyongHanzi2(changyongHanzi);

        List<String> biaozhunHanguoyu = getBiaozhunHanguoyu();
        writeBiaozhunHanguoyu2(biaozhunHanguoyu);

        makePraseAllInOne();

        makeSingleCharMb();

        encodeHangulPinyin(true);
    }

    /**
     * 給碼表英文編碼
     * 
     * @author fszhouzz@qq.com
     * @throws Exception
     * @time 2018年1月4日上午9:28:27
     * @param deleteYanwen
     *            純諺文的詞都删除
     */
    private static void encodeHangulPinyin(boolean deleteYanwen) throws Exception {
        String mbfile = mbsBaseDir + "korea-12000.txt";
        List<String> mblist = IOUtils.readLines(mbfile);
        List<String> mbOther = IOUtils.readLines(Cj00AllInOneTest.mbkoreaOther);
        mblist.addAll(mbOther);

        Map<String, List<String>> mbMap = new HashMap<String, List<String>>();
        for (String str : mblist) {
            if (str.contains(" ")) {
                String[] part = str.split(" ");
                List<String> codes = mbMap.get(part[1]);
                if (null == codes) {
                    codes = new ArrayList<String>();
                }
                codes.add(part[0]);

                mbMap.put(part[1], codes);
            }
        }

        String file1 = mbsBaseDir + "韓文漢字單字的碼表.txt";
        String file2 = mbsBaseDir + "韓文漢字詞組碼表的整合.txt";
        List<String> encoded1 = new ArrayList<String>(encodeMbfile(file1, mbMap));
        List<String> encoded2 = new ArrayList<String>(encodeMbfile(file2, mbMap));

        String file11 = Cj00AllInOneTest.koreaHanja;
        String file21 = mbsBaseDir + "en韓文漢字詞組碼表的整合.txt";

        // 純諺文的詞都删除
        if (deleteYanwen) {
            for (int i = encoded1.size() - 1; i >= 0; i--) {
                String line = encoded1.get(i);
                if (line.contains(" ")) {
                    String[] parts = line.split(" ");
                    if (parts[1].matches(yanwenPtn)) {
                        encoded1.remove(i);
                    }
                }
            }
            for (int i = encoded2.size() - 1; i >= 0; i--) {
                String line = encoded2.get(i);
                if (line.contains(" ")) {
                    String[] parts = line.split(" ");
                    if (parts[1].matches(yanwenPtn)) {
                        encoded2.remove(i);
                    }
                }
            }
        }

        IOUtils.writeFile(file11, encoded1);
        IOUtils.uniqueCodeFile(file11);
        IOUtils.orderCodeFile(file11);
        IOUtils.writeFile(file21, encoded2);
        IOUtils.uniqueCodeFile(file21);
        IOUtils.orderCodeFile(file21);

        System.out.println(mbMap.get("힐"));
    }

    /**
     * 編碼碼表
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月4日上午9:38:15
     * @param file
     *            詞組碼表：諺文+空格+詞組
     * @param mbMap
     *            碼表：諺文單字-英文編碼列表
     * @return
     */
    private static Set<String> encodeMbfile(String file, Map<String, List<String>> mbMap) {
        LinkedHashSet<String> res = new LinkedHashSet<String>();
        outerFor: for (String str : IOUtils.readLines(file)) {
            if (str.contains(" ")) {
                String[] part = str.split(" ");
                char[] chas = part[0].toCharArray();
                // 所有可能的編碼
                List<String> encodes = null;
                for (Character cha : chas) {
                    List<String> codes = mbMap.get(cha.toString());
                    if (null == codes || codes.isEmpty()) {
                        System.out.println("編碼沒有：" + cha);
                        continue outerFor;
                    }
                    if (null == encodes) {
                        encodes = new ArrayList<String>(codes);
                    } else {
                        List<String> encodes2 = new ArrayList<String>();
                        for (String suffix : codes) {
                            for (String enc : encodes) {
                                encodes2.add(enc + suffix);
                            }
                        }
                        encodes = encodes2;
                    }
                }
                if (null != encodes && !encodes.isEmpty()) {
                    for (String enc : encodes) {
                        String line1 = enc + " " + part[0];
                        String line2 = enc + " " + part[1];
                        if (!res.contains(line1)) {
                            res.add(line1);
                        }
                        if (!res.contains(line2)) {
                            res.add(line2);
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 生成所有單字的碼表
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月4日上午9:16:47
     * @throws Exception
     */
    private static void makeSingleCharMb() throws Exception {
        String file = mbsBaseDir + "韓文漢字單字的碼表.txt";
        String fileOri = mbsBaseDir + "韓文漢字詞組碼表的整合.txt";
        List<String> listOri = IOUtils.readLines(fileOri);

        Set<String> resSinCodes = new HashSet<String>();
        List<String> resSins = new ArrayList<String>();
        String sinPtn = singleCharPtn;
        for (String ji : listOri) {
            if (ji.matches(sinPtn)) {
                resSinCodes.add(ji.split(" ")[0]);
            }
        }
        for (String ji : listOri) {
            if (resSinCodes.contains(ji.split(" ")[0])) {
                resSins.add(ji);
            }
        }

        if (!resSins.isEmpty()) {
            IOUtils.writeFile(file, resSins);
            IOUtils.uniqueCodeFile(file);
            IOUtils.orderCodeFile(file);
        }
    }

    /**
     * 韓文漢字詞組碼表的整合
     * 
     * @author fszhouzz@qq.com
     * @throws Exception
     * @time 2018年1月4日上午9:12:19
     */
    private static void makePraseAllInOne() throws Exception {
        String file1 = mbsBaseDir + "标准韩国语词典2.txt";
        String file2 = mbsBaseDir + "韩文汉字词典2.txt";
        String file3 = mbsBaseDir + "韩语常用汉字对照2.txt";
        String file4 = mbsBaseDir + "00補充一些韓語漢字.txt";

        String fileAll = mbsBaseDir + "韓文漢字詞組碼表的整合.txt";

        List<String> list1 = IOUtils.readLines(file1);
        List<String> list2 = IOUtils.readLines(file2);
        List<String> list3 = IOUtils.readLines(file3);
        List<String> list4 = IOUtils.readLines(file4);

        List<String> listAll = new ArrayList<String>();
        listAll.addAll(list1);
        listAll.addAll(list2);
        listAll.addAll(list3);
        listAll.addAll(list4);

        IOUtils.writeFile(fileAll, listAll);
        IOUtils.uniqueCodeFile(fileAll);
        IOUtils.orderCodeFile(fileAll);
    }

    /**
     * 寫《标准韩国语词典2.txt》
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月3日下午5:33:01
     * @param biaozhunHanguoyu
     * @throws Exception
     */
    private static void writeBiaozhunHanguoyu2(List<String> list) throws Exception {
        String file = mbsBaseDir + "标准韩国语词典2.txt";
        IOUtils.writeFile(file, list);
        IOUtils.uniqueCodeFile(file);
        IOUtils.orderCodeFile(file);
    }

    /**
     * 讀《标准韩国语词典.txt》
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月3日下午5:17:06
     * @return
     */
    private static List<String> getBiaozhunHanguoyu() {
        List<String> list = IOUtils.readLines(mbsBaseDir + "标准韩国语词典.txt");
        List<String> res = new ArrayList<String>();
        for (String str : list) {
            if (str.contains(" ")) {
                String[] part = str.split(" ");
                if (part.length > 2) {
                    int i = 0;
                    int j = part.length - 1;
                    String preffix = part[i];
                    String suffix = part[j];
                    while (i < j - 1) {
                        if (preffix.length() < suffix.length()) {
                            i++;
                            preffix += part[i];
                        } else if (preffix.length() > suffix.length()) {
                            j--;
                            suffix = part[j] + suffix;
                        } else {
                            i++;
                            j--;
                            preffix += part[i];
                            suffix = part[j] + suffix;
                        }
                    }
                    if (preffix.length() != suffix.length()) {
                        System.out.println("多個空格的長度不等：" + preffix + " " + suffix);
                    } else {
                        res.add(preffix + " " + suffix);
                    }
                } else if (part.length == 2) {
                    res.add(str);
                } else {
                    System.out.println("按空格分後長度爲一：" + str);
                }
            } else {
                System.out.println("沒有空格：" + str);
            }
        }
        // 解析一個諺文對應多個漢字詞的
        // 它都用/分隔的
        // 同時去掉数字
        List<String> res2 = new ArrayList<String>();
        for (String str : res) {
            str = str.replaceAll("[0-9]+", "");
            if (str.contains("/")) {
                String[] part = str.split(" ");
                if (part.length != 2) {
                    System.out.println("第二步空格不對：" + str);
                    continue;
                } else {
                    String preffix = part[0];
                    for (String suf : part[1].split("/")) {
                        res2.add(preffix + " " + suf);
                    }
                }
            } else {
                res2.add(str);
            }
        }
        return res2;
    }

    /**
     * 寫《韩语常用汉字对照2.txt》
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月3日下午5:08:24
     * @param list
     * @throws Exception
     */
    private static void writeHanyuChangyongHanzi2(List<String> list) throws Exception {
        String file = mbsBaseDir + "韩语常用汉字对照2.txt";
        IOUtils.writeFile(file, list);
        IOUtils.uniqueCodeFile(file);
        IOUtils.orderCodeFile(file);
    }

    /**
     * 读《韩语常用汉字对照.txt》
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月3日下午5:04:17
     * @return
     */
    private static List<String> getHanyuChangyongHanzi() {
        List<String> list = IOUtils.readLines(mbsBaseDir + "韩语常用汉字对照.txt");
        List<String> res = new ArrayList<String>();
        for (String str : list) {
            if (str.contains(" ")) {
                String[] part = str.split(" ");
                if (part.length >= 2) {
                    String preffix = part[0];
                    for (int i = 1; i < part.length; i++) {
                        if (preffix.length() != part[i].length()) {
                            System.out.println("多空格的諺漢長度不等：" + str);
                        } else {
                            res.add(preffix + " " + part[i]);
                        }
                    }
                } else {
                    System.out.println("按空格分後長度爲一：" + str);
                }
            } else {
                System.out.println("沒有空格：" + str);
            }
        }
        return res;
    }

    /**
     * 寫《韩文汉字词典.txt》所有的詞：諺文、漢字一一對應
     * 
     * @author fszhouzz@qq.com
     * @throws Exception
     * @time 2018年1月3日下午4:46:19
     */
    private static void writeDictHanwen2(List<String> list) throws Exception {
        String file = mbsBaseDir + "韩文汉字词典2.txt";
        IOUtils.writeFile(file, list);
        IOUtils.uniqueCodeFile(file);
        IOUtils.orderCodeFile(file);
    }

    /**
     * 解析《韩文汉字词典.txt》所有的詞：諺文、漢字一一對應
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月3日上午11:29:09
     */
    private static List<String> getDictHanwen() {
        List<String> dictHanwen = IOUtils.readLines(mbsBaseDir + "韩文汉字词典.txt");
        List<String> res = new ArrayList<String>();
        for (String str : dictHanwen) {
            if (str.contains(" ")) {
                String[] part = str.split(" ");
                if (part.length > 2) {
                    // 多個空格有兩種情況
                    // 1，如“새 사 僿”多個諺文再帶一個漢字詞
                    // 2，如“”一個諺文，帶多個漢字詞
                    // 所看第二個是不是諺文就好了
                    System.out.println("有多個空格：" + str);
                    if (part[1].matches(yanwenPtn)) {
                        String suffix = part[part.length - 1];
                        for (int i = 0; i < part.length - 1; i++) {
                            res.add(part[i] + " " + suffix);
                        }
                    } else {
                        String preffix = part[0];
                        for (int i = 1; i < part.length; i++) {
                            if (preffix.length() != part[i].length()) {
                                System.out.println("多空格的諺漢長度不等：" + str);
                            } else {
                                res.add(preffix + " " + part[i]);
                            }
                        }
                    }
                } else if (part.length == 2) {
                    if (part[0].length() != part[1].length()) {
                        System.out.println("諺漢長度不等：" + str);
                        continue;
                    }
                    res.add(str);
                } else {
                    System.out.println("按空格分後長度爲一：" + str);
                }
            } else {
                System.out.println("沒有空格：" + str);
            }
        }
        return res;
    }
}
