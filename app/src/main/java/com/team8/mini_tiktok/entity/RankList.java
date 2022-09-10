package com.team8.mini_tiktok.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @introduction: 电影榜单实体类
 * @author: T19
 * @time: 2022.09.10 12:06
 */
public class RankList implements Serializable {
    private List<String> actors;
    private List<String> areas;
    private List<String> directors;
    private String discussion_hot;
    private String hot;
    private String id;
    private String influence_hot;
    private String maoyan_id;
    private String name;
    private String name_en;
    private String poster;
    private String release_date;
    private String search_hot;
    private List<String> tags;
    private String topic_hot;
    private String type;

    public List<String> getActors() {
        return actors;
    }
    public List<String> getAreas() {
        return areas;
    }
    public List<String> getDirectors() {
        return directors;
    }
    public String getActorsString(){
        StringBuilder res = new StringBuilder();
        if (this.getActors() != null && this.getActors().size() > 0) {
            for (String str : this.getActors() ) {
                res.append("/").append(str);
            }
            return res.substring(1);
        }
       return "未知";
    }
    public String getAreasString(){
        StringBuilder res = new StringBuilder();
        if (this.getAreas() != null && this.getAreas().size() > 0) {
            for (String str : this.getAreas()) {
                res.append("/").append(str);
            }
            return res.substring(1);
        }
        return "未知";
    }
    public String getDirectorsString(){
        StringBuilder res = new StringBuilder();
        if (this.getDirectors() != null && this.getDirectors().size() > 0) {
            for (String str : this.getDirectors()) {
                res.append("/").append(str);
            }
            return res.substring(1);
        }
        return "未知";
    }
    public String getDiscussion_hot() {
        return discussion_hot;
    }
    public String getHot() {
        return hot;
    }
    public String getId() {
        return id;
    }
    public String getInfluence_hot() {
        return influence_hot;
    }
    public String getMaoyan_id() {
        return maoyan_id;
    }
    public String getName() {
        return name;
    }
    public String getName_en() {
        return name_en;
    }
    public String getPoster() {
        return poster;
    }
    public String getRelease_date() {
        return release_date;
    }
    public String getSearch_hot() {
        return search_hot;
    }
    public List<String> getTags() {
        return tags;
    }
    public String getTopic_hot() {
        return topic_hot;
    }
    public String getType() {
        return type;
    }
}
