package glia.traveldemo;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity)
@CucumberOptions(features="src/test/resources/features/consult_dictionary/LookupADefinition.feature")
class DefinitionTestSuite {}
