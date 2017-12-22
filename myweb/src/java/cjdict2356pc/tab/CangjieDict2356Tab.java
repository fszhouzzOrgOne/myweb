package cjdict2356pc.tab;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * 字典的主要內容選項卡<br/>
 * 參見：http://blog.csdn.net/sweetgirl520/article/details/51346263
 * 
 * @author fszhouzz@qq.com
 * @time 2017年12月21日下午2:45:45
 */
public class CangjieDict2356Tab extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JTabbedPane jTabbedpane = new JTabbedPane();
    private String[] tabNames = { "倉頡字典", "版本說明" };
    // 圖標都用個没有背景的圖
    ImageIcon icon = createImageIcon("images" + File.separator + "tabimg.png");

    public CangjieDict2356Tab() {
        layoutComponents();
        this.setBackground(Color.GRAY);
    }

    private void layoutComponents() {
        JPanel jpanelFirst = new CangjieDict2356TabDictPanel();
        jTabbedpane.addTab(tabNames[0], icon, jpanelFirst, tabNames[0]);

        JPanel jpanelSecond = new CangjieDict2356TabLogPanel();
        jTabbedpane.addTab(tabNames[1], icon, jpanelSecond, tabNames[1]);
        setLayout(new GridLayout(1, 1));
        add(jTabbedpane);

    }

    /** 生成圖標 */
    private ImageIcon createImageIcon(String path) {
        URL url = CangjieDict2356Tab.class.getResource(path);
        if (url == null) {
            System.out.println("the image " + path + " is not exist!");
            return null;
        }
        return new ImageIcon(url);
    }
}
