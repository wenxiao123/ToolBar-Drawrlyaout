package com.example.wenxiao.toolbardrawrlyaout.application;

import android.app.Application;

import com.example.wenxiao.toolbardrawrlyaout.bean.Contains;
import com.example.wenxiao.toolbardrawrlyaout.bean.MoreContains;
import com.example.wenxiao.toolbardrawrlyaout.bean.News;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class MyApplication extends Application {
    private String search="11";

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    //频道Title集合
    private List<News> containsList,moreContainsList;

    public List<News> getContainsList() {
        return containsList;
    }

    public void setContainsList(List<News> containsList) {
        this.containsList = containsList;
    }

    public List<News> getMoreContainsList() {
        return moreContainsList;
    }

    public void setMoreContainsList(List<News> moreContainsList) {
        this.moreContainsList = moreContainsList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化频道Title集合
        containsList = Contains.getData();
        moreContainsList = MoreContains.getData();
        //初始化Image-Loader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .threadPoolSize(3)//默认
                .threadPriority(Thread.NORM_PRIORITY-2)//设置线程的优先级
                //当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认缓存多个不同大小的相同图片
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheExtraOptions(480,800)
                .memoryCache(new WeakMemoryCache())
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//设置缓存文件的名字
                .discCacheFileCount(100)//缓存文件的最大个数
                .tasksProcessingOrder(QueueProcessingType.LIFO)//设置图片下载和显示的工作队列顺序
                .enableLogging()//是否打印日志用于检查错误
                .build();
        //按照上面的配置信息获取ImageLoader对象
        ImageLoader.getInstance().init(config);
    }
}
