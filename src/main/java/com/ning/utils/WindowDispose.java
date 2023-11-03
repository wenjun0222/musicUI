package com.ning.utils;

import com.ning.dialog.LrcShowDialog;
import com.ning.dialog.RegisterDialog;
import com.ning.dialog.UpdatePwdDialog;
import com.ning.thread.ShowThread;
import com.ning.ui.LoginUI;
import com.ning.ui.MainUI;

import javax.swing.*;

public class WindowDispose {
    private static MainUI mainUI=MainUI.getMainUI();
    private static LoginUI loginUI=LoginUI.getLoginUI();
    private static RegisterDialog registerDialog=RegisterDialog.getRegisterDialog();
    private static UpdatePwdDialog updatePwdDialog=UpdatePwdDialog.getUpdatePwdDialog();
    private static LrcShowDialog lrcShowDialog=LrcShowDialog.getLrcShowDialog();
    private static ShowThread showThread=ShowThread.getShowThread();
    public static void loginUIDispose(){
        loginUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginUI.dispose();
        registerDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updatePwdDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public static void mainUIDispose(){
        mainUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lrcShowDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showThread.clearMap();
    }
}
