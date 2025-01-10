package familysearch3.thread;

import java.util.List;

import familysearch3.vo.FamilyTreeItem;

/**
 * 抽象的下載工具
 * 
 * @author 周子照
 */
public abstract class AbstractDownloadThread implements Runnable {

    /** 已經下載的是否重新下 */
    private Boolean redownload = null;
    /** 是否取消運行 */
    private Boolean isCanceled = false;

    protected List<FamilyTreeItem> items = null; // 家譜
    /** 保存路徑 */
    protected String filePath = null;

    public AbstractDownloadThread(List<FamilyTreeItem> items, String filePath) {
        this.items = items;
        this.filePath = filePath;
    }

    /**
     * 取消運行
     * 
     * @author t
     */
    public void cancel() {
        isCanceled = true;
    }

    /**
     * 是否已經取消
     * 
     * @author t
     */
    public Boolean isCanceled() {
        return isCanceled;
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
                if (item.isImage() && !item.isFinished()) {
                    return false;
                } else {
                    // 多個譜書
                    try {
                        for (FamilyTreeItem image : item.getChildren()) {
                            if (!image.isFinished()) {
                                return false;
                            }
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 文件存在，看是否要重新下載
     * 
     * @author t
     * @time 2016-8-27上午12:50:01
     */
    public Boolean getRedownload() {
        return redownload;
    }

    /**
     * 文件存在，看是否要重新下載
     * 
     * @author t
     * @time 2016-8-27上午12:50:06
     */
    public void setRedownload(Boolean redownload) {
        if (this.redownload == null) {
            this.redownload = redownload;
        }
    }
}
