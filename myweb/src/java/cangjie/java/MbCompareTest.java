package cangjie.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cangjie.java.util.IOUtils;
import unicode.UnicodeConvertUtil;
import unicode.UnicodeHanziUtil;

/**
 * 碼表對比
 */
public class MbCompareTest {

    public static String mbsBaseDir = "src\\java\\cangjie\\mb\\";
    public static String mb68000 = mbsBaseDir + "cjmb" + File.separator + "oldmb" + File.separator + "cj6-8300.txt";
    public static String mb610000 = mbsBaseDir + "cjmb" + File.separator + "oldmb" + File.separator + "cj6-13053.txt";
    public static String mb620000 = mbsBaseDir + "cjmb" + File.separator + "oldmb" + File.separator + "cj6-20902.txt";
    public static String mb670000 = mbsBaseDir + "cjmb" + File.separator + "oldmb" + File.separator + "cj6-70000.txt";

    public static void main(String[] args) throws Exception {
        Set<String> list1 = new LinkedHashSet<String>(
                IOUtils.readLines(Cj00AllInOneTest.mb6newDict, true));

        Set<String> list2 = new LinkedHashSet<String>();
        List<String> listMore = IOUtils.readLines(Cj00AllInOneTest.mb6more,
                true);
        // 摸否有重複行
        for (String line : listMore) {
            if (!list2.contains(line)) {
                list2.add(line);
            } else {
                System.out.println("more: " + line);
            }
        }
        list2.addAll(IOUtils.readLines(Cj00AllInOneTest.mb6morePua, true));

        // 六代碼表沒有的編碼
        List<String> list3 = compareGetDiff(list1, list2);
        System.out.println("compareGetDiff: " + list3.size());

        // 六代碼表已有的碼表
        Set<String> listExist = getIntersection(list1, list2);
        System.out.println(
                "getIntersection: " + listExist.size() + ", " + listExist);

        // 各個區漢字是否都有了：
        Set<String> chars = new LinkedHashSet<String>();
        for (String line : list1) {
            if (line.contains(" ")) {
                String[] splits = line.split(" +");
                String cha = splits[1];
                chars.add(cha);
            }
        }
        Set<String> set = UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.baseRange);
        set.addAll(UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.base2Range));
        set.addAll(UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.AextRange));
        set.addAll(UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.BextRange));
        set.addAll(UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.CextRange));
        set.addAll(UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.DextRange));
        set.addAll(UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.EextRange));
        set.addAll(UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.FextRange));
        set.addAll(UnicodeConvertUtil.getStringSet(UnicodeHanziUtil.GextRange));
        set.removeAll(chars);
        System.out.println(set);
        String res = "";
        for (String str : set) {
            res += str;
        }
        System.out.println(res);
    }

    /**
     * 求碼表的交集
     */
    public static Set<String> getIntersection(Collection<String>... sets) {
        Set<String> inters = new LinkedHashSet<String>();
        if (null != sets) {
            List<Set<String>> sets2 = new ArrayList<Set<String>>();
            for (Collection<String> set : sets) {
                sets2.add(new HashSet<String>(set));
            }
            for (Collection<String> set : sets2) {
                for (String code : set) {
                    if (!inters.contains(code) && isInAllSet(code, sets2)) {
                        inters.add(code);
                    }
                }
            }
        }
        return inters;
    }

    /** 一個编码是否在所有集合中都含有 */
    private static boolean isInAllSet(String code, List<Set<String>> sets) {
        if (null != sets && null != code && code.length() > 0) {
            for (Set<String> set : sets) {
                if (!set.contains(code)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较碼表的不同，newSet中有，而set1中沒有的行
     * 
     * @author t
     * @time 2017-1-3上午12:02:52
     */
    public static List<String> compareGetDiff(Collection<String> set1, Collection<String> newSet) throws Exception {
        List<String> diff = new ArrayList<String>();

        for (String n : newSet) {
            if (!set1.contains(n)) {
                diff.add(n);
            }
        }
        return diff;
    }

    /**
     * 比较碼表的不同：在碼表newSet中有，而在set1中沒有的字符
     */
    public static List<String> compareGetDiffChars(Collection<String> set1, Collection<String> newSet) {
        List<String> diff = new ArrayList<String>();

        Set<String> oldChars = new HashSet<String>();
        for (String line : set1) {
            if (line.contains(" ")) {
                String[] splits = line.split(" +");
                String cha = splits[1];
                oldChars.add(cha);
            }
        }

        for (String n : newSet) {
            if (n.contains(" ")) {
                String[] splits = n.split(" +");
                String cha = splits[1];
                if (!oldChars.contains(cha)) {
                    diff.add(n);
                }
            }
        }
        return diff;
    }
}
