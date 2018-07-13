package time.天文历.constant;

/**
 * 節氣工具類
 */
public class SolarTermsUtil {

    /** 節氣名稱 */
    private static final String[] termNames = { "冬至", "小寒", "大寒", "立春", "雨水",
            "驚蟄", "春分", "淸明", "穀雨", "立夏", "小滿", "芒種", "夏至", "小暑", "大暑", "立秋",
            "處暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪" };

    /** 简化字 */
    private static final String[] termNamesSim = { "冬至", "小寒", "大寒", "立春",
            "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑",
            "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪" };

    public static String[] getTermNames() {
        return termNames;
    }

}
