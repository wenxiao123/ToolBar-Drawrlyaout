package com.example.wenxiao.toolbardrawrlyaout.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.wenxiao.toolbardrawrlyaout.R;

/**
 * Created by ZhangYang on 2017/7/4.
 */

public class WelcomeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
               if (isFirstRun())
               {
                   //应用程序第一次运行，跳转到引导页面
                   startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
               }else {
                   //如果不是第一次运行，跳转到主界面
                   startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
               }
                //关闭自己
                finish();
                return false;
            }
        }).sendEmptyMessageDelayed(0,2500);
    }
    //判断是否是第一次运行
    private boolean isFirstRun()
    {
        //SharedPreferences存储共享变量的文件路径位于“/data/data/应用程序包/shared_prefs”目录下
        //获取Preferences,如果没有则自动创建
        SharedPreferences preferences = getSharedPreferences("app",MODE_PRIVATE);
        boolean isFirst = preferences.getBoolean("first_run",true);
        //如果是第一次运行
        if (isFirst)
        {
            //改变为不是第一次
            preferences.edit().putBoolean("first_run",false).commit();
        }
        return isFirst;
    }
}
