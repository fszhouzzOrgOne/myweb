package cjdict2356pc.tab;

import java.awt.GridLayout;
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
    ImageIcon icon = createImageIcon("images/tabimg.png");

    public CangjieDict2356Tab() {
        layoutComponents();
    }

    private void layoutComponents() {
        int i = 0;
        JPanel jpanelFirst = new JPanel();
        jpanelFirst.setSize(100, 30);
        jTabbedpane.addTab(tabNames[i++], icon, jpanelFirst, "first");
        // jTabbedpane.setMnemonicAt(0, KeyEvent.VK_0);

        // 第二个标签下的JPanel
        JPanel jpanelSecond = new JPanel();
        jpanelSecond.setSize(100, 30);
        jTabbedpane.addTab(tabNames[i++], icon, jpanelSecond, "second");
        // jTabbedpane.setMnemonicAt(1, KeyEvent.VK_1);
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
