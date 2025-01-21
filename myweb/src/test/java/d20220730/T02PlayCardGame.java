package d20220730;

import java.util.Scanner;

/**
 * 玩牌高手：<br/>
 * 给定一个长度为N的整型数，表示选手在N轮可选择的牌面数。<br />
 * 1，在每轮里选手可以选择获取该轮牌面，则其总分数加上该轮牌面分数，为其新的总分数。<br />
 * 2,选手也可以不选择本轮牌面，直接到下一轮，此时将当前总分数还原到3轮前的总分数，<br />
 * 若当前轮次小于等于3,则总分数为0.<br />
 * 3,选手总分数为0,且每轮都要参加。<br />
 * 输入：1,-5,-6,4,3,6,-2 输出：11 <br />
 */
public class T02PlayCardGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        String[] inputs = input.trim().split(",");
        int[] nums = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            nums[i] = Integer.parseInt(inputs[i]);
        }
        int[] tempResult = new int[inputs.length];
        tempResult[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int preResult = i <= 2 ? 0 : tempResult[i - 3];
            tempResult[i] = Math.max(tempResult[i - 1] + nums[i], preResult);
        }
        System.out.println(tempResult[tempResult.length - 1]);
    }
}
