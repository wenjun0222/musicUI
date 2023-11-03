package com.ning.ui.main.right_down_panel;

import com.ning.common_component.SingerShowPanel;
import com.ning.entity.Singer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 歌手列表展示面板
 * */
public class SingerListShowPanel extends JPanel{
    private final static SingerListShowPanel INSTANCE=new SingerListShowPanel();
    /**
     * 歌手标签
     * */
    private JLabel singerLabel=new JLabel();
    /**
     * 歌手列表展示小面板
     * */
    private JPanel singerListPanel=new JPanel();
    /**
     * 滚动面板
     * */
    private JScrollPane scrollPanel=new JScrollPane(singerListPanel);

    public SingerListShowPanel(){
        init();
    }
    /**
     *初始化歌手面板，宽为950，高为620
     * */
    public void init(){
        this.setLayout(null);
        this.setBounds(0,0,950,620);
        this.setBackground(Color.white);
        singerLabelInit();
        this.add(singerLabel);
        scrollPaneInit();
        this.add(scrollPanel);
//        singerListPanelInit();
    }
    /**
     * 初始化歌手标签，宽为500，高为40
     * 距离整体面板左侧距离为50，上侧距离为20
     * 字体加粗，大小为40
     * */
    private void singerLabelInit(){
        singerLabel.setBounds(50,20,500,40);
        singerLabel.setText("歌手");
        singerLabel.setFont(new Font(null,Font.BOLD,40));
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
        singerListPanel.setLayout(null);
        singerListPanel.setBackground(Color.white);
        scrollPanel.setViewportView(singerListPanel);
        scrollPanel.setFocusTraversalPolicyProvider(false);
    }
    public JPanel getSingerListPanel(){
        return singerListPanel;
    }
    public static SingerListShowPanel getSingerListShowPanel(){
        return INSTANCE;
    }
    public void loadMusicData(List<Singer> singerList, Map<String,ImageIcon> singerImageMap){
        //开始将歌单分类展示在主窗体右下面板中，各个歌单类型之间的空隙距离为20，与展示列表面板上侧距离为20，左侧距离为45
        int size = singerList.size();
        //列数
        int columnSize = 4;
        //行数
        int rowSize = size / columnSize + 1;
        singerListPanel.removeAll();
        singerListPanel.setPreferredSize(new Dimension(950,rowSize*250+40));
        int index = 0;
        for (int y = 0; y < rowSize; y++) {
            for (int x = 0; x < columnSize; x++) {
                Singer singer = singerList.get(index);
                ImageIcon singerImage=singerImageMap.get(singer.getSingerPhotoUrl());
                SingerShowPanel singerShowPanel=new SingerShowPanel(singer,20+x*220,20+250*y,singerImage);
                singerListPanel.add(singerShowPanel);
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
        singerListPanel.repaint();
        singerListPanel.validate();
    }

}
