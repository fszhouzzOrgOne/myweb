package familysearch2;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import familysearch2.frame.DownloadFrame;

/**
 * 
 * 
 * @author 周子照
 */
public class DownloadMain2 {

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new DownloadFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
