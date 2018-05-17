package time.天文历.constant;

import time.天文历.LunarDate;

/**
 * 回曆
 */
public class MoslemCalendar {
    /** 
     * 計算回曆
     * @param dayRL 2000.0起算儒略日
     * @param date 只用於塡返回結果
     */
    public static void getHuiLi(int dayRL, LunarDate date) {
        // 以下算法使用Excel测试得到,测试时主要关心年临界与月临界
        int z, y, m, d;
        d = dayRL + 503105;
        z = (int) Math.floor(d / 10631); // 10631为一周期(30年)
        d -= z * 10631;
        y = (int) Math.floor((d + 0.5) / 354.366); // 加0.5的作用是保证闰年正确(一周中的闰年是第2,5,7,10,13,16,18,21,24,26,29年)
        d -= (int) Math.floor(y * 354.366 + 0.5);
        m = (int) Math.floor((d + 0.11) / 29.51); // 分子加0.11,分母加0.01的作用是第354或355天的的月分保持为12月(m=11)
        d -= (int) Math.floor(m * 29.5 + 0.5);
        date.sethYear(z * 30 + y + 1);
        date.sethMonth(m + 1);
        date.sethDay(d + 1);
    }
}