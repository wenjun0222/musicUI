package com.ning.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ning.common_component.LoadingPanel;
import com.ning.common_component.NetworkErrorPanel;
import com.ning.common_component.*;
import com.ning.entity.ResponseResult;
import com.ning.entity.Singer;
import com.ning.entity.SongType;
import com.ning.entity.query.Music;
import com.ning.entity.query.SingerMusicList;
import com.ning.entity.query.SongTypeMusicList;
import com.ning.entity.query.UserQuery;
import com.ning.ui.main.PlayMusicPanel;
import com.ning.ui.main.RightDownPanel;
import com.ning.ui.main.right_down_panel.*;
import com.ning.utils.OnlineImageUtil;
import com.ning.utils.UserUtil;
import com.ning.utils.http.DoGetThread;
import com.ning.utils.http.HttpUtil;
import com.ning.ui.main.right_down_panel.*;

import javax.swing.*;
import java.util.*;

public class ShowThread  {
    private final static ShowThread INSTANCE=new ShowThread();
    public static ShowThread getShowThread(){
        return INSTANCE;
    }
    /**
     * 搜索音乐后的得到的结果标志
     * */
    private Integer searchDataResult;
    /**
     * 搜索音乐列表面板
     * */
    private SearchMusicPanel searchMusicPanel=SearchMusicPanel.getSearchMusicPanel();
    /**
     * 歌手照片集合
     * */
    private Map<String, ImageIcon> singerImageMap=new HashMap<>();
    /**
     * 歌曲类型照片集合
     * */
    private Map<String, ImageIcon> songTypeIconMap=new HashMap<>();
    public void clearMap(){
        singerImageMap.clear();
        songTypeIconMap.clear();
    }
    /**
     * 用户最近播放的音乐列表
     * */
    private RecentPlaySongPanel recentPlaySongPanel=RecentPlaySongPanel.getRecentPlaySongPanel();
    /**
     * 用户喜欢的音乐面板列表面板
     * */
    private UserLikeMusicPanel userLikeMusicPanel=UserLikeMusicPanel.getMyLikeMusicPanel();
    /**
     * 歌曲类型信息以及对应的歌曲列表面板
     * */
    private SongTypeMusicPanel songTypeMusicPanel=SongTypeMusicPanel.getSongTypeMusicPanel();
    /**
     * 当前主窗体右下面板展示歌曲类型信息以及音乐作品面板的歌曲类型id
     * */
    private Integer currentTypeId;
    /**
     * 设置当前主窗体右下面板展示歌曲类型信息以及音乐作品面板的歌曲类型id
     */
    public void setCurrentTypeId(Integer currentTypeId) {
        this.currentTypeId = currentTypeId;
    }
    /**
     * 当前主窗体右下面板展示歌手信息以及音乐作品面板的歌手id
     * */
    private Integer currentSingerId;
    /**
     * 设置当前主窗体右下面板展示歌手信息以及音乐作品面板的歌手id
     * */
    public void setCurrentSingerId(Integer currentSingerId){
        this.currentSingerId=currentSingerId;
    }
    /**
     * 播放音乐线程
     * */
    private PlayMusic playMusic=PlayMusic.getInstance();
    /**
     * 获取登录的用户信息
     * */
    private UserQuery user= UserUtil.getUser();
    /**
     * 主窗体的右下面板
     * */
    private RightDownPanel rightDownPanel=RightDownPanel.getRightDownPanel();
    /**
     * 歌曲类型列表面板
     * */
    private SongTypeListPanel songTypeListPanel=SongTypeListPanel.getSongTypeListPanel();
    /**
     * 网络错误面板
     * */
    private NetworkErrorPanel networkErrorPanel=new NetworkErrorPanel();
    /**
     * 加载面板
     * */
    private LoadingPanel loadingPanel=new LoadingPanel();
    /**
     * 主窗体右下面板展示的面板名称
     * */
    private String currentPanel;
    /**
     * 歌手列表面板
     * */
    private SingerListShowPanel singerListShowPanel=SingerListShowPanel.getSingerListShowPanel();
    /**
     * 设置主窗体右下面板展示的面板名称
     * */
    public void setCurrentPanel(String currentPanel){
        this.currentPanel=currentPanel;
    }
    /**
     * 歌手列表
     * */
    private  List<Singer> singerList=new ArrayList<>();
    /**
     * 歌曲列表
     * */
    private List<Music> musicList=null;
    /**
     * 歌手信息以及相关的音乐作品作品列表展示面板
     * */
    private SingerMusicListPanel singerMusicListPanel=SingerMusicListPanel.getSingerMusicListPanel();
    /**
     * 对外暴露歌曲类型加载线程
     * */
    public ShowSongTypeListThread getShowSongTypeListThread(){
        return new ShowSongTypeListThread();
    }
    /**
     * 对外暴露最近播放的音乐加载线程
     * */
    public RecentlyPlayMusicThread getRecentlyPlayMusicThread(){
        return new RecentlyPlayMusicThread();
    }
    /**
     * 对外暴露歌手加载线程
     * */
    public ShowSingerListThread getShowSingerListThread(){
        return new ShowSingerListThread();
    }
    /**
     * 对外暴露歌手加载线程
     * */
    public ShowSingerMusicThread getShowSingerMusicThread(){
        return new ShowSingerMusicThread();
    }
    /**
     * 对外暴露加载歌曲类型信息以及对应的歌曲线程
     * */
    public LoadSongTypeMusicThread getLoadSongTypeMusicThread(){
        return new LoadSongTypeMusicThread();
    }
    /**
     * 对外暴露根据歌曲名称或者歌手名称搜索相关的音乐线程
     * */
    public SearchMusicByNameThread getSearchMusicByNameThread(){
        return new SearchMusicByNameThread();
    }
    /**
     * 对外暴露加载用户喜欢的音乐列表线程
     * */
    public LoadUserLikeSongThread loadUserLikeSongThread(){
        return new LoadUserLikeSongThread();
    }
    /**
     * 对外暴露将歌曲添加到用户喜欢列表线程
     * */
    public addUserLikeSongThread addUserLikeSongThread(){
        return new addUserLikeSongThread();
    }
    /**
     * 取消用户喜欢的音乐线程
     * */
    public CancelUserLikeSongThread cancelUserLikeSongThread(){
        return new CancelUserLikeSongThread();
    }
    /**
     * 歌单分类加载线程
     * */
    public class ShowSongTypeListThread extends Thread {
        private List<SongType> songTypeList;
        public void run() {
            //一直加载的gif面板，若加载完成则刷新成歌单分类列表
            rightDownPanel.changePanel(loadingPanel);
            HttpUtil httpUtil=new DoGetThread("music/song/getSongTypeList") {
                @Override
                public void success(ResponseResult responseResult) {
                    if(responseResult.code==200){
                        JSON json=(JSON) responseResult.data;
                        String s = json.toJSONString();
                        songTypeList = JSONObject.parseArray(s, SongType.class);
                        //若是歌单分类图标未加载到内存中，则开始加载到内存中。
                        if (songTypeIconMap.size() <= 0) {
                            for (int x = 0; x < songTypeList.size(); x++) {
                                try {
                                    SongType songType=songTypeList.get(x);
                                    String songTypePhotoUrl = songType.getTypePhotoUrl();
                                    songTypeIconMap.put(songTypePhotoUrl,
                                            OnlineImageUtil.getRectImage(songTypePhotoUrl, 250));
                                } catch (Exception e) {
                                    //网络连接错误时，主窗体右下页面刷新成网络错误提示面板，并且开始等待网络重新链接时再次加载
                                    rightDownPanel.changePanel(networkErrorPanel);
                                }
                            }
                        }
                        songTypeListPanel.loadData(songTypeList,songTypeIconMap);
                        //判断是否需要将歌单分类列表面板展示在主窗体右下面板中
                        if(currentPanel.equals("songTypeListPanel")) {
                            rightDownPanel.changePanel(songTypeListPanel);
                        }
                    }
                }
            };
            httpUtil.run();

        }
    }
    /**
     * 歌手加载线程
     * */
    public class ShowSingerListThread extends Thread{
        @Override
        public void run() {
            rightDownPanel.changePanel(loadingPanel);
            //一直加载的gif面板，若加载完成则刷新成歌手列表面板
            HttpUtil httpUtil=new DoGetThread("music/singer/getSingerList") {
                @Override
                public void success(ResponseResult responseResult) {
                    if(responseResult.code==200) {
                        JSON singerListJson=(JSON) responseResult.data;
                        String s = singerListJson.toJSONString();
                        singerList = JSONObject.parseArray(s, Singer.class);
                        if(singerImageMap.size()<=0){
                            for(int x=0;x<singerList.size();x++){
                                try {
                                    String singerPhotoPath = singerList.get(x).getSingerPhotoUrl();
                                    singerImageMap.put(singerPhotoPath,OnlineImageUtil.getOvalImage(singerPhotoPath,200));
                                } catch (Exception e) {
                                    //网络连接错误时，主窗体右下页面刷新成网络错误提示面板，并且开始等待网络重新链接时再次加载
                                    rightDownPanel.changePanel(networkErrorPanel);
                                }
                            }
                        }
                        singerListShowPanel.loadMusicData(singerList,singerImageMap);
                        if(currentPanel.equals("singerListPanel")) {
                            rightDownPanel.changePanel(singerListShowPanel);
                        }
                    }
                }
            };
            httpUtil.run();

        }
    }
    /**
     * 加载最近播放的音乐列表线程
     * */
    public  class RecentlyPlayMusicThread extends Thread{
        @Override
        public void run() {
            musicList = mapConvertList();
            recentPlaySongPanel.loadDataMusic(musicList);
            playMusic.setMusicList(musicList);
            playMusic.changeMusicList();
            if(currentPanel.equals("recentlyPlayMusicPanel")){
                rightDownPanel.changePanel(recentPlaySongPanel);
            }
        }
    }
    /**
     * 加载歌手信息以及相关的音乐作品列表线程
     * */
    public class ShowSingerMusicThread extends Thread{
        private SingerMusicList singerMusicList;
        @Override
        public void run() {
            //一直加载的gif面板，若加载完成则刷新成歌单分类列表
            rightDownPanel.changePanel(loadingPanel);
            HttpUtil httpUtil=new DoGetThread("music/song/getSongListBySingerAndUser" +
                    "/"+user.getId()+"/"+currentSingerId) {
                @Override
                public void success(ResponseResult responseResult) {
                    if(responseResult.code==200){
                        JSON singerMusicListJson =(JSON) responseResult.data;
                        singerMusicList = JSON.toJavaObject(singerMusicListJson, SingerMusicList.class);
                        musicList = singerMusicList.getMusicList();
                        playMusic.setMusicList(musicList);
                        playMusic.changeMusicList();
                        singerMusicListPanel.loadMusicData(singerMusicList);
                        if(currentPanel.equals("singerMusicListPanel")) {
                            rightDownPanel.changePanel(singerMusicListPanel);
                        }
                    }
                }
            };
            httpUtil.run();
        }
    }
    /**
     * 加载歌曲类型信息以及对应的歌曲线程
     * */
    public class LoadSongTypeMusicThread extends Thread{
        private SongTypeMusicList songTypeMusicList;
        @Override
        public void run() {
            //一直加载的gif面板，若加载完成则刷新成歌单分类列表
            rightDownPanel.changePanel(loadingPanel);
            HttpUtil httpUtil=new DoGetThread("music/song/getSongListBySingerAndType/"+user.getId()+
                    "/"+currentTypeId) {
                @Override
                public void success(ResponseResult responseResult) {
                    if(responseResult.code==200){
                        JSON json=(JSON) responseResult.data;
                        songTypeMusicList=JSON.toJavaObject(json, SongTypeMusicList.class);
                        musicList = songTypeMusicList.getMusicList();
                        playMusic.setMusicList(musicList);
                        playMusic.changeMusicList();
                        songTypeMusicPanel.loadMusicData(songTypeMusicList);
                        if(currentPanel.equals("songTypeMusicPanel")) {
                            rightDownPanel.changePanel(songTypeMusicPanel);
                        }
                    }
                }
            };
            httpUtil.run();

        }
    }
    /**
     * 加载用户喜欢的音乐列表线程
     * */
    public class LoadUserLikeSongThread extends Thread{
        @Override
        public void run() {
            //一直加载的gif面板，若加载完成则刷新成歌单分类列表
            rightDownPanel.changePanel(loadingPanel);
            HttpUtil httpUtil=new DoGetThread("/user/getUserFavoriteSongById/"+user.getId()) {
                @Override
                public void success(ResponseResult responseResult) {
                    if(responseResult.code==200){
                        JSON json=(JSON) responseResult.data;
                        String s = json.toJSONString();
                        musicList = JSONObject.parseArray(s, Music.class);
                        userLikeMusicPanel.loadMusicData(musicList);
                        playMusic.setMusicList(musicList);
                        playMusic.changeMusicList();
                        if(currentPanel.equals("userLikeMusicPanel")){
                            rightDownPanel.changePanel(userLikeMusicPanel);
                        }
                    }
                }
            };
            httpUtil.run();
        }
    }
    /**
     * 根据歌曲名称或者歌手名称搜索相关的音乐线程
     * */
    public class SearchMusicByNameThread extends Thread{
        private String searchName;

