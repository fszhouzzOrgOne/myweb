package testProject1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * zhangzixian z00480202对所有人说： 05:13 PM
2012伦敦奥运会即将到来，大家都非常关注奖牌榜的情况，现在我们假设奖牌榜的排名规则如下：

1、首先gold medal数量多的排在前面；
2、其次silver medal数量多的排在前面；
3、然后bronze medal数量多的排在前面；
4、若以上三个条件仍无法区分名次，则以国家名称的字典序排定。
我们假设国家名称不超过20个字符、各种奖牌数不超过100，且大于等于0。

解答要求
时间限制：1000ms, 内存限制：100MB
输入
第一行输入一个整数N(0<N<21)，代表国家数量;
然后接下来的N行，每行包含一个字符串Namei表示每个国家的名称，和三个整数Gi、Si、Bi表示每个获得的gold medal、silver medal、bronze medal的数量，以空格隔开，如(China 51 20 21)，具体见样例输入。

输出
输出奖牌榜的依次顺序，只输出国家名称，各占一行，具体见样例输出。

样例
输入样例 1 复制

5
China 32 28 34
England 12 34 22
France 23 33 2
Japan 12 34 25
Rusia 23 43 0
输出样例 1

China
Rusia
France
Japan
England

加个例子：
6
China 32 28 34
England 12 34 22
France 23 33 2
Japan 12 34 25
Rusia 23 43 0
Angland 12 34 22
 * 
 * @author Administrator
 * @time 2022年5月30日下午5:14:20
 */
public class OlympicGames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        List<Medal> medals = new ArrayList<>(cnt);
        for (int i = 0; i < cnt; i++) {
            String name = sc.next();
            int gi = sc.nextInt();
            int si = sc.nextInt();
            int bi = sc.nextInt();
            medals.add(new Medal(name, gi, si, bi));
        }
        sc.close();
        medals.sort((x, y) -> {
            // 金、银、铜降序
            int giRes = Integer.compare(y.getGi(), x.getGi());
            if (giRes != 0) {
                return giRes;
            }
            int siRes = Integer.compare(y.getSi(), x.getSi());
            if (siRes != 0) {
                return siRes;
            }
            int biRes = Integer.compare(y.getBi(), x.getBi());
            if (biRes != 0) {
                return biRes;
            }
            // 名称升序
            return x.getName().compareTo(y.getName());
        });
        medals.forEach(m -> System.out.println(m.getName()));
    }
}

class Medal {
    private String name;
    private Integer gi;
    private Integer si;
    private Integer bi;

    public Medal(String name, Integer gi, Integer si, Integer bi) {
        this.name = name;
        this.gi = gi;
        this.si = si;
        this.bi = bi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGi() {
        return gi;
    }

    public void setGi(Integer gi) {
        this.gi = gi;
    }

    public Integer getSi() {
        return si;
    }

    public void setSi(Integer si) {
        this.si = si;
    }

    public Integer getBi() {
        return bi;
    }

    public void setBi(Integer bi) {
        this.bi = bi;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bi == null) ? 0 : bi.hashCode());
        result = prime * result + ((gi == null) ? 0 : gi.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((si == null) ? 0 : si.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Medal other = (Medal) obj;
        if (bi == null) {
            if (other.bi != null)
                return false;
        } else if (!bi.equals(other.bi))
            return false;
        if (gi == null) {
            if (other.gi != null)
                return false;
        } else if (!gi.equals(other.gi))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (si == null) {
            if (other.si != null)
                return false;
        } else if (!si.equals(other.si))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Medal [name=" + name + ", gi=" + gi + ", si=" + si + ", bi=" + bi + "]";
    }
}
