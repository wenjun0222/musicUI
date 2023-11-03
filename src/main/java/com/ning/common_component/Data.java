package com.ning.common_component;

import com.ning.entity.Singer;
import com.ning.entity.SongType;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<SongType> getSongTypeList(){
        List<SongType> songTypeList=new ArrayList<>();
        for(int x=0;x<25;x++) {
            SongType songType = new SongType();
            songType.setId(1);
            songType.setTypeName("古风");
            songType.setTypeSummary("超好听的国风歌曲");
            songType.setTypePhotoUrl("https://pic3.zhimg.com/v2-fe9848818cbd1cdd6a232a1855084606_r.jpg");
            songTypeList.add(songType);
        }
        return songTypeList;
    }
    public static List<Singer> getSingerList(){
        List<Singer> singerList=new ArrayList<>();
        return singerList;
    }
}
