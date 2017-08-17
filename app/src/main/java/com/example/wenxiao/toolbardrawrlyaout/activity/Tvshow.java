package com.example.wenxiao.toolbardrawrlyaout.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wenxiao.toolbardrawrlyaout.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class Tvshow extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv);
        String ss=getIntent().getStringExtra("mp4");
        String aa=getIntent().getStringExtra("title");
        String bb=getIntent().getStringExtra("mp5");
        text= (TextView) findViewById(R.id.text);
        text.setText(aa);
        JCVideoPlayerStandard player = (JCVideoPlayerStandard) findViewById(R.id.player_list_video);
        boolean setUp = player.setUp(ss, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp)
        {
            Glide.with(this).load(bb)
                    .into(player.thumbImageView);
        }
    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress())
        {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
