package unicode.braille;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unicode.UnicodeConvertUtil;

/**
 * 盲文
 * 
 * 
 * @author fszhouzz@qq.com
 * @time 2018年11月2日 下午7:43:04
 */
public class BrailleTest {

    private static Map<String, Integer> keyMap1 = new HashMap<String, Integer>();
    private static Map<String, String> keyMap2 = new HashMap<String, String>();
    private static Map<String, String> codeCharMap = new HashMap<String, String>();

    static {
        keyMap1.put("q", 0);
        keyMap1.put("w", 1);
        keyMap1.put("e", 2);
        keyMap1.put("r", 3);
        keyMap1.put("t", 4);
        keyMap1.put("y", 5);
        keyMap1.put("u", 6);
        keyMap1.put("i", 7);
        keyMap1.put("o", 8);

        keyMap2.put("p", "00000000");
        keyMap2.put("a", "0000");
        keyMap2.put("s", "0010");
        keyMap2.put("d", "0001");
        keyMap2.put("f", "0011");
        keyMap2.put("g", "1000");
        keyMap2.put("h", "1010");
        keyMap2.put("j", "1001");
        keyMap2.put("k", "1011");
        keyMap2.put("l", "0100");
        keyMap2.put("z", "0110");
        keyMap2.put("x", "0101");
        keyMap2.put("c", "0111");
        keyMap2.put("v", "1100");
        keyMap2.put("b", "1110");
        keyMap2.put("n", "1101");
        keyMap2.put("m", "1111");

        List<String> lines = getBraillePatterns();
        for (String line : lines) {
            System.out.println(line);
            String[] parts = line.split(" +");
            codeCharMap.put(parts[0].replaceAll("[12345678]", "1"), parts[1]);
        }
    }

    public static void main(String[] args) {
        List<String> parts = resolveInputParts("hqweonlox");
        System.out.println(parts);
        List<String> reses = new ArrayList<String>();
        for (String part : parts) {
            String one = part.charAt(0) + "";
            String res = null;
            if (keyMap1.keySet().contains(one)) {
                res = resolveNumInput(part);
            } else if (keyMap2.keySet().contains(one)) {
                res = resolveImageInput(part);
            }
            reses.add(res);
        }
        System.out.println(reses);
    }

    public static String resolveImageInput(String part) {
        String code1 = keyMap2.get(part.charAt(0) + "");
        if (code1.length() == 8) {
            return codeCharMap.get(code1);
        }
        String code2 = keyMap2.get(part.charAt(1) + "");
        List<String> codes = new ArrayList<String>(
                Arrays.asList("0", "0", "0", "0", "0", "0", "0", "0"));
        codes.set(0, code1.charAt(0) + "");
        codes.set(1, code1.charAt(2) + "");
        codes.set(2, code2.charAt(0) + "");
        codes.set(3, code1.charAt(1) + "");
        codes.set(4, code1.charAt(3) + "");
        codes.set(5, code2.charAt(1) + "");
        codes.set(6, code2.charAt(2) + "");
        codes.set(7, code2.charAt(3) + "");
        String codex = "";
        for (String one : codes) {
            codex += one;
        }
        return codeCharMap.get(codex);
    }

    public static String resolveNumInput(String part) {
        List<String> codes = new ArrayList<String>(
                Arrays.asList("0", "0", "0", "0", "0", "0", "0", "0"));
        for (int i = 0; i < part.length(); i++) {
            int one = keyMap1.get(part.charAt(i) + "");
            codes.set(one - 1, "1");
        }
        String codex = "";
        for (String one : codes) {
            codex += one;
        }
        return codeCharMap.get(codex);
    }

    public static List<String> resolveInputParts(String input) {
        boolean isNum = false;
        List<String> parts = new ArrayList<String>();
        String part = "";
        for (int i = 0; i < input.length(); i++) {
            String one = input.charAt(i) + "";
            if (keyMap1.keySet().contains(one)) {
                if (!isNum && part.length() > 0) {
                    parts.add(part + (part.length() > 1 ? "" : "a"));
                    part = "";
                }
                isNum = true;
                int oneInt = keyMap1.get(one);
                if ("q".equals(one)) {
                    if (part.length() > 0) {
                        parts.add(part);
                        part = "";
                    }
                } else {
                    if (part.length() > 0) {
                        String two = part.charAt(part.length() - 1) + "";
                        int twoInt = keyMap1.get(two);
                        if (twoInt >= oneInt) {
                            parts.add(part);
                            part = one;
                        } else {
                            part += one;
                        }
                    } else {
                        part = one;
                    }
                }
            } else if (keyMap2.keySet().contains(one)) {
                if (isNum && part.length() > 0) {
                    parts.add(part);
                    part = "";
                }
                isNum = false;
                if ("p".equals(one)) {
                    if (part.length() > 0) {
                        parts.add(part + (part.length() > 1 ? "" : "a"));
                        part = "";
                    }
                    parts.add("p");
                } else {
                    if (part.length() > 0) {
                        parts.add(part + one);
                        part = "";
                    } else if (i == input.length() - 1) {
                        parts.add(one + "a");
                        part = "";
                    } else {
                        part = one;
                    }
                }
            }
        }
        if (part.length() > 0) {
            parts.add(part);
            part = "";
        }
        return parts;
    }

    public static List<String> getBraillePatterns() {
        int start = 0x2800;
        int end = 0x28FF;
        List<String> lines = new ArrayList<String>();
        for (int i = start; i <= end; i++) {
            String str = UnicodeConvertUtil.getStringByUnicode(i);
            String name = Character.getName(i);
            String code = name.contains("-")
                    ? name.substring(name.indexOf("-") + 1) : "00000000";
            String line = toFullCode(code) + " " + str;
            lines.add(line);
        }
        return lines;
    }

    public static String toFullCode(String code) {
        int len = 8;
        if (code.length() == len) {
            return code;
        }
        String res = "";
        for (int i = 0; i < code.length(); i++) {
            String one = code.charAt(i) + "";
            int oneInt = Integer.parseInt(one);
            if ((i + 1) != oneInt) {
                int start = (i == 0) ? 1
                        : Integer.parseInt(code.charAt(i - 1) + "") + 1;
                int end = oneInt - 1;
                for (int j = start; j <= end; j++) {
                    res += "0";
                }
            }
            res += one;
        }
        while (res.length() < len) {
            res += "0";
        }
        return res;
    }
}
