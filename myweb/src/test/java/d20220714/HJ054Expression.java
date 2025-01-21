package d20220714;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * HJ054表达式求值：<br/>
 * 算术表达式求值，计算出结果。<br/>
 * 合法字符有[+-*\\/()0-9]。<br/>
 * 如输入400+5，输出105。<br/>
 * 
 * @author Administrator
 * @time 2022年7月16日上午11:17:08
 */
public class HJ054Expression {
    private static final String VALID_CHAR_PATTERN = "[+-*\\/()0-9]+";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine().trim().replaceAll(" +", "");
        
    }
}
