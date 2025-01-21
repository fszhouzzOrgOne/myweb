package d20220803;

import java.util.Scanner;

/**
 * 实现 strStr() 函数。
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle
 * 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 * 示例 1：
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 * 输入：haystack = "", needle = ""
 * 输出：0
 */
public class FindAStrInAString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim().replaceAll("haystack = ", "");
        line = line.replaceAll("needle = ", "");
        String[] strs = line.split(", +");
        sc.close();
        System.out.println(findFirstPosition(strs[0].substring(1, strs[0].length() - 1),
                strs[1].substring(1, strs[1].length() - 1)));
    }

    private static int findFirstPosition(String str, String str1) {
        if (str.isEmpty() && str1.isEmpty()) {
            return 0;
        }
        int index = -1;
        if (str1.length() > str.length()) {
            return index;
        }
        if (str1.length() == str.length() && !equalStrs(str, 0, str1)) {
            return index;
        }
        for (int i = 0; i < str.length() - str1.length(); i++) {
            if (equalStrs(str, i, str1)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static boolean equalStrs(String str, int i, String str1) {
        for (int j = 0; j < str1.length(); j++) {
            if (str.charAt(i + j) != str1.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
