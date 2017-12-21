package cjdict2356pc.view;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import cjdict2356pc.dto.Item;
import unicode.UnicodeHanziUtil;

/**
 * 字典查詢結果列表項
 * 
 * @author fszhouzz@qq.com
 * @time 2017年12月21日 下午10:19:30
 */
public class Cangjie2356ListItemView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JLabel labelChar = null;
    private JLabel labelUnicodeRangeName = null;
    private JLabel labelEncode = null;

    public Cangjie2356ListItemView(Item it) {
        this.setLayout(null);
        int width = 423;
        int height = 50;
        setSize(width, height);
        Border border = BorderFactory.createEtchedBorder();
        this.setBorder(border);

        String charLabelText = it.getCharacter();
        String encodeLabelText = it.getEncode() + "（" + it.getEncodeName() + "）";
        String unicodeRangeName = "";
        if (it.isEmpty()) {
            charLabelText = "(空)";
            encodeLabelText = "無結果。";
        } else {
            unicodeRangeName = UnicodeHanziUtil.getRangeNameByChar(it.getCharacter());
        }

        labelChar = new JLabel(charLabelText, null, SwingConstants.CENTER);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
        labelChar.setFont(font);
        int charX = 0;
        int charY = 0;
        int charWidth = height * 150 / 100;
        int charHeight = height;
        labelChar.setBounds(charX, charY, charWidth, charHeight);
        add(labelChar);

        labelUnicodeRangeName = new JLabel(unicodeRangeName, null, SwingConstants.LEFT);
        int rangeX = charWidth;
        int rangeY = 0;
        int rangeWidth = width - charWidth;
        int rangeHeight = height / 2;
        labelUnicodeRangeName.setBounds(rangeX, rangeY, rangeWidth, rangeHeight);
        add(labelUnicodeRangeName);

        labelEncode = new JLabel(encodeLabelText, null, SwingConstants.LEFT);
        int encX = charWidth;
        int encY = rangeHeight;
        int encWidth = rangeWidth;
        int encHeight = rangeHeight;
        labelEncode.setBounds(encX, encY, encWidth, encHeight);
        add(labelEncode);
    }
}