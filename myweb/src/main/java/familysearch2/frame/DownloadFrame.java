package familysearch2.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import familysearch2.vo.FamilyTreeItem;

public class DownloadFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * 所有要生成下拉框的的家譜信息下拉列表
     */
    private List<FamilyTreeItem> items = null;
    private List<JComboBox> boxes = null;

    private void mInit() {
        items = new ArrayList<FamilyTreeItem>();
        boxes = new ArrayList<JComboBox>();

        FamilyTreeItem root = new FamilyTreeItem();
        root.setType("Collection");
        root.setDescriptionId("sd_c_1787988");
        root.setParent(null);
        root.setTitle("中國, 族譜收藏 1239-2014年".replace(" ", "").replace(",", "，"));
        root.setIdentifier("https://familysearch.org/recapi/collections/1787988/waypoints");
        items.add(0, root);
    }

    public DownloadFrame() {
        mInit();

        this.setTitle("Familysearch網站家譜下載");
        this.setResizable(false);
        this.setBounds(100, 100, 720, 400);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null); // new FlowLayout(FlowLayout.CENTER, 5,
                                     // 5));
        try {
            this.setVisible(true);
            this.setTitle(this.getCleanTitle() + "（查詢中...）");
            resetComboBoxes(contentPane);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "查詢失敗", "錯誤",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 得到乾淨的標題
     * 
     * @author 周子照
     * @return String
     */
    public String getCleanTitle() {
        String cleanTitle = this.getTitle();
        Pattern p = Pattern.compile("[\\(（].*[\\)）]");
        Matcher m = p.matcher(cleanTitle);
        while (m.find()) {
            cleanTitle = cleanTitle.replace(m.group(), "");
        }
        return cleanTitle;
    }

    public void flashFrame() {
        this.setVisible(false);
        this.setVisible(true);
    }

    public void resetComboBoxes(JPanel contentPane) throws Exception {
        // 如果原來沒有下拉列表，全部生成
        if (boxes.size() < items.size()) {
            for (int index = boxes.size(); index < items.size(); index++) {
                FamilyTreeItem item = items.get(index);
                JComboBox comboBox = null;
                boolean shouldAddbox = true;
                for (FamilyTreeItem child : item.getChildren()) {
                    // 是圖片，不再新增下拉列表
                    if (child.getType().endsWith("DigitalArtifact")) {
                        shouldAddbox = false;
                        break;
                    }
                    if (null == comboBox) {
                        comboBox = new JComboBox();
                        comboBox.addItem("");
                    }
                    comboBox.addItem(child.getTitle());
                }
                // 選中事件
                if (shouldAddbox) {
                    comboBox.addItemListener(new MyItemListener(this, item));
                    boxes.add(comboBox);
                }
            }
        }

        // 加入面板中
        contentPane.removeAll();
        int labelWidth = 250;
        int comboWidth = 400;
        int labelX = 10;
        int comboX = labelWidth + labelX + 10;
        int height = 30;
        int y;
        int index = 0;
        for (; index < boxes.size(); index++) {
            final FamilyTreeItem item = items.get(index);
            JLabel label = new JLabel(item.getTitle() + "：", null,
                    SwingConstants.RIGHT);
            y = index * 40 + 10;
            label.setBounds(labelX, y, labelWidth, height);
            contentPane.add(label);

            JComboBox comboBox = boxes.get(index);
            comboBox.setBounds(comboX, y, comboWidth, height);
            contentPane.add(comboBox);
        }

        // 如果此時items個數大于boxes，說明到了圖片一層了，可以開始下載了
        if (items.size() == boxes.size() + 1) {
            final FamilyTreeItem targetItem = items.get(boxes.size());
            JLabel label = new JLabel("準備下載“" + targetItem.getTitle() + "”，共"
                    + targetItem.getChildren().size() + "個圖片：", null,
                    SwingConstants.CENTER);
            y = index * 40 + 10;
            label.setBounds(labelX, y, comboX + comboWidth, height);
            contentPane.add(label);
            index++;

            // 基目錄的選擇
            label = new JLabel("請先選擇保存目錄：", null, SwingConstants.RIGHT);
            y = index * 40 + 10;
            label.setBounds(labelX, y, labelWidth, height);
            contentPane.add(label);
            final JTextField text = new JTextField(); // 基目錄
            text.setEditable(false);
            text.setBounds(comboX, y, labelWidth, height);
            text.setText(System.getProperty("user.dir"));
            contentPane.add(text);
            JButton choosePathButton = new JButton("選"); // 選擇文件按鈕
            choosePathButton.setBounds(comboX + labelWidth + 5, y, 40, height);
            choosePathButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jfc = new JFileChooser();
                    jfc.setDialogTitle("選擇保存目錄");
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int result = jfc.showOpenDialog(DownloadFrame.this);
                    File file = null;
                    if (JFileChooser.APPROVE_OPTION == result) {
                        file = jfc.getSelectedFile();
                        if (!file.isDirectory()) {
                            JOptionPane.showMessageDialog(null, "目錄不存在");
                            return;
                        }
                        String path = file.getAbsolutePath();
                        text.setText(path);
                    } else {
                        return;
                    }
                }
            });
            contentPane.add(choosePathButton);
            index++;

            // 下載按鈕
            JButton downloadButton = new JButton("開始下載");
            y = index * 40 + 10;
            downloadButton.setBounds(comboX, y, 100, height);
            downloadButton.addActionListener(new MyDownloadListener(targetItem,
                    text, this));

            index++;
            JLabel tipLabel = new JLabel(
                    "注意：提示下載失敗，重新下載卽可；圖片未下載完整，刪除該文件，重新下載卽可；"
                            + "請查看對應目錄是否下完，界面上不會有表示", null,
                    SwingConstants.CENTER);
            y = index * 40 + 10;
            tipLabel.setBounds(labelX, y, comboX + comboWidth, height);
            contentPane.add(downloadButton);
            contentPane.add(tipLabel);
        }

        contentPane.repaint();

        this.setTitle(this.getCleanTitle());
        this.flashFrame();
    }

    /**
     * 按題名查找下拉框對應的節點
     * 
     * @author 周子照
     * @return Integer
     */
    public Integer getItemIndexByTitle(String title) {
        // 從後面開始找，因有多個名叫“不詳”的節點
        for (int index = items.size() - 1; index >= 0; index--) {
            if (title.equals(items.get(index).getTitle())) {
                return index;
            }
        }
        return null;
    }

    public List<FamilyTreeItem> getItems() {
        return items;
    }

    public void setItems(List<FamilyTreeItem> items) {
        this.items = items;
    }

    public List<JComboBox> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<JComboBox> boxes) {
        this.boxes = boxes;
    }

}