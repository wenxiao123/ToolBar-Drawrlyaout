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
import com.example.wenxiao.toolbardrawrlyaout.bean.New;
import com.example.wenxiao.toolbardrawrlyaout.util.JsonParseUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYang on 2017/6/20.
 * 从网络获取数据接口
 */

public class HttpUtil {
    /**
     * 获取JSON字符串
     * @param context
     */
    private static List<New> newList = new ArrayList<>();
    private static MyListViewAdapter adapter;
    private static MyListViewAdapter1 adapter1;
    public static void pushVolleyGetJson(final Context context, final ListView listView, int index, final String ss)
    {
        //volleyGetJson(context,listView,ss);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jor = new JsonObjectRequest(Url.TopUrl + ss+ "/" + index + Url.endUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        newList.addAll(JsonParseUtil.jsonParse(response,ss));
                        Log.e("size", "====>"+newList.size());
//                        adapter = new MyListViewAdapter(newList,context);
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
    public static void volleyGetJson(final Context context, final ListView listView,final String ss)
    {
        //volleyGetJson(context,listView,ss);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jor = new JsonObjectRequest(Url.TopUrl + ss+ "/" + 0 + Url.endUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //解析JSON
                        newList = JsonParseUtil.jsonParse(response,ss);
                        Log.e("TAG",response+"");
                        Log.e("list",newList.toString() );
                        //给ListView添加适配器
                        adapter = new MyListViewAdapter(newList,context);
                        listView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG",error+"" );
            }
        });
        mQueue.add(jor);
    }
    public static List volleyGetJson1(final Context context, final ListView listView,final String ss,final String aa)
    {
        //volleyGetJson(context,listView,ss);
        final List list1=new ArrayList();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jor = new JsonObjectRequest(Url.TopUrl + ss+ "/" + 0 + "-20.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //解析JSON
                        newList = JsonParseUtil.jsonParse(response,ss);
                        //List list=new ArrayList();
                        for(int i=0;i<newList.size();i++){
                            if(newList.get(i).getTitle().contains(aa)){
                                //newList.remove(i);
                                list1.add(newList.get(i));
                            }
                        }
                       // Log.e("TAG",aa);
                        Log.e("list",newList.toString() );
                        Log.e("list",newList.size()+"" );
                        //给ListView添加适配器
                        adapter1 = new MyListViewAdapter1(list1,context);
                        listView.setAdapter(adapter1);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG",error+"" );
            }
        });
        mQueue.add(jor);
        return list1;
    }
    public static List pushVolleyGetJson1(final Context context, final ListView listView, int index, final String ss,final String aa)
    {
        //volleyGetJson(context,listView,ss);
        final List list=new ArrayList();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jor = new JsonObjectRequest(Url.TopUrl + ss+ "/" + index + "-20.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        newList.addAll(JsonParseUtil.jsonParse(response,ss));
                        for(int i=0;i<newList.size();i++){
                            if(newList.get(i).getTitle().contains(aa)){
                                //newList.remove(i);
                                list.add(newList.get(i));
                            }}
                        Log.e("size", "====>"+newList.size());
//                        adapter = new MyListViewAdapter(newList,context);
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
        return list;
    }
    }

