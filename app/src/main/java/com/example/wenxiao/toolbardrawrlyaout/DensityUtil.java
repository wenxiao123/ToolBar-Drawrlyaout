package com.example.wenxiao.toolbardrawrlyaout;

import android.content.Context;

/**
 * Created by ZhangYang on 2017/6/28.
 */

public class DensityUtil {
    /**
     * 根据手机的分辨率从dp单位转成为px(像素)
     */
    public static int dip2px(Context context,float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale+0.5f);
    }
    /**
     * 根据手机的分辨率从px(像素)单位转成为dp
     */
    public static int px2dip(Context context,float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }
}

