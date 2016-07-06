package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.adapter.NewsListAdapter;
import com.wenc.tltd.adapter.StringListAdapter;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.service.NewsService;

/**
 * 新闻列表
 */
public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_news_list);
        final ListView listView = (ListView) findViewById(R.id.news_list);
        NewsListAdapter newsListAdapter = new NewsListAdapter(getApplicationContext());
        NewsService newsService = new NewsService(NewsActivity.this, getApplicationContext(), listView, newsListAdapter);
        newsService.newsList("1", null);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.page_news_list_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsListAdapter newsListAdapter = new NewsListAdapter(getApplicationContext());
                NewsService newsService = new NewsService(NewsActivity.this, getApplicationContext(), listView, newsListAdapter);
                newsService.newsList("1", refreshLayout);
            }
        });
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
