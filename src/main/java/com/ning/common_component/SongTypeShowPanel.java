package com.ning.common_component;

import com.ning.entity.SongType;
import com.ning.thread.ShowThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 单个音乐类型展示面板
 * */
public class SongTypeShowPanel extends JPanel {
    private ImageIcon imageIcon;
    /**
     * 距离音乐类型列表展示面板的左侧距离
     * */
    private int x;
    /**
     * 距离音乐类型列表展示面板的上侧距离
     * */
    private int y;
    private SongType songType;
    /**
     * 音乐类型图片
     * */
    private JLabel songTypeImage=new JLabel();
    /**
     * 音乐类型的简单概述
     * */
    private JLabel songTypeNameLabel=new JLabel();
    public SongTypeShowPanel(SongType songType,int x,int y,ImageIcon imageIcon){
        this.imageIcon=imageIcon;
        this.songType=songType;
        this.x=x;
        this.y=y;
        init();
    }
    private void init(){
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setBounds(x,y,250,290);
        songTypeImageInit();
        this.add(songTypeImage);
        songTypeNameLabelInit();
        this.add(songTypeNameLabel);
    }
    /**
     * 初始化音乐类型图片，距离整体面板左侧以及上侧距离都为0，大小为250
     * */
    private void  songTypeImageInit(){
        songTypeImage.setBounds(0,0,250,250);
        songTypeImage.addMouseListener(songTypeMusicListener());
        songTypeImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
        songTypeImage.setIcon(imageIcon);
    }
    /**
     * 初始化音乐类型简单概述，宽为250，高为40，距离整体面板左侧距离为0，上侧距离为250
     * 字体加粗，大小25
     * */
    private void songTypeNameLabelInit(){
        songTypeNameLabel.setFont(new Font(null,Font.BOLD,20));
        songTypeNameLabel.setBounds(0,250,250,40);
        songTypeNameLabel.setText("【"+songType.getTypeName()+"】"+songType.getTypeSummary());
        songTypeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        songTypeNameLabel.addMouseListener(songTypeMusicListener());
        songTypeNameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 歌曲类型面板监听事件
     * */
    public MouseListener songTypeMusicListener(){
        JLabel songTypeNameLabel = this.getSongTypeNameLabel();
        MouseListener songTypeMusicListener=new MouseAdapter() {
            //鼠标进入面板时，字体变蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                songTypeNameLabel.setForeground(Color.blue);
            }
            //鼠标退出面板时，字体变黑色
            @Override
            public void mouseExited(MouseEvent e) {
                songTypeNameLabel.setForeground(Color.black);
            }
            //按下鼠标后，右下侧面板移除所有面板，将歌曲类型音乐面板加入其中
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowThread showThread=ShowThread.getShowThread();
                showThread.setCurrentTypeId(songType.getId());
                ShowThread.LoadSongTypeMusicThread loadSongTypeMusicThread =
                        showThread.getLoadSongTypeMusicThread();
                showThread.setCurrentPanel("songTypeMusicPanel");
                loadSongTypeMusicThread.start();
            }
        };
        return songTypeMusicListener;
    }
    public JLabel getSongTypeNameLabel() {
        return songTypeNameLabel;
    }
}
