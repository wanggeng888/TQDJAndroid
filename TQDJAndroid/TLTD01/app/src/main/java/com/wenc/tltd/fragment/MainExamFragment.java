package com.wenc.tltd.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.activity.ExamActivity;
import com.wenc.tltd.dialog.IOSDialog;
import com.wenc.tltd.entity.Exam;
import com.wenc.tltd.service.AccountService;
import com.wenc.tltd.service.ExamService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainExamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainExamFragment extends Fragment implements View.OnClickListener {

    private static MainExamFragment fragment;

    public MainExamFragment() {
    }

    public static MainExamFragment newInstance() {
        if (fragment == null) {
            fragment = new MainExamFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_main_exam, container, false);
        Button beginExam = (Button) view.findViewById(R.id.page_exam_beginExam);
        Button updateExam = (Button) view.findViewById(R.id.page_exam_updateExam);
        view.setOnClickListener(this);
        beginExam.setOnClickListener(this);
        updateExam.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        ExamService examService = new ExamService(getActivity(), getActivity().getApplicationContext());
        switch (v.getId()) {
            case R.id.page_exam_beginExam:
                if(examService.isExamEmpty()){
                    IOSDialog.Builder builder = new IOSDialog.Builder(getActivity());
                    builder.setMessage("请先更新题库");
                    builder.setPositiveBtnClickListener("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(), ExamActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                }
                break;
            case R.id.page_exam_updateExam:
                examService.updateExam();
                break;
        }
    }
}
