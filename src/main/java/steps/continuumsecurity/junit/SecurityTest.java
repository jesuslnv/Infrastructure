package steps.continuumsecurity.junit;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import steps.continuumsecurity.scanner.ZapManager;
import steps.continuumsecurity.web.drivers.DriverFactory;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        plugin = {"pretty", "html:build/reports/cucumber/html", "json:build/reports/cucumber/all_tests.json", "junit:build/reports/junit/all_tests.xml"},
        glue = {"steps.continuumsecurity.steps"},
        tags = {"~@skip"}
)
public class SecurityTest {
    @AfterClass
    public static void tearDown() {
        DriverFactory.quitAll();
        ZapManager.getInstance().stopZap();
    }
}