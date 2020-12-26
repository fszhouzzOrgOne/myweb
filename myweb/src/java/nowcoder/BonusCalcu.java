package nowcoder;

import java.util.Scanner;

public class BonusCalcu {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int interest = in.nextInt();
            int bonus = getBonus(interest);
            System.out.println(bonus);
        }
    }

    private static int getBonus(int interest) {
        if (interest <= 0) {
            return 0;
        }
        int bonus = 0;
        boolean isBigger = false;
        if (interest > 1000000) {
            bonus += (interest - 1000000) * 0.01;
            isBigger = true;
        }
        if (isBigger) {
            bonus += (1000000 - 600000) * 0.015;
        } else if (interest > 600000) {
            bonus += (interest - 600000) * 0.015;
            isBigger = true;
        }
        if (isBigger) {
            bonus += (600000 - 400000) * 0.03;
        } else if (interest > 400000) {
            bonus += (interest - 400000) * 0.03;
            isBigger = true;
        }
        if (isBigger) {
            bonus += (400000 - 200000) * 0.05;
        } else if (interest > 200000) {
            bonus += (interest - 200000) * 0.05;
            isBigger = true;
        }
        if (isBigger) {
            bonus += (200000 - 100000) * 0.075;
        } else if (interest > 100000) {
            bonus += (interest - 100000) * 0.075;
            isBigger = true;
        }
        if (isBigger) {
            bonus += 100000 * 0.1;
        } else if (interest > 0) {
            bonus += interest * 0.1;
        }
        return bonus;
    }
}
