package com.ning.overrideUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class ProgressUI extends BasicProgressBarUI {
    private JProgressBar jProgressBar;
    private Color foreColor;

    ProgressUI(JProgressBar jProgressBar,Color foreColor) {
        this.jProgressBar = jProgressBar;
        this.foreColor=foreColor;
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        this.jProgressBar.setBackground(Color.gray);
        this.jProgressBar.setForeground(foreColor);
        super.paintDeterminate(g, c);
    }

}
