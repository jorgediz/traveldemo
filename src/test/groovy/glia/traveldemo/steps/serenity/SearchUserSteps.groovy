package glia.traveldemo.steps.serenity

import glia.traveldemo.pages.HomePage
import glia.traveldemo.pages.ResultsPage
import groovy.transform.CompileStatic
import net.thucydides.core.annotations.Step
import net.thucydides.core.steps.ScenarioSteps

import org.hamcrest.Matcher
import org.hamcrest.collection.IsIterableContainingInOrder

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.contains

@CompileStatic
class SearchUserSteps extends ScenarioSteps {

    HomePage homePage
    ResultsPage resultsPage

    //    @Step
    //    def should_see_definition(String definition) {
    //        assertThat page.definitions, hasItem(containsString(definition))
    //    }

    @Step
    def am_on_the_home_page() {
        homePage.in_page()
    }

    @Step
    def choose_language(String language) {
        homePage.choose_language(language)
    }

    @Step
    def choose_currency(String currency) {
        homePage.choose_currency(currency)
    }

    @Step
    def submit_search() {
        homePage.submit_search()
        resultsPage.shouldBeDisplayed()
    }

    @Step
    def from(String city) {
        homePage.from(city)
    }

    @Step
    def to(String city) {
        homePage.to(city)
    }

    @Step
    def am_on_the_results_page() {
        resultsPage.in_page()
        resultsPage.shouldBeDisplayed()
    }
    
    @Step
    def see_results_that_are_sorted() {
        List<BigDecimal> prices = resultsPage.prices
        List<BigDecimal> sortedPrices = new ArrayList<BigDecimal>(prices)
        Collections.sort(sortedPrices)
        BigDecimal[] sortedArray = (BigDecimal[]) sortedPrices.toArray()
        
        assertThat (
            prices,
            IsIterableContainingInOrder.contains(sortedArray)
        )        
    }


    @Step
    def see_the_travel_options_by(String transport) {
        List prices = resultsPage.prices
//        assertThat (
//            Collections.sort(prices), 
//            contains(prices)
//        )
    }

}