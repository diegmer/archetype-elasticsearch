package com.adidas.elasticsearch.service;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;

public class SearchService {

    TransportClient client;

    public SearchService(TransportClient client) {
        this.client = client;
    }

    public SearchResponse search() {
        return client.prepareSearch().get();
    }

//    public SearchRequestBuilder searchMatchQuery(String name, String text) {
//        return client.prepareSearch().setQuery(QueryBuilders.matchQuery(name, text)).setSize(1);
//    }

    public SearchResponse searchMatchQuery(String name, String text) {
        SearchResponse response = client.prepareSearch().setQuery(QueryBuilders.matchQuery(name, text)).execute().actionGet();
        return response;
    }
}
