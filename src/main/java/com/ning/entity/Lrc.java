package com.ning.entity;

public class Lrc {
    private Long prefixTime;
    private String content;

    public Lrc(Long prefixTime, String content) {
        this.prefixTime = prefixTime;
        this.content = content;
    }

    public Long getPrefixTime() {
        return prefixTime;
    }

    public void setPrefixTime(Long prefixTime) {
        this.prefixTime = prefixTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Lrc{" +
                "prefixTime=" + prefixTime +
                ", content='" + content + '\'' +
                '}';
    }
}
