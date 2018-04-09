package com.adidas.elasticsearch.service;

import com.adidas.elasticsearch.model.Tweet;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class CreateService {

    TransportClient client;

    public CreateService(TransportClient client) {
        this.client = client;
    }

    //Index document
    //The following example indexes a JSON document into an index called twitter, under a type called tweet

    public IndexResponse create(String index, String type) {
        Tweet tweet = new Tweet("diegmer", "Where is my master????");
        tweet.setTweetMapJson(tweet);
        return client.prepareIndex(index, type)
                .setSource(tweet.getTweetMapJson(), XContentType.JSON)
                .get();

    }

    /**
     * Indexes a JSON document into an index
     *
     * @param index
     * @param type
     * @param mapJson
     * @return
     */
    public IndexResponse create(String index, String type, Map<String, Object> mapJson) {
        return client.prepareIndex(index, type)
                .setSource(mapJson, XContentType.JSON)
                .get();

    }

    /**
     * Indexes a JSON document into an index
     *
     * @param index
     * @param type
     * @param json
     * @return
     */
    public IndexResponse create(String index, String type, String json) {
        return client.prepareIndex(index, type)
                .setSource(json, XContentType.JSON)
                .get();

    }

    /**
     *
     * @param index
     * @param type
     * @param user
     * @param sms
     * @return
     * @throws IOException
     */
    public IndexResponse create(String index, String type, String user, String sms) throws IOException {
        IndexResponse response = client.prepareIndex(index, type, "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", user)
                        .field("postDate", new Date())
                        .field("message", sms)
                        .endObject()
                )
                .execute()
                .actionGet();
        return response;
    }


}
