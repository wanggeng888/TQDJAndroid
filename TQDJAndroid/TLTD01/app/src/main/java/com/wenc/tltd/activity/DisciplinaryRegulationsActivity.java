package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
 * 纪律处分条例
 */
public class DisciplinaryRegulationsActivity extends AppCompatActivity {

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
                Intent intent = new Intent();
                intent.putExtra(Keyword.JLCFINDEX, String.valueOf(position));
                if(position < 2){
                    intent.setClass(getApplicationContext(), DisciplinaryRegulationsSubActivity.class);
                }else{
                    intent.setClass(getApplicationContext(), DisciplinaryRegulationsTxtActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    private StringListAdapter initAdapter() {
        String[] drList = new String[]{getResources().getString(R.string.jlcfTitle1), getResources().getString(R.string.jlcfTitle2), getResources().getString(R.string.jlcfTitle3)};
        return new StringListAdapter(getApplicationContext(), drList);
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
