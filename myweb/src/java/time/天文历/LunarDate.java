package time.天文历;

public class LunarDate {

	/** ob.d0 2000.0起算儒略日,北京时12:00 */
	private int dayRL;
	/** ob.di 所在公历月内日序数 */
	private int dayIndex;
	/** ob.y 所在公历年,同lun.y */
	private int year;
	/** ob.m 所在公历月,同lun.m */
	private int month;
	/** ob.d 日名称(公历) */
	private int day;
	/** ob.dn 所在公历月的总天数,同lun.dn */
	private int daysOfMonth;
	/** ob.week0 所在月的月首的星期,同lun.w0 */
	private int weekFirst;
	/** ob.week 星期 */
	private int week;
	/** ob.weeki 在本月中的周序号 */
	private int weekIndex;
	/** ob.weekN 本月的总周数 */
	private int weeksOfMonth;
	/** ob.Hyear 年(回历) */
	private int hYear;
	/** ob.Hmonth 月(回历) */
	private int hMonth;
	/** ob.Hday 日(回历) */
	private int hDay;
	/** ob.Lmc 月名称 */
	private String lunarMonthName;
	/** ob.Ldi 距农历月首的编移量,0对应初一 */
	private int lunarMonthOffset;
	/** ob.Ldc 日名称 */
	private String lunarDayName;
	/** ob.Ldn 月大小 */
	private int daysofLunarMonth;
	/** ob.Lleap 闰状况(值为'闰'或空串) */
	private String lunarLunarLeap;
	/** ob.Lmc2 下个月名称,判断除夕时要用到 */
	private String nextLunarMonthName;
	/** ob.Ljq 节气 */
	private String lunarSolarTerm;
	/** ob.cur_dz 距冬至的天数 */
	private int daysToDZ;
	/** ob.cur_xz 距夏至的天数 */
	private int daysToXZ;
	/** ob.cur_lq 距立秋的天数 */
	private int daysToLQ;
	/** ob.cur_mz 距芒种的天数 */
	private int daysToMZ;
	/** ob.cur_xs 距小暑的天数 */
	private int daysToXS;
	/** ob.Lyear2, ob.Lyear3 干支年. Lyear2和Lyear3的区别在于新年的判定, Lyear2是立春, Lyear3则是春节[正月] */
	private String cnEraYear;
	/** ob.Lmonth2 干支月 */
	private String cnEraMonth;
	/** ob.Lday2 干支日 */
	private String cnEraDay;
	/** ob.Ltime2 干支时 */
	private String cnEraTime;
	/** ob.XiZ 星座 */
	private String constellation;
	/** ob.Lyear 农历年 */
	private int lunarYear;
	/** ob.Lmonth 农历月 */
	private int lunarMonth;
	/** ob.yxmc 月相名称 */
	private String moonPhaseName;
	/** ob.yxjd 月相时刻(儒略日) */
	private double moonPhaseTime;
	/** ob.yxsj 月相时间串 */
	private String moonPhaseTimeStr;
	/** ob.jqmc 节气名称 */
	private String solarTermName;
	/** ob.jqjd 节气时刻(儒略日) */
	private double solarTermTime;
	/** ob.jqsj 节气时间串 */
	private String solarTermTimeStr;
	/** ob.Lyear4 黄帝纪年 */
	private int kingYear;

	/** r.A 重要喜庆日子名称(可将日子名称置红) */
	private String impHappyName;
	/** r.B 重要日子名称 */
	private String impName;
	/** r.C 各种日子名称(连成一大串) */
	private String allName;
	/** r.Fjia 放假日子(可用于日期数字置红) */
	private int holiday;

	/** ob.d0 2000.0起算儒略日,北京时12:00 */
	public int getDayRL() {
		return dayRL;
	}

	/** ob.d0 2000.0起算儒略日,北京时12:00 */
	public void setDayRL(int dayRL) {
		this.dayRL = dayRL;
	}

	/** ob.di 所在公历月内日序数 */
	public int getDayIndex() {
		return dayIndex;
	}

	/** ob.di 所在公历月内日序数 */
	public void setDayIndex(int dayIndex) {
		this.dayIndex = dayIndex;
	}

	/** ob.y 所在公历年,同lun.y */
	public int getYear() {
		return year;
	}

	/** ob.y 所在公历年,同lun.y */
	public void setYear(int year) {
		this.year = year;
	}

	/** ob.m 所在公历月,同lun.m */
	public int getMonth() {
		return month;
	}

	/** ob.m 所在公历月,同lun.m */
	public void setMonth(int month) {
		this.month = month;
	}

	/** ob.d 日名称(公历) */
	public int getDay() {
		return day;
	}

	/** ob.d 日名称(公历) */
	public void setDay(int day) {
		this.day = day;
	}

	/** ob.dn 所在公历月的总天数,同lun.dn */
	public int getDaysOfMonth() {
		return daysOfMonth;
	}

	/** ob.dn 所在公历月的总天数,同lun.dn */
	public void setDaysOfMonth(int daysOfMonth) {
		this.daysOfMonth = daysOfMonth;
	}

