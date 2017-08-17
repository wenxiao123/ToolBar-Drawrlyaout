package com.example.wenxiao.toolbardrawrlyaout.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wenxiao.toolbardrawrlyaout.R;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class ImageActivity extends AppCompatActivity {
    private ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        init();
    }

    private void init() {
        img= (ImageView) findViewById(R.id.image_btn);
        String Url=getIntent().getStringExtra("img");
        Glide.with(this).load(Url).into(img);
    }
}
