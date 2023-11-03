package com.ning.dialog;

import com.ning.DiaLogParent;
import com.ning.dialog.listener.LrcShowListener;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * 歌词展示面板
 * */
public class LrcShowDialog extends DiaLogParent {
    private final static LrcShowDialog INSTANCE=new LrcShowDialog();

    public static LrcShowDialog getLrcShowDialog() {
        return INSTANCE;
    }

    private LrcShowListener lrcShowListener=new LrcShowListener();
    private final static int WIDTH=1000;
    private final static int HEIGHT=60;
    private JLabel lrcLabel=new JLabel();
    public LrcShowDialog(){
        init();
    }
    public void init(){
        this.setLayout(null);
        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this,false);
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        this.setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH,HEIGHT);
        lrcLabelInit();
        lrcShowListener.setLrcShowDialog(this,lrcLabel);
        this.add(lrcLabel);
        this.setAlwaysOnTop(true);
    }
    public void lrcLabelInit(){
        lrcLabel.setBounds(0,0,WIDTH,HEIGHT);
        lrcLabel.setForeground(Color.blue);
        lrcLabel.setFont(new Font(null,Font.BOLD,40));
        lrcLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lrcLabel.addMouseListener(lrcShowListener.dialogOpacityListener());
        lrcLabel.addMouseMotionListener(lrcShowListener.dialogOpacityMotionListener());
    }
    public JLabel getLrcLabel() {
        return lrcLabel;
    }
}
