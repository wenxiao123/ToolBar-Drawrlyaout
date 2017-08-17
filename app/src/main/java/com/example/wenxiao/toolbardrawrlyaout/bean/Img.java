package com.example.wenxiao.toolbardrawrlyaout.bean;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class Img {
    private String desc,createdate,scover,seturl,setname;

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    public Img(String desc, String createdate, String scover, String seturl, String setname) {
        this.desc = desc;
        this.createdate = createdate;
        this.scover = scover;
        this.seturl = seturl;
        this.setname = setname;
    }

    public Img(String desc, String createdate, String scover, String seturl) {
        this.desc = desc;
        this.createdate = createdate;
        this.scover = scover;
        this.seturl = seturl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getScover() {
        return scover;
    }

    public void setScover(String scover) {
        this.scover = scover;
    }

    public String getSeturl() {
        return seturl;
    }

    public void setSeturl(String seturl) {
        this.seturl = seturl;
    }
}
