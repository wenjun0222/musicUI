package com.ning.common_component;

import com.ning.entity.query.Music;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 歌曲下载面板
 * */
public class DownloadPanel extends JPanel {
    private final static Object lock=new Object();
    private static volatile boolean stopDownload=false;
    private static volatile int currentProgress=0;
    private static JProgressBar progressBar=new JProgressBar();
    private int y;
    private static Music music;
    private static volatile int musicSize;
    private static JLabel musicSizeLabel=new JLabel();
    public DownloadPanel(int y,Music music){
        this.y=y;
        this.music=music;
        init();
    }
    private void init(){
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(50,y,900,50);
        //歌曲名称标签，距离歌曲信息展示面板左侧为25
        JLabel songNameLabel=getSongMsgLabel(25,music.getSongName());
        this.add(songNameLabel);
        //歌手名称标签，距离歌曲信息展示面板左侧为225
        JLabel singerNameLabel=getSongMsgLabel(225,music.getSingerName());
        this.add(singerNameLabel);
        progressBarInit();
        this.add(progressBar);
        musicSizeLabelInit();
        this.add(musicSizeLabel);
    }
    /**
     * 歌曲名称，歌手名称，歌曲大小标签都由这个方法获得
     * 标签距离歌曲信息展示面板上侧距离为15，宽为150，高为20，
     * 字体大小为20，加粗
     * */
    private JLabel getSongMsgLabel(int x,String labelName){
        JLabel songMsgLabel=new JLabel();
        songMsgLabel.setFont(new Font(null,Font.BOLD,20));
        songMsgLabel.setBounds(x,15,150,20);
        songMsgLabel.setText(labelName);
        return songMsgLabel;
    }
    private void progressBarInit(){
        progressBar.setBounds(350,5,250,40);
        progressBar.setBorderPainted(false);
        progressBar.setForeground(new Color(30,144,255));
        progressBar.setBackground(new Color(211,211,211));
        progressBar.setOrientation(SwingConstants.HORIZONTAL);
    }
    private  void musicSizeLabelInit(){
        musicSizeLabel.setFont(new Font(null,Font.BOLD,20));
        musicSizeLabel.setBounds(675,15,150,20);
    }
    static class DownLoadThread extends Thread{
        private RandomAccessFile randomAccessFile;
        private URLConnection urlConnection;
        private InputStream inputStream;
        public DownLoadThread(RandomAccessFile randomAccessFile,
                              URLConnection urlConnection){
            this.randomAccessFile=randomAccessFile;
            this.urlConnection=urlConnection;
        }
        @Override
        public void run() {
            try {
                byte[] bytes=new  byte[1024];
                inputStream = urlConnection.getInputStream();
                int len=0;
                while ((len=inputStream.read(bytes))!=-1){
                    randomAccessFile.write(bytes,0,len);
                    synchronized (lock){
                        currentProgress+=len;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    static class DownLoadPoolThread extends Thread{
        private int threadPoolNum=3;

        @Override
        public void run() {
            ExecutorService executorService= Executors.newCachedThreadPool();
            try {
                int musicSize = new URL(music.getSongUrl()).openConnection().getContentLength();
                musicSizeLabel.setText(musicSize + "");
                progressBar.setMinimum(0);
                progressBar.setMaximum(musicSize);
                int downloadNum = musicSize / threadPoolNum;
                for (int x = 0; x < threadPoolNum; x++) {
                    int startLength = downloadNum * x;
                    int endLength = downloadNum * (x + 1);
                    URLConnection urlConnection = new URL(music.getSongUrl()).openConnection();
                    urlConnection.setRequestProperty("Range", "bytes=" + String.valueOf(startLength) + "-" + String.valueOf(endLength));
                    urlConnection.connect();
                    RandomAccessFile randomAccessFile=new RandomAccessFile("","rw");
                    randomAccessFile.seek(startLength);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true){
                                try {
                                    progressBar.setValue(currentProgress);
                                    if (currentProgress >= musicSize) {
                                        break;
                                    }
                                    Thread.sleep(100);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
