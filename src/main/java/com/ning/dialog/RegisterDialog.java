package com.ning.dialog;

import com.ning.DiaLogParent;
import com.ning.common_component.CodeButton;
import com.ning.dialog.listener.RegisterListener;
import com.ning.dialog.register.RegisterDownPanel;
import com.ning.dialog.register.RegisterTopPanel;
import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用户注册主窗体
 * */
public class RegisterDialog extends DiaLogParent {
    private final static RegisterDialog INSTANCE=new RegisterDialog();

    public static RegisterDialog getRegisterDialog() {
        return INSTANCE;
    }

    private final static ReentrantLock lock=new ReentrantLock();
    /**
     * 窗体宽度
     * */
    private final static int WIDTH=450;
    /**
     * 窗体高度
     * */
    private final static int HEIGHT=680;
    /**
     * 顶部面板
     * */
    private RegisterTopPanel topPanel=new RegisterTopPanel();
    /**
     * 底部面板
     * */
    private RegisterDownPanel registerDownPanel=new RegisterDownPanel();
    private RegisterListener registerListener=new RegisterListener();
    /**
     * 获取验证码按钮
     * */
    private CodeButton codeButton=new CodeButton();
    /**
     * 初始化注册窗体
     * */
    public void registerDialogInit(){
        ImageIcon windowIcon= LocalImageUtil.getLocalSquareImage("image/熊猫.png",40);
        this.setIconImage(windowIcon.getImage().getScaledInstance(40,40,Image.SCALE_REPLICATE));
        this.setTitle("用户注册");
        this.setAlwaysOnTop(true);
        this.setBackground(Color.white);
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        this.setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH,HEIGHT);
        this.setBackground(Color.black);
        this.add(topPanel);
        codeButtonInit();
        registerDownPanel.add(codeButton);
        this.add(registerDownPanel);
        registerListener.setRegisterDialog(this);
        this.setVisible(true);
    }
    /**
     * 初始化获取验证码按钮,宽为98，高为40，距离左距离为252，上侧为440
     * */
    public void codeButtonInit(){
        codeButton.setBounds(252,440,98,40);
        codeButton.setDiaLogParent(this);
        codeButton.setInputPanel(registerDownPanel.getInputAccountPanel());
        codeButton.setLock(lock);
    }
    public RegisterDownPanel getRegisterDownPanel() {
        return registerDownPanel;
    }
}
