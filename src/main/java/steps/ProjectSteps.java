package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import pages.GooglePage;
import pages.Google.ServiceSupportPage;
import pages.Google.ServiceSupport.CreateTicketPage;

public class ProjectSteps {
    private WebDriverManager webDriverManager;
    private WebDriver webDriver;

    @Before
    public void before(Scenario scenario) {
        webDriverManager = new WebDriverManager();
        webDriver = webDriverManager.getDriver(scenario);
    }

    @After
    public void after(Scenario scenario) {
        webDriverManager.closeDriver(scenario);
    }

    @Given("^I Navigate To Google Page$")
    public void NavigateTo_GooglePage() {
        GooglePage googlePage = new GooglePage(webDriver);
        googlePage.NavigateTo_GooglePage();
    }

    @Then("^Google Page is Correctly Displayed$")
    public void GooglePage_CorrectlyDisplayed() {
        GooglePage googlePage = new GooglePage(webDriver);
        boolean response = googlePage.GooglePage_CorrectlyDisplayed();
        Assert.assertTrue("Google Page isn't Correctly Displayed", response);
    }

    @When("^I Click on Create Ticket Button; in Service Support Page$")
    public void ClickOn_CreateTicketButton_in_ServiceSupportPage() {
        ServiceSupportPage serviceSupportPage = new ServiceSupportPage(webDriver);
        serviceSupportPage.ClickOn_CreateTicketButton();
    }

    @Then("^Create Ticket Page is Correctly Displayed; in Service Support Page$")
    public void CreateTicketPage_CorrectlyDisplayed_in_ServiceSupportPage() {
        CreateTicketPage createTicketPage = new CreateTicketPage(webDriver);
        boolean response = createTicketPage.CreateTicketPage_CorrectlyDisplayed();
        Assert.assertTrue("Create Ticket Page isn't Correctly Displayed", response);
    }
}