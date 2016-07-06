package com.wenc.tltd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.entity.Exam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WenC on 2016-06-14.
 */
public class ExamSubjectAdapter extends BaseAdapter {

    private List<Exam> mExamList;
    private Context mContext;

    public ExamSubjectAdapter(Context context, List<Exam> examList) {
        this.mContext = context;
        this.mExamList = examList;
    }

    @Override
    public int getCount() {
        return mExamList.size();
    }

    @Override
    public Object getItem(int position) {
        return mExamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.page_exam_subject_item, null);
        }
        Exam exam = mExamList.get(position);
        String title = exam.getId() + ". 题目：" + exam.getTitle();
        String option1 = exam.getOption1() == null ? "" : "A." + exam.getOption1();
        String option2 = exam.getOption2() == null ? "" : "B." + exam.getOption2();
        String option3 = exam.getOption3() == null ? "" : "C." + exam.getOption3();
        String option4 = exam.getOption4() == null ? "" : "D." + exam.getOption4();
        String answer = "答案：";
        switch (exam.getAnswer()) {
            case 1:
                answer += "A";
                break;
            case 2:
                answer += "B";
                break;
            case 3:
                answer += "C";
                break;
            case 4:
                answer += "D";
                break;
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.page_tv_exam_subject_title);
        tvTitle.setText(title);
        TextView tvOption1 = (TextView) convertView.findViewById(R.id.page_tv_exam_subject_option1);
        tvOption1.setText(option1);
        TextView tvOption2 = (TextView) convertView.findViewById(R.id.page_tv_exam_subject_option2);
        tvOption2.setText(option2);
        TextView tvOption3 = (TextView) convertView.findViewById(R.id.page_tv_exam_subject_option3);
        tvOption3.setText(option3);
        TextView tvOption4 = (TextView) convertView.findViewById(R.id.page_tv_exam_subject_option4);
        tvOption4.setText(option4);
        TextView tvAnswer = (TextView) convertView.findViewById(R.id.page_tv_exam_subject_answer);
        tvAnswer.setText(answer);
        return convertView;
    }
}
