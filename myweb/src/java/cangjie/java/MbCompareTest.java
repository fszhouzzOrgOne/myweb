package cangjie.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cangjie.java.util.IOUtils;

/**
 * 碼表對比
 */
public class MbCompareTest {

    public static void main(String[] args) {
        List<String> list1 = IOUtils.readLines(Cj00AllInOneTest.mb6newDict);
        List<String> list2 = IOUtils.readLines(Cj00AllInOneTest.mb6more);
        ;
        List<String> list3 = new ArrayList<String>(getIntersection(list1, list2));
        for (String str : list3) {
            System.out.println(str);
        }
        System.out.println(list3.size());
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
