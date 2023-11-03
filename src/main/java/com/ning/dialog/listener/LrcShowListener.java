package com.ning.dialog.listener;

import com.ning.DiaLogParent;
import com.ning.dialog.LrcShowDialog;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LrcShowListener {
    private JLabel lrcLabel;
    private DiaLogParent lrcShowDialog;
    private final static Point origin = new Point();
    public void setLrcShowDialog(LrcShowDialog lrcShowDialog,JLabel lrcLabel) {
        this.lrcShowDialog=lrcShowDialog;
        this.lrcLabel=lrcLabel;
    }
    public MouseListener dialogOpacityListener(){
        MouseListener dialogOpacityListener=new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 当鼠标按下的时候获得窗口当前的位置
                origin.x = e.getX();
                origin.y = e.getY();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                AWTUtilities.setWindowOpaque(lrcShowDialog,true);
                AWTUtilities.setWindowOpacity(lrcShowDialog,0.8f);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                AWTUtilities.setWindowOpacity(lrcShowDialog,1f);
                AWTUtilities.setWindowOpaque(lrcShowDialog,false);
            }
        };
        return dialogOpacityListener;
    }
    public MouseMotionListener dialogOpacityMotionListener(){
        MouseMotionListener dialogOpacityMotionListener=new MouseMotionAdapter() {
            // 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
            public void mouseDragged(MouseEvent e) {
                // 当鼠标拖动时获取窗口当前位置
                Point p = lrcShowDialog.getLocation();
                // 设置窗口的位置
                // 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                lrcShowDialog.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        };
        return dialogOpacityMotionListener;
    }
}
