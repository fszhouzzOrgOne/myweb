package time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cangjie.java.util.IOUtils;

public class JulianGregCalTimetest {
    static SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    public static int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    public static List<String> _400YearDays = null;

    public static String mbsBaseDir = "src\\java\\time\\";

    static {
        // 格列曆400一循环，所以用個400的所有日期
        _400YearDays = new ArrayList<String>();
        for (int i = 2001; i <= 2400; i++) {
            boolean isleap = isLeapGregCal(i);
            for (int m = 1; m <= 12; m++) {
                String preffix = "";
                if (m < 10) {
                    preffix = "0";
                }
                int monthEnd = monthDays[m - 1];
                if (isleap && m == 2) {
                    monthEnd += 1;
                }
                for (int d = 1; d <= monthEnd; d++) {
                    String preffixDay = "";
                    if (d < 10) {
                        preffixDay = "0";
                    }
                    _400YearDays.add(i + "-" + preffix + m + "-" + preffixDay + d);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // getJulianAndGregCal();
        System.out.println("-1" + isLeapGregCal(-1));
        System.out.println("-401" + isLeapGregCal(-401));
        System.out.println("-101" + isLeapGregCal(-101));
        System.out.println("-3201" + isLeapGregCal(-101));
        System.out.println("4" + isLeapGregCal(4));
        System.out.println(daysBetweenGregCal("CE0722-01-01", "BCE0722-01-01"));

        Calendar cal1 = DateGanzhiTest.getCalendarByStrDate("BCE0722-01-09");
        Calendar cal2 = DateGanzhiTest.getCalendarByStrDate("CE0721-12-28");
        long days = DateGanzhiTest.daysBetween(cal1.getTime(), cal2.getTime());
        System.out.println("days: " + days);

        System.out.println("addDaysGregCal: " + addDaysGregCal("BCE0722-01-01", 527044));
        System.out.println("addDaysGregCal: " + addDaysGregCal("CE0722-01-01", -527044));
    }

    private static void getJulianAndGregCal() throws Exception {
        Date date111 = sdf_yyyyMMdd.parse("0001-01-01");
        Calendar cal111 = Calendar.getInstance();
        cal111.setTime(date111);

        int startYear = -1201;
        Date endDate = sdf_yyyyMMdd.parse("2400-12-31");
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);

        List<String> _2000YearDays = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            _2000YearDays.addAll(_400YearDays);
        }

        List<String> reses = new ArrayList<String>();
        int currGregYear = 2400;
        for (int i = _2000YearDays.size() - 1; i >= 0; i--) {
            String one = _2000YearDays.get(i);
            String[] parts = one.split("-");

            String res = "";
            if (cal111.after(cal)) {
                res += "B";
                // 到了BCE723了，不再要了
                if (cal.get(Calendar.YEAR) == -startYear) {
                    break;
                }
            }
            res += cal.get(Calendar.YEAR) + "年,";
            res += cal.get(Calendar.MONTH) + 1 + "月,";
            res += cal.get(Calendar.DATE) + "日,";
            res += (currGregYear < 0 ? "B" : "") + Math.abs(currGregYear) + "年," + parts[1] + "月," + parts[2] + "日";
            // 夏曆等不加了，加干支紀日，紀時
            String ceDateStr = "CE";
            if (cal111.after(cal)) {
                ceDateStr = "BCE";
            }
            ceDateStr += sdf_yyyyMMdd.format(cal.getTime());
            res += "," + DateGanzhiTest.getDateGanzhi(ceDateStr) + "日";
            res += "," + DateGanzhiTest.getHourGanzhi(ceDateStr, 0) + "時";

            if (!(cal111.before(cal) && cal.get(Calendar.YEAR) > 2100)) {
                reses.add(res);
            }

            cal.add(Calendar.DATE, -1);
            if ("0101".equals(parts[1] + parts[2])) {
                currGregYear--;
                if (currGregYear == 0) {
                    currGregYear--;
                }
            }
        }

        Collections.reverse(reses);
        String destFile = mbsBaseDir + "days-2000year.txt";
        IOUtils.writeFile(destFile, reses);
    }

    /**
     * 日期加減多少天，格列曆
     * 
     * @param date1
     *            日期串
     * @param days
     *            天數
     * @return
     * @throws Exception
     */
    public static String addDaysGregCal(String date1, int days) throws Exception {
        String ptn = "(BCE|CE)\\d{4}(-\\d{2}){2}";
        if (!date1.matches(ptn)) {
            throw new Exception("日期格式錯誤。");
        }
        String[] parts1 = date1.split("-");
        parts1[0] = parts1[0].replaceAll("CE", "").replaceAll("B", "-");
        int year1 = Integer.parseInt(parts1[0]), month1 = Integer.parseInt(parts1[1]),
                day1 = Integer.parseInt(parts1[2]);
        // 先看本年1月1日，到今天有多少天
        int days11 = 0;
        for (int i = 1; i < month1; i++) {
            days11 += monthDays[i - 1];
            if (i == 2 && isLeapGregCal(year1)) {
                days11++;
            }
        }
        days11--;
        days11 += day1;
        // 按1月1日，要加減多少天
        int daysParam = days - days11;
        int theYear = year1;
        int theYearDays = (isLeapGregCal(theYear) ? 366 : 365);
        // 如果是負數，先轉成正數
        // 最終，當年1月1日，加daysParam天後還在當年
        while (daysParam < 0) {
            theYear--;
            if (theYear == 0) {
                theYear--;
            }
            theYearDays = (isLeapGregCal(theYear) ? 366 : 365);
            daysParam += theYearDays;
        }
        // 正向走，一年一年加
        while (theYearDays <= daysParam) {
            daysParam -= theYearDays;
            theYear++;
            if (theYear == 0) {
                theYear++;
            }
            theYearDays = (isLeapGregCal(theYear) ? 366 : 365);
        }
        // 當年1月1日，加daysParam天，得到最終結果
        int theMonth = 1;
        int theDay = 0;
        for (int i = 1; i <= 12; i++) {
            theMonth = i;
            int monthDayCnt = monthDays[i - 1];
            if (i == 2 && isLeapGregCal(theYear)) {
                monthDayCnt++;
            }
            daysParam -= monthDayCnt;

            if (daysParam <= 0) {
                theDay = monthDayCnt + daysParam;
                break;
            }
        }
        return (theYear < 0 ? "BCE" : "CE") + Math.abs(theYear) + "-" + theMonth + "-" + theDay;
    }

    /**
     * 两个日期之间的天數，格列曆
     * 
     * @param date1
     *            日期字符串，格式如公元前BCEyyyy-MM-dd，元後CEyyyy-MM-dd
     * @param date2
     *            日期字符串，格式同上
     * @return
     * @throws Exception
     */
    public static long daysBetweenGregCal(String date1, String date2) throws Exception {
        String ptn = "(BCE|CE)\\d{4}(-\\d{2}){2}";
        if (!date1.matches(ptn) || !date2.matches(ptn)) {
            throw new Exception("日期格式錯誤。");
        }
        String[] parts1 = date1.split("-");
        String[] parts2 = date2.split("-");
        parts1[0] = parts1[0].replaceAll("CE", "").replaceAll("B", "-");
        parts2[0] = parts2[0].replaceAll("CE", "").replaceAll("B", "-");

        int year1 = Integer.parseInt(parts1[0]), month1 = Integer.parseInt(parts1[1]),
                day1 = Integer.parseInt(parts1[2]);
        int year2 = Integer.parseInt(parts2[0]), month2 = Integer.parseInt(parts2[1]),
                day2 = Integer.parseInt(parts2[2]);
        if ((year1 > year2) || (year1 == year2 && month1 > month2)
                || (year1 == year2 && month1 == month2 && day1 > day2)) {
            int year = year1, month = month1, day = day1;
            year1 = year2;
            month1 = month2;
            day1 = day2;
            year2 = year;
            month2 = month;
            day2 = day;
        }
        int days = 0;
        if (year1 == year2) {
            if (month1 == month2) {
                days = day2 - day1;
            } else {
                // 第一個月，到倒數第二月
                for (int i = month1; i < month2; i++) {
                    days += monthDays[i - 1];
                    if (i == 2 && isLeapGregCal(year1)) {
                        days++;
                    }
                }
                // 第一個月，減去day1
                days -= day1;
                // 最後一月，加上day2
                days += day2;
            }
        } else {
            // 第一年
            for (int i = month1; i <= 12; i++) {
                days += monthDays[i - 1];
                if (i == 2 && isLeapGregCal(year1)) {
                    days++;
                }
            }
            days -= day1;
            // 第二年到倒數第二年
            for (int i = year1 + 1; i < year2; i++) {
                if (i == 0) {
                    continue;
                }
                days += 365;
                if (isLeapGregCal(i))
                    days++;
            }
            // 最後一年
            for (int i = 1; i < month2; i++) {
                days += monthDays[i - 1];
                if (i == 2 && isLeapGregCal(year1)) {
                    days++;
                }
            }
            days += day2;
        }
        return days;
    }

    /**
     * 格列曆是否閏年
     * 
     * @param year
     * @return
     */
    public static boolean isLeapGregCal(int year) {
        boolean isleap = false;
        if (year > 0) {
            if ((year % 100 == 0 && year % 400 == 0 && year % 3200 != 0) || (year % 100 != 0 && year % 4 == 0)) {
                isleap = true;
            }
        } else if (year < 0) {
            year = Math.abs(year);
            if (year <= 100) {
                if (year % 4 == 1) {
                    isleap = true;
                }
            } else {
                if ((year % 100 == 1 && year % 400 == 1 && year % 3200 != 1) || (year % 100 != 1 && year % 4 == 1)) {
                    isleap = true;
                }
            }
        }
        return isleap;
    }

}
