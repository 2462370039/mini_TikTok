package com.team8.mini_tiktok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team8.mini_tiktok.R;
import com.team8.mini_tiktok.entity.RankResponse;
import com.team8.mini_tiktok.network.Api;
import com.team8.mini_tiktok.network.ApiConfig;
import com.team8.mini_tiktok.network.MyCallBack;

import java.util.HashMap;

public class RankActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_rank;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {
        //获取Bundle中数据access-token
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Args");
        String type = bundle.getString("type");
        String access_token = bundle.getString("access-token");
        //TODO:if (access-token == "")
        //TODO:删除
        Log.d("TZH", "onCreate: type=" + type);
        Log.d("TZH", "onCreate: access_token=" + access_token);

        HashMap<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("version", "");
        getRankList(params, access_token);

        //请求数据
        //TODO:未封装
//        HttpUrl.Builder builder = HttpUrl.parse(ApiConfig.SERVER_ADDRESS + ApiConfig.GET_RANK).newBuilder();
//        for (Map.Entry<String, Object> entry : params.entrySet()){
//            builder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
//        }
//        HttpUrl httpUrl = builder.build();
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(httpUrl.toString())
//                .addHeader("contentType", "application/json")
//                .addHeader("access-token", access_token)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                Log.e("TZH", "onFailure: ", e);
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                String res = response.body().string();
//                Log.d("TZH", "onResponse: res=" + res);
//                TextView tvres = findViewById(R.id.tv_rank);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tvres.setText(res);
//                    }
//                });
//            }
//        });
    }

    private void getRankList(HashMap<String, Object> params, String access_token){
        Api.config(ApiConfig.GET_RANK, params).getRequest("application/json", access_token,  new MyCallBack() {
            @Override
            public void onSuccess(String res) {
                //绑定数据
                Gson gson = new Gson();
                RankResponse response = gson.fromJson(res, RankResponse.class);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}