package testProject1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 对字符串中的所有单词进行倒排。<br/>
 * 说明：<br/>
 * 1、构成单词的字符只有26个大写或小写英文字母；<br/>
 * 2、非构成单词的字符均视为单词间隔符；<br/>
 * 3、要求倒排后的单词间隔符以一个空格表示；如果原字符串中相邻单词间有多个间隔符时，倒排转换后也只允许出现一个空格间隔符；<br/>
 * 4、每个单词最长20个字母；<br/>
 * I am a student 结果student a am I  <br/>
 * $bo*y gi!r#l 结果l r gi y bo  <br/>
 * 
 * @author Administrator
 * @time 2022年6月14日下午9:47:08
 */
public class HJ31ReversingWords {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        String[] words = str.trim().split("[^a-zA-Z]+");
        for (int i = 0, j = words.length - 1; i < j; i++, j--) {
            String t = words[i];
            words[i] = words[j];
            words[j] = t;
        }
        String res = Arrays.stream(words).collect(Collectors.joining(" "));
        System.out.println(res);
    }
}
