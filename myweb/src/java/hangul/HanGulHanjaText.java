package hangul;

import java.util.ArrayList;
import java.util.List;

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

    private static String mbsBaseDir = "src\\java\\hangul\\file\\";

    public static void main(String[] args) throws Exception {
        // List<String> dictHanwen = getDictHanwen();
        // writeDictHanwen2(dictHanwen);

        // List<String> changyongHanzi = getHanyuChangyongHanzi();
        // writeHanyuChangyongHanzi2(changyongHanzi);

        // List<String> biaozhunHanguoyu = getBiaozhunHanguoyu();
        // writeBiaozhunHanguoyu(biaozhunHanguoyu);
    }

    /**
     * 寫《标准韩国语词典2.txt》
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月3日下午5:33:01
     * @param biaozhunHanguoyu
     * @throws Exception
     */
    private static void writeBiaozhunHanguoyu(List<String> list) throws Exception {
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
