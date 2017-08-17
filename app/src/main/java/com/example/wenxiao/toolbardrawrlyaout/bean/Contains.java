package com.example.wenxiao.toolbardrawrlyaout.bean;

import com.example.wenxiao.toolbardrawrlyaout.http.Url;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYang on 2017/6/29.
 * 存放初始化频道的集合
 */

public class Contains {
    public static List<News> getData()
    {
        List<News> list = new ArrayList<>();
        News news = new News();
        news.setId(0);
        news.setTitle("头条");
        news.setUrl_id(Url.TopId);
        list.add(news);

        news = new News();
        news.setId(1);
        news.setTitle("精选");
        news.setUrl_id(Url.JingXuanId);
        list.add(news);

        news = new News();
        news.setId(2);
        news.setTitle("娱乐");
        news.setUrl_id(Url.YuLeId);
        list.add(news);

        news = new News();
        news.setId(3);
        news.setTitle("体育");
        news.setUrl_id(Url.TiYuId);
        list.add(news);

        news = new News();
        news.setId(4);
        news.setTitle("财经");
        news.setUrl_id(Url.CaiJingId);
        list.add(news);

        news = new News();
        news.setId(5);
        news.setTitle("科技");
        news.setUrl_id(Url.KeJiId);
        list.add(news);

        news = new News();
        news.setId(6);
        news.setTitle("电影");
        news.setUrl_id(Url.DianYingId);
        list.add(news);

        news = new News();
        news.setId(7);
        news.setTitle("汽车");
        news.setUrl_id(Url.QiChiId);
        list.add(news);

        news = new News();
        news.setId(8);
        news.setTitle("笑话");
        news.setUrl_id(Url.XiaoHuaId);
        list.add(news);

        news = new News();
        news.setId(9);
        news.setTitle("游戏");
        news.setUrl_id(Url.YouXiId);
        list.add(news);

        news = new News();
        news.setId(10);
        news.setTitle("时尚");
        news.setUrl_id(Url.ShiShangId);
        list.add(news);

        news = new News();
        news.setId(11);
        news.setTitle("情感");
        news.setUrl_id(Url.QingGanId);
        list.add(news);

        news = new News();
        news.setId(12);
        news.setTitle("电台");
        news.setUrl_id(Url.DianTaiId);
        list.add(news);

        news = new News();
        news.setId(13);
        news.setTitle("NBA");
        news.setUrl_id(Url.NBAId);
        list.add(news);

        return list;
    }
}
