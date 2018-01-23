package hangul;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import cangjie.java.util.IOUtils;

/**
 * 羅馬字轉韓文方法
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月22日下午6:04:52
 */
public class Namaja2HangeulTest {

    private static Map<String, List<String>> baseMbMap = null;
    private static int maxCodeLen = 0; // 7
    private static int minCodeLen = 3; // 1

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(baseMbMap.keySet().size() + " " + maxCodeLen + " " + minCodeLen);

        System.out.println(getHangeulFromNamaja("joseon"));
    }

    public static void init() {
        try {
            // context = con;
            // InputStream is =
            // context.getResources().getAssets().open("database" +
            // File.separator + "korea-12000.txt");
            InputStream is = new FileInputStream("src\\java\\hangul\\file\\korea-12000.txt");
            List<String> res = getBaseMbByIS(is);
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

    /**
     * 取基本碼表
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月22日 下午8:02:57
     * @param is
     * @return
     */
    private static List<String> getBaseMbByIS(InputStream is) {
        String patn = "[가-힣]{1}";
        List<String> res = IOUtils.readLines(is);
        if (null != res && !res.isEmpty()) {
            List<String> res2 = IOUtils.readLines(is);
            for (String line : res) {
                if (line.contains(" ")) {
                    String[] parts = line.split(" ");
                    if (parts[1].matches(patn)) {
                        res2.add(line);
                    }
                }
            }
            res = res2;
        }
        return res;
    }

    /**
     * 羅馬字轉韓文列表
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月22日 下午8:18:05
     * @param namaja
     *            羅馬字
     * @return 韓文列表
     */
    public static List<String> getHangeulFromNamaja(String namaja) {
        if (null == namaja || namaja.trim().length() == 0) {
            return null;
        }
        namaja = namaja.trim().toLowerCase();

        if (namaja.length() == 1) {
            return baseMbMap.get(namaja);
        }

        List<Integer> lens = TypingFromRomanUtils.getPartsLen(namaja, baseMbMap, minCodeLen, maxCodeLen);
        List<String> res = TypingFromRomanUtils.getResByPartsLen(namaja, baseMbMap, lens);

        // 去褈
        res = new ArrayList<String>(new HashSet<String>(res));

        // 排序
        TypingFromRomanUtils.sortListByLengthAndSo(res);
        return res;
    }
}
