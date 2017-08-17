package com.example.wenxiao.toolbardrawrlyaout.http;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.MyListViewAdapter3;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.Myadpter;
import com.example.wenxiao.toolbardrawrlyaout.bean.Date;
import com.example.wenxiao.toolbardrawrlyaout.bean.Img;
import com.example.wenxiao.toolbardrawrlyaout.util.JsonParseUtil4;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class HttpUtil4 {
    /**
     * 获取JSON字符串
     * @param context
     */
    private static List<Date> newList = new ArrayList<>();
    private static Myadpter adapter;
    public static void volleyGetJson(final Context context, final ListView listView)
    {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jor = new JsonObjectRequest("http://wthrcdn.etouch.cn/weather_mini?city=%E6%88%90%E9%83%BD", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //解析JSON
                       try {
                           Gson gson = new Gson();
                           JSONArray ja = response.getJSONArray("forecast");
                           for (int i = 0; i < ja.length(); i++) {
                               newList.add(gson.fromJson(ja.getJSONObject(i).toString(), Date.class));
                           }
                           Log.e("size", "====>" + newList.size());
                           adapter = new Myadpter(newList, context);
                           listView.setAdapter(adapter);
                           Log.e("YYY", newList.toString());
                       }catch (JSONException e) {
                           e.printStackTrace();
                       }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG1",error+"" );
            }
        });
        mQueue.add(jor);
    }
    }

