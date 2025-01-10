package time.天文历.constant;

import time.天文历.LunarDate;

/**
 * 夏曆節日
 */
public class ObFestivalCn {
    /** 中文數字 */
    public static final String[] numCn = { "零", "一", "二", "三", "四", "五", "六",
            "七", "八", "九", "十" };

    /** 計算夏曆節日 */
    public static void getDayName(LunarDate date, LunarDate dateTwo) {
        String impHappyName = dateTwo.getImpHappyName() == null ? "" : dateTwo
                .getImpHappyName();
        String impName = dateTwo.getImpName() == null ? "" : dateTwo
                .getImpName();
        String allName = dateTwo.getAllName() == null ? "" : dateTwo
                .getAllName();
        int holiday = dateTwo.getHoliday();

        // 按农历日期查找重量点节假日
        String d = date.getLunarMonthName()
                + (date.getLunarMonthName().length() < 2 ? "月" : "")
                + date.getLunarDayName();
        if (!date.getLunarLunarLeap().equals("闰")) {
            if (d.equals("正月初一")) {
                impHappyName += "春节 ";
                holiday = 1;
            }
            if (d.equals("正月初二")) {
                impName += "大年初二 ";
                holiday = 1;
            }
            if (d.equals("五月初五")) {
                impHappyName += "端午节 ";
                holiday = 1;
            }
            if (d.equals("八月十五")) {
                impHappyName += "中秋节 ";
                holiday = 1;
            }
            if (d.equals("正月十五")) {
                impHappyName += "元宵节 ";
                impName += "上元节 ";
                allName += "壮族歌墟节 苗族踩山节 达斡尔族卡钦 ";
            }
            if (d.equals("正月十六"))
                allName += "侗族芦笙节(至正月二十) ";
            if (d.equals("正月廿五"))
                allName += "填仓节 ";
            if (d.equals("正月廿九"))
                allName += "送穷日 ";
            if (d.equals("二月初一"))
                allName += "瑶族忌鸟节 ";
            if (d.equals("二月初二")) {
                impName += "春龙节(龙抬头) ";
                allName += "畲族会亲节 ";
            }
            if (d.equals("二月初八"))
                allName += "傈傈族刀杆节 ";
            if (d.equals("三月初三")) {
                impName += "北帝诞 ";
                allName += "苗族黎族歌墟节 ";
            }
            if (d.equals("三月十五"))
                allName += "白族三月街(至三月二十) ";
            if (d.equals("三月廿三"))
                impName += "天后诞 妈祖诞 ";
            if (d.equals("四月初八"))
                impName += "牛王诞 ";
            if (d.equals("四月十八"))
                allName += "锡伯族西迁节 ";
            if (d.equals("五月十三")) {
                impName += "关帝诞 ";
                allName += "阿昌族泼水节 ";
            }
            if (d.equals("五月廿二"))
                allName += "鄂温克族米阔鲁节 ";
            if (d.equals("五月廿九"))
                allName += "瑶族达努节 ";
            if (d.equals("六月初六")) {
                impName += "姑姑节 天贶节 ";
                allName += "壮族祭田节 瑶族尝新节 ";
            }
            if (d.equals("六月廿四"))
                allName += "火把节、星回节(彝、白、佤、阿昌、纳西、基诺族 ) ";
            if (d.equals("七月初七"))
                impName += "七夕(中国情人节,乞巧节,女儿节 ) ";
            if (d.equals("七月十三"))
                allName += "侗族吃新节 ";
            if (d.equals("七月十五"))
                impName += "中元节 鬼节";
            if (d.equals("九月初九"))
                impName += "重阳节 ";
            if (d.equals("十月初一"))
                impName += "祭祖节(十月朝) ";
            if (d.equals("十月十五"))
                impName += "下元节 ";
            if (d.equals("十月十六"))
                allName += "瑶族盘王节 ";
            if (d.equals("十二初八"))
                impName += "腊八节 ";
        }
        if (date.getNextLunarMonthName().equals("正")) { // 最后一月
            if (d.equals("十二三十") && date.getDaysofLunarMonth() == 30) {
                impHappyName += "除夕 ";
                holiday = 1;
            }
            if (d.equals("十二廿九") && date.getDaysofLunarMonth() == 29) {
                impHappyName += "除夕 ";
                holiday = 1;
            }
            if (d.equals("十二廿三"))
                impName += "小年 ";
        }
        if (date.getLunarSolarTerm() != null
                && !date.getLunarSolarTerm().equals("")) {
            if (date.getLunarSolarTerm().equals("清明")) {
                impHappyName += date.getLunarSolarTerm() + " ";
                holiday = 1;
            } else
                impName += date.getLunarSolarTerm() + " ";
        }

        // 农历杂节
        String w, w2;
        if (date.getDaysToDZ() >= 0 && date.getDaysToDZ() < 81) { // 数九
            w = numCn[(int) (Math.floor(date.getDaysToDZ() / 9) + 1)];
            if (date.getDaysToDZ() % 9 == 0)
                impName += "『" + w + "九』 ";
            else
                allName += w + "九第" + (date.getDaysToDZ() % 9 + 1) + "天 ";
        }

        w = Common.subString(date.getCnEraDay(), 0, 1);
        w2 = Common.subString(date.getCnEraDay(), 1, 2);
        if (date.getDaysToXZ() >= 20 && date.getDaysToXZ() < 30
                && w.equals("庚"))
            impName += "初伏 ";
        if (date.getDaysToXZ() >= 30 && date.getDaysToXZ() < 40
                && w.equals("庚"))
            impName += "中伏 ";
        if (date.getDaysToLQ() >= 0 && date.getDaysToLQ() < 10 && w.equals("庚"))
            impName += "末伏 ";
        if (date.getDaysToMZ() >= 0 && date.getDaysToMZ() < 10 && w.equals("丙"))
            impName += "入梅 ";
        if (date.getDaysToXS() >= 0 && date.getDaysToXS() < 12
                && w2.equals("未"))
            impName += "出梅 ";

        dateTwo.setImpHappyName(impHappyName);
        dateTwo.setImpName(impName);
        dateTwo.setAllName(allName);
        dateTwo.setHoliday(holiday);
    }
}
