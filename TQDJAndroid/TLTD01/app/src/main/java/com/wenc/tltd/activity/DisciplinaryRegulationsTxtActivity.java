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

public class DisciplinaryRegulationsTxtActivity extends AppCompatActivity {

    private static final String TAG = "DRActivity";
    private String[] content;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_dr_text);
        initContent();
        if (title != null && !title.isEmpty() && content != null) {
            TextView tvPcTitle = (TextView) findViewById(R.id.tv_drTitle);
            tvPcTitle.setText(title);
            tvPcTitle.setTextSize(25);
            tvPcTitle.setGravity(Gravity.CENTER);
            tvPcTitle.setTextColor(Color.BLACK);
            // 设置标题布局
            LinearLayout.LayoutParams tvTitleLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvTitleLayoutParams.setMargins(0, 30, 0, 30);
            tvPcTitle.setLayoutParams(tvTitleLayoutParams);
            LinearLayout llPcText = (LinearLayout) findViewById(R.id.ll_drText);
            for (String con : content) {
                TextView tvCon = new TextView(getApplicationContext());
                tvCon.setText(con);
                tvCon.setTextSize(22);
                tvCon.setTextColor(Color.BLACK);
                tvCon.setLineSpacing(0, 1.5f);
                llPcText.addView(tvCon);
            }
        }
    }

    private void initContent() {
        Intent intent = getIntent();
        String jlcfIndex = intent.getStringExtra(Keyword.JLCFINDEX);
        String[] jlcfIndexes = jlcfIndex.split("_");
        Log.d(TAG, "jlcfIndex: " + jlcfIndex);
        int pjIndex = 0;
        int pjSubIndex = 0;
        if (jlcfIndexes.length == 1) {
            pjIndex = Integer.parseInt(jlcfIndexes[0]);
        }
        if (jlcfIndexes.length == 2) {
            pjIndex = Integer.parseInt(jlcfIndexes[0]);
            pjSubIndex = Integer.parseInt(jlcfIndexes[1]);
        }
        switch (pjIndex) {
            case 0:
                switch (pjSubIndex){
                    case 0:
                        title = getResources().getString(R.string.jlcfSub1Title1);
                        content = getResources().getStringArray(R.array.jlcfSub1Title1Sub);
                        break;
                    case 1:
                        title = getResources().getString(R.string.jlcfSub1Title2);
                        content = getResources().getStringArray(R.array.jlcfSub1Title2Sub);
                        break;
                    case 2:
                        title = getResources().getString(R.string.jlcfSub1Title3);
                        content = getResources().getStringArray(R.array.jlcfSub1Title3Sub);
                        break;
                    case 3:
                        title = getResources().getString(R.string.jlcfSub1Title4);
                        content = getResources().getStringArray(R.array.jlcfSub1Title4Sub);
                        break;
                    case 4:
                        title = getResources().getString(R.string.jlcfSub1Title5);
                        content = getResources().getStringArray(R.array.jlcfSub1Title5Sub);
                        break;
                }
                break;
            case 1:
                switch (pjSubIndex){
                    case 0:
                        title = getResources().getString(R.string.jlcfSub2Title1);
                        content = getResources().getStringArray(R.array.jlcfSub1Title2Sub);
                        break;
                    case 1:
                        title = getResources().getString(R.string.jlcfSub2Title2);
                        content = getResources().getStringArray(R.array.jlcfSub2Title2Sub);
                        break;
                    case 2:
                        title = getResources().getString(R.string.jlcfSub2Title3);
                        content = getResources().getStringArray(R.array.jlcfSub2Title3Sub);
                        break;
                    case 3:
                        title = getResources().getString(R.string.jlcfSub2Title4);
                        content = getResources().getStringArray(R.array.jlcfSub2Title4sub);
                        break;
                    case 4:
                        title = getResources().getString(R.string.jlcfSub2Title5);
                        content = getResources().getStringArray(R.array.jlcfSub2Title5Sub);
                        break;
                    case 5:
                        title = getResources().getString(R.string.jlcfSub2Title6);
                        content = getResources().getStringArray(R.array.jlcfSub2Title6Sub);
                        break;
                }
                break;
            case 2:
                title = getResources().getString(R.string.jlcfSub3Title1);
                content = getResources().getStringArray(R.array.jlcfSub3Title1Sub);
                break;
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
            tvActionBar.setText(R.string.disciplinaryAdction);
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
