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
import com.example.wenxiao.toolbardrawrlyaout.activity.New_one;
import com.example.wenxiao.toolbardrawrlyaout.activity.Search;
import com.example.wenxiao.toolbardrawrlyaout.bean.New;
import com.example.wenxiao.toolbardrawrlyaout.util.ImageLoaderDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by ZhangYang on 2017/6/21.
 */

public class MyListViewAdapter1 extends BaseAdapter{
    private List<New> news;
    private LayoutInflater inflater;
    //private BitmapCache bitmapCache;
    private DisplayImageOptions options;
    private ImageLoaderDisplayListener listener = new ImageLoaderDisplayListener();
    public MyListViewAdapter1(List<New> news, Context context) {
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
        final  New n = news.get(position);
        holder.title.setText(n.getTitle());
        holder.content.setText(n.getSource());
        holder.img.setImageResource(R.mipmap.ic_launcher);
        //bitmapCache.displayImg(n.getImgsrc(),holder.img);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(n.getImgsrc(),holder.img,options,listener);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(inflater.getContext(), New_one.class);
                intent.putExtra("news",n.getUrl_3w());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //context.startActivity(intent);
                inflater.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
