package com.example.wenxiao.toolbardrawrlyaout.adapter1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wenxiao.toolbardrawrlyaout.R;
import com.example.wenxiao.toolbardrawrlyaout.bean.Date;
import com.example.wenxiao.toolbardrawrlyaout.bean.New;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class Myadpter extends BaseAdapter{
    private List<Date> news;
    private LayoutInflater inflater;
    public Myadpter(List<Date> news, Context context) {
        this.news = news;
        this.inflater =  LayoutInflater.from(context);
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
        private TextView date, high, fengli, low, fengxiang, type;
    }
    ViewHolder holder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView = inflater.inflate(R.layout.item3,null);
            holder = new ViewHolder();
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.high = (TextView) convertView.findViewById(R.id.high);
            holder.fengli = (TextView) convertView.findViewById(R.id.fengli);
            holder.low = (TextView) convertView.findViewById(R.id.low);
            holder.fengxiang = (TextView) convertView.findViewById(R.id.fengxiang);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            convertView.setTag(holder);
        }else
        {
            holder =(ViewHolder) convertView.getTag();
        }
        final  Date n = news.get(position);
        holder.date.setText(n.getDate());
        holder.high.setText(n.getHigh());
        holder.fengli.setText(n.getFengli());
        holder.low.setText(n.getLow());
        holder.fengxiang.setText(n.getFengxiang());
        holder.type.setText(n.getType());
        return convertView;
    }
}
