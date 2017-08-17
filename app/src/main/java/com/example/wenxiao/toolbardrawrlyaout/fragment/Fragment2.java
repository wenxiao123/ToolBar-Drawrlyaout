package com.example.wenxiao.toolbardrawrlyaout.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.activity.MainActivity;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.MyListViewAdapter;
import com.example.wenxiao.toolbardrawrlyaout.bean.New;
import com.example.wenxiao.toolbardrawrlyaout.bean.News;

import java.util.ArrayList;
import java.util.List;

import db.DbHelper;


/**
 * Created by ZhangYang on 2017/6/26.
 */

public class Fragment2 extends Fragment{
    private ListView view2;
    private DbHelper helper;
    private MyListViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view2= (ListView) getView().findViewById(R.id.view2);
        //adapter=new MyListViewAdapter(getView().getContext(),)
        helper = new DbHelper(getContext());
        //通过DBHelper对象获取数据库对象
        helper.getWritableDatabase();
        adapter = new MyListViewAdapter(queryBySQL(),getContext());
        view2.setAdapter(adapter);
    }

    private List<New> queryBySQL() {
        List<New> list = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from shangcang",null);
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url_id"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String img = cursor.getString(cursor.getColumnIndex("img"));
            //Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
            New n=new New(title,url,img,content);
            list.add(n);
        }
        cursor.close();
        db.close();
        return list;
    }
}
