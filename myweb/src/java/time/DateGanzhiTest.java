package time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cangjie.java.util.IOUtils;

/**
 * 近三千年的所有日期，加干支<br/>
 */
public class DateGanzhiTest {

    static SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 每日子時干支<br/>
     * CE1990-02-03 甲子 丙子 戊子 庚子 壬子
     */
    private static final String[] ganzhiDayStart = { "甲子", "丙子", "戊子", "庚子", "壬子" };

    // CE2017-12-12 癸酉日
    private static final String[] ganzhi = { "甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午", "辛未", "壬申", "癸酉", "甲戌", "乙亥",
            "丙子", "丁丑", "戊寅", "己卯", "庚辰", "辛巳", "壬午", "癸未", "甲申", "乙酉", "丙戌", "丁亥", "戊子", "己丑", "庚寅", "辛卯", "壬辰", "癸巳",
            "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑", "壬寅", "癸卯", "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥",
            "壬子", "癸丑", "甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥" };

    public static String mbsBaseDir = "src\\java\\time\\";

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date begin = sdf.parse("0001-01-01");
        cal.setTime(begin);
        // 所有日期
        List<String> dayList = new ArrayList<String>();
        for (int i = (366 * -730); i <= (366 * 2050); i++) {
            cal.add(Calendar.DATE, i);
            dayList.add((i < 0 ? "BCE" : "CE") + sdf.format(cal.getTime()));

            cal.setTime(begin);
        }

        List<String> dayList2 = new ArrayList<String>();
        for (int i = 0; i < dayList.size(); i++) {
            String dayGz = dayList.get(i);
            dayGz += "    " + getDateGanzhi(dayList.get(i)) + "日" + getHourGanzhi(dayList.get(i), 0) + "時";
            dayList2.add(dayGz);
        }

        String destFile = mbsBaseDir + "days-150002500.txt";
        IOUtils.writeFile(destFile, dayList2);

        System.out.println("getGanzhiByOffset: " + getGanzhiByOffset("甲子", -1));
        System.out.println("getGanzhiByOffset: " + getGanzhiByOffset("甲子", 0));
        System.out.println("getGanzhiByOffset: " + getGanzhiByOffset("甲子", 1));
        System.out.println("getGanzhiByOffset: " + getGanzhiByOffset("甲子", 62));

        System.out.println("getDateGanzhi: " + getDateGanzhi("BCE0001-12-31"));
        System.out.println("getDateGanzhi: " + getDateGanzhi("CE0001-01-01"));

        System.out.println(getHourGanzhi("CE1990-02-11", -2));
        System.out.println(getHourGanzhi("CE1990-02-11", -1));
        System.out.println(getHourGanzhi("CE1990-02-11", 0));
        System.out.println(getHourGanzhi("CE1990-02-11", 1));
        System.out.println(getHourGanzhi("CE1990-02-11", 2));
        System.out.println(getHourGanzhi("CE1990-02-11", 3));
        System.out.println(getHourGanzhi("CE1990-02-11", 4));
    }

    /**
     * 按偏移量得到前後第幾個干支
     * 
     * @param gzPar
     *            干支
     * @param offset
     *            偏移量
     * @return 前後第幾個干支
     */
    public static String getGanzhiByOffset(String gzPar, long offset) {
        List<String> gzs = new ArrayList<String>();
        for (String s : ganzhi) {
            gzs.add(s);
        }
        // 偏移量
        int offset2 = (int) (offset % gzs.size());
        if (offset2 < 0) {
            offset2 = gzs.size() + offset2;
        }
        // 目標下標
        int index = (gzs.indexOf(gzPar) + offset2) % gzs.size();
        return gzs.get(index);
    }

    /**
     * 得到日期的干支紀日
     * 
     * @param date
     *            日期格式，公元前如BCE2018-05-16，公元後如CE2018-05-16<br/>
     *            BCE、CE不傳，默認CE
     * @return
     * @throws Exception
     */
    public static String getDateGanzhi(String date) throws Exception {
        // CE2017-12-12 癸酉
        long days = daysBetween(getCalendarByStrDate("CE2017-12-12").getTime(), getCalendarByStrDate(date).getTime());
        return getGanzhiByOffset("癸酉", days);
    }

    /**
     * 得到時辰的干支
     * 
     * @param date
     *            日期格式，公元前如BCE2018-05-16，公元後如CE2018-05-16<br/>
     *            BCE、CE不傳，默認CE
     * @param hour
     *            小時，1算丑時，3算寅時……23、24算第二天子時
     * @return
     * @throws Exception
     */
    public static String getHourGanzhi(String date, Integer hour) throws Exception {
        // CE1990-02-03 甲子
        long days = daysBetween(getCalendarByStrDate("CE1990-02-03").getTime(), getCalendarByStrDate(date).getTime());

        List<String> gzHours = new ArrayList<String>();
        for (String gz : ganzhiDayStart) {
            gzHours.add(gz);
        }
        // 偏移量
        int offset = (int) (days % gzHours.size());
        if (offset < 0) {
            offset = gzHours.size() + offset;
        }
        // 目標下標
        int index = (gzHours.indexOf("甲子") + offset) % gzHours.size();
        String res = gzHours.get(index);
        if (null != hour) {
            List<String> gzs = new ArrayList<String>();
            for (String gz : ganzhi) {
                gzs.add(gz);
            }
            int evenHour = hour;
            if (hour % 2 != 0) {
                evenHour += 1;
            }
            return getGanzhiByOffset(res, evenHour / 2);
        }
        return res;
    }

    /**
     * 兩日期字符串得到日期對象
     * 
     * @param date
     *            日期格式，公元前如BCE2018-05-16，公元後如CE2018-05-16<br/>
     *            BCE、CE不傳，默認CE
     * @return
     * @throws Exception
     */
    public static Calendar getCalendarByStrDate(String date) throws Exception {
        String[] dates = date.split("-");
        String year = dates[0].replaceAll("BCE", "-").replaceAll("CE", "");
        Calendar cal = Calendar.getInstance();
        // 如果是公元前，加一年，不然公元前第1年沒有算入
        boolean bce = year.startsWith("-");
        cal.set(Calendar.YEAR, Integer.parseInt(year) + (bce ? 1 : 0));
        cal.set(Calendar.MONTH, Integer.parseInt(dates[1]) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(dates[2]));
        cal.set(Calendar.HOUR_OF_DAY, 0); // HOUR_OF_DAY 24-hour clock.
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 兩日期之間的天數，不計時分秒
     * 
     * @param date1
     *            日期一
     * @param date2
     *            日期二
     * @return
     * @throws Exception
     */
    public static long daysBetween(Date date1, Date date2) throws Exception {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal1.set(Calendar.HOUR, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.set(Calendar.HOUR, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        long millises = (cal2.getTimeInMillis() - cal1.getTimeInMillis());
        return millises / (24 * 3600 * 1000);
    }

}
