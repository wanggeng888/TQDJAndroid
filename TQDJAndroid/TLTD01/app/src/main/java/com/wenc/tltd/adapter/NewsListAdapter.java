package com.wenc.tltd.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WenC on 2016-06-11.
 */
public class NewsListAdapter extends BaseAdapter {

    private Context mContext;
    private List<News> mNewsList;

    public NewsListAdapter(Context context) {
        this.mContext = context;
        this.mNewsList = new ArrayList<News>();
    }

    public void setNewList(List<News> newsList) {
        mNewsList.addAll(newsList);
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mNewsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.page_news_list_item, null);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.page_news_list_item_title);
        TextView tvDate = (TextView) convertView.findViewById(R.id.page_news_list_item_date);
        TextView tvReadCount = (TextView) convertView.findViewById(R.id.page_news_list_item_read);
        tvTitle.setText(mNewsList.get(position).getTitle());
        tvDate.setText(mNewsList.get(position).getTime());
        tvReadCount.setText(String.valueOf(mNewsList.get(position).getReadCount()));
        return convertView;
    }
}
