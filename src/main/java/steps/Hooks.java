package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

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
