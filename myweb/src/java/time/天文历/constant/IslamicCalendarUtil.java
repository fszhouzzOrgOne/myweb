package time.天文历.constant;

import java.text.SimpleDateFormat;
import java.util.Date;

import time.DateGanzhiTest;
import time.天文历.LunarDate;

/**
 * 回曆
 */
public class IslamicCalendarUtil {
    static SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 計算回曆
     * 
     * @param dayRL
     *            2000.0起算儒略日
     * @param date
     *            只用於塡返回結果
     */
    public static void getHuiLi(int dayRL, LunarDate date) {
        String huili = getHuiLiByJD(dayRL);
        String[] parts = huili.split(" ");
        date.sethYear(Integer.parseInt(parts[0]));
        date.sethMonth(Integer.parseInt(parts[1]));
        date.sethDay(Integer.parseInt(parts[2]));
    }

    /**
     * 按儒略日算出回曆年月日字符串
     * 
     * @param jd
     *            2000.0起算儒略日
     * 
     * @return 回曆年月日，格式如“(-)1420 09 24”
     */
    public static String getHuiLiByJD(int jd) {
        // 以下算法使用Excel测试得到,测试时主要关心年临界与月临界
        int z, y, m, d;
        d = jd + 503105;
        z = (int) Math.floor(d / 10631); // 10631为一周期(30年)
        d -= z * 10631;
        y = (int) Math.floor((d + 0.5) / 354.366); // 加0.5的作用是保证闰年正确(一周中的闰年是第2,5,7,10,13,16,18,21,24,26,29年)
        d -= (int) Math.floor(y * 354.366 + 0.5);
        m = (int) Math.floor((d + 0.11) / 29.51); // 分子加0.11,分母加0.01的作用是第354或355天的的月分保持为12月(m=11)
        d -= (int) Math.floor(m * 29.5 + 0.5);
        int year = z * 30 + y + 1;
        int month = m + 1;
        int day = d + 1;
        return fomatHuili2Str(year, month, day);
    }

    /**
     * 按日期對象算出回曆年月日字符串
     * 
     * @param date
     * @return 回曆年月日，格式如“(-)1420 09 24”
     */
    public static String getHuiliByDate(Date date) {
        // 公元2000年1月1日，設儒略日相對值爲0，回曆1420-9-24
        // 實際值參見Common.J2000=2451545
        int theJd = 0;
        String res = null;
        try {
            Date date0 = sdf_yyyyMMdd.parse("2000-01-01");
            Long days = DateGanzhiTest.daysBetween(date0, date);
            theJd = days.intValue();
            res = getHuiLiByJD(theJd);
        } catch (Exception e) {
        }
        return res;
    }

    /**
     * 格式化回曆年月日
     * 
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     * @return 回曆年月日，格式如“(-)1420 09 24”
     */
    public static String fomatHuili2Str(int year, int month, int day) {
        boolean beforeEra = (year < 0);
        String yearStr = Math.abs(year) + "";
        while (yearStr.length() < 4) {
            yearStr = "0" + yearStr;
        }
        if (beforeEra) {
            yearStr = "-" + yearStr;
        }
        return yearStr + " " + (month < 10 ? "0" : "") + month + " " + (day < 10 ? "0" : "") + day;
    }

    public static void main(String[] args) throws Exception {
        String dateStr = "0621-07-15";
        System.out.println(dateStr + ": " + getHuiliByDate(sdf_yyyyMMdd.parse(dateStr)));

        System.out.println(getHuiliByDate(new Date()));
    }
}