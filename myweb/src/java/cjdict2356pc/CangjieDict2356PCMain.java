package cjdict2356pc;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import cjdict2356pc.frame.CangjieDict2356Frame;
import cjdict2356pc.tab.CangjieDict2356Tab;

/**
 * 倉頡字典2356PC版
 * 
 * @author fszhouzz@qq.com
 * @time 2017年12月21日下午2:20:47
 */
public class CangjieDict2356PCMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // JFrame.setDefaultLookAndFeelDecorated(true); // java外觀

                // 字體設置造成選項卡的標籤文字不顯示
                Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
                initGlobalFontSetting(font);

                JFrame frame = CangjieDict2356Frame.getInstance();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); // 居中顯示
                frame.setLayout(null);

                JPanel contentPane = new CangjieDict2356Tab();
                frame.setContentPane(contentPane);
                frame.setVisible(true);
            }
        });
    }

    /**
     * 設置全局字體
     * 
     * @author t
     */
    public static void initGlobalFontSetting(Font fnt) {
        FontUIResource fontRes = new FontUIResource(fnt);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

}
