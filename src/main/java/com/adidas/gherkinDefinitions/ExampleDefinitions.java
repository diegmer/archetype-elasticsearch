package com.adidas.gherkinDefinitions;

import com.adidas.serenitySteps.ExampleSteps;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class ExampleDefinitions {

    @Steps
    private ExampleSteps exampleSteps;

    @When("I delete all index")
    public void iRequestToCreateNewUser() {
        exampleSteps.deleteAllIndices();
    }

}
