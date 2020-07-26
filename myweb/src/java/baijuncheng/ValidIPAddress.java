package baijuncheng;

import java.util.Scanner;

public class ValidIPAddress {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            boolean valid = checkValidIp(str);
            System.out.println(valid);
        }
    }

    private static boolean checkValidIp(String str) {
        if (!str.matches("[0-9]{1,3}(\\.[0-9]{1,3}){3}")) {
            return false;
        }
        String[] parts = str.split("\\.");
        for (String part : parts) {
            int one = Integer.parseInt(part);
            if (one < 0 || one > 255) {
                return false;
            }
        }
        return true;
    }
}
