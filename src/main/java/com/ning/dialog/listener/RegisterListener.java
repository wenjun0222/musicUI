package com.ning.dialog.listener;

import com.ning.dialog.RegisterDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterListener {
    protected static RegisterDialog registerDialog;
    public void setRegisterDialog(RegisterDialog registerDialog) {
        this.registerDialog=registerDialog;
    }
    /**
     * 窗口显示事件监听方法
     * */
    public MouseListener onTopListener(){
        MouseListener onTopListener=new MouseAdapter() {
            //鼠标进入窗口区域时，该窗口直接显示在所有窗口最前端
            @Override
            public void mouseEntered(MouseEvent e) {
                registerDialog.setAlwaysOnTop(true);
            }
            //鼠标退出窗口区域时，该窗口不直接显示在所有窗口最前端
            @Override
            public void mouseExited(MouseEvent e) {
                registerDialog.setAlwaysOnTop(false);
            }
        };
        return onTopListener;
    }
}
