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

import static ch.lambdaj.Lambda.convert

@CompileStatic
@DefaultUrl("http://www.goeuro.com")
class HomePage extends PageObject {
    Actions actions
    
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
    
    @FindBy(className="hotel-checkboxes")
    WebElementFacade hotel_checkboxes

    @FindBy(className="search-page")
    WebElementFacade body

    def on_the_page() {
        open()
        shouldBeDisplayed()
        waitFor body
        actions = new Actions(driver)
        

//        String highlight_element_in_focus = """
//function createClass(name,rules){
//    var style = document.createElement('style');
//    style.type = 'text/css';
//    document.getElementsByTagName('head')[0].appendChild(style);
//    if(!(style.sheet||{}).insertRule) 
//        (style.styleSheet || style.sheet).addRule(name, rules);
//    else
//        style.sheet.insertRule(name+"{"+rules+"}",0);
//}
//createClass('*:focus','border: 10px red !important;');        
//"""
//        evaluateJavascript highlight_element_in_focus
    }
    
    def uncheck_hotel_checkboxes() {
        hotel_checkboxes.with {
            findAll By.tagName("input")
        }   
    }
    
    
    def prefer_language(String language) {
        actions.moveToElement(language_selection)

        language_selection.with {
            find By.className("dropdown-sel-val") click()
            String selection_id = "lang-switch--"+language
            
            WebElementFacade selection = find By.id(selection_id) 
            focus selection_id
            selection.waitUntilClickable()
            selection.click()
            selection.click()
        }
    }

    def prefer_currency(String currency) {
        actions.moveToElement(currency_selection)

        currency_selection.with {
            find By.className("dropdown-sel-val") click()
            String selection_id = "currency-switch--"+currency

            WebElementFacade selection = find By.id(selection_id)
            focus selection_id
            selection.waitUntilClickable()
            selection.click()
            selection.click()
        }        
    }

    def search() {
        search_button.with {
            actions.moveToElement it
            waitUntilEnabled()
            String id = getAttribute("id")
            focus id
            waitUntilClickable()
            click()
        }
    }

    def focus(String id) {
        evaluateJavascript "document.getElementById('"+id+"').focus()"        
        waitABit(1000)
    }
    
    def from(String city) {
        from_filter_input.with {
            focus "from_filter"
            sendKeys city
        }
    }

    def to(String city) {
        to_filter_input.with {
            focus "to_filter"
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