package baijuncheng;

import java.util.Scanner;

public class RabbisBirth {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int monthCount = in.nextInt();
            int res = countRabbit(monthCount);
            System.out.println(res);
        }
    }

    private static int countRabbit(int monthCount) {
        if (monthCount == 1|| monthCount == 2) {
            return 1;
        } else {
            return countRabbit(monthCount - 1) + countRabbit(monthCount - 2);
        }
    }
}
