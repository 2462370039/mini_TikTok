package com.team8.mini_tiktok.entity;

import java.io.Serializable;

/**
 * @introduction: 获取access_token响应实体类
 * @author: T19
 * @time: 2022.09.08 21:25
 */
public class GetAccessResponse implements Serializable {

    private DataBean data;
    private String message;

    public DataBean getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataBean {
        private String access_token;
        private String captcha;
        private String desc_url;
        private String description;
        private int error_code;
        private int expires_in;
        private String log_id;
        private String open_id;
        private int refresh_expires_in;
        private String refresh_token;
        private String scope;

        public String getAccess_token() {
            return access_token;
        }

        public String getCaptcha() {
            return captcha;
        }

        public String getDesc_url() {
            return desc_url;
        }

        public String getDescription() {
            return description;
        }

        public int getError_code() {
            return error_code;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public String getLog_id() {
            return log_id;
        }

        public String getOpen_id() {
            return open_id;
        }

        public int getRefresh_expires_in() {
            return refresh_expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public String getScope() {
            return scope;
        }
    }
}
