package d20220709;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 英文输入法：<br/>
 * 依据输入的单词前缀，从已输入的英文语句中，联想出用户想输入的单词，区分大小写。<br/>
 * 按字典序输出联想到的单词序列；联想不到，输出用户输入的前缀。<br/>
 * 输入如下，要输出He：<br/>
I love you
He
 * 
 * @author Administrator
 * @time 2022年7月9日下午12:58:39
 */
public class T02EnglishInputMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String pre = sc.nextLine();
        sc.close();
        String[] parts = str.split("[^a-zA-Z]+");
        String res = findWordsByPre(parts, pre);
        System.out.println(res);
    }

    private static String findWordsByPre(String[] parts, String pre) {
        List<String> res = new ArrayList<>();
        for (String one : parts) {
            if (one.startsWith(pre)) {
                res.add(one);
            }
        }
        if (res.isEmpty()) {
            return pre;
        }
        Collections.sort(res);
        return res.toString().replaceAll("[\\[\\],]", "");
    }
}
