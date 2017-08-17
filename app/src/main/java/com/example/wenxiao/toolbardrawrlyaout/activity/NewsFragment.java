package com.example.wenxiao.toolbardrawrlyaout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.adapter.MyFragmentViewPagerAdapter;
import com.example.wenxiao.toolbardrawrlyaout.application.MyApplication;
import com.example.wenxiao.toolbardrawrlyaout.bean.Contains;
import com.example.wenxiao.toolbardrawrlyaout.bean.News;
import com.example.wenxiao.toolbardrawrlyaout.fragment.Fragments;
import com.example.wenxiao.toolbardrawrlyaout.http.Url;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class NewsFragment extends Fragment {
    private ImageView textView;
    /**
     * ActionBarDrawerToggle是drawerLayout.DrawerListener实现，和NavigationDrawer搭配使用
     * 推荐使用这个方法，符合Android design规范
     */
    private ActionBarDrawerToggle drawerToggle;
    private Button btn1,btn2,btn3,btn4;
    private FragmentManager fm;
    private ViewPager viewPager;
    //声明Fragment集合
    private List<Fragment> fragments;
    private TabLayout tableLayout;
    private List<News> title;
    private MyApplication app;
    //静态变量，用来判断是否需要刷新
    public static int isRefresh ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.actity_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initControls();
    }
    private void initControls() {
        app= (MyApplication) getActivity().getApplication();
        tableLayout= (TabLayout)getView().findViewById(R.id.TabLayout);
        viewPager = (ViewPager)getView().findViewById(R.id.fragment_ViewPager);
        title=app.getContainsList();
        fragments = new ArrayList<>();
        //初始化Fragment
        for (int i = 0;i< title.size();i++)
        {
            Bundle data = new Bundle();
            data.putString("text", title.get(i).getUrl_id());
            Fragments newFragment = new Fragments();
            //通过setArguments来传递数据
            newFragment.setArguments(data);
            fragments.add(newFragment);
        }
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for(int i=0;i<title.size();i++){
            tableLayout.addTab(tableLayout.newTab().setText(title.get(i).getTitle()));
        }
        //创建适配器
        MyFragmentViewPagerAdapter adapter =
                new MyFragmentViewPagerAdapter(getChildFragmentManager(),fragments,title);
        //添加适配器
        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);
        textView= (ImageView)getView().findViewById(R.id.imageView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),otherActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRefresh == 1)
        {
            isRefresh = 0;
            initControls();
        }
    }
}
