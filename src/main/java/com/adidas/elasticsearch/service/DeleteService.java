package com.adidas.elasticsearch.service;

import net.serenitybdd.core.Serenity;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
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

    //DONE
    /**
     * Delete a index
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


    //DONE
    /**
     * Delete all index from elasticsearch server
     */
    public void deleteAllIndices() {
        ActionFuture<DeleteIndexResponse> deleteIndexResponses = client.admin().indices().delete(new DeleteIndexRequest(ALL_INDICES));
        client.admin().indices().flush(new FlushRequest(ALL_INDICES));
        boolean acknowledged = deleteIndexResponses.actionGet().isAcknowledged();
        Serenity.setSessionVariable("acknowledged").to(acknowledged);
    }


    //DONE
    /**
     * @param index
     * @param type
     * @param id
     * @return
     */
    public void delete(String index, String type, String id) {
        DeleteResponse response = client.prepareDelete(index, type, id).get();
        Serenity.setSessionVariable("response").to(response.getResult().toString());
    }



    //TODO
    /**
     * @param name
     * @param text
     * @return
     */
    public long deleteByQuery(String name, String text) {
//        return new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE).setQuery(QueryBuilders.matchQuery(name, text))
//                .execute().actionGet().getTotalDeleted();

        long totalDeleted = new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
                .setIndices("twitter")
                .setTypes("tweet")
                .setQuery(QueryBuilders.matchQuery(name, text))
                .execute().actionGet().getTotalDeleted();
        return totalDeleted;



    }


}
