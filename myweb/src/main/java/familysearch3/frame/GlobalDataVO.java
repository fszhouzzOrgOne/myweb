package familysearch3.frame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import familysearch3.vo.FamilyTreeItem;

public class GlobalDataVO {
    // 根元素
    static FamilyTreeItem root = null;

    static List<FamilyTreeItem> headMenuItems = null;
    static List<JLabel> headMenuTitles = null;

    static List<FamilyTreeItem> bodyMenuItems = null;
    static List<JLabel> bodyMenuTitles = null;
    /**
     * 選中的圖片
     */
    static List<FamilyTreeItem> selectedItems = null;
    /**
     * 選中的標籤
     */
    static List<JLabel> selectedTitles = null;

    static {
        FamilyTreeItem root = new FamilyTreeItem();
        root.setType("Collection");
        root.setDescriptionId("sd_c_1787988");
        root.setParent(null);
        root.setTitle("中國, 族譜收藏 1239-2014年".replace(" ", "").replace(",", "，"));
        root.setIdentifier("https://familysearch.org/recapi/collections/1787988/waypoints");

        headMenuItems = new ArrayList<FamilyTreeItem>();
        headMenuItems.add(root);
    }
}
