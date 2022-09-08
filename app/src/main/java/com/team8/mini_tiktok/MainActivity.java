package com.team8.mini_tiktok;

import android.view.View;
import android.widget.Button;

import com.team8.mini_tiktok.activity.BaseActivity;

public class MainActivity extends BaseActivity {
    Button button_login;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        button_login = findViewById(R.id.btn_login);
    }

    @Override
    protected void initDate() {
        //TODO: 判断token，保存登录状态
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     *
     */
    private void getCode(){

    }
}