package nowcoder;

import java.util.Scanner;

public class ReversingNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            if (str.matches("-?[0-9]+")) {
                String minus = str.charAt(0) == '-' ? "-" : "";
                String numStr = str.substring(str.charAt(0) == '-' ? 1 : 0);
                char[] chas = reverseNumStr(numStr);
                String res = minus;
                for (char c : chas) {
                    res += c;
                }
                System.out.println(res);
            }
        }
    }

    private static char[] reverseNumStr(String numStr) {
        char[] nums = numStr.toCharArray();
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            char tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }
}
