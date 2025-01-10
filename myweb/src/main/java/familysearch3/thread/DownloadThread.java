package familysearch3.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

import familysearch3.frame.DownloadFrame3;
import familysearch3.vo.FamilyTreeItem;

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

        long start = System.currentTimeMillis();
        int totalBookCnt = 0;
        int totalImageCnt = 0;
        // 全是圖片
        if (items.get(0).isImage()) {
            totalBookCnt = 1;
            totalImageCnt = items.size();
        } else { // 多個譜書
            totalBookCnt = items.size();
        }
        int bookFailCnt = 0;
        int imageFailCnt = 0;
        int imageSuccessCnt = 0;
        DownloadFrame3.addProgress("開始下載：" + filePath);
        // 綱絡有限制了，只能慢慢下載
        outerFor: for (FamilyTreeItem item : items) {
            // 如果是張圖片
            if (item.isImage()) {
                // 取消運行了
                if (isCanceled()) {
                    break outerFor;
                }

                long startTemp = System.currentTimeMillis();
                doDownload(item, filePath);

                // 取消運行了
                if (isCanceled()) {
                    break outerFor;
                }

                // 未成功就重試
                if (!item.isFinished()) {
                    retryDownload(item, filePath);
                }
                // 重試還未成功
                if (!item.isFinished()) {
                    imageFailCnt++;
                } else {
                    // 排除秒下的圖片，因爲是已經存在的
                    if ((System.currentTimeMillis() - startTemp) > 1000) {
                        imageSuccessCnt++;
                    }
                }
            } else {
                String subFilePath = filePath + File.separator
                        + item.getTitle() + File.separator;
                File subdir = new File(subFilePath);
                if (!subdir.exists()) {
                    subdir.mkdir();
                }
                DownloadFrame3.addProgress("開始下載：" + subFilePath);
                // 下載多個譜書時
                List<FamilyTreeItem> images = null;
                try {
                    images = item.getChildren();
                } catch (Exception e) {
                    DownloadFrame3.addProgress("譜書《" + item.getTitle()
                            + "》加載圖片失敗，跳過。");
                    bookFailCnt++;
                    continue;
                }
                if (null != images && !images.isEmpty()) {
                    totalImageCnt += images.size();
                    for (FamilyTreeItem image : images) {
                        // 取消運行了
                        if (isCanceled()) {
                            break outerFor;
                        }

                        long startTemp = System.currentTimeMillis();
                        doDownload(image, subFilePath);

                        // 取消運行了
                        if (isCanceled()) {
                            break outerFor;
                        }

                        // 未成功就重試
                        if (!image.isFinished()) {
                            retryDownload(image, subFilePath);
                        }
                        // 重試還未成功
                        if (!image.isFinished()) {
                            imageFailCnt++;
                        } else {
                            // 排除秒下的圖片，因爲是已經存在的
                            if ((System.currentTimeMillis() - startTemp) > 1000) {
                                imageSuccessCnt++;
                            }
                        }
                    }
                }
            }
        }

        String time = "耗時" + (System.currentTimeMillis() - start) + "毫秒。";
        String speed = imageSuccessCnt == 0 ? ""
                : "下載速度約"
                        + (long) ((double) (System.currentTimeMillis() - start) / imageSuccessCnt)
                        + "毫秒每張。";
        String result = "共" + totalBookCnt + "本譜書，下載其中" + totalImageCnt
                + "張圖片。失敗" + bookFailCnt + "本譜書，失敗" + imageFailCnt + "張圖片。"
                + time + speed;
        if (super.isFinished()) {
            DownloadFrame3.addProgress("下載完成：" + result);
            DownloadFrame3.downloadFinished();
            return;
        }
        // 未完成有兩種情況：取消、有錯
        if (isCanceled()) {
            DownloadFrame3.addProgress("下載已取消：" + result);
            DownloadFrame3.downloadFinished();
        } else {
            DownloadFrame3.addProgress("下載結束：。" + result);
            DownloadFrame3.downloadFinished();
        }
    }

    /**
     * 下載一張圖片
     * 
     * @author t
     */
    private void doDownload(FamilyTreeItem item, String filePath) {
        InputStream inStream = null;
        FileOutputStream fs = null;
        String absoluteName = filePath + item.getTitle();
        File dest = null;
        try {
            dest = new File(absoluteName);

            // 文件存在，看是否要重新下載
            if (dest.exists()) {
                if (this.getRedownload() != null
                        && true == this.getRedownload()) {
                    dest.delete();
                } else {
                    item.setFinished(true);
                    dest = null;
                    return;
                }
            }

            long start = System.currentTimeMillis();
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
            DownloadFrame3.addProgress("下載成功，耗時"
                    + (System.currentTimeMillis() - start) + "毫秒："
                    + absoluteName);
        } catch (Exception e) {
            item.setFinished(false);
            DownloadFrame3.addProgress("下載失敗：" + absoluteName + " 地址："
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

        // 無論如何， 都休息一下
        sleepForAWhile();
    }

    /**
     * 重試下載三次
     * 
     * @author t
     * @time 2016-8-27上午12:10:27
     */
    private void retryDownload(FamilyTreeItem item, String filePath) {
        int retryTimes = 0;
        while (!item.isFinished()) {
            File dest = new File(filePath + item.getTitle());
            if (dest.exists()) {
                DownloadFrame3
                        .addProgress("删除文件：" + filePath + item.getTitle());
                dest.delete();
            }

            // 取消運行了
            if (isCanceled()) {
                break;
            }

            doDownload(item, filePath);

            retryTimes++;
            if (retryTimes == 3) {
                DownloadFrame3.addProgress("三次下載失敗，取消下載："
                        + item.getIdentifier());
                break;
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
            Random random = new Random();
            long millis = (long) (random.nextDouble() * 15 * 1000);
            Thread.sleep(millis);
            DownloadFrame3.addProgress("休息了" + millis + "毫秒。");
        } catch (Exception e) {
        }
    }
}
