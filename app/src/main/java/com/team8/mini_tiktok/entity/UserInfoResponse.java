package com.team8.mini_tiktok.entity;

import com.team8.mini_tiktok.entity.data.UserInfoEntity;

import java.io.Serializable;

/**
 * @introduction: 用户信息响应实体类
 * @author: T19
 * @time: 2022.09.11 02:10
 */
public class UserInfoResponse implements Serializable {

    private UserInfoEntity userInfo;
    private String message;

    public UserInfoEntity getUserInfo() {
        return userInfo;
    }

    public String getMessage() {
        return message;
    }
}
