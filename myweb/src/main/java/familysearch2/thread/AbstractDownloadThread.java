package familysearch2.thread;

import java.util.List;

import familysearch2.vo.FamilyTreeItem;

/**
 * 抽象的下載工具
 * 
 * @author 周子照
 */
public abstract class AbstractDownloadThread implements Runnable {

    protected List<FamilyTreeItem> items = null; // 家譜
    protected String filePath = null; // 保存路徑

    public AbstractDownloadThread(List<FamilyTreeItem> items, String filePath) {
        this.items = items;
        this.filePath = filePath;
    }

    /**
     * 是否完成
     * 
     * @author 周子照
     * @return boolean
     */
    public boolean isFinished() {
        if (null != items) {
            for (FamilyTreeItem item : items) {
                if (!item.isFinished()) {
                    return false;
                }
            }
        }
        return true;
    }
}
