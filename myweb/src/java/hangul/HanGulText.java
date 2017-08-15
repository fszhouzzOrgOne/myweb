package hangul;

import java.util.ArrayList;
import java.util.List;

import cangjie.java.util.IOUtils;

// 韓文字符
public class HanGulText {

    private static String mbsBaseDir = "src\\java\\hangul\\file\\";

    public static void main(String[] args) throws Exception {
        // 字母
        List<String> chas = new ArrayList<String>();
        for (char i = '\u1100'; i <= '\u11FF'; i++) {
            chas.add(Character.toString(i));
        }
        // 兼容字母
        List<String> chas2 = new ArrayList<String>();
        for (char i = '\u3130'; i <= '\u318F'; i++) {
            chas2.add(Character.toString(i));
        }
        // 字母擴展A
        List<String> chas3 = new ArrayList<String>();
        for (char i = '\uA960'; i <= '\uA97F'; i++) {
            chas3.add(Character.toString(i));
        }
        // 字母擴展B
        List<String> chas4 = new ArrayList<String>();
        for (char i = '\uD7B0'; i <= '\uD7FF'; i++) {
            chas4.add(Character.toString(i));
        }
        // 音節，電腦可以顯示AC00-D7A3
        List<String> chas5 = new ArrayList<String>();
        for (char i = '\uAC00'; i <= '\uD7AF'; i++) {
            chas5.add(Character.toString(i));
        }

        List<String> chaAll = new ArrayList<String>();
        chaAll.addAll(chas);
        chaAll.addAll(chas2);
        chaAll.addAll(chas3);
        chaAll.addAll(chas4);
        chaAll.addAll(chas5);

        IOUtils.writeFile(mbsBaseDir + "hangulchars.txt", chaAll);
    }
}
