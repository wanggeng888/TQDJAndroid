package com.wenc.tltd.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.activity.SigninActivity;
import com.wenc.tltd.activity.MainActivity;
import com.wenc.tltd.dialog.IOSDialog;
import com.wenc.tltd.i.IMessageCode;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.i.WebUrl;
import com.wenc.tltd.utils.request.RequestThread;
import com.wenc.tltd.utils.message.SendHandlerMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WenC on 2016-06-07.
 */
public class AccountService implements Keyword, WebUrl, IMessageCode {

    private static final String LOG = "AccountService";
    private Context mContext;
    private Activity mActivity;
    private static final int SIGNIN = 100;
    private static final int SIGNOUT = 200;
    private static final int REGIST = 300;
    private static final int AUTO_SIGNIN = 400;
    private static final int IS_SIGNIN_SUCCESS = 101;
    private static final int IS_SIGNOUT_SUCCESS = 201;
    private static final int IS_REGIST_SUCCESS = 301;
    private static final int IS_AUTOSIGN_SUCCESS = 401;
    private ProgressDialog progressDialog;
    public AccountService(Context context) {
        this.mContext = context;
    }

    public AccountService(Activity activity, Context context) {
        this.mContext = context;
        this.mActivity = activity;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param signBtn
     */
    public void signIn(final String username, String password, Button signBtn) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(USERNAME, username);
        params.put(PASSWORD, password);
        progressDialog = ProgressDialog.show(mActivity,"","正在登陆...");
        new Thread(new RequestThread(SIGNIN_URL, params, signBtn) {
            @Override
            public void doResult(JSONObject result) {
                if (result != null) {
                    try {
                        String code = result.getString(CODE);
                        if (SUCCESS.equals(code)) {
                            String token = result.getString(TOKEN);
                            if (token != null && !token.isEmpty()) {
                                SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString(TOKEN, token);
                                editor.putString(USERNAME, username);
                                editor.commit();
                                SendHandlerMessage.sendMessage(handler, SIGNIN, IS_SIGNIN_SUCCESS);
                                Intent intent = new Intent();
                                intent.setClass(mContext, MainActivity.class);
                                mActivity.startActivity(intent);
                                mActivity.finish();
                            } else {
                                SendHandlerMessage.sendMessage(handler, SIGNIN, LOGIN_ERROR_TOKEN_ERROR);
                            }
                        } else if (ERROR.equals(code)) {
                            String message = result.getString(MESSAGE);
                            SendHandlerMessage.sendMessage(handler, SIGNIN, Integer.parseInt(message));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(LOG, "result is null");
                    SendHandlerMessage.sendMessage(handler, SIGNIN, REQUEST_ERROR_PARAMS_ERROR);
                }
            }
        }).start();
    }

    /**
     * 自动登录
     *
     * @param username
     * @param token
     */
    public void autoSignIn(String username, String token) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(USERNAME, username);
        params.put(TOKEN, token);
        new Thread(new RequestThread(AUTO_SIGN_URL, params) {
            @Override
            public void doResult(JSONObject result) {
                Intent intent = new Intent();
                if (result != null) {
                    try {
                        String code = result.getString(CODE);
                        if (SUCCESS.equals(code)) {
                            String token = result.getString(TOKEN);
                            if (token != null && !token.isEmpty()) {
                                SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString(TOKEN, token);
                                editor.commit();
                                intent.setClass(mContext, MainActivity.class);
                            } else {
                                SendHandlerMessage.sendMessage(handler, AUTO_SIGNIN, LOGIN_ERROR_TOKEN_ERROR);
                                intent.setClass(mContext, SigninActivity.class);
                            }
                        } else if (ERROR.equals(code)) {
                            String message = result.getString(MESSAGE);
                            SendHandlerMessage.sendMessage(handler, AUTO_SIGNIN, Integer.parseInt(message));
                            intent.setClass(mContext, SigninActivity.class);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        SendHandlerMessage.sendMessage(handler, AUTO_SIGNIN, REQUEST_ERROR_PARAMS_ERROR);
                    }
                } else {
                    SendHandlerMessage.sendMessage(handler, AUTO_SIGNIN, REQUEST_ERROR_PARAMS_ERROR);
                    intent.setClass(mContext, SigninActivity.class);
                }
                mActivity.startActivity(intent);
                mActivity.finish();
            }
        }).start();
    }

    /**
     * 注销
     */
    public void signOut() {
        SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        String token = sp.getString(TOKEN, "");
        String username = sp.getString(USERNAME, "");
        Map<String, String> params = new HashMap<String, String>();
        params.put(TOKEN, token);
        params.put(USERNAME, username);
        new Thread(new RequestThread(SIGNOUT_URL, params) {
            @Override
            public void doResult(JSONObject result) {
                editor.putString(TOKEN, "");
                editor.commit();
                SendHandlerMessage.sendMessage(handler, SIGNOUT);
                Intent intent = new Intent();
                intent.setClass(mContext, SigninActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
            }
        }).start();
    }

    /**
     * 账号注册
     *
     * @param username
     * @param password
     * @param registBtn
     */
    public void regist(String username, String password, Button registBtn) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(USERNAME, username);
        params.put(PASSWORD, password);
        new Thread(new RequestThread(REGIST_URL, params, registBtn) {
            @Override
            public void doResult(JSONObject result) {
                if (result != null) {
                    try {
                        String code = result.getString(CODE);
                        if (SUCCESS.equals(code)) {
                            SendHandlerMessage.sendMessage(handler, REGIST, IS_REGIST_SUCCESS);
                            mActivity.finish();
                        } else if (ERROR.equals(code)) {
                            String message = result.getString(MESSAGE);
                            SendHandlerMessage.sendMessage(handler, REGIST, Integer.parseInt(message));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    SendHandlerMessage.sendMessage(handler, REGIST, REQUEST_ERROR_PARAMS_ERROR);
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SIGNIN:
                    if (msg.arg1 == IS_SIGNIN_SUCCESS) {
                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();

                    } else {
                        IOSDialog.Builder loginDialogBuilder = new IOSDialog.Builder(mActivity);
                        switch (msg.arg1) {
                            case LOGIN_ERROR_USERNAME_PASSWORD_UNMATCHED:
                                loginDialogBuilder.setMessage(mContext.getResources().getString(R.string.loginErrorUsernamePasswordUNmatched));
                                progressDialog.dismiss();

                                break;
                            case REGIST_ERROR_USERNAME_ILLEGAL:
                                loginDialogBuilder.setMessage(mContext.getResources().getString(R.string.registErrorUsernameIllegal));
                                progressDialog.dismiss();

                                break;
                            case LOGIN_ERROR_TOKEN_ERROR:
                                Toast.makeText(mContext, mContext.getResources().getString(R.string.loginErrorTokenError), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                break;
                            case REQUEST_ERROR_PARAMS_ERROR:
                                loginDialogBuilder.setMessage(mContext.getResources().getString(R.string.requestErrorParamsError));
                                progressDialog.dismiss();

                                break;
                        }
                        loginDialogBuilder.setPositiveBtnClickListener("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        loginDialogBuilder.create().show();
                    }
                    break;
                case SIGNOUT:
                    Toast.makeText(mContext, "注销成功", Toast.LENGTH_SHORT).show();
                    break;
                case AUTO_SIGNIN:
                    switch (msg.arg1) {
                        case VERIFY_ERROR_TOKEN_ERROR:
                            Toast.makeText(mContext, R.string.tokenVerifyError, Toast.LENGTH_SHORT).show();
                            break;
                        case REQUEST_ERROR_PARAMS_ERROR:
                            Toast.makeText(mContext, R.string.requestErrorParamsError, Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
                case REGIST:
                    if (msg.arg1 == IS_REGIST_SUCCESS) {
                        Toast.makeText(mContext, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                    } else {
                        IOSDialog.Builder registDialogBuilder = new IOSDialog.Builder(mActivity);
                        switch (msg.arg1) {
                            case REGIST_ERROR_USERNAME_EXIST:
                                registDialogBuilder.setMessage(mContext.getResources().getString(R.string.registErrorUsernameExist));
                                break;
                            case REGIST_ERROR_USERNAME_ILLEGAL:
                                registDialogBuilder.setMessage(mContext.getResources().getString(R.string.registErrorUsernameIllegal));
                                break;
                            case REGIST_ERROR_PASSWORD_LENGTH_ILLEGAL:
                                registDialogBuilder.setMessage(mContext.getResources().getString(R.string.registErrorPasswordLength));
                                break;
                            case REQUEST_ERROR_PARAMS_ERROR:
                                registDialogBuilder.setMessage(mContext.getResources().getString(R.string.requestErrorParamsError));
                                break;
                        }
                        registDialogBuilder.setPositiveBtnClickListener("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        registDialogBuilder.create().show();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
