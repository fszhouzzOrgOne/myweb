package familysearch3.util;

public class CharacterUtil {

    /**
     * 是否英文字符
     * 
     * @author t
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 得到字符串長度，英文字符算1，其他算2
     * 
     * @author t
     */
    public static int getLength2(String str) {
        if (null == str || "".equals(str)) {
            return 0;
        }
        int length = 0;
        for (int i = 0; i < str.length(); i++) {
            char cha = str.charAt(i);
            if (CharacterUtil.isLetter(cha)) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 在數字前面補0，湊足位數，如果長於length不會截斷它
     * 
     * @author t
     */
    public static String formatNumber(Integer number, Integer length) {
        if (number == null) {
            number = 0;
        }
        String nStr = String.valueOf(number);
        while (nStr.length() < length) {
            nStr = "0" + nStr;
        }
        return nStr;
    }
    
}
