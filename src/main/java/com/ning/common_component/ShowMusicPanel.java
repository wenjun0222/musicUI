package com.ning.common_component;

import com.ning.entity.query.Music;
import com.ning.thread.PlayMusic;
import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 歌曲信息展示面板，展示爱心的颜色，歌曲的名称，歌手，类型，时长
 */
public class ShowMusicPanel extends JPanel {
    private PlayMusic playMusic=PlayMusic.getInstance();
    private int currentSongIndex;
    /**
     * 音乐实体类
     * */
    private Music music;
    /**
     * 距离音乐列表面板上侧的距离
     * */
    private int y;
    /**
     * 是否添加到我喜欢爱心图标，大小为20
     **/
    private JLabel loveImage=new JLabel();
    public ShowMusicPanel(Music music,int y,int currentSongIndex){
        this.currentSongIndex=currentSongIndex;
        this.music=music;
        this.y=y;
        init();
    }
    /**
     * 初始化歌曲信息展示面板
     * */
    private void init(){
        this.addMouseListener(playCurrentMusic(currentSongIndex));
        this.setBounds(50,y,900,50);
        this.setLayout(null);
        this.setBackground(Color.white);
        loveImageInit();
        this.add(loveImage);
        //歌曲名称标签，距离歌曲信息展示面板左侧为25
        JLabel songNameLabel=getSongMsgLabel(25,music.getSongName());
        this.add(songNameLabel);
        //歌手名称标签，距离歌曲信息展示面板左侧为225
        JLabel singerNameLabel=getSongMsgLabel(225,music.getSingerName());
        this.add(singerNameLabel);
        //歌曲类型标签，距离歌曲信息展示面板左侧为450
        JLabel songTypeLabel=getSongMsgLabel(450,music.getSongType());
        this.add(songTypeLabel);
        //歌曲时长标签，距离歌曲信息展示面板左侧为675
        JLabel durationLabel=getSongMsgLabel(675,music.getSongDuration());
        this.add(durationLabel);

    }
    /**
     * 初始化爱心图标面板，距离歌曲信息展示面板左侧为0，大小为20
     * 如果歌曲已经被用户添加到我喜欢系列，面板添加红色爱心图标，反之添加黑色爱心图标
     * 爱心图标距离爱心面板左侧以及上侧距离为0，大小为20
     * */
    private void loveImageInit(){
        loveImage.setBackground(Color.white);
        loveImage.setBounds(0,15,20,20);
        ImageIcon imageIcon=null;
        if(music.getUserFavoriteSongId()==null){
            imageIcon= LocalImageUtil.getLocalSquareImage("image/icon/未添加到我喜欢.png",20);
        }else{
            imageIcon= LocalImageUtil.getLocalSquareImage("image/icon/已添加到我喜欢.png",20);
        }
        loveImage.setIcon(imageIcon);
    }
    /**
     * 歌曲名称，歌手名称，歌曲类型，歌曲时长标签都由这个方法获得
     * 标签距离歌曲信息展示面板上侧距离为15，宽为150，高为20，
     * 字体大小为20，加粗
     * */
    private JLabel getSongMsgLabel(int x,String labelName){
        JLabel musicShowLabel=new JLabel();
        musicShowLabel.setFont(new Font(null,Font.BOLD,18));
        musicShowLabel.setBounds(x,15,200,18);
        musicShowLabel.setText(labelName);
        return musicShowLabel;
    }
    public MouseListener playCurrentMusic(int currentIndex){
        MouseListener playCurrentMusic=new MouseAdapter() {
            //点击当前歌曲面板时，播放当前音乐
            @Override
            public void mouseClicked(MouseEvent e) {
                playMusic.playCurrentMusic(currentIndex);
            }
            //鼠标进入当前歌曲面板时，当前音乐面板背景色变为白烟色
            @Override
            public void mouseEntered(MouseEvent e) {
                loveImage.setBackground(new Color(220,220,220));
                setBackground(new Color(220,220,220));
            }
            //鼠标退出当前歌曲面板时，当前音乐面板背景色恢复原样
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.white);
                loveImage.setBackground(Color.white);
            }
        };
        return playCurrentMusic;
    }
    public JLabel getLoveImage() {
        return loveImage;
    }
}
