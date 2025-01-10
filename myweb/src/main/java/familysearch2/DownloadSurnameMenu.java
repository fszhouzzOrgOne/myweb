package familysearch2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import familysearch2.vo.FamilyTreeItem;

/**
 * 下載一個姓氏所有家譜目錄
 * 
 * 
 * @author 日月遞炤
 * @time 2017年10月21日 下午12:04:37
 */
public class DownloadSurnameMenu {
    static private int count = 0;

    static private String SURNAME = "Zhou周";

    public static String menuBaseDir = "src\\java\\familysearch2\\";
    public static String menuFileName = "genealogy2.txt";

    public static void main(String[] args) throws Exception {
        FamilyTreeItem root = new FamilyTreeItem();
        root.setType("Collection");
        root.setDescriptionId("sd_c_1787988");
        root.setParent(null);
        root.setTitle("中國, 族譜收藏 1239-2014年".replace(" ", "").replace(",", "，"));
        root.setIdentifier("https://familysearch.org/recapi/collections/1787988/waypoints");

        for (FamilyTreeItem child : root.getChildren()) {
            if (SURNAME.equals(child.getTitle())) {
                printAllSubmenu(child, child);
            }
        }
    }

    /**
     * 遞歸遍歷所有的子節點，打印目錄
     * 
     * @author 日月遞炤
     * @time 2017年10月21日 下午12:27:12
     * @param item
     * @param currParent
     * @throws Exception
     */
    public static void printAllSubmenu(FamilyTreeItem item, FamilyTreeItem currParent) throws Exception {
        for (FamilyTreeItem child : currParent.getChildren()) {
            // 是圖片，不再找子節點，開始打卽currParent底目錄
            if (child.getType().endsWith("DigitalArtifact")) {
                printMenu(item, currParent);
                break;
            } else {
                // printMenu(item, child);
                printAllSubmenu(item, child);
            }
        }
    }

    /**
     * 打印出從topParent到item的目錄
     * 
     * @author 日月遞炤
     * @time 2017年10月21日 下午12:18:03
     * @param topParent
     * @param item
     * @throws Exception
     */
    private static void printMenu(FamilyTreeItem topParent, FamilyTreeItem item) throws Exception {
        List<FamilyTreeItem> menus = new ArrayList<FamilyTreeItem>();
        FamilyTreeItem curr = item;
        do {
            menus.add(curr);
            curr = curr.getParent();
        } while (!topParent.equals(curr));
        // 打印
        String menuStr = "";
        for (int i = menus.size() - 1; i >= 0; i--) {
            menuStr += menus.get(i).getTitle();
            menuStr += ">";
        }
        menuStr += ((null != item.getChildren()) ? item.getChildren().size() : 0) + "頁";
        menuStr = (++count) + "，" + menuStr;
        updateContent(menuFileName, menuStr, true);
        // 休息一秒
        sleepForAWhile();
    }

    /**
     * 休息一下子
     * 
     * @author t
     */
    private static void sleepForAWhile() {
        try {
            Random random = new Random();
            long millis = (long) (random.nextDouble() * 5 * 1000);
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

    /**
     * 寫入文件<br />
     * 參見：http://blog.sina.com.cn/s/blog_46e73e77010134ok.html
     * 
     * @author 日月遞炤
     * @time 2017年10月21日 下午12:48:03
     * @param name
     * @param content
     * @param append
     * @return
     */
    public static boolean updateContent(String name, String content, boolean append) {
        System.out.println(content);
        boolean res = true;
        String path = menuBaseDir + name;
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, append);
            if (append) {
                content = System.getProperty("line.separator") + content;
            }
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            res = false;
            ex.printStackTrace();
        }
        return res;
    }
}
