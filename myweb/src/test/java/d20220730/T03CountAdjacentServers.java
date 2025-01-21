package d20220730;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 可以组成网终的服务器：<br/>
 * 在一个机房中，服务器的位置标识在n*m的整数矩阵网格中，1表示单元格上有服务器，0表示没有。<br/>
 * 如果两台服务器位于同一行或者同一列中紧邻的位置，则认位它们之间可以组成一个局域网。<br/>
 * 请你统计机房中最大的局去碰网的包含的服务器的个数。<br/>
 * 输入描述：第一行输入两个正整数，n和m，之后的n*m的二维数组，代表服务器信息。<br/>
 * 输出描述：最大局域网包含的服务器的个数。<br/>
 * 输入如下，输出3：<br/>
 * 2 2 1 0 1 1
 */
public class T03CountAdjacentServers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] servers = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                servers[i][j] = sc.nextInt();
            }
        }
        sc.close();
        int maxCount = calMaxServers(servers);
        System.out.println(maxCount);
    }

    private static int calMaxServers(int[][] servers) {
        int result = 0;
        for (int i = 0; i < servers.length; i++) {
            for (int j = 0; j < servers[i].length; j++) {
                if (servers[i][j] == 1) {
                    int tempResult = calOnePoint(servers, i, j);
                    if (tempResult > result) {
                        result = tempResult;
                    }
                }
            }
        }
        return result;
    }

    private static int calOnePoint(int[][] servers, int i, int j) {
        int count = 0;
        Queue<ServerPoint> queue = new LinkedList<>();
        queue.add(new ServerPoint(i, j));
        while (queue.size() > 0) {
            ServerPoint point = queue.poll();
            int row = point.getI();
            int col = point.getJ();
            if (servers[row][col] == 0) {// 之前置0的代表已访问过，防止重复访问
                continue;
            }
            // 找上下左右的点，如果是1,则加入队列中
            if (row > 0 && servers[row - 1][col] == 1) {// 上
                queue.add(new ServerPoint(row - 1, col));
            }
            if (row < servers.length - 1 && servers[row + 1][col] == 1) {// 下
                queue.add(new ServerPoint(row + 1, col));
            }
            if (col > 0 && servers[row][col - 1] == 1) {// 左
                queue.add(new ServerPoint(row, col - 1));
            }
            if (col < servers.length - 1 && servers[row][col + 1] == 1) {// 右
                queue.add(new ServerPoint(row, col + 1));
            }
            count++;
            servers[row][col] = 0;// 把该点置为0
        }
        return count;
    }
}

class ServerPoint {
    private int i;
    private int j;

    public ServerPoint(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
