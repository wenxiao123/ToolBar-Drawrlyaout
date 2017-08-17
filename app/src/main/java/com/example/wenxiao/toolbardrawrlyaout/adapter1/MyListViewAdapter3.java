package com.example.wenxiao.toolbardrawrlyaout.adapter1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.activity.Img_one;
import com.example.wenxiao.toolbardrawrlyaout.activity.Tvshow;
import com.example.wenxiao.toolbardrawrlyaout.bean.Img;
import com.example.wenxiao.toolbardrawrlyaout.bean.Tv;
import com.example.wenxiao.toolbardrawrlyaout.util.ImageLoaderDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by ZhangYang on 2017/6/21.
 */

public class MyListViewAdapter3 extends BaseAdapter{
    private List<Img> news;
    private LayoutInflater inflater;
    //private BitmapCache bitmapCache;
    private DisplayImageOptions options;
    private ImageLoaderDisplayListener listener = new ImageLoaderDisplayListener();
    public MyListViewAdapter3(List<Img> news, Context context) {
        this.news = news;
        inflater = LayoutInflater.from(context);
        //bitmapCache = new BitmapCache();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)//设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误时显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)//设置图片加载/解码过程中错误时显示的图片
                .cacheInMemory(true)//是否要缓存到内存中
                .cacheOnDisc(true)//是否缓存到SD卡上
                .displayer(new RoundedBitmapDisplayer(0))
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .build();

    }
    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        private ImageView img;
        private TextView title,content;
    }
    ViewHolder holder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView = inflater.inflate(R.layout.item,null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.img = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        final  Img n = news.get(position);
        holder.title.setText(n.getSetname());
        holder.content.setText(n.getCreatedate());
        holder.img.setImageResource(R.mipmap.ic_launcher);
        //bitmapCache.displayImg(n.getImgsrc(),holder.img);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(n.getScover(),holder.img,options,listener);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String ss=n.getSeturl();
//                String aa=n.getTopicDesc();
                Intent intent=new Intent(inflater.getContext(), Img_one.class);
//                intent.putExtra("title",aa);
                intent.putExtra("mp4",ss);
                inflater.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
