package com.example.wenxiao.toolbardrawrlyaout.util;

import com.example.wenxiao.toolbardrawrlyaout.bean.Date;
import com.example.wenxiao.toolbardrawrlyaout.bean.Tv;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYang on 2017/6/20.
 */

public class JsonParseUtil4 {
    /**
     * 解析JSON字符串
     */
    public static List<Date> jsonParse(JSONObject jsonObject, String ss)
    {
        Gson gson = new Gson();
        List<Date> news = new ArrayList<>();
        try {
            JSONArray ja = jsonObject.getJSONArray("forecast");
            for(int i = 0;i<ja.length();i++)
            {
                news.add(gson.fromJson(ja.getJSONObject(i).toString(),Date.class));
            }
            return news;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
