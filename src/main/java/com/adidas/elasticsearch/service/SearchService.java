package com.adidas.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

public class SearchService {

    TransportClient client;

    public SearchService(TransportClient client) {
        this.client = client;
    }

//    public SearchResponse search() {
//        return client.prepareSearch().get();
//    }


    //DONE

    /**
     * The standard query for performing full text queries, including fuzzy matching and phrase or proximity queries.
     *
     * @param index -> The index where search
     * @param name  -> Field you query on
     * @param text  -> Text you are looking for
     * @return
     */
    public SearchResponse searchMatchQuery(String index, String name, String text) {
        SearchResponse response = client.prepareSearch(index).setQuery(QueryBuilders.matchQuery(name, text)).execute().actionGet();
        return response;
    }


    //DONE

    /**
     * The multi-field version of the match query.
     *
     * @param index      where search
     * @param query      -> Text you are looking for
     * @param fieldNames -> Fields you query on
     * @return
     */
    public SearchResponse searchMultiMatchQuery(String index, String query, String... fieldNames) {
        //With Operator
        //SearchResponse response = client.prepareSearch(index).setQuery(QueryBuilders.multiMatchQuery(query, fieldNames).operator(Operator.AND)).execute().actionGet();
        SearchResponse response = client.prepareSearch(index).setQuery(QueryBuilders.multiMatchQuery(query, fieldNames)).execute().actionGet();
        return response;
    }


    //DONE

    /**
     * Here’re some basic operators that can be used alongside the AND/OR/NOT operators to build search queries:
     * <p>
     * The required operator (+): requires that a specific piece of text exists somewhere in fields of a document.
     * The prohibit operator (–): excludes all documents that contain a keyword declared after the (–) symbol.
     * The simple_query_string supports the following special characters:
     * <p>
     * + signifies AND operation
     * | signifies OR operation
     * - negates a single token
     * " wraps a number of tokens to signify a phrase for searching
     * * at the end of a term signifies a prefix query
     * ( and ) signify precedence
     * ~N after a word signifies edit distance (fuzziness)
     * ~N after a phrase signifies slop amount
     *
     * @param index  The index where search
     * @param filter
     * @return
     */
    public SearchResponse searchSimpleQueryStringQuery(String index, String filter) {
        SearchResponse response = client.prepareSearch(index).setQuery(QueryBuilders.simpleQueryStringQuery(filter)).execute().actionGet();
        return response;
    }


    //DONE
    //ToRefactor
    /**
     * @param index The index where search
     * @return
     */
    public SearchResponse searchBoolQuery(String index) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder qb = boolQuery()
                .must(termQuery("user", "mario"))
                .must(termQuery("message", "mario"))
                .mustNot(termQuery("content", "test2"));
        searchSourceBuilder.query(qb);

        SearchResponse response = client
                .prepareSearch(index)
                .setTypes("tweet")
                .setQuery(qb)
                .execute().actionGet();

        return response;
    }


}
