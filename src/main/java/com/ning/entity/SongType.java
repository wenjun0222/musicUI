package com.ning.entity;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 歌曲类型列表(SongType)实体类
 *
 * @author makejava
 * @since 2023-06-20 19:34:57
 */
public class SongType implements Serializable {
    private static final long serialVersionUID = -96567577399461525L;
    /**
     * 歌曲类型id
     */
    private Integer id;
    /**
     * 歌曲类型
     */
    private String typeName;
    /**
     * 歌曲简短概述
     * */
    private String typeSummary;
    /**
     * 歌曲类型介绍
     */
    private String typeDescription;
    /**
     * 歌曲类型照片地址
     * */
    private String typePhotoUrl;
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
     * 歌曲类型状态,0为正常，1为已下架
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypeSummary() {
        return typeSummary;
    }

    public void setTypeSummary(String typeSummary) {
        this.typeSummary = typeSummary;
    }

    public String getTypePhotoUrl() {
        return typePhotoUrl;
    }

    public void setTypePhotoUrl(String typePhotoUrl) {
        this.typePhotoUrl = typePhotoUrl;
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
        return "SongType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", typeSummary='" + typeSummary + '\'' +
                ", typeDescription='" + typeDescription + '\'' +
                ", typePhotoUrl='" + typePhotoUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creator='" + creator + '\'' +
                ", editor='" + editor + '\'' +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

