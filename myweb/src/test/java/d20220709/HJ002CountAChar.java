package d20220709;

import java.util.Locale;
import java.util.Scanner;

/**
 * HJ2 计算某字符出现次数：<br/>
 * 接受一个由字母、数字和空格组成的字符串，和一个字符，<br/>
 * 不区分大小写。<br/>
 * AbcadefA 找A
 * 
 * @author Administrator
 * @time 2022年7月10日下午3:43:59
 */
public class HJ002CountAChar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().toLowerCase(Locale.ROOT);
        char ch = sc.nextLine().toLowerCase(Locale.ROOT).charAt(0);
        sc.close();
        int cnt = 0;
        for (int i = 0; i < line.length(); i++) {
            if (ch == line.charAt(i)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
