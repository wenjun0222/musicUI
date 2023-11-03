package com.ning.ui;

import com.ning.UIParent;
import com.ning.thread.PlayMusic;
import com.ning.ui.main.MainLeftPanel;
import com.ning.ui.main.PlayMusicPanel;
import com.ning.ui.main.RightDownPanel;
import com.ning.ui.main.RightTopPanel;
import com.ning.ui.main.listener.MainUIListener;
import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 主界面窗体
 * */
public class MainUI extends UIParent {
    private PlayMusic playMusic;
    private final static MainUI INSTANCE=new MainUI();
    public static MainUI getMainUI(){
        return INSTANCE;
    }
    /**
     * 登录窗口的宽度
     * */
    private final static int WIDTH=1200;
    /**
     * 登录窗口的高度
     * */
    private final static int HEIGHT=800;
    public  MainUI(){
    }
    /**
     * 初始化主界面窗体。
     * */
    public void mainUIInit(){
        PlayMusic playMusic=PlayMusic.getInstance();
        PlayMusicPanel playMusicPanel=PlayMusicPanel.getPlayMusicPanel();
        MainUIListener mainUIListener=new MainUIListener();
        RightTopPanel rightTopPanel=RightTopPanel.getRightTopPanel();
        MainLeftPanel mainLeftPanel=MainLeftPanel.getMainLeftPanel();
        RightDownPanel rightDownPanel=RightDownPanel.getRightDownPanel();
        ImageIcon windowIcon= LocalImageUtil.getLocalSquareImage("image/熊猫.png",20);
        this.setIconImage(windowIcon.getImage());
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        this.setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(mainLeftPanel);
        this.add(rightTopPanel);
        this.add(rightDownPanel);
        this.add(playMusicPanel);
        this.setTitle("熊猫音乐");
        playMusic.stop=false;
        playMusic.setMainUI(this);
        playMusic.start();
        mainUIListener.setComponents(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainUI mainUI = getMainUI();
        mainUI.mainUIInit();
    }
}
