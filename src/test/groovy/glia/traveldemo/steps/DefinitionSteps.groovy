package glia.traveldemo.steps

import net.thucydides.core.annotations.Steps
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

import glia.traveldemo.steps.serenity.EndUserSteps
import groovy.transform.CompileStatic

@CompileStatic
class DefinitionSteps {

    @Steps
    EndUserSteps anna

    @Given("the user is on the Wikionary home page")
    def the_user_is_on_the_Wikionary_home_page() {
        anna.is_the_home_page()
    }

    @When("the user looks up the definition of the word '(.*)'")
    def the_user_looks_up_the_definition_of_the_word___ (String word) {
        anna.looks_for word
    }

    @Then("they should see the definition '(.*)'")
    def they_should_see_the_definition___(String definition) {
        anna.should_see_definition definition
    }

}
