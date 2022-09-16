package com.team8.mini_tiktok.entity.data;

/**
 * @introduction: 用户信息实体类
 * @author: T19
 * @time: 2022.09.11 02:14
 */
public class UserInfoEntity {
    private String avatar;
    private String avatar_larger;
    private String captcha;
    private String city;
    private String client_key;
    private String country;
    private String desc_url;
    private String description;
    private String district;
    private String e_account_role;
    private int error_code;
    private int gender;
    private String log_id;
    private String nickname;
    private String open_id;
    private String province;
    private String union_id;

    public String getAvatar() {
        return avatar;
    }

    public String getAvatar_larger() {
        return avatar_larger;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getCity() {
        return city;
    }

    public String getClient_key() {
        return client_key;
    }

    public String getCountry() {
        return country;
    }

    public String getDesc_url() {
        return desc_url;
    }

    public String getDescription() {
        return description;
    }

    public String getDistrict() {
        return district;
    }

    public String getE_account_role() {
        return e_account_role;
    }

    public int getError_code() {
        return error_code;
    }

    public int getGender() {
        return gender;
    }

    public String getLog_id() {
        return log_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getOpen_id() {
        return open_id;
    }

    public String getProvince() {
        return province;
    }

    public String getUnion_id() {
        return union_id;
    }
}