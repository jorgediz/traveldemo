package glia.traveldemo.steps

import net.thucydides.core.annotations.Steps
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java.en.And

import glia.traveldemo.steps.serenity.SearchUserSteps
import groovy.transform.CompileStatic

@CompileStatic
class SearchSteps {

    @Steps
    SearchUserSteps i
    
    @Given("i am on the home page")
    def i_am_on_the_home_page() {
        i.am_on_the_home_page()
    }

    @Then("i should be on the results page")
    def i_should_be_on_the_results_page() {
        i.am_on_the_results_page()
    }

    
    @And("i choose '(.*)' as language")
    def i_choose___as_language (String language) {
        i.choose_language language
    }

    @And("i choose '(.*)' as currency")
    def i_choose___as_currency (String currency) {
        i.choose_currency currency
    }

    @When("i submit the search")
    def i_submit_the_search () {
        i.submit_search()
    }

    @And("i choose from '(.*)' to '(.*)'")
    def i_choose_from___to___ (String from, String to) {
        i.from(from)
        i.to(to)
    }

    @And("travel options should be sorted by price")
    def travel_options_should_be_sorted_by_price() {
        i.see_results_that_are_sorted()
    }
    
    @And("i see the travel options by '(.*)'")
    def i_see_the_travel_options_by___(String transport) {
        i.see_the_travel_options_by(transport)
    } 

    @Then("i see errors")
    def i_see_errors() {
        i.see_errors()
    } 
}
