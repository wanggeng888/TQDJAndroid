package com.wenc.tltd.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.activity.ExamResultActivity;
import com.wenc.tltd.adapter.ExamRecordListAdapter;
import com.wenc.tltd.entity.Exam;
import com.wenc.tltd.entity.ExamRecord;
import com.wenc.tltd.i.IMessageCode;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.i.WebUrl;
import com.wenc.tltd.utils.database.SQLiteHelper;
import com.wenc.tltd.utils.message.SendHandlerMessage;
import com.wenc.tltd.utils.request.RequestThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

public class ExamService implements Keyword, WebUrl, IMessageCode {

    private static final String LOG = "ExamService";
    private static final int EXAM = 100;
    private static final int EXAMUPDATE = 200;
    private static final int EXAMCOMMIT = 300;
    private static final int EXAMRECORD = 400;
    private static final int ACHIEVEMENT_RECORD = 8000;
    private static final int ACHIEVEMENT_RECORD_ERROR = 8001;
    private static final String SUBJECT = "subject";
    private static final String OPTION1 = "option1";
    private static final String OPTION2 = "option2";
    private static final String OPTION3 = "option3";
    private static final String OPTION4 = "option4";
    private static final String ANSWER = "answer";
    private int score; // 成绩
    private Context mContext;
    private Activity mActivity;
    private SQLiteHelper helper;
    private ProgressDialog progressDialog;
    private ListView recordListView;
    private List<ExamRecord> examRecordList;

    public ExamService(Context context) {
        this.mContext = context;
        helper = new SQLiteHelper(mContext, "tltd");
    }

    public ExamService(Activity activity, Context context) {
        this.mActivity = activity;
        this.mContext = context;
        helper = new SQLiteHelper(mContext, "tltd");
    }

    public void updateExam() {
        SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
        String username = sp.getString(USERNAME, "");
        String token = sp.getString(TOKEN, "");
        Map<String, String> map = new HashMap<String, String>();
        map.put(USERNAME, username);
        map.put(TOKEN, token);
        progressDialog = ProgressDialog.show(mActivity, "", "正在更新题库...");
        helper.delete();
        new Thread(new RequestThread(EXAM_UPDATE_URL, map) {
            @Override
            public void doResult(JSONObject result) {
                if (result != null) {
                    try {
                        String code = result.getString(CODE);
                        if (SUCCESS.equals(code)) {
                            JSONArray jsonArray = result.getJSONArray(DATA);
                            List<Exam> examList = new ArrayList<Exam>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Exam exam = new Exam();
                                exam.setTitle(jsonArray.getJSONObject(i).getString(SUBJECT));
                                exam.setOption1("null".equals(jsonArray.getJSONObject(i).getString(OPTION1)) ? null : jsonArray.getJSONObject(i).getString(OPTION1));
                                exam.setOption2("null".equals(jsonArray.getJSONObject(i).getString(OPTION2)) ? null : jsonArray.getJSONObject(i).getString(OPTION2));
                                exam.setOption3("null".equals(jsonArray.getJSONObject(i).getString(OPTION3)) ? null : jsonArray.getJSONObject(i).getString(OPTION3));
                                exam.setOption4("null".equals(jsonArray.getJSONObject(i).getString(OPTION4)) ? null : jsonArray.getJSONObject(i).getString(OPTION4));
                                exam.setAnswer(Integer.parseInt(jsonArray.getJSONObject(i).getString(ANSWER)));
                                examList.add(exam);
                            }
                            helper.createDB(); // 创建数据库
                            helper.create(examList);
                            SendHandlerMessage.sendMessage(handler, EXAMUPDATE);
                        } else if (ERROR.equals(code)) {
                            String message = result.getString(MESSAGE);
                            SendHandlerMessage.sendMessage(handler, EXAM, Integer.parseInt(message));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        SendHandlerMessage.sendMessage(handler, EXAM, REQUEST_ERROR_PARAMS_ERROR);
                    }
                } else {
                    SendHandlerMessage.sendMessage(handler, EXAM, REQUEST_ERROR_PARAMS_ERROR);
                }
            }
        }).start();
    }

    /**
     * 数据库读取考试列表
     *
     * @return
     */
    public List<Exam> examList() {
        List<Exam> result = new ArrayList<Exam>();
        List<Exam> examList = helper.searchExam();
        int allCount = examList.size();
        List<Integer> examIndexes = examIndexes(allCount);
        for (int i = 0; i < examIndexes.size(); i++) {
            result.add(examList.get(examIndexes.get(i)));
        }
        return result;
    }

