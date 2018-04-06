package com.adidas.serenitySteps;

import com.adidas.elasticsearch.ClientElastic;
import com.adidas.elasticsearch.service.CreateService;
import com.adidas.elasticsearch.service.DeleteService;
import net.thucydides.core.annotations.Step;

import java.net.UnknownHostException;

public class ExampleSteps {

    private ClientElastic elastic;

    @Step
    public void deleteAllIndices() throws UnknownHostException {
        DeleteService delete = new DeleteService(elastic.getTransportClient());
    }


    @Step
    public void createIndex(String index) throws UnknownHostException {
        CreateService create = new CreateService(elastic.getTransportClient(elastic.getSettings()));
        create.create(index, "tweet");
    }

}
