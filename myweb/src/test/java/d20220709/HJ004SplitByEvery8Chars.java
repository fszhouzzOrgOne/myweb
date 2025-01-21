package d20220709;

import java.util.Arrays;
import java.util.Scanner;

/**
 * HJ4 字符串分隔：<br/>
 * 输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；<br/>
 * abcdefghi
 * 
 * @author Administrator
 * @time 2022年7月10日下午3:54:18
 */
public class HJ004SplitByEvery8Chars {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line == null || line.length() < 1) {
                break;
            }
            int mod = line.length() % 8;
            if (mod > 0) {
                line = line + getNZeros(8 - mod);
            }
            int times = line.length() / 8;
            for (int i = 0; i < times; i++) {
                System.out.println(line.substring(i * 8, i * 8 + 8));
            }
        }
        sc.close();
    }

    private static String getNZeros(int i) {
        char[] zero = new char[i];
        Arrays.fill(zero, '0');
        return new String(zero);
    }
}
