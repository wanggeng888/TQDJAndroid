package com.wenc.tltd.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenc.tltd.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by WenC on 2016-06-14.
 */
public class ExamListAdapter extends BaseAdapter {

    private List<String> mOptions;
    private List<ImageView> ivList;
    private Context mContext;
    private int selectedId = -1;

    public ExamListAdapter(Context context, List<String> options) {
        this.mContext = context;
        this.mOptions = options;
        ivList = new ArrayList<ImageView>();
    }

    @Override
    public int getCount() {
        return mOptions.size();
    }

    @Override
    public Object getItem(int position) {
        return ivList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int selectOption(int selectPosition) {
        System.out.println("---->> ivList: " + ivList);
        if (selectedId > -1) {
            switch (selectedId) {
                case 0:
                    ivList.get(selectedId).setImageResource(R.mipmap.option_a_default);
                    break;
                case 1:
                    ivList.get(selectedId).setImageResource(R.mipmap.option_b_default);
                    break;
                case 2:
                    ivList.get(selectedId).setImageResource(R.mipmap.option_c_default);
                    break;
                case 3:
                    ivList.get(selectedId).setImageResource(R.mipmap.option_d_default);
                    break;
            }
        }
        switch (selectPosition) {
            case 0:
                ivList.get(0).setImageResource(R.mipmap.option_a_select);
                selectedId = 0;
                break;
            case 1:
                ivList.get(1).setImageResource(R.mipmap.option_b_select);
                selectedId = 1;
                break;
            case 2:
                ivList.get(2).setImageResource(R.mipmap.option_c_select);
                selectedId = 2;
                break;
            case 3:
                ivList.get(3).setImageResource(R.mipmap.option_d_select);
                selectedId = 3;
                break;
        }
        return selectPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.page_exam_option_item, null);
        }
        ImageView ivOption = (ImageView) convertView.findViewById(R.id.page_exam_iv_option);
        switch (position) {
            case 0:
                ivOption.setImageResource(R.mipmap.option_a_default);
                break;
            case 1:
                ivOption.setImageResource(R.mipmap.option_b_default);
                break;
            case 2:
                ivOption.setImageResource(R.mipmap.option_c_default);
                break;
            case 3:
                ivOption.setImageResource(R.mipmap.option_d_default);
                break;
        }
        if (!ivList.isEmpty()) {
            for (int i = 0; i < ivList.size(); i++) {
                if (ivOption == ivList.get(i)) {
                    continue;
                } else if (i == ivList.size() - 1) {
                    ivList.add(ivOption);
                    break;
                }
            }
        } else {
            ivList.add(ivOption);
        }
        TextView tvOption = (TextView) convertView.findViewById(R.id.page_exam_tv_option);
        tvOption.setText(mOptions.get(position));
        return convertView;
    }
}
