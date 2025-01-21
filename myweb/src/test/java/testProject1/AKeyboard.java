package testProject1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 鍵盤打字輸出結果
 * 
 */
public class AKeyboard {

    private static final Map<Integer, String[]> keyMap = new HashMap<Integer, String[]>();
    private static final String CHANG_MODE_KEY = "#";
    private static final String PAUSE_KEY = "/";
    
    static {
        keyMap.put(1, new String[]{",", "."});
        keyMap.put(2, new String[]{"a", "b", "c"});
        keyMap.put(3, new String[]{"d", "e", "f"});
        keyMap.put(4, new String[]{"g", "h", "i"});
        keyMap.put(5, new String[]{"j", "k", "l"});
        keyMap.put(6, new String[]{"m", "n", "o"});
        keyMap.put(7, new String[]{"p", "q", "r", "s"});
        keyMap.put(8, new String[]{"t", "u", "v"});
        keyMap.put(9, new String[]{"w", "x", "y", "z"});
        keyMap.put(0, new String[]{" "});
        // # en/123   
        // /分隔 2/3 ad 22/3 bd 2233/3/4/55566 22
        // # / 变化 22
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = null;
        while (in.hasNext()) {
            line = in.next();
            System.out.println(translateInput(line));
        }
        in.close();
    }

    private static String translateInput(String line) {
        if (null == line || line.trim().length() < 1) {
            return "";
        }
        String[] modes = line.trim().split(CHANG_MODE_KEY);
        boolean numberMode = true;
        StringBuilder bu = new StringBuilder();
        for (String str : modes) {
            bu.append(translateInputByMode(str, numberMode));
            numberMode = !numberMode;
        }
        return bu.toString();
    }

    private static String translateInputByMode(String str, boolean numberMode) {
        if (numberMode) {
            return str.replaceAll(PAUSE_KEY, "");
        }
        String[] inputs = str.trim().split(PAUSE_KEY);
        StringBuilder bu = new StringBuilder();
        for (String input : inputs) {
            bu.append(translateEnglishInputByPause(input));
        }
        return bu.toString();
    }

    private static String translateEnglishInputByPause(String str) {
        if (null == str || str.trim().length() < 1) {
            return "";
        }
        String input = str.trim();
        int start = 0;
        int end = 0;
        char cha = input.charAt(start);
        StringBuilder bu = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            end = i;
            if (cha != input.charAt(i)) {
                bu.append(translateEnglishInputByKey(input.substring(start, end)));
                cha = input.charAt(i);
                // 当前的变化了，重新开始
                start = i;
            }
        }
        if (start < input.length()) {
            // 最后一个
            bu.append(translateEnglishInputByKey(input.substring(start)));
        }
        return bu.toString();
    }

    private static String translateEnglishInputByKey(String str) {
        Integer key = Integer.parseInt(str.substring(str.length() - 1));
        String[] vals = keyMap.get(key);
        // 余0取最后一个
        int validLen = str.length() % vals.length;
        int index = (0 == validLen) ? vals.length - 1: validLen - 1;
        return vals[index];
    }
}
