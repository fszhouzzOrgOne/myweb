package d20220727;

import java.util.Scanner;

/**
 * 先有n种树，会有m棵不会成活，可以补种k棵，求最后最多能有几棵连续的树。<br />
 * 输入总共几种树n，接着是m，表示共几棵不会成活。 <br />
 * 接着是m个数，表示第几棵不会成活；最后输入k，表示可以补种几棵树。<br />
 * 如5 2 2 4 1，输出：3 <br />
 * 如10 3 2 4 7 1，输出6 <br />
 * 如100 12 2 4 7 25 26 77 78 54 60 24 34 35 10，输出96 <br />
 */
public class T02LargestAmountOfTreesInLine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 0表示成活，1表示不成活
        int[] trees = new int[n];
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int index = sc.nextInt();
            trees[index - 1] = 1;
        }
        int k = sc.nextInt();
        sc.close();
        System.out.println(largestAmountOfTreesBF(trees, k));
    }

    // 暴力 80%通过，后面超时
    // if (i > trees.length - res)剪枝了，90%通过
    private static int largestAmountOfTreesBF(int[] trees, int k) {
        int res = 0;
        for (int i = 0; i < trees.length; i++) {
            // 剪下枝，后面再不可能比res长了
            if (i > trees.length - res) {
                break;
            }
            int cnt1 = k;
            for (int j = i; j < trees.length; j++) {
                if (trees[j] == 1) {
                    cnt1--;
                }
                if (cnt1 < 0) {
                    if (j - i > res) {
                        res = j - i;
                    }
                    break;
                }
                if (j == trees.length - 1) {
                    if (j - i + 1 > res) {
                        res = j - i + 1;
                    }
                }
            }
        }
        return res;
    }
}
