package com.ning.ui.main.right_down_panel;

import com.ning.common_component.ShowMusicPanel;
import com.ning.entity.query.Music;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 通过搜索获得的歌曲列表面板
 * */
public class SearchMusicPanel extends JPanel {
    /**
     * 无任何音乐信息标签
     * */
    private JLabel noAnyMusicLabel=new JLabel();
    /**
     * 音乐搜索列表名称标签
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
    public SearchMusicPanel(){
        init();
    }
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
        noAnyMusicLabelInit();
    }
    /**
     * 初始化音乐搜索列表名称标签，宽为500，高为50，距离我喜欢音乐列表面板左侧距离为30，上侧距离为10，
     * 字体大小为50，加粗
     * */
    private void searchMusicLabelInit(){
        searchMusicLabel.setBounds(50,20,500,40);
        searchMusicLabel.setText("音乐搜索列表");
        searchMusicLabel.setFont(new Font(null,Font.BOLD,40));
    }
    /**
     * 初始化音乐列表表头，宽为900，高为30，距离搜索音乐面板左侧距离为50，上侧距离为70,背景色为白色
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
     * 初始化滚动面板，宽为950，高为510，距离搜索音乐展示面板左侧距离为0，上侧距离为110.
     * 垂直滚动条显示，水平滚动条不显示
     * 添加音乐音乐列表面板，宽为950.
     * */
    public void musicScrollPanelInit() {
        musicScrollPanel.setBorder(null);
        musicScrollPanel.setBounds(0, 110, 950, 510);
        musicScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        musicScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        musicScrollPanel.setBackground(Color.white);
        showMusicPanel.setLayout(null);
        showMusicPanel.setBackground(Color.white);
        musicScrollPanel.getViewport().add(showMusicPanel);
    }
    private void noAnyMusicLabelInit(){
        noAnyMusicLabel.setBounds(0,0,930,50);
        noAnyMusicLabel.setFont(new Font(null,Font.BOLD,30));
        noAnyMusicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noAnyMusicLabel.setText("未搜索到相关歌曲");
    }
    /**
     * 将音乐列表中的歌曲加载到该面板中
     * */
    public void loadMusicData(List<Music> musicList, Integer searchDataResult){
        showMusicPanel.removeAll();
        if(searchDataResult==1){
            showMusicPanel.setPreferredSize(new Dimension(950,50*musicList.size()+50));
            showMusicPanel.add(noAnyMusicLabel);
        }else {
            showMusicPanel.setPreferredSize(new Dimension(950,50*musicList.size()));
        }
        for (int x=0;x<musicList.size();x++) {
            Music music = musicList.get(x);
            ShowMusicPanel showMusic=null;
            if(searchDataResult==1){
                showMusic=new ShowMusicPanel(music,50*(x+1),x);
            }else {
                showMusic=new ShowMusicPanel(music,50*x,x);
            }
            showMusicPanel.add(showMusic);
        }
        showMusicPanel.repaint();
        showMusicPanel.validate();
    }
    private final static SearchMusicPanel INSTANCE=new SearchMusicPanel();
    public static SearchMusicPanel getSearchMusicPanel(){
        return INSTANCE;
    }
}
