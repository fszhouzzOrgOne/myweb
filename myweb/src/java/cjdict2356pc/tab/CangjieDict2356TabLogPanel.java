package cjdict2356pc.tab;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cangjie.java.util.IOUtils;

/**
 * 版本說明的內容
 * 
 * @author fszhouzz@qq.com
 * @time 2017年12月21日下午3:54:17
 */
public class CangjieDict2356TabLogPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    String logFileName = "src" + File.separator + "java" + File.separator + "cjdict2356pc" + File.separator + "version"
            + File.separator + "log.txt";

    public CangjieDict2356TabLogPanel() {
        JTextArea logTextArea = new JTextArea(25, 50);
        logTextArea.setFont(new Font("宋体", Font.PLAIN, 16)); // 固定字體，不隨外界變化
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        logTextArea.setBackground(new Color(220, 220, 220));
        logTextArea.setText(getVLogText());
        logTextArea.setCaretPosition(0); // 滾到第一行，不然初始化展示最後一行

        JScrollPane middleLogPanel = new JScrollPane(logTextArea);
        add(middleLogPanel);
    }

    private String getVLogText() {
        List<String> lines = IOUtils.readLines(logFileName);
        String res = "";
        if (null != lines && !lines.isEmpty()) {
            for (String line : lines) {
                res += "    " + line + "\n";
            }
        }
        return res;
    }
}
