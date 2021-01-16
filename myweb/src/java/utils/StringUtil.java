package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串操作
 * 
 * @author fszhouzz@qq.com
 * @time 2019年3月9日 下午10:07:40
 */
public class StringUtil {
    
    public static void main(String[] args) {
        String str = "1223";
        generateAllPermutationTest(str);
    }
    
    // 得到字符串的全排列測試
    public static void generateAllPermutationTest(String str) {
        String tmp1 = generateFirstPermutation(str);
        String tmp2 = generateLastPermutation(str);
        while (null != tmp1) {
            System.out.println(tmp1 + " " + tmp2);
            tmp1 = generateNextPermutation(tmp1);
            tmp2 = generatePreviousPermutation(tmp2);
        }
    }
    
    /**
     * 得到字符串的全排列1：第一個，再找下一個
     */
    public static List<String> generateAllPermutation1(String str) {
        if (isEmpty(str)) {
            return null;
        }
        List<String> perms = new ArrayList<String>();
        String tmp = generateFirstPermutation(str);
        while (null != tmp) {
            perms.add(tmp);
            tmp = generateNextPermutation(tmp);
        }
        return perms;
    }

    /**
     * 得到字符串的全排列2：最後一個，再找上一個
     */
    public static List<String> generateAllPermutation2(String str) {
        if (isEmpty(str)) {
            return null;
        }
        List<String> perms = new ArrayList<String>();
        String tmp = generateLastPermutation(str);
        while (null != tmp) {
            perms.add(tmp);
            tmp = generatePreviousPermutation(tmp);
        }
        return perms;
    }

    /**
     * 得到字符串的全排列的上一個<br/>
     * 思路就是：從後面向前找，找到第一個上升的位置，設它的值是ba，<br/>
     * 找b後面比b小的所有值中最大的一個值，和b交換位置，<br/>
     * 再把原來b所在位置後面的值做降序排列。降序，沒有再比降序更大的了
     */
    private static String generatePreviousPermutation(String str) {
        if (str.length() < 2) {
            return null;
        }
        // 從後面開始，找第一次上升，當是<=時，還是要往前面移，保證上升
        int currIndex = str.length() - 1;
        int previous = currIndex - 1;
        while (previous >= 0 && str.charAt(previous) <= str.charAt(currIndex)) {
            currIndex--;
            previous--;
        }
        // 到最開頭了，前面的還是小於等於當前值，說明沒有找到上升位置
        if (previous < 0) {
            return null;
        }
        if (0 == previous && str.charAt(previous) <= str.charAt(currIndex)) {
            return null;
        }
        // 找previous後面，比previous位置的值小的所有值中，最大的一個值
        int targetIndex = -1;
        for (int i = str.length() - 1; i > previous; i--) {
            char cha = str.charAt(i);
            if (cha < str.charAt(previous)) {
                if (targetIndex < 0 || cha > str.charAt(targetIndex)) {
                    targetIndex = i;
                }
            }
        }
        // previous和targetIndex值交換位置
        StringBuilder sbd = new StringBuilder(str);
        char tmp = sbd.charAt(targetIndex);
        sbd.setCharAt(targetIndex, sbd.charAt(previous));
        sbd.setCharAt(previous, tmp);
        // previous後面要降序排列
        if (previous >= str.length() - 2) {
            return sbd.toString();
        }
        return sbd.substring(0, previous + 1) +
                generateLastPermutation(sbd.substring(previous + 1));
    }

    /**
     * 得到字符串的全排列的下一個<br/>
     * 思路就是：從後面向前找，找到第一個下降的位置，設它的值是ab，<br/>
     * 找a後面比a大的所有值中最小的一個值，和a交換位置，<br/>
     * 再把原來a所在位置後面的值做升序排列。升序，沒有比升序更小的了。
     */
    private static String generateNextPermutation(String str) {
        if (str.length() < 2) {
            return null;
        }
        // 從後面開始，找第一次下降，當是>=時，還是要往前面移，保證下降
        int currIndex = str.length() - 1;
        int previous = currIndex - 1;
        while (previous >= 0 && str.charAt(previous) >= str.charAt(currIndex)) {
            currIndex--;
            previous--;
        }
        // 到最開頭了，前面的還是大於等於當前值，說明沒有找到下降位置
        if (previous < 0) {
            return null;
        }
        if (0 == previous && str.charAt(previous) >= str.charAt(currIndex)) {
            return null;
        }
        // 找previous後面，比previous位置的值大的所有值中，最小的一個值
        int targetIndex = -1;
        for (int i = str.length() - 1; i > previous; i--) {
            char cha = str.charAt(i);
            if (cha > str.charAt(previous)) {
                if (targetIndex < 0 || cha < str.charAt(targetIndex)) {
                    targetIndex = i;
                }
            }
        }
        // previous和targetIndex值交換位置
        StringBuilder sbd = new StringBuilder(str);
        char tmp = sbd.charAt(targetIndex);
        sbd.setCharAt(targetIndex, sbd.charAt(previous));
        sbd.setCharAt(previous, tmp);
        // previous後面要升序排列
        if (previous >= str.length() - 2) {
            return sbd.toString();
        }
        return sbd.substring(0, previous + 1) +
                generateFirstPermutation(sbd.substring(previous + 1));
    }

    /**
     * 得到字符串的全排列的第一個
     */
    public static String generateFirstPermutation(String str) {
        char[] chas = str.toCharArray();
        Arrays.sort(chas);
        return new String(chas);
    }

    /**
     * 得到字符串的全排列的最後一個
     */
    private static String generateLastPermutation(String str) {
        char[] chas = str.toCharArray();
        Arrays.sort(chas);
        return new StringBuilder(new String(chas)).reverse().toString();
    }

    public static boolean isEmpty(String param) {
        return null == param || param.trim().isEmpty();
    }

    public static boolean isNotEmpty(String param) {
        return !isEmpty(param);
    }

}
