package com.example.wenxiao.toolbardrawrlyaout.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.adapter1.StaggeregAdapter;
import com.example.wenxiao.toolbardrawrlyaout.application.MyApplication;
import com.example.wenxiao.toolbardrawrlyaout.bean.Contains;
import com.example.wenxiao.toolbardrawrlyaout.bean.MoreContains;
import com.example.wenxiao.toolbardrawrlyaout.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class otherActivity extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView1;
    private List<News> mDatas,mDatas1;
    private StaggeregAdapter adapter,adapter1;
    private TextView close;
    private MyApplication app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pindao);
        initData();
        close= (TextView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsFragment.isRefresh=1;
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
        adapter = new StaggeregAdapter(this,mDatas);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(6,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView1 = (RecyclerView) findViewById(R.id.id_recyclerView1);
        adapter1 = new StaggeregAdapter(this,mDatas1);
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(6,StaggeredGridLayoutManager.VERTICAL));
        recyclerView1.setAdapter(adapter1);
//        //设置动画
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        initEvent();
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new StaggeregAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                adapter1.addData(mDatas.get(position));
                if(!mDatas.get(position).getTitle().equals("头条")) {
                    adapter.removeData(position);
               }else{
                    Toast.makeText(otherActivity.this,"头条不能删除",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(otherActivity.this,"长按点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });
        adapter1.setOnItemClickListener(new StaggeregAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                adapter.addData(mDatas1.get(position));
                adapter1.removeData(position);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(otherActivity.this,"长按点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        app= (MyApplication) getApplication();
        //mDatas = new ArrayList<>();
        mDatas=app.getContainsList();
        //mDatas= Contains.getData();
        mDatas1 = app.getMoreContainsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}

