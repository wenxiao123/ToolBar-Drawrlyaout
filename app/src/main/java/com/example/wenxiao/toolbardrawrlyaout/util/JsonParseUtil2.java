package com.example.wenxiao.toolbardrawrlyaout.util;

import android.util.Log;

import com.example.wenxiao.toolbardrawrlyaout.bean.Img;
import com.example.wenxiao.toolbardrawrlyaout.bean.Tv;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYang on 2017/6/20.
 */

public class JsonParseUtil2 {
    /**
     * 解析JSON字符串
     */
    public static List<Img> jsonParse(JSONArray response) {
        Log.e("TTT",response+"");
        List<Img> list=new ArrayList<>();
        for(int i = 0;i<response.length();i++)
        {
            try {
                JSONObject jsonObject = response.getJSONObject(i);
                String desc = jsonObject.getString("desc");
                String createdate = jsonObject.getString("createdate");
                String scover = jsonObject.getString("scover");
                String seturl = jsonObject.getString("seturl");
                String sename = jsonObject.getString("setname");
                list.add(new Img(desc,createdate,scover,seturl,sename));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
            return list;
          }

    }

