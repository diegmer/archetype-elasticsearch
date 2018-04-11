package com.adidas.serenitySteps;

import com.adidas.elasticsearch.ClientElastic;
import com.adidas.elasticsearch.service.CreateService;
import com.adidas.elasticsearch.service.DeleteService;
import com.adidas.elasticsearch.service.SearchService;
import com.adidas.elasticsearch.service.UpdateService;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Response;
import org.junit.After;
import org.junit.Assert;

import java.io.IOException;
import java.net.UnknownHostException;
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
    public void deleteIndex(String index, String type, String id) throws UnknownHostException {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.delete(index, type, id);
    }

    @Step
    public void deleteByQuery(String field, String value) throws UnknownHostException {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.deleteByQuery(field, value);
        Assert.assertTrue("Fail to delete", "1".equalsIgnoreCase(String.valueOf(delete.deleteByQuery(field, value))));
    }

    @Step
    public void isAcknowledged() throws UnknownHostException {
        if (Serenity.sessionVariableCalled("acknowledged") != null) {
            Assert.assertTrue("acknowledged is false", Serenity.sessionVariableCalled("acknowledged"));
        }
    }


    @Step
    public void createIndex(String index) throws UnknownHostException {
        //Two options to create index with specific settings and with defects settings
        //CreateService create = new CreateService(elastic.getTransportClient(elastic.getSettings()));
        CreateService create = new CreateService(elastic.getTransportClient());
        create.create(index, "tweet");
    }

    @Step
    public void createIndex(String index, String user, String sms) throws IOException {
        //Two options to create index with specific settings and with defects settings
        //CreateService create = new CreateService(elastic.getTransportClient(elastic.getSettings()));
        CreateService create = new CreateService(elastic.getTransportClient());
        create.create(index, "tweet", user, sms);
    }

    @Step
    public void updateIndex(String index, String type) throws IOException, ExecutionException, InterruptedException {
        UpdateService update = new UpdateService(elastic.getTransportClient());
        update.updateDocument(index, type);
    }

    @Step
    public void search(String field, String value) throws UnknownHostException {
        SearchService search = new SearchService(elastic.getTransportClient());
        long hitsCount = search.searchMatchQuery(field, value);
        Serenity.setSessionVariable("hitsCount").to(hitsCount);
//        if (!search.searchMatchQuery(field, value).status().toString().equalsIgnoreCase("OK")) {
//            Assert.fail("Error");
//        }
    }


    /**
     * Method to verify an status code received from the scenario
     * @param expectedResult Expected status code in the response
     * */
    @Step
    public void verifyResult(String expectedResult){
        String result = Serenity.sessionVariableCalled("response");
        Assert.assertEquals("Result doesn't match", expectedResult, result);
    }

    /**
     *
     * @param expectedHitsCounts
     */
    @Step
    public void verifyHit(String expectedHitsCounts){
        String hitsCount = Serenity.sessionVariableCalled("hitsCount").toString();
        Assert.assertEquals("Result doesn't match", expectedHitsCounts, hitsCount);
    }

    @After
    public void closeClient() {
        elastic.closeClient();
    }

}
