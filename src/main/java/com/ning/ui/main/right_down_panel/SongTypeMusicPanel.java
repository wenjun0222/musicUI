package com.ning.ui.main.right_down_panel;

import com.ning.common_component.ShowMusicPanel;
import com.ning.entity.query.Music;
import com.ning.entity.query.SongTypeMusicList;
import com.ning.utils.OnlineImageUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 歌曲类型音乐列表面板
 * */
public class SongTypeMusicPanel extends JPanel {
    private final static SongTypeMusicPanel INSTANCE=new SongTypeMusicPanel();
    /**
     * 古风照片，大小为220，正方形
     * */
    private JLabel songTypeImage=new JLabel();
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
    private JPanel songListPanel=new JPanel();
    /**
     * 照片标题标签
     * */
    private JLabel titleLabel=new JLabel();
    /**
     *
     * */
    JTextArea typeDescriptionArea=new JTextArea();
    public SongTypeMusicPanel(){
        init();
    }
    /**
     * 初始化整体面板
     * */
    private void init() {
        this.setLayout(null);
        this.setBounds(0,0, 950, 620);
        this.setBackground(Color.white);
        songTypeImageInit();
        this.add(songTypeImage);
        titleLabelInit();
        this.add(titleLabel);
        typeDescriptionAreaInit();
        this.add(typeDescriptionArea);
        tableHeaderPanelInit();
        this.add(tableHeaderPanel);
        musicScrollPanelInit();
        this.add(musicScrollPanel);
    }
    /**
     * 初始化歌曲类型照片，距离整体面板左侧以及上侧距离都为0，大小为220
     * */
    private void songTypeImageInit(){
        songTypeImage.setBounds(50,30,220,220);
    }
    /**
     * 初始化歌手标题，宽为600，高为50，距离整体面板左侧距离为270，,上侧距离都为50。
     * 字体大小为40，加粗，水平居中。
     * */
    private void titleLabelInit(){
        titleLabel.setBounds(270,50,600,50);
        titleLabel.setFont(new Font(null,Font.BOLD,40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    private void typeDescriptionAreaInit(){
        typeDescriptionArea.setLineWrap(true);
        typeDescriptionArea.setBounds(320,110,550,130);
        typeDescriptionArea.setFont(new Font(null,Font.PLAIN,21));
        typeDescriptionArea.setBackground(new Color(245,245,245));
        typeDescriptionArea.setEditable(false);
    }
    /**
     * 初始化音乐列表表头，宽为900，高为30，距离搜索音乐面板左侧距离为50，上侧距离为260,背景色为白色
     * */
    private void tableHeaderPanelInit(){
        tableHeaderPanel.setLayout(null);
        tableHeaderPanel.setBounds(50,260, 900,30);
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
     * 初始化滚动面板，宽为950，高为320，距离歌手音乐作品展示面板左侧距离为0，上侧距离为300.
     * 垂直滚动条显示，水平滚动条不显示
     * */
    public void musicScrollPanelInit() {
        musicScrollPanel.setBorder(null);
        musicScrollPanel.setBounds(0, 300, 950, 320);
        musicScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        musicScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        musicScrollPanel.setBackground(Color.white);
        songListPanel.setLayout(null);
        songListPanel.setBackground(Color.white);
        musicScrollPanel.getViewport().add(songListPanel);
    }
    /**
     * 将音乐类型的信息以及相关的歌曲作品加载到面板中
     * */
    public void loadMusicData(SongTypeMusicList songTypeMusicList){
        ImageIcon imageIcon= OnlineImageUtil.
                getRectImage(songTypeMusicList.getTypePhotoUrl(),220);
        songTypeImage.setIcon(imageIcon);
        titleLabel.setText("【"+songTypeMusicList.getTypeName()+"】"+songTypeMusicList.getTypeSummary());
        typeDescriptionArea.setText(songTypeMusicList.getTypeDescription());
        List<Music> musicList = songTypeMusicList.getMusicList();
        changeMusicList(musicList);
    }
    /**
     * 将音乐类型的相关歌曲列表加载面板中
     * */
    public void changeMusicList(List<Music> musicList){
        songListPanel.setPreferredSize(new Dimension(950,musicList.size()*50));
        songListPanel.removeAll();
        for (int i = 0; i < musicList.size(); i++) {
            ShowMusicPanel showMusicPanel=new ShowMusicPanel(musicList.get(i),i*50,i);
            songListPanel.add(showMusicPanel);
        }
        songListPanel.repaint();
        songListPanel.validate();
    }
    public static SongTypeMusicPanel getSongTypeMusicPanel(){
        return INSTANCE;
    }
}
