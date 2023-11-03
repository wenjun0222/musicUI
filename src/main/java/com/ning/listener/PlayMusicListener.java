package com.ning.listener;

import com.ning.DiaLogParent;
import com.ning.thread.PlayMusic;
import com.ning.thread.ShowThread;
import com.ning.ui.main.PlayMusicPanel;
import com.ning.ui.main.listener.MainUIListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayMusicListener extends MainUIListener {
    private ShowThread showThread=ShowThread.getShowThread();
    /**
     * 提示标签框
     * */
    private boolean showLrcDialog=true;
    JPanel promptPanel=new JPanel();
    JLabel promptLabel=new JLabel();
    DiaLogParent promptDialog=new DiaLogParent();
    {
        promptLabel.setLocation(0,0);
        promptLabel.setFont(new Font(null,Font.PLAIN,25));
        promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        promptPanel.setBackground(new Color(0,191,255));
        promptPanel.setLocation(0,0);
        promptPanel.setLayout(null);
        promptPanel.add(promptLabel);
        promptDialog.add(promptPanel);
    }
    private PlayMusicPanel playMusicPanel;
    private PlayMusic playMusic=PlayMusic.getInstance();
    /**
     * 停止播放音乐事件监听，按下按钮后，音乐停止，面板中停止组件换为播放组件
     * */
    public MouseListener stopMusicListener(){
        MouseListener stopMusicListener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();//定位当前鼠标位置（一定要写在函数内）
                promptLabel.setText("停止");
                promptDialog.setBounds((int)p.getX()+20,(int)p.getY()+20,60,30);
                promptPanel.setSize(60,30);
                promptLabel.setSize(60,30);
                promptDialog.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                promptDialog.setVisible(false);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel playSongPanel=getPlayMusicPanel().getPlaySongPanel();
                JLabel stopSongIcon = getPlayMusicPanel().getStopSongIcon();
                playSongPanel.removeAll();
                playSongPanel.add(stopSongIcon);
                playSongPanel.repaint();
                playSongPanel.validate();
                playMusic.stopPlayMusic();
            }
        };
        return stopMusicListener;
    }
    /**
     * 继续播放音乐事件监听，按下按钮后，音乐停止，面板中播放组件换为停止组件
     * */
    public MouseListener continuePlayListener(){
        MouseListener continuePlayListener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();//定位当前鼠标位置（一定要写在函数内）
                promptLabel.setText("播放");
                promptDialog.setBounds((int)p.getX()+20,(int)p.getY()+20,60,30);
                promptPanel.setSize(60,30);
                promptLabel.setSize(60,30);
                promptDialog.setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                promptDialog.setVisible(false);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel playSongPanel=getPlayMusicPanel().getPlaySongPanel();
                JLabel playSongIcon = getPlayMusicPanel().getPlaySongIcon();
                playSongPanel.removeAll();
                playSongPanel.add(playSongIcon);
                playSongPanel.repaint();
                playSongPanel.validate();
                playMusic.continuePlaySong();
            }
        };
        return continuePlayListener;
    }
    /**
     * 播放下一首音乐事件监听
     * */
    public  MouseListener playNextSongListener(){
        MouseListener playNextSongListener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();//定位当前鼠标位置（一定要写在函数内）
                promptLabel.setText("下一首");
                promptDialog.setBounds((int)p.getX()+20,(int)p.getY()+20,80,30);
                promptPanel.setSize(80,30);
                promptLabel.setSize(80,30);
                promptDialog.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                promptDialog.setVisible(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playMusic.playNextMusic();
            }
        };
        return playNextSongListener;
    }
    /**
     * 播放上一首音乐事件监听
     * */
    public MouseListener playPreviousSongListener(){
        MouseListener playPreviousSongListener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();//定位当前鼠标位置（一定要写在函数内）
                promptLabel.setText("上一首");
                promptDialog.setBounds((int)p.getX()+20,(int)p.getY()+20,80,30);
                promptPanel.setSize(80,30);
                promptLabel.setSize(80,30);
                promptDialog.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                promptDialog.setVisible(false);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                playMusic.playPreviousMusic();
            }
        };
        return playPreviousSongListener;
    }
    /**
     * 音乐播放类型事件监听，按下按钮，音乐播放类型菜单栏显示出来
     * */
    public MouseListener showPopupMenuListener(){
        MouseListener showPopupMenuListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playMusicPanel.showPopupMenu(mainUI);
            }
        };
        return showPopupMenuListener;
    }
    /**
     * 音乐列表循环播放监听事件
     * */
    public ActionListener listLoopListener(){
        ActionListener listLoopListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel playTypeLabel = playMusicPanel.getPlayTypeLabel();
                playTypeLabel.setText("列表循环");
                playMusic.listLoopPlay();
            }
        };
        return listLoopListener;
    }
    /**
     * 单曲循环播放监听事件
     * */
    public ActionListener singleLoopListener(){
        ActionListener singleLoopListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel playTypeLabel = playMusicPanel.getPlayTypeLabel();
                playTypeLabel.setText("单曲循环");
                playMusic.singleLoopPlay();
            }
        };
        return singleLoopListener;
    }
    /**
     * 歌词窗口监听事件
     * */
    public MouseListener lrcDialogShowListener(){
        MouseListener lrcDialogShowListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(showLrcDialog){
                    playMusic.closeLrcDialog();
                }else{
                    playMusic.showLrcDialog();
                }
                showLrcDialog=!showLrcDialog;
            }
        };
        return lrcDialogShowListener;
    }
    /**
     * 黑色爱心鼠标监听事件
     * */
    public MouseListener addUserLikeListener(){
        MouseListener addUserLikeListener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();//定位当前鼠标位置（一定要写在函数内）
                promptLabel.setText("添加到我喜欢");
                promptDialog.setBounds((int)p.getX()+20,(int)p.getY()+20,150,30);
                promptPanel.setSize(150,30);
                promptLabel.setSize(150,30);
                promptDialog.setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                promptDialog.setVisible(false);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowThread.addUserLikeSongThread addUserLikeSongThread = showThread.addUserLikeSongThread();
                addUserLikeSongThread.setPlayMusicPanel(playMusicPanel);
                addUserLikeSongThread.start();
            }
        };
        return addUserLikeListener;
    }
    /**
     * 红色爱心鼠标监听事件
     * */
    public MouseListener cancelUserLikeListener(){
        MouseListener addUserLikeListener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();//定位当前鼠标位置（一定要写在函数内）
                promptLabel.setText("取消喜欢");
                promptDialog.setBounds((int)p.getX()+20,(int)p.getY()+20,120,30);
                promptPanel.setSize(120,30);
                promptLabel.setSize(120,30);
                promptDialog.setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                promptDialog.setVisible(false);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowThread.CancelUserLikeSongThread cancelUserLikeSongThread = showThread.cancelUserLikeSongThread();
                cancelUserLikeSongThread.setPlayMusicPanel(playMusicPanel);
                cancelUserLikeSongThread.start();
            }
        };
        return addUserLikeListener;
    }
    public PlayMusicPanel getPlayMusicPanel() {
        return playMusicPanel;
    }
    public void setPlayMusicPanel(PlayMusicPanel playMusicPanel) {
        this.playMusicPanel = playMusicPanel;
    }

}
