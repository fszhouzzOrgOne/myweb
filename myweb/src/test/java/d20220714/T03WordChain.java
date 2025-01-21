package d20220714;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 单词接龙：<br/>
 * 给定一批单词，接龙规则：<br/>
 * 后面首字母，和前面尾字母相同；存在多个，取最长的；长度一样，取字典序最小的。<br/>
 * 已参与的，不重复接龙。<br/>
 * 输入第一行是起始单词下标，第二行是总单词数，后面每行一个单词，全小写。<br/>
 * 输入如下，要输出worddwordda：<br/>
0
6
word
dd
da
bc
dword
d
 */
public class T03WordChain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int index = Integer.parseInt(sc.nextLine().trim());
        int cnt = Integer.parseInt(sc.nextLine().trim());
        List<String> words = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            words.add(sc.nextLine().trim());
        }
        sc.close();
        System.out.println(wordChain(words, index));
    }

    private static String wordChain(List<String> words, int index) {
        String chain = words.remove(index);
        while (true) {
            sort(words, chain);
            if (words.get(0).charAt(0) != chain.charAt(chain.length() - 1)) {
                break;
            }
            chain += words.remove(0);
        }
        return chain;
    }

    // 基数排序法模擬
    private static void sort(List<String> words, String chain) {
        bubleSort(words, (x, y) -> x.compareTo(y));
        bubleSort(words, (x, y) -> -Integer.compare(x.length(), y.length()));
        bubleSort(words, (x, y) -> {
            boolean resx = x.charAt(0) == chain.charAt(chain.length() - 1);
            boolean resy = y.charAt(0) == chain.charAt(chain.length() - 1);
            if (resx && resy) {
                return 0;
            }
            // 降序
            if (resy) {
                return 1;
            }
            return -1;
        });
    }

    // 冒泡排序
    private static void bubleSort(List<String> words, Comparator<String> comparator) {
        for (int i = 0; i < words.size(); i++) {
            for (int j = words.size() - 1; j > i; j--) {
                // j比j - 1小，前移
                if (comparator.compare(words.get(j), words.get(j - 1)) < 0) {
                    swap(words, j, j - 1);
                }
            }
        }
    }

    private static void swap(List<String> words, int j, int i) {
        String temp = words.get(i);
        words.set(i, words.get(j));
        words.set(j, temp);
    }
}
