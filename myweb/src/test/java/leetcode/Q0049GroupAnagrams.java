package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q0049GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (null == strs) {
            return null;
        }
        Map<String, List<String>> map = Arrays.asList(strs).stream().collect(Collectors.groupingBy(item -> {
            char[] array = item.toCharArray();
            Arrays.sort(array);
            return new String(array);
        }));
        return new ArrayList<>(map.values());
    }
}
