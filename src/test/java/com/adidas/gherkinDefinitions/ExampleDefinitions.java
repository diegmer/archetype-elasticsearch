package com.adidas.gherkinDefinitions;

import com.adidas.serenitySteps.ExampleSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ExampleDefinitions {

    @Steps
    private ExampleSteps exampleSteps;

    @When("^I request to create a new index \"([^\"]*)\" with default values$")
    public void iCreateANewIndexWithDefaultValues(String index) throws Throwable {
        exampleSteps.createIndex(index);
    }


    @And("^I update the index \"([^\"]*)\" and type \"([^\"]*)\"$")
    public void iUpdateTheIndexAndType(String index, String type) throws Throwable {
        exampleSteps.updateIndex(index, type);
    }


    @And("^I delete \"([^\"]*)\" \"([^\"]*)\"$")
    public void iDelete(String field, String value) throws Throwable {
        exampleSteps.deleteByQuery(field, value);
    }

    @And("^I insert new tweet \"([^\"]*)\" for user \"([^\"]*)\" in index \"([^\"]*)\"$")
    public void iInsertNewTweetForUserInIndex(String sms, String user, String index) throws Throwable {
        exampleSteps.createIndex(index, user, sms);
    }


    @And("^The value for the \"([^\"]*)\" after post operation should be \"([^\"]*)\"$")
    public void theValueForTheAfterPostOperationShouldBe(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I should get \"([^\"]*)\" result$")
    public void iShouldGetResult(String expectedResult) throws Throwable {
        exampleSteps.verifyResult(expectedResult);
    }

    @Then("^I should get \"([^\"]*)\" (?:hits|hit)$")
    public void iShouldGetHit(String expectedHit) throws Throwable {
        exampleSteps.verifyHit(expectedHit);
    }

    @When("^I request to delete a index by name \"([^\"]*)\"$")
    public void iRequestToDeleteAIndexByName(String index) throws Throwable {
        exampleSteps.deleteIndex(index);
    }

    @Then("^I should get (.*) status code$")
    public void iShouldGetStatusCode(int expectedStatusCode) throws Throwable {
        exampleSteps.verifyStatusCode(expectedStatusCode);
    }

    @When("^I request to delete a \"([^\"]*)\" by value \"([^\"]*)\"$")
    public void iRequestToDeleteAByValue(String field, String value) throws Throwable {
        exampleSteps.deleteByQuery(field, value);
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
    public void iRequestToSearchFieldWithValue(String index, String field, String value) throws Throwable {
        exampleSteps.searchMatchQuery(index, field, value);
    }

    @Then("^I should is acknowledged$")
    public void iShouldIsAcknowledged() throws Throwable {
        exampleSteps.isAcknowledged();
    }

    @When("^I request to delete a \"([^\"]*)\" with nick \"([^\"]*)\"$")
    public void iRequestToDeleteAWithNick(String field, String value) throws Throwable {
        exampleSteps.deleteByQuery(field, value);
    }

    @When("^I request to search with filter \"([^\"]*)\" in index \"([^\"]*)\"$")
    public void iRequestToSearchWithFilterInIndex(String filter, String index) throws Throwable {
        exampleSteps.searchSimpleQueryStringQuery(index, filter);
    }

    @When("^I request to search \"([^\"]*)\" in fields \"([^\"]*)\" in index \"([^\"]*)\"$")
    public void iRequestToSearchInFieldsInIndex(String query, List<String> fields, String index) throws Throwable {
        exampleSteps.searchMultiMatchQuery(query, fields, index);
    }

    @When("^I request to search bool query in index \"([^\"]*)\"$")
    public void iRequestToSearchBoolQueryInIndex(String index) throws Throwable {
        exampleSteps.searchBoolQueryInIndex(index);
    }
}
