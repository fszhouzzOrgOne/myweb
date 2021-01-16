package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 列表工具
 * 
 * @author fszhouzz@qq.com
 * @time 2021年1月16日 下午7:57:25
 */
public class ListUtil {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        Map<String, Integer> weights = null;
        list.add("2");
        list.add("3");
        generateAllPermutationTest(list, weights);
    }
    
    // 得到列表的全排列測試
    public static void generateAllPermutationTest(List<String> list, 
            Map<String, Integer> weights) {
        ArrayList<String> tmp1 = generateFirstPermutation(list, weights);
        ArrayList<String> tmp2 = generateLastPermutation(list, weights);
        while (null != tmp1) {
            System.out.println(tmp1 + " " + tmp2);
            tmp1 = generateNextPermutation(tmp1, weights);
            tmp2 = generatePreviousPermutation(tmp2, weights);
        }
    }

    /**
     * 得到列表的全排列1：第一個，再找下一個
     */
    public static List<ArrayList<String>> generateAllPermutation1(
            List<String> list, Map<String, Integer> weights) {
        if (isEmpty(list)) {
            return null;
        }
        List<ArrayList<String>> perms = new ArrayList<ArrayList<String>>();
        ArrayList<String> tmp = generateFirstPermutation(list, weights);
        while (null != tmp) {
            perms.add(tmp);
            tmp = generateNextPermutation(tmp, weights);
        }
        return perms;
    }

    /**
     * 得到列表的全排列2：最後一個，再找上一個
     */
    public static List<ArrayList<String>> generateAllPermutation2(
            List<String> list, Map<String, Integer> weights) {
        if (isEmpty(list)) {
            return null;
        }
        List<ArrayList<String>> perms = new ArrayList<ArrayList<String>>();
        ArrayList<String> tmp = generateLastPermutation(list, weights);
        while (null != tmp) {
            perms.add(tmp);
            tmp = generatePreviousPermutation(tmp, weights);
        }
        return perms;
    }

    /**
     * 獲取下一個排列：<br/>
     * 從後面開始找，找到第一個下降，假設是ab，<br/>
     * 找a後面比a大的最小的一個，假設是d，和a交换位置，<br/>
     * 把a原來位置，就是d的新位置，它後面的所有元素升序排列
     */
    private static ArrayList<String> generateNextPermutation(
            ArrayList<String> list, Map<String, Integer> weights) {
        if (isEmpty(list) || list.size() < 2) {
            return list;
        }
        ComparatorByWeights compa = new ComparatorByWeights(weights);
        // 後面開始找，找到第一個下降
        int currIndex = list.size() - 1;
        int previous = currIndex - 1;
        while (previous >= 0 && compa.compare(list.get(previous),
                list.get(currIndex)) >= 0) {
            currIndex--;
            previous--;
        }
        // 直到最前面還沒有找到，退出
        if (previous < 0) {
            return null;
        }
        // 後面比previous位置大的最小的一個
        int targetIndex = -1;
        for (int i = list.size() - 1; i > previous; i--) {
            if (compa.compare(list.get(previous), list.get(i)) < 0) {
                if (targetIndex < 0 || compa.compare(list.get(i),
                        list.get(targetIndex)) < 0) {
                    targetIndex = i;
                }
            }
        }
        // previous和targetIndex處的值，交换位置
        ArrayList<String> tmp = new ArrayList<String>(list);
        String str = tmp.get(previous);
        tmp.set(previous, tmp.get(targetIndex));
        tmp.set(targetIndex, str);
        // previous以後的升序排列
        if (previous >= tmp.size() - 2) {
            return tmp;
        }
        ArrayList<String> res = new ArrayList<String>();
        List<String> res1 = tmp.subList(0, previous + 1);
        ArrayList<String> res2 = generateFirstPermutation(
                tmp.subList(previous + 1, tmp.size()), weights);
        res.addAll(res1);
        res.addAll(res2);
        return res;
    }

    private static ArrayList<String> generateFirstPermutation(List<String> list,
            Map<String, Integer> weights) {
        ArrayList<String> tmp = new ArrayList<String>(list);
        ComparatorByWeights compa = new ComparatorByWeights(weights);
        Collections.sort(tmp, compa);
        return tmp;
    }

    private static ArrayList<String> generatePreviousPermutation(
            ArrayList<String> list, Map<String, Integer> weights) {
        if (isEmpty(list) || list.size() < 2) {
            return list;
        }
        ComparatorByWeights compa = new ComparatorByWeights(weights);
        // 後面開始找，找到第一個上升
        int currIndex = list.size() - 1;
        int previous = currIndex - 1;
        while (previous >= 0 && compa.compare(list.get(previous),
                list.get(currIndex)) <= 0) {
            currIndex--;
            previous--;
        }
        // 直到最前面還沒有找到，退出
        if (previous < 0) {
            return null;
        }
        // 後面比previous位置小的最大的一個
        int targetIndex = -1;
        for (int i = list.size() - 1; i > previous; i--) {
            if (compa.compare(list.get(previous), list.get(i)) > 0) {
                if (targetIndex < 0 || compa.compare(list.get(i),
                        list.get(targetIndex)) > 0) {
                    targetIndex = i;
                }
            }
        }
        // previous和targetIndex處的值，交换位置
        ArrayList<String> tmp = new ArrayList<String>(list);
        String str = tmp.get(previous);
        tmp.set(previous, tmp.get(targetIndex));
        tmp.set(targetIndex, str);
        // previous以後的降序排列
        if (previous >= tmp.size() - 2) {
            return tmp;
        }
        ArrayList<String> res = new ArrayList<String>();
        List<String> res1 = tmp.subList(0, previous + 1);
        ArrayList<String> res2 = generateLastPermutation(
                tmp.subList(previous + 1, tmp.size()), weights);
        res.addAll(res1);
        res.addAll(res2);
        return res;
    }

    private static ArrayList<String> generateLastPermutation(List<String> list,
            Map<String, Integer> weights) {
        ArrayList<String> tmp = new ArrayList<String>(list);
        ComparatorByWeightsReversed compa = new ComparatorByWeightsReversed(weights);
        Collections.sort(tmp, compa);
        return tmp;
    }

    public static boolean isEmpty(List<String> list) {
        return null == list || list.isEmpty();
    }

    /**
     * 字符串值大小的比較器，按權重映射比較
     */
    static class ComparatorByWeights implements Comparator<String> {

        private Map<String, Integer> weights = null;

        public ComparatorByWeights(Map<String, Integer> weights) {
            this.weights = weights;
        }

        @Override
        public int compare(String str1, String str2) {
            if (null == str1 && null == str2) {
                return 1;
            }
            if (null == str1) {
                return -1;
            }
            if (null == str2) {
                return 1;
            }
            if (null == weights) {
                return str1.compareTo(str2);
            }
            Integer w1 = weights.get(str1);
            Integer w2 = weights.get(str2);
            if (null == w1 && null == w2) {
                return str1.compareTo(str2);
            }
            if (null == w1) {
                return -1;
            }
            if (null == w2) {
                return 1;
            }
            return w1.compareTo(w2);
        }
    }
    

    /**
     * 字符串值大小的比較器，按權重映射比較
     */
    static class ComparatorByWeightsReversed implements Comparator<String> {

        private Map<String, Integer> weights = null;

        public ComparatorByWeightsReversed(Map<String, Integer> weights) {
            this.weights = weights;
        }

        @Override
        public int compare(String str1, String str2) {
            if (null == str1 && null == str2) {
                return 1;
            }
            if (null == str1) {
                return 1;
            }
            if (null == str2) {
                return -1;
            }
            if (null == weights) {
                return str2.compareTo(str1);
            }
            Integer w1 = weights.get(str1);
            Integer w2 = weights.get(str2);
            if (null == w1 && null == w2) {
                return str2.compareTo(str1);
            }
            if (null == w1) {
                return 1;
            }
            if (null == w2) {
                return -1;
            }
            return w2.compareTo(w1);
        }
    }
}
