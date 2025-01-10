package familysearch2.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import familysearch2.thread.AbstractDownloadThread;
import familysearch2.thread.DownloadThread;
import familysearch2.vo.FamilyTreeItem;

/**
 * 下載事件處理
 * 
 * @author 周子照
 */
public class MyDownloadListener implements ActionListener {

    private FamilyTreeItem targetItem = null;
    private JTextField text = null;
    private DownloadFrame frame = null;

    public MyDownloadListener(FamilyTreeItem targetItem, JTextField text,
            DownloadFrame frame) {
        this.targetItem = targetItem;
        this.text = text;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (null == text.getText() || "".equals(text.getText())) {
            frame.setTitle(frame.getCleanTitle() + "（請先指定下載目錄）");
            frame.flashFrame();
            return;
        }
        final JButton downloadButton = (JButton) e.getSource();
        try {
            // 下載按鈕置無效
            downloadButton.setEnabled(false);
            frame.setTitle(frame.getCleanTitle() + "（下載中...）");
            frame.flashFrame();

            List<FamilyTreeItem> images = targetItem.getChildren();
            for (int k = 0; k < images.size(); k++) {
                FamilyTreeItem im = images.get(k);
                // 父節點的下標
                int parentIndex = targetItem.getParent().getChildren()
                        .indexOf(targetItem);
                im.setTitle(generateFileName(parentIndex, k));
            }
            String filePath = text.getText() + File.separator
                    + targetItem.getTitle() + File.separator;
            final AbstractDownloadThread dt = new DownloadThread(images,
                    filePath);
            new Thread(dt).start();

            // 定時讓下載按鈕可以
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if (dt.isFinished()) {
                        downloadButton.setEnabled(true);
                        frame.setTitle(frame.getCleanTitle() + "（下載完成）");
                        frame.flashFrame();
                        timer.cancel();
                    }
                }
            }, 10000, 50000);
        } catch (Exception e1) {
            downloadButton.setEnabled(true);
            frame.setTitle(frame.getCleanTitle() + "（下載失敗）");
            frame.flashFrame();
            JOptionPane.showMessageDialog(frame, "下載失敗，請重新下載，已下載了的不會重複下載的。",
                    "錯誤", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 生成文件名
     * 
     * @author 周子照
     * @param familyIndex
     *            家譜序號，比如方上在湘潭是第幾個譜
     * @param imageIndex
     *            圖片序號，比如圖片是第幾張圖片
     * @return String
     */
    private String generateFileName(int familyIndex, int imageIndex) {
        return formatNumber(familyIndex + 1, 3)
                + formatNumber(imageIndex + 1, 4) + ".jpg";
    }

    // 在前面補0，湊足位數
    private String formatNumber(Integer number, Integer count) {
        if (number == null) {
            number = 0;
        }
        String nStr = String.valueOf(number);
        if (nStr.length() < count) {
            String temp = "";
            for (int i = 0; i < count - nStr.length(); i++) {
                temp += "0";
            }
            nStr = temp + nStr;
        }
        return nStr;
    }
}
