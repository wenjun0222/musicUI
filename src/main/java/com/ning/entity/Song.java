package com.ning.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 歌曲表(Song)实体类
 */
public class Song implements Serializable {
    private static final long serialVersionUID = -39074907419431855L;
    /**
     * 歌曲id
     */
    private Integer id;
    /**
     * 歌曲名称
     */
    private String songName;
    /**
     * 歌曲文件地址
     */
    private String songUrl;
    /**
     * 歌曲时长
     * */
    private String songDuration;
    /**
     * 歌曲播放时的字节长度
     * */
    private Integer songPlayLength;
    /**
     * 歌词文件地址
     * */
    private String lrcUrl;
    /**
     * 歌手id
     */
    private Integer singerId;
    /**
     * 歌曲类型
     */
    private Integer typeId;
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
    /**
     * 0表示上架，1已下架
     */
    private Integer status;
    /**
     * 0未删除，1已删除
     */
    private Integer isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public Integer getSongPlayLength() {
        return songPlayLength;
    }

    public void setSongPlayLength(Integer songPlayLength) {
        this.songPlayLength = songPlayLength;
    }

    public String getLrcUrl() {
        return lrcUrl;
    }

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", songDuration='" + songDuration + '\'' +
                ", songPlayLength=" + songPlayLength +
                ", lrcUrl='" + lrcUrl + '\'' +
                ", singerId=" + singerId +
                ", typeId=" + typeId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creator='" + creator + '\'' +
                ", editor='" + editor + '\'' +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

