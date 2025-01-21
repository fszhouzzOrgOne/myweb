package d20220730;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 分苹果：<br/>
 * A，B两个人把苹果分两堆。A计算规则是按二进制加法计算，并且不计算进位 12+5=9（1100+0101=9）<br/>
 * B的计算规则是按十进制，包括正常进位。<br/>
 * B希望满足A的前提下，获取最多苹果重量。<br/>
 * 输入3 3 5 6 输出：11 <br/>
 * 输入3 9 5 12 输出：21 <br/>
 */
public class T01DeliverApples {

    // 能分成两堆，都异或的结果，必定为0
    // 取出最小的一个值min，其他值的异或结果必定=min
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> queue = new PriorityQueue<>();
        int init = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            init ^= num;
            queue.add(num);
            sum += num;
        }
        sc.close();
        if (init == 0) {
            int min = queue.poll();
            System.out.println(sum - min);
        } else {
            System.out.println(-1);
        }
    }
}
