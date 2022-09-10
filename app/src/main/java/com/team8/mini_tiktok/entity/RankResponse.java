package com.team8.mini_tiktok.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @introduction: 电影榜单响应实体类
 * @author: T19
 * @time: 2022.09.10 12:00
 */
public class RankResponse implements Serializable {

    private DataBean data;
    private ExtraBean extra;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public static class DataBean {
        private String active_time;
        private String description;
        private String error_code;
        @SerializedName("list")
        private List<RankList> rankList;

        public String getActive_time() {
            return active_time;
        }

        public void setActive_time(String active_time) {
            this.active_time = active_time;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public List<RankList> getRankList() {
            return rankList;
        }

        public void setRankList(List<RankList> rankList) {
            this.rankList = rankList;
        }

    }

    public static class ExtraBean {
        private String description;
        private String error_code;
        private String logid;
        private String now;
        private String sub_description;
        private String sub_error_code;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public String getLogid() {
            return logid;
        }

        public void setLogid(String logid) {
            this.logid = logid;
        }

        public String getNow() {
            return now;
        }

        public void setNow(String now) {
            this.now = now;
        }

        public String getSub_description() {
            return sub_description;
        }

        public void setSub_description(String sub_description) {
            this.sub_description = sub_description;
        }

        public String getSub_error_code() {
            return sub_error_code;
        }

        public void setSub_error_code(String sub_error_code) {
            this.sub_error_code = sub_error_code;
        }
    }
}
