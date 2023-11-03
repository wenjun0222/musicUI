package com.ning.ui.main.right_down_panel;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ning.common_component.ShowMusicPanel;
import com.ning.entity.ResponseResult;
import com.ning.entity.query.Music;
import com.ning.entity.query.UserQuery;
import com.ning.thread.PlayMusic;
import com.ning.utils.UserUtil;
import com.ning.utils.http.DoGetThread;
import com.ning.utils.http.HttpUtil;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * 我喜欢音乐列表面板
 * */
public class UserLikeMusicPanel extends JPanel{
    private final static UserLikeMusicPanel INSTANCE=new UserLikeMusicPanel();
    /**
     *音乐播放线程
     * */
    private PlayMusic playMusic=PlayMusic.getInstance();
    /**
     * 我喜欢音乐标签
     * */
    private JLabel ILikeMusicLabel=new JLabel();
    /**
     * 歌曲列表表头面板
     * */
    private JPanel tableHeaderPanel=new JPanel();
    /**
     * 歌曲列表展示滚动面板
     * */
    JScrollPane musicScrollPanel=new JScrollPane();
    /**
     * 音乐列表展示面板
     * */
    private JPanel showMusicPanel=new JPanel();
    public UserLikeMusicPanel(){
        init();
    }
    /**
     *初始化我喜欢音乐列表面板,
     * */
    public void init(){
        this.setBounds(0,0, 950,620);
        this.setLayout(null);
        this.setBackground(Color.white);
        ILikeMusicLabelInit();
        this.add(ILikeMusicLabel);
        tableHeaderPanelInit();
        this.add(tableHeaderPanel);
        musicScrollPanelInit();
        this.add(musicScrollPanel);
    }
    /**
     * 初始化我喜欢标签，宽为500，高为50，距离我喜欢音乐列表面板左侧距离为30，上侧距离为10，
     * 字体大小为50，加粗
     * */
    private void ILikeMusicLabelInit(){
        ILikeMusicLabel.setBounds(50,20,500,40);
        ILikeMusicLabel.setText("我喜欢");
        ILikeMusicLabel.setFont(new Font(null,Font.BOLD,40));
    }
    /**
     * 初始化音乐列表表头，宽为900，高为30，距离我喜欢音乐列表面板左侧距离为50，上侧距离为80,背景色为白色
     * */
    private void tableHeaderPanelInit(){
        tableHeaderPanel.setLayout(null);
        tableHeaderPanel.setBounds(50,80, 900,30);
        tableHeaderPanel.setBackground(Color.white);
        //歌名标签，距离表头面板左侧距离为0
        JLabel songNameLabel = getSongInformationLabel(0, "歌名");
        tableHeaderPanel.add(songNameLabel);
        //歌手名字标签，距离表头面板左侧距离为225
        JLabel singerNameLabel = getSongInformationLabel(225, "歌手");
        tableHeaderPanel.add(singerNameLabel);
        //歌曲类型标签，距离表头面板左侧距离为450
        JLabel songTypeLabel = getSongInformationLabel(450, "类型");
        tableHeaderPanel.add(songTypeLabel);
        //歌曲时长标签，距离表头面板左侧距离为675
        JLabel durationLabel = getSongInformationLabel(675, "时长");
        tableHeaderPanel.add(durationLabel);
    }
    /**
     * 音乐列表的表头面板中歌曲名称，歌手名称，歌曲类型，歌曲时长等标签获取方法
     * 宽为80，高为30，距离表头面板左侧距离由x决定，上侧距离为0
     * 字体大小为24，加粗，颜色为灰色。
     * */
    private JLabel getSongInformationLabel(int x,String prompt){
        JLabel songInformationLabel=new JLabel();
        songInformationLabel.setFont(new Font(null,Font.BOLD,24));
        songInformationLabel.setBounds(x,0,80,30);
        songInformationLabel.setText(prompt);
        songInformationLabel.setForeground(Color.GRAY);
        return songInformationLabel;
    }
    /**
     * 初始化滚动面板，宽为950，高为510，距离我喜欢音乐展示面板左侧距离为0，上侧距离为110.
     * 垂直滚动条显示，水平滚动条不显示
     * 添加音乐音乐列表面板，宽为950.
     * */
    public void musicScrollPanelInit(){
        musicScrollPanel.setBorder(null);
        musicScrollPanel.setBounds(0,110,950,510);
        musicScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        musicScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        musicScrollPanel.setBackground(Color.white);
        showMusicPanel.setLayout(null);
        showMusicPanel.setBackground(Color.white);
        musicScrollPanel.getViewport().add(showMusicPanel);
        UserQuery user=UserUtil.getUser();
        UserLikeMusicPanel userLikeMusicPanel=this;
        HttpUtil httpUtil=new DoGetThread("/user/getUserFavoriteSongById/"+user.getId()) {
            @Override
            public void success(ResponseResult responseResult) {
                if(responseResult.code==200){
                    JSON json=(JSON) responseResult.data;
                    String s = json.toJSONString();
                    List<Music> musicList = JSONObject.parseArray(s, Music.class);
                    userLikeMusicPanel.loadMusicData(musicList);
                    playMusic.setMusicList(musicList);
                }
            }
            @Override
            public void error(Exception e) {
            }
        };
        httpUtil.run();
    }
    public void loadMusicData(List<Music> musicList){
        showMusicPanel.removeAll();
        showMusicPanel.setPreferredSize(new Dimension(950,50*musicList.size()));
        for (int x=0;x<musicList.size();x++) {
            Music music = musicList.get(x);
            ShowMusicPanel showMusic=new ShowMusicPanel(music,50*x,x);
            showMusicPanel.add(showMusic);
        }
        showMusicPanel.repaint();
        showMusicPanel.validate();
    }
    /**
     * 获取歌曲长度
     * */
    private static int getMusicLength(File file) {
        AudioInputStream audioInputStream = null;
        AudioFormat audioFormat = null;// 文件格式
        int musicLength = 0;
        int len;
        byte[] bytes = new byte[1024];
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
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
            while ((len = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
                musicLength += len;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicLength;
    }
    public static UserLikeMusicPanel getMyLikeMusicPanel(){
        return INSTANCE;
    }

}
