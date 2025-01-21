package d20220714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * HJ53 杨辉三角的变形： <br/>
 * 以上三角形的数阵，第一行只有一个数1， <br/>
 * 以下每行的每个数，是恰好是它上面的数、左上角数和右上角的数，3个数之和。<br/>
 * <br/>
 */
public class HJ053YangHuiTriangle {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine().trim();
        int no = Integer.parseInt(line);
        System.out.println(firstIndexOfAnEvenNumber(no));
        System.out.println(firstIndexOfAnEvenNumber2(no));
        System.out.println(firstIndexOfAnEvenNumber3(no));
    }

    // 按-1 -1 2 3 2 4 2 3 2 4……判断
    private static int firstIndexOfAnEvenNumber3(int no) {
        if (no < 3) {
            return -1;
        }
        int[] res = {2, 3, 2, 4};
        return res[(no + 1) % 4];
    }

    // 用个两行的二维数组，上下交替使用
    // 运行超时:您的程序未能在规定时间内运行结束
    private static int firstIndexOfAnEvenNumber2(int no) {
        int lastCnt = 2 * no - 1;
        int mid = lastCnt / 2;
        // 按对称看，只要mid+1列
        int lineCnt = 2;
        int[][] triangle = new int[lineCnt][mid + 1];
        int res = -1;
        for (int i = 0; i < no; i++) {
            int lineIndex = i % lineCnt;
            int preLine = (i + 1) % lineCnt;
            for (int j = mid - i; j <= mid; j++) {
                if (j == mid - i) {
                    triangle[lineIndex][j] = 1;
                    continue;
                }
                if (j == mid) {
                    triangle[lineIndex][j] = 2 * triangle[preLine][j - 1] + triangle[preLine][j];
                    continue;
                }
                triangle[lineIndex][j] = triangle[preLine][j - 1] + triangle[preLine][j] + triangle[preLine][j + 1];
                if (i == no - 1 && triangle[lineIndex][j] % 2 == 0) {
                    res = j + 1;
                    break;
                }
            }
            // System.out.println(Arrays.toString(triangle[lineIndex]).replaceAll(" ", "\t"));
        }
        return res;
    }

    // 直接用int[][]，10000个时，java.lang.OutOfMemoryError: Java heap space
    private static int firstIndexOfAnEvenNumber(int no) {
        int lastCnt = 2 * no - 1;
        int mid = lastCnt / 2;
        // 按对称看，只要mid+1列
        int[][] triangle = new int[no][mid + 1];
        int res = -1;
        for (int i = 0; i < no; i++) {
            for (int j = mid - i; j <= mid; j++) {
                if (j == mid - i) {
                    triangle[i][j] = 1;
                    continue;
                }
                if (j == mid) {
                    triangle[i][j] = 2 * triangle[i - 1][j - 1] + triangle[i - 1][j];
                    continue;
                }
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j] + triangle[i - 1][j + 1];
                if (i == no - 1 && triangle[i][j] % 2 == 0) {
                    res = j + 1;
                    break;
                }
            }
            // System.out.println(Arrays.toString(triangle[i]).replaceAll(" ", "\t"));
        }
        return res;
    }
}
