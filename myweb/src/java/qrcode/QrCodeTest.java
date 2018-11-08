package qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import cangjie.java.util.IOUtils;

/**
 * 利用zxing开源工具生成二维码QRCode<br/>
 * https://www.cnblogs.com/hongten/archive/2012/10/26/java_qrcode.html
 * 
 * 
 * @author fszhouzz@qq.com
 * @time 2018年11月8日 下午10:38:34
 */
public class QrCodeTest {
    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static String mbsBaseDir = "src\\java\\qrcode\\";

    public static void main(String[] args) throws Exception {
        File file = new File(mbsBaseDir + "qrcode_src.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        List<String> list = IOUtils.readLines(mbsBaseDir + "qrcode_src.txt");
        String str = "";
        for (String one : list) {
            str += one + "\r\n";
        }
        generateQrCodePng(str);
    }

    public static void generateQrCodePng(String str) {
        if (null == str || "".equals(str)) {
            return;
        }
        int width = 500;
        QrCodeTest test = new QrCodeTest();
        File file = new File(mbsBaseDir + "qrcode_" + width + ".png");
        test.encode(str, file, BarcodeFormat.QR_CODE, width, width, null);
        //
        test.decode(file);
    }

    /**
     * 生成QRCode二维码<br>
     * 注意com.google.zxing.qrcode.encoder.Encoder.java中的<br>
     * static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
     * 如果有中文，不能直接用UTF-8，得先按ISO-8859-1格式編碼字符串，否则中文编译后解析不了<br>
     */
    public void encode(String contents, File file, BarcodeFormat format,
            int width, int height, Map<EncodeHintType, ?> hints) {
        try {
            contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");
            System.out.println("encoded contents: " + contents);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    format, width, height);
            writeToFile(bitMatrix, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码图片<br>
     * 
     * @param matrix
     * @param format
     *            图片格式
     * @param file
     *            生成二维码图片位置
     * @throws IOException
     */
    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, file);
    }

    /**
     * 生成二维码内容<br>
     * 
     * @param matrix
     * @return
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
            }
        }
        return image;
    }

    /** 解析QRCode二维码 */
    public void decode(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null) {
                System.out.println("Could not decode image");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result;
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            /* 解码设置编码方式为：utf-8 */
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            result = new MultiFormatReader().decode(bitmap, hints);
            String resultStr = result.getText();
            System.out.println("解析后内容：" + resultStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}