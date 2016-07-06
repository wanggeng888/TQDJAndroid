package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.i.Keyword;

public class PartyConstitutionTxtActivity extends AppCompatActivity {

    private static final String TAG = "PCActivity";
    private String[] content;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_pc_text);
        initContent();
        if (title != null && !title.isEmpty() && content != null) {
            TextView tvPcTitle = (TextView) findViewById(R.id.tv_pcTitle);
            tvPcTitle.setText(title);
            tvPcTitle.setTextSize(25);
            tvPcTitle.setGravity(Gravity.CENTER);
            tvPcTitle.setTextColor(Color.BLACK);
            // 设置标题布局
            LinearLayout.LayoutParams tvTitleLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvTitleLayoutParams.setMargins(0, 30, 0, 30);
            tvPcTitle.setLayoutParams(tvTitleLayoutParams);
            LinearLayout llPcText = (LinearLayout) findViewById(R.id.ll_pcText);
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
        int pcIndex = intent.getIntExtra(Keyword.PCINDEX, -1);
        Log.d(TAG, "pcIndex: " + pcIndex);
        if (pcIndex > -1 && pcIndex < 12) {
            switch (pcIndex) {
                case 0:
                    title = getResources().getString(R.string.pcTitle0);
                    content = getResources().getStringArray(R.array.pc0);
                    break;
                case 1:
                    title = getResources().getString(R.string.pcTitle1);
                    content = getResources().getStringArray(R.array.pc1);
                    break;
                case 2:
                    title = getResources().getString(R.string.pcTitle2);
                    content = getResources().getStringArray(R.array.pc2);
                    break;
                case 3:
                    title = getResources().getString(R.string.pcTitle3);
                    content = getResources().getStringArray(R.array.pc3);
                    break;
                case 4:
                    title = getResources().getString(R.string.pcTitle4);
                    content = getResources().getStringArray(R.array.pc4);
                    break;
                case 5:
                    title = getResources().getString(R.string.pcTitle5);
                    content = getResources().getStringArray(R.array.pc5);
                    break;
                case 6:
                    title = getResources().getString(R.string.pcTitle6);
                    content = getResources().getStringArray(R.array.pc6);
                    break;
                case 7:
                    title = getResources().getString(R.string.pcTitle7);
                    content = getResources().getStringArray(R.array.pc7);
                    break;
                case 8:
                    title = getResources().getString(R.string.pcTitle8);
                    content = getResources().getStringArray(R.array.pc8);
                    break;
                case 9:
                    title = getResources().getString(R.string.pcTitle9);
                    content = getResources().getStringArray(R.array.pc9);
                    break;
                case 10:
                    title = getResources().getString(R.string.pcTitle10);
                    content = getResources().getStringArray(R.array.pc10);
                    break;
                case 11:
                    title = getResources().getString(R.string.pcTitle11);
                    content = getResources().getStringArray(R.array.pc11);
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
            tvActionBar.setText(R.string.studyPartyConstitution);
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
