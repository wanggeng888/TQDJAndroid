package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.service.NewsService;

/**
 * Created by WenC on 2016-06-11.
 */
public class CarouselActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_news);
        Intent intent = getIntent();
        int id = intent.getIntExtra(Keyword.ID, 0);
        if (id > 0) {
            TextView tvTitle = (TextView) findViewById(R.id.news_tv_title);
            TextView tvTime = (TextView) findViewById(R.id.news_tv_time);
            TextView tvText = (TextView) findViewById(R.id.news_tv_txt);
            ImageView ivImg = (ImageView) findViewById(R.id.news_iv_img);
            NewsService newsService = new NewsService(getApplicationContext());
            newsService.newsInfo(String.valueOf(id), tvTitle, tvTime, tvText, ivImg, true);
        }
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(false);
            View v = LayoutInflater.from(this).inflate(R.layout.actionbar_sub, null);
            TextView tvActionBar = (TextView) v.findViewById(R.id.tv_main_actionbar);
            ImageView ivActionBar = (ImageView) v.findViewById(R.id.back_img);
            tvActionBar.setText(R.string.news);
            actionBar.setCustomView(v);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbarBgColor)));
            ivActionBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

}
