package com.ning.dialog.register;

import com.ning.dialog.register.listener.RegisterTopListener;
import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;

public class RegisterTopPanel extends JPanel {
    private final static int WIDTH=450;
    private final static int HEIGHT=40;
    private JPanel closeIconPanel=new JPanel();
    private JLabel closeIcon=new JLabel();
    private JLabel windowNameLabel=new JLabel();
    private RegisterTopListener topPanelListener=new RegisterTopListener();
    public RegisterTopPanel(){
        init();
    }
    private void init(){
        topPanelListener.setRegisterTopPanel(this);
        this.setBackground(new Color(30,144,255));
        this.setLayout(null);
        this.setBounds(0,0,WIDTH,HEIGHT);
        closeIconInit();
        this.add(closeIconPanel);
        windowNameLabelInit();
        this.add(windowNameLabel);
    }
    private void closeIconInit() {
        closeIconPanel.setLayout(null);
        closeIconPanel.setBounds(WIDTH-40,0,40,40);
        closeIconPanel.setBackground(new Color(30,144,255));
        closeIcon.setBounds(8, 8, 24, 24);
        closeIcon.addMouseListener(topPanelListener.closeDialogListener());
        ImageIcon imageIcon = LocalImageUtil.
                getLocalSquareImage("image/icon/关闭白色.png", 24);
        closeIcon.setIcon(imageIcon);
        closeIconPanel.add(closeIcon);
        closeIconPanel.addMouseListener(topPanelListener.closeDialogListener());
    }
    private void windowNameLabelInit(){
        windowNameLabel.setBounds(0,0,150,40);
        windowNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        windowNameLabel.setFont(new Font(null,Font.BOLD,25));
        windowNameLabel.setForeground(Color.white);
        windowNameLabel.setText("用户注册");
    }
    public JPanel getCloseIconPanel() {
        return closeIconPanel;
    }
}
