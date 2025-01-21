package testProject1;

import java.util.Scanner;

/**
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。〈br/>
 * 从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。〈br/>
 * A10;S20;W10;D30;X;A1A;B10A11;;A10; 结果（10， -10）〈br/>
 * A10;S20;W10;D30;X; 结果 20,-10〈br/>
 * 
 * @author Administrator
 * @time 2022年6月14日下午9:22:55
 */
public class HJ17MovingCoordinates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        str = str.replaceAll(" ", "");
        int[] co = new int[]{0, 0};
        if (str.length() < 1) {
            printCoordinate(co);
            return;
        }
        String[] steps = str.split(";");
        for (String one : steps) {
            moveToCoordinate(co, one);
        }
        printCoordinate(co);
    }

    // 移到一个坐标
    private static void moveToCoordinate(int[] co, String one) {
        if (null == one || !one.matches("[ASDW]{1}[0-9]+")) {
            return;
        }
        int num = Integer.parseInt(one.substring(1));
        if (one.startsWith("A")) {
            co[0] += -num;
        } else if (one.startsWith("D")) {
            co[0] += num;
        } else if (one.startsWith("W")) {
            co[1] += num;
        } else if (one.startsWith("S")) {
            co[1] += -num;
        }
    }

    private static void printCoordinate(int[] co) {
        System.out.println(co[0] + "," + co[1]);
    }
}
