package steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;

public class Hooks {

    @AfterStep
    public void afterStep() {
        //Run the Penetration Testing to detect anomalies
        WebDriverManager.runPenetrationTesting();
    }

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
