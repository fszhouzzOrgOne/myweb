package familysearch3.vo;

import java.util.ArrayList;
import java.util.List;

import familysearch3.frame.DownloadFrame3;
import familysearch3.util.CharacterUtil;
import familysearch3.xml.FamilyTreeXmlParser;

/**
 * 家譜信息節點，如綱站按地區分上下級
 * 
 * @author 周子照
 */
public class FamilyTreeItem {
    private String descriptionId; // 文檔中的標識
    private String type; // 節點類型
    private String title = null; // 題名
    private String identifier = null; // xml信息描述地址
    private FamilyTreeItem parent = null; // 父節點
    private List<FamilyTreeItem> children = null; // 子節點列表

    private volatile boolean isFinished = false; // 是不是下載完成

    /**
     * 是圖片節點
     * 
     * @author t
     */
    public boolean isImage() {
        return null != getType() && getType().endsWith("DigitalArtifact");
    }

    /**
     * 添加子節點
     * 
     * @author 周子照
     * @return void
     */
    public void addChild(FamilyTreeItem item) {
        if (null == children) {
            children = new ArrayList<FamilyTreeItem>();
        }
        item.setParent(this);
        children.add(item);
    }

    /**
     * 按題名找出子節點
     * 
     * @author 周子照
     * @return FamilyTreeItem
     * @throws Exception
     */
    public FamilyTreeItem getChildByTitle(String title) throws Exception {
        if (null != getChildren() && null != title) {
            for (FamilyTreeItem child : getChildren()) {
                if (title.equals(child.getTitle())) {
                    return child;
                }
            }
        }
        return null;
    }

    public List<FamilyTreeItem> getChildren() throws Exception {
        if (!this.isImage() && null == children) {
            new FamilyTreeXmlParser(this).parse();
        }
        return children;
    }

    public String getTitle() {
        // 如果沒有名字，生成一個名字
        if (null == this.title) {
            try {
                int myIndex = parent.getChildren().indexOf(this);
                this.title = CharacterUtil.formatNumber(myIndex + 1, String
                        .valueOf(parent.getChildren().size()).length() + 2)
                        + ".jpg";
            } catch (Exception e) {
                DownloadFrame3.addProgress(parent.getTitle() + "的某個子節點生成名字失敗");
            }
        }
        return title;
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
        return CharacterUtil.formatNumber(familyIndex + 1, 3)
                + CharacterUtil.formatNumber(imageIndex + 1, 4) + ".jpg";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public FamilyTreeItem getParent() {
        return parent;
    }

    public void setParent(FamilyTreeItem parent) {
        this.parent = parent;
    }

    public String getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(String descriptionId) {
        this.descriptionId = descriptionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FamilyTreeItem [type=" + type + ", title=" + title
                + ", identifier=" + identifier + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((descriptionId == null) ? 0 : descriptionId.hashCode());
        result = prime * result
                + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FamilyTreeItem other = (FamilyTreeItem) obj;
        if (descriptionId == null) {
            if (other.descriptionId != null)
                return false;
        } else if (!descriptionId.equals(other.descriptionId))
            return false;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public boolean isFinished() {
        return isFinished;
    }

}
