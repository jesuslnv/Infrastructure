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
import pages.Google.Images.ImagePreviewPage;
import pages.Google.ImagesPage;
import pages.GooglePage;

public class ProjectSteps {
    private WebDriver webDriver;

    @AfterStep
    public void afterStep(Scenario scenario) {
        //Run the Penetration Testing to detect anomalies
        WebDriverManager.runPenetrationTesting(scenario);
    }

    @Before
    public void before(Scenario scenario) {
        //Set the "webDriver" with a new one or the previous
        webDriver = WebDriverManager.getWebDriver(scenario);
    }

    @After
    public void after(Scenario scenario) {
        //Closes the Driver when the Test is Finished
        WebDriverManager.closeDriver(scenario);
    }

    @Given("^I Login To Google Page with (.*) as User and (.*) as Password$")
    public void loginTo_GooglePage(String user, String pass) {
        GooglePage googlePage = new GooglePage(webDriver);
        googlePage.loginTo_GooglePage(user, pass);
    }

    @Then("^Google Page is Correctly Displayed$")
    public void googlePage_CorrectlyDisplayed() {
        GooglePage googlePage = new GooglePage(webDriver);
        boolean response = googlePage.googlePage_CorrectlyDisplayed();
        Assert.assertTrue("Google Page isn't Correctly Displayed", response);
    }

    @When("^I Set (.*) as search value; in Google Page$")
    public void set_SearchValue_in_GooglePage(String value) {
        GooglePage googlePage = new GooglePage(webDriver);
        googlePage.set_SearchValue_GooglePage(value);
    }

    @When("^I Press Enter Key; in Google Page$")
    public void pressEnter_in_GooglePage() {
        GooglePage googlePage = new GooglePage(webDriver);
        googlePage.press_EnterKey();
    }

    @Then("^Search Result Correctly Displayed; in Google Page$")
    public void searchResult_CorrectlyDisplayed_in_GooglePage() {
        GooglePage googlePage = new GooglePage(webDriver);
        boolean response = googlePage.searchResult_CorrectlyDisplayed();
        Assert.assertTrue("Search result isn't Correctly Displayed", response);
    }

    @When("^I Click on Images Button; in Google Page$")
    public void clickOn_ImagesButton_in_GooglePage() {
        GooglePage googlePage = new GooglePage(webDriver);
        googlePage.clickOn_ImagesButton();
    }

    @Then("^Images Page is Correctly Displayed; in Google Page$")
    public void imagesPage_CorrectlyDisplayed_in_GooglePage() {
        ImagesPage imagesPage = new ImagesPage(webDriver);
        boolean response = imagesPage.imagesPage_CorrectlyDisplayed();
        Assert.assertTrue("Images Page isn't Correctly Displayed", response);
    }

    @When("^I Click on First Image Found; in Images Page; in Google Page$")
    public void clickOn_FirstImageFound_in_ImagesPage_in_GooglePage() {
        ImagesPage imagesPage = new ImagesPage(webDriver);
        imagesPage.clickOn_FirstImageFound();
    }

    @Then("^Image Preview Page is Correctly Displayed; in Google Page$")
    public void imagePreviewPage_CorrectlyDisplayed_in_ImagesPage_in_GooglePage() {
        ImagePreviewPage imagePreviewPage = new ImagePreviewPage(webDriver);
        boolean response = imagePreviewPage.imagesPreviewPage_CorrectlyDisplayed();
        Assert.assertTrue("Image preview Page isn't Correctly Displayed", response);
    }
}