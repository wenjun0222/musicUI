package com.ning.ui.main.listener;

import com.ning.thread.ShowThread;
import com.ning.ui.main.RightTopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 主窗体右上面板事件监听类
 * */
public class MainRightTopListener extends MainUIListener{
    private RightTopPanel rightTopPanel;
    public void setRightTopPanel(RightTopPanel rightTopPanel) {
        this.rightTopPanel=rightTopPanel;
    }
    /**
     * 窗口关闭事件监听方法
     * */
    public MouseListener closeListener(){
        JPanel closePanel = rightTopPanel.getClosePanel();
        JLabel closeIcon = rightTopPanel.getCloseIcon();
        MouseListener closeListener=new MouseAdapter() {
            //按下鼠标，程序整体退出
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            //鼠标进入该图标区域时，图标背景色变为红色
            @Override
            public void mouseEntered(MouseEvent e) {
                closePanel.setBackground(Color.red);
                closeIcon.setBackground(Color.red);
            }
            //鼠标进入该图标区域时，图标背景色恢复为原色
            @Override
            public void mouseExited(MouseEvent e) {
                closePanel.setBackground(new Color(173,216,230));
                closeIcon.setBackground(new Color(173,216,230));
            }
        };
        return closeListener;
    }
    /**
     * 缩小图标鼠标监听方法
     * */
    public MouseListener reduceListener(){
        JPanel reducePanel = rightTopPanel.getReducePanel();
        JLabel reduceIcon = rightTopPanel.getReduceIcon();
        MouseListener reduceListener=new MouseAdapter() {
            //按下鼠标，整体窗口缩小到系统任务栏
            @Override
            public void mouseClicked(MouseEvent e) {
                mainUI.setExtendedState(Frame.ICONIFIED);
            }
            //鼠标进入该图标区域时，图标背景色变为红色
            @Override
            public void mouseEntered(MouseEvent e) {
                reducePanel.setBackground(Color.red);
                reduceIcon.setBackground(Color.red);
            }
            //鼠标进入该图标区域时，图标背景色恢复为原色
            @Override
            public void mouseExited(MouseEvent e) {
                reducePanel.setBackground(new Color(173,216,230));
                reduceIcon.setBackground(new Color(173,216,230));
            }
        };
        return reduceListener;
    }
    /**
     * 设置图标鼠标监听方法
     * */
    public MouseListener settingListener(){
        JPanel settingPanel = rightTopPanel.getSettingPanel();
        JLabel settingIcon = rightTopPanel.getSettingIcon();
        MouseListener  settingListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            //鼠标进入该图标区域时，图标背景色变为红色
            @Override
            public void mouseEntered(MouseEvent e) {
                settingPanel.setBackground(Color.red);
                settingIcon.setBackground(Color.red);
            }
            //鼠标进入该图标区域时，图标背景色恢复为原色
            @Override
            public void mouseExited(MouseEvent e) {
                settingPanel.setBackground(new Color(173,216,230));
                settingIcon.setBackground(new Color(173,216,230));
            }
        };
        return  settingListener;
    }
    /**
     * 搜索图标鼠标事件监听方法
     * */
    public MouseListener searchListener(){
        JTextField searchField = rightTopPanel.getSearchField();
        MouseListener searchListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchName = searchField.getText();
                ShowThread showThread=ShowThread.getShowThread();
                showThread.setCurrentPanel("searchMusicPanel");
                ShowThread.SearchMusicByNameThread searchMusicByNameThread =
                        showThread.getSearchMusicByNameThread();
                searchMusicByNameThread.setSearchName(searchName);
                searchMusicByNameThread.start();
            }
        };
        return searchListener;
    }
    /**
     * 搜索输入框鼠标监听方法
     * */
    public MouseListener searchFieldListener(String prompt){
        JTextField searchField = rightTopPanel.getSearchField();
        MouseListener searchFieldListener=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                String text = searchField.getText();
                if(text.equals(prompt)){
                    searchField.setText("");
                    searchField.setForeground(Color.black);
                }
                searchField.setFocusable(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                String text = searchField.getText();
                if(text.equals("")){
                    searchField.setText(prompt);
                    searchField.setForeground(Color.gray);
                }
                searchField.setFocusable(false);
            }
        };
        return searchFieldListener;
    }
}
