package com.adidas.elasticsearch.service;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DeleteService {

    private static final String ALL_INDICES = "_all";

    TransportClient client;

    public DeleteService(TransportClient client) {
        this.client = client;
    }

    /**
     * @param index
     */
    public void deleteIndex(String index) {
//        DeleteIndexRequest indexRequest = new DeleteIndexRequest(index);
//        client.admin().indices().delete(indexRequest).actionGet();
        try {
            DeleteIndexResponse deleteIndexResponse = client.admin().indices().delete(new DeleteIndexRequest(index)).actionGet();
            if (deleteIndexResponse != null) {
                Logger.getLogger(getClass().getName()).log(
                        Level.INFO, "Index has been deleted...");
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(
                    Level.SEVERE, "Exception occurred while delete Document :", ex);
        }
    }

    /**
     *
     */
    public void deleteAllIndices() {
        client.admin().indices().delete(new DeleteIndexRequest(ALL_INDICES));
        client.admin().indices().flush(new FlushRequest(ALL_INDICES));
    }

    /**
     * @param index
     * @param type
     * @param id
     * @return
     */
    public DocWriteResponse.Result delete(String index, String type, String id) {
        DeleteResponse response = client.prepareDelete(index, type, id).get();
        return response.getResult();
        //return client.prepareDelete("test", "tweet", id).get();
    }

    /**
     * @param name
     * @return
     */
    public long deleteByQuery(String name) {
        return new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE).setQuery(QueryBuilders.termQuery("name", name))
                .execute().actionGet().getTotalDeleted();

    }


}
