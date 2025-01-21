package d20220709;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 组成最大数:<br/>
 * 小组每位都有一张卡片,卡片上是6位内的正整数，将卡片连起来，找到能组成的最大数字。<br/>
 * 输入：22,221，输出22221。<br/>
 * 输入：22,9,221，输出922221。<br/>
 * 输入：22,9,241，输出924122。<br/>
 * 
 * @author Administrator
 * @time 2022年7月9日下午12:54:59
 */
public class T01TheLargestNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inputs = sc.nextLine().split(",");
        Arrays.sort(inputs, (x, y) -> {
            int minLen = Math.min(x.length(), y.length());
            int minRes = -x.substring(0, minLen).compareTo(y.substring(0, minLen));
            if (minRes != 0) {
                return minRes;
            }
            if (x.length() > minLen) {
                return -x.substring(minLen).compareTo(y);
            } else {
                return -x.compareTo(y.substring(minLen));
            }
        });
        System.out.println(Arrays.toString(inputs).replaceAll("[\\]\\[ ]+", ""));
    }
}
