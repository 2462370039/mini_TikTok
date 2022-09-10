package com.team8.mini_tiktok.douyinapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bytedance.sdk.open.aweme.CommonConstants;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.handler.IApiEventHandler;
import com.bytedance.sdk.open.aweme.common.model.BaseReq;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;

import com.google.gson.Gson;
import com.team8.mini_tiktok.AppConfig;
import com.team8.mini_tiktok.activity.HomeActivity;
import com.team8.mini_tiktok.entity.GetAccessResponse;
import com.team8.mini_tiktok.entity.GetClientTokenResponse;
import com.team8.mini_tiktok.network.Api;
import com.team8.mini_tiktok.network.ApiConfig;
import com.team8.mini_tiktok.network.MyCallBack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 主要功能：接受授权返回结果的activity
 * <p>
 * <p>
 * 也可通过request.callerLocalEntry = "com.xxx.xxx...activity"; 定义自己的回调类
 */
public class DouYinEntryActivity extends Activity implements IApiEventHandler {

    public static final String TAG = "TZH";

    DouYinOpenApi douYinOpenApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        douYinOpenApi = DouYinOpenApiFactory.create(this);
        douYinOpenApi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        //授权成功,获取authCode
        if (resp.getType() == CommonConstants.ModeType.SEND_AUTH_RESPONSE) {
            Authorization.Response response = (Authorization.Response) resp;
            Intent intent = null;
            if (resp.isSuccess()) {
                Toast.makeText(this, "授权成功，获得权限：" + response.grantedPermissions,
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "authCode:" + response.authCode, Toast.LENGTH_LONG).show();
                //TODO:删除
                Log.d("TZH", "onResp: authCode=" + response.authCode);
                getAccessToken(response.authCode);
            } else {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onErrorIntent(Intent intent) {
        // 错误数据
        Toast.makeText(this, "Intent出错", Toast.LENGTH_LONG).show();
    }

    private void getAccessToken(String authCode){
        HashMap<String, Object> params = new HashMap<>();
        params.put("client_secret", AppConfig.clientSecret);
        params.put("code", authCode);
        params.put("grant_type", ApiConfig.GRANT_TYPE_GET_ACT);
        params.put("client_key", AppConfig.clientkey);

        /*未封装
        //FormBody formBody = new FormBody.Builder().build();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("client_secret", AppConfig.clientSecret);
        builder.add("code", authCode);
        builder.add("grant_type", ApiConfig.GRANT_TYPE);
        builder.add("client_key", AppConfig.clientkey);
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(ApiConfig.SERVER_ADDRESS + ApiConfig.GET_ACCESS_TOKEN)
                .addHeader("contentType", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("TAG", "onFailure: ", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String res = response.body().string();
                Log.d("TAG", "onResponse: res=" + res);
                Gson gson = new Gson();
                GetAccessResponse accessResponse = gson.fromJson(res, GetAccessResponse.class);
                if ("success".equals(accessResponse.getMessage())){
                    GetAccessResponse.DataBean dataBean = accessResponse.getData();
                    Log.d("TAG", "onResponse: token=" + dataBean.getAccess_token());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DouYinEntryActivity.this, "获取到token", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });*/
        Api.config(ApiConfig.GET_ACCESS_TOKEN, params).postRequest( "application/x-www-form-urlencoded", new MyCallBack() {
            @Override
            public void onSuccess(String res) {
                Gson gson = new Gson();
                GetAccessResponse accessResponse = gson.fromJson(res, GetAccessResponse.class);
                if ("success".equals(accessResponse.getMessage())){//响应成功
                    Log.d(TAG, "onSuccess : get access_token --->  success");
                    GetAccessResponse.DataBean data = accessResponse.getData();
                    String access_token = data.getAccess_token();
                    String refresh_token = data.getRefresh_token();
                    //TODO:delete
                    Log.d("TZH", "onSuccess: access_token=" + access_token);
                    Log.d("TZH", "onSuccess: refresh_token=" + refresh_token);
                    Log.d("TZH", "onSuccess: response=" + res);

                    //保存access_token
                    SharedPreferences sp = getSharedPreferences(AppConfig.SP_FILE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("access_token", access_token);
                    editor.apply();

                    //获取client_access
                    getClientToken();

                    Intent in = new Intent(DouYinEntryActivity.this, HomeActivity.class);
                    startActivity(in);
                } else {
                    Log.e(TAG, "onSuccess : get access_token --->  error_code:" + accessResponse.getData().getError_code());
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void getClientToken(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("client_key", AppConfig.clientkey);
        params.put("client_secret", AppConfig.clientSecret);
        params.put("grant_type", ApiConfig.GRANT_TYPE_GET_CLT);


        //未封装
        /*RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("client_key", "awbdn4d2ldjf16de")
                .addFormDataPart("client_secret", "3109788557d63054134")
                .addFormDataPart("grant_type", "client_credential")
                .build();

        Request request = new Request.Builder()
                .url("https://open.douyin.com/oauth/client_token/")
                .addHeader("Content-Type", "multipart/form-data")
                .post(requestBody)
                .build();*/
        /*FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()){
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        RequestBody requestBodyPost = builder.build();

        Request request = new Request.Builder()
                .url("https://open.douyin.com/oauth/client_token/")
                .addHeader("Content-Type", contentType)
                .post(requestBodyPost)
                .build();

        Log.d("TZH", "postRequest: request" + request+ "body" + requestBody);
        OkHttpClient client = new OkHttpClient();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String res = Objects.requireNonNull(response.body()).string();
                String client_token;
                Gson gson = new Gson();
                GetClientTokenResponse clientTokenResponse = gson.fromJson(res, GetClientTokenResponse.class);
                if ("success".equals(clientTokenResponse.getMessage())) {//响应成功
                    Log.d(TAG, "onSuccess : get client_token --->  success");
                    GetClientTokenResponse.DataBean data = clientTokenResponse.getData();
                    client_token = data.getAccess_token();
                    //保存access_token
                    SharedPreferences sp = getSharedPreferences(AppConfig.SP_FILE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("client_token", client_token);
                    editor.apply();
                } else {
                    Log.d(TAG, "onSuccess: get client_token ---> error_code:" + clientTokenResponse.getData().getError_code());
                }
            }
        });*/
        Api.config(ApiConfig.GET_CLIENT_TOKEN, params).postRequest("multipart/form-data;charset=utf-8", new MyCallBack() {
            @Override
            public void onSuccess(String res) {
                String client_token;
                Gson gson = new Gson();
                GetClientTokenResponse clientTokenResponse = gson.fromJson(res, GetClientTokenResponse.class);
                if ("success".equals(clientTokenResponse.getMessage())) {//响应成功
                    Log.d(TAG, "onSuccess : get client_token --->  success");
                    GetClientTokenResponse.DataBean data = clientTokenResponse.getData();
                    client_token = data.getAccess_token();
                    //保存access_token
                    SharedPreferences sp = getSharedPreferences(AppConfig.SP_FILE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("client_token", client_token);
                    editor.apply();
                } else {
                    Log.d(TAG, "onSuccess: get client_token ---> error_code:" + clientTokenResponse.getData().getError_code());
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
