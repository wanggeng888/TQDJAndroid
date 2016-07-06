package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

public class DisciplinaryRegulationsSubActivity extends AppCompatActivity {

    private static final String TAG = "SeriesSpeechSubActivity";
    private int parentIndex; //父activity传入的值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_disciplinary_regulations);
        StringListAdapter adapter = initAdapter();
        ListView listView = (ListView) findViewById(R.id.disciplinaryRegulationsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parentIndex > -1) {
                    Intent intent = new Intent();
                    intent.putExtra(Keyword.JLCFINDEX, parentIndex + "_" + position);
                    intent.setClass(getApplicationContext(), DisciplinaryRegulationsTxtActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private StringListAdapter initAdapter() {
        String[] ssList = null;
        Intent intent = getIntent();
        parentIndex = Integer.parseInt(intent.getStringExtra(Keyword.JLCFINDEX));
        Log.d(TAG, "parentIndex: "+ parentIndex);
        if (parentIndex > -1 && parentIndex < 5) {
            switch (parentIndex) {
                case 0:
                    ssList = new String[]{getResources().getString(R.string.jlcfSub1Title1),getResources().getString(R.string.jlcfSub1Title2),getResources().getString(R.string.jlcfSub1Title3),getResources().getString(R.string.jlcfSub1Title4),getResources().getString(R.string.jlcfSub1Title5)};
                    break;
                case 1:
                    ssList = new String[]{getResources().getString(R.string.jlcfSub2Title1),getResources().getString(R.string.jlcfSub2Title2),getResources().getString(R.string.jlcfSub2Title3),getResources().getString(R.string.jlcfSub2Title4),getResources().getString(R.string.jlcfSub2Title5),getResources().getString(R.string.jlcfSub2Title6)};
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
