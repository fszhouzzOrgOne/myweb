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
    
    private static String yanwenPtn = "[\\u4e00-\\u9fff\\u3400-\\u4dbf\\uF900-\\uFAFF]+";
    
    private static String mbsBaseDir = "src\\java\\hangul\\file\\";
    
    public static void main(String[] args) {
        List<String> dictHanwen = getDictHanwen();
    }

    /**
     * 《韩文汉字词典.txt》所有的詞：諺文、漢字一一對應
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
