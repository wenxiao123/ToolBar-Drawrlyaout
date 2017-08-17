package com.example.wenxiao.toolbardrawrlyaout.bean;



import com.example.wenxiao.toolbardrawrlyaout.http.Url;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYang on 2017/6/29.
 * 更多可选频道
 */

public class MoreContains {
    public static List<News> getData(){
        List<News> list = new ArrayList<>();
        News news = new News();
        news.setId(0);
        news.setTitle("数码");
        news.setUrl_id(Url.ShuMaId);
        list.add(news);

        news = new News();
        news.setId(1);
        news.setTitle("移动");
        news.setUrl_id(Url.YiDongId);
        list.add(news);

        news = new News();
        news.setId(2);
        news.setTitle("彩票");
        news.setUrl_id(Url.CaiPiaoId);
        list.add(news);

        news = new News();
        news.setId(3);
        news.setTitle("教育");
        news.setUrl_id(Url.JiaoYuId);
        list.add(news);

        news = new News();
        news.setId(4);
        news.setTitle("论坛");
        news.setUrl_id(Url.LunTanId);
        list.add(news);

        news = new News();
        news.setId(5);
        news.setTitle("旅游");
        news.setUrl_id(Url.LvYouId);
        list.add(news);

        news = new News();
        news.setId(6);
        news.setTitle("手机");
        news.setUrl_id(Url.ShouJiId);
        list.add(news);

        news = new News();
        news.setId(7);
        news.setTitle("博客");
        news.setUrl_id(Url.BoKeId);
        list.add(news);

        news = new News();
        news.setId(8);
        news.setTitle("社会");
        news.setUrl_id(Url.SheHuiId);
        list.add(news);

        news = new News();
        news.setId(9);
        news.setTitle("家居");
        news.setUrl_id(Url.JiaJuId);
        list.add(news);

        news = new News();
        news.setId(10);
        news.setTitle("暴雪");
        news.setUrl_id(Url.BaoXueId);
        list.add(news);

        news = new News();
        news.setId(11);
        news.setTitle("亲子");
        news.setUrl_id(Url.QinZiId);
        list.add(news);
        news = new News();

        news.setId(12);
        news.setTitle("CBA");
        news.setUrl_id(Url.CBAId);
        list.add(news);

        news = new News();
        news.setId(13);
        news.setTitle("消息");
        news.setUrl_id(Url.MsgId);
        list.add(news);

        news = new News();
        news.setId(14);
        news.setTitle("北京");
        news.setUrl_id(Url.BeiJingId);
        list.add(news);

        news = new News();
        news.setId(15);
        news.setTitle("军事");
        news.setUrl_id(Url.JunShiId);
        list.add(news);

        news = new News();
        news.setId(16);
        news.setTitle("房产");
        news.setUrl_id(Url.FangChanId);
        list.add(news);

        return list;
    }
}
