package com.ning.entity.query;


import java.time.LocalDateTime;

public class Music {
    /**
     * 歌曲id
     * */
    private Integer id;
    /**
     * 是否加载到我喜欢音乐列表，0为已经加入，1未加入。
     * */
    private Integer userFavoriteSongId;
    /**
     * 歌手名称
     * */
    private String songName;
    /**
     * 歌手名称
     * */
    private String singerName;
    /**
     * 歌曲文件路径
     * */
    private String songUrl;
    /**
     * 歌曲类型
     * */
    private String songType;
    /**
     * 歌词文件路径
     * */
    private String lrcUrl;
    /**
     * 歌曲时长
     * */
    private String songDuration;
    /**
     * 歌手照片文件路径
     * */
    private String singerPhotoUrl;
    /**
     * 歌曲字节长度
     * */
    private Integer songPlayLength;
    /**
     * 歌曲状态
     * */
    private Integer status;
    /**
     * 创建时间
     * */
    private LocalDateTime createTime;
    /**
     * 更新时间
     * */
    private LocalDateTime updateTime;
    /**
     * 创建者
     * */
    private String creator;
    /**
     * 更新者
     * */
    private String editor;
    private Integer isDeleted;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserFavoriteSongId() {
        return userFavoriteSongId;
    }

    public void setUserFavoriteSongId(Integer userFavoriteSongId) {
        this.userFavoriteSongId = userFavoriteSongId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSongType() {
        return songType;
    }

    public void setSongType(String songType) {
        this.songType = songType;
    }

    public String getLrcUrl() {
        return lrcUrl;
    }

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getSingerPhotoUrl() {
        return singerPhotoUrl;
    }

    public void setSingerPhotoUrl(String singerPhotoUrl) {
        this.singerPhotoUrl = singerPhotoUrl;
    }

    public Integer getSongPlayLength() {
        return songPlayLength;
    }

    public void setSongPlayLength(Integer songPlayLength) {
        this.songPlayLength = songPlayLength;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", userFavoriteSongId=" + userFavoriteSongId +
                ", songName='" + songName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", songType='" + songType + '\'' +
                ", lrcUrl='" + lrcUrl + '\'' +
                ", songDuration='" + songDuration + '\'' +
                ", singerPhotoUrl='" + singerPhotoUrl + '\'' +
                ", songPlayLength=" + songPlayLength +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creator='" + creator + '\'' +
                ", editor='" + editor + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
