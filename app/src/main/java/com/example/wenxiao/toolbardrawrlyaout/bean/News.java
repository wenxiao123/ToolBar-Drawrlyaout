package com.example.wenxiao.toolbardrawrlyaout.bean;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class News {
    private int id;
    private String title;
    private String url_id;

    public News() {
    }

    public News(int id, String title, String url_id) {
        this.id = id;
        this.title = title;
        this.url_id = url_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_id() {
        return url_id;
    }

    public void setUrl_id(String url_id) {
        this.url_id = url_id;
    }
}
