package glia.traveldemo.steps

import glia.traveldemo.pages.HomePage
import glia.traveldemo.pages.ResultsPage
import groovy.transform.CompileStatic
import net.thucydides.core.annotations.Step
import net.thucydides.core.steps.ScenarioSteps

import java.util.Collections
import org.hamcrest.Matcher
import org.hamcrest.collection.IsIterableContainingInOrder

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.contains

@CompileStatic
class UserSteps extends ScenarioSteps {

    HomePage homePage
    ResultsPage resultsPage

    //    @Step
    //    def should_see_definition(String definition) {
    //        assertThat page.definitions, hasItem(containsString(definition))
    //    }

    @Step
    def am_on_the_home_page() {
        homePage.on_the_page()
    }

    @Step
    def am_on_the_results_page() {
        resultsPage.on_the_page()
    }

    @Step
    def prefer_language(String language) {
        homePage.prefer_language language
    }

    @Step
    def prefer_currency(String currency) {
        homePage.prefer_currency currency
    }

    @Step
    def search() {
        homePage.search()
        resultsPage.shouldBeDisplayed()
    }

    @Step
    def go_from(String city) {
        homePage.from(city)
    }

    @Step
    def go_to(String city) {
        homePage.to(city)
    }
    
    @Step
    def see_travel_options_sorted_by_price() {
        assert_list_is_sorted resultsPage.prices
    }

    @Step
    def see_travel_options_by(String transport) {
        List prices = resultsPage.prices
        assert_list_is_sorted prices
    }

    @Step
    def see_errors() {
        homePage.see_errors()
    }

    def assert_list_is_sorted(List<BigDecimal> original) {
        List<BigDecimal> sorted = new ArrayList<BigDecimal>(original)
        Collections.sort(sorted)
        BigDecimal[] sortedArray = (BigDecimal[]) sorted.toArray()
        
        assertThat (
            original,
            IsIterableContainingInOrder.contains(sortedArray)
        )    
    }
}