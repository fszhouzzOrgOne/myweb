package testProject1;

import java.util.Scanner;

/**
 * 输入一个整数，将这个整数以字符串的形式逆序输出<br/>
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001<br/>
 * 1516000 
 * 
 * @author Administrator
 * @time 2022年6月14日下午9:41:23
 */
public class HJ11ReversingNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer num = sc.nextInt();
        sc.close();
        char[] chas = num.toString().toCharArray();
        for (int i = 0, j = chas.length - 1; i < j; i++, j--) {
            char t = chas[i];
            chas[i] = chas[j];
            chas[j] = t;
        }
        System.out.println(new String(chas));
    }
}
