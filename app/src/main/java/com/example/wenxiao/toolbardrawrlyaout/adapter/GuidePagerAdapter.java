package com.example.wenxiao.toolbardrawrlyaout.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ZhangYang on 2017/7/4.
 * 引导页面的适配器
 */

public class GuidePagerAdapter extends PagerAdapter{
    private List<View> views;
    public GuidePagerAdapter(List<View> views)
    {
        this.views = views;
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = views.get(position);
        container.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
