package com.example.wenxiao.toolbardrawrlyaout.http;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.MyListViewAdapter;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.MyListViewAdapter1;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.MyListViewAdapter2;
import com.example.wenxiao.toolbardrawrlyaout.bean.New;
import com.example.wenxiao.toolbardrawrlyaout.bean.Tv;
import com.example.wenxiao.toolbardrawrlyaout.util.JsonParseUtil;
import com.example.wenxiao.toolbardrawrlyaout.util.JsonParseUtil1;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYang on 2017/6/20.
 * 从网络获取数据接口
 */

public class HttpUtil1 {
    /**
     * 获取JSON字符串
     * @param context
     */
    private static List<Tv> newList = new ArrayList<>();
    private static MyListViewAdapter2 adapter;
    private static MyListViewAdapter1 adapter1;
    public static void pushVolleyGetJson(final Context context, final ListView listView, int index)
    {
        //volleyGetJson(context,listView,ss);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jor = new JsonObjectRequest("http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/"+index+"-10.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //解析JSON
                        String ss="V9LG4B3A0";
                        newList.addAll(JsonParseUtil1.jsonParse(response,ss));
                        Log.e("size", "====>"+newList.size());
//                        adapter = new MyListViewAdapter2(newList,context);
//                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        Log.e("list",newList.toString() );

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG",error+"" );
            }
        });
        mQueue.add(jor);
    }
    public static void volleyGetJson(final Context context, final ListView listView)
    {
        //volleyGetJson(context,listView,ss);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jor = new JsonObjectRequest("http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //解析JSON
                        String ss="V9LG4B3A0";
                        newList = JsonParseUtil1.jsonParse(response,ss);
                        Log.e("TAG",response+"");
                        Log.e("list",newList.toString() );
                        //给ListView添加适配器
                        adapter = new MyListViewAdapter2(newList,context);
                        listView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAf",error+"" );
            }
        });
        mQueue.add(jor);
    }
    }

