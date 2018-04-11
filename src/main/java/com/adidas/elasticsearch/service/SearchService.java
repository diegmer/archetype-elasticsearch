package com.adidas.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

public class SearchService {

    TransportClient client;

    public SearchService(TransportClient client) {
        this.client = client;
    }

    public SearchResponse search() {
        return client.prepareSearch().get();
    }


    /**
     * The standard query for performing full text queries, including fuzzy matching and phrase or proximity queries.
     * @param name
     * @param text
     * @return
     */
    public long searchMatchQuery(String name, String text) {
        SearchResponse response = client.prepareSearch("twitter").setQuery(QueryBuilders.matchQuery(name, text)).execute().actionGet();
        SearchHits hits = response.getHits();
        //Assert.equals(1, response.getHits().getTotalHits());
        System.out.println("response.getHits().getTotalHits() -> " + response.getHits().getTotalHits());
        return hits.getTotalHits();
    }


    /**
     * The multi-field version of the match query.
     * @param name
     * @param fieldNames
     * @return
     */
    public long searchMultiMatchQuery(String name, String... fieldNames) {
        SearchResponse response = client.prepareSearch("twitter").setQuery(QueryBuilders.multiMatchQuery(name, fieldNames)).execute().actionGet();
        SearchHits hits = response.getHits();
        return hits.getTotalHits();
    }


    /**
     * Here’re some basic operators that can be used alongside the AND/OR/NOT operators to build search queries:
     *
     * The required operator (+): requires that a specific piece of text exists somewhere in fields of a document.
     * The prohibit operator (–): excludes all documents that contain a keyword declared after the (–) symbol.
     * @param filter
     * @return
     */
    public long searchSimpleQueryStringQuery(String filter) {
        SearchResponse response = client.prepareSearch("twitter").setQuery(QueryBuilders.simpleQueryStringQuery(filter)).execute().actionGet();
        SearchHits hits = response.getHits();
        return hits.getTotalHits();
    }


    /**
     *
     * @return
     */
    public List<String> getMatchAllQueryData(String index) {
        QueryBuilder query = matchAllQuery();
        System.out.println("getMatchAllQueryCount query =>" + query.toString());
        SearchHit[] hits = client.prepareSearch(index).setQuery(query).execute().actionGet().getHits().getHits();
        List<String> list = new ArrayList<String>();
        for (SearchHit hit : hits) {
            list.add(hit.getSourceAsString());
        }
        return list;
    }


}
