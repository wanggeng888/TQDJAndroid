package com.wenc.tltd.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.activity.AboutWeActivity;
import com.wenc.tltd.activity.OpinionActivity;
import com.wenc.tltd.dialog.IOSDialog;
import com.wenc.tltd.entity.Exam;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.service.AccountService;
import com.wenc.tltd.service.ExamService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMineFragment extends Fragment implements View.OnClickListener {

    private static MainMineFragment fragment;
    private IOSDialog.Builder builder;

    public MainMineFragment() {
    }

    public static MainMineFragment newInstance() {
        if (fragment == null) {
            fragment = new MainMineFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_main_we, container, false);
        view.setOnClickListener(this);
        LinearLayout llDeleteLayout = (LinearLayout) view.findViewById(R.id.we_delete);
        LinearLayout llAboutLayout = (LinearLayout) view.findViewById(R.id.we_about);
        LinearLayout llOpinionLayout = (LinearLayout) view.findViewById(R.id.we_opinion);
        LinearLayout llSignoutLayout = (LinearLayout) view.findViewById(R.id.we_signout);
        TextView tv = (TextView) view.findViewById(R.id.we_account_text);
        llDeleteLayout.setOnClickListener(this);
        llAboutLayout.setOnClickListener(this);
        llOpinionLayout.setOnClickListener(this);
        llSignoutLayout.setOnClickListener(this);

        SharedPreferences sp = getActivity().getSharedPreferences(Keyword.BCONF, Activity.MODE_PRIVATE);
        tv.setText(sp.getString(Keyword.USERNAME, ""));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.we_delete:
                builder = new IOSDialog.Builder(getActivity());
                builder.setMessage("确认删除缓存吗？");
                builder.setPositiveBtnClickListener("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExamService examService = new ExamService(getActivity().getApplicationContext());
                        examService.deleteDB();
                        Toast.makeText(getActivity(), "缓存已删除", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.we_about:
                Intent aboutWeIntent = new Intent();
                aboutWeIntent.setClass(getActivity().getApplicationContext(), AboutWeActivity.class);
                startActivity(aboutWeIntent);
                break;
            case R.id.we_opinion:
                Intent opinionIntent = new Intent();
                opinionIntent.setClass(getActivity().getApplicationContext(), OpinionActivity.class);
                startActivity(opinionIntent);
                break;
            case R.id.we_signout:
                IOSDialog.Builder builder = new IOSDialog.Builder(getActivity());
                builder.setMessage("确认退出登录吗？");
                builder.setPositiveBtnClickListener("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        AccountService accountService = new AccountService(getActivity(), getActivity().getApplicationContext());
                        accountService.signOut();
                    }
                });
                builder.setNegativeBtnClickListener("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
        }
    }
}
