package com.ning;

import com.ning.ui.LoginUI;

public class Main {
    private static LoginUI loginUI1=LoginUI.getLoginUI();
    public  static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                loginUI1.loginUIInit();
            }
        });
        t1.start();
        Thread.currentThread().setName("熊猫音乐");
    }
}
