package com.ning.ui.login_component.listener;

import com.ning.ui.login_component.LoginTopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 登录窗体顶部面板事件监听类
 * */
public class LoginTopListener extends LoginListener{
    private LoginTopPanel loginTopPanel;

    public void setLoginTopPanel(LoginTopPanel loginTopPanel) {
        this.loginTopPanel = loginTopPanel;
    }
    /**
     * 关闭图标事件监听方法
     * */
    public MouseListener closeListener(){
        JPanel closePanel = loginTopPanel.getClosePanel();
        MouseListener closeListener=new MouseAdapter() {
            //鼠标进入图标区域时，背景色变为红色
            @Override
            public void mouseEntered(MouseEvent e) {
                closePanel.setBackground(Color.red);
            }
            //鼠标退出图标区域时，背景色恢复为深天蓝色
            @Override
            public void mouseExited(MouseEvent e) {
                closePanel.setBackground(new Color(0,191,255));
            }
            //按下鼠标时，窗体退出，程序结束。
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        };
        return closeListener;
    }
    /**
     * 关闭图标事件监听方法
     * */
    public MouseListener reduceListener(){
        JPanel reducePanel = loginTopPanel.getReducePanel();
        MouseListener reduceListener=new MouseAdapter() {
            //鼠标进入图标区域时，背景色变为红色
            @Override
            public void mouseEntered(MouseEvent e) {
                reducePanel.setBackground(Color.red);
            }
            //鼠标退出图标区域时，背景色恢复为深天蓝色
            @Override
            public void mouseExited(MouseEvent e) {
                reducePanel.setBackground(new Color(0,191,255));
            }
            //按下鼠标时，窗体退出，程序结束。
            @Override
            public void mouseClicked(MouseEvent e) {
                loginUI.setExtendedState(Frame.ICONIFIED);
            }
        };
        return reduceListener;
    }

}
