package com.adidas.elasticsearch.service;

import com.adidas.elasticsearch.model.Tweet;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class CreateService {

    TransportClient client;

    public CreateService(TransportClient client) {
        this.client = client;
    }

    /**
     * The following example indexes a JSON document into an index under a type
     *
     * @param index -> Name of index
     * @param type  -> Name od type
     */
    public IndexResponse create(String index, String type, List<String> data) {
        //Tweet tweet = new Tweet("diegmer", "Where is my master????");
        Tweet tweet = new Tweet();
        for (int i = 0; i < data.size(); i++) {
            tweet.setUser(data.get(i++));
            tweet.setMessage(data.get(i++));
        }
        tweet.setTweetMapJson(tweet);
        IndexResponse response = client.prepareIndex(index, type, "1")
                .setSource(tweet.getTweetMapJson(), XContentType.JSON)
                .get();
        return response;
    }

    /**
     * Indexes a JSON document into an index
     *
     * @param index   -> name of index
     * @param type    -> name of type
     * @param mapJson -> Json to add
     * @return
     */
    public IndexResponse create(String index, String type, Map<String, Object> mapJson) {
        IndexResponse response = client.prepareIndex(index, type)
                .setSource(mapJson, XContentType.JSON)
                .get();
        return response;
    }

    /**
     * Indexes a JSON document, in format String,  into an index
     *
     * @param index -> name of index
     * @param type  -> name of type
     * @param json  -> Json to add in String format
     * @return
     */
    public IndexResponse create(String index, String type, String json) {
        IndexResponse response = client.prepareIndex(index, type)
                .setSource(json, XContentType.JSON)
                .get();
        return response;
    }

    /**
     * @param index
     * @param type
     * @param user
     * @param sms
     * @return
     * @throws IOException
     */
    public IndexResponse create(String index, String type, String user, String sms) throws IOException {
        IndexResponse response = client.prepareIndex(index, type)
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", user)
                        .field("postDate", new Date().getTime())
                        .field("message", sms)
                        .endObject()
                )
                .get();
        return response;
    }


}
