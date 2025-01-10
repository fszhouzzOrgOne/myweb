package nowcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Biggest2InTheArray {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String dateStr = in.nextLine();
            String[] parts = dateStr.split(",");
            List<String> numList = Arrays.asList(parts);
            numList.sort(new Comparator<String>() {
                @Override
                public int compare(String str1, String str2) {
                    int num1 = Integer.parseInt(str1);
                    int num2 = Integer.parseInt(str2);
                    return num2 - num1;
                }
            });
            System.out.println(numList.get(0) + "," + numList.get(1));
        }
    }

}
