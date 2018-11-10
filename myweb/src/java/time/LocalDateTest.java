package time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.parse("19631118",
                DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate date2 = LocalDate.parse("20181110",
                DateTimeFormatter.ofPattern("yyyyMMdd"));
        long lon1 = date.toEpochDay();
        long lon2 = date2.toEpochDay();

        System.out.println(lon2 - lon1);
    }
}
