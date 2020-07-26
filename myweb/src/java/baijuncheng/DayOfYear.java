package baijuncheng;

import java.util.Calendar;
import java.util.Scanner;

public class DayOfYear {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String dateStr = in.nextLine();
            String[] parts = dateStr.split(" +");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            int dayNo = getDayOfYear(year, month, day);
            System.out.println(dayNo);
        }
    }

    private static int getDayOfYear(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, day);
        return cal.get(Calendar.DAY_OF_YEAR);
    }
}