    private List<Integer> examIndexes(int allCount) {
        Random random = new Random();
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < EXAM_SIZE; i++) {
            int randomIndex = random.nextInt(allCount);
            if (i == 0) {
                ids.add(randomIndex);
                continue;
            }
            for (int j = 0; j < ids.size(); j++) {
                if (randomIndex == ids.get(j)) {
                    i--;
                    break;
                }
                if (j == ids.size() - 1) {
                    ids.add(randomIndex);
                    break;
                }
            }

        }
        return ids;
    }

    public List<Exam> allExamList() {
        return helper.searchAllExam();
    }

    /**
     * 提交考试成绩
     *
     * @param answer
     */
    public void submitExam(List<Map<String, String>> answer) {
        SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
        String username = sp.getString(USERNAME, "");
        String token = sp.getString(TOKEN, "");
        try {
            JSONArray jsonArray = new JSONArray(answer);
            String data = Base64.encodeToString(Base64.encodeToString(URLEncoder.encode(jsonArray.toString(), "utf-8").getBytes(), Base64.DEFAULT).getBytes(), Base64.DEFAULT);
            Map<String, String> map = new HashMap<String, String>();
            map.put(USERNAME, username);
            map.put(TOKEN, token);
            map.put(DATA, data);
            progressDialog = ProgressDialog.show(mActivity, "", "正在计算成绩...");
            new Thread(new RequestThread(EXAM_COMMIT, map) {
                @Override
                public void doResult(JSONObject result) {
                    if (result != null) {
                        try {
                            String code = result.getString(CODE);
                            if (SUCCESS.equals(code)) {
                                score = (int) (Double.parseDouble(result.getString(SCORE)));
                                SendHandlerMessage.sendMessage(handler, EXAMCOMMIT);
                            } else if (ERROR.equals(code)) {
                                String message = result.getString(MESSAGE);
                                SendHandlerMessage.sendMessage(handler, EXAM, Integer.parseInt(message));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            SendHandlerMessage.sendMessage(handler, EXAM, REQUEST_ERROR_PARAMS_ERROR);
                        }
                    } else {
                        SendHandlerMessage.sendMessage(handler, EXAM, REQUEST_ERROR_PARAMS_ERROR);
                    }
                }
            }).start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void examRecord(ListView listView) {
        this.recordListView = listView;
        SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
        String username = sp.getString(USERNAME, "");
        String token = sp.getString(TOKEN, "");
        Map<String, String> map = new HashMap<String, String>();
        map.put(USERNAME, username);
        map.put(TOKEN, token);
        progressDialog = ProgressDialog.show(mActivity, "", "正在查询...");
        new Thread(new RequestThread(EXAM_RECORD, map) {
            @Override
            public void doResult(JSONObject result) {
                if (result != null) {
                    try {
                        String code = result.getString(CODE);
                        if (SUCCESS.equals(code)) {
                            JSONArray jsonArray = result.getJSONArray(DATA);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                ExamRecord examRecord = new ExamRecord(obj.getString(SCORE), obj.getString(TIME));
                                if (examRecordList == null) {
                                    examRecordList = new ArrayList<ExamRecord>();
                                }
                                examRecordList.add(examRecord);
                            }
                            SendHandlerMessage.sendMessage(handler, EXAMRECORD, ACHIEVEMENT_RECORD);
                        } else if (ERROR.equals(code)) {
                            String message = result.getString(MESSAGE);
                            SendHandlerMessage.sendMessage(handler, EXAMRECORD, Integer.parseInt(message));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        SendHandlerMessage.sendMessage(handler, EXAM, REQUEST_ERROR_PARAMS_ERROR);
                    }
                } else {
                    SendHandlerMessage.sendMessage(handler, EXAM, REQUEST_ERROR_PARAMS_ERROR);
                }
            }
        }).start();
    }

    public boolean isExamEmpty() {
        return helper.isDBEmpty();
    }

    public void deleteDB() {
        helper.delete();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EXAM:
                    switch (msg.arg1) {
                        case REQUEST_ERROR_PARAMS_ERROR:
                            Toast.makeText(mContext, R.string.requestErrorParamsError, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            break;
                        case VERIFY_ERROR_TOKEN_ERROR:
                            Toast.makeText(mContext, R.string.tokenVerifyError, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            break;
                        case EXAM_SROCE_ERROR:
                            Toast.makeText(mContext, R.string.examScoreError, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            break;
                    }
                    break;
                case EXAMUPDATE:
                    Toast.makeText(mContext, R.string.examUpdateSuccess, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    break;
                case EXAMCOMMIT:
                    progressDialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra(SCORE, score);
                    intent.setClass(mContext, ExamResultActivity.class);
                    mActivity.startActivity(intent);
                    mActivity.finish();
                    break;
                case EXAMRECORD:
                    switch (msg.arg1) {
                        case ACHIEVEMENT_RECORD:
                            progressDialog.dismiss();
                            ExamRecordListAdapter adapter = new ExamRecordListAdapter(mContext, examRecordList);
                            recordListView.setAdapter(adapter);
                            break;
                        case ACHIEVEMENT_RECORD_ERROR:
                            Toast.makeText(mContext, R.string.examRecordError, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            break;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
