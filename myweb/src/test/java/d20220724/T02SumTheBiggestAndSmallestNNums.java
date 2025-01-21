package d20220724;

import java.util.Scanner;

/**
 * 最大N个数，和最小N个数，的和：<br/>
 * 还需要对数组去重。<br/>
 * 输入m，表示有m个数；接下输入m个数；最後输入n，表示最大最小n个数。 如输入5 95 88 83 64 100 2，要输出342 <br/>
 * 如输入6 95 88 83 64 100 100 2，要输出342 <br/>
 */
public class T02SumTheBiggestAndSmallestNNums {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] nums = new int[m];
        for (int i = 0; i < m; i++) {
            nums[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        sc.close();
        System.out.println(sumTheBiggestAndSmallestNNum(nums, n));
    }

    private static Integer sumTheBiggestAndSmallestNNum(int[] nums, int n) {
        insertionSort(nums);
        Integer res = null;
        int added = 0;
        for (int i = 0; i < nums.length && added < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            res = (res == null) ? nums[i] : res + nums[i];
            added++;
        }
        added = 0;
        for (int i = nums.length - 1; i >= 0 && added < n; i--) {
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                continue;
            }
            res += nums[i];
            added++;
        }
        return res;
    }

    private static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int one = nums[i];
            int j = i;
            while (j >= 1 && nums[j - 1] > one) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = one;
        }
    }
}
