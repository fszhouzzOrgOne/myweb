package unicode;

import java.util.ArrayList;
import java.util.Set;

import cangjie.java.util.IOUtils;

/**
 * 把私用區(0xE000, 0xF8FF)打印出來
 * 
 * 
 * @author fszhouzz@qq.com
 * @time 2019年9月27日 上午12:30:03
 */
public class PrivateUserAreaTest {
    private static String mbsBaseDir = "src\\java\\unicode\\";
    private static String fileName = "PrivateUserArea.txt";

    public static void main(String[] args) throws Exception {
        Set<String> set = UnicodeConvertUtil.getStringSet(0xE000, 0xF8FF);
        IOUtils.writeFile(mbsBaseDir + fileName, new ArrayList<String>(set));
    }
}
