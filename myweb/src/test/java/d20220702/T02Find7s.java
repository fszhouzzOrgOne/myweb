package d20220702;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 喊7的次数重排：<br/>
 * n个人围成一圈，并编号一到n，从1开始报数，报数都比前一个人大1。<br/>
 * 数字是7的位数或数字本身包含7，就喊“过”。<br/>
 * 输入是各人喊“过”的次数，判断对不对，输出正确的顺序。<br/>
 * 输入0 1 0，是不对的顺序，输出：1 0 0 <br/>
 * 
 * @author Administrator
 * @time 2022年7月2日下午2:00:17
 */
public class T02Find7s {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ns = sc.nextLine().trim().split(" ");
        sc.close();
        int total = 0;
        for (int i = 0; i < ns.length; i++) {
            total += Integer.parseInt(ns[i]);
        }
        int[] order = findTheRightOrderFor7s(total, ns.length);
        System.out.println(Arrays.toString(order).replaceAll("[\\[\\],]", ""));
    }

    private static int[] findTheRightOrderFor7s(int total, int n) {
        int[] order = new int[n];
        Integer no = 1;
        int count = 0;
        while (count < total) {
            int index = (no - 1) % n;
            if (no % 7 == 0 || no.toString().contains("7")) {
                order[index]++;
                count++;
            }
            no++;
        }
        return order;
    }
}
