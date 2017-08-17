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

import com.example.wenxiao.toolbardrawrlyaout.activity.New_one;
import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.bean.New;
import com.example.wenxiao.toolbardrawrlyaout.util.BitmapCache;
import com.example.wenxiao.toolbardrawrlyaout.util.ImageLoaderDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by ZhangYang on 2017/6/21.
 */

public class MyListViewAdapter extends BaseAdapter{
    //item的类型
    private final int VIEWTYPE_TOP = 0;
    private final int VIEWTYPE_CONTENT = 1;
    private final int VIEWTYPE_MID = 2;
    private final int VIEWTYPE_COUNT = 3;
    //新闻集合
    private List<New> news;
    private LayoutInflater inflater;
    //private BitmapCache bitmapCache;
    private DisplayImageOptions options;
    private ImageLoaderDisplayListener listener = new ImageLoaderDisplayListener();
    public MyListViewAdapter(List<New> news, Context context) {
        this.news = news;
        inflater = LayoutInflater.from(context);
        //bitmapCache = new BitmapCache(context);
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)//设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.guide_1)//设置图片Uri为空或是错误时显示的图片
                .showImageOnFail(R.mipmap.guide_1)//设置图片加载/解码过程中错误时显示的图片
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

    @Override
    public int getViewTypeCount() {
        return VIEWTYPE_COUNT;
    }
    //设置item类型
    @Override
    public int getItemViewType(int position) {
        New n  = news.get(position);
        if (n.getHasHead()==1 && n.getHasImg() == 1)
        {
            return VIEWTYPE_TOP;
        }
        if (n.getImgType() == 1)
        {
            return  VIEWTYPE_MID;
        }
        return VIEWTYPE_CONTENT;
    }

    class ViewHolder{
        private ImageView img;
        private TextView title,content,time;
    }
    ViewHolder holder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            holder = new ViewHolder();
            //获取item的类型
            switch (getItemViewType(position))
            {
                case VIEWTYPE_CONTENT:
                    convertView = inflater.inflate(R.layout.item,parent,false);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    holder.content = (TextView) convertView.findViewById(R.id.content);
                    holder.time = (TextView) convertView.findViewById(R.id.time);
                    holder.img = (ImageView) convertView.findViewById(R.id.icon);
                    convertView.setTag(holder);
                    break;
                case VIEWTYPE_TOP:
                    convertView = inflater.inflate(R.layout.item_big,parent,false);
                    holder.title = (TextView) convertView.findViewById(R.id.title_big);
                    holder.content = (TextView) convertView.findViewById(R.id.resourse_big);
                    holder.time = (TextView) convertView.findViewById(R.id.count_big);
                    holder.img = (ImageView) convertView.findViewById(R.id.image_big);
                    convertView.setTag(holder);
                    break;
                case VIEWTYPE_MID:
                    convertView = inflater.inflate(R.layout.item_mid,parent,false);
                    holder.title = (TextView) convertView.findViewById(R.id.title_mid);
                    holder.content = (TextView) convertView.findViewById(R.id.resourse_mid);
                    holder.time = (TextView) convertView.findViewById(R.id.count_mid);
                    holder.img = (ImageView) convertView.findViewById(R.id.image_mid);
                    convertView.setTag(holder);
                    break;
            }
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
       final New n = news.get(position);
        holder.title.setText(n.getTitle());
        holder.content.setText(n.getSource());
        holder.time.setText(getNumber(n.getReplyCount()));
        //bitmapCache.displayImg(n.getImgsrc(),holder.img);
        //获得ImageLoader对象
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(n.getImgsrc(),holder.img,options,listener);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(inflater.getContext(),New_one.class);
                intent.putExtra("title",n.getTitle());
                intent.putExtra("news",n.getUrl_3w());
                intent.putExtra("img",n.getImgsrc());
                intent.putExtra("content",n.getSource());
                inflater.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
    /**
     * 换算跟帖数字
     */
    private String getNumber(String str)
    {
        int i = 0;
        if (str == null)
        {

        }else
        {
            i = Integer.parseInt(str);
            if (i>9999)
            {
                float f = i/10000;
                return  f+"万跟帖";
            }
        }
        return i+"跟帖";
    }
}
