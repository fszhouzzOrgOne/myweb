package nowcoder;

import java.util.Scanner;

/**
 * 不知道錯在哪裏
 * 
 * 
 * @author fszhouzz@qq.com
 * @time 2020年7月26日 下午7:06:43
 */
public class FallingBouncingBall {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            int time = 10;
            int high0 = in.nextInt();
            double total = getTotalDistance(high0, time);
            double high = getBounceHigh(high0, time);
            System.out.println(total + "," + high);
        }
        in.close();
    }

    private static double getFallHigh(int high0, int time) {
        double res = high0 * Math.pow(0.5, time - 1);
        return res;
    }

    private static double getBounceHigh(int high0, int time) {
        double res = high0 * Math.pow(0.5, time);
        return res;
    }

    private static double getTotalDistance(int high0, int time) {
        double res = 0;
        for (int i = 1; i <= 10; i++) {
            double fall = getFallHigh(high0, i);
            res += fall;
        }
        for (int i = 1; i <= 9; i++) {
            double bounce = getBounceHigh(high0, i);
            res += bounce;
        }
        return res;
    }

}
