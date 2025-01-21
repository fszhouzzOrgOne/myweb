package d20220714;

import java.util.Scanner;

/**
 * 拼接URL：<br/>
 * 给定url前后缀，拼接成一个url。<br/>
 * 不能有多余的“/”号。<br/>
 * 输入/acm/,/bb 输出/acd/bb <br/>
 * 输入/acm,/bb 输出/acd/bb <br/>
 * 输入/acm,bb 输出/acd/bb <br/>
 */
public class T02ConcatenateURLParts {
    private static final String SLASH = "/";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().trim().split(",+");
        sc.close();
        // prefix
        if (parts[0].endsWith(SLASH)) {
            parts[0] = parts[0].substring(0, parts[0].length() - 1);
        }
        // suffix
        if (!parts[1].startsWith(SLASH)) {
            parts[1] = SLASH + parts[1];
        }
        System.out.println(parts[0] + parts[1]);
    }
}
