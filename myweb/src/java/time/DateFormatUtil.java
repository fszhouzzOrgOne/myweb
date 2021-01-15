package time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 時間格式化工具
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月15日 下午9:07:35
 */
public class DateFormatUtil {
    private static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
    
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
     * 直到毫秒的格式化yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @param date
     * @return
     */
    public static String formatFull(Date date) {
        return new SimpleDateFormat(FORMAT_FULL).format(date);
    }
}
