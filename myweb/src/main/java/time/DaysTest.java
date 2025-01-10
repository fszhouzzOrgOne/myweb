package time;

import java.util.ArrayList;
import java.util.List;

public class DaysTest {
    private static final List<String> mons = new ArrayList<String>();
    private static final List<String> days = new ArrayList<String>();

    public static void main(String[] args) {
        for (String m : mons) {
            for (String d : days) {
                System.out.println(m + d);
            }
        }
    }

    static {
        mons.add("正月");
        mons.add("二月");
        mons.add("三月");
        mons.add("四月");
        mons.add("五月");
        mons.add("六月");
        mons.add("七月");
        mons.add("八月");
        mons.add("九月");
        mons.add("十月");
        mons.add("十一月");
        mons.add("十二月");
        days.add("初一");
        days.add("初二");
        days.add("初三");
        days.add("初四");
        days.add("初五");
        days.add("初六");
        days.add("初七");
        days.add("初八");
        days.add("初九");
        days.add("初十");
        days.add("十一");
        days.add("十二");
        days.add("十三");
        days.add("十四");
        days.add("十五");
        days.add("十六");
        days.add("十七");
        days.add("十八");
        days.add("十九");
        days.add("二十");
        days.add("二十一");
        days.add("二十二");
        days.add("二十三");
        days.add("二十四");
        days.add("二十五");
        days.add("二十六");
        days.add("二十七");
        days.add("二十八");
        days.add("二十九");
        days.add("三十");
    }
}
