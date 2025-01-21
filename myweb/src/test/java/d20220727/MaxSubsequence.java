package d20220727;

import java.util.Arrays;
import java.util.Stack;

/**
 * 最大子序列
 */
public class MaxSubsequence {

    public static void main(String[] args) {
        String[] strs = "872649201".split("");
        int k = 4;
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        System.out.println(Arrays.toString(maxSubsequence(nums, k)));
    }

    private static Integer[] maxSubsequence(int[] nums, int k) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num > stack.peek() && (stack.size() + nums.length - i) > k) {
                stack.pop();
            }
            stack.add(num);
        }
        return stack.toArray(new Integer[k]);
    }
}
