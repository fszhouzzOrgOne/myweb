package unicode;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 統一碼漢字工具类<br/>
 * 參見http://yedict.com/zsts.htm
 * 
 * @author fszhouzz@qq.com
 * @time 2017年12月11日上午9:37:37
 */
public class UnicodeHanziUtil {
    /** 基本區 */
    private static int[] baseRange = { 0x4E00, 0x9FA5 };
    /** 基本補充 */
    private static int[] base2Range = { 0x9FA6, 0x9FEA };
    /** 擴展A */
    private static int[] AextRange = { 0x3400, 0x4DB5 };
    /** 擴展B */
    private static int[] BextRange = { 0x20000, 0x2A6D6 };
    /** 擴展C */
    private static int[] CextRange = { 0x2A700, 0x2B734 };
    /** 擴展D */
    private static int[] DextRange = { 0x2B740, 0x2B81D };
    /** 擴展E */
    private static int[] EextRange = { 0x2B820, 0x2CEA1 };
    /** 擴展F */
    private static int[] FextRange = { 0x2CEB0, 0x2EBE0 };
    /** 康熙部首 */
    private static int[] kangxiPart = { 0x2F00, 0x2FD5 };
    /** 部首擴展 */
    private static int[] extPart = { 0x2E80, 0x2EF3 };
    /** 兼容漢字 */
    private static int[] hanziCompt = { 0xF900, 0xFAD9 };
    /** 兼容擴展 */
    private static int[] extCompt = { 0x2F800, 0x2FA1D };
    /** 漢字筆畫 */
    private static int[] hanziStroke = { 0x31C0, 0x31E3 };
    /** 漢字結構 */
    private static int[] hanziStruct = { 0x2FF0, 0x2FFB };
    /** 漢字注音 */
    private static int[] zhuyin = { 0x3105, 0x3120 };
    /** 注音擴展 */
    private static int[] extZhuyin = { 0x31A0, 0x31BA };

    private static Map<String, Object> nameRangeMap = new LinkedHashMap<String, Object>();

    static {
        nameRangeMap.put("基本區", baseRange);
        nameRangeMap.put("基本補充", base2Range);
        nameRangeMap.put("擴展A區", AextRange);
        nameRangeMap.put("擴展B區", BextRange);
        nameRangeMap.put("擴展C區", CextRange);
        nameRangeMap.put("擴展D區", DextRange);
        nameRangeMap.put("擴展E區", EextRange);
        nameRangeMap.put("擴展F區", FextRange);

        nameRangeMap.put("康熙部首", kangxiPart);
        nameRangeMap.put("部首擴展", extPart);
        nameRangeMap.put("兼容漢字", hanziCompt);
        nameRangeMap.put("兼容擴展", extCompt);
        nameRangeMap.put("漢字筆畫", hanziStroke);
        nameRangeMap.put("漢字結構", hanziStruct);
        nameRangeMap.put("漢字注音", zhuyin);
        nameRangeMap.put("注音擴展", extZhuyin);
    }

    public static void main(String[] args) {
        System.out.println(CextRange[1] - CextRange[0] + 1);

        String aaaa = "文字\u2CEB0";
        // 擴展區漢字長度2
        System.out.println(aaaa.length());
        System.out.println(aaaa.toCharArray().length);

        System.out.println(getRangeNameByChar("𠔻"));
        System.out.println(getRangeNameByChar("ㆬ"));
    }

    public static String getRangeNameByChar(String charStr) {
        if (null == charStr || charStr.toCharArray().length > 2) {
            return "";
        }
        for (String name : nameRangeMap.keySet()) {
            int[] range = (int[]) nameRangeMap.get(name);
            if (getStringSet(range).contains(charStr)) {
                return name;
            }
        }
        return "";
    }

    public static Set<String> getStringSet(int[] range) {
        return getStringSet(range[0], range[1]);
    }

    public static Set<String> getStringSet(int start, int end) {
        Set<String> set = new LinkedHashSet<String>();
        for (int i = start; i <= end; i++) {
            String res = getStringByUnicode(i);
            if (!set.contains(res)) {
                set.add(res);
            }
        }
        return set;
    }

    public static String getStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}
