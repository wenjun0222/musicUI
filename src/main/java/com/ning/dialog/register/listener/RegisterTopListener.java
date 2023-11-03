package com.ning.dialog.register.listener;

import com.ning.dialog.listener.RegisterListener;
import com.ning.dialog.register.RegisterTopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 登录窗口顶部面板事件监听类
 * */
public class RegisterTopListener extends RegisterListener {
    private RegisterTopPanel registerTopPanel;
    /**
     * 关闭图标监听方法
     * */
    public void setRegisterTopPanel(RegisterTopPanel registerTopPanel) {
        this.registerTopPanel = registerTopPanel;
    }
    public MouseListener closeDialogListener(){
        JPanel closeIconPanel = registerTopPanel.getCloseIconPanel();
        MouseListener closeDialogListener=new MouseAdapter() {
            //鼠标进入该图标区域时，背景色变为红色
            @Override
            public void mouseEntered(MouseEvent e) {
                closeIconPanel.setBackground(Color.red);
            }
            //鼠标进入该图标区域时，背景色恢复为原色
            @Override
            public void mouseExited(MouseEvent e) {
                closeIconPanel.setBackground(new Color(30,144,255));
            }
            //按下鼠标，背景色恢复为原色，窗口恢复原样，并且关闭窗体
            @Override
            public void mouseClicked(MouseEvent e) {
                closeIconPanel.setBackground(new Color(30,144,255));
                registerDialog.getRegisterDownPanel().clearCache();
                registerDialog.dispose();
            }
        };
        return closeDialogListener;
    }
}
