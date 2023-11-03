package com.ning.dialog.register.downPanel_component;

import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 明文输入框面板，直接显示输入框里面的内容
 * */
public class InputPanel extends JPanel {
    /**
     * 输入框
     **/
    private JTextField inputField=new JTextField();
    /**
     * 输入框面板图标,大小为24
     **/
    private JLabel inputIconLabel=new JLabel();
    /**
     * 图标名称
     * */
    private String photoPath;
    /**
     * 输入框内部的文字提示
     * */
    private String inputPrompt;
    public InputPanel(String photoPath, String inputPrompt, int x, int y, int width, int height){
        this.setBounds(x,y,width,height);
        this.photoPath=photoPath;
        this.inputPrompt=inputPrompt;
        init();
    }
    /**
     * 初始化面板，边框颜色为深天蓝色，背景色为白烟色
     * */
    private void init(){
        this.setBackground(Color.white);
        this.setLayout(null);
        inputIconLabelInit();
        this.add(inputIconLabel);
        inputFieldInit();
        this.add(inputField);
        this.setBorder(BorderFactory.createLineBorder(new Color(0,191,255)));
    }
    /**
     * 初始化输入框，高度为30，文字大小为19，文字颜色为灰色，距离面板左侧距离为35，无边框效果，背景色为白色垂直居中
     * */
    private void inputFieldInit(){
        inputField.setBounds(35,(getHeight()-30)/2,getWidth()-32-5,30);
        inputField.setText(inputPrompt);
        inputField.setFont(new Font(null,Font.PLAIN,18));
        inputField.setForeground(Color.gray);
        inputField.setBorder(null);
        inputField.setBackground(Color.white);
        inputField.addMouseListener(listener());
        inputField.setFocusable(false);
    }

    /**
     * 初始化输入框面板图标，宽与高都为24，距离面板左侧距离为24，垂直居中
     * */
    private void inputIconLabelInit(){
        ImageIcon imageIcon= LocalImageUtil.getLocalSquareImage(photoPath,24);
        inputIconLabel.setIcon(imageIcon);
        inputIconLabel.setBounds(5,(getHeight()-24)/2,24,24);
    }
    /**
     * 外部可获取输入框进行事件监听动作
     */
    public JTextField getInputField() {
        return inputField;
    }
    public MouseListener listener(){
        MouseListener listener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                String text = inputField.getText();
                if(text.equals(inputPrompt)){
                    inputField.setText("");
                    inputField.setForeground(Color.black);
                }
                inputField.setFocusable(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                String text = inputField.getText();
                if(text.equals("")){
                    inputField.setText(inputPrompt);
                    inputField.setForeground(Color.gray);
                }
                inputField.setFocusable(false);
            }
        };
        return listener;
    }
    public void clearCache(){
        inputField.setText(inputPrompt);
        inputField.setForeground(Color.gray);
    }
}
