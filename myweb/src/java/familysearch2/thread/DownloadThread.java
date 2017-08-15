package familysearch2.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

import familysearch2.vo.FamilyTreeItem;

/**
 * 下載線程類
 * 
 * @author 周子照
 */
public class DownloadThread extends AbstractDownloadThread {

    public DownloadThread(List<FamilyTreeItem> items, String filePath) {
        super(items, filePath);
    }

    @Override
    public void run() {
        if (null == items || items.isEmpty()) {
            return;
        }
        // 默認的存儲路徑
        if (null == filePath || "".equals(filePath.trim())) {
            filePath = "D:/DownloadThreadFils/"
                    + Thread.currentThread().getName() + "/";
        }

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        System.out.println("開始下載：" + filePath);
        // 綱絡有限制了，只能慢慢下載
        for (FamilyTreeItem item : items) {
            doDownload(item);

            while (!item.isFinished()) {
                File dest = new File(filePath + item.getTitle());
                if (dest.exists()) {
                    System.out.println("删除文件：" + filePath + item.getTitle());
                    dest.delete();
                }

                sleepForAWhile();
                doDownload(item);
            }
        }
    }

    /**
     * 休息一下子
     * 
     * @author t
     */
    private void sleepForAWhile() {
        try {
            long mi = new Random().nextInt(10) + 5;
            Thread.sleep(mi * 1000L);
            System.out.println("休息了" + mi + "秒鐘");
        } catch (Exception e) {
        }
    }

    /**
     * 下載一張圖片
     * 
     * @author t
     */
    private void doDownload(FamilyTreeItem item) {
        InputStream inStream = null;
        FileOutputStream fs = null;
        String absoluteName = filePath + item.getTitle();
        File dest = null;
        try {
            dest = new File(absoluteName);
            
            // 文件存在，不再下載
            // 不能比較本地文件长度和服务器不相等dest.length() != conn.getContentLength()，可能被封鎖
            if (dest.exists()) {
                item.setFinished(true);
                dest = null;
                return;
            }
            
            dest.createNewFile();
            URL urlObj = new URL(item.getIdentifier());
            HttpURLConnection conn = (HttpURLConnection) urlObj
                    .openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Language", "zh-CN");
            conn.setRequestProperty("Charset", "UTF-8");
            // 一定要設置接收類型: Accept=""都可以，但不能没有，否則找不到文件
            conn.setRequestProperty(
                    "Accept",
                    "*/*, image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                            + "application/x-shockwave-flash, application/xaml+xml, "
                            + "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
                            + "application/x-ms-application, application/vnd.ms-excel, "
                            + "application/vnd.ms-powerpoint, application/msword");

            inStream = conn.getInputStream();
            fs = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int byteread = 0;
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            item.setFinished(true); // 完成狀態
            System.out.println("成功：" + absoluteName);
        } catch (Exception e) {
            item.setFinished(false);
            System.out.println("下載失敗：" + absoluteName + " 地址："
                    + item.getIdentifier());
            try {
                // 删除它
                if (null != dest && dest.exists()) {
                    dest.delete();
                }
            } catch (Exception ed) {
            }
        } finally {
            try {
                if (null != fs) {
                    fs.flush();
                    fs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (null != inStream) {
                    inStream.close();
                }
            } catch (Exception e) {
            }
        }

        // 成功了就休息下
        if (item.isFinished()) {
            sleepForAWhile();
        }
    }
}
