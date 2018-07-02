package time.xiali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NongLiTest {

    private static final String[] chinaMoons = { "正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月",
            "十二月" };
    private static final String[] chinaMoonDays = { "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一",
            "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七",
            "二十八", "二十九", "三十" };

    public static void main(String[] args) {
        String date = "19900211";
        String str = NongLi.getDate(date);
        System.out.println(str);

        List<String> mons = new ArrayList<String>(Arrays.asList(chinaMoons));
        mons.add("一月");

        for (String moon : mons) {
            for (String day : chinaMoonDays) {
                System.out.println(moon + day);
                System.out.println(moon + day + "日");
                System.out.println("閏" + moon + day);
                System.out.println("閏" + moon + day + "日");
                System.out.println("闰" + moon + day);
                System.out.println("闰" + moon + day + "日");
            }
        }
    }

}
