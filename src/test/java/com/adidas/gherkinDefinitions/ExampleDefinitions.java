package com.adidas.gherkinDefinitions;

import com.adidas.serenitySteps.ExampleSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.net.UnknownHostException;

public class ExampleDefinitions {

    @Steps
    private ExampleSteps exampleSteps;

    @When("I delete all index$")
    public void iDelleteAllIndex() throws UnknownHostException {
        exampleSteps.deleteAllIndices();
    }

    @When("^I create a new index \"([^\"]*)\" with default values$")
    public void iCreateANewIndexWithDefaultValues(String index) throws Throwable {
        exampleSteps.createIndex(index);
    }

    @When("^I delete index \"([^\"]*)\"$")
    public void iDeleteIndex(String index) throws Throwable {
        exampleSteps.deleteIndex(index);
    }

    @And("^I update the index \"([^\"]*)\" and type \"([^\"]*)\"$")
    public void iUpdateTheIndexAndType(String index, String type) throws Throwable {
        exampleSteps.updateIndex(index, type);
    }

    @And("^I search \"([^\"]*)\" \"([^\"]*)\"$")
    public void iSearch(String field, String value) throws Throwable {
        exampleSteps.search(field, value);
    }

    @And("^I delete \"([^\"]*)\" \"([^\"]*)\"$")
    public void iDelete(String field, String value) throws Throwable {
        exampleSteps.deleteByQuery(field, value);
    }
}
