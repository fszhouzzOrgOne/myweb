package nowcoder;

import java.util.Arrays;
import java.util.Scanner;


/**
 * NC66 两个链表的第一个公共结点<br/>
 * 思路：一定有公共结点，在公共结点之後，都會是是公共結點。<br/>
 * 就看它們的長度，取其中長的，先跑一段，讓兩個鏈表剩餘部分變成一樣長，<br/>
 * 然後一個個對比就可以了，找到一個相等的，就是公共的了<br/>
 * 他有輸入是這樣的：{1,2,3,4},{5,6,7},{}
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月12日 下午10:10:02
 */
public class NC066TheFirstCommonNodeInLists {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    
    public static void main(String[] args) {
        // {1,5,3,9,4},{2,7,8,3,9,4}
        // {1,2,3,4},{5,6,7},{}
        // {1,2,3,4,5},{},{}
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().replaceAll(" +", "").split("\\},\\{");
        parts[0] = parts[0].replaceAll("\\{", "");
        parts[1] = parts[1].replaceAll("\\}", "");
//        System.out.println(Arrays.toString(parts));
        ListNode l1 = generateList(parts[0]);
        ListNode l2 = generateList(parts[1]);
//        print(l1);
//        print(l2);
        ListNode res = FindFirstCommonNode(l1, l2);
        System.out.println(null == res ? "null" : res.val);
    }
    
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (null == pHead1 || null == pHead2) {
            return null;
        }
        ListNode res = null;
        ListNode tmp1 = pHead1;
        ListNode tmp2 = pHead2;
        int len1 = getLength(tmp1);
        int len2 = getLength(tmp2);
        int diff = Math.abs(len1 - len2);
        if (diff > 0) {
            if (len1 > len2) {
                while (diff > 0) {
                    tmp1 = tmp1.next;
                    diff--;
                }
            } else {
                while (diff > 0) {
                    tmp2 = tmp2.next;
                    diff--;
                }
            }
        }
        while (tmp1.val != tmp2.val) {
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
            // 一直到了最後，都沒有找到相等的，跳出，不再比較
            if (null == tmp1 || null == tmp2) {
                break;
            }
        }
        // 找到了相等的，就不為空
        if (null != tmp1) {
            res = tmp1;
        }
        return res;
    }

    /** 遍歷一回，取到長度 */
    private static int getLength(ListNode pHead1) {
        if (null == pHead1) {
            return 0;
        }
        int len = 1;
        ListNode tmp = pHead1.next;
        while (null != tmp) {
            len++;
            tmp = tmp.next;
        }
        return len;
    }

    private static ListNode generateList(String str) {
        if (null == str || str.trim().length() <= 0) {
            return null;
        }
        ListNode head = null;
        ListNode tail = null;
        String[] nodes = str.split(",");
        for (int i = 0; i < nodes.length; i++) {
            int v = Integer.parseInt(nodes[i]);
            ListNode node = new ListNode(v);
            if (null == head) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = tail.next;
            }
//            System.out.println(v); // ok
        }
        return head;
    }

    private static void print(ListNode res) {
        ListNode tmp = res;
        System.out.print("{");
        while (null != tmp) {
            System.out.print(tmp.val);
            if (null != tmp.next) {
                System.out.print(",");
            }
            tmp = tmp.next;
        }
        System.out.println("}");
    }
}
