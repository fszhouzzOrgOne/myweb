package time.天文历;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import time.DateGanzhiTest;

/**
 * JulianDate.java、 LunarCalendar.java、 LunarConstant.java、 LunarDate.java<br/>
 * 四個類來自：https://download.csdn.net/download/wangpeng047/7755661<br/>
 */
public class Test {
    static SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws Exception {
        String dateStr = "2018-06-04";
        Date date = sdf_yyyyMMdd.parse(dateStr);

        Date begin = sdf_yyyyMMdd.parse("0001-01-01");
        Calendar begincal = Calendar.getInstance();
        begincal.setTime(begin);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String eraPreffix = (begincal.after(cal)) ? "BCE" : "CE";

        LunarCalendar lc = new LunarCalendar(date);
        
        System.out.println(lc.getNianHao());
        System.out.println(eraPreffix + cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月"
                + cal.get(Calendar.DATE) + "日");
        System.out.println(lc.getDateString() + "," + lc.getGanZhiDateString()
                + DateGanzhiTest.getHourGanzhi(eraPreffix + sdf_yyyyMMdd.format(cal.getTime()), 0) + "時");
        System.out.println("回曆" + lc.getLunarDate().gethYear() + "年" + lc.getLunarDate().gethMonth() + "月"
                + lc.getLunarDate().gethDay() + "日");
        
        System.out.println(lc.getLunarDate().getImpHappyName());
        System.out.println(lc.getLunarDate().getImpName());
        System.out.println(lc.getLunarDate().getAllName());
        
        for (LunarDate ld : new LunarCalendar().getMonthLunarDates()) {
            System.out.println(ld);
        }
    }
}
