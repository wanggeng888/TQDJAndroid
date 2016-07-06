package com.wenc.tltd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.i.WebUrl;
import com.wenc.tltd.service.AccountService;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity implements WebUrl, Keyword {

    private static final String TAG = "SigninActivity";
    private EditText etxUsername; // 用户名
    private EditText etxPassword; // 密码
    private TextView tvRegist; // 免费注册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);
        etxUsername = (EditText) this.findViewById(R.id.login_etx_username);
        etxPassword = (EditText) this.findViewById(R.id.login_etx_password);
        tvRegist = (TextView) this.findViewById(R.id.regist_txt);
        final Button signinBtn = (Button) this.findViewById(R.id.login_btn);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etxUsername.getText().toString();
                String password = etxPassword.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    final Map<String, String> params = new HashMap<String, String>();
                    params.put(USERNAME, username);
                    params.put(PASSWORD, password);

                    AccountService accountService = new AccountService(SigninActivity.this, getApplicationContext());
                    accountService.signIn(username, password, signinBtn);

                } else {
                    Toast.makeText(getApplicationContext(), "用户名或密码为空", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
                startActivity(intent);
            }
        });
    }

}
