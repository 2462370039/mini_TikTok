package com.team8.mini_tiktok.network;

/**
 * @introduction: 回调
 * @author: T19
 * @time: 2022.09.08 12:18
 */
public interface MyCallBack {
    //请求成功
    void onSuccess(String res);
    //请求失败
    void onFailure(Exception e);
}
