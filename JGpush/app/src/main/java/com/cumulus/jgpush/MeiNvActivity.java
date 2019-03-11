package com.cumulus.jgpush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MeiNvActivity extends AppCompatActivity {

    private ImageView mIvImg;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_nv);
        initView();
        url = getIntent().getStringExtra("url");
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher);
        Glide.with(this).load(url).apply(options).into(mIvImg);
    }

    private void initView() {
        mIvImg = (ImageView) findViewById(R.id.iv_img);



    }
}
