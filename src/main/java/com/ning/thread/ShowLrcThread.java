package com.ning.thread;

import com.ning.entity.Lrc;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 歌词窗体展示线程
 * */
public class ShowLrcThread extends Thread{
    private JLabel lrcLabel;
    private List<Lrc> lrcList;
    private JProgressBar songProgressBar;
    public void setLrc(JLabel lrcLabel,List<Lrc> lrcList,JProgressBar songProgressBar){
        this.lrcLabel=lrcLabel;
        this.lrcList=lrcList;
        this.songProgressBar=songProgressBar;
    }
    @Override
    public void run() {
        int value=0;
        for (int i = 0; i < lrcList.size(); i++) {
            Lrc currentLrc = lrcList.get(i);
            Lrc nextLrc = lrcList.get(i + 1);
            lrcLabel.setText(currentLrc.getContent());
            while(value>=currentLrc.getPrefixTime()&& value< nextLrc.getPrefixTime()){
                value=songProgressBar.getValue();
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
