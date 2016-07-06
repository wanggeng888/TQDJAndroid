package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.i.Keyword;

public class TLTDTxtActivity extends AppCompatActivity {

    private static final String TAG = "TLTDActivity";
    private String[] content;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_tltd_text);
        initContent();
        if (title != null && !title.isEmpty() && content != null) {
            TextView tvPcTitle = (TextView) findViewById(R.id.tv_tltdTitle);
            tvPcTitle.setText(title);
            tvPcTitle.setTextSize(25);
            tvPcTitle.setGravity(Gravity.CENTER);
            tvPcTitle.setTextColor(Color.BLACK);
            // 设置标题布局
            LinearLayout.LayoutParams tvTitleLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvTitleLayoutParams.setMargins(0, 30, 0, 30);
            tvPcTitle.setLayoutParams(tvTitleLayoutParams);
            LinearLayout llPcText = (LinearLayout) findViewById(R.id.ll_tltdText);
            for (String con : content) {
                TextView tvCon = new TextView(getApplicationContext());
                tvCon.setText(con);
                tvCon.setTextSize(22);
                tvCon.setTextColor(Color.BLACK);
                tvCon.setLineSpacing(0,1.5f);
                llPcText.addView(tvCon);
            }
        }
    }

    private void initContent() {
        Intent intent = getIntent();
        int tltdIndex = intent.getIntExtra(Keyword.TLTDINDEX, -1);
        Log.d(TAG, "tltdINDEX: " + tltdIndex);
        if (tltdIndex > -1 && tltdIndex < 4) {
            switch (tltdIndex) {
                case 0:
                    title = getResources().getString(R.string.tltdTitle1);
                    content = getResources().getStringArray(R.array.tltdTitle1Sub);
                    break;
            }
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
            tvActionBar.setText(R.string.studyTltd);
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
