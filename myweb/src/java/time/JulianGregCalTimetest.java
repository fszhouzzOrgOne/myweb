package time;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cangjie.java.util.IOUtils;

public class JulianGregCalTimetest {
    static SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    /** 年份數字格式化 */
    static NumberFormat nfYear = NumberFormat.getInstance();

    public static int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public static String mbsBaseDir = "src\\java\\time\\";

    static {
        nfYear.setGroupingUsed(false);
        nfYear.setMaximumIntegerDigits(4);
        nfYear.setMinimumIntegerDigits(4);
    }

    public static void main(String[] args) throws Exception {
        // getJulianAndGregCal();

        Date date = sdf_yyyyMMdd.parse("0001-10-04");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -551);
        String gregCal = getGregCalByDate(cal.getTime());
        System.out.println(sdf_yyyyMMdd.format(cal.getTime()));
        System.out.println("gregCal: " + gregCal);
        Date date2 = getDateByGregCal(gregCal);
        System.out.println(sdf_yyyyMMdd.format(date2));
    }

    /**
     * 生成儒略曆和格列曆對照表
     * 
     * @throws Exception
     */
    public static void getJulianAndGregCal() throws Exception {
        Date date111 = sdf_yyyyMMdd.parse("0001-01-01");
        Calendar cal111 = Calendar.getInstance();
        cal111.setTime(date111);

        Date endDate = sdf_yyyyMMdd.parse("2100-12-31");
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);

        String gregDate = null;

        List<String> reses = new ArrayList<String>();
        while (!(cal111.after(cal) && cal.get(Calendar.YEAR) >= 723)) {
            gregDate = getGregCalByDate(cal.getTime());
            String parts[] = gregDate.split("-");

            String res = "";
            if (cal111.after(cal)) {
                res += "B";
            }
            res += cal.get(Calendar.YEAR) + "年,";
            res += cal.get(Calendar.MONTH) + 1 + "月,";
            res += cal.get(Calendar.DATE) + "日,";
            res += parts[0].replaceAll("CE", "") + "年," + parts[1] + "月," + parts[2] + "日";
            // 夏曆等不加了，加干支紀日，紀時
            String ceDateStr = "CE";
            if (cal111.after(cal)) {
                ceDateStr = "BCE";
            }
            ceDateStr += sdf_yyyyMMdd.format(cal.getTime());
            res += "," + DateGanzhiTest.getDateGanzhi(ceDateStr) + "日";
            res += "," + DateGanzhiTest.getHourGanzhi(ceDateStr, 0) + "時";

            reses.add(res);
            cal.add(Calendar.DATE, -1);
        }

        Collections.reverse(reses);
        String destFile = mbsBaseDir + "days-2000year.txt";
        IOUtils.writeFile(destFile, reses);
    }

    /**
     * 按日期對象取格列曆日期
     * 
     * @param parse
     * @return
     * @throws Exception
     */
    public static String getGregCalByDate(Date date) throws Exception {
        Date date2 = sdf_yyyyMMdd.parse("1582-10-15");
        Long days = DateGanzhiTest.daysBetween(date2, date);
        return addDaysGregCal("CE1582-10-15", days.intValue());
    }

    /**
     * 按格列曆日期取日期對象
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getDateByGregCal(String date) throws Exception {
        String ptn = "(BCE|CE)\\d{4}(-\\d{2}){2}";
        if (!date.matches(ptn)) {
            throw new Exception("日期格式錯誤。");
        }
        Long days = daysBetweenGregCal("CE1582-10-15", date);
        Date date2 = sdf_yyyyMMdd.parse("1582-10-15");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date2);
        cal.add(Calendar.DATE, days.intValue());
        return cal.getTime();
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
        if (days == 0) {
            return date1;
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
        int daysParam = days + days11;
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
        int theDay = 1;
        if (daysParam != 0) {
            for (int i = 1; i <= 12; i++) {
                theMonth = i;
                int monthDayCnt = monthDays[i - 1];
                if (i == 2 && isLeapGregCal(theYear)) {
                    monthDayCnt++;
                }
                daysParam -= monthDayCnt;

                if (daysParam < 0) {
                    theDay = monthDayCnt + daysParam + 1;
                    break;
                } else if (daysParam == 0) {
                    // 下月一號
                    theDay = 1;
                    theMonth++;
                    break;
                }
            }
        }
        return formatGregCalYMD(theYear, theMonth, theDay);
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
        boolean switched = false;
        if ((year1 > year2) || (year1 == year2 && month1 > month2)
                || (year1 == year2 && month1 == month2 && day1 > day2)) {
            int year = year1, month = month1, day = day1;
            year1 = year2;
            month1 = month2;
            day1 = day2;
            year2 = year;
            month2 = month;
            day2 = day;

            switched = true;
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
        return days * (switched ? -1 : 1);
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

    /**
     * 格式化年月日
     * 
     * @param year
     * @param month
     * @param day
     * @return 加上BCE/CE
     */
    public static String formatGregCalYMD(int year, int month, int day) {
        return (year < 0 ? "BCE" : "CE") + nfYear.format(Math.abs(year)) + "-" + (month < 10 ? "0" : "") + month + "-"
                + (day < 10 ? "0" : "") + day;
    }

}
