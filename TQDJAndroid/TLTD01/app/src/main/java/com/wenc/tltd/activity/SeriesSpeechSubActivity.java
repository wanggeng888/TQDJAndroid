package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class SeriesSpeechSubActivity extends AppCompatActivity {

    private static final String TAG = "SeriesSpeechSubActivity";
    private int parentIndex; //父activity传入的值

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
                if (parentIndex > -1) {
                    Intent intent = new Intent();
                    switch (parentIndex) {
                        case 0:
                            intent.putExtra(Keyword.SS_SUB_INDEX, parentIndex + "_" + position);
                            if(position == 0){
                                intent.setClass(getApplicationContext(), SeriesSpeechSubSubTxtActivity.class);
                            }else{
                                intent.setClass(getApplicationContext(), SeriesSpeechSubSubActivity.class);
                            }
                            break;
                        case 1:
                            intent.putExtra(Keyword.SS_SUB_INDEX, parentIndex + "_" + position);
                            intent.setClass(getApplicationContext(), SeriesSpeechSubSubTxtActivity.class);
                            break;
                        case 2:
                            intent.putExtra(Keyword.SS_SUB_INDEX, parentIndex + "_" + position);
                            intent.setClass(getApplicationContext(), SeriesSpeechSubSubTxtActivity.class);
                            break;
                        case 3:
                            intent.putExtra(Keyword.SS_SUB_INDEX, parentIndex + "_" + position);
                            intent.setClass(getApplicationContext(), SeriesSpeechSubSubTxtActivity.class);
                            break;
                        case 4:
                            intent.putExtra(Keyword.SS_SUB_INDEX, parentIndex + "_" + position);
                            intent.setClass(getApplicationContext(), SeriesSpeechSubSubTxtActivity.class);
                            break;
                    }
                    startActivity(intent);
                }
            }
        });
    }

    private StringListAdapter initAdapter() {
        String[] ssList = null;
        Intent intent = getIntent();
        parentIndex = intent.getIntExtra(Keyword.SSINDEX, -1);
        Log.d(TAG, "parentIndex: "+ parentIndex);
        if (parentIndex > -1 && parentIndex < 5) {
            switch (parentIndex) {
                case 0:
                    ssList = new String[]{getResources().getString(R.string.xjpSsTitle0), getResources().getString(R.string.xjpSsTitle1), getResources().getString(R.string.xjpSsTitle2), getResources().getString(R.string.xjpSsTitle3), getResources().getString(R.string.xjpSsTitle4), getResources().getString(R.string.xjpSsTitle5), getResources().getString(R.string.xjpSsTitle6), getResources().getString(R.string.xjpSsTitle7), getResources().getString(R.string.xjpSsTitle8), getResources().getString(R.string.xjpSsTitle9), getResources().getString(R.string.xjpSsTitle10), getResources().getString(R.string.xjpSsTitle11), getResources().getString(R.string.xjpSsTitle12), getResources().getString(R.string.xjpSsTitle13), getResources().getString(R.string.xjpSsTitle14), getResources().getString(R.string.xjpSsTitle15), getResources().getString(R.string.xjpSsTitle16)};
                    break;
                case 1:
                    ssList = new String[]{getResources().getString(R.string.xdzbSsTitle0), getResources().getString(R.string.xdzbSsTitle1), getResources().getString(R.string.xdzbSsTitle2), getResources().getString(R.string.xdzbSsTitle3), getResources().getString(R.string.xdzbSsTitle4), getResources().getString(R.string.xdzbSsTitle5), getResources().getString(R.string.xdzbSsTitle6)};
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
