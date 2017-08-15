package familysearch2.frame;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import familysearch2.vo.FamilyTreeItem;

/**
 * 下拉選中事件處理
 * 
 * @author 周子照
 */
public class MyItemListener implements ItemListener {

    private FamilyTreeItem item = null;
    private DownloadFrame frame = null;

    public MyItemListener(DownloadFrame frame, FamilyTreeItem item1) {
        this.item = item1;
        this.frame = frame;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 當前下拉框後面的全删除
            Integer keepIndex = frame.getItemIndexByTitle(item.getTitle());
            if (null != keepIndex) {
                for (int del = frame.getItems().size() - 1; del > keepIndex; del--) {
                    frame.getItems().remove(del);
                    if (frame.getBoxes().size() == del + 1) {
                        frame.getBoxes().remove(del);
                    }
                }
            }
            // 查找選中的的
            JComboBox comboBox = (JComboBox) e.getSource();
            String s = (String) comboBox.getSelectedItem();
            if (null != s && !"".equals(s)) {
                frame.setTitle(frame.getCleanTitle() + "（查詢中...）");
                try {
                    FamilyTreeItem select = item.getChildByTitle(s);
                    if (null != select) {
                        frame.getItems().add(select);

                        frame.resetComboBoxes((JPanel) frame.getContentPane());
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "查詢失敗", "錯誤",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
