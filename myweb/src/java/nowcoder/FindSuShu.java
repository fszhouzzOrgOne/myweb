package nowcoder;

import java.util.ArrayList;
import java.util.List;

public class FindSuShu {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 600; i <= 800; i++) {
            if (checkSushu(i)) {
                nums.add(i);
            }
        }
        System.out.println(nums);
    }

    private static boolean checkSushu(int num) {
        for (int i = 2; i <= num / 2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
