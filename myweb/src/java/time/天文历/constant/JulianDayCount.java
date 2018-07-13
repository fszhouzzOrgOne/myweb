package time.天文历.constant;

/**
 * 儒略日計算
 */
public class JulianDayCount {
    /** J2000.0纪元在天文学上使用。前缀"J"代表这是一个儒略纪元法，而不是一个贝塞耳纪元。 */
    public static final int J2000 = 2451545;

    public static void main(String[] args) {
        System.out.println(commonEraToJDCount("CE2018-07-13"));
        System.out.println(commonEraToJDCount(2018, 7, 13));
        System.out.println(jdCountToCommonEra(commonEraToJDCount(2018, 7, 13)));

        System.out.println(commonEraToJDCount(0, 12, 31));
        System.out.println(commonEraToJDCount("BCE0001-12-31"));
        System.out
                .println(jdCountToCommonEra(commonEraToJDCount("BCE0001-12-31")));

        System.out.println(commonEraToJDCount("BCE0002-12-31"));
        System.out
                .println(jdCountToCommonEra(commonEraToJDCount("BCE0002-12-31")));
    }

    /**
     * 公曆轉儒略日數
     * 
     * @param year
     *            年，含0年
     * @param month
     *            月
     * @param day
     *            日
     * @return
     */
    public static double commonEraToJDCount(int year, int month, double day) {
        int n = 0, G = 0;
        if (year * 372 + month * 31 + Math.floor(day) >= 588829)
            G = 1; // 判断是否为格里高利历日1582*372+10*31+15
        if (month <= 2) {
            month += 12;
            year--;
        }
        if (G != 0) {
            n = (int) Math.floor(year / 100);
            n = 2 - n + (int) Math.floor(n / 4); // 加百年闰
        }
        return Math.floor(365.25 * (year + 4716))
                + Math.floor(30.6001 * (month + 1)) + day + n - 1524.5;
    }

    /**
     * 公曆轉儒略日數
     * 
     * @param dateStr
     *            日期字串，格式如“CE0918-07-13”“CE2018-07-13”“BCE0551-07-13”
     * @return
     */
    public static double commonEraToJDCount(String dateStr) {
        String[] parts = dateStr.split("-");
        boolean isBce = false;
        if (parts[0].startsWith("BCE")) {
            isBce = true;
        }
        parts[0] = parts[0].replaceAll("CE", "").replaceAll("B", "");
        int year = Integer.parseInt(parts[0]) * (isBce ? -1 : 1);
        // 公元前的年份加1，前1年對應0年
        if (year < 0) {
            year++;
        }
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        return commonEraToJDCount(year, month, day);
    }

    /**
     * 儒略日數轉公曆
     * 
     * @param jd
     *            儒略日數
     * @return 返回時間字符串，不含0年，如“CE2018-07-13 00:00:00.0”“BCE0001-12-31 00:00:00.0”
     */
    public static String jdCountToCommonEra(double jd) {
        int year, month, day, hour, minute;
        double second;
        int D = (int) Math.floor(jd + 0.5), c;
        double F = jd + 0.5 - D; // 取得日数的整数部份A及小数部分F

        if (D >= 2299161) {
            c = (int) Math.floor((D - 1867216.25) / 36524.25);
            D += 1 + c - Math.floor(c / 4);
        }
        D += 1524;
        year = (int) Math.floor((D - 122.1) / 365.25);// 年数
        D -= Math.floor(365.25 * year);
        month = (int) Math.floor(D / 30.601); // 月数
        D -= Math.floor(30.601 * month);
        day = D; // 日数
        if (month > 13) {
            month -= 13;
            year -= 4715;
        } else {
            month -= 1;
            year -= 4716;
        }
        // 日的小数转为时分秒
        F *= 24;
        hour = (int) Math.floor(F);
        F -= hour;
        F *= 60;
        minute = (int) Math.floor(F);
        F -= minute;
        F *= 60;
        second = F;

        boolean isBce = year <= 0;
        if (isBce) { // 去掉0年，改爲公元前1年
            year = Math.abs(year) + 1;
        }
        String yearStr = String.valueOf(year);
        while (yearStr.length() < 4) {
            yearStr = "0" + yearStr;
        }
        yearStr = (isBce ? "B" : "") + "CE" + yearStr;

        String commonEra = yearStr;
        commonEra += "-" + (month < 10 ? "0" : "") + month;
        commonEra += "-" + (day < 10 ? "0" : "") + day;
        commonEra += " " + (hour < 10 ? "0" : "") + hour;
        commonEra += ":" + (minute < 10 ? "0" : "") + minute;
        commonEra += ":" + (second < 10 ? "0" : "") + second;
        return commonEra;
    }
}
