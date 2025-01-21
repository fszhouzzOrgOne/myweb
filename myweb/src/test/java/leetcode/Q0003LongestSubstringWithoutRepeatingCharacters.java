package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Q0003LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bbbb"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() <= 0) {
            return 0;
        }
        // 滑動窗口
        // 存字符和下標
        Map<Character, Integer> hash = new HashMap<>();
        int max = 0;
        int len;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (hash.containsKey(c)) {
                // 从start到i - 1都去掉
                int existIndex = hash.get(c);
                for (int j = start; j <= existIndex; j++) {
                    Character tempCh = s.charAt(j);
                    hash.remove(tempCh);
                }
                start = existIndex + 1;
            }
            len = i - start + 1;
            hash.put(c, i);
            if (max < len) {
                max = len;
            }
        }
        return max;
    }
}
