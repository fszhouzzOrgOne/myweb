package d20220702;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 数组去重排序：〈br/>
 *  给定一个乱序数组，算每个数字出现次数，<br/>
 * 按高到低排序，相同的，按原来出现的顺序排序。 <br/>
 * 输入：1,3,3,3,2,4,4,4,5<br/>
 * 输出：3,4,1,2,5<br/>
 * 
 * @author Administrator
 * @time 2022年7月2日下午1:43:40
 */
public class T01OrderNumbersInAnArray {
    private static final String COMMA = ",";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ns = sc.nextLine().trim().split(COMMA);
        sc.close();
        Map<String, Number> map = new HashMap<>();
        for (int i = 0; i < ns.length; i++) {
            Number n = map.getOrDefault(ns[i], new Number(ns[i], i));
            n.setCount(n.getCount() + 1);
            map.put(ns[i], n);
        }
        String res = map.values().stream().sorted((x, y) -> {
            int cntRes = -Integer.compare(x.getCount(), y.getCount());
            if (cntRes != 0) {
                return cntRes;
            }
            return Integer.compare(x.getIndex(), y.getIndex());
        }).map(x -> x.getName()).collect(Collectors.joining(COMMA));
        System.out.println(res);
    }

    public static class Number {
        private String name;
        private int index;
        private int count;

        public Number(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

    }
}
