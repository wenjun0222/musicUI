package com.ning.ui.main;

import com.ning.ui.main.listener.MainLeftPanelListener;
import com.ning.utils.OvalImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 主体窗口左侧面板
 * */
public class MainLeftPanel extends JPanel {
    private final  static  MainLeftPanel INSTANCE=new MainLeftPanel();
    public static MainLeftPanel getMainLeftPanel(){
        return INSTANCE;
    }
    /**
     * 主窗体左侧面板监听事件
     * */
    private MainLeftPanelListener leftPanelListener=new MainLeftPanelListener();
    /**
     * 主窗口左侧面板的宽度
     * */
    private final static int WIDTH=250;
    /**
     * 主窗口左侧面板的高度
     * */
    private final static int HEIGHT=800;
    /**
     * 面板图标
     * */
    private OvalImageUtil image=new OvalImageUtil("image/熊猫.jpg",50);
    /**
     * 软件名称标签
     * */
    private JLabel softwareNameLabel=new JLabel();
    /**
     * 在线音乐系列标签
     * */
    private JLabel onlineMusicLabel=new JLabel();
    /**
     * 歌手按钮面板,距离面板左侧距离为25，顶部距离为200，宽为200，高为40
     * */
    private LeftButtonPanel singerButtonPanel=new LeftButtonPanel(200,"image/icon/歌手.png","歌手");
    /**
     * 歌单分类按钮面板,距离面板左侧距离为25，顶部距离为260，宽为200，高为40
     * */
    private LeftButtonPanel songClassifyButtonPanel=new LeftButtonPanel(260,"image/icon/分类.png","歌单分类");
    /**
     * 我的音乐系列标签
     * */
    private JLabel myMusicLabel=new JLabel();
    /**
     * 我喜欢按钮面板,距离面板左侧距离为25，顶部距离为420，宽为200，高为40
     * */
    private LeftButtonPanel ILikeButtonPanel=new LeftButtonPanel(420,"image/icon/我喜欢.png","我喜欢");
    /**
     * 本地下载按钮面板,距离面板左侧距离为25，顶部距离为480，宽为200，高为40
     * */
    private LeftButtonPanel downloadButtonPanel=new LeftButtonPanel(480,"image/icon/下载.png","本地下载");
    /**
     * 播放历史按钮面板,距离面板左侧距离为25，顶部距离为540，宽为200，高为40
     * */
    private LeftButtonPanel playHistoryButtonPanel=new LeftButtonPanel(540,"image/icon/播放历史.png","播放历史");
    public MainLeftPanel(){
        init();
    }
    /**
     * 初始化主窗体左侧面板，距离左左侧距离以及上侧距离为0。宽为250，高为800，背景色为浅灰色
     * */
    public void init(){
        leftPanelListener.setMainLeftPanel(this);
        this.setBounds(0,0,WIDTH,HEIGHT);
        this.setLayout(null);
        this.setBackground(new Color(220,220,220));
        imageInit();
        this.add(image);
        softwareNameLabelInit();
        this.add(softwareNameLabel);
        onlineMusicLabelInit();
        this.add(onlineMusicLabel);
        singerButtonPanel.addMouseListener(leftPanelListener.singerButtonListener());
        this.add(singerButtonPanel);
        songClassifyButtonPanel.addMouseListener(leftPanelListener.songClassifyListener());
        this.add(songClassifyButtonPanel);
        myMusicLabelInit();
        this.add(myMusicLabel);
        ILikeButtonPanel.addMouseListener(leftPanelListener.MyLIkeMusicListener());
        this.add(ILikeButtonPanel);
        downloadButtonPanel.addMouseListener(leftPanelListener.downloadListener());
        this.add(downloadButtonPanel);
        playHistoryButtonPanel.addMouseListener(leftPanelListener.playHistoryListener());
        this.add(playHistoryButtonPanel);
    }
    /**
     * 初始化主窗体软件圆形图标，距离面板左侧为30，上侧为30，大小为50，
     * */
    public void imageInit(){
        image.setBounds(30,30,50,50);
    }
    /**
     * 初始化主窗体软甲名称标签，宽为200，高为50，距离面板左侧为90，上侧为30
     * 字体大小为30，加粗
     * */
    public void softwareNameLabelInit(){
        softwareNameLabel.setText("熊猫音乐");
        softwareNameLabel.setBounds(90,30,200,50);
        softwareNameLabel.setFont(new Font(null,Font.BOLD,30));
    }
    /**
     * 初始化在线音乐标签，宽为200，高为50，距离面板左侧为30，上侧为150
     * 字体颜色为灰色，加粗，大小为25
     * */
    public void onlineMusicLabelInit() {
        onlineMusicLabel.setText("在线音乐");
        onlineMusicLabel.setBounds(30,150,200,30);
        onlineMusicLabel.setFont(new Font(null,Font.BOLD,25));
        onlineMusicLabel.setForeground(Color.gray);
    }
    /**
     * 初始化我的音乐标签，宽为200，高为50，距离面板左侧为30，上侧为370
     * 字体颜色为灰色，加粗，大小为25
     * */
    public void myMusicLabelInit() {
        myMusicLabel.setText("我的音乐");
        myMusicLabel.setBounds(30,370,200,30);
        myMusicLabel.setFont(new Font(null,Font.BOLD,25));
        myMusicLabel.setForeground(Color.gray);
    }

    public LeftButtonPanel getILikeButtonPanel() {
        return ILikeButtonPanel;
    }

    public LeftButtonPanel getDownloadButtonPanel() {
        return downloadButtonPanel;
    }

    public LeftButtonPanel getPlayHistoryButtonPanel() {
        return playHistoryButtonPanel;
    }

    public LeftButtonPanel getSingerButtonPanel() {
        return singerButtonPanel;
    }

    public LeftButtonPanel getSongClassifyButtonPanel() {
        return songClassifyButtonPanel;
    }
}
