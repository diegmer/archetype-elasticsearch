package com.adidas.serenitySteps;

import com.adidas.elasticsearch.ClientElastic;
import com.adidas.elasticsearch.service.CreateService;
import com.adidas.elasticsearch.service.DeleteService;
import com.adidas.elasticsearch.service.SearchService;
import com.adidas.elasticsearch.service.UpdateService;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.junit.After;
import org.junit.Assert;

import java.util.List;

public class ExampleSteps {

    private ClientElastic elastic = new ClientElastic();

    /**
     * DELETE
     */

    /**
     * Performs a DELETE operation that will delete all indices from elasticsearch server
     *
     * @throws Throwable
     */
    @Step
    public void deleteAllIndices() throws Throwable {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.deleteAllIndices();
    }

    /**
     * Performs a DELETE operation that will delete one concrete index
     *
     * @param index -> Name index to delete
     * @throws Throwable
     */
    @Step
    public void deleteIndex(String index) throws Throwable {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.deleteIndex(index);
    }

    /**
     * Performs a DELETE operation that will delete one concrete index, type and id
     *
     * @param index -> Name index
     * @param type  -> Name type
     * @param id    -> Number ID
     * @throws Throwable
     */
    @Step
    public void deleteIndex(String index, String type, String id) throws Throwable {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.delete(index, type, id);
    }

    //TODO

    /**
     * @param field
     * @param value
     * @throws Throwable
     */
    @Step
    public void deleteByQuery(String field, String value) throws Throwable {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
        delete.deleteByQuery(field, value);
        Assert.assertTrue("Fail to delete", "1".equalsIgnoreCase(String.valueOf(delete.deleteByQuery(field, value))));
    }


    /**
     * Check if operation is acknowledged
     */
    @Step
    public void isAcknowledged() {
        if (Serenity.sessionVariableCalled("acknowledged") != null) {
            Assert.assertTrue("acknowledged is false", Serenity.sessionVariableCalled("acknowledged"));
        }
    }


    /**
     * Performs a CREATE operation that will create one concrete index
     *
     * @param index -> Name of index
     * @throws Throwable
     */
    @Step
    public void createIndex(String index, String type, List<String> data) throws Throwable {
        //Two options to create index with specific settings and with defects settings
        //CreateService create = new CreateService(elastic.getTransportClient(elastic.getSettings()));
        CreateService create = new CreateService(elastic.getTransportClient());
        IndexResponse response = create.create(index, type, data);
        Serenity.setSessionVariable("response").to(response.status().getStatus());
        Serenity.setSessionVariable("result").to(response.getResult().toString());
    }


    /**
     * @param index
     * @param user
     * @param sms
     * @throws Throwable
     */
    @Step
    public void createIndex(String index, String user, String sms) throws Throwable {
        //Two options to create index with specific settings and with defects settings
        //CreateService create = new CreateService(elastic.getTransportClient(elastic.getSettings()));
        CreateService create = new CreateService(elastic.getTransportClient());
        create.create(index, "tweet", user, sms);
    }


    /**
     * SEARCH
     */


    /**
     * @param index
     * @param field
     * @param value
     * @throws Throwable
     */
    @Step
    public void searchMatchQuery(String index, String field, String value) throws Throwable {
        SearchService search = new SearchService(elastic.getTransportClient());
        SearchResponse response = search.searchMatchQuery(index, field, value);
        Serenity.setSessionVariable("response").to(response);
        Serenity.setSessionVariable("hitsCount").to(response.getHits().getTotalHits());
    }


    /**
     * @param query
     * @param fields
     * @param index
     * @throws Throwable
     */
    @Step
    public void searchMultiMatchQuery(String query, List<String> fields, String index) throws Throwable {
        SearchService search = new SearchService(elastic.getTransportClient());
        String[] fieldsArgs = fields.stream().toArray(String[]::new);
        SearchResponse response = search.searchMultiMatchQuery(index, query, fieldsArgs);
        Serenity.setSessionVariable("response").to(response);
        Serenity.setSessionVariable("hitsCount").to(response.getHits().getTotalHits());
    }

    /**
     * @param index
     * @param filter
     * @throws Throwable
     */
    @Step
    public void searchSimpleQueryStringQuery(String index, String filter) throws Throwable {
        SearchService search = new SearchService(elastic.getTransportClient());
        SearchResponse response = search.searchSimpleQueryStringQuery(index, filter);
        Serenity.setSessionVariable("response").to(response);
        Serenity.setSessionVariable("hitsCount").to(response.getHits().getTotalHits());
    }


    /**
     * @param index
     * @throws Throwable
     */
    @Step
    public void searchBoolQueryInIndex(String index) throws Throwable {
        SearchService search = new SearchService(elastic.getTransportClient());
        SearchResponse response = search.searchBoolQuery(index);
        Serenity.setSessionVariable("response").to(response);
        Serenity.setSessionVariable("hitsCount").to(response.getHits().getTotalHits());
    }


    /**
     * Method to verify an status code received from the scenario
     *
     * @param expectedResult Expected status code in the response
     */
    @Step
    public void verifyResult(String expectedResult) {
        String result = Serenity.sessionVariableCalled("result");
        Assert.assertEquals("Result doesn't match", expectedResult, result);
    }

    /**
     * @param expectedHitsCounts
     */
    @Step
    public void verifyHit(String expectedHitsCounts) {
        String hitsCount = Serenity.sessionVariableCalled("hitsCount").toString();
        Assert.assertEquals("Result doesn't match", expectedHitsCounts, hitsCount);
    }

    /**
     * Closes the client.
     */
    @After
    public void closeClient() {
        elastic.closeClient();
    }


//    /**
//     * Method to verify an status code received from the scenario
//     *
//     * @param expectedStatusCode Expected status code in the response
//     */
//    @Step
//    public void verifyStatusCode(int expectedStatusCode) {
//
//        SearchResponse res = Serenity.sessionVariableCalled("response");
//        Assert.assertEquals("status code doesn't match", expectedStatusCode, res.status().getStatus());
//    }

    /**
     * Method to verify an status code received from the scenario
     *
     * @param expectedStatusCode Expected status code in the response
     */
    @Step
    public void verifyStatusCode(int expectedStatusCode, String operation) {
        if (operation.equalsIgnoreCase("search")) {
            SearchResponse searchResponse = Serenity.sessionVariableCalled("response");
            Assert.assertEquals("status code doesn't match", expectedStatusCode, searchResponse.status().getStatus());
        } else {
            DocWriteResponse response = new DocWriteResponse() {
            };
            switch (operation) {
                case "create":
                    IndexResponse indexResponse = Serenity.sessionVariableCalled("response");
                    response = indexResponse;
                    break;
                case "update":
                    UpdateResponse updateResponse = Serenity.sessionVariableCalled("response");
                    response = updateResponse;
                    break;
                case "delete":
                    DeleteResponse deleteResponse = Serenity.sessionVariableCalled("response");
                    response = deleteResponse;
                    break;
                default:
                    break;
            }
            Assert.assertEquals("status code doesn't match", expectedStatusCode, response.status().getStatus());
        }

    }


    /**
     * @param index
     * @param field
     * @param value
     * @throws Throwable
     */
    @Step
    public void updateMatchQuery(String index, String field, String value) throws Throwable {
        UpdateService update = new UpdateService(elastic.getTransportClient());
        UpdateResponse response = update.updateDocument(index, field, value);
        Serenity.setSessionVariable("response").to(response);
        Serenity.setSessionVariable("result").to(response.getResult().name());
    }

}
