package nowcoder;

import java.util.Scanner;
import java.util.Stack;

/**
 * NC76 用两个栈实现队列<br/>
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 
 * @author fszhouzz@qq.com
 * @time 2020年12月27日 上午5:19:30
 */
public class NC076MakeAQueueUsing2Stacks {

    public static void main(String[] args) {
        // 1,2,4,4,5
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        String[] parts = input.replaceAll(" +", "").split(",");
        NC076MakeAQueueUsing2Stacks queue = new NC076MakeAQueueUsing2Stacks();
        for (int i = 0; i < parts.length; i++) {
            int one = Integer.parseInt(parts[i]);
            queue.push(one);
        }
        for (int i = 0; i < parts.length; i++) {
            System.out.println(queue.pop());
        }
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        while (stack1.size() > 1) {
            stack2.push(stack1.pop());
        }
        int node = stack1.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return node;
    }
}
