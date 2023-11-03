package com.ning.dialog.register.downPanel_component;

import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 密码输入框面板，直接显示输入框里面的内容
 * */
public class PasswordPanel extends JPanel {
    /**
     * 密码输入框
     **/
    private JPasswordField passwordField=new JPasswordField();
    /**
     * 密码输入框面板图标,大小为24
     **/
    private JLabel passwordIconLabel=new JLabel();
    /**
     * 密码输入框内部的文字提示
     * */
    private String inputPrompt;
    public PasswordPanel(String inputPrompt, int x, int y, int width, int height){
        this.setBounds(x,y,width,height);
        this.inputPrompt=inputPrompt;
        init();
    }
    /**
     * 初始化面板，边框颜色为深天蓝色,背景色为白色
     * */
    private void init(){
        this.setBackground(Color.white);
        this.setLayout(null);
        passwordIconLabelInit();
        this.add(passwordIconLabel);
        passwordFieldInit();
        this.add(passwordField);
        this.setBorder(BorderFactory.createLineBorder(new Color(0,191,255)));
    }
    /**
     * 初始密码化输入框，高度为30，文字大小为19，文字颜色为灰色，距离面板左侧距离为35，无边框效果，背景色为白色，垂直居中
     * */
    private void passwordFieldInit(){
        passwordField.setBounds(35,(getHeight()-30)/2,getWidth()-35-5,30);
        passwordField.setText(inputPrompt);
        passwordField.setFont(new Font(null,Font.PLAIN,18));
        passwordField.setEchoChar((char)0);
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.gray);
        passwordField.setBorder(null);
        passwordField.addMouseListener(listener());
        passwordField.setFocusable(false);
    }
    /**
     * 初始化输入框面板图标，宽与高都为25，距离面板左侧距离为25，垂直居中
     * */
    public void passwordIconLabelInit(){
        ImageIcon imageIcon= LocalImageUtil.getLocalSquareImage("image/icon/密码.png",25);
        passwordIconLabel.setIcon(imageIcon);
        passwordIconLabel.setBounds(5,(getHeight()-25)/2,25,25);

    }
    public void clearCache(){
        passwordField.setText(inputPrompt);
        passwordField.setEchoChar((char)0);
        passwordField.setForeground(Color.gray);
    }
    /**
     * 外部可获取输入框进行事件监听动作
     */
    public JPasswordField getPasswordField() {
        return passwordField;
    }
    private MouseListener listener(){
        MouseListener listener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                String text = passwordField.getText();
                if(text.equals(inputPrompt)){
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                    passwordField.setForeground(Color.black);
                }
                passwordField.setFocusable(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                String text = passwordField.getText();
                if(text.equals("")){
                    passwordField.setText(inputPrompt);
                    passwordField.setEchoChar((char)0);
                    passwordField.setForeground(Color.gray);
                }
                passwordField.setFocusable(false);
            }
        };
        return listener;
    }
}
