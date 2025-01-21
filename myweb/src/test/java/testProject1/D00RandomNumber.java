package testProject1;

import java.util.Random;

/**
 * 生成随机数
 * 
 * @author Administrator
 * @time 2022年6月13日下午8:20:41
 */
public class D00RandomNumber {
    private static final int MAX = 415;
    
    public static void main(String[] args) {
        int num = new Random().nextInt(MAX);
        System.out.println(num);
    }
}
