package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

/**
 * 系列讲话
 */
public class SeriesSpeechActivity extends AppCompatActivity {

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
                if(position < 2){
                    intent.putExtra(Keyword.SSINDEX, position);
                    intent.setClass(getApplicationContext(), SeriesSpeechSubActivity.class);
                }else{
                    intent.putExtra(Keyword.SS_SUB_INDEX, String.valueOf(position));
                    intent.setClass(getApplicationContext(), SeriesSpeechSubSubTxtActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    private StringListAdapter initAdapter() {
        return new StringListAdapter(getApplicationContext(), getResources().getStringArray(R.array.ssList));
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
