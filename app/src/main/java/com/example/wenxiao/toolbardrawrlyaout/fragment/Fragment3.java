package com.example.wenxiao.toolbardrawrlyaout.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.http.HttpUtil;
import com.example.wenxiao.toolbardrawrlyaout.http.HttpUtil1;
import com.example.wenxiao.toolbardrawrlyaout.view.SwipeRefreshView;


/**
 * Created by ZhangYang on 2017/6/26.
 */

public class Fragment3 extends Fragment{
    private ListView view2;
    private int index;
    private SwipeRefreshView swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view2= (ListView) getView().findViewById(R.id.view2);
        swipeRefreshLayout = (SwipeRefreshView)getView(). findViewById(R.id.srl);
        HttpUtil1.volleyGetJson(getView().getContext(),view2);
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
                        HttpUtil1.volleyGetJson(getView().getContext(),view2);
                        Toast.makeText(getContext(),"数据已刷新",Toast.LENGTH_SHORT).show();
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
                        HttpUtil1.pushVolleyGetJson(getContext(),view2,index);
                        Toast.makeText(getContext(),"加载了20条数据",Toast.LENGTH_SHORT).show();
                        //加载完成后设置为不加载状态，将加载进度收起来
                        swipeRefreshLayout.setLoading(false);
                    }
                },2000);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    HttpUtil1.volleyGetJson(getContext(),view2);
                    Toast.makeText(getContext(),"数据已刷新",Toast.LENGTH_SHORT).show();
                    //加载完数据后设置为不刷新状态，将下拉进度条收起
                    swipeRefreshLayout.setRefreshing(false);
                }
            },0);
        }
    }


}

