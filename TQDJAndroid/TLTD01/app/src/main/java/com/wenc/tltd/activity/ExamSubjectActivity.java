package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.adapter.ExamSubjectAdapter;
import com.wenc.tltd.entity.Exam;
import com.wenc.tltd.service.ExamService;

import java.util.List;

public class ExamSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_exam_subject);
        ExamService examService = new ExamService(ExamSubjectActivity.this, getApplicationContext());
        List<Exam> examList = examService.allExamList();
        if (examList != null && !examList.isEmpty()) {
            ListView lvSubject = (ListView) findViewById(R.id.page_lv_exam_subject);
            ExamSubjectAdapter adapter = new ExamSubjectAdapter(getApplicationContext(), examList);
            lvSubject.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(), "题库为空，请更新题库", Toast.LENGTH_SHORT).show();
            finish();
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
            tvActionBar.setText(R.string.examSubject);
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
