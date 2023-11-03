package com.ning.ui.main;

import com.ning.listener.PlayMusicListener;
import com.ning.thread.PlayMusic;
import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 音乐播放面板
 * */
public class PlayMusicPanel extends JPanel {
    private final  static PlayMusicPanel INSTANCE=new PlayMusicPanel();
    public static PlayMusicPanel getPlayMusicPanel(){
        return INSTANCE;
    }
    /**
     * 音乐播放进度条
     * */
    private JProgressBar songProgressBar=new JProgressBar();
    /**
     * 音乐播放类型菜单
     * */
    private JPopupMenu playTypeMenu=new JPopupMenu();
    /**
     * 列表循环播放
     * */
    private JMenuItem listLoopMenuItem=new JMenuItem("列表循环");
    /**
     * 单曲循环播放
     * */
    private JMenuItem singleLoopMenuItem=new JMenuItem("单曲循环");
    /**
     * 音乐播放类型菜单
     * */
    private JLabel playTypeLabel=new JLabel();
    /**
     * 音乐播放线程
     * */
    private PlayMusic playMusic=PlayMusic.getInstance();
    /**
     * 歌曲播放事件监听类
     * */
    private PlayMusicListener playMusicListener=new PlayMusicListener();
    /**
     * 歌手照片，大小为60
     * */
    private JLabel singerImage=new JLabel();
    /**
     * 是否添加到我喜欢面板
     * */
    private JPanel  addMyLikePanel=new JPanel();
    /**
     * 已添加到我喜欢图标,大小为28
     * */
    private JLabel hasAddMyLikeIcon=new JLabel();
    /**
     * 未添加到我喜欢图标，大小也为28
     * */
    private JLabel noAddMyLikeIcon=new JLabel();
    /**
     * 下载图标，大小为28
     * */
    private JLabel downloadIcon=new JLabel();
    /**
     * 歌曲名称标签
     * */
    private JLabel songNameLabel=new JLabel();
    /**
     * 播放上一首图标，大小为30
     * */
    private JLabel previousSongIcon=new JLabel();
    /**
     * 播放下一首图标，大小为30
     * */
    private JLabel nextSongIcon=new JLabel();
    /**
     * 播放面板，大小为30
     * */
    private JPanel playSongPanel=new JPanel();
    /**
     * 播放音乐图标，大小为40
     * */
    private JLabel playSongIcon=new JLabel();
    /**
     * 停止播放音乐图标，大小为40
     * */
    private JLabel stopSongIcon=new JLabel();
    /**
     * 显示歌词标签
     * */
    private JLabel showLrcLabel=new JLabel();
    public PlayMusicPanel(){
        init();
    }
    /**
     * 初始化音乐播放面板，宽为950，高为100，距离主窗体左侧距离为250，上侧距离为700，背景色为白烟色，添加一系列组件
     * */
    public void init(){
        this.setBounds(250,700,950,100);
        this.setLayout(null);
        this.setBackground(new Color(245,245,245));
        songProgressBarInit();
        this.add(songProgressBar);
        singerImageInit();
        this.add(singerImage);
        songNameLabelInit();
        this.add(songNameLabel);
        previousSongInit();
        this.add(previousSongIcon);
        playSongPanelInit();
        this.add(playSongPanel);
        stopSongInit();
        nextSongInit();
        this.add(nextSongIcon);
        addMyLikePanelInit();
        this.add(addMyLikePanel);
        downloadIconInit();
        this.add(downloadIcon);
        showLrcLabelInit();
        this.add(showLrcLabel);
        playTypeLabelInit();
        this.add(playTypeLabel);
        PopupMenuInit();
        hasAddMyLikeIconInit();
        noAddMyLikeIconInit();
        playMusicListener.setPlayMusicPanel(this);
        playMusic.setPlayMusicPanel(this);
    }
    /**
     * 初始化歌手图片，距离音乐播放面板左侧距离为40，上侧为20，大小为60
     * */
    private void singerImageInit(){
        singerImage.setBounds(40,20,60,60);
        singerImage.setBackground(new Color(245,245,245));
        ImageIcon imageIcon= LocalImageUtil.getLocalOvalImage("image/熊猫.png",60);
        singerImage.setIcon(imageIcon);
    }/**
     * 初始化歌名标签，宽为240，高为28，距离音乐播放面板左侧距离为110，上侧为20，
     * 字体大小为20，加粗
     * */
    private void songNameLabelInit(){
        songNameLabel.setBounds(110,20,240,28);
        songNameLabel.setFont(new Font(null,Font.BOLD,20));
        songNameLabel.setText("熊猫音乐");
    }
    /**
     * 初始化爱心图标面板，距离音乐播放面板左侧距离为110，上侧为52，大小为28
     * */
    private void addMyLikePanelInit(){
        addMyLikePanel.setBounds(110,52,28,28);
        addMyLikePanel.setLayout(null);
        addMyLikePanel.setBackground(new Color(245,245,245));
        addMyLikePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMyLikePanel.add(noAddMyLikeIcon);
    }
    /**
     * 初始化已经添加到我喜欢音乐列表标签，大小为28
     * */
    public void hasAddMyLikeIconInit(){
        hasAddMyLikeIcon.setBounds(0,0,28,28);
        ImageIcon imageIcon= LocalImageUtil.getLocalSquareImage("image/icon/已添加到我喜欢.png",28);
        hasAddMyLikeIcon.setIcon(imageIcon);
        hasAddMyLikeIcon.addMouseListener(playMusicListener.cancelUserLikeListener());
    }
    /**
     * 初始化未添加到我喜欢音乐列表标签，大小为28
     * */
    public void noAddMyLikeIconInit(){
        noAddMyLikeIcon.setBounds(0,0,28,28);
        ImageIcon imageIcon= LocalImageUtil.getLocalSquareImage("image/icon/未添加到我喜欢.png",28);
        noAddMyLikeIcon.setIcon(imageIcon);
        noAddMyLikeIcon.addMouseListener(playMusicListener.addUserLikeListener());
    }
    /**
     * 初始化下载图标，距离音乐播放面板左侧距离为148，上侧为20，大小为28
     * */
    private void downloadIconInit(){
        downloadIcon.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/下载.png",28));
        downloadIcon.setBounds(148,52,28,28);
        downloadIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 初始化播放上一首按钮标签，距离音乐播放面板左侧距离为405，上侧为35，大小为30
     * */
    private void previousSongInit(){
        previousSongIcon.setBounds(405,35,30,30);
        previousSongIcon.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/上一首.png",30));
        previousSongIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        previousSongIcon.addMouseListener(playMusicListener.playPreviousSongListener());
    }
    /**
     * 初始化播放音乐按钮面板，距离音乐播放面板左侧距离为445，上侧为30，大小为40
     * 添加音乐播放或者停止事件
     * */
    private void playSongPanelInit(){
        playSongPanel.setBounds(455,30,40,40);
        playSongPanel.setLayout(null);
        playSongPanel.setBackground(new Color(245,245,245));
        playSongIconInit();
        stopSongInit();
        playSongPanel.add(stopSongIcon);
    }
    public void playSongIconInit(){
        playSongIcon.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/播放.png",40));
        playSongIcon.setBounds(0,0,40,40);
        playSongIcon.addMouseListener(playMusicListener.stopMusicListener());
        playSongIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 初始化播放下一首按钮标签，距离音乐播放面板左侧距离为515，上侧为35，大小为30
     * 添加播放下一首歌曲事件
     * */
    private void nextSongInit(){
        nextSongIcon.setBounds(515,35,30,30);
        nextSongIcon.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/下一首.png",30));
        nextSongIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextSongIcon.addMouseListener(playMusicListener.playNextSongListener());
    }
    /**
     * 初始化停止播放音乐按钮图标，距离音乐播放面板左侧距离为445，上侧为30，大小为40
     * */
    private void stopSongInit(){
        stopSongIcon.setBounds(0,0,40,40);
        stopSongIcon.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/停止.png",40));
        stopSongIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stopSongIcon.addMouseListener(playMusicListener.continuePlayListener());
    }
    /**
     * 初始化歌词窗体展示按钮标签，距离音乐播放面板左侧距离为850，上侧为30。宽为70，高为40
     * 字体加粗，大小为25
     * */
    private void showLrcLabelInit(){
        showLrcLabel.setText("歌词");
        showLrcLabel.setFont(new Font(null,Font.BOLD,25));
        showLrcLabel.setForeground(Color.BLUE);
        showLrcLabel.setBounds(850,30,70,40);
        showLrcLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showLrcLabel.addMouseListener(playMusicListener.lrcDialogShowListener());
    }
    /**
     * 初始化播放类型标签，距离音乐播放面板左侧距离为720，上侧为35。宽为70，高为30
     * 字体加粗，大小为25
     * */
    private void playTypeLabelInit(){
        playTypeLabel.setBounds(700,35,120,30);
        playTypeLabel.setFont(new Font(null,Font.BOLD,25));
        playTypeLabel.setText("列表循环");
        playTypeLabel.addMouseListener(playMusicListener.showPopupMenuListener());
        playTypeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 歌曲播放进度表
     * */
    private void songProgressBarInit(){
        songProgressBar.setBounds(0,0,950,8);
        songProgressBar.setBorderPainted(false);
        songProgressBar.setForeground(new Color(30,144,255));
        songProgressBar.setBackground(new Color(211,211,211));
        songProgressBar.setOrientation(SwingConstants.HORIZONTAL);
    }
    /**
     * 音乐播放类型菜单初始化
     * */
    public void PopupMenuInit(){
        playTypeMenu.setPopupSize(100,80);
        playTypeMenu.setBorderPainted(false);
        listLoopMenuItem.setPreferredSize(new Dimension(100,40));
        listLoopMenuItem.setFont(new Font(null,Font.BOLD,20));
        listLoopMenuItem.setBackground(new Color(0,191,255));
        listLoopMenuItem.addActionListener(playMusicListener.listLoopListener());
        playTypeMenu.add(listLoopMenuItem);
        singleLoopMenuItem.setPreferredSize(new Dimension(100,40));
        singleLoopMenuItem.setFont(new Font(null,Font.BOLD,20));
        singleLoopMenuItem.setBackground(new Color(0,191,255));
        singleLoopMenuItem.addActionListener(playMusicListener.singleLoopListener());
        playTypeMenu.add(singleLoopMenuItem);
    }
    /**
     * 音乐播放类型菜单栏显示
     * */
    public void showPopupMenu(Component component){
        playTypeMenu.show(component,950,650);
    }
    public JPanel getPlaySongPanel(){
        return playSongPanel;
    }

    public JPanel getAddMyLikePanel() {
        return addMyLikePanel;
    }

    public JLabel getHasAddMyLikeIcon() {
        return hasAddMyLikeIcon;
    }

    public JLabel getNoAddMyLikeIcon() {
        return noAddMyLikeIcon;
    }

    public JLabel getStopSongIcon() {
        return stopSongIcon;
    }

    public JLabel getPlaySongIcon() {
        return playSongIcon;
    }

    public JLabel getPlayTypeLabel() {
        return playTypeLabel;
    }
    public JLabel getSingerImage() {
        return singerImage;
    }
    /**
     * 设置爱心显示面板根据userFavoriteSongId的值来显示为红心还是黑心
     * */
    public void setAddMyLikePanel(Integer userFavoriteSongId){
        addMyLikePanel.removeAll();
        if(userFavoriteSongId!=null){
            addMyLikePanel.add(hasAddMyLikeIcon);
        }else {
            addMyLikePanel.add(noAddMyLikeIcon);
        }
        addMyLikePanel.repaint();
        addMyLikePanel.validate();
    }
    public JLabel getSongNameLabel() {
        return songNameLabel;
    }
    public JProgressBar getSongProgressBar() {
        return songProgressBar;
    }
}
