package time;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 時區時間的轉換
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月15日 下午8:37:37
 */
public class TimeZoneUtil {
    public static void main(String[] args) {
        String pattern = "GMT[+-][01]?[0-9]{1}:[0-9]{2}";
        System.out.println("GMT-08:30".matches(pattern));
        System.out.println("GMT-12:00".matches(pattern));

        Date date = DateFormatUtil.parse("2021-01-15 11:30:20");
        System.out.println(DateFormatUtil.formatFull(date));
        Date dateUtc = convertToUTC(date, generateTimeZone("UTC+8:00"));
        System.out.println(DateFormatUtil.formatFull(dateUtc));
        Date dateOrg = convertUTCToTimeZone(dateUtc,
                generateTimeZone("UTC+8:00"));
        System.out.println(DateFormatUtil.formatFull(dateOrg));
        System.out.println("從東八區，轉到西三區：" + DateFormatUtil
                .format(convertTimeZone(date, generateTimeZone("UTC+8:00"),
                        generateTimeZone("UTC-3:00"))));
        System.out.println("從東八區，轉到東十三區：" + DateFormatUtil
                .format(convertTimeZone(date, generateTimeZone("UTC+8:00"),
                        generateTimeZone("UTC+13:00"))));
    }

    /**
     * 從一個時區，轉到另一個時區<br/>
     * 也是計算時間戳的差，用默認的Date表示
     * 
     * @param srcDate
     * @param srcTimezone
     * @param destTimezone
     * @return
     */
    public static Date convertTimeZone(Date srcDate, TimeZone srcTimezone,
            TimeZone destTimezone) {
        if (null == srcDate || null == srcTimezone) {
            return null;
        }
        if (null == destTimezone) {
            return srcDate;
        }
        Date dateUtc = convertToUTC(srcDate, srcTimezone);
        return convertUTCToTimeZone(dateUtc, destTimezone);
    }

    /**
     * 把時間轉換成UTC標準時間，這裡說的是指：<br/>
     * 把srcDate時間，關注它的年月日打印結果，當成是這個時區的時間，<br/>
     * 然後求UTC標準時間，也關注它的年月日。<br/>
     * 效果就是：打印出來後，年月日時分秒變化了
     * 
     * @param srcDate
     * @param timezone
     * @return
     */
    public static Date convertToUTC(Date srcDate, TimeZone timezone) {
        if (null == srcDate) {
            return null;
        }
        if (null == timezone) {
            return srcDate;
        }
        // 得到時間戳，這個值是與時區無關的
        long srcTime = srcDate.getTime();
        // 當前時區時間和UTC標準時間偏移量計算
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(timezone);
        cal.setTimeInMillis(srcTime);
        // 相對於UTC時區偏移量
        long zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 夏令時偏移量the daylight saving offset in milliseconds.
        long dlsOffset = cal.get(Calendar.DST_OFFSET);
        long millisUtc = (cal.getTimeInMillis() - zoneOffset - dlsOffset);
        cal.setTimeZone(generateTimeZone("GMT+00:00"));
        cal.setTimeInMillis(millisUtc);
        return cal.getTime();
    }

    /**
     * 假設utcDate中是UTC標準時間，求對應時區的時間<br/>
     * 關注它的打印結果，返回值打印出來的時間，就是當UTC標準時間是utcDate時，<br/>
     * 對應時區的時間打印出來是什麼<br/>
     * 
     * @param utcDate
     * @param timezone
     * @return
     */
    public static Date convertUTCToTimeZone(Date utcDate, TimeZone timezone) {
        if (null == utcDate) {
            return null;
        }
        if (null == timezone) {
            return utcDate;
        }
        // 得到時間戳，這個值是與時區無關的
        long srcTime = utcDate.getTime();
        // 當前時區時間和UTC標準時間偏移量計算
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(timezone);
        cal.setTimeInMillis(srcTime);
        // 相對於UTC時區偏移量
        long zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 夏令時偏移量the daylight saving offset in milliseconds.
        long dlsOffset = cal.get(Calendar.DST_OFFSET);
        long millisUtc = (cal.getTimeInMillis() + zoneOffset + dlsOffset);
        cal.setTimeInMillis(millisUtc);
        return cal.getTime();
    }

    /**
     * 用id生成時區對象
     * 
     * @param timezone
     *            時區id，格式如：GMT-8:00、GMT-08:00、UTC-8:00
     * @return
     */
    public static TimeZone generateTimeZone(String timezone) {
        if (null == timezone) {
            return null;
        }
        String tz = timezone.toUpperCase().replaceAll("UTC", "GMT");
        String pattern = "GMT[+-][01]?[0-9]{1}:[0-9]{2}";
        if (!tz.matches(pattern)) {
            throw new IllegalArgumentException(
                    "Invalid format of timezone: " + timezone);
        }
        return TimeZone.getTimeZone(tz);
    }
}
