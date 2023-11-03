package com.ning.ui.login_component.listener;


import com.alibaba.fastjson.JSON;
import com.ning.common_component.Buttons;
import com.ning.dialog.MessageDialog;
import com.ning.dialog.RegisterDialog;
import com.ning.dialog.UpdatePwdDialog;
import com.ning.entity.ResponseResult;
import com.ning.entity.query.UserQuery;
import com.ning.ui.LoginUI;
import com.ning.ui.MainUI;
import com.ning.utils.UserUtil;
import com.ning.utils.http.DoPostThread;
import com.ning.utils.http.HttpUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 登录窗口事件监听类
 * */
public class LoginListener{
    RegisterDialog registerDialog=new RegisterDialog();
    UpdatePwdDialog updatePwdDialog=new UpdatePwdDialog();
    private UserQuery userQuery=new UserQuery();
    protected static LoginUI loginUI;
    public  void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }
    /**
     * 登录按钮事件监听方法
     * */
    public MouseListener loginButtonListener(){
        Buttons loginButton = loginUI.getLoginButton();
        JTextField accountField = loginUI.getInputPanel().getInputField();
        JPasswordField passwordField = loginUI.getPasswordPanel().getPasswordField();
        MouseListener loginButtonListener=new MouseAdapter() {
            //鼠标进入按钮区域时，背景色变为黑色
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setColor(Color.black);
            }
            //鼠标退出按钮区域时，背景色恢复为深天蓝色
            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setColor(new Color(0,191,255));
            }
            //按下鼠标左键，开始与后台进行网络登录链接。
            @Override
            public void mouseClicked(MouseEvent e) {
                MainUI mainUI=MainUI.getMainUI();
                String account = accountField.getText();
                String password = passwordField.getText();
                if(account.equals("")||account.equals("输入账号")){
                    MessageDialog.showErrorMessage(loginUI,"账号不得为空");
                    return;
                }
                if(password.equals("")||password.equals("输入密码")){
                    MessageDialog.showErrorMessage(loginUI,"密码不得为空");
                    return;
                }
                userQuery.setAccount(account);
                userQuery.setPassword(password);
                HttpUtil httpUtil=new DoPostThread("user/login",userQuery) {
                    @Override
                    public void success(ResponseResult responseResult) {
                        if(responseResult.code==200){
                            JSON userJson = (JSON) responseResult.data;
                            UserQuery userQuery = JSON.toJavaObject(userJson,UserQuery.class);
                            UserUtil.insertUser(userQuery);
                            mainUI.mainUIInit();
                            loginUI.dispose();
                        }else if(responseResult.code==201){
                            MessageDialog.showErrorMessage(loginUI,responseResult.getMessage());
                        }else{
                            MessageDialog.showErrorMessage(loginUI,"服务器错误");
                        }
                    }
                    @Override
                    public void error(Exception e) {
                        e.printStackTrace();
                        MessageDialog.showErrorMessage(loginUI,e.getCause().toString());
                    }
                };
                httpUtil.start();
            }
        };
        return loginButtonListener;
    }
    /**
     * 用户注册按钮标签事件监听方法
     * */
    public MouseListener registerDialogListener(){
        JLabel registerLabel = loginUI.getRegisterLabel();
        MouseListener registerDialogListener=new MouseAdapter() {
            //鼠标进入标签区域时，字体变为深天蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                registerLabel.setForeground(new Color(0,191,255));
            }
            //鼠标退出标签区域时，字体变为黑色
            @Override
            public void mouseExited(MouseEvent e) {
                registerLabel.setForeground(Color.black);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                registerDialog.registerDialogInit();
            }
        };
        return registerDialogListener;
    }
    /**
     * 用户注册按钮标签事件监听方法
     * */
    public MouseListener updatePwdDialogListener(){
        JLabel forgetPwdLabel = loginUI.getForgetPwdLabel();
        MouseListener updatePwdDialogListener=new MouseAdapter() {
            //鼠标进入标签区域时，字体变为深天蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                forgetPwdLabel.setForeground(new Color(0,191,255));
            }
            //鼠标退出标签区域时，字体变为黑色
            @Override
            public void mouseExited(MouseEvent e) {
                forgetPwdLabel.setForeground(Color.black);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                updatePwdDialog.updateDialogInit();
            }
        };
        return updatePwdDialogListener;
    }
    /**
     * 窗口显示事件监听方法
     * */
    public MouseListener onTopListener(){
        MouseListener onTopListener=new MouseAdapter() {
            //鼠标进入窗口区域时，该窗口直接显示在所有窗口最前端
            @Override
            public void mouseEntered(MouseEvent e) {
                loginUI.setAlwaysOnTop(true);
            }
            //鼠标退出窗口区域时，该窗口不直接显示在所有窗口最前端
            @Override
            public void mouseExited(MouseEvent e) {
                loginUI.setAlwaysOnTop(false);
            }
        };
        return onTopListener;
    }
}
