package com.ning.common_component;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JButton {
    private Color color;
    public Buttons(){
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = getGraphics2D(g);
        graphics2D.setColor(getColor());
        graphics2D.fillRoundRect(0,0,getSize().width,getSize().height,20,20);        //绘制一个圆角矩形getSize()为获取组件的大小
        super.paintComponent(graphics2D);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D graphics2D = getGraphics2D(g);
        graphics2D.drawRoundRect(0,0,getSize().width,getSize().height,20,20);
    }
    public void setColor(Color color){
        this.color=color;
    }
    private Color getColor() {
        return color;
    }
    /**
     *
     * */
    private Graphics2D getGraphics2D(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setStroke(new BasicStroke(0.01f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return graphics2D;
    }
}
