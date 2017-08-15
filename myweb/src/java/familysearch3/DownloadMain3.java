package familysearch3;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import familysearch3.frame.DownloadFrame3;

public class DownloadMain3 {

    /**
     * 下載家譜工具三入口
     * 
     * @author t
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
                initGlobalFontSetting(font);

                JFrame frame = DownloadFrame3.getInstance();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); // 居中顯示
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
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys
                .hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

}
