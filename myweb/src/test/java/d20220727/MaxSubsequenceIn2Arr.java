package d20220727;



import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定长度分别为m和n的两个数组，其元素由0-9构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n)个数字拼接成一个新的数，
 * 要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为k的数组。
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出: * [9, 8, 6, 5, 3]
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出: [6, 7, 6, 0, 4]
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出: [9, 8, 9]
 */
public class MaxSubsequenceIn2Arr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums1 = inputNums(sc.nextLine());
        int[] nums2 = inputNums(sc.nextLine());
        int k = Integer.parseInt(sc.nextLine().split("=")[1].trim());
        sc.close();
        int[] res = maxArr(nums1, nums2, k);
        System.out.println(Arrays.toString(res));
    }

    private static int[] maxArr(int[] nums1, int[] nums2, int k) {
        int startIndex = k - nums2.length;
        if (startIndex < 0) {
            startIndex = 0;
        }
        int[] res = new int[k];
        int endIndex = nums1.length < k ? nums1.length : k;
        for (int i = startIndex; i <= endIndex; i++) {
            int[] sub1 = getSubArr(nums1, i);
            int[] sub2 = getSubArr(nums2, k - i);
            int[] res1 = combineArr(sub1, sub2);
            res = compareArr(res, 0, res1, 0) > 0 ? res : res1;
        }
        return res;
    }

    private static int[] combineArr(int[] sub1, int[] sub2) {
        int[] res = new int[sub1.length + sub2.length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < res.length; i++) {
            // 看i1、i2开始的后缀大的，取一个放入res
            if (compareArr(sub1, i1, sub2, i2) > 0) {
                res[i] = sub1[i1];
                i1++;
            } else {
                res[i] = sub2[i2];
                i2++;
            }
        }
        return res;
    }

    // 看i1、i2开始的后缀大
    private static int compareArr(int[] sub1, int i1, int[] sub2, int i2) {
        // 都有的部分
        while (i1 < sub1.length && i2 < sub2.length) {
            int r = Integer.compare(sub1[i1], sub2[i2]);
            if (r != 0) {
                return r;
            }
            i1++;
            i2++;
        }
        // 后面比长度
        int len1 = sub1.length - i1;
        int len2 = sub2.length - i2;
        return len1 - len2;
    }

    // 取k长的最大子序列
    private static int[] getSubArr(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] stack = new int[k];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (size > 0 && num > stack[size - 1] && (size + nums.length - i) > k) {
                size--;
            }
            if (size < k) {
                stack[size++] = num;
            }
        }
        return stack;
    }

    private static int[] inputNums(String line) {
        String nums = line.trim().split("=")[1].trim().replaceAll("[\\]\\[,]", "");
        String[] parts = nums.split(" +");
        int[] arr = new int[parts.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }
}
