package com.ning.ui.main.right_down_panel;

import com.ning.common_component.SongTypeShowPanel;
import com.ning.entity.SongType;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 音乐类型展示面板
 * */
public class SongTypeListPanel extends JPanel {
    private final static SongTypeListPanel INSTANCE=new SongTypeListPanel();
    public static SongTypeListPanel getSongTypeListPanel(){
        return INSTANCE;
    }
    /**
     * 音乐类型标签
     * */
    private JLabel songTypeLabel=new JLabel();
    /**
     * 音乐类型列表展示小面板
     * */
    private JPanel songTypeListShowPanel=new JPanel();
    /**
     * 滚动面板
     * */
    private JScrollPane scrollPanel=new JScrollPane(songTypeListShowPanel);

    public SongTypeListPanel(){
        init();
    }
    /**
     *初始化音乐类型面板，宽为950，高为620
     * */
    public void init(){
        this.setLayout(null);
        this.setBounds(0,0,950,620);
        this.setBackground(Color.white);
        songTypeLabelInit();
        this.add(songTypeLabel);
        scrollPaneInit();
        this.add(scrollPanel);
    }
    /**
     * 初始化音乐类型标签，宽为500，高为40
     * 距离整体面板左侧距离为50，上侧距离为20
     * 字体加粗，大小为40
     * */
    private void songTypeLabelInit(){
        songTypeLabel.setBounds(50,20,500,40);
        songTypeLabel.setText("歌单类型");
        songTypeLabel.setFont(new Font(null,Font.BOLD,40));
    }
    /**
     * 初始化滚动面板，宽为950，高为550，距离整体面板左侧距离为0，上侧距离为70。
     * 垂直方向显示滚动条，水平方向不显示滚动条
     * */
    private void scrollPaneInit(){
        scrollPanel.setBorder(null);
        scrollPanel.setBounds(0,70,950,550);
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        songTypeListShowPanel.setLayout(null);
        songTypeListShowPanel.setBackground(Color.white);
        scrollPanel.setViewportView(songTypeListShowPanel);
        scrollPanel.setFocusTraversalPolicyProvider(false);
    }
    public void loadData(List<SongType> songTypeList, Map<String,ImageIcon> songTypeIconMap){
        //开始将歌单分类展示在主窗体右下面板中，各个歌单类型之间的空隙距离为20，与展示列表面板上侧距离为20，左侧距离为45
        int size = songTypeList.size();
        //列数
        int columnSize = 3;
        //行数
        int rowSize = size / columnSize + 1;
        songTypeListShowPanel.removeAll();
        songTypeListShowPanel.setPreferredSize(new Dimension(950, 310 * rowSize + 40));
        int index = 0;
        for (int y = 0; y < rowSize; y++) {
            for (int x = 0; x < columnSize; x++) {
                SongType songType = songTypeList.get(index);
                //直接从内存中获取歌单分类图标
                ImageIcon imageIcon = songTypeIconMap.get(songType.getTypePhotoUrl());
                SongTypeShowPanel songTypeShowPanel = new SongTypeShowPanel(songType, 45 + 295 * x,
                        20 + 310 * y, imageIcon);
                songTypeListShowPanel.add(songTypeShowPanel);
                index++;
                //加载完毕后，跳出该加载循环。
                if (index == size) {
                    break;
                }
            }
            //加载完毕后，跳出该加载循环。
            if (index == size) {
                break;
            }
        }
        songTypeListShowPanel.repaint();
        songTypeListShowPanel.validate();
    }
}
