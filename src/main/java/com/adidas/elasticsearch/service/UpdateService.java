package com.adidas.elasticsearch.service;

import com.adidas.elasticsearch.util.Tweet;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class UpdateService {

    TransportClient client;

    public UpdateService(TransportClient client) {
        this.client = client;
    }

    public void updateDocument(String index, String type) throws IOException, ExecutionException, InterruptedException {
//        XContentBuilder jsonBuilder = jsonBuilder();
//        Tweet tweet = new Tweet("diegmer2", "hi hi hi hi hi hi hi");
//        tweet.setTweetMapJson(tweet);
//        jsonBuilder.map(tweet.getTweetMapJson());
//        UpdateResponse updateResponse = updateIndex(index, type, "1", jsonBuilder);


        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index);
        updateRequest.type(type);
        updateRequest.id("1");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("user", "diegmer2")
                .endObject());
        client.update(updateRequest).get();

//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.index("index");
//        updateRequest.type("type");
//        updateRequest.id("1");
//        updateRequest.doc(jsonBuilder()
//                .startObject()
//                .field("gender", "male")
//                .endObject());
//        client.update(updateRequest).get();
    }

    public UpdateResponse updateIndex(String index, String type, String id, XContentBuilder jsonData) {
        UpdateResponse response = null;
        try {
            response = client.prepareUpdate(index, type, id)
                    .setDoc(jsonData)
                    .execute().get();
            return response;
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(
                    Level.SEVERE, "Exception in update index :", ex);
        }
        return response;
    }
}
