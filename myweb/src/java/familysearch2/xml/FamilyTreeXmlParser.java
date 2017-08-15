package familysearch2.xml;

import java.net.URLEncoder;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import familysearch2.vo.FamilyTreeItem;

/**
 * 下載xml解析，它的子節點列表
 * 
 * @author 周子照
 */
public class FamilyTreeXmlParser {
    
    private String oldIdentifier = null;

    private FamilyTreeItem treeItem = null; // 節點信息

    public FamilyTreeXmlParser(FamilyTreeItem treeItem) {
        oldIdentifier = treeItem.getIdentifier();
        this.treeItem = treeItem;
    }

    public void parse() throws Exception {
        System.out.println("開始查詢“" + treeItem.getTitle() + "”："
                + treeItem.getIdentifier());
        doParse();
        
        treeItem.setFinished(true); // 完成狀態
        System.out.println("查詢完成“" + treeItem.getTitle() + "”，共"
                + treeItem.getChildren().size() + "個子節點。");
    }

    private boolean doParse() throws Exception {
        boolean paginatedSearch = false;
        Document doc = XmlParserUtil.getDocumentByUrl(treeItem.getIdentifier());
        if (null == doc) {
            return paginatedSearch;
        }
        Node root = doc.getChildNodes().item(0);

        List<Node> nodeList = XmlParserUtil.getChindrenByNodeName(root,
                "sourceDescription");
        addNodeToChildren(nodeList);
        
        List<Node> links = XmlParserUtil.getChindrenByNodeName(root, "link");
        // 獲取rel=next的href属性，設置爲treeItem.setIdentifier(identifier);
        for (Node linkNode : links) {
            String rel = linkNode.getAttributes().getNamedItem("rel")
                    .getNodeValue();
            if ("next".equals(rel)) {
                String newIden = linkNode.getAttributes().getNamedItem("href")
                        .getNodeValue() + "&count=1000";
                treeItem.setIdentifier(newIden);   
                paginatedSearch = true;
            }
        }
        System.out.println("paginatedSearch: " + paginatedSearch);
        if (paginatedSearch) {
            System.out.println("繼續查詢“" + treeItem.getTitle() + "”："
                    + treeItem.getIdentifier());
            paginatedSearch = doParse();
        } else {
            treeItem.setIdentifier(oldIdentifier);
        }
        return paginatedSearch;
    }

    /**
     * 把子節點找出來
     * 
     * @author 周子照
     * @return void
     */
    private void addNodeToChildren(List<Node> nodeList)
            throws Exception {
        for (int index = 0; index < nodeList.size(); index++) {
            Node item = nodeList.get(index);
            String type = item.getAttributes().getNamedItem("resourceType")
                    .getNodeValue();
            // componentOf
            List<Node> componentOfs = XmlParserUtil.getChindrenByNodeName(item,
                    "componentOf");
            if (null != componentOfs && !componentOfs.isEmpty()) {
                String componentOf = componentOfs.get(0).getAttributes()
                        .getNamedItem("description").getNodeValue();
                // 只添加父節點標識對應的子節點
                if (componentOf != null
                        && componentOf.endsWith(treeItem.getDescriptionId())) {
                    FamilyTreeItem info = new FamilyTreeItem();
                    // resourceType
                    info.setType(type);
                    // title
                    List<Node> titles = XmlParserUtil.getChindrenByNodeName(
                            item, "title");
                    if (null != titles && !titles.isEmpty()) {
                        String temp = titles.get(0).getFirstChild()
                                .getNodeValue();
                        // 名稱中的標點換成中文
                        temp = temp.replace(" ", "").replace(":", "：")
                                .replace(",", "，");
                        info.setTitle(temp);
                    }
                    // identifier
                    List<Node> identifiers = XmlParserUtil
                            .getChindrenByNodeName(item, "identifier");
                    if (null != identifiers && !identifiers.isEmpty()) {
                        String iden = identifiers.get(0).getFirstChild()
                                .getNodeValue();
                        // 中間節點，加入數量&count=5000，不然默認只查詢100張圖片
                        if (info.getType().endsWith("Collection")
                                && !iden.contains("count")) {
                            iden = iden + "&count=1000";
                        }
                        // 圖片節點
                        if (info.getType().endsWith("DigitalArtifact")) {
                            // 截去參數
                            iden = iden.substring(0, iden.indexOf("?"));
                            // 改成下載地址
                            iden = generateDownloadUrl(iden);
                        }
                        info.setIdentifier(iden);
                    }

                    // 默認id
                    info.setDescriptionId("src_1");
                    treeItem.addChild(info);
                }
            }
        }
    }

    /**
     * 生成下載地址
     * 
     * @author t
     */
    private String generateDownloadUrl(String iden) throws Exception {
        // 不能走下載接口，會被監控而不能批量下載
        @SuppressWarnings("unused")
        String download = "https://familysearch.org/image/download?uri="
                + URLEncoder.encode(iden, "UTF-8");
        String distjpg = iden + "/dist.jpg";
        return distjpg;
    }
}
