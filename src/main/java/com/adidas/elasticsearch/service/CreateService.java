package com.adidas.elasticsearch.service;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Map;

public class CreateService {

    TransportClient client;

    public CreateService(TransportClient client) {
        this.client = client;
    }

    //Index document
    //The following example indexes a JSON document into an index called twitter, under a type called tweet

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

}
