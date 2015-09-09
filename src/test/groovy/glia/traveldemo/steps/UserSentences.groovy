package glia.traveldemo.steps

import net.thucydides.core.annotations.Steps
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java.en.And

import glia.traveldemo.steps.UserSteps
import groovy.transform.CompileStatic

@CompileStatic
class UserSentences {

    @Steps
    UserSteps i
   
    @Given("i am on the home page")
    def i_am_on_the_home_page() {
        i.am_on_the_home_page()
    }

    @Then("i am on the results page")
    def i_am_on_the_results_page() {
        i.am_on_the_results_page()
    }

    
    @And("i prefer '(.*)' as language")
    def i_prefer___as_language (String language) {
        i.prefer_language language
    }

    @And("i prefer '(.*)' as currency")
    def i_prefer___as_currency (String currency) {
        i.prefer_currency currency
    }

    @And("i travel by '(.*)'")
    def i_travel_by___ (String transport) {
        i.travel_by transport
    }

    @And("i go one way")
    def i_go_one_way () {
        i.go_one_way()
    }
    
    @And("i go round trip")
    def i_go_round_trip () {
        i.go_round_trip()
    }
    
    @When("i search")
    def i_search () {
        i.search()
    }

    @And("i want to go from '(.*)' to '(.*)'")
    def i_want_to_go_from___to___ (String from, String to) {
        i.go_from from
        i.go_to to
    }
    
    @And("i see travel options sorted by price")
    def i_see_travel_options_sorted_by_price() {
        i.see_travel_options_sorted_by_price()
    }
    
    @And("i see travel options by '(.*)'")
    def i_see_travel_options_by___(String transport) {
        i.see_travel_options_by transport
    } 

    @Then("i see errors")
    def i_see_errors() {
        i.see_errors()
    } 
}
