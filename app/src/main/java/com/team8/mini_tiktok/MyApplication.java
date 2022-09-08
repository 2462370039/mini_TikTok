package com.team8.mini_tiktok;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.DouYinOpenConfig;

/**
 * @introduction:
 * @author: T19
 * @time: 2022.09.07 19:45
 */
public class MyApplication extends Application {
    public static final String clientkey = AppConfig.clientkey;
    public static final String clientSecret = AppConfig.clientSecret;

    @Override
    public void onCreate() {
        super.onCreate();
        DouYinOpenApiFactory.init(new DouYinOpenConfig(clientkey));
    }
}
