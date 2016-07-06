package com.wenc.tltd.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.dialog.IOSDialog;
import com.wenc.tltd.fragment.MainExamFragment;
import com.wenc.tltd.fragment.MainListFragment;
import com.wenc.tltd.fragment.MainMineFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout imgBtnMain, imgBtnExam, imgBtnMine;
    private ImageView btnImgMain, btnImgExam, btnImgMine;
    private TextView txtMain, txtExam, txtMine;
    private MainListFragment listFragment;
    private MainExamFragment examFragment;
    private MainMineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_main);
        imgBtnMain = (RelativeLayout) findViewById(R.id.btn_main);
        imgBtnExam = (RelativeLayout) findViewById(R.id.btn_exam);
        imgBtnMine = (RelativeLayout) findViewById(R.id.btn_mine);

        btnImgMain = (ImageView) findViewById(R.id.btn_img_main);
        btnImgExam = (ImageView) findViewById(R.id.btn_img_exam);
        btnImgMine = (ImageView) findViewById(R.id.btn_img_mine);

        txtMain = (TextView) findViewById(R.id.nav_txt_home);
        txtExam = (TextView) findViewById(R.id.nav_txt_exam);
        txtMine = (TextView) findViewById(R.id.nav_txt_mine);

        imgBtnMain.setOnClickListener(this);
        imgBtnExam.setOnClickListener(this);
        imgBtnMine.setOnClickListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        listFragment = MainListFragment.newInstance();
        examFragment = MainExamFragment.newInstance();
        mineFragment = MainMineFragment.newInstance();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_fragment_continer, listFragment);
        transaction.add(R.id.main_fragment_continer, examFragment);
        transaction.add(R.id.main_fragment_continer, mineFragment);
        transaction.hide(examFragment);
        transaction.hide(mineFragment);
        transaction.commit();

    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.actionbar_main);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(false);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbarBgColor)));
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.btn_main:
                btnImgMain.setImageResource(R.mipmap.nav_home_normal);
                btnImgExam.setImageResource(R.mipmap.nav_history);
                btnImgMine.setImageResource(R.mipmap.nav_person);

                txtMain.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxt)));
                txtExam.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxtNormal)));
                txtMine.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxtNormal)));
                transaction.show(listFragment);
                transaction.hide(examFragment);
                transaction.hide(mineFragment);
                break;
            case R.id.btn_exam:
                btnImgMain.setImageResource(R.mipmap.nav_home);
                btnImgExam.setImageResource(R.mipmap.nav_history_normal);
                btnImgMine.setImageResource(R.mipmap.nav_person);

                txtMain.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxtNormal)));
                txtExam.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxt)));
                txtMine.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxtNormal)));
                transaction.show(examFragment);
                transaction.hide(listFragment);
                transaction.hide(mineFragment);
                break;
            case R.id.btn_mine:
                btnImgMain.setImageResource(R.mipmap.nav_home);
                btnImgExam.setImageResource(R.mipmap.nav_history);
                btnImgMine.setImageResource(R.mipmap.nav_person_normal);

                txtMain.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxtNormal)));
                txtExam.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxtNormal)));
                txtMine.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navTxt)));
                transaction.show(mineFragment);
                transaction.hide(listFragment);
                transaction.hide(examFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        IOSDialog.Builder builder = new IOSDialog.Builder(MainActivity.this);
        builder.setMessage("确定要退出吗？");
        builder.setPositiveBtnClickListener("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeBtnClickListener("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
