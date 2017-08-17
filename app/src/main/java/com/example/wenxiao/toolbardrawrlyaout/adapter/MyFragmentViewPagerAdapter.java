package com.example.wenxiao.toolbardrawrlyaout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.example.wenxiao.toolbardrawrlyaout.bean.News;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class MyFragmentViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<News> tab;

    public MyFragmentViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<News> tab) {
        super(fm);
        this.fragments = fragments;
        this.tab = tab;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab.get(position).getTitle();
    }

}