        public void setSearchName(String searchName) {
            this.searchName = searchName;
        }
        @Override
        public void run() {
            //一直加载的gif面板，若加载完成则刷新成歌单分类列表
            rightDownPanel.changePanel(loadingPanel);
            HttpUtil httpUtil=new DoGetThread("music/song/getMusicListByName/" +
                    searchName+"/"+user.getId()) {
                @Override
                public void success(ResponseResult responseResult) {
                    JSON json=(JSON) responseResult.data;
                    String s = json.toJSONString();
                    musicList= JSONObject.parseArray(s,Music.class);
                    for (Music music : musicList) {
                        System.out.println(music);
                    }
                    if(responseResult.code==200){
                        searchDataResult=0;
                    }else if(responseResult.code==201){
                        searchDataResult=1;
                    }
                    searchMusicPanel.loadMusicData(musicList,searchDataResult);
                    playMusic.setMusicList(musicList);
                    playMusic.changeMusicList();
                    if(currentPanel.equals("searchMusicPanel")){
                        rightDownPanel.changePanel(searchMusicPanel);
                    }
                }
            };
            httpUtil.run();
        }
    }
    /**
     * 将歌曲添加到用户喜欢列表线程
     * */
    public class addUserLikeSongThread extends Thread{
        private PlayMusicPanel playMusicPanel;

