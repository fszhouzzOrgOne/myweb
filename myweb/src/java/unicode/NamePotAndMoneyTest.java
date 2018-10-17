package unicode;

public class NamePotAndMoneyTest {
    public static void main(String[] args) {
        // http://www.chinanews.com/sh/2016/01-27/7736202.shtml
        // 姓名中的點00B7
        String pot1 = "·";
        String pot2 = "•";
        System.out.println("\u00B7");
        System.out.println("\u00B7".equals(pot1));
        System.out.println("\u00B7".equals(pot2));

        // https://www.jianshu.com/p/3c13d7d8485a
        // ¥是人民幣和日元的符号，半角字符。 U+000A5
        // ￥是全角字符 U+0FFE5
        // Ұ ұ是一個哈薩克语西里爾字母。

        // money
        System.out.println('\u20BF');
        System.out.println('\u060B');
        System.out.println('\u17DB');
        System.out.println('\uFDFC');

        for (char i = '\u2190'; i <= '\u21FF'; i++) {
            System.out.print(" " + i);
        }
    }
}
