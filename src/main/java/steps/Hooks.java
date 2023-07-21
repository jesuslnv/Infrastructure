package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {WebDriverManager.class})
public class Hooks {

    @Before
    public void before() {
        //Set the "webDriver" with a new one or the previous
        WebDriverManager.getWebDriver();
    }

    @After
    public void after(Scenario scenario) {
        //Closes the Driver when the Test is Finished
        WebDriverManager.closeDriver(scenario);
    }
}
