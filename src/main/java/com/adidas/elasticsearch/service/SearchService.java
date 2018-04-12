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


    //DONE
    /**
     * The standard query for performing full text queries, including fuzzy matching and phrase or proximity queries.
     *
     * @param index where search
     * @param name -> Field yoy query on
     * @param text -> Text you are looking for
     * @return
     */
    public long searchMatchQuery(String index, String name, String text) {
        SearchResponse response = client.prepareSearch(index).setQuery(QueryBuilders.matchQuery(name, text)).execute().actionGet();
        SearchHits hits = response.getHits();
        return hits.getTotalHits();
    }


    /**
     * The multi-field version of the match query.
     *
     * @param query -> Text you are looking for
     * @param fieldNames -> Fields you query on
     * @return
     */
    public long searchMultiMatchQuery(String index, String query, String... fieldNames) {
        SearchResponse response = client.prepareSearch(index).setQuery(QueryBuilders.multiMatchQuery(query, fieldNames)).execute().actionGet();
        SearchHits hits = response.getHits();
        return hits.getTotalHits();
    }


    //DONE
    /**
     * Here’re some basic operators that can be used alongside the AND/OR/NOT operators to build search queries:
     * <p>
     * The required operator (+): requires that a specific piece of text exists somewhere in fields of a document.
     * The prohibit operator (–): excludes all documents that contain a keyword declared after the (–) symbol.
     * The simple_query_string supports the following special characters:
     *
     * + signifies AND operation
     * | signifies OR operation
     * - negates a single token
     * " wraps a number of tokens to signify a phrase for searching
     * * at the end of a term signifies a prefix query
     * ( and ) signify precedence
     * ~N after a word signifies edit distance (fuzziness)
     * ~N after a phrase signifies slop amount
     *
     * @param index where search
     * @param filter
     * @return
     */
    public long searchSimpleQueryStringQuery(String index, String filter) {
        SearchResponse response = client.prepareSearch(index).setQuery(QueryBuilders.simpleQueryStringQuery(filter)).execute().actionGet();
        SearchHits hits = response.getHits();
        return hits.getTotalHits();
    }


    /**
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
