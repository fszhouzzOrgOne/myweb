package time.天文历;

import java.text.SimpleDateFormat;

/**
 * JulianDate.java、 LunarCalendar.java、 LunarConstant.java、 LunarDate.java<br/>
 * 四個類來自：https://download.csdn.net/download/wangpeng047/7755661<br/>
 */
public class Test {
    static SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws Exception {
        String dateStr = "0000-01-01";
        LunarCalendar lc = new LunarCalendar(sdf_yyyyMMdd.parse(dateStr));
        System.out.println(lc.getYear() + " " + lc.getGanZhiDateString());
        System.out.println(lc.getNianHao());
    }
}
