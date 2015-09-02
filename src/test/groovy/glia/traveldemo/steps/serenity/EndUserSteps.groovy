package glia.traveldemo.steps.serenity

import glia.traveldemo.pages.DictionaryPage
import groovy.transform.CompileStatic
import net.thucydides.core.annotations.Step
import net.thucydides.core.steps.ScenarioSteps

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.hasItem

@CompileStatic
class EndUserSteps extends ScenarioSteps {

    DictionaryPage dictionaryPage

    @Step
    def enters(String keyword) {
        dictionaryPage.enter_keywords keyword
    }

    @Step
    def starts_search() {
        dictionaryPage.lookup_terms()
    }

    @Step
    def should_see_definition(String definition) {
        assertThat dictionaryPage.definitions, hasItem(containsString(definition))
    }

    @Step
    def is_the_home_page() {
        dictionaryPage.open()
    }

    @Step
    def looks_for(String term) {
        enters term
        starts_search()
    }
}