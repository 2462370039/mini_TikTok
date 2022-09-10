package com.team8.mini_tiktok.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.team8.mini_tiktok.AppConfig;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @introduction: Okhttp网络请求封装类
 * @author: T19
 * @time: 2022.09.08 12:10
 */
public class Api {
    public static final String TAG = "api";

    private static OkHttpClient client;
    private static String requestUrl;
    private static HashMap<String, Object> mParams;
    public static Api api = new Api();

    public Api(){
    }

    public static Api config(String url, HashMap<String, Object> params){
        //1创建OkHttp
         client = new OkHttpClient.Builder()
                 .build();
         requestUrl = ApiConfig.SERVER_ADDRESS + url;
         mParams = params;
         return api;
    }

    private String getUrlWithParams(String url, HashMap<String, Object> params){
        if (params != null && !params.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for ( Map.Entry<String, Object> entry : params.entrySet() ) {
                if (TextUtils.isEmpty(builder.toString())) {
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(entry.getKey()).append("=").append(entry.getValue());
            }
            url += builder.toString();
        }
        Log.d(TAG, "getUrlWithParams: url=" + url);
        return url;
    }

    /**
     * post请求(传 x-www-form-urlencoded)
     * @param callBack 回调接口
     */
    public void postRequest(String contentType, final MyCallBack callBack){

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : mParams.entrySet()){
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        RequestBody requestBodyPost = builder.build();

        Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader("Content-Type", contentType)
                .post(requestBodyPost)
                .build();

        Log.d("TZH", "postRequest: request=" + request);
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String res = Objects.requireNonNull(response.body()).string();
                callBack.onSuccess(res);
            }
        });
    }


    public void getRequest(String contentType, String token, final MyCallBack callBack){
        //获取token
//        SharedPreferences sp = context.getSharedPreferences(AppConfig.SP_FILE_NAME, Context.MODE_PRIVATE);
//        String token = sp.getString("access_token", "");
        //拼接url
        String url = getUrlWithParams(requestUrl, mParams);
        Log.d(TAG, "getRequest: url=" + url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("access-token", token)
                .addHeader("Content-Type", contentType)
                .get()
                .build();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String res = Objects.requireNonNull(response.body()).string();
                Log.d(TAG, "onResponse: res=" + res);
                callBack.onSuccess(res);
            }
        });
    }
}
