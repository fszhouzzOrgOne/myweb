package cjdict2356pc.tab;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 倉頡字典的內容
 * 
 * @author fszhouzz@qq.com
 * @time 2017年12月21日下午4:34:27
 */
public class CangjieDict2356TabDictPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JLabel searchLabel = null;
    private JTextField searchField = null;
    private JButton searchButton = null;

    public CangjieDict2356TabDictPanel() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        int gap = 10;
        int compHeight = 30;

        int labelWidth = 200;
        int labelX = gap;
        int labelY = 0;
        searchLabel = new JLabel("輸入查詢：");
        labelY = gap;
        searchLabel.setBounds(labelX, labelY, labelWidth, compHeight);
        add(searchLabel);
        
        searchField = new JTextField("輸入編碼或文字查詢...", 14);
        int fieldX = labelX + labelWidth + gap;
        int fieldY = gap;
        int fieldWidth = 200;
        searchField.setBounds(fieldX, fieldY, fieldWidth, compHeight);
        add(searchField);
        
        searchButton = new JButton("查詢");
        int buttonX = fieldX + fieldWidth + gap;
        int buttonY = gap;
        int buttonWidth = 80;
        searchButton.setBounds(buttonX, buttonY, buttonWidth, compHeight);
        add(searchButton);
    }
}
