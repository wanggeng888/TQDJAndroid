package com.wenc.tltd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.entity.Exam;
import com.wenc.tltd.entity.ExamRecord;

import java.util.List;

/**
 * Created by WenC on 2016-06-16.
 */
public class ExamRecordListAdapter extends BaseAdapter {

    private List<ExamRecord> mExamRecordList;
    private Context mContext;

    public ExamRecordListAdapter(Context context, List<ExamRecord> examRecordList) {
        this.mContext = context;
        this.mExamRecordList = examRecordList;
    }

    @Override
    public int getCount() {
        if(mExamRecordList != null){
            return mExamRecordList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mExamRecordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.page_exam_record_item, null);
        }
        TextView tvScore = (TextView) convertView.findViewById(R.id.page_tv_exam_score);
        tvScore.setText("成绩：" + mExamRecordList.get(position).getRecord());
        TextView tvTime = (TextView) convertView.findViewById(R.id.page_tv_exam_time);
        tvTime.setText("时间：" + mExamRecordList.get(position).getTime());
        return convertView;
    }
}
