package cjdict2356pc.view;

import java.util.List;

import javax.swing.JPanel;

/**
 * 結果元素列表
 * 
 * 
 * @author fszhouzz@qq.com
 * @time 2017年12月21日 下午10:49:37
 */
public class Cangjie2356ListItemList extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Cangjie2356ListItemList(List<Cangjie2356ListItemView> views) {
        int width = views.get(0).getWidth();
        int height = views.get(0).getHeight() * views.size();
        this.setSize(width, height);
        this.setLayout(null);

        for (int i = 0; i < views.size(); i++) {
            Cangjie2356ListItemView view = views.get(i);
            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();
            int viewX = 0;
            int viewY = i * viewHeight;
            view.setBounds(viewX, viewY, viewWidth, viewHeight);
            add(view);
        }
    }
}
