package com.ning.dialog.register.listener;

import com.ning.common_component.Buttons;
import com.ning.dialog.MessageDialog;
import com.ning.dialog.listener.RegisterListener;
import com.ning.dialog.register.RegisterDownPanel;
import com.ning.entity.ResponseResult;
import com.ning.entity.query.UserQuery;
import com.ning.utils.OnlineImageUtil;
import com.ning.utils.http.DoPostAndFileThread;
import com.ning.utils.http.DoPostThread;
import com.ning.utils.http.HttpUtil;
import org.apache.tika.Tika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * 注册窗口鼠标事件监听
 * */
public class RegisterDownListener extends RegisterListener {
    /**
     * 注册窗体的底部面板
     * */
    private RegisterDownPanel registerDownPanel;
    private UserQuery userQuery=new UserQuery();
    public void setRightDownPanel(RegisterDownPanel registerDownPanel) {
        this.registerDownPanel = registerDownPanel;
    }
    /**
     * 上传头像按钮标签监听事件
     * */
    public MouseListener updateAvatarListener(){
        JLabel updateAvatarLabel = registerDownPanel.getUpdateAvatarLabel();
        JLabel userAvatarLabel = registerDownPanel.getUserAvatarLabel();
        MouseListener updateAvatarListener=new MouseAdapter() {
            //鼠标进入该标签区域时，字体颜色变为深蓝色
            @Override
            public void mouseEntered(MouseEvent e) {
                updateAvatarLabel.setForeground(new Color(0,191,255));
            }
            //鼠标退出该标签区域时，字体颜色变为黑色
            @Override
            public void mouseExited(MouseEvent e) {
                updateAvatarLabel.setForeground(Color.black);
            }
            @Override
            //按下鼠标后，打开文件选择器，替换头像
            public void mouseClicked(MouseEvent e) {
                FileDialog fileDialog=new FileDialog(registerDialog,"请选择图片",FileDialog.LOAD);
                fileDialog.setIconImage(null);
                fileDialog.show();
                File[] files = fileDialog.getFiles();
                File imageFile=files[0];
                Tika tika = new Tika();
                //检测文件格式是否为png,jpg,jpeg格式，且大小不得超过1MB。
                try {
                    String fileType = tika.detect(imageFile);
                    if (!(fileType.equals("image/jpeg") || fileType.equals("image/png"))) {
                        MessageDialog.showErrorMessage(registerDialog, "头像仅限于jpeg,jpg,png格式");
                        return;
                    }
                    if (imageFile.length() > 1024 * 1024) {
                        MessageDialog.showErrorMessage(registerDialog, "头像文件大小不得超过1MB");
                        return;
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                HttpUtil httpUtil=new DoPostAndFileThread("user/uploadAvatar",imageFile) {
                    @Override
                    public void success(ResponseResult responseResult) {
                        if(responseResult.code==200){
                            String imageUrl=(String) responseResult.data;
                            userQuery.setAvatarPath(imageUrl);
                            ImageIcon imageIcon= OnlineImageUtil.getOvalImage(imageUrl,160);
                            userAvatarLabel.setIcon(imageIcon);
                        }else {
                            MessageDialog.showErrorMessage(registerDialog,"服务器错误");
                        }
                    }
                    @Override
                    public void error(Exception e) {
                        String message = e.getCause().getMessage();
                        if(message.contains("refused")){
                            MessageDialog.showErrorMessage(registerDialog,"网络错误");
                        }else {
                            MessageDialog.showErrorMessage(registerDialog,"未知错误");
                        }
                    }
                };
                httpUtil.start();
            }
        };
        return updateAvatarListener;
    }
    /**
     * 注册按钮鼠标监听事件
     * */
    public MouseListener registerButtonListener(){
        Buttons registerButton = registerDownPanel.getRegisterButton();
        JTextField inputAccountField = registerDownPanel.getInputAccountPanel().getInputField();
        JTextField inputUserNameField = registerDownPanel.getInputUserNamePanel().getInputField();
        JTextField inputCodeField = registerDownPanel.getInputCodePanel().getInputField();
        JPasswordField passwordField = registerDownPanel.getPasswordPanel().getPasswordField();
        JRadioButton manRadio = registerDownPanel.getSexRadioPanel().getManRadio();
        MouseListener registerButtonListener=new MouseAdapter() {
            //鼠标进入该按钮区域时,按钮背景色变为黑色
            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setColor(Color.black);
            }
            //鼠标退出该按钮区域时，按钮背景色变为深天蓝色
            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setColor(new Color(0,191,255));
            }
            //鼠标按下时，开始校验注册数据，并且与后端建立链接传输数据。
            @Override
            public void mouseClicked(MouseEvent e) {
                String account=inputAccountField.getText();
                String username = inputUserNameField.getText();
                String code = inputCodeField.getText();
                String password =passwordField.getText();
                String avatarPath = userQuery.getAvatarPath();
                if(username.equals("输入用户名")||username.length()>7){
                    MessageDialog.showErrorMessage(registerDialog, "用户名不得为空，且在七个字内");
                    return;
                }
                if(account.equals("输入账号")){
                    MessageDialog.showErrorMessage(registerDialog, "账号不得为空");
                    return;
                }
                if(password.equals("输入密码") || password.length()<6 || password.length()>=10 || password.contains(" ")){
                    MessageDialog.showErrorMessage(registerDialog, "密码不得为空，禁止空格，长度在6-10个以内");
                    return;
                }
                if(code.equals("验证码")){
                    MessageDialog.showErrorMessage(registerDialog, "验证码不得为空");
                    return;
                }
                if(!(avatarPath!=null&&avatarPath.length()>0)){
                    avatarPath="image/icon/默认头像.jpg";
                }
                if(manRadio.isSelected()){
                    userQuery.setGender("男");
                }else{
                    userQuery.setGender("女");
                }
                userQuery.setAccount(account);
                userQuery.setAvatarPath(avatarPath);
                userQuery.setPassword(password);
                userQuery.setCode(code);
                userQuery.setUsername(username);
                HttpUtil httpUtil=new DoPostThread("user/register",userQuery) {
                    @Override
                    public void success(ResponseResult responseResult) {
                        if(responseResult.code==200){
                            MessageDialog.showSuccessMessage(registerDialog,"注册成功!");
                        }else if(responseResult.code==201){
                            String message = responseResult.getMessage();
                            MessageDialog.showErrorMessage(registerDialog,message);
                        }else {
                            MessageDialog.showErrorMessage(registerDialog,"未知错误!");
                        }
                    }
                    @Override
                    public void error(Exception e) {
                        String message = e.getCause().getMessage();
                        if(message.contains("refused")){
                            MessageDialog.showErrorMessage(registerDialog,"网络错误");
                        }else {
                            MessageDialog.showErrorMessage(registerDialog,"未知错误");
                        }
                    }
                };
                httpUtil.start();
            }
        };
        return registerButtonListener;
    }
}