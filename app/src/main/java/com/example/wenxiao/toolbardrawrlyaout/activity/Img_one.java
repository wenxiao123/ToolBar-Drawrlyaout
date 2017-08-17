package com.example.wenxiao.toolbardrawrlyaout.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wenxiao.toolbardrawrlyaout.R;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class Img_one extends AppCompatActivity {
    private WebView web;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_one);
        web= (WebView) findViewById(R.id.web);
        String mp4=getIntent().getStringExtra("mp4");
        Log.e("TAG", "onCreate: "+mp4);
        //webView加载web资源
        web.loadUrl(mp4);
        //覆盖webView默认使用第三方或者系统默认浏览器打开网页的行为
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //返回true的时候是使用webView去打开
                return true;
            }
        });
    }
}
