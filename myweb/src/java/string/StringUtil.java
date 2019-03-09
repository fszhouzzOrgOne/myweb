package string;

/**
 * 字符串操作
 * 
 * @author fszhouzz@qq.com
 * @time 2019年3月9日 下午10:07:40
 */
public class StringUtil {

    public static boolean isEmpty(String param) {
        return null == param || param.trim().isEmpty();
    }

    public static boolean isNotEmpty(String param) {
        return !isEmpty(param);
    }

}
