package d20220714;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 关联子串<br/>
 * 输入两个字符串，str1 str2，<br/>
 * 如果str1中的字符，经过调整位置，是str2的子串，str1就是str2的关联子串。<br/>
 * 如果是关联子串，输入子串在str2中的起始下标，否则输出-1。<br/>
 * 输入：abc efghicabiii，输出5，<br/>
 * 输入：abc efghicadbiii，输出-1。<br/>
 * 输入：abcd efghicadbiii，输出5。<br/>
 */
public class T01RelatedSubstring {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().trim().split(" +");
        sc.close();
        System.out.println(getRelatedStrIndex(strs[0], strs[1]));
    }

    private static int getRelatedStrIndex(String str1, String str2) {
        List<String> perms = new ArrayList<>();
        permutation(str1.toCharArray(), 0, str1.length() - 1, perms);
        int res = -1;
        for (String per : perms) {
            int index = str2.indexOf(per);
            if (res < 0 || (index > 0 && index < res)) {
                res = index;
            }
        }
        return res;
    }

    private static void permutation(char[] chas, int start, int end, List<String> res) {
        if (start >= end) {
            res.add(new String(chas));
            return;
        }
        // 每一位都放到第一位，递归，再恢复
        for (int i = start; i <= end; i++) {
            swap(chas, start, i);
            permutation(chas, start + 1, end, res);
            swap(chas, start, i);
        }
    }

    private static void swap(char[] chas, int i, int j) {
        char temp = chas[i];
        chas[i] = chas[j];
        chas[j] = temp;
    }
}
