package com.ning.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 歌手列表(Singer)实体类
 *
 * @author makejava
 * @since 2023-06-20 19:33:16
 */
public class Singer implements Serializable {
    private static final long serialVersionUID = 679299187623834654L;
    /**
     * 歌手id
     */
    private Integer id;
    /**
     * 歌手名字
     */
    private String singerName;
    /**
     * 歌手职业
     * */
    private String job;
    /**
     * 歌手国籍
     * */
    private String nationality;
    /**
     * 歌手代表作
     * */
    private String masterpiece;
    /**
     * 歌手照片地址
     */
    private String singerPhotoUrl;
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
     * 歌手状态
     * */
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

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerPhotoUrl() {
        return singerPhotoUrl;
    }

    public void setSingerPhotoUrl(String singerPhotoUrl) {
        this.singerPhotoUrl = singerPhotoUrl;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMasterpiece() {
        return masterpiece;
    }

    public void setMasterpiece(String masterpiece) {
        this.masterpiece = masterpiece;
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
        return "Singer{" +
                "id=" + id +
                ", singerName='" + singerName + '\'' +
                ", job='" + job + '\'' +
                ", nationality='" + nationality + '\'' +
                ", masterpiece='" + masterpiece + '\'' +
                ", singerPhotoUrl='" + singerPhotoUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creator='" + creator + '\'' +
                ", editor='" + editor + '\'' +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

