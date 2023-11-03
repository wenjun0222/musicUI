package com.ning.ui.login_component;

import com.ning.ui.login_component.listener.LoginTopListener;
import com.ning.utils.LocalImageUtil;
import com.ning.utils.OvalImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 登录窗口的顶部面板
 * */
public class LoginTopPanel extends JPanel {
    private LoginTopListener loginTopListener=new LoginTopListener();
    /**
     * 窗体关闭图标面板
     * */
    private JPanel closePanel=new JPanel();
    /**
     * 窗体缩小图标面板
     * */
    private JPanel reducePanel=new JPanel();
    /**
     * 窗体关闭图标，高度为20，正方形
     * */
    private JLabel closeIcon=new JLabel();
    /**
     * 窗体缩小图标，高度为20，正方形
     * */
    private JLabel reduceIcon=new JLabel();
    /**
     * 软件名称     * */
    private JLabel softwareName=new JLabel();
    /**
     * 熊猫图标标签
     * */
    private OvalImageUtil pandaIcon=new OvalImageUtil("image/熊猫.png",100);
    public LoginTopPanel(){
        init();
    }
    /**
     * 初始化登录窗体顶部面板，位置距离窗口上、左侧距离为0，长度为400，高为100,背景为深天蓝色
     * */
    private void init(){
        loginTopListener.setLoginTopPanel(this);
        this.setSize(400,100);
        this.setLayout(null);
        this.setBackground(new Color(0,191,255));
        closePanelInit();
        this.add(closePanel);
        reducePanelInit();
        this.add(reducePanel);
        softwareNameInit();
        this.add(softwareName);
        pandaIconInit();
        this.add(pandaIcon);
    }
    /**初始化窗体关闭图标面板，高度为40，面板整体离登录窗体右侧距离为40，离上侧距离为0，，边框为深天蓝色
     * 关闭图标位置离面板上、左侧距离为10
     * */
    private void closePanelInit(){
        closePanel.setLayout(null);
        closePanel.setBackground(new Color(0,191,255));
        closePanel.setBounds(getWidth()-40,0,40,40);
        ImageIcon imageIcon= LocalImageUtil.
                getLocalSquareImage("image/icon/关闭白色.png",20);
        closeIcon.setIcon(imageIcon);
        closeIcon.setBounds(10,10,20,20);
        closePanel.add(closeIcon);
        closeIcon.addMouseListener(loginTopListener.closeListener());
    }
    /**初始化窗体缩小图标面板，高度为40，面板整体离登录窗体右侧距离为80，离上侧距离为0，边框为深天蓝色
     * 缩小图标位置离面板上、左侧距离为10
     * */
    private void reducePanelInit(){
        reducePanel.setLayout(null);
        reducePanel.setBounds(getWidth()-80,0,40,40);
        reducePanel.setBackground(new Color(0,191,255));
        reduceIcon.setBounds(10,10,20,20);
        ImageIcon imageIcon= LocalImageUtil.
                getLocalSquareImage("image/icon/缩小白色.png",20);
        reduceIcon.setIcon(imageIcon);
        reduceIcon.addMouseListener(loginTopListener.reduceListener());
        reducePanel.add(reduceIcon);
    }
    /**
     * 添加圆形熊猫图片，直径为100，距离顶部面板左侧150，上侧50
     * */
    private void pandaIconInit(){
        pandaIcon.setBounds(150,50,100,100);
    }
    /**
     * 初始化软件名称标签，距离窗口顶部面板上侧距离为10、左侧距离为20，长160，宽45
     * 字体大小为32，颜色为白色，加粗
     * */
    private void softwareNameInit(){
        softwareName.setText("熊猫音乐");
        softwareName.setBounds(20,10,160,45);
        softwareName.setForeground(Color.white);
        softwareName.setFont(new Font(null,Font.BOLD,32));
    }
    /**
     * 外部可获取窗体关闭面板进行事件监听动作
     * */
    public JPanel getClosePanel() {
        return closePanel;
    }
    /**
     * 外部可获取窗体缩小面板进行事件监听动作
     * */
    public JPanel getReducePanel() {
        return reducePanel;
    }
}
