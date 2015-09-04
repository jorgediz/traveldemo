/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package glia.traveldemo.pages

import groovy.transform.CompileStatic
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement

import java.util.regex.Matcher
import java.util.regex.Pattern

import java.util.Collections

import net.serenitybdd.core.annotations.findby.FindBy
import net.serenitybdd.core.pages.PageObject
import net.serenitybdd.core.pages.WebElementFacade
import net.thucydides.core.annotations.DefaultUrl

@CompileStatic
@DefaultUrl("http://www.goeuro.com/search")
class ResultsPage extends PageObject {
    @FindBy(className="search-results-page")
    WebElementFacade body
    
    @FindBy(id="results")
    WebElementFacade results
    
    @FindBy(id="results-train")
    WebElementFacade results_train
   
    List<BigDecimal> getPrices() {
        List<BigDecimal> prices = new ArrayList<BigDecimal>();
        
        List<WebElement> priceElements = 
        results_train.findElements By.className("price-no")
        
        for (WebElement priceElement : priceElements) {
            prices.add to_big_decimal(priceElement)
        }
         
        prices
    }
   
    BigDecimal to_big_decimal(final WebElement priceElement) {
        String beforeComma = 
        priceElement.findElement By.className("currency-beforecomma") text 
        String decimals = 
        priceElement.findElement By.className("currency-decimals") text
            
        new BigDecimal(
            extract_number(beforeComma) + "." + extract_number(decimals));        
    }
    
    String extract_number(String text) {
        Matcher matcher = Pattern.compile("\\d+").matcher(text);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group())
        } else  {
            return 0
        }
    }
    
    def in_page() {
        waitFor body
        focus("results")
    }
    
    def focus(String id) {
        evaluateJavascript "document.getElementById('"+id+"').focus()"        
        waitABit(2000)
    }

}
