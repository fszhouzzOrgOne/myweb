package phonetic;

import java.util.ArrayList;
import java.util.List;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;

/**
 * 國際音標符號<br/>
 * 來源：https://zh.wiktionary.org/wiki/附录:国际音标符号<br/>
 * 碼表：按維基詞典編碼a-z，ʔ • ʡ • ʕ • ʢ • ʘ • ǀ • ǂ • ǁ • ǃ 九個放入z鍵中，之後的符號用zz編碼。
 * 
 * @author fszhouzz@qq.com
 * @time 2018年3月20日上午11:43:53
 */
public class PhoneticTest {

    public static String mbsBaseDir = "src\\java\\phonetic\\";
    // 原碼表
    private static String phoneticAllFile = mbsBaseDir + "國際音標符號.txt";

    public static void main(String[] args) throws Exception {
        List<String> mb = IOUtils.readLines(phoneticAllFile);

        String az = "abcdefghijklmnopqrstuvwxyz";
        String[] azs = new String[az.length()];
        for (int i = 0; i < az.length(); i++) {
            azs[i] = az.charAt(i) + "";
        }

        List<String> res = new ArrayList<String>();
        // ʔ • ʡ • ʕ • ʢ • ʘ • ǀ • ǂ • ǁ • ǃ 九個放入z鍵中
        // 之後的符號放入zz中
        String currentKey = "";
        for (String str : mb) {
            // 符號放入zz中
            if ("zz".equals(str)) {
                currentKey = "zz";
                continue;
            } else {
                for (String key : azs) {
                    if (key.equals(str)) {
                        currentKey = key;
                    }
                }
            }
            
            res.add(currentKey + "   " + str);
        }

        // res
        IOUtils.writeFile(Cj00AllInOneTest.phonetic200, res);
    }
}
