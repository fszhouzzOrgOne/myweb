package testProject1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 二叉树用数组表示<br/>
 * 若根下标在1，第n个节点的子节点下标为2n和2n+1，<br/>
 * 若根下标在0，第n个节点的子节点下标为2n+1和2n+2，<br/>
 * 节点为空用-1表示。<br/>
 * 求树中从根到最小的叶子节点这个路径。<br/>
 * 如输入3 5 7 -1 -1 2 4，输出3 7 2<br/>
 * 如输入3 5 7 -1 -1 2 4 -1 -1 -1 -1 -1 -1 1输出3 7 4 1
 */
public class FindingTheSmallestLeaf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();
        sc.close();
        List<Integer> ints = Arrays.stream(str.split(" +")).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        int index = findTheIndexOfTheSmallestLeaf(ints, 0, -1);
        List<Integer> res = findTheRouteFromTheSmallestLeaf(ints, index);
        if (res.isEmpty()) {
            return;
        }
        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.print(res.get(i));
            if (i > 0) {
                System.out.print(" ");
            }
        }
    }

    private static List<Integer> findTheRouteFromTheSmallestLeaf(List<Integer> ints, int index) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (index < 1) {
            return Collections.<Integer> emptyList();
        }
        res.add(ints.get(index));
        // 父节点下标
        int pi = (index + 1) / 2 - 1;
        while (pi >= 0) {
            res.add(ints.get(pi));
            pi = (pi + 1) / 2 - 1;
        }
        return res;
    }

    private static int findTheIndexOfTheSmallestLeaf(List<Integer> ints, int from, int minIndex) {
        if (from >= ints.size()) {
            return minIndex;
        }
        int li = 2 * from + 1;
        int ri = 2 * from + 2;
        boolean noLeft = (li >= ints.size() || ints.get(li) == -1);
        boolean noRight = (ri >= ints.size() || ints.get(ri) == -1);
        if (noLeft && noRight) {
            if ((minIndex == -1) || (ints.get(from) < ints.get(minIndex))) {
                return from;
            } else {
                return minIndex;
            }
        }
        
        int lm = minIndex;
        int rm = minIndex;
        // 有左子树
        if (!noLeft) {
            lm = findTheIndexOfTheSmallestLeaf(ints, li, minIndex);
        }
        // 有右子树
        if (!noRight) {
            rm = findTheIndexOfTheSmallestLeaf(ints, ri, minIndex);
        }
        // 如果没左子树
        if (lm == -1) {
            return rm;
        }
        // 如果没右子树
        if (rm == -1) {
            return lm;
        }
        return ints.get(lm) < ints.get(rm) ? lm : rm;
    }
}
