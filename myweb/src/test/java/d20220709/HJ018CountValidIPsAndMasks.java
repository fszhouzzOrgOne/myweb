package d20220709;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * HJ18 识别有效的IP地址和掩码并进行分类统计：<br/>
 * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。<br/>
 * 所有的IP地址划分为 A,B,C,D,E五类：<br/>
 * A类地址从1.0.0.0到126.255.255.255;<br/>
 * B类地址从128.0.0.0到191.255.255.255;<br/>
 * C类地址从192.0.0.0到223.255.255.255;<br/>
 * D类地址从224.0.0.0到239.255.255.255；<br/>
 * E类地址从240.0.0.0到255.255.255.255<br/>
 * <br/>
 * 私网IP范围是：<br/>
 * 从10.0.0.0到10.255.255.255<br/>
 * 从172.16.0.0到172.31.255.255<br/>
 * 从192.168.0.0到192.168.255.255<br/>
 * <br/>
 * 子网掩码为二进制下前面是连续的1，然后全是0。<br/>
 * 输出：<br/>
 * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。<br/>
 * 如下输入，输出：1 0 1 0 0 2 1<br/>
10.70.44.68~255.254.255.0
1.0.0.1~255.0.0.0
192.168.0.2~255.255.255.0
19..0.~255.255.255.0
 * 如下输入，输出：0 0 0 0 0 0 0<br/>
0.201.56.50~255.255.111.255
127.201.56.50~255.255.111.255
 * 
 * @author Administrator
 * @time 2022年7月10日下午4:06:08
 */
public class HJ018CountValidIPsAndMasks {
    private static final String IP_PATTERN;

    static {
        String nums = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        IP_PATTERN = "(" + nums + "\\.){3}" + nums;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> cntMap = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (null == line || line.isEmpty()) {
                break;
            }
            String[] parts = line.split("~");
            // 注意找不到type的只忽略，不算错的：即0或127开头的。
            if (checkValidIP(parts[0])) {
                String type = getIPType(parts[0]);
                if (type != null) {
                    if (!checkValidIP(parts[1]) || !checkValidMask(parts[1])) {
                        cntMap.put("错", cntMap.getOrDefault("错", 0) + 1);
                    } else {
                        cntMap.put(type, cntMap.getOrDefault(type, 0) + 1);
                        if (checkPrivateIP(parts[0])) {
                            cntMap.put("私", cntMap.getOrDefault("私", 0) + 1);
                        }
                    }
                }
            } else {
                cntMap.put("错", cntMap.getOrDefault("错", 0) + 1);
            }
        }
        sc.close();
        print(cntMap);
    }

    private static void print(Map<String, Integer> cntMap) {
        int[] res = new int[7];
        res[0] = cntMap.getOrDefault("A", 0);
        res[1] = cntMap.getOrDefault("B", 0);
        res[2] = cntMap.getOrDefault("C", 0);
        res[3] = cntMap.getOrDefault("D", 0);
        res[4] = cntMap.getOrDefault("E", 0);
        res[5] = cntMap.getOrDefault("错", 0);
        res[6] = cntMap.getOrDefault("私", 0);
        System.out.println(Arrays.toString(res).replaceAll("[\\[\\],]", ""));
    }

    // 私用
    private static boolean checkPrivateIP(String ip) {
        int num1 = Integer.parseInt(ip.substring(0, ip.indexOf(".")));
        if (num1 == 10) {
            return true;
        }
        int index = ip.indexOf(".");
        int num2 = Integer.parseInt(ip.substring(index + 1, ip.indexOf(".", index + 1)));
        return (num1 == 172 && num2 >= 16 && num2 <= 31) || (num1 == 192 && num2 == 168);
    }

    // 返回IP的分类：A、B、C、D、E
    private static String getIPType(String ip) {
        int num = Integer.parseInt(ip.substring(0, ip.indexOf(".")));
        // 0的不要
        if (num == 0 || num == 127) {
            return null;
        }
        if (num >= 1 && num <= 126) {
            return "A";
        }
        // 127的不要
        if (num >= 128 && num <= 191) {
            return "B";
        }
        if (num >= 129 && num <= 223) {
            return "C";
        }
        if (num >= 224 && num <= 239) {
            return "D";
        }
        if (num >= 240 && num <= 255) {
            return "E";
        }
        return null;
    }

    private static boolean checkValidIP(String ip) {
        return ip.matches(IP_PATTERN);
    }

    private static boolean checkValidMask(String ip) {
        // 转成二进制
        String bi = null;
        int index = 0;
        for (int i = 0; i < 3; i++) {
            int index2 = ip.indexOf(".", index);
            Integer num1 = Integer.parseInt(ip.substring(index, index2));
            if (bi == null) {
                bi = fill8Digits(Integer.toBinaryString(num1));
            } else {
                bi += fill8Digits(Integer.toBinaryString(num1));
            }
            index = index2 + 1;
        }
        Integer num1 = Integer.parseInt(ip.substring(index));
        bi += fill8Digits(Integer.toBinaryString(num1));
        // 不能全1或全0
        return bi.matches("1+0+");
    }

    private static String fill8Digits(String bi) {
        if (bi.length() == 8) {
            return bi;
        }
        int mod = bi.length() % 8;
        char[] ch = new char[8 - mod];
        Arrays.fill(ch, '0');
        return new String(ch) + bi;
    }
}
