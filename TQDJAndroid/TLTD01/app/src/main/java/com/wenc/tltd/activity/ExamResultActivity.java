package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.i.Keyword;

public class ExamResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_exam_result);
        Intent intent = getIntent();
        int score = intent.getIntExtra(Keyword.SCORE, 0);
        TextView tvScore = (TextView) findViewById(R.id.page_exam_tv_score);
        tvScore.setText(String.valueOf(score));
        TextView tvText = (TextView) findViewById(R.id.page_exam_tv_text);
        if (score < 30) {
            tvText.setText(getResources().getString(R.string.examResultIdiot));
        } else if (score < 60) {
            tvText.setText(getResources().getString(R.string.examResultWorkHard));
        } else if (score < 80) {
            tvText.setText(getResources().getString(R.string.examResultStillWorkHard));
        } else if (score < 100) {
            tvText.setText(getResources().getString(R.string.examResultNice));
        } else {
            tvText.setText(getResources().getString(R.string.examResultPerfect));
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
            tvActionBar.setText(R.string.examCenter);
            actionBar.setCustomView(v);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbarBgColor)));
            ivActionBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
