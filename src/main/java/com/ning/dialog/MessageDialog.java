package com.ning.dialog;

import com.ning.DiaLogParent;
import com.ning.common_component.Buttons;
import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 消息提示框
 * */
public class MessageDialog {
    /**
     * 消息提示框宽度
     * */
    private static int WIDTH=350;
    /**
     * 消息提示框高度
     * */
    private static int HEIGHT=160;
    private static ImageIcon imageIcon;
    public final static int ERROR_MESSAGE=1;
    public final static int SUCCESS_MESSAGE=2;
    /**
     * 警告提示框，父组件为JFrame
     * */
    public static void showErrorMessage(JFrame parentComponent,String message){
        DiaLogParent diaLogParent=new DiaLogParent(parentComponent,true);
        messageDialog(diaLogParent,ERROR_MESSAGE,message);
    }
    /**
     * 警告提示框，父组件为JDialog
     * */
    public static void showErrorMessage(JDialog parentComponent,String message){
        DiaLogParent diaLogParent=new DiaLogParent(parentComponent,true);
        messageDialog(diaLogParent,ERROR_MESSAGE,message);
    }
    /**
     * 成功提示框，父组件为JDialog
     * */
    public static void showSuccessMessage(JDialog parentComponent,String message){
        DiaLogParent diaLogParent=new DiaLogParent(parentComponent,true);
        messageDialog(diaLogParent,SUCCESS_MESSAGE,message);
    }
    /**
     * 成功提示框，父组件为JFrame
     * */
    public static void showSuccessMessage(JFrame parentComponent,String message){
        DiaLogParent diaLogParent=new DiaLogParent(parentComponent,true);
        messageDialog(diaLogParent,SUCCESS_MESSAGE,message);
    }
    private static void messageDialog(DiaLogParent diaLogParent,int messageType,String message){
        int promptLabelWidth=message.length()*30;
        diaLogParent.setBackground(new Color(169,169,169));
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        diaLogParent.setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH,HEIGHT);
        JLabel promptLabel=new JLabel();
        promptLabel.setBounds(60,15,90,30);
        promptLabel.setFont(new Font(null,Font.BOLD,30));
        JLabel iconLabel=new JLabel();
        iconLabel.setBounds(25,15,30,30);
        if(messageType==ERROR_MESSAGE){
            imageIcon= LocalImageUtil.getLocalSquareImage("image/icon/警告.png",30);
            promptLabel.setText("警告");
            promptLabel.setForeground(Color.red);
        }else if(messageType==SUCCESS_MESSAGE){
            imageIcon=LocalImageUtil.getLocalSquareImage("image/icon/成功.png",30);
            promptLabel.setText("成功");
            promptLabel.setForeground(Color.green);
        }
        iconLabel.setIcon(imageIcon);
        diaLogParent.add(promptLabel);
        diaLogParent.add(iconLabel);
        JLabel messageLabel=new JLabel();
        messageLabel.setFont(new Font(null,Font.PLAIN,16));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBounds((WIDTH-promptLabelWidth)/2,48,promptLabelWidth,40);
        messageLabel.setText(message);
        diaLogParent.add(messageLabel);
        Buttons button=new Buttons();
        button.setText("确  定");
        button.setFont( new Font(null,Font.BOLD,22));
        button.setColor(new Color(0,191,255));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBounds((WIDTH-100)/2,105,100,30);
        button.setForeground(Color.white);
        button.setBorder(null);
        button.addMouseListener(closeListener(diaLogParent,button));
        diaLogParent.add(button);
        diaLogParent.setVisible(true);
    }
    /**
     * 确定按钮监听事件
     * */
    public static MouseListener closeListener(Dialog dialog,Buttons button){
        MouseListener closeListener=new MouseAdapter() {
            //进入该按钮区域，按钮变黑色
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setColor(Color.black);
            }
            //退出该按钮区域，按钮恢复为原色
            @Override
            public void mouseExited(MouseEvent e) {
                button.setColor(new Color(0,191,255));
            }
            //按下按钮，消息提示框关闭
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
            }
        };
        return closeListener;
    }
}
