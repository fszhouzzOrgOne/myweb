package cjdict2356pc.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定義字體<br/>
 * 參見：http://alog2012.iteye.com/blog/1616465
 */
public class FontManager {

    private static final String FONT_NAME = "KaiXinSong2.1.ttf";

    public Font getDefinedFont() {
        Font definedFont = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            is = FontManager.class.getResourceAsStream(FONT_NAME);
            bis = new BufferedInputStream(is);
            // createFont返回一个使用指定字体类型和输入数据的新 Font。<br>
            // 新 Font磅值为 1，样式为 PLAIN,注意 此方法不会关闭 InputStream
            definedFont = Font.createFont(Font.TRUETYPE_FONT, bis);
            // 复制此 Font对象并应用新样式，创建一个指定磅值的新 Font对象。
            definedFont = definedFont.deriveFont(30);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return definedFont;
    }
}
