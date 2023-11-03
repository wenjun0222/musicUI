package com.ning.ui.main;

import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;

public class LeftButtonPanel extends JPanel {
    /**
     * 按钮面板距离主窗口左侧面板顶部距离
     * */
    private int y;
    /**
     * 按钮面板的图标
     * */
    private JLabel image=new JLabel();
    /**
     * 图片名称
     * */
    private String photoName;
    /**
     * 按钮面板的名称
     * */
    private String buttonName;
    /**
     * 按钮面板的标签，主要负责设置按钮面板的名称
     * */
    private JLabel buttonLabel=new JLabel();
    public LeftButtonPanel(int y,String photoName,String buttonName){
        this.y=y;
        this.photoName=photoName;
        this.buttonName=buttonName;
        init();
    }
    /**
     * 初始化按钮面板，距离面板左侧为25，宽为200，高为40
     * 添加图标以及按钮名字标签，背景色为白烟色
     * */
    private void init() {
        this.setBounds(25,y,200,40);
        this.setLayout(null);
        this.setBackground(new Color(220,220,220));
        imageInit();
        this.add(image);
        buttonLabelInit();
        this.add(buttonLabel);
    }
    /**
     * 初始化按钮面板图标，距离按钮面板左侧以及上侧距离都为5，大小为30
     * */
    public void imageInit(){
        image.setBounds(5,5,30,30);
        image.setIcon(LocalImageUtil.getLocalSquareImage(photoName,30));
    }
    /**
     * 初始化按钮面板标签，距离按钮面板左侧距离为45，上侧为5，宽为160，高为30
     * 字体大小为26，加粗
     * */
    public void buttonLabelInit(){
        buttonLabel.setText(buttonName);
        buttonLabel.setFont(new Font(null,Font.BOLD,26));
        buttonLabel.setBounds(45,5,160,30);
        buttonLabel.setBackground(Color.yellow);
    }
    public JLabel getImage() {
        return image;
    }
}
