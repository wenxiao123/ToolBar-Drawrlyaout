package com.example.wenxiao.toolbardrawrlyaout.bean;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class Tv {
    private String cover, mp4_url, title, ptime, topicImg, topicName;

    public Tv(String cover, String mp4_url, String title, String ptime, String topicImg, String topicName) {
        this.cover = cover;
        this.mp4_url = mp4_url;
        this.title = title;
        this.ptime = ptime;
        this.topicImg = topicImg;
        this.topicName = topicName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}