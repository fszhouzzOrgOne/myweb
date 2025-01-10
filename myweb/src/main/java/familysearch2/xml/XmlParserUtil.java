package familysearch2.xml;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM解析xml工具
 * 
 * @author 周子照
 */
public class XmlParserUtil {

    /**
     * 從地址獲取DOM文档對象
     * 
     * @author 周子照
     */
    public static Document getDocumentByUrl(String url) throws Exception {
        if (null == url || "".equals(url.trim())) {
            return null;
        }
        URL urlObj = new URL(url);
        URLConnection conn = urlObj.openConnection();
        InputStream inStream = conn.getInputStream();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(inStream);
    }

    /**
     * 按節點名得到所有直接子節點
     * 
     * @author 周子照
     */
    public static List<Node> getChindrenByNodeName(Node root, String name) {
        List<Node> result = new ArrayList<Node>();
        NodeList nodeList = root.getChildNodes();
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node item = nodeList.item(index);
            if (name.equals(item.getNodeName())) {
                result.add(item);
            }
        }
        return result;
    }
}
