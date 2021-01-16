package time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 時間格式化工具
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月15日 下午9:07:35
 */
public class DateFormatUtil {
    private static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
    
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("format: " + format(date));
        System.out.println("formatZone8: " + formatZone8(date));
        // 東八區2021-01-16 10，0區2點，東二區0點
        System.out.println("formatTimeZone: " + formatTimeZone(date,
                TimeZoneUtil.generateTimeZone("UTC-9:00")));
        System.out.println("formatFull: " + formatFull(date));
        System.out.println("formatFullZone8: " + formatFullZone8(date));
        System.out.println("formatFullTimeZone: " + formatFullTimeZone(date,
                TimeZoneUtil.generateTimeZone("UTC-2:00")));
        // parse
        Date date1 = parse("2021-01-16 11:30:20");
        Date date2 = parseZone8("2021-01-16 11:30:20");
        Date date3 = parseTimeZone("2021-01-16 04:30:20",
                TimeZoneUtil.generateTimeZone("UTC+1:00"));
        System.out.println(formatZone8(date1));
        System.out.println(formatZone8(date2));
        System.out.println(formatZone8(date3));
        // convertToUTC
        String srcDateStr = "2021-01-16 11:30:20";
        TimeZone timezone = TimeZoneUtil.generateTimeZone("UTC-9:00");
        String utcDateStr = convertToUTC(srcDateStr, timezone);
        System.out.println("convertToUTC: " + utcDateStr);
        System.out.println("convertUTCToTimeZone: " + convertUTCToTimeZone(utcDateStr,
                TimeZoneUtil.generateTimeZone("UTC-9:00")));
        System.out.println("convertUTCToTimeZone: " + convertUTCToTimeZone(utcDateStr,
                TimeZoneUtil.generateTimeZone("UTC+8:00")));
        // convertTimeZone
        srcDateStr = "2021-01-16 11:30:20";
        TimeZone srcTz = TimeZoneUtil.generateTimeZone("UTC+9:00");
        TimeZone destTz = TimeZoneUtil.generateTimeZone("UTC-3:00");
        String destDateStr = convertTimeZone(srcDateStr, srcTz, destTz);
        System.out.println("convertTimeZone: " + destDateStr);
    }
    
    /**
     * 源時區時間串轉換成對應時區時間年月日時分秒字符串<br/>
     * 這個用處，就不依賴服務器的默認時區，因為沒有直接new Date()對象<br/>
     * 如果客户端傳來時間字符串，和它的時區，在服務器就可以隨意轉換成任意時區處理
     * 
     * @param srcDateStr 原時區日期時間串，格式如：yyyy-MM-dd HH:mm:ss
     */
    public static String convertTimeZone(String srcDateStr, TimeZone srcTimezone,
            TimeZone destTimezone) {
        if (null == srcDateStr || null == srcTimezone) {
            return null;
        }
        if (null == destTimezone) {
            return srcDateStr;
        }
        Date date = parseTimeZone(srcDateStr, srcTimezone);
        return formatTimeZone(date, destTimezone);
    }
    
    /**
     * 轉換成UTC標準時間年月日時分秒字符串
     * 
     * @param srcDateStr 原日期時間串，格式如：yyyy-MM-dd HH:mm:ss
     */
    public static String convertToUTC(String srcDateStr, TimeZone timezone) {
        if (null == srcDateStr) {
            return null;
        }
        if (null == timezone) {
            return srcDateStr;
        }
        Date date = parseTimeZone(srcDateStr, timezone);
        return formatTimeZone(date, TimeZoneUtil.generateTimeZone("UTC+00:00"));
    }
    
    /**
     * UTC時間串轉換成對應時區時間年月日時分秒字符串
     * 
     * @param utcDateStr 原UTC日期時間串，格式如：yyyy-MM-dd HH:mm:ss
     */
    public static String convertUTCToTimeZone(String utcDateStr, TimeZone timezone) {
        if (null == utcDateStr) {
            return null;
        }
        if (null == timezone) {
            return utcDateStr;
        }
        Date date = parseTimeZone(utcDateStr, TimeZoneUtil.generateTimeZone("UTC+00:00"));
        return formatTimeZone(date, timezone);
    }
    
    /**
     * 默认格式化yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        return new SimpleDateFormat(FORMAT_DEFAULT).format(date);
    }
    
    /**
     * 在東八區，默认格式化yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String formatZone8(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT);
        sdf.setTimeZone(TimeZoneUtil.generateTimeZone("UTC+8:00"));
        return sdf.format(date);
    }
    
    /**
     * 在指定時區，默认格式化yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String formatTimeZone(Date date, TimeZone timezone) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT);
        if (null != timezone) {
            sdf.setTimeZone(timezone);
        }
        return sdf.format(date);
    }
    
    /**
     * 轉成日期對象
     * 
     * @param dateStr 格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parse(String dateStr) {
        try {
            return new SimpleDateFormat(FORMAT_DEFAULT).parse(dateStr);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 在東八區，轉成日期對象
     * 
     * @param dateStr 格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseZone8(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT);
            sdf.setTimeZone(TimeZoneUtil.generateTimeZone("UTC+8:00"));
            return sdf.parse(dateStr);
        } catch (Exception e) {
        }
        return null;
    }
    
    /**
     * 在指定時區，轉成日期對象
     * 
     * @param dateStr 格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseTimeZone(String dateStr, TimeZone timezone) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT);
            if (null != timezone) {
                sdf.setTimeZone(timezone);
            }
            return sdf.parse(dateStr);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 直到毫秒的格式化yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @param date
     * @return
     */
    public static String formatFull(Date date) {
        return new SimpleDateFormat(FORMAT_FULL).format(date);
    }

    /**
     * 在東八區，直到毫秒的格式化yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @param date
     * @return
     */
    public static String formatFullZone8(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_FULL);
        sdf.setTimeZone(TimeZoneUtil.generateTimeZone("UTC+8:00"));
        return sdf.format(date);
    }
    

    /**
     * 在指定時區，直到毫秒的格式化yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @param date
     * @return
     */
    public static String formatFullTimeZone(Date date, TimeZone timezone) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_FULL);
        if (null != timezone) {
            sdf.setTimeZone(timezone);
        }
        return sdf.format(date);
    }
}