        public void setPlayMusicPanel(PlayMusicPanel playMusicPanel) {
            this.playMusicPanel=playMusicPanel;
        }
        @Override
        public void run() {
            Music music=playMusic.getCurrentPlayMusic();
            int currentPlaySongId = music.getId();
            HttpUtil httpUtil=new DoGetThread("user/addUserLikeSong/"+currentPlaySongId+"/"+user.getId()) {
                @Override
                public void success(ResponseResult responseResult) {
                    if(responseResult.code==200) {
                        Integer userLikeSongId = (Integer) responseResult.data;
                        if(!(currentPanel.equals("recentlyPlayMusicPanel") || currentPanel.equals("userLikeMusicPanel"))) {
                            for (Music music : musicList) {
                                if (music.getId() == currentPlaySongId) {
                                    music.setUserFavoriteSongId(userLikeSongId);
                                    break;
                                }
                            }
                        }
                        switch (currentPanel){
                            case"singerMusicListPanel":
                                singerMusicListPanel.changeMusicList(musicList);
                                break;
                            case"songTypeMusicPanel":
                                songTypeMusicPanel.changeMusicList(musicList);
                                break;
                            case"recentlyPlayMusicPanel":
                                setIdToMusicMap(userLikeSongId,currentPlaySongId);
                                musicList = mapConvertList();
                                recentPlaySongPanel.loadDataMusic(musicList);
                                break;
                            case"searchMusicPanel":
                                searchMusicPanel.loadMusicData(musicList,searchDataResult);
                                break;
                            case"userLikeMusicPanel":
                                music.setUserFavoriteSongId(userLikeSongId);
                                musicList.add(music);
                                userLikeMusicPanel.loadMusicData(musicList);
                                break;
                        }
                        playMusic.setMusicList(musicList);
                        playMusic.changeMusicList();
                        playMusicPanel.setAddMyLikePanel(1);
                        if(!currentPanel.equals("recentlyPlayMusicPanel")) {
                            setIdToMusicMap(userLikeSongId,currentPlaySongId);
                        }
                    }
                }
            };
            httpUtil.run();
        }
    }
    /**
     * 取消用户喜欢的音乐线程
     * */
    public class CancelUserLikeSongThread extends Thread{
        private PlayMusicPanel playMusicPanel;
        public void setPlayMusicPanel(PlayMusicPanel playMusicPanel) {
            this.playMusicPanel=playMusicPanel;
        }
        @Override
        public void run() {
            int currentPlaySongId = playMusic.getCurrentPlayMusic().getId();
            int userLikeSongId = playMusic.getCurrentPlayMusic().getUserFavoriteSongId();
            HttpUtil httpUtil=new DoGetThread("user/deleteUserLikeSong/"+userLikeSongId+"/"+user.getId()) {
                @Override
                public void success(ResponseResult responseResult) {
                    if(responseResult.code==200){
                        if(!(currentPanel.equals("recentlyPlayMusicPanel") || currentPanel.equals("userLikeMusicPanel"))) {
                            for (Music music : musicList) {
                                if (music.getId() == currentPlaySongId) {
                                    music.setUserFavoriteSongId(null);
                                    break;
                                }
                            }
                        }
                        switch (currentPanel){
                            case"singerMusicListPanel":
                                singerMusicListPanel.changeMusicList(musicList);
                                break;
                            case"songTypeMusicPanel":
                                songTypeMusicPanel.changeMusicList(musicList);
                                break;
                            case"recentlyPlayMusicPanel":
                                setIdToMusicMap(null,currentPlaySongId);
                                musicList = mapConvertList();
                                recentPlaySongPanel.loadDataMusic(musicList);
                                break;
                            case"searchMusicPanel":
                                searchMusicPanel.loadMusicData(musicList,searchDataResult);
                                break;
                            case"userLikeMusicPanel":
                                int size = musicList.size();
                                for(int x=0;x<size;x++){
                                    if(currentPlaySongId==musicList.get(x).getId()){
                                        musicList.remove(x);
                                        break;
                                    }
                                }
                                userLikeMusicPanel.loadMusicData(musicList);
                                break;
                        }
                        playMusic.setMusicList(musicList);
                        playMusic.changeMusicList();
                        playMusicPanel.setAddMyLikePanel(null);
                        if(!currentPanel.equals("recentlyPlayMusicPanel")) {
                            setIdToMusicMap(null,currentPlaySongId);
                        }
                    }

                }
            };
            httpUtil.run();
        }
    }
    /**
     * 将歌曲Map集合转化为List集合
     * */
    public List<Music> mapConvertList(){
        List<Music> musicList=new ArrayList<>();
        Map<Integer,Music> recentlyPlayMusic= playMusic.recentlyPlayMusic;
        Collection<Music> values = recentlyPlayMusic.values();
        Iterator<Music> iterator = values.iterator();
        while (iterator.hasNext()){
            Music music = iterator.next();
            musicList.add(music);
        }
        return musicList;
    }
    /**
     * 将userLikeSongId值设置为最近播放音乐Map集合的众多中的一个
     * */
    private void setIdToMusicMap(Integer userLikeSongId,Integer currentPlaySongId){
        Map<Integer, Music> recentlyPlayMusic = playMusic.recentlyPlayMusic;
        Music music = recentlyPlayMusic.get(currentPlaySongId);
        music.setUserFavoriteSongId(userLikeSongId);
        recentlyPlayMusic.put(music.getId(),music);
    }
}
