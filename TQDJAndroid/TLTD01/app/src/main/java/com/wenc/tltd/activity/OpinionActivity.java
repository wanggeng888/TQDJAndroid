package com.wenc.tltd.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenc.tltd.R;

public class OpinionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_opinion);
        Button btnOpinion = (Button) findViewById(R.id.page_btn_opinion);
        btnOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
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
            tvActionBar.setText(R.string.opinion);
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
