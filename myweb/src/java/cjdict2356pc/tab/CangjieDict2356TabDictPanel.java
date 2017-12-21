package cjdict2356pc.tab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cjdict2356pc.dto.Group;
import cjdict2356pc.dto.Item;
import cjdict2356pc.mb.SettingDictMbUtils;
import cjdict2356pc.view.Cangjie2356ListItemList;
import cjdict2356pc.view.Cangjie2356ListView;
import cjdict2356pc.view.Cangjie2356ListViewGroup;
import cjdict2356pc.view.Cangjie2356ListViewItem;

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

    /**
     * 查詢結果展示列表
     */
    private JScrollPane resListPanel = null;

    /**
     * 所有的分組和查詢結果
     */
    private List<Group> gData = null;
    /** 已經打開的分組，分組代碼 */
    private List<String> openGroupCodes = new ArrayList<String>();

    public CangjieDict2356TabDictPanel() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        int gap = 10;
        int compHeight = 30;

        int labelWidth = 100;
        int labelX = gap;
        int labelY = 0;
        searchLabel = new JLabel("輸入查詢：", null, SwingConstants.RIGHT);
        labelY = gap;
        searchLabel.setBounds(labelX, labelY, labelWidth, compHeight);
        add(searchLabel);

        searchField = new JTextField(null, 14);
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
        searchButton.addActionListener(new SearchButtonActionListener());
        add(searchButton);

        resListPanel = getJScrollPane();
        int listX = labelX;
        int listY = gap + compHeight + gap;
        int listWidth = 425;
        int listHeight = 440;
        resListPanel.setBounds(listX, listY, listWidth, listHeight);
        add(resListPanel);

        setgData(null);
    }

    /**
     * 生成列表
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月21日下午5:22:19
     * @return
     */
    private JScrollPane getJScrollPane() {

        String[] words = { "quick", "brown", "hungry", "wild" };
        JList list = new JList(words);
        JScrollPane resListPanel = new JScrollPane(list);
        return resListPanel;
    }

    /**
     * 設置查詢結果數據
     * 
     * @author fsz
     * @time 2017年9月27日上午9:44:04
     * @param gData
     */
    public void setgData(List<Group> gDataPar) {
        if (null == gDataPar || gDataPar.isEmpty()) {
            gDataPar = SettingDictMbUtils.initGroupDatas();
        }
        gData = gDataPar;

        openGroupCodes.clear();

        updateResListPanel();
    }

    /**
     * 更新查詢結果展示列表
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月21日 下午11:41:39
     */
    public void updateResListPanel() {
        if (null == resListPanel) {
            return;
        }
        List<Cangjie2356ListView> its = new ArrayList<Cangjie2356ListView>();
        for (Group gp : gData) {
            its.add(new Cangjie2356ListViewGroup(gp));
            for (Item it : gp.getItems()) {
                its.add(new Cangjie2356ListViewItem(it));
            }
        }

        Cangjie2356ListItemList list = new Cangjie2356ListItemList(its);

        resListPanel.setViewportView(list);
        resListPanel.repaint();
    }

    /**
     * 查詢按鈕點擊事件
     * 
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月21日 下午9:47:54
     */
    class SearchButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (null != searchField) {
                List<Group> gData = null;
                String textInput = searchField.getText();
                if (null != textInput && !"".equals(textInput.trim().replaceAll(" ", ""))) {
                    textInput = textInput.trim().replaceAll(" ", "").toLowerCase();
                    String pattern = "[a-zA-Z]{1,}";
                    if (textInput.matches(pattern)) {
                        gData = SettingDictMbUtils.selectDbByCode(textInput);
                    } else {
                        gData = SettingDictMbUtils.selectDbByChar(textInput);
                    }
                } else {
                    searchField.setText("");
                    searchField.requestFocus();
                    JOptionPane.showMessageDialog(null, "輸入編碼或文字查詢。");
                    return;
                }
                setgData(gData);
            }
        }

    }
}