	/** ob.week0 所在月的月首的星期,同lun.w0 */
	public int getWeekFirst() {
		return weekFirst;
	}

	/** ob.week0 所在月的月首的星期,同lun.w0 */
	public void setWeekFirst(int weekFirst) {
		this.weekFirst = weekFirst;
	}

	/** ob.week 星期 */
	public int getWeek() {
		return week;
	}

	/** ob.week 星期 */
	public void setWeek(int week) {
		this.week = week;
	}

	/** ob.weeki 在本月中的周序号 */
	public int getWeekIndex() {
		return weekIndex;
	}

	/** ob.weeki 在本月中的周序号 */
	public void setWeekIndex(int weekIndex) {
		this.weekIndex = weekIndex;
	}

	/** ob.weekN 本月的总周数 */
	public int getWeeksOfMonth() {
		return weeksOfMonth;
	}

	/** ob.weekN 本月的总周数 */
	public void setWeeksOfMonth(int weeksOfMonth) {
		this.weeksOfMonth = weeksOfMonth;
	}

	/** 回曆年 */
	public int gethYear() {
		return hYear;
	}

	/** 回曆年 */
	public void sethYear(int hYear) {
		this.hYear = hYear;
	}

	/** 回曆月 */
	public int gethMonth() {
		return hMonth;
	}

	/** 回曆月 */
	public void sethMonth(int hMonth) {
		this.hMonth = hMonth;
	}

	/** 回曆日 */
	public int gethDay() {
		return hDay;
	}

	/** 回曆日 */
	public void sethDay(int hDay) {
		this.hDay = hDay;
	}

	/** ob.Lmc 月名称 */
	public String getLunarMonthName() {
		return lunarMonthName;
	}

	/** ob.Lmc 月名称 */
	public void setLunarMonthName(String lunarMonthName) {
		this.lunarMonthName = lunarMonthName;
	}

	/** ob.Ldi 距农历月首的编移量,0对应初一 */
	public int getLunarMonthOffset() {
		return lunarMonthOffset;
	}

	/** ob.Ldi 距农历月首的编移量,0对应初一 */
	public void setLunarMonthOffset(int lunarMonthOffset) {
		this.lunarMonthOffset = lunarMonthOffset;
	}

	/** ob.Ldc 日名称 */
	public String getLunarDayName() {
		return lunarDayName;
	}

	/** ob.Ldc 日名称 */
	public void setLunarDayName(String lunarDayName) {
		this.lunarDayName = lunarDayName;
	}

	/** ob.Ldn 月大小 */
	public int getDaysofLunarMonth() {
		return daysofLunarMonth;
	}

	/** ob.Ldn 月大小 */
	public void setDaysofLunarMonth(int daysofLunarMonth) {
		this.daysofLunarMonth = daysofLunarMonth;
	}

	/** ob.Lleap 闰状况(值为'闰'或空串) */
	public String getLunarLunarLeap() {
		return lunarLunarLeap;
	}

	/** ob.Lleap 闰状况(值为'闰'或空串) */
	public void setLunarLunarLeap(String lunarLunarLeap) {
		this.lunarLunarLeap = lunarLunarLeap;
	}

	/** ob.Lmc2 下个月名称,判断除夕时要用到 */
	public String getNextLunarMonthName() {
		return nextLunarMonthName;
	}

	/** ob.Lmc2 下个月名称,判断除夕时要用到 */
	public void setNextLunarMonthName(String nextLunarMonthName) {
		this.nextLunarMonthName = nextLunarMonthName;
	}

	/** ob.Ljq 节气 */
	public String getLunarSolarTerm() {
		return lunarSolarTerm;
	}

	/** ob.Ljq 节气 */
	public void setLunarSolarTerm(String lunarSolarTerm) {
		this.lunarSolarTerm = lunarSolarTerm;
	}

	/** ob.cur_dz 距冬至的天数 */
	public int getDaysToDZ() {
		return daysToDZ;
	}

	/** ob.cur_dz 距冬至的天数 */
	public void setDaysToDZ(int daysToDZ) {
		this.daysToDZ = daysToDZ;
	}

	/** ob.cur_xz 距夏至的天数 */
	public int getDaysToXZ() {
		return daysToXZ;
	}

	/** ob.cur_xz 距夏至的天数 */
	public void setDaysToXZ(int daysToXZ) {
		this.daysToXZ = daysToXZ;
	}

	/** ob.cur_lq 距立秋的天数 */
	public int getDaysToLQ() {
		return daysToLQ;
	}

	/** ob.cur_lq 距立秋的天数 */
	public void setDaysToLQ(int daysToLQ) {
		this.daysToLQ = daysToLQ;
	}

	/** ob.cur_mz 距芒种的天数 */
	public int getDaysToMZ() {
		return daysToMZ;
	}

	/** ob.cur_mz 距芒种的天数 */
	public void setDaysToMZ(int daysToMZ) {
		this.daysToMZ = daysToMZ;
	}

	/** ob.cur_xs 距小暑的天数 */
	public int getDaysToXS() {
		return daysToXS;
	}

