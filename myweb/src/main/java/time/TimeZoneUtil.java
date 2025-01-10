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
        System.out.println("從東八區，轉到西五區：" + DateFormatUtil
                .format(convertTimeZone(date, generateTimeZone("UTC+8:00"),
                        generateTimeZone("UTC-5:00"))));
        System.out.println("從東八區，轉到東十三區：" + DateFormatUtil
                .format(convertTimeZone(date, generateTimeZone("UTC+8:00"),
                        generateTimeZone("UTC+13:00"))));
    }

    /**
     * 從一個時區，轉到另一個時區<br/>
     * 也是計算時間戳的差，用默認的Date表示<br/>
     * Date打印出來，是有默認時區的，這樣轉換的用處，就是：<br/>
     * 服務器、數據庫，默認時區不變，客户端時區可以不同，<br/>
     * 客户端傳過來的時間，可以用它的時區，統一轉換成服務器的時區時間，統一處理<br/>
     * 方便用於客户端傳來時間戳，服務器用Date對象接收的情況
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
     * 把時間轉換成UTC標準時間，返回一個Date對象：<br/>
     * Date對象的時間戳變化了，減去了相對於UTC時區偏移量<br/>
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
        // 相對於UTC時區偏移量
        long zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 夏令時偏移量the daylight saving offset in milliseconds.
        long dlsOffset = cal.get(Calendar.DST_OFFSET);
        long millisUtc = (srcTime - zoneOffset - dlsOffset);
        return new Date(millisUtc);
    }

    /**
     * 假設utcDate中是UTC標準時間，求對應時區的時間<br/>
     * utcDate的時間戳變化了，加上了時區相對於UTC時區偏移量<br/>
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
        // 相對於UTC時區偏移量
        long zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 夏令時偏移量the daylight saving offset in milliseconds.
        long dlsOffset = cal.get(Calendar.DST_OFFSET);
        long millisZone = (srcTime + zoneOffset + dlsOffset);
        return new Date(millisZone);
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
