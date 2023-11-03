package com.ning.ui.main.listener;

import com.ning.thread.ShowThread;
import com.ning.ui.main.LeftButtonPanel;
import com.ning.ui.main.MainLeftPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 主体窗口左侧面板监听事件
 * */
public class MainLeftPanelListener extends MainUIListener{
    private ShowThread showThread=ShowThread.getShowThread();
    private MainLeftPanel mainLeftPanel;
    /**
     * 我喜欢按钮监听事件
     * */
    public MouseListener MyLIkeMusicListener(){
        LeftButtonPanel ILikeButtonPanel = mainLeftPanel.getILikeButtonPanel();
        JLabel image = ILikeButtonPanel.getImage();
        MouseListener myLIkeMusicListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showThread.setCurrentPanel("userLikeMusicPanel");
                ShowThread.LoadUserLikeSongThread loadUserLikeSongThread =
                        showThread.loadUserLikeSongThread();
                loadUserLikeSongThread.start();
            }
            //鼠标进入该按钮区域时，按钮背景色变为深蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                ILikeButtonPanel.setBackground(new Color(0,191,255));
                image.setBackground(new Color(0,191,255));
            }
            //鼠标退出该按钮区域时，按钮背景色变为浅灰色
            @Override
            public void mouseExited(MouseEvent e) {
                ILikeButtonPanel.setBackground(new Color(220,220,220));
                image.setBackground(new Color(220,220,220));
            }
        };
        return myLIkeMusicListener;
    }
    /**
     * 下载按钮监听事件
     * */
    public MouseListener downloadListener(){
        LeftButtonPanel downloadButtonPanel = mainLeftPanel.getDownloadButtonPanel();
        JLabel image = downloadButtonPanel.getImage();
        MouseListener downloadListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            //鼠标进入该按钮区域时，按钮背景色变为深蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                downloadButtonPanel.setBackground(new Color(0,191,255));
                image.setBackground(new Color(0,191,255));
            }
            //鼠标退出该按钮区域时，按钮背景色变为浅灰色
            @Override
            public void mouseExited(MouseEvent e) {
                downloadButtonPanel.setBackground(new Color(220,220,220));
                image.setBackground(new Color(220,220,220));
            }
        };
        return downloadListener;
    }
    /**
     * 播放历史按钮监听事件
     * */
    public MouseListener playHistoryListener(){
        LeftButtonPanel playHistoryButtonPanel = mainLeftPanel.getPlayHistoryButtonPanel();
        JLabel image = playHistoryButtonPanel.getImage();
        MouseListener playHistoryListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowThread.RecentlyPlayMusicThread recentlyPlayMusicThread =
                        showThread.getRecentlyPlayMusicThread();
                showThread.setCurrentPanel("recentlyPlayMusicPanel");
                recentlyPlayMusicThread.start();
            }
            //鼠标进入该按钮区域时，按钮背景色变为深蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                playHistoryButtonPanel.setBackground(new Color(0,191,255));
                image.setBackground(new Color(0,191,255));
            }
            //鼠标退出该按钮区域时，按钮背景色变为浅灰色
            @Override
            public void mouseExited(MouseEvent e) {
                playHistoryButtonPanel.setBackground(new Color(220,220,220));
                image.setBackground(new Color(220,220,220));
            }
        };
        return playHistoryListener;
    }
    /**
     * 歌手按钮监听事件
     * */
    public MouseListener singerButtonListener(){
        LeftButtonPanel singerButtonPanel = mainLeftPanel.getSingerButtonPanel();
        JLabel image = singerButtonPanel.getImage();
        MouseListener singerButtonListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showThread.setCurrentPanel("singerListPanel");
                ShowThread.ShowSingerListThread showSingerListThread = showThread.getShowSingerListThread();
                showSingerListThread.start();
            }
            //鼠标进入该按钮区域时，按钮背景色变为深蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                singerButtonPanel.setBackground(new Color(0,191,255));
                image.setBackground(new Color(0,191,255));
            }
            //鼠标退出该按钮区域时，按钮背景色变为浅灰色
            @Override
            public void mouseExited(MouseEvent e) {
                singerButtonPanel.setBackground(new Color(220,220,220));
                image.setBackground(new Color(220,220,220));
            }
        };
        return singerButtonListener;
    }
    /**
     * 歌单分类按钮监听事件
     * */
    public MouseListener songClassifyListener() {
        LeftButtonPanel songClassifyButtonPanel = mainLeftPanel.getSongClassifyButtonPanel();
        JLabel image =songClassifyButtonPanel.getImage();
        MouseListener songClassifyListener = new MouseAdapter() {
            //按下按钮后，开启加载歌单分类线程
            @Override
            public void mouseClicked(MouseEvent e) {
                showThread.setCurrentPanel("songTypeListPanel");
                ShowThread.ShowSongTypeListThread showSongTypeListThread = showThread.getShowSongTypeListThread();
                showSongTypeListThread.start();
            }
            //鼠标进入该按钮区域时，按钮背景色变为深蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                songClassifyButtonPanel.setBackground(new Color(0, 191, 255));
                image.setBackground(new Color(0, 191, 255));
            }
            //鼠标退出该按钮区域时，按钮背景色变为浅灰色
            @Override
            public void mouseExited(MouseEvent e) {
                songClassifyButtonPanel.setBackground(new Color(220, 220, 220));
                image.setBackground(new Color(220, 220, 220));
            }
        };
        return songClassifyListener;
    }
    public void setMainLeftPanel(MainLeftPanel mainLeftPanel) {
        this.mainLeftPanel = mainLeftPanel;
    }
}
