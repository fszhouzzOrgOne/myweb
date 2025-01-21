package d20220709;

import java.util.Scanner;

/**
 * 代码编辑器：<br/>
 * 
 * 
 * @author Administrator
 * @time 2022年7月9日下午1:11:32
 */
public class T03CodeEditor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = Integer.parseInt(sc.nextLine().trim());
        String text = sc.nextLine();
        int index = 0;
        int cnt2 = 0;
        for (int i = 0; i < cnt; i++) {
            String[] parts = sc.nextLine().trim().split(" +");
            String param = parts[1];
            switch (parts[0]) {
            case "INSERT":
                if (index == 0) {
                    text = param + text;
                } else if (index == text.length()) {
                    text += param;
                } else {
                    text = text.substring(0, index) + param + text.substring(index);
                }
                index += param.length();
                break;
            case "FORWARD":
                cnt2 = Integer.parseInt(param);
                index += cnt2;
                if (index > text.length()) {
                    index = text.length();
                }
                break;
            case "BACKWARD":
                cnt2 = Integer.parseInt(param);
                index -= cnt2;
                if (index < 0) {
                    index = 0;
                }
                break;
            case "SEARCH-FORWARD":
                if (index == text.length()) {
                    break;
                }
                int index2 = text.indexOf(param, index);
                if (index2 > index) {
                    index = index2;
                }
                break;
            case "SEARCH-BACKWARD":
                if (index == 0) {
                    break;
                }
                int index3 = text.indexOf(param);
                if (index3 >= 0 && index3 < index) {
                    index = index3;
                }
                break;
            case "DELETE":
                if (index == text.length()) {
                    break;
                }
                cnt2 = Integer.parseInt(param);
                String pre = (index == 0) ? "" : text.substring(0, index);
                String rest = text.substring(index);
                if (rest.length() <= cnt2) {
                    text = pre;
                    break;
                }
                text = pre + rest.substring(cnt2);
                break;
            case "REPLACE":
                if (index == text.length()) {
                    text += param;
                    index = text.length();
                    break;
                }
                String pre2 = (index == 0) ? "" : text.substring(0, index);
                String rest2 = text.substring(index);
                if (rest2.length() <= param.length()) {
                    text = pre2 + param;
                    index = text.length();
                } else {
                    text = pre2 + param + rest2.substring(param.length());
                    index += param.length();
                }
                break;
            }
            System.out.println(text + ", index=" + index);
        }
        sc.close();
        System.out.println(text);
    }
}
