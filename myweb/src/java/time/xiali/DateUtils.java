package time.xiali;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        System.out.println(datesMoreAfterBegin(sdf.parse("20170131 18:00:00"), new Date()));
        System.out.println(daysMoreAfterBegin(sdf.parse("20170131 18:00:00"), new Date()));
    }

    /** 兩個日期相隔天數，日期不同就算一天 */
    public static int datesMoreAfterBegin(Date begin, Date end) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date beginDate = sdf.parse(sdf.format(begin)); // 格式yyyyMMdd
        Date endDate = sdf.parse(sdf.format(end));
        return ((Long) ((endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24)))
                .intValue();
    }
    
    /** 兩個日期相差天數，滿一天才算一天 */
    public static int daysMoreAfterBegin(Date begin, Date end) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date beginDate = sdf.parse(sdf.format(begin)); // 格式yyyyMMdd
        Date endDate = sdf.parse(sdf.format(end));
        return ((Long) ((endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24)))
                .intValue();
    }

}
