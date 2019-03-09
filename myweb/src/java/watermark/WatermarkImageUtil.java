package watermark;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 加文字水印：平鋪滿，旋轉
 * 
 * @author fszhouzz@qq.com
 * @time 2019年3月9日 下午10:12:36
 */
public class WatermarkImageUtil {
    private static String baseDir = "src\\java\\watermark\\";

    public static void main(String[] args) {
        String output = baseDir;
        String source = baseDir + "java.jpeg"; // 源图片路径
        String imageName = "mark_image"; // 图片名称
        String imageType = "jpeg"; // 图片类型jpg,jpeg,png,gif
        String text = "仅用于某某银行";
        Integer degree = -45; // 水印旋转角度-45，null表示不旋转
        String result = WatermarkImageUtil.markImageByMoreText(source, output,
                imageName, imageType, Color.red, text, degree);
        System.out.println(result);
    }

    /**
     * 给图片添加多个文字水印、可设置水印文字旋转角度<br/>
     * 思路：给原图片外接圆的外切正方形加水印，按原图片中心点旋转
     *
     * @param source
     *            需要添加水印的图片路径（）
     * @param output
     *            添加水印后图片输出路径（）
     * @param imageName
     *            图片名称（如：11111）
     * @param imageType
     *            图片类型（如：jpg）
     * @param color
     *            水印文字的颜色
     * @param word
     *            水印文字
     * @param degree
     *            水印文字旋转角度，为null表示不旋转
     */

    public static String markImageByMoreText(String source, String output,
            String imageName, String imageType, Color color, String word,
            Integer degree) {
        String result = "添加文字水印出错";
        try {
            // 读取原图片信息
            File file = new File(source);
            if (!file.isFile()) {
                return file + " 不是一个图片文件!";
            }
            Image img = ImageIO.read(file);
            // 图片宽
            int width = img.getWidth(null);
            // 图片高
            int height = img.getHeight(null);
            // 文字大小
            int fontSize = 50;
            // 加水印
            BufferedImage bi = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            g.drawImage(img, 0, 0, width, height, null);
            // 设置水印字体样式
            Font font = new Font("宋体", Font.PLAIN, fontSize);
            // 根据图片的背景设置水印颜色
            g.setFont(font);
            g.setColor(color);
            if (null != degree) {
                // 设置水印旋转，在长方形的中点为原点坐标
                g.rotate(Math.toRadians(degree), (double) bi.getWidth() / 2,
                        (double) bi.getHeight() / 2);
            }
            // 原图片外接圆的半径
            int circumcircleRadius = ((Double) Math
                    .sqrt(Math.pow(width, 2) + Math.pow(height, 2))).intValue()
                    / 2;
            // 原图片外接圆的外切正方形的原点x和y，两值一定为负数
            int circumscribingSquareX = width / 2 - circumcircleRadius;
            int circumscribingSquareY = height / 2 - circumcircleRadius;
            // 水印间的间隔
            int space = 2 * fontSize;
            for (int x = circumscribingSquareX; x <= circumscribingSquareX
                    + circumcircleRadius * 2;) {
                for (int y = circumscribingSquareY; y <= circumscribingSquareY
                        + circumcircleRadius * 2;) {
                    // 画水印位置
                    g.drawString(word, x, y);
                    y += space + fontSize;
                }
                x += space + fontSize * word.length();
            }
            g.dispose();
            // 输出图片
            File sf = new File(output, imageName + "." + imageType);
            ImageIO.write(bi, imageType, sf); // 保存图片
            result = "图片完成添加Word水印";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
