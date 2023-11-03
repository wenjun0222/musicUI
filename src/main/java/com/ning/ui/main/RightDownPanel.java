package com.ning.ui.main;

import com.ning.ui.main.right_down_panel.DownLoadListPanel;
import com.ning.ui.main.right_down_panel.UserLikeMusicPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 主窗体右下面板
 * */
public class RightDownPanel extends JPanel {
    private final static RightDownPanel INSTANCE=new RightDownPanel();
    private UserLikeMusicPanel myLikeMusicPanel= UserLikeMusicPanel.getMyLikeMusicPanel();
    private DownLoadListPanel downLoadListPanel=DownLoadListPanel.getDownLoadListPanel();
    public  RightDownPanel(){
        init();
    }
    /**
     * 初始化主窗体右下面板，宽为950，高为620，距离主窗体左侧距离为250，上侧距离为80.背景色为白色
     * */
    public void init(){
        this.setBounds(250,80,950,620);
        this.setBackground(Color.white);
        this.setLayout(null);
//        this.add(downLoadListPanel);
        this.add(myLikeMusicPanel);
    }
    public static RightDownPanel getRightDownPanel(){
        return INSTANCE;
    }
    public void changePanel(JPanel jPanel){
        this.removeAll();
        this.add(jPanel);
        this.repaint();
        this.validate();
    }
}
