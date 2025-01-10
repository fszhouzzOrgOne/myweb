package time.天文历.constant;

import java.util.regex.Pattern;

public class Common {
    
    /** 天干 */
    public static final String[] Gan = { "甲", "乙", "丙", "丁", "戊", "己", "庚",
            "辛", "壬", "癸" };
    /** 地支 */
    public static final String[] Zhi = { "子", "丑", "寅", "卯", "辰", "巳", "午",
            "未", "申", "酉", "戌", "亥" };
    /** 屬相 */
    public static final String[] ShX = { "鼠", "牛", "虎", "兔", "龙", "蛇", "马",
            "羊", "猴", "鸡", "狗", "猪" };
    /** 星座 */
    public static final String[] XiZ = { "摩羯", "水瓶", "双鱼", "白羊", "金牛", "双子",
            "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手" };
    /** 月相名称表 */
    public static final String[] yxmc = { "朔", "上弦", "望", "下弦" };

    /** 月名称,建寅 */
    public static final String[] ymc = { "十一", "十二", "正", "二", "三", "四", "五",
            "六", "七", "八", "九", "十" };
    /** 中文日期名稱 */
    public static final String[] rmc = { "初一", "初二", "初三", "初四", "初五", "初六",
            "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七",
            "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八",
            "廿九", "三十", "卅一" };

    /**
     * 取子串
     * 
     * @param str
     * @param beginIndex 開始下標
     * @return
     */
    public static String subString(String str, int beginIndex) {
        return subString(str, beginIndex, 0);
    }

    /**
     * 取子串
     * 
     * @param str
     * @param beginIndex 開始下標
     * @param endIndex 結束下標
     * @return
     */
    public static String subString(String str, int beginIndex, int endIndex) {
        String subStr = "";
        if (subStr != null) {
            int length = str.length();
            if (beginIndex >= 0 && beginIndex < length) {
                if (endIndex == 0) {
                    subStr = str.substring(beginIndex);
                } else {
                    if (beginIndex < endIndex && endIndex <= length) {
                        subStr = str.substring(beginIndex, endIndex);
                    }
                }
            }
        }
        return subStr;
    }

    //=================================天文常数=========================================
    /** 天文常數 每弧度的角秒数 */
    public static final double rad = 180 * 3600 / Math.PI;
    
    /** pi * 2 */
    public static final double pi2 = Math.PI * 2;

    //=================================deltat T计算=====================================
    /** deltat T计算-- TD - UT1 计算表 */
    private static final double[] dt_at = { -4000, 108371.7, -13036.80, 392.000, 0.0000, -500, 17201.0, -627.82, 16.170, -0.3413, -150,
            12200.6, -346.41, 5.403, -0.1593, 150, 9113.8, -328.13, -1.647, 0.0377, 500, 5707.5, -391.41, 0.915, 0.3145, 900, 2203.4,
            -283.45, 13.034, -0.1778, 1300, 490.1, -57.35, 2.085, -0.0072, 1600, 120.0, -9.81, -1.532, 0.1403, 1700, 10.2, -0.91,
            0.510, -0.0370, 1800, 13.4, -0.72, 0.202, -0.0193, 1830, 7.8, -1.81, 0.416, -0.0247, 1860, 8.3, -0.13, -0.406, 0.0292,
            1880, -5.4, 0.32, -0.183, 0.0173, 1900, -2.3, 2.06, 0.169, -0.0135, 1920, 21.2, 1.69, -0.304, 0.0167, 1940, 24.2, 1.22,
            -0.064, 0.0031, 1960, 33.2, 0.51, 0.231, -0.0109, 1980, 51.0, 1.29, -0.026, 0.0032, 2000, 63.87, 0.1, 0, 0, 2005, 64.7,
            0.4, 0, 0, 2015, 69 };

    /** 二次曲线外推 */
    private static double dt_ext(double y, int jsd) {
        double dy = (y - 1820) / 100;
        return -20 + jsd * dy * dy;
    }

    /** 计算世界时与原子时之差,传入年 */
    private static double dt_calc(double y) {
        double y0 = dt_at[dt_at.length - 2]; // 表中最后一年
        double t0 = dt_at[dt_at.length - 1]; // 表中最后一年的deltatT
        if (y >= y0) {
            int jsd = 31; // sjd是y1年之后的加速度估计。瑞士星历表jsd=31,NASA网站jsd=32,skmap的jsd=29
            if (y > y0 + 100)
                return dt_ext(y, jsd);
            double v = dt_ext(y, jsd); // 二次曲线外推
            double dv = dt_ext(y0, jsd) - t0; // ye年的二次外推与te的差
            return v - dv * (y0 + 100 - y) / 100;
        }
        int i;
        double[] d = dt_at;
        for (i = 0; i < d.length; i += 5)
            if (y < d[i + 5])
                break;
        double t1 = (y - d[i]) / (d[i + 5] - d[i]) * 10, t2 = t1 * t1, t3 = t2 * t1;
        return d[i + 1] + d[i + 2] * t1 + d[i + 3] * t2 + d[i + 4] * t3;
    }

    /** 传入儒略日(J2000起算),计算TD-UT(单位:日) */
    public static double dt_T(double t) {
        return dt_calc(t / 365.2425 + 2000) / 86400.0;
    }

    //=================================章动计算=========================================
    /** 章动计算--中精度章动计算表 */
    private static final double[] nutB = { 2.1824, -33.75705, 36e-6, -1720, 920, 3.5069, 1256.66393, 11e-6, -132, 57, 1.3375,
            16799.4182, -51e-6, -23, 10, 4.3649, -67.5141, 72e-6, 21, -9, 0.04, -628.302, 0, -14, 0, 2.36, 8328.691, 0, 7, 0, 3.46,
            1884.966, 0, -5, 2, 5.44, 16833.175, 0, -4, 2, 3.69, 25128.110, 0, -3, 0, 3.55, 628.362, 0, 2, 0 };

    /** 章动计算--只计算黄经章动 */
    public static double nutationLon2(double t) {
        double a, t2 = t * t, dL = 0;
        double[] B = nutB;
        for (int i = 0; i < B.length; i += 5) {
            if (i == 0)
                a = -1.742 * t;
            else
                a = 0;
            dL += (B[i + 3] + a) * Math.sin(B[i] + B[i + 1] * t + B[i + 2] * t2);
        }
        return dL / 100 / rad;
    }

    // ========================================================
    /** 传入普通纪年或天文纪年，传回天文纪年 */
    public static int year2Ayear(String c) throws RuntimeException {
        Pattern pattern = Pattern.compile("[^0-9Bb*-]");
        String y = pattern.matcher(c).replaceAll("");
        pattern = Pattern.compile("[Bb*-]{2,}");
        y = pattern.matcher(y).replaceFirst("B");
        int year;
        String q = Common.subString(y, 0, 1);
        if (q.equals("B") || q.equals("b") || q.equals("*")) { //通用纪年法(公元前)
            year = 1 - Integer.parseInt(Common.subString(y, 1));
            if (year > 0) {
                throw new RuntimeException("通用纪法的公元前纪法从B.C.1年开始。并且没有公元0年");
            }
        } else
            year = Integer.parseInt(y);
        if (year < -4712)
            throw new RuntimeException("超过B.C. 4713不准");
        if (year > 9999)
            throw new RuntimeException("超过9999年的农历计算很不准。");
        return year;
    }
}
