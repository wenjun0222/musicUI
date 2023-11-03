package com.ning.thread;

import com.ning.dialog.LrcShowDialog;
import com.ning.entity.Lrc;
import com.ning.entity.query.Music;
import com.ning.ui.MainUI;
import com.ning.ui.main.PlayMusicPanel;
import com.ning.utils.LrcUtil;
import com.ning.utils.OnlineImageUtil;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 控制播放音乐线程
 * */
public class PlayMusic extends Thread{
    /**
     * 音乐播放控制面板
     * */
    private PlayMusicPanel playMusicPanel;
    public void setPlayMusicPanel(PlayMusicPanel playMusicPanel) {
        this.playMusicPanel=playMusicPanel;
    }
    /**
     * 主界面
     * */
    private MainUI mainUI;
    /**
     * 歌词展示线程
     * */
    private ShowLrcThread showLrcThread;
    /**
     * 歌词展示框
     * */
    private LrcShowDialog lrcShowDialog=LrcShowDialog.getLrcShowDialog();
    /**
     * 鼠标点击音乐信息面板后要播放的当前音乐信息面板的索引值
     * */
    private int currentProgress;
    /**
     * 通过单例模式方便外部控制音乐播放。
     * */
    private final static PlayMusic INSTANCE=new PlayMusic();
    public static PlayMusic getInstance(){
        return INSTANCE;
    }
    /**
     * 是否已经改变要播放的音乐列表
     * */
    private static volatile boolean changeMusliList=false;
    public void setMainUI(MainUI mainUI) {
        this.mainUI = mainUI;
    }
    /**
     * 双击要播放的当前音乐面板时的音乐索引
     * */
    private static volatile int currentSongIndex;
    /**
     * 是否播放下一首歌曲
     * */
    private static volatile boolean playCurrentSong=false;
    /**
     * 音乐列表
     * */
    private List<Music> musicList;
    /**
     * 是否播放上一首音乐，默认为false
     * */
    private static volatile boolean playPreviousMusic=false;
    /**
     * 是否播停止放音乐，默认为false
     * */
    private static volatile boolean stopPlayMusic=true;
    /**
     * 是否播放下一首音乐，默认为false
     * */
    private static volatile boolean playNextMusic=false;
    /**
     * 是否单曲循环播放音乐音乐，默认为false
     * */
    private static volatile boolean isLoop=false;
    /**
     * 播放上一首歌曲
     * */
    public void playPreviousMusic() {
        playPreviousMusic=true;
    }
    /**
     * 继续播放音乐
     * */
    public void continuePlaySong(){
        stopPlayMusic=false;
    }
    /**
     * 暂时停止博邦歌曲
     * */
    public void stopPlayMusic(){
        stopPlayMusic=true;
    }
    /**
     * 播放下一首歌曲
     * */
    public void playNextMusic(){
        playNextMusic=true;
    }
    /**
     * 歌曲单首循环播放
     **/
    public void singleLoopPlay(){
        isLoop=true;
    }
    public void listLoopPlay(){
        isLoop=false;
    }
    /**
     * 通过外部设置音乐列表
     * */
    public void setMusicList(List<Music> musicList){
        this.musicList=musicList;
    }
    private List<Music> getMusicList(){
        return this.musicList;
    }
    /**
     * 改变要播放的音乐列表
     * */
    public void changeMusicList(){
        changeMusliList=true;
    }

