package com.wenc.tltd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wenc.tltd.R;

import java.util.zip.Inflater;

/**
 * Created by WenC on 2016-06-02.
 */
public class StringListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mStrList;

    public StringListAdapter(Context context, String[] strList) {
        this.mContext = context;
        this.mStrList = strList;
    }

    @Override
    public int getCount() {
        return mStrList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.string_list, null);
        }
        TextView strItem = (TextView) convertView.findViewById(R.id.stringListItem);
        strItem.setText(mStrList[position]);
        return convertView;
    }
}
