package dayan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayanqiuyiTest {

    // 天元1
    static int tianyuan = 1;

    public static void main(String[] args) {
        int row = 20;
        int column = 20;
        String[][] arrar = new String[row][column];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                int chenglv = getChenglyu(i, j);
                boolean correct = (i * chenglv) % j == 1;
                arrar[i - 1][j - 1] = (correct) ? chenglv + "" : " ";
            }
        }
        
        System.out.print(" , ");
        for (int j = 1; j <= column; j++) {
            System.out.print(j + ", ");
        }
        System.out.println();
        for (int i = 1; i <= row; i++) {
            System.out.print(i + ", ");
            for (int j = 1; j <= column; j++) {
                System.out.print(arrar[i - 1][j - 1] + ", ");
            }
            System.out.println();
        }

        getChenglyu(3, 4);
        System.out.println();
        getChenglyu(17, 7);
    }

    /**
     * @param yanshu
     *            衍數
     * @param dingmu
     *            定母
     */
    static int getChenglyu(int yanshu, int dingmu) {
        System.out.println("衍數" + yanshu + "定母" + dingmu + "：");
        int result = 0;
        List<Map<String, Object>> temps = new ArrayList<Map<String, Object>>();

        if (!(yanshu == dingmu || hasGongYue(yanshu, dingmu))) {
            boolean downward = true; // 以右上除右下
            int beichushu = dingmu;
            int chushu = yanshu;
            while (true) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("downward", downward);
                map.put("beichushu", beichushu);
                map.put("chushu", chushu);
                if (!(temps.isEmpty() && beichushu < chushu)) {
                    int tempRs = 0; // 商數
                    int tempyu = 0; // 奇數，卽餘數
                    if (chushu != 1) {
                        tempRs = beichushu / chushu;
                        tempyu = beichushu % chushu;
                    } else {
                        tempRs = beichushu - 1; // 任何數，以一除它，都奇一
                        tempyu = 1;
                    }
                    map.put("shang", tempRs);
                    map.put("yushu", tempyu);
                    temps.add(map);
                    // 如果是以右下除右上，且餘數为1，就結果
                    if (!downward && tempyu == 1) {
                        break;
                    }
                    beichushu = chushu;
                    chushu = tempyu;
                } else {
                    int temp = beichushu;
                    beichushu = chushu;
                    chushu = temp;
                    temps.add(map);
                }
                downward = !downward;
            }
            System.out.println("計算次數: " + temps.size());
            for (int i = 0; i < temps.size(); i++) {
                Map<String, Object> map = temps.get(i);
                System.out
                        .println("第"
                                + (i + 1)
                                + "次，"
                                + (((Boolean) map.get("downward") == true ? "右上除右下"
                                        : "右下除右上")
                                        + "，"
                                        + map.get("chushu")
                                        + "除"
                                        + map.get("beichushu") + (map
                                        .get("shang") == null ? "，不可除" : "，商为"
                                        + map.get("shang") + "餘"
                                        + map.get("yushu"))));
            }

            // 算结果
            int index = 0;
            if (null == temps.get(0).get("shang")) {
                index = 2;
            }
            int chengji = 1;
            for (; index < temps.size(); index++) {
                int tempShang = (Integer) temps.get(index).get("shang");
                chengji *= tempShang;
            }
            System.out.println("商數累乘結果；" + chengji);
            result = tianyuan + chengji;
            System.out.println("以商數累乘結果加天元1得乘率：" + result);
            boolean correct = (yanshu * result) % dingmu == 1;
            System.out.println("衍數乘以乘率，再對定母取餘是否等於一："
                    + (correct ? "等於一" : "不等於一"));
        }
        return result;
    }

    /**
     * 有公約數
     */
    public static boolean hasGongYue(int m, int n) {
        int min = (m < n) ? m : n;
        for (int i = 2; i <= min; i++) {
            if (m % i == 0 && n % i == 0) {
                return true;
            }
        }
        return false;
    }
}
