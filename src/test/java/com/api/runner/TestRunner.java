package com.api.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
/**
 * This class is a TestRunner for Cucumber tests using TestNG.
 * It is responsible for running the Cucumber tests defined in the specified feature files.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.api.steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/CucumberTestReport.json"
        },
        tags = "@SurepayTesting" //Feature level execution
        //tags="@FunctionalTesting or @UsersPostsTesting". //Scenario level execution
        //tags="@FunctionalTesting and @IntegrationTesting" //Scenario level execution
)

public class TestRunner extends AbstractTestNGCucumberTests  {
}
