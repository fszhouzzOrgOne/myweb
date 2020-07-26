package baijuncheng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Permutation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            List<String> res = new ArrayList<String>();
            if (null != str) {
                str = str.trim();
                if (str.length() > 0) {
                    char[] chas = str.toCharArray();
                    Arrays.sort(chas);
                    String first = new String(chas);
                    res.add(first);
                    String next = first;
                    while ((next = getNextLine(next)) != null) {
                        res.add(next);
                    }
                }
            }
            if (!res.isEmpty()) {
                System.out.println(res);
            }
        }
    }

    private static String getNextLine(String first) {
        char[] chas = first.toCharArray();
        int index = -1;
        String next = null;
        for (int i = chas.length - 1; i > 0; i--) {
            if (chas[i] > chas[i - 1]) {
                index = i - 1;
                break;
            }
        }
        if (index >= 0) {
            int minIndex = index;
            for (int i = chas.length - 1; i > index; i--) {
                if (chas[i] > chas[index]) {
                    minIndex = i;
                    break;
                }
            }
            swap(chas, index, minIndex);
            next = new String(chas);
            next = next.substring(0, index + 1)
                    + reverseString(next.substring(index + 1));
        }
        return next;
    }

    private static String reverseString(String str) {
        char[] chas = str.toCharArray();
        char[] chas2 = new char[chas.length];
        for (int i = chas.length - 1; i >= 0; i--) {
            int index2 = chas.length - 1 - i;
            chas2[index2] = chas[i];
        }
        return new String(chas2);
    }

    private static void swap(char[] chas, int index, int minIndex) {
        char tmp = chas[index];
        chas[index] = chas[minIndex];
        chas[minIndex] = tmp;
    }
}
