package com.team8.mini_tiktok;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;
import com.team8.mini_tiktok.activity.BaseActivity;
import com.team8.mini_tiktok.activity.HomeActivity;
import com.team8.mini_tiktok.network.ApiConfig;

public class MainActivity extends BaseActivity {
    DouYinOpenApi douYinOpenApi;
    private String mScope = ApiConfig.SCOPE;

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
        getClientToken();
        //TODO: 判断token，保存登录状态
        String access_token = getStringFromSP("access_token");
        if (access_token != null && !TextUtils.isEmpty(access_token)) { //存在access_token,调转到HomeActivity
            Log.d("TZH", "MainActivity initDate: access_token=" + access_token);
            navigateToWithFlag(HomeActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        douYinOpenApi = DouYinOpenApiFactory.create(this);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 如果本地未安装抖音或者抖音的版本过低，会直接自动调用 web页面 进行授权
                sendAuth();
            }
        });
    }

    private boolean sendAuth() {
        Authorization.Request request = new Authorization.Request();
        request.scope = mScope;                          // 用户授权时必选权限
        request.optionalScope0 = "mobile";     // 用户授权时可选权限（默认选择）
//        request.optionalScope0 = mOptionalScope1;    // 用户授权时可选权限（默认不选）
        request.state = "ww";                                   // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
        return douYinOpenApi.authorize(request);               // 优先使用抖音app进行授权，如果抖音app因版本或者其他原因无法授权，则使用wap页授权
    }
}