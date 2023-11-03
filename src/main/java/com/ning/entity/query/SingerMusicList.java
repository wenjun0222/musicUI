package com.ning.entity.query;



import com.ning.entity.Singer;

import java.io.Serializable;
import java.util.List;

public class SingerMusicList extends Singer implements Serializable {
    private List<Music> musicList;

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    @Override
    public String toString() {
        return "SingerAndSongQuery{" +
                super.toString()+
                ",musicList=" + musicList +
                '}';
    }
}
