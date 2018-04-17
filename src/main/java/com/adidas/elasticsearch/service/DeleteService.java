package com.adidas.elasticsearch.service;

import net.serenitybdd.core.Serenity;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DeleteService {

    private static final String ALL_INDICES = "_all";

    TransportClient client;

    public DeleteService(TransportClient client) {
        this.client = client;
    }

    /**
     * Delete a index from elasticsearch server
     *
     * @param index index to delete
     */
    public void deleteIndex(String index) {
        try {
            DeleteIndexRequest indexRequest = new DeleteIndexRequest(index);
            DeleteIndexResponse deleteIndexResponse = client.admin().indices().delete(indexRequest).actionGet();
            boolean acknowledged = deleteIndexResponse.isAcknowledged();
            Serenity.setSessionVariable("acknowledged").to(acknowledged);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception occurred while delete Document :", ex);
            Assert.fail("Exception occurred while delete Document :" + ex);
        }
    }


    /**
     * Delete all index from elasticsearch server
     */
    public void deleteAllIndices() {
        ActionFuture<DeleteIndexResponse> deleteIndexResponses = client.admin().indices().delete(new DeleteIndexRequest(ALL_INDICES));
        client.admin().indices().flush(new FlushRequest(ALL_INDICES));
        boolean acknowledged = deleteIndexResponses.actionGet().isAcknowledged();
        Serenity.setSessionVariable("acknowledged").to(acknowledged);
    }


    /**
     * Delete one index by name index, type and id
     *
     * @param index -> Index to delete
     * @param type  -> Type index to delete
     * @param id    -> Id to delete
     * @return DeleteResponse
     */
    public DeleteResponse delete(String index, String type, String id) {
        DeleteResponse response = client.prepareDelete(index, type, id).get();
        return response;
    }


    //TODO

    /**
     * @param field
     * @param value
     * @return
     */
    public DeleteByQueryResponse deleteByQuery(String index, String type, String field, String value) {
        DeleteByQueryResponse response = new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
                .setIndices(index)
                .setTypes(type)
                .setQuery(QueryBuilders.matchQuery(field, value))
                .execute().actionGet();
        return response;
    }

}