	/** ob.cur_xs 距小暑的天数 */
	public void setDaysToXS(int daysToXS) {
		this.daysToXS = daysToXS;
	}

	/** ob.Lyear2, ob.Lyear3 干支年. Lyear2和Lyear3的区别在于新年的判定, Lyear2是立春, Lyear3则是春节[正月] */
	public String getCnEraYear() {
		return cnEraYear;
	}

	/** ob.Lyear2, ob.Lyear3 干支年. Lyear2和Lyear3的区别在于新年的判定, Lyear2是立春, Lyear3则是春节[正月] */
	public void setCnEraYear(String cnEraYear) {
		this.cnEraYear = cnEraYear;
	}

	/** ob.Lmonth2 干支月 */
	public String getCnEraMonth() {
		return cnEraMonth;
	}

	/** ob.Lmonth2 干支月 */
	public void setCnEraMonth(String cnEraMonth) {
		this.cnEraMonth = cnEraMonth;
	}

	/** ob.Lday2 干支日 */
	public String getCnEraDay() {
		return cnEraDay;
	}

	/** ob.Lday2 干支日 */
	public void setCnEraDay(String cnEraDay) {
		this.cnEraDay = cnEraDay;
	}

	/** ob.Ltime2 干支时 */
	public String getCnEraTime() {
		return cnEraTime;
	}

	/** ob.Ltime2 干支时 */
	public void setCnEraTime(String cnEraTime) {
		this.cnEraTime = cnEraTime;
	}

	/** ob.XiZ 星座 */
	public String getConstellation() {
		return constellation;
	}

	/** ob.XiZ 星座 */
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	/** ob.Lyear 农历年 */
	public int getLunarYear() {
		return lunarYear;
	}

	/** ob.Lyear 农历年 */
	public void setLunarYear(int lunarYear) {
		this.lunarYear = lunarYear;
	}

	/** ob.Lmonth 农历月 */
	public int getLunarMonth() {
		return lunarMonth;
	}

	/** ob.Lmonth 农历月 */
	public void setLunarMonth(int lunarMonth) {
		this.lunarMonth = lunarMonth;
	}

	/** ob.yxmc 月相名称 */
	public String getMoonPhaseName() {
		return moonPhaseName;
	}

	/** ob.yxmc 月相名称 */
	public void setMoonPhaseName(String moonPhaseName) {
		this.moonPhaseName = moonPhaseName;
	}

	/** ob.yxjd 月相时刻(儒略日) */
	public double getMoonPhaseTime() {
		return moonPhaseTime;
	}

	/** ob.yxjd 月相时刻(儒略日) */
	public void setMoonPhaseTime(double moonPhaseTime) {
		this.moonPhaseTime = moonPhaseTime;
	}

	/** ob.yxsj 月相时间串 */
	public String getMoonPhaseTimeStr() {
		return moonPhaseTimeStr;
	}

	/** ob.yxsj 月相时间串 */
	public void setMoonPhaseTimeStr(String moonPhaseTimeStr) {
		this.moonPhaseTimeStr = moonPhaseTimeStr;
	}

	/** ob.jqmc 节气名称 */
	public String getSolarTermName() {
		return solarTermName;
	}

	/** ob.jqmc 节气名称 */
	public void setSolarTermName(String solarTermName) {
		this.solarTermName = solarTermName;
	}

	/** ob.jqjd 节气时刻(儒略日) */
	public double getSolarTermTime() {
		return solarTermTime;
	}

	/** ob.jqjd 节气时刻(儒略日) */
	public void setSolarTermTime(double solarTermTime) {
		this.solarTermTime = solarTermTime;
	}

	/** ob.jqsj 节气时间串 */
	public String getSolarTermTimeStr() {
		return solarTermTimeStr;
	}

	/** ob.jqsj 节气时间串 */
	public void setSolarTermTimeStr(String solarTermTimeStr) {
		this.solarTermTimeStr = solarTermTimeStr;
	}

	/** ob.Lyear4 黄帝纪年 */
	public int getKingYear() {
		return kingYear;
	}

	/** ob.Lyear4 黄帝纪年 */
	public void setKingYear(int kingYear) {
		this.kingYear = kingYear;
	}

	/** r.A 重要喜庆日子名称(可将日子名称置红) */
	public String getImpHappyName() {
		return impHappyName;
	}

	/** r.A 重要喜庆日子名称(可将日子名称置红) */
	public void setImpHappyName(String impHappyName) {
		this.impHappyName = impHappyName;
	}

	/** r.B 重要日子名称 */
	public String getImpName() {
		return impName;
	}

	/** r.B 重要日子名称 */
	public void setImpName(String impName) {
		this.impName = impName;
	}

	/** r.C 各种日子名称(连成一大串) */
	public String getAllName() {
		return allName;
	}

	/** r.C 各种日子名称(连成一大串) */
	public void setAllName(String allName) {
		this.allName = allName;
	}

	/** r.Fjia 放假日子(可用于日期数字置红) */
	public int getHoliday() {
		return holiday;
	}

	/** r.Fjia 放假日子(可用于日期数字置红) */
	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}
}
