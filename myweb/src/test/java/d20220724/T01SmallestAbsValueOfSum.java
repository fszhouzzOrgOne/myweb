package d20220724;

import java.util.Scanner;

/**
 * 数字两两之和的绝对值最小：
 * 5 7 -3 -1 11 15，输出2
 */
public class T01SmallestAbsValueOfSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().trim().split(" +");
        sc.close();
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        System.out.println(smallestAbsValueOfSum(nums));
    }

    private static int smallestAbsValueOfSum(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int res2 = Math.abs(nums[i] + nums[j]);
                if (res2 < res) {
                    res = res2;
                }
            }
        }
        return res;
    }
}
