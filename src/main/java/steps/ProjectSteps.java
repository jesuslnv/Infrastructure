package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ManagedSolutions.ServiceSupport.CreateTicketPage;
import pages.ManagedSolutions.ServiceSupportPage;
import pages.ManagedSolutionsPage;

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

    @Given("^I Login to IBM Cloud with (.*) as user and (.*) as password$")
    public void LoginTo_IBMCloud(String user, String pass) {
        ManagedSolutionsPage managedSolutionsPage = new ManagedSolutionsPage(webDriver);
        managedSolutionsPage.LoginToWeb_IBMCloud(user, pass);
    }

    @Then("^IBM Cloud Page is Correctly Displayed$")
    public void IBMCloudPage_CorrectlyDisplayed() {
        ManagedSolutionsPage managedSolutionsPage = new ManagedSolutionsPage(webDriver);
        boolean response = managedSolutionsPage.IBMCloud_CorrectlyDisplayed();
        Assert.assertTrue("IBM Cloud Page isn't Correctly Displayed", response);
    }

    @When("^I Navigate to the URL (.*)$")
    public void NavigateToTheURL(String url) {
        ManagedSolutionsPage managedSolutionsPage = new ManagedSolutionsPage(webDriver);
        managedSolutionsPage.NavigateToTheURL(url);
    }

    @Then("^Service Support Page is Correctly Displayed$")
    public void ServiceSupportPage_CorrectlyDisplayed() {
        ServiceSupportPage serviceSupportPage = new ServiceSupportPage(webDriver);
        boolean response = serviceSupportPage.ServiceSupportPage_CorrectlyDisplayed();
        Assert.assertTrue("Service Support Page isn't Correctly Displayed", response);
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

    @When("^I Click on (.*) as Ticket Type; in Create Ticket Page; in Service Support Page$")
    public void ClickOn_TicketType_in_CreateTicketPage_in_ServiceSupportPage(String value) {
        CreateTicketPage createTicketPage = new CreateTicketPage(webDriver);
        createTicketPage.ClickOn_TicketType(value);
    }

    @When("^I Select (.*) as Location; in Create Ticket Page; in Service Support Page$")
    public void Select_Location_in_CreateTicketPage_in_ServiceSupportPage(String value) {
        CreateTicketPage createTicketPage = new CreateTicketPage(webDriver);
        createTicketPage.Select_Location(value);
    }

    @When("^I Select (.*) as Customer Expedite; in Create Ticket Page; in Service Support Page$")
    public void Select_CustomerExpedite_in_CreateTicketPage_in_ServiceSupportPage(String value) {
        CreateTicketPage createTicketPage = new CreateTicketPage(webDriver);
        createTicketPage.Select_CustomerExpedite(value);
    }

    @When("^I Set (.*) as Short Description; in Create Ticket Page; in Service Support Page$")
    public void Set_ShortDescription_in_CreateTicketPage_in_ServiceSupportPage(String value) {
        CreateTicketPage createTicketPage = new CreateTicketPage(webDriver);
        createTicketPage.Set_ShortDescription(value);
    }

    @When("^I Set (.*) as Description; in Create Ticket Page; in Service Support Page$")
    public void Set_Description_in_CreateTicketPage_in_ServiceSupportPage(String value) {
        CreateTicketPage createTicketPage = new CreateTicketPage(webDriver);
        createTicketPage.Set_Description(value);
    }

    @When("^I Click on Submit Button; in Create Ticket Page; in Service Support Page$")
    public void ClickOn_SubmitButton_in_CreateTicketPage_in_ServiceSupportPage() {
        CreateTicketPage createTicketPage = new CreateTicketPage(webDriver);
        createTicketPage.ClickOn_SubmitButton();
    }

    @When("^I Set (.*) as Filter By; in Service Support Page$")
    public void Set_FilterBy_in_ServiceSupportPage(String value) {
        ServiceSupportPage serviceSupportPage = new ServiceSupportPage(webDriver);
        serviceSupportPage.Set_FilterBy(value);
    }

    @Then("^Resulted Ticket List is Correctly Displayed; in Service Support Page$")
    public void ResultedTickedList_CorrectlyDisplayed_in_ServiceSupportPage() {
        ServiceSupportPage serviceSupportPage = new ServiceSupportPage(webDriver);
        boolean response = serviceSupportPage.ResultedTicketList_CorrectlyDisplayed();
        Assert.assertTrue("Resulted Ticket List isn't Correctly Displayed", response);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}