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

import com.bumptech.glide.Glide;
import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.activity.New_one;
import com.example.wenxiao.toolbardrawrlyaout.activity.Tvshow;
import com.example.wenxiao.toolbardrawrlyaout.bean.New;
import com.example.wenxiao.toolbardrawrlyaout.bean.Tv;
import com.example.wenxiao.toolbardrawrlyaout.http.CircleImageView;
import com.example.wenxiao.toolbardrawrlyaout.util.ImageLoaderDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by ZhangYang on 2017/6/21.
 */

public class MyListViewAdapter2 extends BaseAdapter{
    private List<Tv> news;
    private LayoutInflater inflater;
    //private BitmapCache bitmapCache;
    private DisplayImageOptions options;
    private ImageLoaderDisplayListener listener = new ImageLoaderDisplayListener();
    public MyListViewAdapter2(List<Tv> news, Context context) {
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
        private JCVideoPlayerStandard player_list_video;
        private TextView title,t1,t2;
        private CircleImageView cir;
    }
    ViewHolder holder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  Tv n = news.get(position);
        if (convertView==null)
        {
            convertView = inflater.inflate(R.layout.item1,null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.text);
            holder.t1 = (TextView) convertView.findViewById(R.id.t1);
            holder.t2 = (TextView) convertView.findViewById(R.id.t2);
            holder.player_list_video=(JCVideoPlayerStandard) convertView.findViewById(R.id.player_list_video);
            holder.cir=(CircleImageView) convertView.findViewById(R.id.cir);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        boolean setUp = holder.player_list_video.setUp(n.getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, n.getTitle());
        if (setUp)
        {
            Glide.with( inflater.getContext()).load(n.getCover())
                    .into(holder.player_list_video.thumbImageView);
        }
        //holder.title.setText(n.getTitle());
        holder.t1.setText(n.getTopicName());
        holder.t2.setText(n.getPtime());
        Glide.with(inflater.getContext()).load(n.getTopicImg()).into(holder.cir);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss=n.getMp4_url();
                String aa=n.getTitle();
                String bb=n.getCover();
                Intent intent=new Intent(inflater.getContext(), Tvshow.class);
                intent.putExtra("title",aa);
                intent.putExtra("mp4",ss);
                intent.putExtra("mp5",bb);
                inflater.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
