package id;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ID62Test {
    private static String w = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static List<String> list = new ArrayList<String>();
    private static final int TIME_MIN_LEN;
    static {
        for (Character cha : w.toCharArray()) {
            list.add(cha.toString());
        }
        int len = 10;
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            cal.setTime(sdf.parse("99991231235959999"));
            len = toRadixString(cal.getTimeInMillis()).length();
        } catch (Exception e) {
        }
        TIME_MIN_LEN = len;
        System.out.println(
                "radix: " + list.size() + ", TIME_MIN_LEN=" + TIME_MIN_LEN);
    }

    public static void main(String[] args) {
        for (String id : ID15s(200)) {
            System.out.println(id);
        }
    }

    public static List<String> ID15s(int count) {
        return IDs(15, count);
    }

    public static List<String> IDs(int len, int count) {
        if (count < 1) {
            return null;
        }
        Set<String> set = new LinkedHashSet<String>();
        while (set.size() < count) {
            String id = ID(len);
            if (!set.contains(id)) {
                set.add(id);
            }
        }
        return new ArrayList<String>(set);
    }

    public static String ID15() {
        return ID(15);
    }

    public static String ID(int len) {
        if (len < TIME_MIN_LEN) {
            throw new IllegalArgumentException("ID长度不能小于" + TIME_MIN_LEN);
        }
        Calendar cal = Calendar.getInstance();
        Long time = cal.getTimeInMillis();
        String res = toRadixString(time);
        while (res.length() < TIME_MIN_LEN) {
            res = list.get(0) + res;
        }
        while (res.length() < len) {
            Random rd = new Random();
            res = list.get(rd.nextInt(w.length())) + res;
        }
        return res;
    }

    /** 转 17IFMEwyspe-HJr */
    public static String toRadixString(Long time) {
        if (null == time) {
            return null;
        }
        long temp = time.longValue();
        if (0 == temp) {
            return list.get(0);
        }
        String res = null;
        while (temp != 0) {
            Long remainder = temp % w.length();
            String get = list.get(remainder.intValue());
            res = (null == res) ? get : get + res;
            temp = temp / w.length();
        }
        return res;
    }
}