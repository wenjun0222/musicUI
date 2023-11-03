package com.ning.ui.main.right_down_panel;

import com.ning.common_component.ShowMusicPanel;
import com.ning.entity.query.Music;
import com.ning.entity.query.SingerMusicList;
import com.ning.utils.OnlineImageUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 单个歌手音乐作品展示面板
 * */
public class SingerMusicListPanel extends JPanel {
    /**
     * 歌手名称标签
     * */
    private JLabel singerNameLabel=new JLabel();
    /**
     * 歌手国籍标签
     * */
    private JLabel singerNationalityLabel=new JLabel();
    /**
     * 歌手职业标签
     * */
    private JLabel singerJobLabel=new JLabel();
    /**
     * 歌手代表作标签
     * */
    private JLabel singerMasterpieceLabel=new JLabel();
    /**
     * 歌手照片，大小为250，正方形
     * */
    private JLabel singerImage=new JLabel();
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
    public SingerMusicListPanel(){
        init();
    }
    /**
     * 初始化单个歌手音乐作品展示面板，宽为950，高为620.距离主窗体右下面板左侧以及上侧距离为0，背景色为白色
     * */
    private void init() {
        this.setLayout(null);
        this.setBounds(0,0, 950, 620);
        this.setBackground(Color.white);
        singerImageInit();
        this.add(singerImage);
        singerNameLabel = getSingerInformationLabel(singerNameLabel,30,  40);
        this.add(singerNameLabel);
        singerNationalityLabel = getSingerInformationLabel(singerNationalityLabel,100, 25);
        this.add(singerNationalityLabel);
        singerJobLabel = getSingerInformationLabel(singerJobLabel,160, 25);
        this.add(singerJobLabel);
        singerMasterpieceLabel = getSingerInformationLabel(singerMasterpieceLabel,220, 25);
        this.add(singerMasterpieceLabel);
        tableHeaderPanelInit();
        this.add(tableHeaderPanel);
        musicScrollPanelInit();
        this.add(musicScrollPanel);
    }
    /**
     * 初始化歌手照片，距离整体面板左侧以及上侧距离都为0，大小为250
     * */
    private void singerImageInit(){
        singerImage.setBounds(100,30,220,220);
    }
    /**
     * 对于歌手名字，国籍，职业，代表作四个标签的初始化，宽为300，距离主窗体右下面板为400。
     * 字体水平居中，字体大小为40时加粗。
     * */
    private JLabel getSingerInformationLabel(JLabel singerInformationLabel,int y,int height){
        singerInformationLabel.setBounds(400,y,300,height);
        if(height==40) {
            singerInformationLabel.setFont(new Font(null, Font.BOLD, height));
        }else{
            singerInformationLabel.setFont(new Font(null, Font.PLAIN, height));
        }
        singerInformationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return singerInformationLabel;
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
        songInformationLabel.setFont(new Font(null, Font.BOLD, 24));
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
        showMusicPanel.setLayout(null);
        showMusicPanel.setBackground(Color.white);
        musicScrollPanel.getViewport().add(showMusicPanel);
    }
    /**
     * 将歌手的头像，名字，职业，国籍，代表作以及相关的音乐作品列表加载到面板中
     * */
    public void loadMusicData(SingerMusicList singerMusicList){
        ImageIcon imageIcon= OnlineImageUtil.getOvalImage(singerMusicList.getSingerPhotoUrl(),220);
        singerImage.setIcon(imageIcon);
        singerNameLabel.setText(singerMusicList.getSingerName());
        singerJobLabel.setText("职业："+singerMusicList.getJob());
        singerNationalityLabel.setText("国籍："+singerMusicList.getNationality());
        singerMasterpieceLabel.setText("代表作："+singerMusicList.getMasterpiece());
        List<Music> musicList = singerMusicList.getMusicList();
        changeMusicList(musicList);
    }
    /**
     * 将歌手的相关的音乐作品列表加载到音乐列表面板中
     * */
    public void changeMusicList(List<Music> musicList){
        showMusicPanel.removeAll();
        showMusicPanel.setPreferredSize(new Dimension(950,musicList.size()*50));
        for (int i = 0; i < musicList.size(); i++) {
            Music music=musicList.get(i);
            ShowMusicPanel musicPanel=new ShowMusicPanel(music,i*50,i);
            showMusicPanel.add(musicPanel);
        }
        showMusicPanel.repaint();
        showMusicPanel.validate();
    }
    private final static SingerMusicListPanel INSTANCE=new SingerMusicListPanel();
    public static SingerMusicListPanel getSingerMusicListPanel(){
        return INSTANCE;
    }
}
