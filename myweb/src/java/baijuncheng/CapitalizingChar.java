package baijuncheng;

import java.util.Scanner;

public class CapitalizingChar {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            if (str.matches("[A-Z]{1}")) {
                System.out.println(str);
            } else if (str.matches("[a-z]{1}")) {
                System.out.println(str.toUpperCase());
            } else {
                str = null;
                System.out.println(str);
            }
        }
    }
}
