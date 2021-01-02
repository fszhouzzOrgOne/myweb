package nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  NC119   最小的K个数<br/>
 *  最小堆排序<br/>
 *  import java.util.ArrayList; 他沒有導入，要在前面導入<br/>
 *  [4,5,1,6,2,7,3,8],10 要是[]，不足的要輸出空
 * 
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月3日 上午5:29:22
 */
public class NC119TheLeastKNumbers {
    public static void main(String[] args) {
        // [4,5,1,6,2,7,3,8],4
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        String[] parts = input.replaceAll("\\[|\\]", "").split(",");
        int k = Integer.parseInt(parts[parts.length - 1]);
        int[] a = new int[parts.length - 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(parts[i]);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(GetLeastNumbers_Solution(a, k));
        System.out.println(Arrays.toString(a));
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (k > input.length) {
            return res;
        }
        // In a complete binary tree, the indexes of the items who has children are le len/2-1
        for (int i = input.length / 2 - 1; i >= 0; i--) {
            heapify(input, i);
        }
        for (int i = 0; i < k; i++) {
            Integer one = deleteRoot(input);
            if (null != one) {
                res.add(one);
            } else {
                return res;
            }
        }
        return res;
    }
    
    /** 刪除根，取出最小值 */
    private static Integer deleteRoot(int[] data) {
        // 用MAX_VALUE佔位
        if (data[0] == Integer.MAX_VALUE) {
            return null;
        }
        int res = data[0];
        // 用最後一個有效值，放入根
        for (int i = data.length - 1; i >= 0; i--) {
            if (Integer.MAX_VALUE != data[i]) {
                data[0] = data[i];
                data[i] = Integer.MAX_VALUE; 
                break;
            }
        }
        heapify(data, 0);
        return res;
    }

    /**
     * 下標i在最小堆轉換到合適位置
     * 
     * @param data 數組
     * @param i 下標
     */
    private static void heapify(int[] data, int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < data.length && data[l] < data[smallest]) {
            smallest = l;
        }
        if (r < data.length && data[r] < data[smallest]) {
            smallest = r;
        }
        if (i == smallest) {
            return;
        }
        swap(data, i, smallest);
        heapify(data, smallest);
    }
    
    private static int left(int i) {
        return i * 2 + 2;
    }
    
    private static int right(int i) {
        return i * 2 + 1;
    }
    
    public static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
}
