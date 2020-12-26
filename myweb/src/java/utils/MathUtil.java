package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 數學計算
 * 
 * @author fszhouzz@qq.com
 * @time 2019年3月9日 下午10:04:47
 */
public class MathUtil {
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
        // if (denominator.trim().matches("(-+)?0*\\.?0*")) {
        // return null;
        // }
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
    }
}
