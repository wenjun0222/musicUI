package com.ning.ui.main.right_down_panel;

import com.ning.common_component.ShowMusicPanel;
import com.ning.entity.query.Music;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 最近播放歌曲的音乐列表
 * */
public class RecentPlaySongPanel extends JPanel {
    /**
     * 最近播放名称标签
     * */
    private JLabel searchMusicLabel=new JLabel();
    /**
     * 歌曲列表表头面板
     * */
    private JPanel tableHeaderPanel=new JPanel();
    /**
     * 歌曲列表展示滚动面板
     * */
    JScrollPane musicScrollPanel=new JScrollPane();
    /**
     * 音乐列表展示面板
     * */
    private JPanel showMusicPanel=new JPanel();
    public RecentPlaySongPanel(){
        init();
    }
    /**
     * 初始化最近播放音乐面板
     * */
    public void init(){
        this.setBounds(0,0, 950,620);
        this.setLayout(null);
        this.setBackground(Color.white);
        searchMusicLabelInit();
        this.add(searchMusicLabel);
        tableHeaderPanelInit();
        this.add(tableHeaderPanel);
        musicScrollPanelInit();
        this.add(musicScrollPanel);
    }
    /**
     * 初始化音乐搜索列表名称标签，宽为500，高为50，距离整体面板左侧距离为30，上侧距离为10，
     * 字体大小为50，加粗
     * */
    private void searchMusicLabelInit(){
        searchMusicLabel.setBounds(50,20,500,40);
        searchMusicLabel.setText("最近播放");
        searchMusicLabel.setFont(new Font(null,Font.BOLD,40));
    }
    /**
     * 初始化音乐列表表头，宽为900，高为30，距离整体面板左侧距离为50，上侧距离为70,背景色为白色
     * */
    private void tableHeaderPanelInit(){
        tableHeaderPanel.setLayout(null);
        tableHeaderPanel.setBounds(50,70, 900,30);
        tableHeaderPanel.setBackground(Color.white);
        //歌名标签，距离表头面板左侧距离为0
        JLabel songNameLabel = getSongInformationLabel(0, "歌名");
        tableHeaderPanel.add(songNameLabel);
        //歌手名字标签，距离表头面板左侧距离为225
        JLabel singerNameLabel = getSongInformationLabel(225, "歌手");
        tableHeaderPanel.add(singerNameLabel);
        //歌曲类型标签，距离表头面板左侧距离为450
        JLabel songTypeLabel = getSongInformationLabel(450, "类型");
        tableHeaderPanel.add(songTypeLabel);
        //歌曲时长标签，距离表头面板左侧距离为675
        JLabel durationLabel = getSongInformationLabel(675, "时长");
        tableHeaderPanel.add(durationLabel);
    }
    /**
     * 音乐列表的表头面板中歌曲名称，歌手名称，歌曲类型，歌曲时长等标签获取方法
     * 宽为80，高为30，距离表头面板左侧距离由x决定，上侧距离为0
     * 字体大小为24，加粗，颜色为灰色。
     * */
    private JLabel getSongInformationLabel(int x,String prompt){
        JLabel songInformationLabel=new JLabel();
        songInformationLabel.setFont(new Font(null,Font.BOLD,24));
        songInformationLabel.setBounds(x,0,80,30);
        songInformationLabel.setText(prompt);
        songInformationLabel.setForeground(Color.GRAY);
        return songInformationLabel;
    }
    /**
     * 初始化滚动面板，宽为950，高为510，距离整体面板左侧距离为0，上侧距离为110.
     * 垂直滚动条显示，水平滚动条不显示
     * 添加音乐音乐列表面板，宽为950.
     * */
    private void musicScrollPanelInit() {
        musicScrollPanel.setBorder(null);
        musicScrollPanel.setBounds(0, 110, 950, 510);
        musicScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        musicScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        musicScrollPanel.setBackground(Color.white);
        showMusicPanel.setLayout(null);
        showMusicPanel.setBackground(Color.white);
        musicScrollPanel.getViewport().add(showMusicPanel);
    }
    /**
     * 将最近播放的音乐列表加载面板中
     * */
    public void loadDataMusic(List<Music> musicList){
        int musicSize=musicList.size();
        showMusicPanel.setPreferredSize(new Dimension(950, 50 * musicSize));
        showMusicPanel.removeAll();
        for (int x=0;x<musicSize;x++) {
            Music music = musicList.get(x);
            ShowMusicPanel showMusic = new ShowMusicPanel(music, 50 * x, x);
            showMusicPanel.add(showMusic);
            musicList.add(music);
        }
        showMusicPanel.repaint();
        showMusicPanel.validate();
    }
    private final static RecentPlaySongPanel INSTANCE=new RecentPlaySongPanel();
    public static RecentPlaySongPanel getRecentPlaySongPanel(){
        return INSTANCE;
    }
}
