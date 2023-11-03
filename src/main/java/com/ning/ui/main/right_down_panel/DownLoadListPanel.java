package com.ning.ui.main.right_down_panel;

import javax.swing.*;
import java.awt.*;

/**
 * 下载列表面板
 * */
public class DownLoadListPanel extends JPanel {
    private final static DownLoadListPanel INSTANCE=new DownLoadListPanel();
    public static DownLoadListPanel getDownLoadListPanel(){
        return INSTANCE;
    }
    /**
     * 下载列表标签
     * */
    private JLabel downLoadLabel=new JLabel();
    /**
     * 下载列表表头面板
     * */
    private JPanel tableHeaderPanel=new JPanel();
    /**
     * 下列表展示滚动面板
     * */
    JScrollPane downloadScrollPanel=new JScrollPane();
    /**
     * 下载列表显示小面板
     * */
    JScrollPane downloadListShowPanel=new JScrollPane();
    public DownLoadListPanel(){
        init();
    }
    private void init(){
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(0,0,950,620);
        downLoadLabelInit();
        this.add(downLoadLabel);
        tableHeaderPanelInit();
        downloadScrollPanelInit();
        this.add(downloadScrollPanel);
    }
    /**
     * 初始化下载列表标签，宽为500，高为40
     * 距离整体面板左侧距离为50，上侧距离为20
     * 字体加粗，大小为40
     * */
    private void downLoadLabelInit(){
        downLoadLabel.setBounds(50,20,500,40);
        downLoadLabel.setText("下载列表");
        downLoadLabel.setFont(new Font(null,Font.BOLD,40));
    }
    /**
     * 初始化下载列表表头，宽为900，高为30，距离下载面板左侧距离为50，上侧距离为100,背景色为白色
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
        //歌曲下载进度标签，距离表头面板左侧距离为350
        JLabel progressLabel = getSongInformationLabel(350, "进度");
        tableHeaderPanel.add(progressLabel);
        //歌曲大小标签，距离表头面板左侧距离为675
        JLabel sizeLabel = getSongInformationLabel(675, "大小");
        tableHeaderPanel.add(sizeLabel);
    }
    /**
     * 下载列表的表头面板中歌曲名称，歌手名称，进度，大小等标签获取方法
     * 宽为80，高为30，距离表头面板左侧距离由x决定，上侧距离为0
     * 字体大小为24，加粗，颜色为灰色。
     * */
    private JLabel getSongInformationLabel(int x,String prompt){
        JLabel tableHeaderLabel=new JLabel();
        tableHeaderLabel.setFont(new Font(null,Font.BOLD,24));
        tableHeaderLabel.setBounds(x,0,80,30);
        tableHeaderLabel.setText(prompt);
        tableHeaderLabel.setForeground(Color.GRAY);
        return tableHeaderLabel;
    }
    /**
     * 初始化滚动面板，宽为950，高为520，距离展示面板左侧距离为0，上侧距离为100.
     * 垂直滚动条显示，水平滚动条不显示
     * */
    private void downloadScrollPanelInit(){
        downloadScrollPanel.setBounds(0,100,950,520);
        downloadScrollPanel.setBorder(null);
        downloadScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        downloadScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        downloadScrollPanel.setBackground(Color.white);
        downloadListShowPanel.setLayout(null);
        downloadListShowPanel.setBackground(Color.white);
        downloadScrollPanel.getViewport().add(downloadListShowPanel);
    }

}
