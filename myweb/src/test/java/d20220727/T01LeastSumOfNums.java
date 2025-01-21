package d20220727;

import java.util.Scanner;

/**
 * 字符串中包含的数字部分的最小和：<br/>
 * 输入只有0-9a-zA-Z +-。<br/>
 * bb12-34aa，输出-31。<br/>
 * abc1234cd，输出10 <br/>
 * abc12+34cd，输出10 <br/>
 */
public class T01LeastSumOfNums {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        sc.close();
        String[] parts = line.split("[^0-9-]+");
        System.out.println(leastSumOfNums(parts));
    }

    private static int leastSumOfNums(String[] parts) {
        int res = 0;
        for (String par : parts) {
            if (par == null || par.trim().length() < 1) {
                continue;
            }
            String tmp = par.replaceAll("\\+", "");
            if (tmp.length() < 1) {
                continue;
            }
            if (tmp.indexOf("-") == -1) {
                res += sumNumByNum(tmp);
            } else {
                int index = tmp.indexOf("-");
                if (index > 0) {
                    res += sumNumByNum(tmp.substring(0, index));
                }
                String[] suffixes = tmp.substring(index).split("-");
                for (String suffix : suffixes) {
                    if (suffix == null || suffix.length() < 1) {
                        continue;
                    }
                    res -= Integer.parseInt(suffix);
                }
            }
        }
        return res;
    }

    private static int sumNumByNum(String tmp) {
        int res = 0;
        String[] nums = tmp.split("");
        for (String num : nums) {
            res += Integer.parseInt(num);
        }
        return res;
    }
}
