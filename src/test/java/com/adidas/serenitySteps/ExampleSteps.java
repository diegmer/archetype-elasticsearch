package com.adidas.serenitySteps;

import com.adidas.elasticsearch.ClientElastic;
import com.adidas.elasticsearch.service.CreateService;
import com.adidas.elasticsearch.service.DeleteService;
import com.adidas.elasticsearch.service.SearchService;
import com.adidas.elasticsearch.service.UpdateService;
import net.thucydides.core.annotations.Step;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Assert;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class ExampleSteps {

    private ClientElastic elastic = new ClientElastic();

    @Step
    public void deleteAllIndices() throws UnknownHostException {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.deleteAllIndices();
    }

    @Step
    public void deleteIndex(String index) throws UnknownHostException {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.deleteIndex(index);
    }

    @Step
    public void deleteByQuery(String field, String value) throws UnknownHostException {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.deleteByQuery(field, value);
    }


    @Step
    public void createIndex(String index) throws UnknownHostException {
        //Two options to create index with specific settings and with defects settings
        //CreateService create = new CreateService(elastic.getTransportClient(elastic.getSettings()));
        CreateService create = new CreateService(elastic.getTransportClient());
        create.create(index, "tweet");
    }

    @Step
    public void updateIndex(String index, String type) throws IOException, ExecutionException, InterruptedException {
        UpdateService update = new UpdateService(elastic.getTransportClient());
        update.updateDocument(index, type);
    }

    @Step
    public void search(String field, String value) throws UnknownHostException {
        SearchService search = new SearchService(elastic.getTransportClient());
//        if (!search.searchMatchQuery(field, value).status().toString().equalsIgnoreCase("OK")) {
//            Assert.fail("Error");
//        }
        long hitsCount = search.searchMatchQuery(field, value);
        System.out.println("HitsCount = " + hitsCount);
//        if (hitsCount == 0) {
//            Assert.fail("Not found");
//        }

    }

}
