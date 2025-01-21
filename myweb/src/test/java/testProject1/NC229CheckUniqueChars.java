package testProject1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 牛客229题<br/>
 * 给定一个字符串，请你判断其中每个字符是否全都不同。<br/>
 * 数据范围：字符串长度满足 1 \le n \le 100 \1≤n≤100 <br/>
 * 输入："nowcoder" 返回值：false<br/>
 * 输入："nowcOder" 返回值：true
 * 
 * @author Administrator
 * @time 2022年6月13日下午8:25:15
 */
public class NC229CheckUniqueChars {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        System.out.println(isUnique(str));
    }
    
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param str string字符串 
     * @return bool布尔型
     */
    public static boolean isUnique(String str) {
        if (null == str || str.isEmpty()) {
            return true;
        }
        char[] chars = str.toCharArray();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            Character ch = chars[i];
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }
}
