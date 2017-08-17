package com.example.wenxiao.toolbardrawrlyaout.http;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.MyListViewAdapter1;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.MyListViewAdapter2;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.MyListViewAdapter3;
import com.example.wenxiao.toolbardrawrlyaout.bean.Img;
import com.example.wenxiao.toolbardrawrlyaout.util.JsonParseUtil;
import com.example.wenxiao.toolbardrawrlyaout.util.JsonParseUtil1;
import com.example.wenxiao.toolbardrawrlyaout.util.JsonParseUtil2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYang on 2017/6/20.
 * 从网络获取数据接口
 */

public class HttpUtil2 {
    /**
     * 获取JSON字符串
     * @param context
     */
    private static List<Img> newList = new ArrayList<>();
    private static MyListViewAdapter3 adapter;
    public static void volleyGetJson(final Context context, final ListView listView, String index)
    {
        //volleyGetJson(context,listView,ss);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonRequest jor = new JsonArrayRequest("http://c.3g.163.com/photo/api/morelist/0096/"+index+".json", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                newList = JsonParseUtil2.jsonParse(response);
                Log.e("TAG",response+"");
                Log.e("list",newList.toString() );
                //给ListView添加适配器
                adapter = new MyListViewAdapter3(newList,context);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "onErrorResponse: "+error);
            }
        });
        mQueue.add(jor);
    }
    public static void pushVolleyGetJson(final Context context, final ListView listView, String index)
    {
        //volleyGetJson(context,listView,ss);
        //volleyGetJson(context,listView,ss);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonRequest jor = new JsonArrayRequest("http://c.3g.163.com/photo/api/morelist/0096/"+index+".json", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                newList.addAll(JsonParseUtil2.jsonParse(response));
                Log.e("TAG",response+"");
                Log.e("list",newList.toString() );
                //给ListView添加适配器
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "onErrorResponse: "+error);
            }
        });
        mQueue.add(jor);
    }
    }

