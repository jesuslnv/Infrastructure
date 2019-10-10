package steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.Belatrix.MyFilesPage;
import pages.BelatrixPage;
import services.PenetrationTesting;

public class ProjectSteps {
    private WebDriver webDriver;

    @AfterStep
    public void afterStep(Scenario scenario) {
        PenetrationTesting.HTTP_IP = Parameters.OWASP_ZAP_HTTP_IP;
        PenetrationTesting.HTTP_PORT = Parameters.OWASP_ZAP_HTTP_PORT;
        PenetrationTesting.SCANNER_STRENGTH = Parameters.OWASP_ZAP_CONFIG_STRENGTH;
        PenetrationTesting.SCANNER_THRESHOLD = Parameters.OWASP_ZAP_CONFIG_THRESHOLD;
        PenetrationTesting.RISK_LEVEL = "MEDIUM";
        PenetrationTesting.runScanner(webDriver.getCurrentUrl());
    }

    @Before
    public void before(Scenario scenario) {
        webDriver = WebDriverManager.getWebDriver(scenario);
    }

    @After
    public void after(Scenario scenario) {
        WebDriverManager.closeDriver(scenario);
    }

    @Given("^I Login To Belatrix Page with (.*) as User and (.*) as Password$")
    public void LoginTo_BelatrixPage(String user, String pass) {
        BelatrixPage belatrixPage = new BelatrixPage(webDriver);
        belatrixPage.LoginTo_BelatrixPage(user, pass);
    }

    @Then("^Belatrix Page is Correctly Displayed$")
    public void BelatrixPage_CorrectlyDisplayed() {
        BelatrixPage belatrixPage = new BelatrixPage(webDriver);
        boolean response = belatrixPage.BelatrixPage_CorrectlyDisplayed();
        Assert.assertTrue("Belatrix Page isn't Correctly Displayed", response);
    }

    @When("^I Click on My Files Button; in Belatrix Main Page$")
    public void ClickOn_MyFilesButton_in_BelatrixMainPage() {
        BelatrixPage belatrixPage = new BelatrixPage(webDriver);
        belatrixPage.ClickOn_MyFilesButton();
    }

    @Then("^My Files Page is Correctly Displayed; in Belatrix Page$")
    public void MyFilesPage_CorrectlyDisplayed_in_BelatrixPage() {
        MyFilesPage myFilesPage = new MyFilesPage(webDriver);
        boolean response = myFilesPage.MyFilesPage_CorrectlyDisplayed();
        Assert.assertTrue("My Files Page isn't Correctly Displayed", response);
    }
}