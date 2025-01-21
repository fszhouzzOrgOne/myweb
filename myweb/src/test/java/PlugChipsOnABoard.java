import java.util.Scanner;
import java.util.Stack;

/**
 * 如下输出11111000\n11111111：<br />
8
2
ACABA
 */
public class PlugChipsOnABoard {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numm = Integer.parseInt(scan.nextLine());
        int numn = Integer.parseInt(scan.nextLine());
        String line = scan.nextLine().trim();
        scan.close();
        Stack[] stacks = new Stack[numn];
        for (int i = 0; i < numn; i++) {
            stacks[i] = new Stack<Integer>();
        }
        plugIn(stacks, numm, line.toCharArray());
        print(stacks);
    }

    private static void plugIn(Stack[] stacks, int limit, char[] charArray) {
        int currRow = 0;
        int cols = limit;
        for (char cha : charArray) {
            int size = getSize(cha);
            if (cols < 8 && size >= 8)  continue;
            if (cols < 2 && size >= 2)  continue;
            if (cols < 1 && size >= 1)  continue;
            // 找到能放的行
            for (int i = currRow; i < stacks.length; i++) {
                if (limit - stacks[i].size() >= size) {
                    putItems(stacks[i], size, 1);
                    break;
                }
            }
            // 当前行滿了
            if (stacks[currRow].size() >= limit) {
                currRow++;
            }
        }
        // 補0
        for (Stack stack : stacks) {
            if (stack.size() < limit) {
                putItems(stack, limit - stack.size(), 0);
            }
        }
    }
    
    private static void putItems(Stack stack, int size, int value) {
        for (int i = 0; i < size; i++) {
            stack.add(value);
        }
    }

    private static int getSize(char cha) {
        if (cha == 'A') {
            return 1;
        } else if (cha == 'B') {
            return 2;
        } else {
            return 8;
        }
    }

    private static void print(Stack[] stacks) {
        for (int i = 0; i < stacks.length; i++) {
            System.out.println(stacks[i].toString().replaceAll("[ \\[\\],]", ""));
        }
    }
}
