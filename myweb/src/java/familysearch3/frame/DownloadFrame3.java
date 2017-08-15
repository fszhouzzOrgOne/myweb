package familysearch3.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import familysearch3.thread.AbstractDownloadThread;
import familysearch3.thread.DownloadThread;
import familysearch3.util.CharacterUtil;
import familysearch3.vo.FamilyTreeItem;

/**
 * 下載家譜工具三
 * 
 * @author t
 */
public class DownloadFrame3 extends JFrame {

    private static final long serialVersionUID = 1L;

    private static class LazyHolder {
        private static final DownloadFrame3 frame3 = new DownloadFrame3();
    }

    /**
     * 獲得實例
     */
    public static DownloadFrame3 getInstance() {
        return LazyHolder.frame3;
    }

    private AbstractDownloadThread downloadThread = null;

    private JPanel contentPane = null;
    /**
     * 北部菜單
     */
    private JPanel northMenuPanel = null;
    private JPanel headMenuPanel = null;
    private JScrollPane bodyMenuPanel = null;
    private JPanel bodyMenuContent = null;
    /**
     * 中部控制
     */
    private JPanel middleControllPanel = null;
    private JTextField middleChooseText = null;
    /** 選擇保存目錄 */
    private JButton middleChooseBtn = null;
    /** 下載所選 */
    private JButton downSelectBtn = null;
    /** 全載下載 */
    private JButton downAllBtn = null;
    /** 重置選中狀態 */
    private JButton deselectAllBtn = null;
    /** 取消下載 */
    private JButton cancelDownBtn = null;
    /**
     * 中部日誌
     */
    private JScrollPane middleLogPanel = null;
    private JTextArea logTextArea = null;
    private static final int TEXT_ROWS = 20;
    private static final int TEXT_COLUMNS = 50;
    /**
     * 南部狀態欄
     */
    private JPanel southStatusPanel = null;
    private JLabel southStatusLine = null;

