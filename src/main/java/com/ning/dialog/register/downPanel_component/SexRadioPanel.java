package com.ning.dialog.register.downPanel_component;

import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 性别选择面板
 * */
public class SexRadioPanel extends JPanel {
    /**
     * 性别图标标签
     * */
    private JLabel sexIconLabel=new JLabel();
    /**
     * 单选框按钮组件
     * */
    private ButtonGroup buttonGroup=new ButtonGroup();
    /**
     * 男性单选框按钮
     * */
    private JRadioButton manRadio=new JRadioButton("男");
    /**
     * 男性单选框按钮
     * */
    private JRadioButton womanRadio=new JRadioButton("女");
    public SexRadioPanel(){
        init();
    }
    /**
     * 初始化性别单选框面板，宽为270，高为40，距离父面板左侧距离为90，上侧距离为505
     * 背景色为白色，边框为深天蓝色。
     * */
    private void init(){
        this.setBounds(90,505,270,40);
        this.setBorder(BorderFactory.createLineBorder(new Color(0,191,255)));
        this.setLayout(null);
        this.setBackground(Color.white);
        sexIconLabelInit();
        this.add(sexIconLabel);
        sexRadioInit();
        this.add(manRadio);
        this.add(womanRadio);
    }
    /**
     * 初始化性别图标标签，距离性别单选面板左侧以及上侧距离都为6，大小为24
     * */
    private void sexIconLabelInit(){
        ImageIcon imageIcon= LocalImageUtil.
                getLocalSquareImage("image/icon/性别.png",24);
        sexIconLabel.setBounds(6,6,24,24);
        sexIconLabel.setIcon(imageIcon);
    }
    /**
     * 初始化性别单选按钮，背景色为白色。字体大小为20，加粗，宽为50，高为38.
     * 男性单选按钮距离面板左侧距离为50，上侧距离为1.默认被选择
     * 女性单选按钮距离面板左侧距离为110，上侧距离为1.默认被选择
     * */
    private void sexRadioInit(){
        buttonGroup.add(manRadio);
        buttonGroup.add(womanRadio);
        manRadio.setBounds(50,1,50,38);
        manRadio.setFont(new Font(null,Font.BOLD,20));
        manRadio.setFocusable(false);
        manRadio.setBackground(Color.white);
        manRadio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        womanRadio.setBounds(110,1,50,38);
        womanRadio.setFont(new Font(null,Font.BOLD,20));
        womanRadio.setFocusable(false);
        womanRadio.setBackground(Color.white);
        womanRadio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        manRadio.setSelected(true);
    }

    public void clearCache(){
        manRadio.setSelected(true);
    }

    public JRadioButton getManRadio() {
        return manRadio;
    }
}
