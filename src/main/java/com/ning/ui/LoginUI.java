package com.ning.ui;

import com.ning.UIParent;
import com.ning.common_component.Buttons;
import com.ning.dialog.register.downPanel_component.InputPanel;
import com.ning.dialog.register.downPanel_component.PasswordPanel;
import com.ning.ui.login_component.LoginTopPanel;
import com.ning.ui.login_component.listener.LoginListener;
import com.ning.utils.LocalImageUtil;
import com.ning.utils.OvalImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 登录界面窗体
 * */
public class LoginUI extends UIParent {
    private final static LoginUI INSTANCE=new LoginUI();
    public static LoginUI getLoginUI(){
        return INSTANCE;
    }
    private LoginListener loginListener=new LoginListener();
    /**
     * 登录窗口的宽度
     * */
    private final static int WIDTH=400;
    /**
     * 登录窗口的高度
     * */
    private final static int HEIGHT=380;
    /**
     * 顶部面板
     * */
    private LoginTopPanel topPanel=new LoginTopPanel();
    /**
     * 底部面板
     * */
    private JPanel bottomPanel=new JPanel();
    /**
     * 账号输入框面板,距离底部面板上侧距离为60，左侧距离为75，自身高度40，宽度250
     * */
    private InputPanel inputPanel=new InputPanel("image/icon/用户.png","输入账号",
            75,60,250,40);
    /**
     * 密码输入框面板,距离底部面板上侧距离为120，左侧距离为75，自身高度40，宽度250
     * */
    private PasswordPanel passwordPanel=new PasswordPanel("输入密码",
    75,120,250,40);
    /**
     * 用户注册按钮标签
     * */
    private JLabel registerLabel=new JLabel();
    /**
     * 登录按钮
     * */
    private Buttons loginButton=new Buttons();
    /**
     * 忘记密码按钮标签
     * */
    private JLabel forgetPwdLabel=new JLabel();
    public LoginUI(){
    }
    /**
     * 初始化登录窗口，长度为400。宽度为370
     * */
    public void loginUIInit(){

        loginListener.setLoginUI(this);
        ImageIcon windowIcon=LocalImageUtil.getLocalSquareImage("image/熊猫.png",40);
        this.setIconImage(windowIcon.getImage().getScaledInstance(40,40,Image.SCALE_REPLICATE));
        this.setTitle("熊猫音乐");
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        this.setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH,HEIGHT);
        topPanel.setBounds(0,0,400,100);
        this.add(topPanel);
        bottomPanelInit();
        this.add(bottomPanel);
        this.addMouseListener(loginListener.onTopListener());
        this.setVisible(true);
    }
    /**
     * 初始化底部面板，距离窗体左侧距离为0，上侧为100，宽度为400，高度为280
     * 添加熊猫圆形图片，直径为100像素，距离面板左侧150，上侧为-50
     * 添加账号输入框以及密码输入框
     * 添加登录按钮，距离底部面板上侧距离为180，左侧为75。
     * 添加注册账号标签，距离底部面板上侧距离为240，左侧为25。
     * 添加注册账号标签，距离底部面板上侧距离为240，左侧为275。
     * */
    private void bottomPanelInit(){
        bottomPanel.setBackground(new Color(245,245,245));
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(0,100,400,280);
        OvalImageUtil pandaIcon=new OvalImageUtil("image/熊猫.png",100);
        pandaIcon.setBounds(150,-50,100,100);
        bottomPanel.add(pandaIcon);
        bottomPanel.add(inputPanel);
        bottomPanel.add(passwordPanel);
        loginButtonInit();
        bottomPanel.add(loginButton);
        registerLabelInit();
        bottomPanel.add(registerLabel);
        forgetPwdLabelInit();
        bottomPanel.add(forgetPwdLabel);
    }
    /**
     * 初始化登录按钮，宽度为250，高度为40
     * 字体颜色为白色，字体大小为30，加粗
     * */
    private void loginButtonInit(){
        loginButton.setBounds(75,180,250,45);
        loginButton.setColor(new Color(0,191,255));
        loginButton.setText("登   录");
        loginButton.setFont(new Font(null,Font.BOLD,30));
        loginButton.setForeground(Color.WHITE);
        loginButton.addMouseListener(loginListener.loginButtonListener());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 初始化注册账号标签，高为25，宽为100
     * 字体颜色为黑，加粗，大小为18
     * */
    private void registerLabelInit(){
        registerLabel.setText("注册账号");
        registerLabel.setFont(new Font(null,Font.BOLD,18));
        registerLabel.setForeground(Color.black);
        registerLabel.setBounds(25,240,100,25);
        registerLabel.addMouseListener(loginListener.registerDialogListener());
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 初始化忘记密码标签，高为25，宽为100
     * 字体颜色为黑，加粗，大小为18
     * */
    public void forgetPwdLabelInit(){
        forgetPwdLabel.setText("忘记密码?");
        forgetPwdLabel.setFont(new Font(null,Font.BOLD,18));
        forgetPwdLabel.setForeground(Color.black);
        forgetPwdLabel.setBounds(275,240,100,25);
        forgetPwdLabel.addMouseListener(loginListener.updatePwdDialogListener());
        forgetPwdLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 外部可获取缩顶部面板进行事件监听动作
     * */
    public LoginTopPanel getTopPanel() {
        return topPanel;
    }
    /**
     * 外部可获取账号输入面板进行事件监听动作
     * */
    public InputPanel getInputPanel() {
        return inputPanel;
    }
    /**
     * 外部可获取密码输入面板进行事件监听动作
     * */
    public PasswordPanel getPasswordPanel() {
        return passwordPanel;
    }
    /**
     * 外部可获取注册标签进行事件监听动作
     * */
    public JLabel getRegisterLabel() {
        return registerLabel;
    }
    /**
     * 外部可获取登录按钮进行事件监听动作
     * */
    public Buttons getLoginButton() {
        return loginButton;
    }
    /**
     * 外部可获取忘记密码标签进行事件监听动作
     * */
    public JLabel getForgetPwdLabel() {
        return forgetPwdLabel;
    }
}
