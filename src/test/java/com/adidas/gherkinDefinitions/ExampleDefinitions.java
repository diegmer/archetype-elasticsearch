package com.adidas.gherkinDefinitions;

import com.adidas.serenitySteps.ExampleSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ExampleDefinitions {

    @Steps
    private ExampleSteps exampleSteps;

    @When("^I request to create a new index \"([^\"]*)\" with values for \"([^\"]*)\"")
    public void iCreateANewIndexWithDefaultValues(String index, String type, List<String> data) throws Throwable {
        exampleSteps.createIndex(index, type, data);
    }

    @When("^I request to delete a index by name \"([^\"]*)\"$")
    public void iRequestToDeleteAIndexByName(String index) throws Throwable {
        exampleSteps.deleteIndex(index);
    }

    @When("^I request to delete a \"([^\"]*)\" by value \"([^\"]*)\" a index \"([^\"]*)\" type \"([^\"]*)\"$")
    public void iRequestToDeleteAByValueAIndexType(String index, String type, String field, String value) throws Throwable {
        exampleSteps.deleteByQuery(index, type, field, value);
    }

    @When("^I request to delete all index$")
    public void iRequestToDeleteAllIndex() throws Throwable {
        exampleSteps.deleteAllIndices();
    }

    @When("^I request to delete a index \"([^\"]*)\" type \"([^\"]*)\" by ID \"([^\"]*)\"$")
    public void iRequestToDeleteAIndexTypeByID(String index, String type, String id) throws Throwable {
        exampleSteps.deleteIndex(index, type, id);
    }


    @When("^I request to search field \"([^\"]*)\" with value \"([^\"]*)\" in index \"([^\"]*)\"$")
    public void iRequestToSearchFieldWithValue(String field, String value, String index) throws Throwable {
        exampleSteps.searchMatchQuery(index, field, value);
    }

    @When("^I request to search with filter \"([^\"]*)\" in index \"([^\"]*)\"$")
    public void iRequestToSearchWithFilterInIndex(String filter, String index) throws Throwable {
        exampleSteps.searchSimpleQueryStringQuery(index, filter);
    }

    @When("^I request to search \"([^\"]*)\" in fields \"([^\"]*)\" in index \"([^\"]*)\"$")
    public void iRequestToSearchInFieldsInIndex(String query, List<String> fields, String index) throws Throwable {
        exampleSteps.searchMultiMatchQuery(query, fields, index);
    }

    @When("^I request to search bool query in index \"([^\"]*)\" and type \"([^\"]*)\"$")
    public void iRequestToSearchBoolQueryInIndex(String index, String type) throws Throwable {
        exampleSteps.searchBoolQueryInIndex(index,type);
    }

    @When("^I request to update field \"([^\"]*)\" with value \"([^\"]*)\" in index \"([^\"]*)\"$")
    public void iRequestToUpdateFieldWithValueInIndex(String field, String value, String index) throws Throwable {
        exampleSteps.updateMatchQuery(index, field, value);
    }

    @Then("^I should get (.*) status code in (?:create|delete|update|search)$")
    public void iShouldGetExpectedStatusCodeStatusCodeIn(int expectedStatusCode, String operation) {
        exampleSteps.verifyStatusCode(expectedStatusCode, operation);
    }

    @Then("^I should get \"([^\"]*)\" result$")
    public void iShouldGetResult(String expectedResult) throws Throwable {
        exampleSteps.verifyResult(expectedResult);
    }

    @Then("^I should get \"([^\"]*)\" (?:hits|hit)$")
    public void iShouldGetHit(String expectedHit) throws Throwable {
        exampleSteps.verifyHit(expectedHit);
    }

    @Then("^I should is acknowledged$")
    public void iShouldIsAcknowledged() throws Throwable {
        exampleSteps.isAcknowledged();
    }

}
