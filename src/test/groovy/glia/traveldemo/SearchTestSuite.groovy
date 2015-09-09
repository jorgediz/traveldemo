package glia.traveldemo;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import java.util.concurrent.TimeUnit

import org.junit.ClassRule
import org.junit.Rule
import org.junit.rules.Timeout
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity)
@CucumberOptions(features="src/test/resources/features/search_cheapest/SearchCheapestTravel.feature")
class SearchTestSuite {
    @ClassRule
    public static Timeout globalTimeout = new Timeout(20, TimeUnit.MINUTES);
}
