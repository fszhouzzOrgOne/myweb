package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * NC78 反转链表 
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月3日 上午5:10:25
 */
public class NC078ReverseList {

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // {1,2,3}
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        String[] parts = input.replaceAll("\\{|\\}", "").split(",");
        ListNode head = null;
        ListNode tail = null;
        for (int i = 0; i < parts.length; i++) {
            ListNode node = new ListNode(Integer.parseInt(parts[i]));
            if (null == tail) {
                tail = node;
                head = node;
            } else {
                tail.next = node;
                tail = tail.next;
            }
        }
        head = ReverseList(head);
        System.out.print('{');
        while (null != head) {
            System.out.print(head.val);
            if (null != head.next) {
                System.out.print(',');
            }
            head = head.next;
        }
        System.out.print('}');
        System.out.println();
    }

    public static ListNode ReverseList(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode curr = head;
        ListNode previous = curr.next;
        curr.next = null;
        while (null != previous) {
            ListNode tmp = previous;
            previous = previous.next;
            tmp.next = curr;
            curr = tmp;
        }
        return curr;
    }
}
