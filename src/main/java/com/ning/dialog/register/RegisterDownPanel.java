package com.ning.dialog.register;


import com.ning.common_component.Buttons;
import com.ning.dialog.register.downPanel_component.InputPanel;
import com.ning.dialog.register.downPanel_component.PasswordPanel;
import com.ning.dialog.register.downPanel_component.SexRadioPanel;
import com.ning.dialog.register.listener.RegisterDownListener;
import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 注册主窗体底部面板
 * */
public class RegisterDownPanel extends JPanel {
    private RegisterDownListener registerDownListener=new RegisterDownListener();
    /**
     * 面板的宽度
     * */
    private final static int WIDTH=450;
    /**
     * 面板的高度
     * */
    private final static int HEIGHT=640;
    /**
     * 用户头像显示标签
     * */
    private JLabel userAvatarLabel=new JLabel();
    /**
     * 更换用户头像按钮标签
     * */
    private JLabel updateAvatarLabel=new JLabel();
    /**
     * 用户名输入框面板,距离底部面板上侧距离245，左侧距离为100，自身高度40，宽度250
     * */
    private InputPanel inputUserNamePanel=new InputPanel("image/icon/用户.png","输入用户名",
            90,245,270,40);
    /**
     * 账号输入框面板,距离底部面板上侧距离310，左侧距离为100，自身高度40，宽度250
     * */
    private InputPanel inputAccountPanel=new InputPanel("image/icon/用户.png","输入账号",
            90,310,270,40);
    /**
     * 密码输入框面板,距离底部面板上侧距离375，左侧距离为90，自身高度40，宽度270
     * */
    private PasswordPanel passwordPanel=new PasswordPanel("输入密码",
            90,375,270,40);
    /**
     * 验证码输入框面板,距离底部面板上侧距离440，左侧距离为90，自身高度40，宽度160
     * */
    private InputPanel inputCodePanel=new InputPanel("image/icon/验证码.png","验证码",
            90,440,160,40);
    /**
     * 性别单选按钮面板
     * */
    private SexRadioPanel sexRadioPanel=new SexRadioPanel();
    /**
     * 注册按钮
     * */
    private Buttons registerButton=new Buttons();
    /**
     * 初始默认头像图标
     * */
    private ImageIcon imageIcon= LocalImageUtil.getLocalOvalImage("image/icon/默认头像.jpg",160);
    public RegisterDownPanel(){
        init();
    }
    /**
     * 初始化底部面板，背景色为白色，距离主题窗口左侧距离为0，上侧距离为40
     * */
    private void init(){
        registerDownListener.setRightDownPanel(this);
        this.setLayout(null);
        this.setBounds(0,40,WIDTH,HEIGHT);
        userAvatarLabelInit();
        this.add(userAvatarLabel);
        updateAvatarLabelInit();
        this.add(updateAvatarLabel);
        this.add(inputUserNamePanel);
        this.add(inputAccountPanel);
        this.add(passwordPanel);
        this.add(inputCodePanel);
        this.add(sexRadioPanel);
        registerButtonInit();
        this.add(registerButton);
        promptLabelInit();
    }
    /**
     * 初始化用户头像标签，大小为160，圆形，距离面板左侧距离为145，上侧距离为10
     * 默认为系统头像
     * */
    private void userAvatarLabelInit(){
        userAvatarLabel.setBounds(145,10,160,160);
        userAvatarLabel.setIcon(imageIcon);
    }
    /**
     * 初始化用户头像按钮标签，宽为200，高为40，距离面板左侧距离为125，上侧距离为180
     * 字体加粗，黑色，大小为25，居中。
     * */
    private void updateAvatarLabelInit(){
        updateAvatarLabel.setBounds(125,180,200,40);
        updateAvatarLabel.setText("上传头像");
        updateAvatarLabel.setBackground(Color.GRAY);
        updateAvatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateAvatarLabel.setFont(new Font(null,Font.BOLD,25));
        updateAvatarLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateAvatarLabel.addMouseListener(registerDownListener.updateAvatarListener());
    }

    /**
     * 初始化注册按钮,宽为98，高为40，距离左距离为252，上侧为570，背景为深天蓝色
     * 字体颜色为白烟色，大小为24，加粗
     * */
    public void registerButtonInit(){
        registerButton.setBounds(100,565,250,45);
        registerButton.setText("注   册");
        registerButton.setColor(new Color(0,191,255));
        registerButton.setFont(new Font(null,Font.BOLD,24));
        registerButton.setForeground(new Color(245,245,245));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addMouseListener(registerDownListener.registerButtonListener());
    }
    //240,305,370,435,500
    private void promptLabelInit(){
        JLabel usernamePromptLabel=getPromptLabel(220,"(*必填)用户名不得超过在七个字");
        this.add(usernamePromptLabel);
        JLabel accountPromptLabel=getPromptLabel(285,"(*必填)用户账号");
        this.add(accountPromptLabel);
        JLabel passwordPromptLabel=getPromptLabel(350,"(*必填)密码6-10个长度，禁止空格");
        this.add(passwordPromptLabel);
        JLabel codePromptLabel=getPromptLabel(415,"(*必填)验证码不得为空");
        this.add(codePromptLabel);
        JLabel sexPromptLabel=getPromptLabel(480,"性别单选按钮");
        this.add(sexPromptLabel);
    }
    /**
     * 用户名、账号，密码，验证码输入框以及性别单选按钮提示标签
     * 宽为290，高为20，距离主面板左侧距离为90。
     * 字体大小为10，红色，加粗。背景色为白色，
     * */
    private JLabel getPromptLabel(int y,String prompt){
        JLabel promptLabel=new JLabel();
        promptLabel.setBounds(90,y,290,25);
        promptLabel.setText(prompt);
        promptLabel.setFont(new Font(null,Font.BOLD,16));
        promptLabel.setForeground(Color.red);
        promptLabel.setBackground(Color.white);
        return promptLabel;
    }
    public void clearCache(){
        userAvatarLabel.setIcon(imageIcon);
        inputAccountPanel.clearCache();
        inputUserNamePanel.clearCache();
        inputCodePanel.clearCache();
        passwordPanel.clearCache();
        sexRadioPanel.clearCache();
    }
    /**
     * 以下方法被事件监听类获取以便于进行事鼠标事件监听
     * */
    public JLabel getUserAvatarLabel() {
        return userAvatarLabel;
    }
    public JLabel getUpdateAvatarLabel() {
        return updateAvatarLabel;
    }
    public InputPanel getInputUserNamePanel() {
        return inputUserNamePanel;
    }
    public InputPanel getInputAccountPanel() {
        return inputAccountPanel;
    }
    public PasswordPanel getPasswordPanel() {
        return passwordPanel;
    }
    public InputPanel getInputCodePanel() {
        return inputCodePanel;
    }
    public SexRadioPanel getSexRadioPanel() {
        return sexRadioPanel;
    }
    public Buttons getRegisterButton() {
        return registerButton;
    }
}
