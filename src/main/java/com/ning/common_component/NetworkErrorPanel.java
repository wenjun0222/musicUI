package com.ning.common_component;

import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 网络连接错误提示面板
 **/
public class NetworkErrorPanel extends JPanel {
    private JLabel errorLabel=new JLabel();
    private JLabel promptLabel=new JLabel();
    public NetworkErrorPanel(){
        init();
    }
    public void init(){
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(0,0,950,620);
        errorLabelInit();
        this.add(errorLabel);
        promptLabelInit();
        this.add(promptLabel);
    }
    private void errorLabelInit() {
        ImageIcon imageIcon= LocalImageUtil.getRectangleImage("image/icon/警告.png",
                300,300);
        errorLabel.setIcon(imageIcon);
        errorLabel.setBounds(300,50,300,300);
    }
    private void promptLabelInit() {
        promptLabel.setBounds(200,370,500,50);
        promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        promptLabel.setFont(new Font(null,Font.BOLD,25));
        promptLabel.setText("网络连接错误，请检查网络连接设置");
    }
}
