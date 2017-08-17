package com.example.wenxiao.toolbardrawrlyaout.adapter1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wenxiao.toolbardrawrlyaout.DensityUtil;
import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.bean.News;
import com.example.wenxiao.toolbardrawrlyaout.fragment.Fragments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYang on 2017/6/28.
 */

public class StaggeregAdapter extends RecyclerView.Adapter<StaggeregAdapter.MyViewHolder>{
    private List<News> mDatas;
    private LayoutInflater inflater;
    private List<Integer> mHeight;
    private Context context;
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    //声明接口，回调点击方法
    public interface OnItemClickListener{
        //点击
        void onItemClickListener(View view, int position);
        //长按
        void onItemLongClickListener(View view, int position);
    }
    public StaggeregAdapter(Context context, List<News> datas)
    {
        inflater = LayoutInflater.from(context);
        mDatas = datas;
        this.context = context;
        //设置item高度
        mHeight = new ArrayList<>();
        for (int i = 0;i<mDatas.size();i++)
        {
            mHeight.add(DensityUtil.dip2px(context,35));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_staggered_home,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //给Item设定一个随机的高度
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeight.get(position);

        holder.tv.setLayoutParams(lp);
        if (mDatas.get(position).getTitle().equals("头条"))
        {
            //更改字体
            holder.tv.setTextColor(Color.RED);
        }
        holder.tv.setText(mDatas.get(position).getTitle());
        //绑定监听
        //如果设置了回调，则设置点击监听
        if (onItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClickListener(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClickListener(holder.itemView,pos);
                    removeData(pos);
                    return false;
                }
            });
        }
    }
    //添加数据
    public void addData(News data)
    {
        if(!data.getTitle().equals("头条")) {
            mDatas.add(mDatas.size(), data);
        }
        mHeight.add(DensityUtil.dip2px(context,35));
        //通知item更新
        notifyItemInserted(mDatas.size());
    }
    //删除数据
    public void removeData(int position)
    {

            mDatas.remove(position);
        //通知item更新
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }
}
