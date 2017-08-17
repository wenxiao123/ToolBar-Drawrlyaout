package com.example.wenxiao.toolbardrawrlyaout.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.activity.MainActivity;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.Myadpter;
import com.example.wenxiao.toolbardrawrlyaout.bean.Date;
import com.example.wenxiao.toolbardrawrlyaout.http.HttpUtil1;
import com.example.wenxiao.toolbardrawrlyaout.http.HttpUtil2;
import com.example.wenxiao.toolbardrawrlyaout.view.SwipeRefreshView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZhangYang on 2017/6/26.
 */

public class Fragment4 extends Fragment{
    private static Myadpter adapter;
    private ListView view3;
    private int index=-1;
    private SwipeRefreshView swipeRefreshLayout;
    final private  List<String> list=new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment4,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        view3= (ListView) getView().findViewById(R.id.view3);
        swipeRefreshLayout = (SwipeRefreshView)getView(). findViewById(R.id.srl);
        HttpUtil2.volleyGetJson(getView().getContext(),view3,list.get(0));
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
                        HttpUtil2.volleyGetJson(getView().getContext(),view3,list.get(0));
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
                if(index==4){
                    index=0;
                }
                else {
                index=index+1;}
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtil2.pushVolleyGetJson(getContext(),view3,list.get(index));
                        Toast.makeText(getContext(),"加载了20条数据",Toast.LENGTH_SHORT).show();
                        //加载完成后设置为不加载状态，将加载进度收起来
                        swipeRefreshLayout.setLoading(false);
                    }
                },2000);
            }
        });
    }

    private void init() {
        list.add("4GJ60096/42358");
        list.add("54GI0096/42010");
        list.add("54GJ0096/42599");
        list.add("54GK0096/42262");
        list.add("54GM0096/39683");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    HttpUtil1.volleyGetJson(getContext(),view3);
                    Toast.makeText(getContext(),"数据已刷新",Toast.LENGTH_SHORT).show();
                    //加载完数据后设置为不刷新状态，将下拉进度条收起
                    swipeRefreshLayout.setRefreshing(false);
                }
            },0);
        }
    }


}
