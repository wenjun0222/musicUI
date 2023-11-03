package com.ning.dialog.listener;

import com.ning.common_component.Buttons;
import com.ning.dialog.MessageDialog;
import com.ning.dialog.UpdatePwdDialog;
import com.ning.entity.ResponseResult;
import com.ning.entity.query.UserQuery;
import com.ning.utils.http.DoPostThread;
import com.ning.utils.http.HttpUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 修改密码窗体事件监听类
 * */
public class UpdatePwdListener {
    private UserQuery userQuery=new UserQuery();
    private UpdatePwdDialog updatePwdDialog;
    public void setUpdatePwdDialog(UpdatePwdDialog updatePwdDialog) {
        this.updatePwdDialog = updatePwdDialog;
    }
    /**
     * 关闭图标事件监听方法
     * */
    public MouseListener closeIconListener(){
        JPanel closePanel = updatePwdDialog.getClosePanel();
        MouseListener closeIconListener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closePanel.setBackground(Color.red);
            }
            //鼠标进入该图标区域时，背景色恢复为原色
            @Override
            public void mouseExited(MouseEvent e) {
                closePanel.setBackground(new Color(30,144,255));
            }
            //按下鼠标，背景色恢复为原色，窗口恢复原样，并且关闭窗体
            @Override
            public void mouseClicked(MouseEvent e) {
                closePanel.setBackground(new Color(30,144,255));
                updatePwdDialog.clearCache();
            }
        };
        return closeIconListener;
    }
    /**
     * 确定按钮鼠标事件监听
     * */
    public MouseListener sureListener(){
        Buttons sureButton = updatePwdDialog.getSureButton();
        JTextField accountField = updatePwdDialog.getInputPanel().getInputField();
        JTextField codeField = updatePwdDialog.getCodePanel().getInputField();
        JPasswordField passwordField = updatePwdDialog.getPasswordPanel().getPasswordField();
        MouseListener sureButtonListener=new MouseAdapter() {
            //鼠标进入该按钮区域时，按钮变黑色
            @Override
            public void mouseEntered(MouseEvent e) {
                sureButton.setColor(Color.black);
            }
            //鼠标进入该按钮区域时，按钮变深天蓝色
            @Override
            public void mouseExited(MouseEvent e) {
                sureButton.setColor(new Color(30,144,255));
            }
            //按下按钮，开始向后台发送修改密码请求
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=0;
                String account= accountField.getText();
                String password = passwordField.getText();
                String code = codeField.getText();
                System.out.println(x);
                x++;
                if(account.equals("输入账号")){
                    MessageDialog.showErrorMessage(updatePwdDialog, "账号不得为空");
                    return;
                }
                if(password.equals("输入密码") || password.length()<6 || password.length()>=10 || password.contains(" ")){
                    MessageDialog.showErrorMessage(updatePwdDialog, "密码不得为空，禁止空格，长度在6-10个以内");
                    return;
                }
                if(code.equals("验证码")){
                    MessageDialog.showErrorMessage(updatePwdDialog, "验证码不得为空");
                    return;
                }
                userQuery.setAccount(account);
                userQuery.setPassword(password);
                userQuery.setCode(code);
                HttpUtil httpUtil=new DoPostThread("user/updatePwdByAccount",userQuery) {
                    @Override
                    public void success(ResponseResult responseResult) {
                        if(responseResult.code==200){
                            MessageDialog.showSuccessMessage(updatePwdDialog,"修改密码成功");
                        }else if(responseResult.code==201){
                            MessageDialog.showErrorMessage(updatePwdDialog,responseResult.message);
                        }else {
                            MessageDialog.showErrorMessage(updatePwdDialog,"服务器错误");
                        }
                    }
                    @Override
                    public void error(Exception e) {
                        String message = e.getCause().getMessage();
                        if(message.contains("refused")){
                            MessageDialog.showErrorMessage(updatePwdDialog,"网络错误");
                        }else {
                            MessageDialog.showErrorMessage(updatePwdDialog,"未知错误");
                        }
                    }
                };
                httpUtil.start();
            }
        };
        return sureButtonListener;
    }
    /**
     * 窗口显示事件监听方法
     * */
    public MouseListener onTopListener(){
        MouseListener onTopListener=new MouseAdapter() {
            //鼠标进入窗口区域时，该窗口直接显示在所有窗口最前端
            @Override
            public void mouseEntered(MouseEvent e) {
                updatePwdDialog.setAlwaysOnTop(true);
            }
            //鼠标退出窗口区域时，该窗口不直接显示在所有窗口最前端
            @Override
            public void mouseExited(MouseEvent e) {
                updatePwdDialog.setAlwaysOnTop(false);
            }
        };
        return onTopListener;
    }
}
