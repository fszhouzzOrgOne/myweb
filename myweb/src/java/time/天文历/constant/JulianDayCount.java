package time.天文历.constant;

/**
 * 儒略日計算
 */
public class JulianDayCount {
    /** J2000.0纪元在天文学上使用。前缀"J"代表这是一个儒略纪元法，而不是一个贝塞耳纪元。 */
    public static final int J2000 = 2451545;

    public static void main(String[] args) {
        String date = "CE0918-07-13";
        String date2 = "CE2018-07-13";
        String date3 = "BCE0001-12-31";
        System.out.println(commonEraToJDCount(date));
        System.out.println(commonEraToJDCount(date2));
        System.out.println(commonEraToJDCount(date3));

        System.out.println(commonEraToJDCount(2018, 7, 13));
        System.out.println(commonEraToJDCount(1, 1, 1));
        System.out.println(commonEraToJDCount(0, 12, 31));
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
}
