package d20220727;

import java.util.Arrays;

/**
 * LC321. 拼接最大数：<br/>
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。<br/>
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，<br/>
 * 要求从同一个数组中取出的数字保持其在原数组中的相对顺序。<br/>
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。<br/>
 */
public class MaxSubsequenceIn2Str {

    public static void main(String[] args) {
        int k = 5;
        int[] nums1 = generateNumArray("67");
        int[] nums2 = generateNumArray("604");
        int[] res = maxNumber(nums1, nums2, k);
        System.out.println(Arrays.toString(res));
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = null;
        // 左边最少可以多少个
        int min = Math.max(0, k - nums2.length);
        int max = Math.min(k, nums1.length);
        for (int i = min; i <= max; i++) {
            int[] sub1 = maxSubsequence(nums1, i);
            int[] sub2 = maxSubsequence(nums2, k - i);
            int[] res1 = merge(sub1, sub2);
            // System.out.println(i + Arrays.toString(sub1) + " + " + (k - i) +
            // Arrays.toString(sub2) + " = "
            // + Arrays.toString(res1));
            res = res == null || maxArray(res, 0, res1, 0) < 0 ? res1 : res;
        }
        return res;
    }

    private static int maxArray(int[] arr1, int i1, int[] arr2, int i2) {
        while (i1 < arr1.length && i2 < arr2.length) {
            int diff = arr1[i1] - arr2[i2];
            if (diff != 0) {
                return diff;
            }
            i1++;
            i2++;
        }
        int len1 = arr1.length - i1;
        int len2 = arr2.length - i2;
        return len1 - len2;
    }

    private static int[] merge(int[] sub1, int[] sub2) {
        if (sub1.length == 0) {
            return sub2;
        }
        if (sub2.length == 0) {
            return sub1;
        }
        int[] res = new int[sub1.length + sub2.length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < res.length; i++) {
            if (maxArray(sub1, i1, sub2, i2) > 0) {
                res[i] = sub1[i1];
                i1++;
            } else {
                res[i] = sub2[i2];
                i2++;
            }
        }
        return res;
    }

    private static int[] generateNumArray(String str) {
        String[] strs = str.split("");
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        return nums;
    }

    private static int[] maxSubsequence(int[] nums, int k) {
        if (k <= 0) {
            return new int[0];
        }
        int[] stack = new int[k];
        int stackSize = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (stackSize > 0 && num > stack[stackSize - 1] && (stackSize + nums.length - i) > k) {
                stackSize--;
            }
            if (stackSize < k) {
                stack[stackSize] = num;
                stackSize++;
            }
        }
        return stack;
    }
}
