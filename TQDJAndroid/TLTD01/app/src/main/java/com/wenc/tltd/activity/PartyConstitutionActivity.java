package com.wenc.tltd.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.adapter.StringListAdapter;
import com.wenc.tltd.i.Keyword;

/**
 * 党章党规
 */
public class PartyConstitutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_party_constitution);
        StringListAdapter adapter = initAdapter();
        ListView listView = (ListView) findViewById(R.id.partyConstitutionList);
        // 设置布局
        LinearLayout.LayoutParams lvLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lvLayoutParams.setMargins(10, 5, 10, 5);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Keyword.PCINDEX, position);
                intent.setClass(getApplicationContext(), PartyConstitutionTxtActivity.class);
                startActivity(intent);
            }
        });

    }

    private StringListAdapter initAdapter() {
        String[] pcTitle = new String[]{getResources().getString(R.string.pcTitle0), getResources().getString(R.string.pcTitle1), getResources().getString(R.string.pcTitle2), getResources().getString(R.string.pcTitle3), getResources().getString(R.string.pcTitle4), getResources().getString(R.string.pcTitle5), getResources().getString(R.string.pcTitle6), getResources().getString(R.string.pcTitle7), getResources().getString(R.string.pcTitle8), getResources().getString(R.string.pcTitle9), getResources().getString(R.string.pcTitle10), getResources().getString(R.string.pcTitle11)};
        return new StringListAdapter(getApplicationContext(), pcTitle);
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
