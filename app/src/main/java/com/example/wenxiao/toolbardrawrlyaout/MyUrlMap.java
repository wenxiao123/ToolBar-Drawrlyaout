package com.example.wenxiao.toolbardrawrlyaout;

import com.example.wenxiao.toolbardrawrlyaout.http.Url;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weian on 2017/6/19.
 */

public class MyUrlMap {
    private Map<String,String> url;
    public Map getMap(){
        url = new HashMap<>();
        url.put("头条", Url.TopId);
        url.put("娱乐",Url.YuLeId );
        url.put("体育",Url.TiYuId );
        url.put("足球",Url.FootId);
        url.put("财经",Url.CaiJingId );
        url.put("彩票",Url.CaiPiaoId);
        url.put("NBA",Url.NBAId);
        url.put("科技",Url.KeJiId );
        url.put("汽车",Url.QiChiId);
        return url;
    }
}
