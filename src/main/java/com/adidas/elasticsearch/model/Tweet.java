package com.adidas.elasticsearch.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Tweet {

    private String user;
    private long postDate;
//    private String postDate;
    private String message;
    private Map<String, Object> tweetMapJson = new HashMap<String, Object>();
    ;

    public Tweet(String user, String message) {
        this.user = user;
        this.postDate = new Date().getTime();
//        this.postDate = new Date().toString();
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

//    public String getPostDate() {
//        return postDate;
//    }

    public long getPostDate() {
        return postDate;
    }

//    public void setPostDate(String postDate) {
//        this.postDate = postDate;
//    }

    public void setPostDate(long postDate) {
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
