package time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cangjie.java.util.IOUtils;

/**
 * 近三千年的所有日期，加干支
 */
public class DateTest {

    // CE2017-12-12 癸酉
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
        for (int i = (366 * -1100); i <= (366 * 2100); i++) {
            cal.add(Calendar.DATE, i);
            dayList.add((i < 0 ? "BCE" : "CE") + sdf.format(cal.getTime()));

            cal.setTime(begin);
        }

        List<String> gzs = new ArrayList<String>();
        for (String gz : ganzhi) {
            gzs.add(gz);
        }

        // 帶上每天的干支
        // CE2017-12-12 癸酉
        System.out.println(dayList.indexOf("CE2017-12-12"));
        System.out.println(gzs.indexOf("癸酉"));
        System.out.println((dayList.indexOf("CE2017-12-12")) % gzs.size());
        int add = gzs.indexOf("癸酉") - (dayList.indexOf("CE2017-12-12")) % gzs.size();
        if (add < 0) {
            add += gzs.size();
        }
        System.out.println("add=" + add);
        List<String> dayList2 = new ArrayList<String>();
        for (int i = 0; i < dayList.size(); i++) {
            String dayGz = dayList.get(i) + "\t" + gzs.get((i + add) % gzs.size());
            dayList2.add(dayGz);
        }

        String destFile = mbsBaseDir + "days-150002500.txt";
        IOUtils.writeFile(destFile, dayList2);
    }

}
