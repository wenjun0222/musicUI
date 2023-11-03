package com.ning.common_component;

import com.ning.entity.Singer;
import com.ning.thread.ShowThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 单个歌手展示面板
 * */
public class SingerShowPanel extends JPanel{
    private ShowThread showThread=ShowThread.getShowThread();
    private int x;
    private int y;
    private Singer singer;
    private ImageIcon imageIcon;
    private JLabel singerImage=new JLabel();
    private JLabel singerNameLabel=new JLabel();
    public SingerShowPanel(Singer singer,int x,int y,ImageIcon imageIcon){
        this.singer=singer;
        this.x=x;
        this.y=y;
        this.imageIcon=imageIcon;
        init();
    }
    private void init(){
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setBounds(x,y,200,230);
        singerImageInit();
        this.add(singerImage);
        singerNameLabelInit();
        this.add(singerNameLabel);
    }
    private void  singerImageInit(){
        singerImage.setBounds(0,0,200,200);
        singerImage.addMouseListener(singerMusicListener());
        singerImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
        singerImage.setIcon(imageIcon);
    }
    private void singerNameLabelInit(){
        singerNameLabel.setFont(new Font(null,Font.BOLD,25));
        singerNameLabel.setBounds(0,200,200,30);
        singerNameLabel.setText(singer.getSingerName());
        singerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        singerNameLabel.addMouseListener(singerMusicListener());
        singerNameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 歌手面板监听事件
     * */
    public MouseListener singerMusicListener(){
        JLabel singerNameLabel = this.getSingerNameLabel();
        MouseListener singerMusicListener=new MouseAdapter() {
            //鼠标进入面板时，字体变蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                singerNameLabel.setForeground(Color.blue);
            }
            //鼠标退出面板时，字体变黑色
            @Override
            public void mouseExited(MouseEvent e) {
                singerNameLabel.setForeground(Color.black);
            }
            //按下鼠标后，右下侧面板移除所有面板，将歌手信息标签以及相关的音乐面板加入其中
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowThread.ShowSingerMusicThread showSingerMusicThread = showThread.
                        getShowSingerMusicThread();
                showThread.setCurrentPanel("singerMusicListPanel");
                showThread.setCurrentSingerId(singer.getId());
                showSingerMusicThread.start();

            }
        };
        return singerMusicListener;
    }
    public JLabel getSingerNameLabel() {
        return singerNameLabel;
    }
}
