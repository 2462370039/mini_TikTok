package com.team8.mini_tiktok.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.team8.mini_tiktok.AppConfig;
import com.team8.mini_tiktok.entity.GetClientTokenResponse;
import com.team8.mini_tiktok.network.Api;
import com.team8.mini_tiktok.network.ApiConfig;
import com.team8.mini_tiktok.network.MyCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @introduction: Activity基类
 * @author: T19
 * @time: 2022.09.08 13:40
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "TZH";
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initDate();
    }

    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initDate();

    /**
     * 跳转到activity
     * @param activity 目标Activity
     */
    public void navigateTo(Class activity){
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
    }

    /**
     * 跳转到activity
     * @param activity  目标Activity
     * @param params  传递的数据集
     */
    public void navigateToWithBundle(Class activity, Map<String, Object> params){
        Intent intent = new Intent(mContext, activity);
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            bundle.putString(entry.getKey(), String.valueOf(entry.getValue()));
        }
        intent.putExtra("Args", bundle);
        startActivity(intent);
    }

    /**
     * 跳转到activity 同时处理Task
     * @param activity 目标Activity
     * @param flags 处理标志 Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK
     */
    public void navigateToWithFlag(Class activity, int flags){
        Intent intent = new Intent(mContext, activity);
        intent.setFlags(flags);
        startActivity(intent);
    }

    /**
     * 从XML文件中获取数据值
     * @param key 要获取数据的key
     * @return 要获取的数据
     */
    protected String getStringFromSP(String key){
        SharedPreferences sp = getSharedPreferences( AppConfig.SP_FILE_NAME, MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保存token信息
     * @param key  "token"
     * @param val  token值
     */
    protected void saveStringToSP(String key, String val){
        SharedPreferences sp = getSharedPreferences( AppConfig.SP_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(key, val);
        editor.apply();
    }

    protected void getClientToken(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("client_key", AppConfig.clientkey);
        params.put("client_secret", AppConfig.clientSecret);
        params.put("grand_type", ApiConfig.GRANT_TYPE_GET_CLT);


        Api.config(ApiConfig.GET_CLIENT_TOKEN, params).postRequest("multipart/form-data", new MyCallBack() {
            @Override
            public void onSuccess(String res) {
                Gson gson = new Gson();
                GetClientTokenResponse clientTokenResponse = gson.fromJson(res, GetClientTokenResponse.class);
                if ("success".equals(clientTokenResponse.getMessage())) {//响应成功
                    Log.d(TAG, "onSuccess : get client_token --->  success");
                    GetClientTokenResponse.DataBean data = clientTokenResponse.getData();
                    String client_token = data.getAccess_token();
                    //保存access_token
                    SharedPreferences sp = getSharedPreferences(AppConfig.SP_FILE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("client_token", client_token);
                    editor.apply();
                } else {
                    Log.d(TAG, "onSuccess: get client_token ---> error_code:" + clientTokenResponse.getData().getError_code() + res);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
