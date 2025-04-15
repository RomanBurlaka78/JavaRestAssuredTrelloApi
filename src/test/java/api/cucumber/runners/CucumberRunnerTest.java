package api.cucumber.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/smoke.feature",
        glue = {"api.cucumber.steps"},
        plugin = {"pretty", "html:target/cucumber-reports" }
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {


}
