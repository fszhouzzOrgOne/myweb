package utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 數學計算
 * 
 * @author fszhouzz@qq.com
 * @time 2019年3月9日 下午10:04:47
 */
public class MathUtil {
    /** 階乘 */
    public static BigInteger factorial(String num) {
        return factorial(new BigInteger(num.trim()));
    }

    /** 階乘 */
    public static BigInteger factorial(BigInteger bi) {
        if (bi.compareTo(BigInteger.ZERO) == 0
                || bi.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        }
        return bi.multiply(factorial(bi.subtract(BigInteger.ONE)));
    }

    /** 組合n取m：C(n,m)=n!/((n-m)! * m!) */
    public static BigInteger combination(String n, String m) {
        return combination(new BigInteger(n), new BigInteger(m));
    }

    /** 組合n取m：C(n,m)=n!/((n-m)! * m!) */
    public static BigInteger combination(BigInteger bin, BigInteger bim) {
        return factorial(bin)
                .divide(factorial(bin.subtract(bim)).multiply(factorial(bim)));
    }

    /** 是否相等 */
    public static boolean checkEqual(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2) == 0;
    }

    /** 是否相等 */
    public static boolean checkEqual(String num1, String num2) {
        return checkEqual(new BigDecimal(num1), new BigDecimal(num2));
    }

    /** 菲波納契數，一個小於它，一個大於等於它 */
    public static List<BigInteger> leastFibonacciGtOrEq(String s) {
        return leastFibonacciGtOrEq(new BigInteger(s));
    }

    /** 菲波納契數，一個小於它，一個大於等於它 */
    public static List<BigInteger> leastFibonacciGtOrEq(BigInteger bi) {
        List<BigInteger> res = new ArrayList<BigInteger>();
        if (bi.compareTo(BigInteger.ZERO) == 0) {
            res.add(BigInteger.ONE);
            res.add(BigInteger.ONE);
            return res;
        }
        BigInteger tmp = BigInteger.ONE;
        BigInteger tmp2 = tmp.add(tmp);
        while (tmp2.compareTo(bi) < 0) {
            BigInteger tmp3 = tmp2;
            tmp2 = tmp2.add(tmp);
            tmp = tmp3;
        }
        res.add(tmp);
        res.add(tmp2);
        return res;
    }

    /** 快速幂 */
    public static BigInteger powerFast(String a, String b) {
        return powerFast(new BigInteger(a), new BigInteger(b));
    }

    /** 快速幂 */
    public static BigInteger powerFast(BigInteger a, BigInteger b) {
        BigInteger bi2 = BigInteger.valueOf(2);
        BigInteger res = BigInteger.ONE;
        BigInteger base = a;
        while (b.compareTo(BigInteger.ZERO) != 0) {
            // 指數是單數時，把基數乘一次到結果中
            if (b.remainder(bi2).compareTo(BigInteger.ONE) == 0) {
                res = res.multiply(base);
            }
            // 指數減半，直到1，到0結束。
            b = b.divide(bi2);
            // 基數變成自己的平方
            base = base.multiply(base);
        }
        return res;
    }

    /**
     * 求百分比1，返回字符串结果
     *
     * @author EX-ZHOUZIZHAO002
     * @time 2019年1月18日上午10:54:58
     * @param numerator
     *            分子
     * @param denominator
     *            分母
     * @param precision
     *            小数点后最多留几位，不传入则返回整数
     * @param addZero
     *            是否补0
     * @return
     */
    public static String calculatePercentage(String numerator,
            String denominator, Integer precision, Boolean addZero) {
        if (StringUtil.isEmpty(numerator) || StringUtil.isEmpty(denominator)) {
            return null;
        }
        BigDecimal a = new BigDecimal(numerator.trim())
                .multiply(new BigDecimal(100));
        return calculateDivision(a.toPlainString(), denominator, precision,
                addZero);
    }

    /**
     * 除法1，返回字符串结果
     *
     * @author EX-ZHOUZIZHAO002
     * @time 2019年1月18日上午10:54:58
     * @param numerator
     *            分子
     * @param denominator
     *            分母
     * @param precision
     *            小数点后最多留几位，不传入则返回整数
     * @param addZero
     *            是否补0
     * @return
     */
    public static String calculateDivision(String numerator, String denominator,
            Integer precision, Boolean addZero) {
        if (StringUtil.isEmpty(numerator) || StringUtil.isEmpty(denominator)) {
            return null;
        }
        BigDecimal a = new BigDecimal(numerator.trim());
        BigDecimal b = new BigDecimal(denominator.trim());
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        if (null == precision) {
            precision = 0;
        }
        BigDecimal res = a.divide(b, precision, RoundingMode.HALF_UP);
        if (!addZero) {
            res = res.stripTrailingZeros();
        }
        return res.toPlainString();
    }

    /**
     * 求百分比2，返回字符串结果
     *
     * @author EX-ZHOUZIZHAO002
     * @time 2019年1月18日上午10:54:58
     * @param numerator
     *            分子
     * @param denominator
     *            分母
     * @param precision
     *            小数点后最多留几位，不传入则返回整数
     * @param addZero
     *            是否补0
     * @return
     */
    public static String calculatePercentage(int numerator, int denominator,
            Integer precision, Boolean addZero) {
        if (0 == denominator) {
            return null;
        }
        return calculateDivision(numerator * 100, denominator, precision,
                addZero);
    }

    /**
     * 除法2，返回字符串结果
     *
     * @author EX-ZHOUZIZHAO002
     * @time 2019年1月18日上午10:54:58
     * @param numerator
     *            分子
     * @param denominator
     *            分母
     * @param precision
     *            小数点后最多留几位，不传入则返回整数
     * @param addZero
     *            是否补0
     * @return
     */
    public static String calculateDivision(int numerator, int denominator,
            Integer precision, Boolean addZero) {
        if (0 == denominator) {
            return null;
        }
        return calculateDivision(numerator + "", denominator + "", precision,
                addZero);
    }

    /**
     * 格式化浮點数字1
     *
     * @author EX-ZHOUZIZHAO002
     * @time 2019年2月25日下午5:13:26
     * @param param
     * @param precision
     *            小数点后最多留几位，不传入则返回整数
     * @param addZero
     *            是否补0
     * @return
     */
    public static String formatDecimal(String param, Integer precision,
            Boolean addZero) {
        if (StringUtil.isEmpty(param)) {
            return null;
        }
        BigDecimal a = new BigDecimal(param.trim());
        if (null == precision) {
            precision = 0;
        }
        BigDecimal res = a.setScale(precision, BigDecimal.ROUND_HALF_UP);
        if (!addZero) {
            res = res.stripTrailingZeros();
        }
        return res.toPlainString();
    }

    /**
     * 格式化浮點数字2
     *
     * @author EX-ZHOUZIZHAO002
     * @time 2019年2月25日下午5:13:26
     * @param param
     * @param precision
     *            小数点后最多留几位，不传入则返回整数
     * @param addZero
     *            是否补0
     * @return
     */
    @Deprecated
    public static String formatDecimal(Float param, Integer precision,
            Boolean addZero) {
        if (null == param) {
            return null;
        }
        if (null == precision || precision <= 0) {
            if (param >= param.intValue() + 0.5) {
                return param.longValue() + 1 + "";
            } else
                return param.longValue() + "";
        }
        // 用0，后面也补0
        String format = "0.";
        for (int i = 0; i < precision; i++) {
            if (null == addZero || !addZero) {
                format += "#";
            } else {
                format += "0";
            }
        }
        return new DecimalFormat(format).format(param);
    }

    public static void main(String[] args) {
        System.out.println(calculatePercentage(10, 5, 4, true));
        System.out.println(calculatePercentage(0, 5, 4, false));
        System.out.println(calculatePercentage(3, 8, 4, true));
        System.out.println(calculatePercentage(3, 8, 4, false));
        System.out.println(calculatePercentage(3, 8, 2, false));
        System.out.println(calculatePercentage(3, 8, 0, false));
        System.out.println(calculatePercentage(3, 8, -1, false));
        System.out.println();
        System.out.println(calculatePercentage("10", "50000000", 20, true));
        System.out.println(calculatePercentage("10", "50000000", 20, false));
        System.out.println(calculatePercentage("3", "8", 2, true));
        System.out.println(calculatePercentage("3", "8", 4, true));
        System.out.println(calculatePercentage("3", "-8", 4, false));
        System.out.println(calculatePercentage("3", "-8", 0, false));
        System.out.println(calculatePercentage("3", "-8", null, false));
        System.out.println(calculatePercentage("3", "-8", -1, false));
        System.out.println("除0: ");
        System.out.println(calculatePercentage("0", "5", 4, true));
        System.out.println(calculatePercentage("0", "5", 4, false));
        System.out.println("除以-0: ");
        System.out.println(calculatePercentage("3", "-0", 4, true));
        System.out.println(calculatePercentage("3", "-0.0", 4, true));
        System.out.println(calculatePercentage("3", "00", 4, true));
        System.out.println(calculatePercentage("3", "0.00", 4, true));
        System.out.println("格式化: ");
        System.out.println(formatDecimal("2.546234", 4, true));
        System.out.println(formatDecimal("2.546234", 2, true));
        System.out.println(formatDecimal("2.546234", 8, true));
        System.out.println(formatDecimal("2.546234", 8, false));
        System.out.println(formatDecimal("2.546234", null, false));
        System.out.println(factorial("3"));
        System.out.println(factorial("5"));
        System.out.println(combination("5", "3"));
        System.out.println(checkEqual("3.1400", "3.14"));
        System.out.println(leastFibonacciGtOrEq("5"));
        System.out.println(powerFast("3", "5"));
    }
}
