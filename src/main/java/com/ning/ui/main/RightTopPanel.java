package com.ning.ui.main;

import com.ning.entity.query.UserQuery;
import com.ning.ui.main.listener.MainRightTopListener;
import com.ning.utils.LocalImageUtil;
import com.ning.utils.OnlineImageUtil;
import com.ning.utils.UserUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 主窗体右上面板
 * */
public class RightTopPanel extends JPanel {
    private UserQuery userQuery= UserUtil.getUser();
    private final static RightTopPanel INSTANCE=new RightTopPanel();
    public static RightTopPanel getRightTopPanel(){
        return INSTANCE;
    }
    /**
     * 主窗体右上面板鼠标监听事件
     * */
    private MainRightTopListener rightTopListener=new MainRightTopListener();
    /**
     * 窗体关闭图标面板
     * */
    private JPanel closePanel=new JPanel();
    /**
     * 窗体缩小图标面板
     * */
    private JPanel reducePanel=new JPanel();
    /**
     * 窗体设置图标面板
     * */
    private JPanel settingPanel=new JPanel();
    /**
     * 音乐搜索面板
     * */
    private JPanel searchPanel=new JPanel();
    /**
     * 窗体关闭图标，高度为20，正方形
     * */
    private JLabel closeIcon=new JLabel();
    /**
     * 窗体缩小图标，高度为20，正方形
     * */
    private JLabel reduceIcon=new JLabel();
    /**
     * 窗体设置图标，高度为20，正方形
     * */
    private JLabel settingIcon=new JLabel();
    /**
     * 搜索图标,大小为30
     * */
    private JLabel searchImage=new JLabel();
    /**
     * 搜索输入框
     * */
    private JTextField searchField=new JTextField();
    /**
     * 用户头像标签
     * */
    private JLabel userAvatarLabel=new JLabel();
    /**
     * 用户名称标签
     * */
    private JLabel usernameLabel=new JLabel();
    public RightTopPanel(){
        init();
    }
    public void init(){
        System.out.println(userQuery);
        rightTopListener.setRightTopPanel(this);
        this.setLayout(null);
        this.setBounds(250,0,950,80);
        this.setBackground(new Color(173,216,230));
        closePanelInit();
        this.add(closePanel);
        reducePanelInit();
        this.add(reducePanel);
        searchPanelInit();
        this.add(searchPanel);
        settingPanelInit();
        this.add(settingPanel);
        userAvatarLabelInit();
        this.add(userAvatarLabel);
        usernameLabelInit();
        this.add(usernameLabel);
    }
    /**初始化窗体关闭图标面板，高度为40，面板整体离登录窗体右侧距离为40，离上侧距离为0，，边框为亮蓝色
     * 关闭图标位置离面板上、左侧距离为10
     * */
    private void closePanelInit(){
        closePanel.setBackground(new Color(173,216,230));
        closePanel.setLayout(null);
        closePanel.setBounds(getWidth()-40,0,40,40);
        closeIcon.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/关闭.png",20));
        closeIcon.addMouseListener(rightTopListener.closeListener());
        closeIcon.setBounds(10,10,20,20);
        closePanel.add(closeIcon);
    }
    /**初始化窗体缩小图标面板，高度为40，面板整体离登录窗体右侧距离为80，离上侧距离为0，边框为亮蓝色
     * 缩小图标位置离面板上、左侧距离为10
     * */
    private void reducePanelInit(){
        reducePanel.setBackground(new Color(173,216,230));
        reducePanel.setLayout(null);
        reducePanel.setBounds(getWidth()-80,0,40,40);
        reduceIcon.setBounds(10,10,20,20);
        reduceIcon.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/缩小.png",20));
        reduceIcon.addMouseListener(rightTopListener.reduceListener());
        reducePanel.add(reduceIcon);
    }
    /**初始化窗体设置图标面板，高度为40，面板整体离登录窗体右侧距离120，离上侧距离为0，边框为亮蓝色
     * 缩小图标位置离面板上、左侧距离为10
     * */
    private void settingPanelInit(){
        settingPanel.setBackground(new Color(173,216,230));
        settingPanel.setLayout(null);
        settingPanel.setBounds(getWidth()-120,0,40,40);
        settingIcon.setBounds(10,10,20,20);
        settingIcon.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/设置.png",20));
        settingIcon.addMouseListener(rightTopListener.settingListener());
        settingPanel.add(settingIcon);
    }
    /**
     * 初始化音乐搜索输入框面板，距离整体面板左侧距离为100，上侧距离为20.宽为250，高为40
     * 添加搜索图标，距离面板左侧距离为215，上侧距离为5，大小为30
     * 添加输入框，距离面板左侧距离为5，上侧距离为2，宽为205，高为36
     * */
    public void searchPanelInit(){
        searchPanel.setBackground(Color.white);
        searchPanel.setBounds(100,20,250,40);
        searchPanel.setLayout(null);
        searchImage.setIcon(LocalImageUtil.getLocalSquareImage("image/icon/搜索.png",30));
        searchImage.setBounds(215,5,30,30);
        searchImage.addMouseListener(rightTopListener.searchListener());
        searchPanel.add(searchImage);
        String prompt="搜索音乐";
        searchField.setText(prompt);
        searchField.setBorder(null);
        searchField.setBackground(Color.white);
        searchField.setFont(new Font(null,Font.PLAIN,20));
        searchField.setBounds(5,2,205,36);
        searchField.addMouseListener(rightTopListener.searchFieldListener(prompt));
        searchPanel.add(searchField);
    }
    /**
     * 用户头像标签初始化，距离整体面板左侧距离为450，上侧距离为10，大小为60
     * */
    private void userAvatarLabelInit(){
        ImageIcon imageIcon= OnlineImageUtil.getOvalImage(userQuery.getAvatarPath(),50);
        userAvatarLabel.setBounds(600,15,50,50);
        userAvatarLabel.setIcon(imageIcon);
    }
    private void usernameLabelInit(){
        usernameLabel.setBounds(660,20,200,40);
        usernameLabel.setFont(new Font(null,Font.BOLD,25));
        usernameLabel.setText(userQuery.getUsername());
    }
    public JPanel getClosePanel() {
        return closePanel;
    }
    public JPanel getReducePanel() {
        return reducePanel;
    }
    public JPanel getSettingPanel() {
        return settingPanel;
    }
    public JLabel getCloseIcon() {
        return closeIcon;
    }
    public JLabel getReduceIcon() {
        return reduceIcon;
    }
    public JLabel getSettingIcon() {
        return settingIcon;
    }
    public JTextField getSearchField() {
        return searchField;
    }
}
