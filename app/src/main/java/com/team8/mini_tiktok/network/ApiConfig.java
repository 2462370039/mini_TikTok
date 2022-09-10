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
     * 获取access_token令牌
     */
    public static final String GET_ACCESS_TOKEN = "/oauth/access_token/";
    /**
     * 获取client_token令牌
     */
    public static final String GET_CLIENT_TOKEN = "/oauth/client_token/";



    public static final String GET_RANK = "/discovery/ent/rank/item/";



    public static final String GRANT_TYPE_GET_ACT = "authorization_code";
    public static final String GRANT_TYPE_GET_CLT = "client_credential";

    /**
     * 授权权限
     */
    public static final String SCOPE = "user_info,trial.whitelist,renew_refresh_token,discovery.ent,fans.list,following.list,video.list,video.data,fans.check";
}
