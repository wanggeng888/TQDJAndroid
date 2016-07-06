package com.wenc.tltd.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.i.IMessageCode;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.i.WebUrl;
import com.wenc.tltd.service.AccountService;

public class RegistActivity extends AppCompatActivity implements Keyword, WebUrl, IMessageCode {
    private EditText registUsername, registPassword, registConfirmPassword;
    private static final int IS_REGIST_SUCCESS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_regist);
        registUsername = (EditText) findViewById(R.id.regist_edt_username);
        registPassword = (EditText) findViewById(R.id.regist_edt_password);
        registConfirmPassword = (EditText) findViewById(R.id.regist_edt_confirm_password);

        final Button registBtn = (Button) findViewById(R.id.regist_btn);
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = registUsername.getText().toString();
                String password = registPassword.getText().toString();
                String cPassword = registConfirmPassword.getText().toString();

                if (username != null && !username.isEmpty()) {
                    if (password != null && !password.isEmpty()) {
                        if (cPassword != null && password.equals(cPassword)) {
                            AccountService accountService = new AccountService(RegistActivity.this, getApplicationContext());
                            accountService.regist(username, password, registBtn);
                        } else {
                            Toast.makeText(getApplicationContext(), "两次密码不同", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
