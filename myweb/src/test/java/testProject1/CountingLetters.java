package testProject1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 统计字符串的字母个数，先按个数降序，相同的按字母排序，且小写在大写前面<br/>
 * 输入xyxyXX，输出x:2;y:2;X:2 〈br/>
 * abcDDDEEacbABcfdbBCC
 * 
 */
public class CountingLetters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();
        sc.close();
        if (str.length() < 1) {
            return;
        }
        Map<Character, Integer> lm = new HashMap<>();
        for (Character ch : str.toCharArray()) {
            int cnt = lm.getOrDefault(ch, 0);
            lm.put(ch, cnt + 1);
        }
        String res = lm.entrySet().stream().sorted((x, y) -> {
            int cntRes = Integer.compare(y.getValue(), x.getValue());
            if (cntRes != 0) {
                return cntRes;
            }
            boolean xUp = Character.compare(x.getKey(), 'A') >= 0 && Character.compare(x.getKey(), 'Z') <= 0;
            boolean yUp = Character.compare(y.getKey(), 'A') >= 0 && Character.compare(y.getKey(), 'Z') <= 0;
            if (xUp && !yUp) {
                return 1;
            }
            if (yUp && !xUp) {
                return -1;
            }
            return Character.compare(x.getKey(), y.getKey());
        })
        .map(x -> x.getKey() + ":" + x.getValue())
        .collect(Collectors.joining(";"));
        System.out.println(res);
    }

}
