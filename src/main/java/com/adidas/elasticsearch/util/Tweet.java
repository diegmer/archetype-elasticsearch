package com.adidas.elasticsearch.util;

import java.util.Date;
import java.util.Map;

public class Tweet {

    private String user;
    private long postDate;
    private String message;
    private Map<String, Object> tweetMapJson;

    public Tweet(String user, String message) {
        this.user = user;
        this.postDate = new Date().getTime();
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getPostDate() {
        return postDate;
    }

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
