package testProject1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 按ASCII碼排序，取出第N小的字符，在原字符串的第一個位置
 * aDbEcGd
 * 先按ASCII排，DEFabcd
 */
public class TheNthLeastChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.next();
            int n = sc.nextInt();
            int index = getIndexOfTheNthLeastAscii(line, n);
            System.out.println(index);
        }
    }

    private static int getIndexOfTheNthLeastAscii(String line, int n) {
        char[] lchars = line.toCharArray();
        Arrays.sort(lchars);
        int target = (n > lchars.length) ? lchars.length : n;
        Character ch = lchars[target - 1];
        return line.indexOf(ch.toString());
    }
}
