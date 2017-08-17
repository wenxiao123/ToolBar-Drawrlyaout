package com.example.wenxiao.toolbardrawrlyaout.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.adapter.MyFragmentViewPagerAdapter;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.Myadpter;
import com.example.wenxiao.toolbardrawrlyaout.application.MyApplication;
import com.example.wenxiao.toolbardrawrlyaout.bean.Date;
import com.example.wenxiao.toolbardrawrlyaout.bean.News;
import com.example.wenxiao.toolbardrawrlyaout.fragment.Fragment2;
import com.example.wenxiao.toolbardrawrlyaout.fragment.Fragment3;
import com.example.wenxiao.toolbardrawrlyaout.fragment.Fragment4;
import com.example.wenxiao.toolbardrawrlyaout.fragment.Fragments;
import com.example.wenxiao.toolbardrawrlyaout.http.HttpUtil4;
import com.example.wenxiao.toolbardrawrlyaout.http.Url;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sharesdk.onekeyshare.OnekeyShare;
import db.DbHelper;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DbHelper helper;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private MyApplication app;
    private EditText edit;
    private Button editbtn,exit;
    /**
     * ActionBarDrawerToggle是drawerLayout.DrawerListener实现，和NavigationDrawer搭配使用
     * 推荐使用这个方法，符合Android design规范
     */
    private ActionBarDrawerToggle drawerToggle;
    private Button btn1,btn2,btn3,btn4;
    private ImageView img1,img2,img3,img4;
    private FragmentManager fm;
    private Fragment f1,f2,f3,f4,f5;
    private static Myadpter adapter;
    private ListView list;
    private static List<Date> newList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app= (MyApplication) getApplication();
        helper = new DbHelper(this);
        //通过DBHelper对象获取数据库对象
        helper.getWritableDatabase();
        queryBySQL();
        init();
        init1();
    }

    private void init1() {
        list= (ListView)findViewById(R.id.list);
        RequestQueue mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest("http://wthrcdn.etouch.cn/weather_mini?city=%E6%88%90%E9%83%BD", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //解析JSON
                        try {
                            Gson gson = new Gson();
                            JSONObject jsonObject=response.getJSONObject("data");
                            JSONArray ja = jsonObject.getJSONArray("forecast");
                            for (int i = 0; i < ja.length(); i++) {
                                newList.add(gson.fromJson(ja.getJSONObject(i).toString(), Date.class));
                            }
                            Log.e("size", "====>" + newList.size());
                            adapter = new Myadpter(newList,MainActivity.this);
                            list.setAdapter(adapter);
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
      exit= (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void queryBySQL() {
        List<News> list = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from new",null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("title"));
            String tel = cursor.getString(cursor.getColumnIndex("url_id"));
            News news = new News(id,name,tel);
            list.add(news);
        }
        List<News> list1 = new ArrayList<>();
        cursor = db.rawQuery("select * from news",null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("title"));
            String tel = cursor.getString(cursor.getColumnIndex("url_id"));
            News news1 = new News(id,name,tel);
            list1.add(news1);
        }
        app.setContainsList(list);
        app.setMoreContainsList(list1);
        cursor.close();
        db.close();
    }

    private void init() {
        img1= (ImageView) findViewById(R.id.btn_fragment11);
        img2= (ImageView) findViewById(R.id.btn_fragment21);
        img3= (ImageView) findViewById(R.id.btn_fragment31);
        img4= (ImageView) findViewById(R.id.btn_fragment41);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("今日新闻");
        //设置ToolBar为标题栏
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        //设置显示旋转菜单
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //activityBar开关
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        //同步开关，如果不写的话，滑动开关一直就是一个状态，不会改变
        drawerToggle.syncState();
        //添加监听
        drawerLayout.addDrawerListener(drawerToggle);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //设置ToolBar的标题
                toolbar.setTitle("菜单");
                //Toast.makeText(MainActivity.this, edit.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("今日新闻");
            }
        });
        f1 = new NewsFragment();
        f2 = new Fragment2();
        f3 = new Fragment3();
        f4 = new Fragment4();
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.dl_container,f2).commit();
        fm.beginTransaction().add(R.id.dl_container,f3).commit();
        fm.beginTransaction().add(R.id.dl_container,f4).commit();
        fm.beginTransaction().add(R.id.dl_container,f1).commit();
        btn1 = (Button) findViewById(R.id.btn_fragment1);
        btn2 = (Button) findViewById(R.id.btn_fragment2);
        btn3 = (Button) findViewById(R.id.btn_fragment3);
        btn4 = (Button) findViewById(R.id.btn_fragment4);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        edit= (EditText) findViewById(R.id.edit);
        editbtn= (Button) findViewById(R.id.editbtn);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search= String.valueOf(edit.getText());
                Intent intent=new Intent(MainActivity.this,Search.class);
                intent.putExtra("search",search);
                startActivity(intent);
                //Toast.makeText(MainActivity.this,aa,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_fragment1:
                fm.beginTransaction().replace(R.id.dl_container,f1).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.btn_fragment11:
                fm.beginTransaction().replace(R.id.dl_container,f1).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.btn_fragment2:
                fm.beginTransaction().replace(R.id.dl_container,f2).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.btn_fragment21:
                fm.beginTransaction().replace(R.id.dl_container,f2).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.btn_fragment3:
                fm.beginTransaction().replace(R.id.dl_container,f4).commit();
                break;
            case R.id.btn_fragment31:
                fm.beginTransaction().replace(R.id.dl_container,f4).commit();
                break;
            case R.id.btn_fragment4:
                fm.beginTransaction().replace(R.id.dl_container,f3).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.btn_fragment41:
                fm.beginTransaction().replace(R.id.dl_container,f3).commit();
                drawerLayout.closeDrawers();
                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from new");
        db.execSQL("delete from news");
        ContentValues values = new ContentValues();
        for(int i=0;i<app.getContainsList().size();i++) {
            //values.put("id", app.getContainsList().get(i).getId());
            values.put("title", app.getContainsList().get(i).getTitle());
            values.put("url_id", app.getContainsList().get(i).getUrl_id());
            db.insert("new", null, values);
        }
        for(int i=0;i<app.getMoreContainsList().size();i++) {
            //values.put("id", app.getContainsList().get(i).getId());
            values.put("title", app.getMoreContainsList().get(i).getTitle());
            values.put("url_id", app.getMoreContainsList().get(i).getUrl_id());
            db.insert("news", null, values);
        }
        db.close();
    }
}

