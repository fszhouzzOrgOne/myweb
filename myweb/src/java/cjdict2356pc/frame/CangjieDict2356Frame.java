package cjdict2356pc.frame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 倉頡字典2356窗口
 * 
 * @author fszhouzz@qq.com
 * @time 2017年12月21日下午2:25:09
 */
public class CangjieDict2356Frame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static class LazyHolder {
        private static final CangjieDict2356Frame frame = new CangjieDict2356Frame();
    }

    /**
     * 獲得實例
     */
    public static CangjieDict2356Frame getInstance() {
        return LazyHolder.frame;
    }

    public CangjieDict2356Frame() {
        setTitle("倉頡字典2356電腦版v1.0");
        setResizable(false);

        int contentWidth = 450;
        int contentHeight = 540;
        int frameWidth = contentWidth + 6;
        int frameHeight = contentHeight + 28;
        setBounds(100, 100, frameWidth, frameHeight);

        // 自定義圖標
        BufferedImage image = null;
        try {
            image = ImageIO.read(this.getClass().getResource("." + File.separator + "ic_launcher.png"));
        } catch (IOException e) {
        }
        this.setIconImage(image);
    }
}
