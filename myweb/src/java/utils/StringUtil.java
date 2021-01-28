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
        String str = "abcdabf";
        String p = "bfc";
        int i = indexOfByKMP(str, p);
        System.out.println(i);
    }
    
    /**
     * KMP算法實現
     * 
     * @param str 文本串
     * @param p 模式串
     * @return 求模式串在文本串，第一次出現的下標
     */
    public static int indexOfByKMP(String str, String p) {
        int i = 0;
        int j = 0;
        // j下標的復位位置數組
        int[] nextJ = generateKMPNextJArr(p);
        int res = -1;
        while (i < str.length()) {
            // j是-1時，模式串p要整體後移，所以都要++，也防越界
            if (-1 == j || str.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                // 匹配成功了，j到p的了末尾
                if (j >= p.length()) {
                    // i也加了1,所以直接減長度
                    res = i - p.length();
                    break;
                }
            } else {
                // KMP的目的在這裡了
                // i不動，j移動到指定位置
                j = nextJ[j];
            }
        }
        return res;
    }
    
    /** 
     * KMP算法中，模式串的next數組，j下標的復位位置數組<br/>
     * 用於當有一個不匹配了，模式串中的下標移動到哪個位置
     */
    private static int[] generateKMPNextJArr(String p) {
        int[] nextJ = new int[p.length()];
        for (int j = 0; j < p.length(); j++) {
            if (0 == j) {
                nextJ[j] = -1; // 第一個就不匹配，模式串全體後移
            } else {
                int pre = j - 1;
                determineKMPNextJValue(p, nextJ, j, pre);
            }
        }
        return nextJ;
    }

    /** KMP算法模式串的next數組，按前面一個值，得到當前值 */
    private static void determineKMPNextJValue(String p, int[] nextJ, int j,
            int currPre) {
        if (nextJ[currPre] == -1) {
            nextJ[j] = 0;
            return;
        }
        // j所在位置前一個字符，等於遞歸中前綴的後面那個位置的字符
        if (p.charAt(j - 1) == p.charAt(nextJ[currPre])) {
            nextJ[j] = nextJ[currPre] + 1;
        } else {
            determineKMPNextJValue(p, nextJ, j, nextJ[currPre]);
        }
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
