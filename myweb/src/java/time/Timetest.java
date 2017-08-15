package time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Timetest {

    private static String[] shiShens = { "子", "丑", "丑", "寅", "寅", "卯", "卯",
            "辰", "辰", "巳", "巳", "午", "午", "未", "未", "申", "申", "酉", "酉", "戌",
            "戌", "亥", "亥", "子" };
    private static String[] kes = { "初刻", "二刻", "三刻", "四刻", "五刻", "六刻", "七刻",
            "末刻" };

    public static void main(String[] args) {
        System.out.println(getShiShen(new Date()));
    }

    /**
     * 得到時辰
     */
    public static List<String> getShiShen(Date date) {
        List<String> result = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String shiShen = shiShens[hour];
        result.add(shiShen + "時"); // 1
        String couZheng = (hour % 2 == 1) ? "初" : "正";
        result.add(shiShen + couZheng); // 2

        int minute = cal.get(Calendar.MINUTE);
        String ke1 = kes[minute / 15];
        result.add(shiShen + couZheng + ke1); // 3

        // 4
        if ("初".equals(couZheng)) {
            result.add(shiShen + "時" + ke1);
        } else {
            result.add(shiShen + "時" + kes[minute / 15 + 4]);
        }
        return result;
    }
}
