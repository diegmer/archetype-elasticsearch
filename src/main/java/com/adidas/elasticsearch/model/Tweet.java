package com.adidas.elasticsearch.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 *  Class Tweet for usage examples of Elasticsearch
 */
public class Tweet {

    private String user;
    private String postDate;
    private String message;
    private Map<String, Object> tweetMapJson = new HashMap<String, Object>();

    public Tweet() {
        Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        this.postDate = formatter.format(new Date());
    }

    public Tweet(String user, String message) {
        //Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        this.user = user;
        this.postDate = formatter.format(new Date());
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getTweetMapJson() {
        return tweetMapJson;
    }

    public void setTweetMapJson(Tweet t) {
        tweetMapJson.put("user", t.getUser());
        //tweetMapJson.put("postDate", new Date());
        tweetMapJson.put("postDate", t.getPostDate());
        tweetMapJson.put("message", t.getMessage());
    }

}
