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
        getJulianAndGregCal();
    }

    private static void getJulianAndGregCal() throws Exception {
        Date date111 = sdf_yyyyMMdd.parse("0001-01-01");
        Calendar cal111 = Calendar.getInstance();
        cal111.setTime(date111);

        int startYear = -1200;
        int endYear = 2000;
        Date endDate = sdf_yyyyMMdd.parse(endYear + "-12-31");
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);

        List<String> _2000YearDays = new ArrayList<String>();
        // 2000年一直到前800年所有格列曆日
        for (int i = 0; i < 10; i++) {
            _2000YearDays.addAll(_400YearDays);
        }

        List<String> reses = new ArrayList<String>();
        int currGregYear = endYear;
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
            reses.add(res);

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
     * 格列曆是否閏年
     * 
     * @param year
     * @return
     */
    public static boolean isLeapGregCal(int year) {
        boolean isleap = false;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            isleap = true;
        } else {
            isleap = false;
        }
        return isleap;
    }

}
