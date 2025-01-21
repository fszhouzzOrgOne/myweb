package d20220709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * HJ16 购物单： <br/>
 * 想买的物品分为两类：主件与附件。<br/>
 * 如果要买归类为附件的物品，必须先买该附件所属的主件，且每件物品只能购买一次。<br/>
 * 输入总钱数、可买商品数、商品列表，计算可获得的最大的满意度。<br/>
 * 满意度：先求所有商品的各价格、重要度乘积，再求总和。<br/>
 * 如下输入，要输出2200：<br/>
1000 5
800 2 0
400 5 1
300 5 1
400 3 0
500 2 0
 * 如下输入，要输出130：<br/>
50 5
20 3 5
20 3 5
10 3 0
10 2 0
10 1 0
 * 
 * @author Administrator
 * @time 2022年7月10日下午4:02:18
 */
public class HJ016ShoppingList {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" +");
        int money = Integer.parseInt(line1[0]);
        int cnt = Integer.parseInt(line1[1]);
        List<Item> its = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            String[] line2 = br.readLine().split(" +");
            its.add(new Item(i + 1, Integer.parseInt(line2[0]), Integer.parseInt(line2[1]),
                    Integer.parseInt(line2[2])));
        }
        System.out.println(highestDegreOfSatisfaction(money, its));
    }

    private static Integer highestDegreOfSatisfaction(int money, List<Item> its) {

        return null;
    }

    // 商品
    private static class Item {
        // 编号
        private int id;
        // 价格
        private int price;
        // 重要度
        private int weight;
        // 主件编号，为0时，自己是主件
        private int mainId;

        public Item(int id, int price, int weight, int mainId) {
            super();
            this.id = id;
            this.price = price;
            this.weight = weight;
            this.mainId = mainId;
        }

        @Override
        public String toString() {
            return "Item [id=" + id + ", price=" + price + ", weight=" + weight + ", mainId=" + mainId + "]";
        }

    }
}
