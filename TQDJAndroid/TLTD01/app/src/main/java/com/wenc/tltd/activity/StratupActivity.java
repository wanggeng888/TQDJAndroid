package com.wenc.tltd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wenc.tltd.R;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.service.AccountService;

/**
 * 启动activity
 */
public class StratupActivity extends AppCompatActivity implements Keyword {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_startup);
        new Thread(new StartUp(StratupActivity.this, getApplicationContext())).start();
    }

    private class StartUp implements Runnable {
        private Activity mActivity;
        private Context mContext;

        public StartUp(Activity activity, Context context) {
            this.mActivity = activity;
            this.mContext = context;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                SharedPreferences sp = getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
                String token = sp.getString(TOKEN, "");
                if (token.isEmpty()) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), SigninActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    String username = sp.getString(USERNAME, "");
                    Looper.prepare();
                    AccountService accountService = new AccountService(mActivity, mContext);
                    accountService.autoSignIn(username, token);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