    private DownloadFrame3() {
        setTitle("Familysearch網站家譜下載3.0");
        setResizable(false);
        int gap = 5; // 邊距和控件間距
        int contentWidth = 800;
        int contentHeight = 600;
        int frameWidth = contentWidth + 6;
        int frameHeight = contentHeight + 28;
        setBounds(100, 100, frameWidth, frameHeight);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(gap, gap, gap, gap));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);

        // 北部菜單
        northMenuPanel = new JPanel();
        int northX = gap;
        int northY = gap;
        int northWidth = contentWidth - gap * 2;
        int northHeight = 300;
        northMenuPanel.setBounds(northX, northY, northWidth, northHeight);
        northMenuPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        northMenuPanel.setLayout(null);
        // 頭菜單
        headMenuPanel = new JPanel();
        headMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        int headHeight = 30;
        headMenuPanel.setBounds(0, 0, northWidth, headHeight);
        // headMenuPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        generateHeadMenu();
        northMenuPanel.add(headMenuPanel);
        // 詳細菜單
        bodyMenuContent = new JPanel();
        bodyMenuContent.setLayout(null);
        bodyMenuPanel = new JScrollPane(bodyMenuContent);
        bodyMenuPanel.setBounds(0, headHeight, northWidth, northHeight
                - headHeight);
        bodyMenuPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        northMenuPanel.add(bodyMenuPanel);
        contentPane.add(northMenuPanel);

        // 中部控制
        int contrlX = gap;
        int contrlY = gap + northHeight;
        int contrlWidth = northWidth;
        int contrlHeight = 30;
        middleControllPanel = new JPanel();
        middleControllPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        middleControllPanel.setBounds(contrlX, contrlY, contrlWidth,
                contrlHeight);
        JLabel savelabel = new JLabel("保存目錄：", null, SwingConstants.RIGHT);
        savelabel.setPreferredSize(new Dimension(60, contrlHeight));
        savelabel.setVerticalAlignment(SwingConstants.CENTER);
        middleControllPanel.add(savelabel);
        middleChooseText = new JTextField(); // 基目錄
        middleChooseText.setEditable(false);
        middleChooseText.setText(System.getProperty("user.dir"));
        middleChooseText.setPreferredSize(new Dimension(300, contrlHeight));
        middleChooseText.setBackground(Color.WHITE);
        middleControllPanel.add(middleChooseText);
        middleChooseBtn = new JButton("選擇");
        downSelectBtn = new JButton("下載");
        deselectAllBtn = new JButton("重置");
        downAllBtn = new JButton("全下");
        cancelDownBtn = new JButton("取消");
        middleChooseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("選擇保存目錄");
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = jfc.showOpenDialog(DownloadFrame3.this);
                File file = null;
                if (JFileChooser.APPROVE_OPTION == result) {
                    file = jfc.getSelectedFile();
                    if (!file.isDirectory()) {
                        JOptionPane.showMessageDialog(null, "目錄不存在");
                        return;
                    }
                    String path = file.getAbsolutePath();
                    middleChooseText.setText(path);
                } else {
                    return;
                }
            }
        });
        downSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FamilyTreeItem parent = GlobalDataVO.headMenuItems
                        .get(GlobalDataVO.headMenuItems.size() - 1);
                doDownloadImages(parent, GlobalDataVO.selectedItems, true);
            }
        });
        deselectAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBodyMenu();
            }
        });
        downAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FamilyTreeItem parent = GlobalDataVO.headMenuItems
                        .get(GlobalDataVO.headMenuItems.size() - 1);
                try {
                    doDownloadImages(parent, parent.getChildren(), false);
                } catch (Exception e1) {
                    DownloadFrame3.addProgress("下載失败，獲取子節點失敗！");
                }
            }
        });
        // 取消
        cancelDownBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelDownload();
            }
        });
        downSelectBtn.setEnabled(false);
        deselectAllBtn.setEnabled(false);
        downAllBtn.setEnabled(false);
        cancelDownBtn.setEnabled(false);
        middleControllPanel.add(middleChooseBtn);
        middleControllPanel.add(downSelectBtn);
        middleControllPanel.add(deselectAllBtn);
        middleControllPanel.add(downAllBtn);
        middleControllPanel.add(cancelDownBtn);
        contentPane.add(middleControllPanel);

        // 中部日誌
        logTextArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        logTextArea.setEditable(false);
        logTextArea.setText("下載日誌打印...\n");
        middleLogPanel = new JScrollPane(logTextArea);
        int middleX = gap;
        int middleY = contrlY + contrlHeight + gap;
        int middleWidth = northWidth;
        int middleHeight = 210;
        middleLogPanel.setBounds(middleX, middleY, middleWidth, middleHeight);
        contentPane.add(middleLogPanel);

        // 南部狀態
        southStatusPanel = new JPanel();
        int southX = gap;
        int southY = middleY + middleHeight + gap;
        int southWidth = northWidth;
        int southHeight = 25;
        southStatusLine = new JLabel("新狀態欄...") {
            private static final long serialVersionUID = 1L;

            @Override
            public void setText(String text) {
                super.setText("狀態: " + text);
            }
        };
        southStatusPanel.setBounds(southX, southY, southWidth, southHeight);
        southStatusPanel.setLayout(null);
        southStatusLine.setBounds(0, 0, southWidth, southHeight);
        southStatusPanel.add(southStatusLine);
        contentPane.add(southStatusPanel);
    }

    /**
     * 頭菜單的生成
     * 
     * @author t
     */
    private void generateHeadMenu() {
        if (null == GlobalDataVO.headMenuTitles) {
            GlobalDataVO.headMenuTitles = new ArrayList<JLabel>();
        }
        // 控件比控件數據少，需要添加菜單
        if (GlobalDataVO.headMenuTitles.size() < GlobalDataVO.headMenuItems
                .size()) {
            int index = GlobalDataVO.headMenuTitles.size();

            // 生成菜單
            for (; index < GlobalDataVO.headMenuItems.size(); index++) {
                FamilyTreeItem head = GlobalDataVO.headMenuItems.get(index);
                final JLabel headlabel = generateHeadMenuOne(index, head);

                GlobalDataVO.headMenuTitles.add(headlabel);
                headMenuPanel.add(headlabel);
            }
        }
        headMenuPanel.validate();
        headMenuPanel.repaint();
    }

    /**
     * 詳細菜單生成
     * 
     * @author t
     */
    private void generateBodyMenu() {
        if (null == GlobalDataVO.bodyMenuTitles) {
            GlobalDataVO.bodyMenuTitles = new ArrayList<JLabel>();
        }
        // 淸空詳情菜單
        if (!GlobalDataVO.bodyMenuTitles.isEmpty()) {
            for (int i = GlobalDataVO.bodyMenuTitles.size() - 1; i >= 0; i--) {
                JLabel label = GlobalDataVO.bodyMenuTitles.remove(i);
                bodyMenuContent.remove(label);
            }
        }
        // 淸空已選中的圖片
        if (null != GlobalDataVO.selectedTitles
                && !GlobalDataVO.selectedTitles.isEmpty()) {
            for (int index = GlobalDataVO.selectedTitles.size() - 1; index >= 0; index--) {
                JLabel bodyLabel = GlobalDataVO.selectedTitles.get(index);
                deselectOneImage(bodyLabel);
            }
            GlobalDataVO.selectedItems.clear();
        }
        // 父節點
        try {
            // 最後一個頭菜單的子節點作爲詳細菜單
            int index = GlobalDataVO.headMenuItems.size() - 1;
            FamilyTreeItem parent = GlobalDataVO.headMenuItems.get(index);
            GlobalDataVO.bodyMenuItems = parent.getChildren();
            // 進入圖片列表了，就可以全下載
            // downAllBtn.setEnabled(GlobalDataVO.bodyMenuItems.get(0).isImage());
            try {
                boolean canDownAll = GlobalDataVO.bodyMenuItems.get(0)
                        .isImage()
                        || GlobalDataVO.bodyMenuItems.get(0).getChildren()
                                .get(0).isImage();
                downAllBtn.setEnabled(canDownAll);
            } catch (Exception e) {
                DownloadFrame3.addProgress("設置全下按鈕失敗！");
            }

            int labelHeight = 40;
            int column = 3;
            int gap = 3;
            for (int k = 0; k < GlobalDataVO.bodyMenuItems.size(); k++) {
                FamilyTreeItem body = GlobalDataVO.bodyMenuItems.get(k);
                JLabel bodyLabel = generateBodyMenuOne(body);
                bodyLabel
                        .setBorder(BorderFactory.createLineBorder(Color.WHITE));
                GlobalDataVO.bodyMenuTitles.add(bodyLabel);
                int width = (bodyMenuPanel.getWidth() - 20 - gap * (column + 1))
                        / column;
                int x = (k % column) * (width + gap) + gap; // 列下標爲：k % 3
                int y = (k / column) * (labelHeight + gap) + gap; // 行下標爲：k / 3
                bodyLabel.setBounds(x, y, width, labelHeight);
                bodyMenuContent.add(bodyLabel);
            }
            int newBodyMenuHeight = (GlobalDataVO.bodyMenuItems.size() / column + 1)
                    * (labelHeight + gap) + gap;
            // 重設大小，不然內容不全，且不出現滾動條
            bodyMenuContent.setPreferredSize(new Dimension(bodyMenuPanel
                    .getWidth() - 20, newBodyMenuHeight));
        } catch (Exception e1) {
            DownloadFrame3.addProgress("詳細菜單生成失败," + e1.getMessage());
        }
        bodyMenuPanel.validate();
        bodyMenuPanel.repaint();
    }

    /**
     * 生成一個詳細菜單項
     * 
     * @author t
     */
    private JLabel generateBodyMenuOne(FamilyTreeItem body) {
        String text = body.getTitle();
        final JLabel bodyLabel = new JLabel("<html><a><font color='blue'>"
                + text + "</font></a></html>");
        bodyLabel.setToolTipText(body.getTitle());
        bodyLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bodyLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = GlobalDataVO.bodyMenuTitles.indexOf(bodyLabel);
                FamilyTreeItem item = GlobalDataVO.bodyMenuItems.get(index);
                // 中間節點
                if (!item.isImage()) {
                    GlobalDataVO.headMenuItems.add(item);
                    // 菜單重新生成
                    generateHeadMenu();
                    generateBodyMenu();
                } else {
                    // 圖片
                    if (null == GlobalDataVO.selectedTitles) {
                        GlobalDataVO.selectedTitles = new ArrayList<JLabel>();
                        GlobalDataVO.selectedItems = new ArrayList<FamilyTreeItem>();
                    }
                    if (!GlobalDataVO.selectedTitles.contains(bodyLabel)) {
                        selectOneImage(bodyLabel);
                        GlobalDataVO.selectedItems.add(item);
                    } else {
                        deselectOneImage(bodyLabel);
                        GlobalDataVO.selectedItems.remove(item);
                    }
                    bodyMenuPanel.validate();
                    bodyMenuPanel.repaint();
                }
            }
        });
        return bodyLabel;
    }

    /**
     * 選中圖片
     * 
     * @author t
     */
    private void selectOneImage(final JLabel bodyLabel) {
        bodyLabel.setOpaque(true);
        bodyLabel.setBackground(Color.GREEN);
        bodyLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        GlobalDataVO.selectedTitles.add(bodyLabel);

        if (!downSelectBtn.isEnabled()) {
            downSelectBtn.setEnabled(true);
            deselectAllBtn.setEnabled(true);
        }
    }

    /**
     * 取消選中圖片
     * 
     * @author t
     */
    private void deselectOneImage(JLabel bodyLabel) {
        bodyLabel.setOpaque(false);
        bodyLabel.setBackground(bodyMenuPanel.getBackground());
        bodyLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        GlobalDataVO.selectedTitles.remove(bodyLabel);

        if (GlobalDataVO.selectedTitles.isEmpty()) {
            downSelectBtn.setEnabled(false);
            deselectAllBtn.setEnabled(false);
        }
    }

    /**
     * 生成一個頭菜單項
     * 
     * @author t
     */
    private JLabel generateHeadMenuOne(int index, FamilyTreeItem head) {
        String text = truncateHeadTitle(head.getTitle(), index == 0);
        final JLabel headlabel = new JLabel("<html><a><font color='blue'>"
                + text + "</font></a>&nbsp;&gt;</html>");
        headlabel.setToolTipText(head.getTitle());
        headlabel.setVerticalAlignment(SwingConstants.CENTER);
        headlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        headlabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = GlobalDataVO.headMenuTitles.indexOf(headlabel);
                if (GlobalDataVO.headMenuTitles.size() > 1) {
                    // 刪除菜單頁
                    for (int i = GlobalDataVO.headMenuTitles.size() - 1; i > index; i--) {
                        JLabel label = GlobalDataVO.headMenuTitles.remove(i);
                        headMenuPanel.remove(label);
                        GlobalDataVO.headMenuItems.remove(i);
                    }
                }
                // 頭菜單的生成
                generateHeadMenu();
                // 詳細菜單生成
                generateBodyMenu();
            }

        });
        return headlabel;
    }

    /**
     * 截斷文字爲了更好顯示
     * 
     * @author t
     */
    private static String truncateHeadTitle(String title, boolean isFirst) {
        if (null == title || "".equals(title)) {
            return "  ";
        } else if (isFirst) {
            return title;
        } else {
            int maxLen = 24;
            int titleLength = CharacterUtil.getLength2(title);
            String result = title;
            if (titleLength > maxLen) {
                while (CharacterUtil.getLength2(result) > maxLen - 1) {
                    result = result.substring(0, result.length() - 1);
                }
                result += "..";
            }
            return result;
        }
    }

    /**
     * 處理進度日誌展示
     * 
     * @author t
     */
    public static void addProgress(final String data) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getInstance().handleProgress(data);
            }
        });
    }

    // 處理進度日誌展示
    private void handleProgress(String pro) {
        southStatusLine.setText(pro);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS:");
        logTextArea.append(sdf.format(new Date()) + pro + "\n");
    }

    /**
     * 下載圖片
     * 
     * @param parent
     *            父節點
     * @param items
     *            可以是子節點，也可以是選中的若干個
     * @param redownload
     *            已经下載的是否重新下
     * @author t
     */
    private void doDownloadImages(FamilyTreeItem parent,
            List<FamilyTreeItem> items, boolean redownload) {
        if (null != downloadThread && !downloadThread.isFinished()) {
            DownloadFrame3.addProgress("目前正在下載中，請稍候。也可以點取消。");
            return;
        }

        if (null == middleChooseText.getText()
                || "".equals(middleChooseText.getText())) {
            DownloadFrame3.addProgress("請先指定下載保存目錄");
            return;
        }
        if (null == items || items.isEmpty()) {
            DownloadFrame3.addProgress("未選中任何圖片");
            return;
        }
        String filePath = middleChooseText.getText();
        // 如果是圖片，帶上父節點標題
        if (items.get(0).isImage()) {
            filePath = filePath + File.separator + parent.getTitle()
                    + File.separator;
        }
        downloadThread = new DownloadThread(items, filePath);
        downloadThread.setRedownload(redownload);
        new Thread(downloadThread).start();
        // 可以取消
        cancelDownBtn.setEnabled(true);
    }

    /**
     * 取消下載
     * 
     * @author t
     * @time 2016-8-21下午12:44:39
     */
    public void cancelDownload() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                downloadThread.cancel();
            }
        });
    }

    /**
     * 下載完成後，包括成功和取消
     * 
     * @author t
     */
    public static void downloadFinished() {
        getInstance().downloadThread = null;
        getInstance().cancelDownBtn.setEnabled(false);
    }

}
