package nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * NC45 实现二叉树先序，中序和后序遍历<br/>
 * 輸入是按樹的行，一行一行來的<br/>
 * 注意格式是帶#的表示空元素：[40,#,41,9,7,#,14,21,#,34,30,27,3]<br/>
 * 如果上面有個節點是#了，輸入就不會再輸入它的子節點了
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月9日 下午7:47:27
 */
public class NC045TraverseBinaryTree {

    /**
     * 核心編程模式，只讓寫threeOrders一個方法<br/>
     * 從main寫起，生成樹、父子下標關係等，全部都考察一遍<br/>
     * 隨時測試。編程理論，寫一點，測一測，寫一點，測一測，寫一點，測一測<br/>
     */
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
        // 收集器模式：List<Integer> list = new ArrayList<Integer>();
        // 訪問者模式：用個訪問者對象
        TreeNodeVisitor visitor = new TreeNodeVisitor();
        preorderTravers(root, visitor);
        arr[0] = visitor.list2Array();
        // 中序
        TreeNodeVisitor visitor2 = new TreeNodeVisitor();
        inorderTravers(root, visitor2);
        arr[1] = visitor2.list2Array();
        // 後序
        TreeNodeVisitor visitor3 = new TreeNodeVisitor();
        postorderTravers(root, visitor3);
        arr[2] = visitor3.list2Array();
        return arr;
    }

    private static void postorderTravers(TreeNode root, TreeNodeVisitor visitor) {
        if (null == root) {
            return;
        }
        postorderTravers(root.left, visitor);
        postorderTravers(root.right, visitor);
        visitor.add(root.val);
    }

    private static void inorderTravers(TreeNode root, TreeNodeVisitor visitor) {
        if (null == root) {
            return;
        }
        inorderTravers(root.left, visitor);
        visitor.add(root.val);
        inorderTravers(root.right, visitor);
    }

    private static void preorderTravers(TreeNode root, TreeNodeVisitor visitor) {
        if (null == root) {
            return;
        }
        visitor.add(root.val);
        preorderTravers(root.left, visitor);
        preorderTravers(root.right, visitor);
    }

    /** 訪問者 */
    static class TreeNodeVisitor {
        List<Integer> list = new ArrayList<Integer>();
        
        public void add(Integer v) {
            list.add(v);
        }
        
        private int[] list2Array() {
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }
            return arr;
        }
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
