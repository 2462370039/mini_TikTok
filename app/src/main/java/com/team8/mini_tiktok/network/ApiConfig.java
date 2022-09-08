package com.team8.mini_tiktok.network;

/**
 * @introduction: 接口配置类
 * @author: T19
 * @time: 2022.09.08 12:03
 */
public class ApiConfig {
    /**
     * 服务地址
     */
    public static final String SERVER_ADDRESS = "https://open.douyin.com";
    /**
     * OAuth 授权码请求（获取临时票据code）
     */
    public static final String GET_CODE = " /platform/oauth/connect/";
    /**
     * 获取token令牌
     */
    public static final String GET_ACCESS_TOKEN = "/oauth/access_token";

}
