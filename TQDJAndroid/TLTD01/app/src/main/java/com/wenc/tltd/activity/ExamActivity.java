package com.wenc.tltd.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.adapter.ExamListAdapter;
import com.wenc.tltd.dialog.IOSDialog;
import com.wenc.tltd.entity.Exam;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.service.AccountService;
import com.wenc.tltd.service.ExamService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamActivity extends AppCompatActivity {
    private static final String ANSWER = "answer";
    private List<Map<String, String>> answerArray;
    private int currentSubject = 0;
    private List<Exam> examList;
    private ListView lvOptions;
    private TextView tvTitle;
    private TextView tvCurrentSubject;
    private TextView tvNextSubject;
    private ImageView ivImageViewType;
    private ExamListAdapter adapter;
    private int selectedAnswer = -1;
    private int examId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_exam);
        ExamService service = new ExamService(getApplicationContext());
        examList = service.examList();
        tvCurrentSubject = (TextView) findViewById(R.id.page_tv_currentNum);
        tvTitle = (TextView) findViewById(R.id.page_tv_exam);
        TextView tvTotalNum = (TextView) findViewById(R.id.page_tv_totalNum);
        tvTotalNum.setText(String.valueOf(Keyword.EXAM_SIZE));
        ivImageViewType = (ImageView) findViewById(R.id.exam_iv_type);
        tvNextSubject = (TextView) findViewById(R.id.page_exam_next);
        tvNextSubject.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 if (selectedAnswer == -1) {
                                                     Toast.makeText(getApplicationContext(), "请选择选项", Toast.LENGTH_SHORT).show();
                                                 } else {
                                                     if (currentSubject < Keyword.EXAM_SIZE - 1) {
                                                         currentSubject += 1;
                                                         Map<String, String> map = new HashMap<String, String>();
                                                         map.put(Keyword.ID, String.valueOf(examId));
                                                         map.put(ANSWER, String.valueOf(selectedAnswer));
                                                         if (answerArray == null) {
                                                             answerArray = new ArrayList<Map<String, String>>();
                                                         }
                                                         answerArray.add(map);
                                                         selectedAnswer = -1;
                                                         setCurrentSubject();
                                                     } else {
                                                         Map<String, String> map = new HashMap<String, String>();
                                                         map.put(Keyword.ID, String.valueOf(examId));
                                                         map.put(ANSWER, String.valueOf(selectedAnswer));
                                                         if (answerArray == null) {
                                                             answerArray = new ArrayList<Map<String, String>>();
                                                         }
                                                         answerArray.add(map);
                                                         ExamService examService = new ExamService(ExamActivity.this, getApplicationContext());
                                                         examService.submitExam(answerArray);
                                                     }
                                                 }
                                             }
                                         }
        );
        lvOptions = (ListView) findViewById(R.id.page_exam_lv_option);
        setCurrentSubject();
    }

    private void setCurrentSubject() {
        if (currentSubject == Keyword.EXAM_SIZE - 1) {
            tvNextSubject.setText("交卷");
        }
        final Exam exam = examList.get(currentSubject);
        tvTitle.setText("\t\t\t\t\t\t\t\t\t" + exam.getTitle());
        tvCurrentSubject.setText(String.valueOf(currentSubject + 1));
        int type = 0;
        List<String> options = new ArrayList<String>();
        if (exam.getOption1() != null) {
            options.add(exam.getOption1());
            type += 1;
        }
        if (exam.getOption2() != null) {
            options.add(exam.getOption2());
            type += 1;
        }
        if (exam.getOption3() != null) {
            options.add(exam.getOption3());
            type += 1;
        }
        if (exam.getOption4() != null) {
            options.add(exam.getOption4());
            type += 1;
        }
        if (type < 3) {
            ivImageViewType.setImageResource(R.mipmap.exam_type01);
        } else {
            ivImageViewType.setImageResource(R.mipmap.exam_type02);
        }
        adapter = new ExamListAdapter(getApplicationContext(), options);
        lvOptions.setAdapter(adapter);
        lvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.selectOption(position);
                selectedAnswer = position + 1;
                examId = exam.getId();
            }
        });
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
            tvActionBar.setText(R.string.examCenter);
            actionBar.setCustomView(v);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbarBgColor)));
            ivActionBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IOSDialog.Builder builder = new IOSDialog.Builder(ExamActivity.this);
                    builder.setMessage("要取消考试吗？");
                    builder.setPositiveBtnClickListener("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
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
            });
        }
    }

    @Override
    public void onBackPressed() {
        IOSDialog.Builder builder = new IOSDialog.Builder(ExamActivity.this);
        builder.setMessage("要取消考试吗？");
        builder.setPositiveBtnClickListener("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
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
