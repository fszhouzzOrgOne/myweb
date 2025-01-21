package testProject1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * top N 訪問量的URL打印出來
 * www.aa.com
 * www.aa.com
 * www.aa.com
 * 3
 * www.aa.com
 * 100 
 * www.aa.com
 * 3
 * 1
 * 65%
 */
public class MFAccessedURLs {
    
    private static final String NUMBER_PATTERN = "[0-9]+"; 
    
    // 大堆
    // url -> cnt
    private static Map<String, Integer> urlCntMap = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = null;
        while (sc.hasNext()) {
            line = sc.next();
            if (null == line) {
                break;
            }
            line = line.trim();
            if (line.matches(NUMBER_PATTERN)) {
                System.out.println(getTopNUrls(Integer.parseInt(line)));
            } else {
                int cnt = urlCntMap.getOrDefault(line, 0);
                urlCntMap.put(line, cnt + 1);
            }
        }
        sc.close();
    }

    private static String getTopNUrls(int cnt) {
        if (urlCntMap.isEmpty()) {
            return null;
        }
        List<String> keys = urlCntMap.entrySet().stream().sorted((x, y) -> y.getValue().compareTo(x.getValue())).limit(cnt).map(e -> e.getKey()).collect(Collectors.toList());
        return concatenateListStrings(keys);
    }

    private static String concatenateListStrings(List<String> keys) {
        if (keys.isEmpty()) {
            return "";
        }
        StringBuilder bu = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            bu.append(keys.get(i));
            if (i < keys.size() - 1) {
                bu.append(",");
            }
        }
        return bu.toString();
    }

}
