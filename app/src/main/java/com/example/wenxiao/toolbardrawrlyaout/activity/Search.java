package com.example.wenxiao.toolbardrawrlyaout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.application.MyApplication;
import com.example.wenxiao.toolbardrawrlyaout.http.HttpUtil;
import com.example.wenxiao.toolbardrawrlyaout.http.Url;
import com.example.wenxiao.toolbardrawrlyaout.view.SwipeRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class Search extends AppCompatActivity {
    private String text;
    private Map url;
    private int index;
    private Toolbar toolbar;
    private ListView listView;
    private SwipeRefreshView swipeRefreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        toolbar= (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("今日新闻");
        final String content=getIntent().getStringExtra("search");
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
       final List<String> list=new ArrayList();
        list.add("111");
//        list.add(Url.CaiJingId);
//        list.add(Url.CaiPiaoId);
        list.add(Url.YuLeId);
       list.add(Url.TopId);
        //text=list.get(1);
        listView= (ListView)findViewById(R.id.view);
        swipeRefreshLayout = (SwipeRefreshView)findViewById(R.id.srl);
        for(int i=0;i<list.size();i++) {
            text=list.get(i);
           if(HttpUtil.volleyGetJson1(getApplicationContext(), listView, list.get(i),content)==null)
           {
               HttpUtil.volleyGetJson1(getApplicationContext(), listView, list.get(i),content);
           }
        }
            //设置下拉进度的背景颜色，默认是白色
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_purple);
        //设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.yellow,R.color.green,R.color.black);
        //下拉刷新监听事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //正在刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtil.volleyGetJson1(getApplicationContext(),listView,text,content);
                        Toast.makeText(getApplicationContext(),"数据已刷新",Toast.LENGTH_SHORT).show();
                        //加载完数据后设置为不刷新状态，将下拉进度条收起
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
        //上拉加载
        swipeRefreshLayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                index+=20;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtil.pushVolleyGetJson1(getApplicationContext(),listView,index,text,content);
                        Toast.makeText(getApplicationContext(),"加载了20条数据",Toast.LENGTH_SHORT).show();
                        //加载完成后设置为不加载状态，将加载进度收起来
                        swipeRefreshLayout.setLoading(false);
                    }
                },2000);
                for(int i=0;i<list.size();i++) {
                    text=list.get(i);
                    if(HttpUtil.pushVolleyGetJson1(getApplicationContext(),listView,index,text,content)==null)
                    {
                        HttpUtil.pushVolleyGetJson1(getApplicationContext(),listView,index,text,content);
                    }
                }
            }
        });
    }

}




