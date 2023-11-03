package com.ning.common_component;

import com.ning.DiaLogParent;
import com.ning.dialog.MessageDialog;
import com.ning.dialog.register.downPanel_component.InputPanel;
import com.ning.entity.ResponseResult;
import com.ning.utils.http.DoGetThread;
import com.ning.utils.http.HttpUtil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.locks.ReentrantLock;

public class CodeButton extends Buttons {
    private static DiaLogParent diaLogParent;
    private CodeButtonThread codeButtonThread;
    private static InputPanel inputPanel;
    public void setDiaLogParent(DiaLogParent diaLogParent) {
        this.diaLogParent = diaLogParent;
    }

    public  void setInputPanel(InputPanel inputPanel) {
        this.inputPanel = inputPanel;
    }

    public CodeButton(){
        codeButtonInit();
    }
    private ReentrantLock lock;
    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }

    /**
     * 初始化验证码按钮，背景色为深天蓝色。
     * 字体大小为24，加粗，字体色为白色
     * */
    public void codeButtonInit(){
        this.setText("验证码");
        this.setColor(new Color(0,191,255));
        this.setFont(new Font(null,Font.BOLD,24));
        this.setForeground(Color.white);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(codeButtonListener());
    }
    /**
     * 验证码按钮鼠标监听事件
     * */
    public MouseListener codeButtonListener(){
        CodeButton codeButton=this;
        MouseListener codeButtonListener=new MouseAdapter() {
            //鼠标进入该按钮区域时,按钮背景色变为黑色
            @Override
            public void mouseEntered(MouseEvent e) {
                codeButton.setColor(Color.black);
            }
            //鼠标退出该按钮区域时，按钮背景色变为深天蓝色
            @Override
            public void mouseExited(MouseEvent e) {
                codeButton.setColor(new Color(0,191,255));
            }
            //按下按钮后，后台开始发送验证码
            @Override
            public void mouseClicked(MouseEvent e) {
                codeButtonThread = new CodeButtonThread(codeButton,lock);
                codeButtonThread.start();
            }
        };
        return codeButtonListener;
    }
    /**
     * 验证码发送线程
     * */
    static class CodeButtonThread extends Thread{
        private  ReentrantLock lock;
        private CodeButton codeButton;
        public CodeButtonThread(CodeButton codeButton,ReentrantLock lock){
            this.codeButton=codeButton;
            this.lock=lock;
        }
        @Override
        public void run() {
            try {
                if(lock.tryLock()) {
                    String account = inputPanel.getInputField().getText();
                    if(account.equals("") || account.equals("输入账号")){
                        MessageDialog.showErrorMessage(diaLogParent,"账号不得为空");
                        return;
                    }
                    HttpUtil httpUtil=new DoGetThread("user/sendCode/"+account) {
                        @Override
                        public void success(ResponseResult responseResult) {
                            if(responseResult.code==200){
                                MessageDialog.showSuccessMessage(diaLogParent,"验证码发送成功");
                            }else {
                                MessageDialog.showErrorMessage(diaLogParent,"验证码发送失败");
                            }
                        }
                        @Override
                        public void error(Exception e) {
                            MessageDialog.showErrorMessage(diaLogParent,"网络错误");
                        }
                    };
                    httpUtil.start();
                    int num = 10;
                    while (num >= 0) {
                        codeButton.setText(num + "");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        if (!diaLogParent.isShowing()) {
                            codeButton.setText("验证码");
                            break;
                        }
                        num--;
                    }
                    codeButton.setText("验证码");
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }
}
