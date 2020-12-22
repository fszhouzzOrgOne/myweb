package nowcoder;

/**
 * NC105    二分查找<br/>
 * 请实现有重复数字的有序数组的二分查找。<br/>
 * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
 * 
 * @author fszhouzz@qq.com
 * @time 2020年12月23日 上午4:30:52
 */
public class NC105BinarySearch {

    public static void main(String[] args) {
//        String input = "5,4,[1,2,4,4,5]";
        String input = "5,1,[2,2,4,4,5]";
        String[] parts = input.replaceAll("\\[|\\]", "").split(",");
        int n = Integer.parseInt(parts[0]);
        int v = Integer.parseInt(parts[1]);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(parts[i + 2]);
        }
        System.out.println(upper_bound_(n, v, a));
    }

    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    public static int upper_bound_ (int n, int v, int[] a) {
        // write code here
        int index = binarySearch(a, 0, a.length - 1, v);
        if (index >= n) {
            return n + 1;
        }
        // 移到前一個位置，所以後面加2
        while (index > 0 && a[index] >= v) {
            index--;
        }
        return index + 2;
    }
    
    public static int binarySearch(int[] a, int startI, int endI, int v) {
        // 相遇了，沒有找到相等的，返回當前位置
        if (startI >= endI) {
            return endI;
        }
        int mi = (startI + endI) / 2;
        if (a[mi] == v) {
            return mi;
        } else if (a[mi] > v) {
            return binarySearch(a, startI, mi - 1, v);
        } else {
            return binarySearch(a, mi + 1, endI, v);
        }
    }
}
