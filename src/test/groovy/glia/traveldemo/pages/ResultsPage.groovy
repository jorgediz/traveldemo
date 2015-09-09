/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package glia.traveldemo.pages

import groovy.transform.CompileStatic
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import java.util.regex.Matcher
import java.util.regex.Pattern

import java.util.Collections

import net.serenitybdd.core.annotations.findby.FindBy
import net.serenitybdd.core.pages.PageObject
import net.serenitybdd.core.pages.WebElementFacade
import net.thucydides.core.annotations.DefaultUrl
import java.util.concurrent.TimeUnit

@CompileStatic
@DefaultUrl("http://www.goeuro.com/search")
class ResultsPage extends PageObject {
    
    @FindBy(className="search-results-page")
    WebElementFacade body
    
    @FindBy(id="results")
    WebElementFacade results
        
    String transport
    String handle
    
    List<BigDecimal> getPrices() {
        List<BigDecimal> prices = new ArrayList<BigDecimal>();

        List<WebElement> priceElements =
               results_active().findElements By.className("price-no")
        
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
            
        "${to_number(beforeComma)}.${to_number(decimals)}" as BigDecimal
    }
    
    String to_number(String text) {
        Pattern.compile("\\d+").matcher(text).with {
            find()
                ? Integer.valueOf(it.group())
                : 0
        }
    }

    WebElementFacade results_by(String transport) {
        find By.id("results-"+transport)
    }

    WebElementFacade tab_by(String transport) {
        find By.id("tab_"+transport)
    }
    
    // bus. train, flight
    def travel_by(String transport) {
        this.transport = transport
        tab_by(transport).click()
        WebElementFacade results_by_transport = results_by(transport)
        
        results_by_transport.with {
            new Actions(driver).moveToElement(it).click().perform()
        }
    }
    
    WebElement results_active() {
        WebElement results_active = results.findElements(By.className("active"))[0]
        assert transport == null || results_active == results_by(transport)
        results_active
    }
    
    def on_the_page() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS)
        
        driver.switchTo().defaultContent()
        handle = driver.windowHandle
        waitFor results
        new Actions(driver).moveToElement(results).perform()
        close_other_windows()
    }
    
    def close_other_windows() {
        driver.with {
            String thisWindow = windowHandle
        
            for (String handle : windowHandles) {
                if (handle != thisWindow) {
                    switchTo().window(handle)
                    close()
                }
            }
            
            switchTo().window(thisWindow)
        }
    }
}