    /**
     * 播放当前音乐面板上显示的音乐
     * */
    public void playCurrentMusic(int  currentSongIndex){
        this.currentSongIndex=currentSongIndex;
        playCurrentSong=true;
    }
    /**
     * 打开歌词展示窗体
     * */
    public void showLrcDialog(){
        lrcShowDialog.setVisible(true);
    }
    /**
     * 关闭歌词展示窗体
     * */
    public void closeLrcDialog(){
        lrcShowDialog.setVisible(false);
    }
    /**
     * 当前播放的音乐，主要用于给用户添加到喜欢音乐列表中或者取消喜欢
     * */
    private volatile  Music currentPlayMusic;
    public Music getCurrentPlayMusic() {
        return currentPlayMusic;
    }
    private final static Lock lock=new ReentrantLock();
    /**
     * 最近播放的音乐
     * */
    public Map<Integer,Music> recentlyPlayMusic=new HashMap<>();
    /**
     * 是否退出整个音乐播放循环
     * */
    public volatile boolean stop=false;
    @Override
    public void run() {
        JLabel lrcLabel = lrcShowDialog.getLrcLabel();
        lrcShowDialog.setVisible(true);
        AudioInputStream audioInputStream=null;
        AudioFormat audioFormat=null;// 文件格式
        SourceDataLine sourceDataLine=null;// 输出设备
        int currentNum=0;
        JPanel playSongPanel = playMusicPanel.getPlaySongPanel();
        JLabel playSongIcon = playMusicPanel.getPlaySongIcon();
        JLabel songNameLabel = playMusicPanel.getSongNameLabel();
        JProgressBar songProgressBar = playMusicPanel.getSongProgressBar();
        JLabel singerImage = playMusicPanel.getSingerImage();
        URLConnection musicConnection=null;
        while (true) {
            changeMusliList=false;
            List<Music> musics = getMusicList();
            lrcLabel.setText("熊猫音乐");
            while (musics==null){
                try {
                    if(musics!=null){
                        break;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    musics=getMusicList();
                }
            }
            for (int num = 0; num < musics.size(); num++) {
                try {
                    if (lock.tryLock()) {
                        if(currentNum!=0){
                            num=currentNum;
                            currentNum=0;
                        }
                        //判断是否要进行单曲循环播放
                        if (isLoop) {
                            num--;
                        }
                        if (num < 0) {
                            num = musics.size() - 1;
                        }
                        //将当前的音乐字符串转化为音乐实体类型,
                        Music music = musics.get(num);
                        //将歌曲名称与类型加载到控制音乐播放面板的音乐名称标签以及主页面的标题中
                        String title = music.getSongName() +"-"+music.getSongType();
                        songNameLabel.setText(title);
                        //修改音乐控制播放面板中歌手图标以及已添加我喜欢面板中的爱心图标
                        mainUI.setTitle(music.getSongName());
                        ImageIcon singerIcon= OnlineImageUtil.getOvalImage(music.getSingerPhotoUrl(),60);
                        singerImage.setIcon(singerIcon);
                        playMusicPanel.setAddMyLikePanel(music.getUserFavoriteSongId());
                        //设置音乐播放进度条的最大值以及最小值
                        currentProgress = 0;
                        songProgressBar.setMinimum(currentProgress);
                        songProgressBar.setMaximum(music.getSongPlayLength());
                        songProgressBar.setValue(currentProgress);
                        //根据歌曲的URL地址获取文件输入流。
                        musicConnection=new URL(music.getSongUrl()).openConnection();
                        musicConnection.connect();
                        InputStream inputStream = musicConnection.getInputStream();
                        audioInputStream = AudioSystem.getAudioInputStream(inputStream);
                        audioFormat = audioInputStream.getFormat();
                        // 转换mp3文件编码
                        if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                            audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                                    audioFormat.getSampleRate(), 16, audioFormat
                                    .getChannels(), audioFormat.getChannels() * 2,
                                    audioFormat.getSampleRate(), false);
                        }
                        //再次重新获得文件输入流
                        audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
                        // 获取受数据行支持的音频格式DataLine.info
                        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
                        // 获得与指定info类型相匹配的行
                        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                        // 打开具有指定格式的行，这样可使行获得所有所需系统资源并变得可操作
                        sourceDataLine.open(audioFormat);
                        // 允许某一个数据行执行数据i/o
                        sourceDataLine.start();
                        // 从音频流读取指定的最大数量的数据字节，并将其放入给定的字节数组中。
                        currentPlayMusic=music;
                        byte[] tempBuffer = new byte[1024 * 6];
                        int len;
                        recentlyPlayMusic.put(music.getId(),music);
                        List<Lrc> lrcList = LrcUtil.getLrcListFromNet(music.getLrcUrl());
                        //开启歌词展示线程
                        showLrcThread = new ShowLrcThread();
                        showLrcThread.setLrc(lrcLabel, lrcList, songProgressBar);
                        showLrcThread.start();
                        //开始播放音乐
                        while ((len = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
                            if(stop){
                                break;
                            }
                            //判断是否要暂时停止播放音乐
                            while (stopPlayMusic) {
                                if (playPreviousMusic || playNextMusic || playCurrentSong) {
                                    stopPlayMusic = false;
                                    //音乐播放控制按钮变更为播放音乐图标
                                    playSongPanel.removeAll();
                                    playSongPanel.add(playSongIcon);
                                    playSongPanel.repaint();
                                    playSongPanel.validate();
                                }
                                Thread.sleep(500);
                            }
                            //判断是否播放下一首歌曲
                            if (playNextMusic) {
                                if (isLoop) {
                                    num++;
                                }
                                break;
                            }
                            //判断是否播放上一首歌曲
                            if (playPreviousMusic) {
                                if (isLoop) {
                                    num--;
                                } else {
                                    num = num - 2;
                                }
                                break;
                            }
                            //判断当前鼠标所在歌曲面板上的歌曲是否进行播放
                            if (playCurrentSong) {
                                if (isLoop) {
                                    num = currentSongIndex;
                                } else {
                                    num = currentSongIndex - 1;
                                }
                                break;
                            }
                            currentProgress += len;
                            songProgressBar.setValue(currentProgress);
                            if (len > 0) {
                                //  通过此源数据行将数据写入混频器,也就是写入内存中
                                sourceDataLine.write(tempBuffer, 0, len);
                            }
                        }
                    }
                    //音乐列列表若发生改变，则跳出当前循环，更改播放的音乐列表
                    if (changeMusliList) {
                        if(playCurrentSong){
                            currentNum=currentSongIndex;
                        }
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    playCurrentSong=false;
                    playPreviousMusic=false;
                    playNextMusic=false;
                    showLrcThread.stop();
                    lock.unlock();
                    if (num == musicList.size() - 1) {
                        num = -1;
                    }
                    if (sourceDataLine != null) {
                        sourceDataLine.drain();
                        sourceDataLine.close();
                    }
                }
            }
        }
    }
}
