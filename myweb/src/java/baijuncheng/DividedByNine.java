package baijuncheng;

import java.util.Scanner;

public class DividedByNine {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            int num = in.nextInt();
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num = num / 10;
            }
            System.out.println(sum % 9 == 0);
        }
    }
}
