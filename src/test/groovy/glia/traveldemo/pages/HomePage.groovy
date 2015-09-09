package glia.traveldemo.pages

import ch.lambdaj.function.convert.Converter
import groovy.transform.CompileStatic
import net.thucydides.core.annotations.DefaultUrl
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import net.serenitybdd.core.pages.WebElementFacade

import net.serenitybdd.core.annotations.findby.FindBy

import net.thucydides.core.pages.PageObject

import java.util.List
import java.util.concurrent.TimeUnit


import static ch.lambdaj.Lambda.convert

@CompileStatic
@DefaultUrl("http://www.goeuro.com")
class HomePage extends PageObject {
    @FindBy(id="header-langswitch")
    WebElementFacade language_selection
    
    @FindBy(id="header-currencyswitch")
    WebElementFacade currency_selection

    @FindBy(id="search-form__submit-btn")
    WebElementFacade search_button

    @FindBy(id="from_filter")
    WebElementFacade from_filter_input

    @FindBy(id="to_filter")
    WebElementFacade to_filter_input
    
    @FindBy(className="analytics-oneway-trip-btn")
    WebElementFacade one_way    
    
    @FindBy(className="analytics-round-trip-btn")
    WebElementFacade round_trip    

    @FindBy(className="hotel-checkboxes")
    WebElementFacade hotel_checkboxes

    @FindBy(className="search-page")
    WebElementFacade body

    String handle
    
    def on_the_page() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS)
        
        open()
        shouldBeDisplayed()
        this.handle = driver.windowHandle
        waitFor body
    }
    
    def uncheck_hotel_checkboxes() {
        hotel_checkboxes.with {
            findAll By.tagName("input")
        }   
    }
    
    def go_one_way() {
        one_way.with {
            new Actions(driver).moveToElement(it).click().perform()
        }
    }

    def go_round_trip() {
        round_trip.with {
            new Actions(driver).moveToElement(it).click().perform()
        }
    }
    
    def prefer_language(String language) {
        language_selection.with {
            new Actions(driver).moveToElement(it).perform()
            
            find By.className("dropdown-sel-val") click()
            String selection_id = "lang-switch--"+language
            
            WebElementFacade selection = find By.id(selection_id) 
            new Actions(driver).moveToElement(selection).clickAndHold().release().perform()
        }
    }

    def prefer_currency(String currency) {
        currency_selection.with {
            new Actions(driver).moveToElement(it).perform()

            find By.className("dropdown-sel-val") click()
            String selection_id = "currency-switch--"+currency

            WebElementFacade selection = find By.id(selection_id)
            new Actions(driver).moveToElement(selection).clickAndHold().release().perform()
        }        
    }

    def search() {
        search_button.with {
            waitUntilClickable()
            focus it
            new Actions(driver).moveToElement(it).clickAndHold().release().perform()
        }
    }

    def focus(WebElementFacade element) {
        String id = element.getAttribute "id"
        evaluateJavascript "document.getElementById('"+id+"').focus()"
        waitABit 1000
    }
    
    def from(String city) {
        from_filter_input.with {
            focus it
            sendKeys city
        }
    }

    def to(String city) {
        to_filter_input.with {
            focus it
            sendKeys city
        }
    }   
    
    List<String> getDefinitions() {
        WebElementFacade definitionList = find By.tagName("ol")
        List<WebElement> results = definitionList.findElements By.tagName("li")
        convert results, toStrings()
    }

    Converter<WebElement, String> toStrings() {
        new Converter<WebElement, String>() {
            public String convert(WebElement from) {
                from.text
            }
        }
    }

    def see_errors() {
        assert findAll(By.cssSelector(".errors,.suggest"))?.size != 1
    }
}