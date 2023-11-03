package com.ning.common_component;


import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JPanel {
    public LoadingPanel(){
        this.setBounds(0,0,950,620);
    }
    @Override
    public void paint(Graphics g) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            ImageIcon imageIcon=new ImageIcon(path+"image/icon/加载中.gif");
            g.drawImage(imageIcon.getImage(),0,0,950,620,this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
