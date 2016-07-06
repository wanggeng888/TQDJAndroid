package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.adapter.StringListAdapter;
import com.wenc.tltd.i.Keyword;

public class SeriesSpeechSubSubActivity extends AppCompatActivity {

    private int parentIndex; //父activity传入的值
    private int subParentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_series_speech);
        StringListAdapter adapter = initAdapter();
        ListView listView = (ListView) findViewById(R.id.seriesSpeechList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Keyword.SS_SUB_INDEX, parentIndex + "_" + subParentIndex + "_" + position);
                intent.setClass(getApplicationContext(), SeriesSpeechSubSubTxtActivity.class);
                startActivity(intent);
            }
        });
    }

    private StringListAdapter initAdapter() {
        String[] ssList = null;
        Intent intent = getIntent();
        String ssSubIndex = intent.getStringExtra(Keyword.SS_SUB_INDEX);
        if (ssSubIndex != null) {
            String[] ssSubIndexes = ssSubIndex.split("_");
            parentIndex = Integer.parseInt(ssSubIndexes[0]);
            subParentIndex = Integer.parseInt(ssSubIndexes[1]);
            switch (parentIndex) {
                case 0:
                    switch (subParentIndex) {
                        case 1:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle1SubTitle1), getResources().getString(R.string.xjpSsTitle1SubTitle2), getResources().getString(R.string.xjpSsTitle1SubTitle3), getResources().getString(R.string.xjpSsTitle1SubTitle4), getResources().getString(R.string.xjpSsTitle1SubTitle5)};
                            break;
                        case 2:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle2SubTitle1), getResources().getString(R.string.xjpSsTitle2SubTitle2), getResources().getString(R.string.xjpSsTitle2SubTitle3), getResources().getString(R.string.xjpSsTitle2SubTitle4), getResources().getString(R.string.xjpSsTitle2SubTitle5), getResources().getString(R.string.xjpSsTitle2SubTitle6)};
                            break;
                        case 3:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle3SubTitle1), getResources().getString(R.string.xjpSsTitle3SubTitle2), getResources().getString(R.string.xjpSsTitle3SubTitle3), getResources().getString(R.string.xjpSsTitle3SubTitle4)};
                            break;
                        case 4:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle4SubTitle1), getResources().getString(R.string.xjpSsTitle4SubTitle2), getResources().getString(R.string.xjpSsTitle4SubTitle3), getResources().getString(R.string.xjpSsTitle4SubTitle4), getResources().getString(R.string.xjpSsTitle4SubTitle5)};
                            break;
                        case 5:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle5SubTitle1), getResources().getString(R.string.xjpSsTitle5SubTitle2), getResources().getString(R.string.xjpSsTitle5SubTitle3), getResources().getString(R.string.xjpSsTitle5SubTitle4), getResources().getString(R.string.xjpSsTitle5SubTitle5), getResources().getString(R.string.xjpSsTitle5SubTitle6)};
                            break;
                        case 6:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle6SubTitle1), getResources().getString(R.string.xjpSsTitle6SubTitle2), getResources().getString(R.string.xjpSsTitle6SubTitle3), getResources().getString(R.string.xjpSsTitle6SubTitle4), getResources().getString(R.string.xjpSsTitle6SubTitle5), getResources().getString(R.string.xjpSsTitle6SubTitle6)};
                            break;
                        case 7:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle7SubTitle1), getResources().getString(R.string.xjpSsTitle7SubTitle2), getResources().getString(R.string.xjpSsTitle7SubTitle3), getResources().getString(R.string.xjpSsTitle7SubTitle4), getResources().getString(R.string.xjpSsTitle7SubTitle5), getResources().getString(R.string.xjpSsTitle7SubTitle6), getResources().getString(R.string.xjpSsTitle7SubTitle7), getResources().getString(R.string.xjpSsTitle7SubTitle8), getResources().getString(R.string.xjpSsTitle7SubTitle9)};
                            break;
                        case 8:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle8SubTitle1), getResources().getString(R.string.xjpSsTitle8SubTitle2), getResources().getString(R.string.xjpSsTitle8SubTitle3), getResources().getString(R.string.xjpSsTitle8SubTitle4)};
                            break;
                        case 9:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle9SubTitle1), getResources().getString(R.string.xjpSsTitle9SubTitle2), getResources().getString(R.string.xjpSsTitle9SubTitle3), getResources().getString(R.string.xjpSsTitle9SubTitle4), getResources().getString(R.string.xjpSsTitle9SubTitle5), getResources().getString(R.string.xjpSsTitle9SubTitle6), getResources().getString(R.string.xjpSsTitle9SubTitle7)};
                            break;
                        case 10:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle10SubTitle1), getResources().getString(R.string.xjpSsTitle10SubTitle2), getResources().getString(R.string.xjpSsTitle10SubTitle3), getResources().getString(R.string.xjpSsTitle10SubTitle4), getResources().getString(R.string.xjpSsTitle10SubTitle5), getResources().getString(R.string.xjpSsTitle10SubTitle6), getResources().getString(R.string.xjpSsTitle10SubTitle7)};
                            break;
                        case 11:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle11SubTitle1), getResources().getString(R.string.xjpSsTitle11SubTitle2), getResources().getString(R.string.xjpSsTitle11SubTitle3), getResources().getString(R.string.xjpSsTitle11SubTitle4), getResources().getString(R.string.xjpSsTitle11SubTitle5), getResources().getString(R.string.xjpSsTitle11SubTitle6), getResources().getString(R.string.xjpSsTitle11SubTitle7), getResources().getString(R.string.xjpSsTitle11SubTitle8)};
                            break;
                        case 12:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle12SubTitle1), getResources().getString(R.string.xjpSsTitle12SubTitle2), getResources().getString(R.string.xjpSsTitle12SubTitle3), getResources().getString(R.string.xjpSsTitle12SubTitle4), getResources().getString(R.string.xjpSsTitle12SubTitle5), getResources().getString(R.string.xjpSsTitle12SubTitle6)};
                            break;
                        case 13:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle13SubTitle1), getResources().getString(R.string.xjpSsTitle13SubTitle2), getResources().getString(R.string.xjpSsTitle13SubTitle3), getResources().getString(R.string.xjpSsTitle13SubTitle4)};
                            break;
                        case 14:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle14SubTitle1), getResources().getString(R.string.xjpSsTitle14SubTitle2), getResources().getString(R.string.xjpSsTitle14SubTitle3), getResources().getString(R.string.xjpSsTitle14SubTitle4), getResources().getString(R.string.xjpSsTitle14SubTitle5), getResources().getString(R.string.xjpSsTitle14SubTitle6)};
                            break;
                        case 15:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle15SubTitle1), getResources().getString(R.string.xjpSsTitle15SubTitle2), getResources().getString(R.string.xjpSsTitle15SubTitle3), getResources().getString(R.string.xjpSsTitle15SubTitle4), getResources().getString(R.string.xjpSsTitle15SubTitle5), getResources().getString(R.string.xjpSsTitle15SubTitle6), getResources().getString(R.string.xjpSsTitle15SubTitle7), getResources().getString(R.string.xjpSsTitle15SubTitle8)};
                            break;
                        case 16:
                            ssList = new String[]{getResources().getString(R.string.xjpSsTitle16SubTitle1), getResources().getString(R.string.xjpSsTitle16SubTitle2), getResources().getString(R.string.xjpSsTitle16SubTitle3), getResources().getString(R.string.xjpSsTitle16SubTitle4), getResources().getString(R.string.xjpSsTitle16SubTitle5), getResources().getString(R.string.xjpSsTitle16SubTitle6)};
                            break;
                    }
                    break;
            }
        }
        return new StringListAdapter(getApplicationContext(), ssList);
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
            tvActionBar.setText(R.string.studySeriesSpeech);
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
