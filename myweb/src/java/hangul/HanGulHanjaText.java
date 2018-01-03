package hangul;

import java.util.List;

import cangjie.java.util.IOUtils;
import unicode.UnicodeHanziUtil;

/**
 * 解析《标准韩国语词典.txt》和《韩文汉字词典.txt》
 * 
 * @author fszhouzz@qq.com
 * @time 2018年1月3日上午11:19:06
 */
public class HanGulHanjaText {

    private static String hanjaPtm = "[\\x{4e00}-\\x{9fff}\\x{3400}-\\x{4dbf}\\x{F900}-\\x{FAFF}]+";
    
    private static String mbsBaseDir = "src\\java\\hangul\\file\\";
    
    public static void main(String[] args) {
        List<String> dictHanwen = getDictHanwen();
        
        System.out.println(UnicodeHanziUtil.getRangeNameByChar("車"));
    }

    /**
     * 《韩文汉字词典.txt》所有的詞：諺文、漢字一一對應
     * 
     * @author fszhouzz@qq.com
     * @time 2018年1月3日上午11:29:09
     */
    private static List<String> getDictHanwen() {
        List<String> dictHanwen = IOUtils.readLines(mbsBaseDir + "韩文汉字词典.txt");
        for (String str : dictHanwen) {
            if (str.contains(" ")) {
                String[] part = str.split(" ");
                if (part.length > 2) {
                    System.out.println("多個空格：" + str);
                } else {
                    if (part[0].length() != part[1].length()) {
                        System.out.println(str);
                    }
                }
            } else {
                System.out.println("沒有空格：" + str);
            }
        }
        return dictHanwen;
    }
}
