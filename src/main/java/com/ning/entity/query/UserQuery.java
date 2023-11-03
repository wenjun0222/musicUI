package com.ning.entity.query;

import java.io.Serializable;

public class UserQuery  implements Serializable {
    private Integer id;
    private String account;
    private String password;
    private String token;
    private String avatarPath;
    private String username;
    private String gender;
    private String code;
    private String editor;
    private String creator;
    private Integer status;
    private Integer isDeleted;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", code='" + code + '\'' +
                ", editor='" + editor + '\'' +
                ", creator='" + creator + '\'' +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
