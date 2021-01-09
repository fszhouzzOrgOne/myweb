package nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * NC45 实现二叉树先序，中序和后序遍历<br/>
 * 輸入是按樹的行，一行一行來的<br/>
 * 注意格式是帶#的表示空元素：[40,#,41,9,7,#,14,21,#,34,30,27,3]<br/>
 * 如果上面有个節點是#了，輸入就不會再輸入它的子節點了
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月9日 下午7:47:27
 */
public class NC45TraverseBinaryTree {

    public static void main(String[] args) {
        // {1,2,3}
        // {1,2,#,3,4,5}
        // {1,#,2,#,3,4,5}
        Scanner in = new Scanner(System.in);
        String[] parts = in.next().replaceAll("\\{|\\}| +", "").split(",");
        List<Integer> ints = new ArrayList<Integer>();
        int index = 0;
        for (int i = 0; i < parts.length; i++) {
            // 0之後的節點，發現父節點為空
            while (index > 0 && null == ints.get(getParentIndex(index))) {
                ints.add(null); // 左
                index++;
                ints.add(null); // 右
                index++;
            }
            if (!"#".equals(parts[i])) {
                ints.add(Integer.parseInt(parts[i]));
            } else {
                ints.add(null);
            }
            index++;
        }
        // System.out.println(ints.size() + ": " + ints);
        TreeNode root = heapify(ints, 0);
        int[][] res = threeOrders(root);
        System.out.print("[");
        for (int i = 0; i < res.length; i++) {
            int[] js = res[i];
            System.out.print(Arrays.toString(js));
            if (i < res.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }


    public static int[][] threeOrders (TreeNode root) {
        // write code here
        int[][] arr = new int[3][];
        // 先序
        List<Integer> list = new ArrayList<Integer>();
        preorderTravers(root, list);
        arr[0] = list2Array(list);
        // 中序
        list = new ArrayList<Integer>();
        inorderTravers(root, list);
        arr[1] = list2Array(list);
        // 後序
        list = new ArrayList<Integer>();
        postorderTravers(root, list);
        arr[2] = list2Array(list);
        return arr;
    }

    private static void postorderTravers(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }
        postorderTravers(root.left, list);
        postorderTravers(root.right, list);
        list.add(root.val);
    }

    private static void inorderTravers(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }
        inorderTravers(root.left, list);
        list.add(root.val);
        inorderTravers(root.right, list);
    }

    private static void preorderTravers(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }
        list.add(root.val);
        preorderTravers(root.left, list);
        preorderTravers(root.right, list);
    }

    /**
     * 遞歸生成樹<br/>
     *    1
     *  2   3
     * 4 5 6 7
     */
    private static TreeNode heapify(List<Integer> ints, int currIndex) {
        if (null == ints.get(currIndex)) {
            return null;
        }
        TreeNode root = new TreeNode(ints.get(currIndex));
        int leftIndex = getLeftIndex(currIndex);
        if (leftIndex < ints.size()) {
            root.left = heapify(ints, leftIndex);
        }
        int rightIndex = getRightIndex(currIndex);
        if (rightIndex < ints.size()) {
            root.right = heapify(ints, rightIndex);
        }
        return root;
    }

    // 3->1, 4->1, 5->2, 6->2, 2->0, 1->0
    private static int getParentIndex(int currIndex) {
        return (currIndex - 1) / 2;
    }

    private static int getLeftIndex(int currIndex) {
        return currIndex * 2 + 1;
    }

    private static int getRightIndex(int currIndex) {
        return currIndex * 2 + 2;
    }

    private static TreeNode addToTree(TreeNode one, TreeNode root) {
        if (null == root) {
            return one;
        }
        if (one.val >= root.val) {
            if (null == root.right) {
                root.right = one;
            } else {
                addToTree(one, root.right);
            }
        } else {
            if (null == root.left) {
                root.left = one;
            } else {
                addToTree(one, root.left);
            }
        }
        return root;
    }

    /** 列表轉成數組 */
    private static int[] list2Array(List<Integer> pre) {
        int[] arr = new int[pre.size()];
        for (int i = 0; i < pre.size(); i++) {
            arr[i] = pre.get(i);
        }
        return arr;
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        
        public TreeNode(int v) {
            val = v;
            // System.out.println(v);
        }
    }
}
