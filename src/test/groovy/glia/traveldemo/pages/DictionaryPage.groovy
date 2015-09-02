package glia.traveldemo.pages

import ch.lambdaj.function.convert.Converter
import groovy.transform.CompileStatic
import net.thucydides.core.annotations.DefaultUrl
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import net.serenitybdd.core.pages.WebElementFacade

import net.serenitybdd.core.annotations.findby.FindBy

import net.thucydides.core.pages.PageObject

import java.util.List

import static ch.lambdaj.Lambda.convert

@CompileStatic
@DefaultUrl("http://en.wiktionary.org/wiki/Wiktionary")
class DictionaryPage extends PageObject {

    @FindBy(name="search")
    WebElementFacade searchTerms

    @FindBy(name="go")
    WebElementFacade lookupButton

    def enter_keywords(String keyword) {
        searchTerms.type keyword
    }

    def lookup_terms() {
        lookupButton.click()
    }

    def List<String> getDefinitions() {
        WebElementFacade definitionList = find By.tagName("ol")
        List<WebElement> results = definitionList.findElements By.tagName("li")
        convert results, toStrings()
    }

    def Converter<WebElement, String> toStrings() {
        new Converter<WebElement, String>() {
            public String convert(WebElement from) {
                from.text
            }
        }
    }
}