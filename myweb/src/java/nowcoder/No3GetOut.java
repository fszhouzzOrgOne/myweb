package nowcoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class No3GetOut {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int count = in.nextInt();
            int no = getLastNo(count);
            System.out.println(no);
        }
    }

    private static int getLastNo(int count) {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < count; i++) {
            list.add(i + 1);
        }
        int index = 0;
        while (list.size() > 1) {
            index = (index + 1) % list.size();
            index = (index + 1) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }

}
