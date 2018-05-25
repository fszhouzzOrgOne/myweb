package time.天文历.constant;

import time.天文历.LunarDate;

public class ObChronology {
    /** 纪年表,由init初始化 */
    public static String[] ChronologyTable;

    /** 中文数字 */
    public static final String[] numCn = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
    /** 天干 */
    public static final String[] Gan = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
    /** 地支 */
    public static final String[] Zhi = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
    /** 屬相 */
    public static final String[] ShX = { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
    /** 星座 */
    public static final String[] XiZ = { "摩羯", "水瓶", "双鱼", "白羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手" };
    /** 月相名称表 */
    public static final String[] yxmc = { "朔", "上弦", "望", "下弦" };
    /** 節氣名稱 */
    public static final String[] jqmc = { "冬至", "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至",
            "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪" };
    /** 月名称,建寅 */
    public static final String[] ymc = { "十一", "十二", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
    /** 中文日期名稱 */
    public static final String[] rmc = { "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三",
            "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十",
            "卅一" };

    public static void main(String[] args) {
        System.out.println(getEraName(911));
    }

    /** 取年号 */
    public static String getEraName(int year) {
        int j;
        String s = "", c;
        String[] JNB = ObChronology.ChronologyTable;
        for (int i = 0; i < JNB.length; i += 7) {
            j = Integer.parseInt(JNB[i]);
            // 年小於表中的年，或年大於年號的末年
            if (year < j || year >= j + Integer.parseInt(JNB[i + 1])) {
                continue;
            }
            // 年號名稱，當前年減去元年，加1，加上已用年份
            c = JNB[i + 6] + (year - j + 1 + Integer.parseInt(JNB[i + 2])) + "年";
            // i爲年號元年,i+3朝代,i+4廟號,i+5皇帝名,i+6年號
            s += (!"".equals(s) ? ";" : "") + "[" + JNB[i + 3] + "]" + JNB[i + 4] + " " + JNB[i + 5] + " " + c;
        }
        return s;
    }

    /** 计算农历节日 */
    public static void getDayName(LunarDate date, LunarDate dateTwo) {
        String impHappyName = dateTwo.getImpHappyName() == null ? "" : dateTwo.getImpHappyName();
        String impName = dateTwo.getImpName() == null ? "" : dateTwo.getImpName();
        String allName = dateTwo.getAllName() == null ? "" : dateTwo.getAllName();
        int holiday = dateTwo.getHoliday();

        // 按农历日期查找重量点节假日
        String d = date.getLunarMonthName() + (date.getLunarMonthName().length() < 2 ? "月" : "")
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
        if (date.getLunarSolarTerm() != null && !date.getLunarSolarTerm().equals("")) {
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
        if (date.getDaysToXZ() >= 20 && date.getDaysToXZ() < 30 && w.equals("庚"))
            impName += "初伏 ";
        if (date.getDaysToXZ() >= 30 && date.getDaysToXZ() < 40 && w.equals("庚"))
            impName += "中伏 ";
        if (date.getDaysToLQ() >= 0 && date.getDaysToLQ() < 10 && w.equals("庚"))
            impName += "末伏 ";
        if (date.getDaysToMZ() >= 0 && date.getDaysToMZ() < 10 && w.equals("丙"))
            impName += "入梅 ";
        if (date.getDaysToXS() >= 0 && date.getDaysToXS() < 12 && w2.equals("未"))
            impName += "出梅 ";

        dateTwo.setImpHappyName(impHappyName);
        dateTwo.setImpName(impName);
        dateTwo.setAllName(allName);
        dateTwo.setHoliday(holiday);
    }

    /** 精气 */
    public static double qi_accurate(double W) {
        double t = XL.S_aLon_t(W) * 36525;
        return t - Common.dt_T(t) + (double) 8 / 24;
    }

    /** 精朔 */
    public static double so_accurate(double W) {
        double t = XL.MS_aLon_t(W) * 36525;
        return t - Common.dt_T(t) + (double) 8 / 24;
    }

    // // 精气
    // public static double qi_accurate2(double jd) {
    // double d = Math.PI / 12;
    // double w = Math.floor((jd + 293) / 365.2422 * 24) * d;
    // double a = this.qi_accurate(w);
    // if (a - jd > 5)
    // return this.qi_accurate(w - d);
    // if (a - jd < -5)
    // return this.qi_accurate(w + d);
    // return a;
    // }
    //
    // // 精朔
    // public static double so_accurate2(double jd) {
    // return this.so_accurate(Math.floor((jd + 8) / 29.5306) * Math.PI * 2);
    // }

    static {
        /**
         * 纪年数据结构：数据用逗号分开，每7个描述一个年号<br/>
         * 格式为:起始公元,使用年数,已用年数,朝代,廟号,皇帝,年号
         */
        StringBuilder sbd = new StringBuilder();
        sbd.append("-2069,45,0,夏,禹,,禹,");
        sbd.append("-2024,10,0,夏,启,,启,");
        sbd.append("-2014,25,0,夏,太康,,太康,");
        sbd.append("-1986,14,0,夏,仲康,,仲康,");
        sbd.append("-1972,28,0,夏,相,,相,");
        sbd.append("-1944,2,0,夏,后羿,,后羿,");
        sbd.append("-1942,38,0,夏,寒浞,,寒浞,");
        sbd.append("-1904,21,0,夏,少康,,少康,");
        sbd.append("-1883,17,0,夏,杼,,杼,");
        sbd.append("-1866,26,0,夏,槐,,槐,");
        sbd.append("-1840,18,0,夏,芒,,芒,");
        sbd.append("-1822,16,0,夏,泄,,泄,");
        sbd.append("-1806,59,0,夏,不降,,不降,");
        sbd.append("-1747,21,0,夏,扃,,扃,");
        sbd.append("-1726,21,0,夏,廑,,廑,");
        sbd.append("-1705,31,0,夏,孔甲,,孔甲,");
        sbd.append("-1674,11,0,夏,皋,,皋,");
        sbd.append("-1663,11,0,夏,发,,发,");
        sbd.append("-1652,53,0,夏,桀,,桀,");
        sbd.append("-1599,11,0,商,商太祖,汤,商汤,");
        sbd.append("-1588,1,0,商,商代王,太乙,商代王,");
        sbd.append("-1587,2,0,商,哀王,子胜,外丙,");
        sbd.append("-1585,4,0,商,懿王,子庸,仲壬,");
        sbd.append("-1581,12,0,商,太宗,子至,太甲,");
        sbd.append("-1569,29,0,商,昭王,子绚,沃丁,");
        sbd.append("-1540,25,0,商,宣王,子辩,太庚,");
        sbd.append("-1515,17,0,商,敬王,子高,小甲,");
        sbd.append("-1498,13,0,商,元王,子密,雍己,");
        sbd.append("-1485,75,0,商,中宗,子伷,太戊,");
        sbd.append("-1410,11,0,商,孝成王,子庄,仲丁,");
        sbd.append("-1399,15,0,商,思王,子发,外壬,");
        sbd.append("-1384,9,0,商,前平王,子整,河亶甲,");
        sbd.append("-1375,19,0,商,穆王,子滕,祖乙,");
        sbd.append("-1356,16,0,商,桓王,子旦,祖辛,");
        sbd.append("-1340,5,0,商,僖王,子逾,沃甲,");
        sbd.append("-1335,9,0,商,庄王,子新,祖丁,");
        sbd.append("-1326,6,0,商,顷王,子更,南庚,");
        sbd.append("-1320,7,0,商,悼王,子和,阳甲,");
        sbd.append("-1313,42,0,商,世祖,子旬,盘庚,");
        sbd.append("-1271,21,0,商,章王,子颂,小辛,");
        sbd.append("-1250,1,0,商,惠王,子敛,小乙,");
        sbd.append("-1249,59,0,商,高宗,子昭,武丁,");
        sbd.append("-1190,2,0,商,后平王,子跃,祖庚,");
        sbd.append("-1188,33,0,商,世宗,子载,祖甲,");
        sbd.append("-1155,8,0,商,甲宗,子先,廪辛,");
        sbd.append("-1147,1,0,商,康祖,子嚣,庚丁,");
        sbd.append("-1146,35,0,商,武祖,子瞿,武乙,");
        sbd.append("-1111,11,0,商,匡王,子托,文丁,");
        sbd.append("-1100,26,0,商,德王,子羡,帝乙,");
        sbd.append("-1074,29,0,商,纣王,子寿,帝辛,");
        sbd.append("-1045,4,0,西周,武王,姬发,武王,");
        sbd.append("-1041,22,0,西周,成王,姬诵,成王,");
        sbd.append("-1019,25,0,西周,康王,姬钊,康王,");
        sbd.append("-994,19,0,西周,昭王,姬瑕,昭王,");
        sbd.append("-975,54,0,西周,穆王,姬满,穆王,");
        sbd.append("-921,23,0,西周,共王,姬繄,共王,");
        sbd.append("-898,8,0,西周,懿王,姬囏,懿王,");
        sbd.append("-890,6,0,西周,孝王,姬辟方,孝王,");
        sbd.append("-884,8,0,西周,夷王,姬燮,夷王,");
        sbd.append("-876,36,0,西周,厉王,姬胡,厉王,");
        sbd.append("-840,14,0,西周,厉王,姬胡,共和,");
        sbd.append("-826,46,0,西周,宣王,姬静,宣王,");
        sbd.append("-780,11,0,西周,幽王,姬宫湦,幽王,");
        sbd.append("-769,51,0,东周,平王,姬宜臼,平王,");
        sbd.append("-718,23,0,东周,桓王,姬林,桓王,");
        sbd.append("-695,15,0,东周,庄王,姬佗,庄王,");
        sbd.append("-680,5,0,东周,釐王,姬胡齐,釐王,");
        sbd.append("-675,25,0,东周,惠王,姬阆,惠王,");
        sbd.append("-650,33,0,东周,襄王,姬郑,襄王,");
        sbd.append("-617,6,0,东周,顷王,姬壬臣,顷王,");
        sbd.append("-611,6,0,东周,匡王,姬班,匡王,");
        sbd.append("-605,21,0,东周,定王,姬瑜,定王,");
        sbd.append("-584,14,0,东周,简王,姬夷,简王,");
        sbd.append("-570,27,0,东周,灵王,姬泄心,灵王,");
        sbd.append("-543,24,0,东周,景王,姬贵,景王,");
        sbd.append("-519,1,0,东周,悼王,姬勐,悼王,");
        sbd.append("-518,44,0,东周,敬王,姬匄,敬王,");
        sbd.append("-474,7,0,东周,元王,姬仁,元王,");
        sbd.append("-467,27,0,东周,贞定王,姬介,贞定王,");
        sbd.append("-440,1,0,东周,哀王-思王,姬去疾-姬叔,哀王-思王,");
        sbd.append("-439,15,0,东周,考王,姬嵬,考王,");
        sbd.append("-424,24,0,东周,威烈王,姬午,威烈王,");
        sbd.append("-400,26,0,东周,安王,姬骄,安王,");
        sbd.append("-374,7,0,东周,烈王,姬喜,烈王,");
        sbd.append("-367,48,0,东周,显王,姬扁,显王,");
        sbd.append("-319,6,0,东周,慎靓王,姬定,慎靓王,");
        sbd.append("-313,8,0,东周,赧王,姬延,赧王,");
        sbd.append("-305,56,0,战国-秦,昭襄王,嬴则,昭襄王,");
        sbd.append("-249,1,0,战国-秦,孝文王,嬴柱,孝文王,");
        sbd.append("-248,3,0,战国-秦,庄襄王,嬴子楚,庄襄王,");
        sbd.append("-245,25,0,秦,嬴政,嬴政,嬴政,");
        sbd.append("-220,12,0,秦,始皇帝,嬴政,始皇,");
        sbd.append("-208,3,0,秦,二世皇帝,嬴胡亥,二世,");
        sbd.append("-205,12,0,西汉,高帝,刘邦,高帝,");
        sbd.append("-193,7,0,西汉,惠帝,刘盈,惠帝,");
        sbd.append("-186,8,0,西汉,高后,吕雉,高后,");
        sbd.append("-178,16,0,西汉,文帝,刘恒,文帝,");
        sbd.append("-162,7,0,西汉,文帝,刘恒,后元,");
        sbd.append("-155,7,0,西汉,景帝,刘启,景帝,");
        sbd.append("-148,6,0,西汉,景帝,刘启,中元,");
        sbd.append("-142,3,0,西汉,景帝,刘启,后元,");
        sbd.append("-139,6,0,西汉,武帝,刘彻,建元,");
        sbd.append("-133,6,0,西汉,武帝,刘彻,元光,");
        sbd.append("-127,6,0,西汉,武帝,刘彻,元朔,");
        sbd.append("-121,6,0,西汉,武帝,刘彻,元狩,");
        sbd.append("-115,6,0,西汉,武帝,刘彻,元鼎,");
        sbd.append("-109,6,0,西汉,武帝,刘彻,元封,");
        sbd.append("-103,4,0,西汉,武帝,刘彻,太初,");
        sbd.append("-99,4,0,西汉,武帝,刘彻,天汉,");
        sbd.append("-95,4,0,西汉,武帝,刘彻,太始,");
        sbd.append("-91,4,0,西汉,武帝,刘彻,征和,");
        sbd.append("-87,2,0,西汉,武帝,刘彻,后元,");
        sbd.append("-85,6,0,西汉,昭帝,刘弗陵,始元,");
        sbd.append("-79,6,0,西汉,昭帝,刘弗陵,元凤,");
        sbd.append("-73,1,0,西汉,昭帝,刘弗陵,元平,");
        sbd.append("-72,4,0,西汉,宣帝,刘询,本始,");
        sbd.append("-68,4,0,西汉,宣帝,刘询,地节,");
        sbd.append("-64,4,0,西汉,宣帝,刘询,元康,");
        sbd.append("-60,4,0,西汉,宣帝,刘询,神爵,");
        sbd.append("-56,4,0,西汉,宣帝,刘询,五凤,");
        sbd.append("-52,4,0,西汉,宣帝,刘询,甘露,");
        sbd.append("-48,1,0,西汉,宣帝,刘询,黄龙,");
        sbd.append("-47,5,0,西汉,元帝,刘奭,初元,");
        sbd.append("-42,5,0,西汉,元帝,刘奭,永光,");
        sbd.append("-37,5,0,西汉,元帝,刘奭,建昭,");
        sbd.append("-32,1,0,西汉,元帝,刘奭,竟宁,");
        sbd.append("-31,4,0,西汉,成帝,刘骜,建始,");
        sbd.append("-27,4,0,西汉,成帝,刘骜,河平,");
        sbd.append("-23,4,0,西汉,成帝,刘骜,阳朔,");
        sbd.append("-19,4,0,西汉,成帝,刘骜,鸿嘉,");
        sbd.append("-15,4,0,西汉,成帝,刘骜,永始,");
        sbd.append("-11,4,0,西汉,成帝,刘骜,元延,");
        sbd.append("-7,2,0,西汉,成帝,刘骜,绥和,");
        sbd.append("-5,4,0,西汉,哀帝,刘欣,建平,");
        sbd.append("-1,2,0,西汉,哀帝,刘欣,元寿,");
        sbd.append("1,5,0,西汉,平帝,刘衍,元始,");
        sbd.append("6,2,0,西汉,孺子婴,王莽摄政,居摄,");
        sbd.append("8,1,0,西汉,孺子婴,王莽摄政,初始,");
        sbd.append("9,5,0,新,,王莽,始建国,");
        sbd.append("14,6,0,新,,王莽,天凤,");
        sbd.append("20,3,0,新,,王莽,地皇,");
        sbd.append("23,2,0,西汉,更始帝,刘玄,更始,");
        sbd.append("25,31,0,东汉,光武帝,刘秀,建武,");
        sbd.append("56,2,0,东汉,光武帝,刘秀,建武中元,");
        sbd.append("58,18,0,东汉,明帝,刘庄,永平,");
        sbd.append("76,8,0,东汉,章帝,刘炟,建初,");
        sbd.append("84,3,0,东汉,章帝,刘炟,元和,");
        sbd.append("87,2,0,东汉,章帝,刘炟,章和,");
        sbd.append("89,16,0,东汉,和帝,刘肇,永元,");
        sbd.append("105,1,0,东汉,和帝,刘肇,元兴,");
        sbd.append("106,1,0,东汉,殇帝,刘隆,延平,");
        sbd.append("107,7,0,东汉,安帝,刘祜,永初,");
        sbd.append("114,6,0,东汉,安帝,刘祜,元初,");
        sbd.append("120,1,0,东汉,安帝,刘祜,永宁,");
        sbd.append("121,1,0,东汉,安帝,刘祜,建光,");
        sbd.append("122,4,0,东汉,安帝,刘祜,延光,");
        sbd.append("126,6,0,东汉,顺帝,刘保,永建,");
        sbd.append("132,4,0,东汉,顺帝,刘保,阳嘉,");
        sbd.append("136,6,0,东汉,顺帝,刘保,永和,");
        sbd.append("142,2,0,东汉,顺帝,刘保,汉安,");
        sbd.append("144,1,0,东汉,顺帝,刘保,建康,");
        sbd.append("145,1,0,东汉,冲帝,刘炳,永嘉,");
        sbd.append("146,1,0,东汉,质帝,刘缵,本初,");
        sbd.append("147,3,0,东汉,桓帝,刘志,建和,");
        sbd.append("150,1,0,东汉,桓帝,刘志,和平,");
        sbd.append("151,2,0,东汉,桓帝,刘志,元嘉,");
        sbd.append("153,2,0,东汉,桓帝,刘志,永兴,");
        sbd.append("155,3,0,东汉,桓帝,刘志,永寿,");
        sbd.append("158,9,0,东汉,桓帝,刘志,延熹,");
        sbd.append("167,1,0,东汉,桓帝,刘志,永康,");
        sbd.append("168,4,0,东汉,灵帝,刘宏,建宁,");
        sbd.append("172,5,0,东汉,灵帝,刘宏,熹平,");
        sbd.append("178,6,0,东汉,灵帝,刘宏,光和,");
        sbd.append("184,6,0,东汉,灵帝,刘宏,中平,");
        sbd.append("190,4,0,东汉,献帝,刘协,初平,");
        sbd.append("194,2,0,东汉,献帝,刘协,兴平,");
        sbd.append("196,24,0,东汉,献帝,刘协,建安,");
        sbd.append("220,7,0,三国-魏,文帝,曹丕,黄初,");
        sbd.append("227,6,0,三国-魏,明帝,曹叡,太和,");
        sbd.append("233,4,0,三国-魏,明帝,曹叡,青龙,");
        sbd.append("237,3,0,三国-魏,明帝,曹叡,景初,");
        sbd.append("240,9,0,三国-魏,齐王,曹芳,正始,");
        sbd.append("249,5,0,三国-魏,齐王,曹芳,嘉平,");
        sbd.append("254,2,0,三国-魏,高贵乡公,曹髦,正元,");
        sbd.append("256,4,0,三国-魏,高贵乡公,曹髦,甘露,");
        sbd.append("260,4,0,三国-魏,元帝,曹奂,景元,");
        sbd.append("264,1,0,三国-魏,元帝,曹奂,咸熙,");
        sbd.append("265,10,0,西晋,武帝,司马炎,泰始,");
        sbd.append("275,5,0,西晋,武帝,司马炎,咸宁,");
        sbd.append("280,10,0,西晋,武帝,司马炎,太康,");
        sbd.append("290,10,0,西晋,武帝,司马炎,太熙,");
        sbd.append("300,1,0,西晋,惠帝,司马衷,永康,");
        sbd.append("301,1,0,西晋,惠帝,司马衷,永宁,");
        sbd.append("302,2,0,西晋,惠帝,司马衷,太安,");
        sbd.append("304,2,0,西晋,惠帝,司马衷,永安,");
        sbd.append("306,1,0,西晋,惠帝,司马衷,光熙,");
        sbd.append("307,6,0,西晋,怀帝,司马炽,永嘉,");
        sbd.append("313,4,0,西晋,愍帝,司马邺,建兴,");
        sbd.append("317,1,0,东晋,元帝,司马睿,建武,");
        sbd.append("318,4,0,东晋,元帝,司马睿,大兴,");
        sbd.append("322,1,0,东晋,元帝,司马睿,永昌,");
        sbd.append("323,3,0,东晋,明帝,司马绍,太宁,");
        sbd.append("326,9,0,东晋,成帝,司马衍,咸和,");
        sbd.append("335,8,0,东晋,成帝,司马衍,咸康,");
        sbd.append("343,2,0,东晋,康帝,司马岳,建元,");
        sbd.append("345,12,0,东晋,穆帝,司马聃,永和,");
        sbd.append("357,5,0,东晋,穆帝,司马聃,升平,");
        sbd.append("362,1,0,东晋,哀帝,司马丕,隆和,");
        sbd.append("363,3,0,东晋,哀帝,司马丕,兴宁,");
        sbd.append("366,5,0,东晋,海西公,司马奕,太和,");
        sbd.append("371,2,0,东晋,简文帝,司马昱,咸安,");
        sbd.append("373,3,0,东晋,孝武帝,司马曜,甯康,");
        sbd.append("376,21,0,东晋,孝武帝,司马曜,太元,");
        sbd.append("397,5,0,东晋,安帝,司马德宗,隆安,");
        sbd.append("402,3,0,东晋,安帝,司马德宗,元兴,");
        sbd.append("405,14,0,东晋,安帝,司马德宗,义熙,");
        sbd.append("419,1,0,东晋,恭帝,司马德文,元熙,");
        sbd.append("420,3,0,南朝/宋,武帝,刘裕,永初,");
        sbd.append("423,2,0,南朝/宋,少帝,刘义符,景平,");
        sbd.append("424,30,0,南朝/宋,文帝,刘義隆,元嘉,");
        sbd.append("454,3,0,南朝/宋,孝武,帝刘骏,孝建,");
        sbd.append("457,8,0,南朝/宋,孝武,帝刘骏,大明,");
        sbd.append("465,1,0,南朝/宋,废帝,刘子业,永光,");
        sbd.append("465,1,0,南朝/宋,废帝,刘子业,景和,");
        sbd.append("465,7,0,南朝/宋,明帝,刘彧,泰始,");
        sbd.append("472,1,0,南朝/宋,明帝,刘彧,泰豫,");
        sbd.append("473,5,0,南朝/宋,废帝,刘昱,元徽,");
        sbd.append("477,3,0,南朝/宋,顺帝,刘准,升明,");
        sbd.append("479,4,0,南朝/齐,高帝,萧道成,建元,");
        sbd.append("483,11,0,南朝/齐,武帝,萧赜,永明,");
        sbd.append("494,1,0,南朝/齐,欎林王,萧昭业,隆昌,");
        sbd.append("494,1,0,南朝/齐,海陵王,萧昭文,延兴,");
        sbd.append("494,5,0,南朝/齐,明帝,萧鸾,建武,");
        sbd.append("498,1,0,南朝/齐,明帝,萧鸾,永泰,");
        sbd.append("499,3,0,南朝/齐,东昏侯,萧宝,中兴,");
        sbd.append("501,2,0,南朝/齐,和帝,萧宝融,中兴,");
        sbd.append("502,18,0,南朝/梁,武帝,萧衍,天监,");
        sbd.append("520,8,0,南朝/梁,武帝,萧衍,普通,");
        sbd.append("527,3,0,南朝/梁,武帝,萧衍,大通,");
        sbd.append("529,6,0,南朝/梁,武帝,萧衍,中大通,");
        sbd.append("535,12,0,南朝/梁,武帝,萧衍,大同,");
        sbd.append("546,2,0,南朝/梁,武帝,萧衍,中大同,");
        sbd.append("547,3,0,南朝/梁,武帝,萧衍,太清,");
        sbd.append("550,2,0,南朝/梁,简文帝,萧纲,大宝,");
        sbd.append("551,2,0,南朝/梁,豫章王,萧栋,天正,");
        sbd.append("552,4,0,南朝/梁,元帝,萧绎,承圣,");
        sbd.append("555,1,0,南朝/梁,贞阳侯,萧渊明,天成,");
        sbd.append("555,2,0,南朝/梁,敬帝,萧方智,绍泰,");
        sbd.append("556,2,0,南朝/梁,敬帝,萧方智,太平,");
        sbd.append("557,3,0,南朝/陈,武帝,陈霸先,太平,");
        sbd.append("560,7,0,南朝/陈,文帝,陈蒨,天嘉,");
        sbd.append("566,1,0,南朝/陈,文帝,陈蒨,天康,");
        sbd.append("567,2,0,南朝/陈,废帝,陈伯宗,光大,");
        sbd.append("569,14,0,南朝/陈,宣帝,陈顼,太建,");
        sbd.append("583,4,0,南朝/陈,后主,陈叔宝,至德,");
        sbd.append("587,3,0,南朝/陈,后主,陈叔宝,祯明,");
        sbd.append("555,8,0,南朝/后梁,宣帝,萧詧,大定,");
        sbd.append("562,24,0,南朝/后梁,明帝,萧岿,天保,");
        sbd.append("586,2,0,南朝/后梁,莒公,萧琮,广运,");
        sbd.append("386,11,0,北朝/北魏,道武帝,拓跋圭,登国,");
        sbd.append("396,3,0,北朝/北魏,道武帝,拓跋圭,皇始,");
        sbd.append("398,7,0,北朝/北魏,道武帝,拓跋圭,天兴,");
        sbd.append("404,6,0,北朝/北魏,道武帝,拓跋圭,天赐,");
        sbd.append("409,5,0,北朝/北魏,明元帝,拓跋嗣,永兴,");
        sbd.append("414,3,0,北朝/北魏,明元帝,拓跋嗣,神瑞,");
        sbd.append("416,8,0,北朝/北魏,明元帝,拓跋嗣,泰常,");
        sbd.append("424,5,0,北朝/北魏,太武帝,拓跋焘,始光,");
        sbd.append("428,4,0,北朝/北魏,太武帝,拓跋焘,神麚,");
        sbd.append("432,3,0,北朝/北魏,太武帝,拓跋焘,延和,");
        sbd.append("435,6,0,北朝/北魏,太武帝,拓跋焘,太延,");
        sbd.append("440,12,0,北朝/北魏,太武帝,拓跋焘,太平真君,");
        sbd.append("451,2,0,北朝/北魏,太武帝,拓跋焘,正平,");
        sbd.append("452,1,0,北朝/北魏,南安王,拓跋余,承平,");
        sbd.append("452,3,0,北朝/北魏,文成帝,拓跋浚,兴安,");
        sbd.append("454,2,0,北朝/北魏,文成帝,拓跋浚,兴光,");
        sbd.append("455,5,0,北朝/北魏,文成帝,拓跋浚,太安,");
        sbd.append("460,6,0,北朝/北魏,文成帝,拓跋浚,和平,");
        sbd.append("466,2,0,北朝/北魏,献文帝,拓跋弘,天安,");
        sbd.append("467,5,0,北朝/北魏,献文帝,拓跋弘,皇兴,");
        sbd.append("471,6,0,北朝/北魏,教文帝,拓跋宏,延兴,");
        sbd.append("476,1,0,北朝/北魏,孝文帝,拓跋宏,承明,");
        sbd.append("477,23,0,北朝/北魏,孝文帝,拓跋宏,太和,");
        sbd.append("500,4,0,北朝/北魏,宣武帝,元恪,景明,");
        sbd.append("504,5,0,北朝/北魏,宣武帝,元恪,正始,");
        sbd.append("508,5,0,北朝/北魏,宣武帝,元恪,永平,");
        sbd.append("512,4,0,北朝/北魏,宣武帝,元恪,延昌,");
        sbd.append("516,3,0,北朝/北魏,孝明帝,元诩,熙平,");
        sbd.append("518,3,0,北朝/北魏,孝明帝,元诩,神龟,");
        sbd.append("520,6,0,北朝/北魏,孝明帝,元诩,正光,");
        sbd.append("525,3,0,北朝/北魏,孝明帝,元诩,孝昌,");
        sbd.append("528,1,0,北朝/北魏,孝明帝,元诩,武泰,");
        sbd.append("528,1,0,北朝/北魏,孝庄帝,元子攸,建义,");
        sbd.append("528,3,0,北朝/北魏,孝庄帝,元子攸,永安,");
        sbd.append("530,2,0,北朝/北魏,东海王,元晔,建明,");
        sbd.append("531,2,0,北朝/北魏,节闵帝,元恭,普泰,");
        sbd.append("531,2,0,北朝/北魏,安定王,元朗,中兴,");
        sbd.append("532,1,0,北朝/北魏,孝武帝,元修,太昌,");
        sbd.append("532,1,0,北朝/北魏,孝武帝,元修,永兴,");
        sbd.append("532,3,0,北朝/北魏,孝武帝,元修,永熙,");
        sbd.append("534,4,0,北朝/东魏,孝静帝,元善见,天平,");
        sbd.append("538,2,0,北朝/东魏,孝静帝,元善见,元象,");
        sbd.append("539,4,0,北朝/东魏,孝静帝,元善见,兴和,");
        sbd.append("543,8,0,北朝/东魏,孝静帝,元善见,武定,");
        sbd.append("535,17,0,北朝/西魏,文帝,元宝炬,大统,");
        sbd.append("552,3,0,北朝/西魏,废帝,元钦,大统,");
        sbd.append("554,3,0,北朝/西魏,恭帝,元廓,大统,");
        sbd.append("550,10,0,北朝/北齐,文宣帝,高洋,天保,");
        sbd.append("560,1,0,北朝/北齐,废帝,高殷,乾明,");
        sbd.append("560,2,0,北朝/北齐,孝昭帝,高演,皇建,");
        sbd.append("561,2,0,北朝/北齐,武成帝,高湛,太宁,");
        sbd.append("562,4,0,北朝/北齐,武成帝,高湛,河清,");
        sbd.append("565,5,0,北朝/北齐,温公,高纬,天统,");
        sbd.append("570,7,0,北朝/北齐,温公,高纬,武平,");
        sbd.append("576,2,0,北朝/北齐,温公,高纬,隆化,");
        sbd.append("576,1,0,北朝/北齐,安德王,高延宗,德昌,");
        sbd.append("577,1,0,北朝/北齐,幼主,高恒,承光,");
        sbd.append("557,1,0,北朝/北周,闵帝,宇文觉,空,");
        sbd.append("557,2,0,北朝/北周,明帝,宇文毓,空,");
        sbd.append("559,2,0,北朝/北周,明帝,宇文毓,武成,");
        sbd.append("561,5,0,北朝/北周,武帝,宇文邕,保定,");
        sbd.append("566,7,0,北朝/北周,武帝,宇文邕,天和,");
        sbd.append("572,7,0,北朝/北周,武帝,宇文邕,建德,");
        sbd.append("578,1,0,北朝/北周,武帝,宇文邕,宣政,");
        sbd.append("579,1,0,北朝/北周,宣帝,宇文贇,大成,");
        sbd.append("579,2,0,北朝/北周,静帝,宇文衍,大象,");
        sbd.append("581,1,0,北朝/北周,静帝,宇文衍,大定,");
        sbd.append("581,20,0,隋,文帝,杨坚,开皇,");
        sbd.append("601,4,0,隋,文帝,杨坚,仁寿,");
        sbd.append("605,13,0,隋,炀帝,杨广,大业,");
        sbd.append("617,2,0,隋,恭帝,杨侑,义宁,");
        sbd.append("618,9,0,唐,高祖,李渊,武德,");
        sbd.append("627,23,0,唐,太宗,李世民,贞观,");
        sbd.append("650,6,0,唐,高宗,李治,永徽,");
        sbd.append("656,6,0,唐,高宗,李治,显庆,");
        sbd.append("661,3,0,唐,高宗,李治,龙朔,");
        sbd.append("664,2,0,唐,高宗,李治,麟德,");
        sbd.append("666,3,0,唐,高宗,李治,乾封,");
        sbd.append("668,3,0,唐,高宗,李治,总章,");
        sbd.append("670,5,0,唐,高宗,李治,咸亨,");
        sbd.append("674,3,0,唐,高宗,李治,上元,");
        sbd.append("676,4,0,唐,高宗,李治,仪凤,");
        sbd.append("679,2,0,唐,高宗,李治,调露,");
        sbd.append("680,2,0,唐,高宗,李治,永隆,");
        sbd.append("681,2,0,唐,高宗,李治,开耀,");
        sbd.append("682,2,0,唐,高宗,李治,永淳,");
        sbd.append("683,1,0,唐,高宗,李治,弘道,");
        sbd.append("684,1,0,唐,中宗,李显,嗣圣,");
        sbd.append("684,1,0,唐,睿宗,李旦,文明,");
        sbd.append("684,1,0,武周,则天后,武曌,光宅,");
        sbd.append("685,4,0,武周,则天后,武曌,垂拱,");
        sbd.append("689,1,0,武周,则天后,武曌,永昌,");
        sbd.append("689,2,0,武周,则天后,武曌,载初,");
        sbd.append("690,3,0,武周,则天后,武曌,天授,");
        sbd.append("692,1,0,武周,则天后,武曌,如意,");
        sbd.append("692,3,0,武周,则天后,武曌,长寿,");
        sbd.append("694,1,0,武周,则天后,武曌,延载,");
        sbd.append("695,1,0,武周,则天后,武曌,证圣,");
        sbd.append("695,2,0,武周,则天后,武曌,天册万岁,");
        sbd.append("696,1,0,武周,则天后,武曌,万岁登封,");
        sbd.append("696,2,0,武周,则天后,武曌,万岁通天,");
        sbd.append("697,1,0,武周,则天后,武曌,神功,");
        sbd.append("698,3,0,武周,则天后,武曌,圣历,");
        sbd.append("700,1,0,武周,则天后,武曌,久视,");
        sbd.append("701,1,0,武周,则天后,武曌,大足,");
        sbd.append("701,4,0,武周,则天后,武曌,长安,");
        sbd.append("705,1,0,武周,则天后,李显,神龙,");
        sbd.append("705,2,0,唐,中宗,李显,神龙,");
        sbd.append("707,4,0,唐,中宗,李显,景龙,");
        sbd.append("710,1,0,唐,温王,李重茂,唐隆,");
        sbd.append("710,2,0,唐,睿宗,李旦,景云,");
        sbd.append("712,1,0,唐,睿宗,李旦,太极,");
        sbd.append("712,1,0,唐,睿宗,李旦,延和,");
        sbd.append("712,2,0,唐,玄宗,李隆基,先天,");
        sbd.append("713,29,0,唐,玄宗,李隆基,开元,");
        sbd.append("742,15,0,唐,玄宗,李隆基,天宝,");
        sbd.append("756,3,0,唐,肃宗,李亨,至德,");
        sbd.append("758,3,0,唐,肃宗,李亨,乾元,");
        sbd.append("760,3,0,唐,肃宗,李亨,上元,");
        sbd.append("762,2,0,唐,肃宗,李亨,宝应,");
        sbd.append("763,2,0,唐,代宗,李豫,广德,");
        sbd.append("765,2,0,唐,肃宗,李亨,永泰,");
        sbd.append("766,14,0,唐,肃宗,李亨,大历,");
        sbd.append("780,4,0,唐,德宗,李适,建中,");
        sbd.append("784,1,0,唐,德宗,李适,兴元,");
        sbd.append("785,21,0,唐,德宗,李适,贞元,");
        sbd.append("805,1,0,唐,顺宗,李诵,永贞,");
        sbd.append("806,15,0,唐,宪宗,李纯,元和,");
        sbd.append("821,4,0,唐,穆宗,李恒,长庆,");
        sbd.append("825,3,0,唐,敬宗,李湛,宝历,");
        sbd.append("827,9,0,唐,文宗,李昂,大和,");
        sbd.append("836,5,0,唐,文宗,李昂,开成,");
        sbd.append("841,6,0,唐,武宗,李炎,会昌,");
        sbd.append("847,14,0,唐,宣宗,李忱,大中,");
        sbd.append("860,15,0,唐,宣宗,李忱,咸通,");
        sbd.append("874,6,0,唐,僖宗,李儇,乾符,");
        sbd.append("880,2,0,唐,僖宗,李儇,广明,");
        sbd.append("881,5,0,唐,僖宗,李儇,中和,");
        sbd.append("885,4,0,唐,僖宗,李儇,光启,");
        sbd.append("888,1,0,唐,僖宗,李儇,文德,");
        sbd.append("889,1,0,唐,昭宗,李晔,龙纪,");
        sbd.append("890,2,0,唐,昭宗,李晔,大顺,");
        sbd.append("892,2,0,唐,昭宗,李晔,景福,");
        sbd.append("894,5,0,唐,昭宗,李晔,乾宁,");
        sbd.append("898,4,0,唐,昭宗,李晔,光化,");
        sbd.append("901,4,0,唐,昭宗,李晔,天复,");
        sbd.append("904,1,0,唐,昭宗,李晔,天佑,");
        sbd.append("905,3,1,唐,昭宣帝,李祝,天佑,");
        sbd.append("907,5,0,五代/梁,太祖,朱温,开平,");
        sbd.append("911,2,0,五代/梁,太祖,朱温,乾化,");
        sbd.append("913,1,0,五代/梁,庶人,朱友珪,鳳曆,");
        sbd.append("913,3,2,五代/梁,末帝,朱友贞,乾化,");
        sbd.append("915,7,0,五代/梁,末帝,朱友贞,贞明,");
        sbd.append("921,3,0,五代/梁,末帝,朱友贞,龙德,");
        sbd.append("923,4,0,五代/唐,庄宗,李存勗,同光,");
        sbd.append("926,5,0,五代/唐,明宗,李嗣源,天成,");
        sbd.append("930,4,0,五代/唐,明宗,李嗣源,长兴,");
        sbd.append("934,1,0,五代/唐,闵帝,李从厚,应顺,");
        sbd.append("934,3,0,五代/唐,潞王,李从珂,清泰,");
        sbd.append("936,6,0,五代/晋,高祖,石敬瑭,天福,");
        sbd.append("942,2,6,五代/晋,出帝,石重贵,天福,");
        sbd.append("944,3,0,五代/晋,出帝,石重贵,开运,");
        sbd.append("947,12,0,五代/汉,高祖,刘知远,天福,");
        sbd.append("948,1,0,五代/汉,隐帝,刘承祐,乾祐,");
        sbd.append("948,3,0,五代/汉,隐帝,刘承祐,乾祐,");
        sbd.append("951,3,0,五代/周,太祖,郭威,广顺,");
        sbd.append("954,1,0,五代/周,太祖,郭威,显德,");
        sbd.append("954,6,0,五代/周,世宗,柴荣,显德,");
        sbd.append("959,2,5,五代/周,恭帝,郭宗训,显德,");
        sbd.append("960,4,0,北宋,太祖,赵匡胤,建隆,");
        sbd.append("963,6,0,北宋,太祖,赵匡胤,乾德,");
        sbd.append("968,9,0,北宋,太祖,赵匡胤,开宝,");
        sbd.append("976,9,0,北宋,太宗,赵炅,太平兴国,");
        sbd.append("984,4,0,北宋,太宗,赵炅,雍熙,");
        sbd.append("988,2,0,北宋,太宗,赵炅,端拱,");
        sbd.append("990,5,0,北宋,太宗,赵炅,淳化,");
        sbd.append("995,3,0,北宋,太宗,赵炅,至道,");
        sbd.append("998,6,0,北宋,真宗,赵恒,咸平,");
        sbd.append("1004,4,0,北宋,真宗,赵恒,景德,");
        sbd.append("1008,9,0,北宋,真宗,赵恒,大中祥符,");
        sbd.append("1017,5,0,北宋,真宗,赵恒,天禧,");
        sbd.append("1022,1,0,北宋,真宗,赵恒,乾兴,");
        sbd.append("1023,10,0,北宋,仁宗,赵祯,天圣,");
        sbd.append("1032,2,0,北宋,仁宗,赵祯,明道,");
        sbd.append("1034,5,0,北宋,仁宗,赵祯,景祐,");
        sbd.append("1038,3,0,北宋,仁宗,赵祯,宝元,");
        sbd.append("1040,2,0,北宋,仁宗,赵祯,康定,");
        sbd.append("1041,8,0,北宋,仁宗,赵祯,庆历,");
        sbd.append("1049,6,0,北宋,仁宗,赵祯,皇祐,");
        sbd.append("1054,3,0,北宋,仁宗,赵祯,至和,");
        sbd.append("1056,8,0,北宋,仁宗,赵祯,嘉祐,");
        sbd.append("1064,4,0,北宋,英宗,赵曙,治平,");
        sbd.append("1068,10,0,北宋,神宗,赵顼,熙宁,");
        sbd.append("1078,8,0,北宋,神宗,赵顼,元丰,");
        sbd.append("1086,9,0,北宋,哲宗,赵煦,元祐,");
        sbd.append("1094,5,0,北宋,哲宗,赵煦,绍圣,");
        sbd.append("1098,3,0,北宋,哲宗,赵煦,元符,");
        sbd.append("1101,1,0,北宋,徽宗,赵佶,建中靖国,");
        sbd.append("1102,5,0,北宋,徽宗,赵佶,崇宁,");
        sbd.append("1107,4,0,北宋,徽宗,赵佶,大观,");
        sbd.append("1111,8,0,北宋,徽宗,赵佶,政和,");
        sbd.append("1118,2,0,北宋,徽宗,赵佶,重和,");
        sbd.append("1119,7,0,北宋,徽宗,赵佶,宣和,");
        sbd.append("1126,2,0,北宋,钦宗,赵桓,靖康,");
        sbd.append("1127,4,0,南宋,高宗,赵构,建炎,");
        sbd.append("1131,32,0,南宋,高宗,赵构,绍兴,");
        sbd.append("1163,2,0,南宋,孝宗,赵慎,隆兴,");
        sbd.append("1165,9,0,南宋,孝宗,赵慎,乾道,");
        sbd.append("1174,16,0,南宋,孝宗,赵慎,淳熙,");
        sbd.append("1190,5,0,南宋,光宗,赵暴,绍熙,");
        sbd.append("1195,6,0,南宋,宁宗,赵扩,庆元,");
        sbd.append("1201,4,0,南宋,宁宗,赵扩,嘉泰,");
        sbd.append("1205,3,0,南宋,宁宗,赵扩,开禧,");
        sbd.append("1208,17,0,南宋,宁宗,赵扩,嘉定,");
        sbd.append("1225,3,0,南宋,理宗,赵昀,宝庆,");
        sbd.append("1228,6,0,南宋,理宗,赵昀,绍定,");
        sbd.append("1234,3,0,南宋,理宗,赵昀,端平,");
        sbd.append("1237,4,0,南宋,理宗,赵昀,嘉熙,");
        sbd.append("1241,12,0,南宋,理宗,赵昀,淳祐,");
        sbd.append("1253,6,0,南宋,理宗,赵昀,寶祐,");
        sbd.append("1259,1,0,南宋,理宗,赵昀,开庆,");
        sbd.append("1260,5,0,南宋,理宗,赵昀,景定,");
        sbd.append("1265,10,0,南宋,度宗,赵禥,咸淳,");
        sbd.append("1275,2,0,南宋,恭宗,赵㬎,德祐,");
        sbd.append("1276,3,0,南宋,端宗,赵昰,景炎,");
        sbd.append("1278,2,0,南宋,帝昺,赵昺,祥兴,");
        sbd.append("1271,24,7,元,世祖,孛儿只斤·忽必烈,至元,");
        sbd.append("1295,3,0,元,成宗,孛儿只斤·铁穆耳,元贞,");
        sbd.append("1297,11,0,元,成宗,孛儿只斤·铁穆耳,大德,");
        sbd.append("1308,4,0,元,武宗,孛儿只斤·海山,至大,");
        sbd.append("1312,2,0,元,仁宗,孛儿只斤·爱育黎拔力八达,皇庆,");
        sbd.append("1314,7,0,元,仁宗,孛儿只斤·愛育黎拔力八達,延祐,");
        sbd.append("1321,3,0,元,英宗,孛儿只斤·硕德八剌,至治,");
        sbd.append("1324,5,0,元,泰定帝,孛儿只斤·也孙铁木耳,泰定,");
        sbd.append("1328,1,0,元,泰定帝,孛儿只斤·也孙铁木耳,至和,");
        sbd.append("1328,1,0,元,幼主,孛儿只斤·阿速吉八,天顺,");
        sbd.append("1328,3,0,元,文宗,孛儿只斤·图贴睦尔,天历,");
        sbd.append("1330,3,0,元,文宗,孛儿只斤·图贴睦尔,至顺,");
        sbd.append("1333,3,0,元,惠宗,孛儿只斤·妥懽贴睦尔,元统,");
        sbd.append("1335,6,0,元,惠宗,孛儿只斤·妥懽贴睦尔,至元,");
        sbd.append("1341,28,0,元,惠宗,孛儿只斤·妥懽贴睦尔,至正,");
        sbd.append("1368,31,0,明,太祖,朱元璋,洪武,");
        sbd.append("1399,4,0,明,惠帝,朱允溫,建文,");
        sbd.append("1403,22,0,明,成祖,朱棣,永乐,");
        sbd.append("1425,1,0,明,仁宗,朱高炽,洪熙,");
        sbd.append("1426,10,0,明,宣宗,朱瞻基,宣德,");
        sbd.append("1436,14,0,明,英宗,朱祁镇,正统,");
        sbd.append("1450,7,0,明,代宗,朱祁钰,景泰,");
        sbd.append("1457,8,0,明,英宗,朱祁镇,天顺,");
        sbd.append("1465,23,0,明,宪宗,朱见深,成化,");
        sbd.append("1488,18,0,明,孝宗,朱祐樘,弘治,");
        sbd.append("1506,16,0,明,武宗,朱厚照,正德,");
        sbd.append("1522,45,0,明,世宗,朱厚熜,嘉靖,");
        sbd.append("1567,6,0,明,穆宗,朱载贺,隆庆,");
        sbd.append("1573,48,0,明,神宗,朱翊钧,万历,");
        sbd.append("1620,1,0,明,光宗,朱常洛,泰昌,");
        sbd.append("1621,7,0,明,熹宗,朱同校,天启,");
        sbd.append("1628,17,0,明,毅宗,朱由检,崇祯,");
        sbd.append("1644,18,0,清,世祖,爱新觉罗福临,顺治,");
        sbd.append("1662,61,0,清,圣祖,爱新觉罗玄烨,康熙,");
        sbd.append("1723,13,0,清,世宗,爱新觉罗胤禛,雍正,");
        sbd.append("1736,60,0,清,高宗,爱新觉罗弘历,乾隆,");
        sbd.append("1796,25,0,清,仁宗,爱新觉罗颙琰,嘉庆,");
        sbd.append("1821,30,0,清,宣宗,爱新觉罗旻宁,道光,");
        sbd.append("1851,11,0,清,文宗,爱新觉罗奕詝,咸丰,");
        sbd.append("1862,13,0,清,穆宗,爱新觉罗载淳,同治,");
        sbd.append("1875,34,0,清,德宗,爱新觉罗载湉,光绪,");
        sbd.append("1909,3,0,清,无廟,爱新觉罗溥仪,宣统,");
        sbd.append("1912,37,0,近、现代,中华民国,,民国,");
        sbd.append("1949,9999,1948,当代,中国,,公历纪元");

        ObChronology.ChronologyTable = sbd.toString().split(",");
    }
}
