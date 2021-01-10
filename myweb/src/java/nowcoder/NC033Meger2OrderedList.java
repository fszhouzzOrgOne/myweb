package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * NC33 合并有序链表<br/>
 * 注意了，可能有空鏈表，如：{},{0}
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月10日 上午10:45:47
 */
public class NC033Meger2OrderedList {

    static class ListNode {
       int val;
       ListNode next = null;
    }
    
    public static void main(String[] args) {
        // {},{0}
        // {2},{1}
        // {1,5,7,9},{2,3,3,4}
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().replaceAll(" +", "").split("\\},\\{");
        parts[0] = parts[0].replaceAll("\\{", "");
        parts[1] = parts[1].replaceAll("\\}", "");
//        System.out.println(Arrays.toString(parts));
        ListNode l1 = generateList(parts[0]);
        ListNode l2 = generateList(parts[1]);
//        print(l1);
//        print(l2);
        ListNode res = mergeTwoLists(l1, l2);
        print(res);
    }

    public static ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        ListNode tmp1 = l1; 
        ListNode tmp2 = l2;
        while (null != tmp1 && null != tmp2) {
            if (tmp1.val <= tmp2.val) {
                if (null == head) {
                    head = tmp1;
                    tail = tmp1;
                } else {
                    tail.next = tmp1;
                    tail = tail.next;
                }
                tmp1 = tmp1.next;
            } else {
                if (null == head) {
                    head = tmp2;
                    tail = tmp2;
                } else {
                    tail.next = tmp2;
                    tail = tail.next;
                }
                tmp2 = tmp2.next;
            }
        }
        while (null != tmp1) {
            if (null == head) {
                head = tmp1;
                tail = tmp1;
            } else {
                tail.next = tmp1;
                tail = tail.next;
            }
            tmp1 = tmp1.next;
        }
        while (null != tmp2) {
            if (null == head) {
                head = tmp2;
                tail = tmp2;
            } else {
                tail.next = tmp2;
                tail = tail.next;
            }
            tmp2 = tmp2.next;
        }
        return head;
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
            ListNode node = new ListNode();
            node.val = v;
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
