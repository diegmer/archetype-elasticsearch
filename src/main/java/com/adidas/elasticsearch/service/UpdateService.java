package com.adidas.elasticsearch.service;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class UpdateService {

    TransportClient client;

    public UpdateService(TransportClient client) {
        this.client = client;
    }

    /**
     *
     * @param index -> Name index to update
     * @param field -> Field you update on
     * @param value -> New value to field
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public UpdateResponse updateDocument(String index, String field, String value) throws ExecutionException, InterruptedException, IOException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index);
        updateRequest.type("tweet");
        updateRequest.id("5");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field(field, value)
                .endObject());
        UpdateResponse response = client.update(updateRequest).get();
        return response;
    }

}
