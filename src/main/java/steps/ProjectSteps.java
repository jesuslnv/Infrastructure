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
import pages.ebay.productInformation.DescriptionTabView;
import pages.ebay.ProductInformationView;
import pages.EbayPage;

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

    @Given("^I Login To Ebay Page with (.*) as User and (.*) as Password$")
    public void loginTo_EbayPage(String user, String pass) {
        EbayPage ebayPage = new EbayPage(webDriver);
        ebayPage.loginToEbayPage(user, pass);
    }

    @Then("^Ebay Page is Correctly Displayed$")
    public void ebayPage_CorrectlyDisplayed() {
        EbayPage ebayPage = new EbayPage(webDriver);
        boolean response = ebayPage.ebayPageCorrectlyDisplayed();
        Assert.assertTrue("Ebay Page isn't Correctly Displayed", response);
    }

    @When("^I Set (.*) as search value; in Ebay Page$")
    public void set_SearchValue_in_EbayPage(String value) {
        EbayPage ebayPage = new EbayPage(webDriver);
        ebayPage.setSearchValueEbayPage(value);
    }

    @When("^I Press Enter Key; in Ebay Page$")
    public void pressEnter_in_EbayPage() {
        EbayPage ebayPage = new EbayPage(webDriver);
        ebayPage.pressEnterKey();
    }

    @Then("^Search Result Correctly Displayed; in Ebay Page$")
    public void searchResult_CorrectlyDisplayed_in_EbayPage() {
        EbayPage ebayPage = new EbayPage(webDriver);
        boolean response = ebayPage.searchResultCorrectlyDisplayed();
        Assert.assertTrue("Search result isn't Correctly Displayed", response);
    }

    @When("^I Click on First Product in List; in Ebay Page$")
    public void clickOn_FirstProductInList_in_EbayPage() {
        EbayPage ebayPage = new EbayPage(webDriver);
        ebayPage.clickOnFirstProductInList();
    }

    @Then("^Product Information Page is Correctly Displayed; in Ebay Page$")
    public void imagePreviewPage_CorrectlyDisplayed_in_ImagesPage_in_GooglePage() {
        ProductInformationView productInformationView = new ProductInformationView(webDriver);
        boolean response = productInformationView.productInformationPageCorrectlyDisplayed();
        Assert.assertTrue("Product Information Page isn't Correctly Displayed", response);
    }

    @When("^I Click on Description Tab; in Product Information Page; in Ebay Page$")
    public void clickOn_DescriptionTab_in_ProductInformationPage_in_EbayPage() {
        ProductInformationView productInformationView = new ProductInformationView(webDriver);
        productInformationView.clickOnDescriptionTab();
    }

    @Then("^Description Tab View is Correctly Displayed; in Product Information Page; in Ebay Page$")
    public void descriptionTabView_CorrectlyDisplayed_in_ProductInformationPage_in_EbayPage() {
        DescriptionTabView descriptionTabView = new DescriptionTabView(webDriver);
        boolean response = descriptionTabView.descriptionTabViewCorrectlyDisplayed();
        Assert.assertTrue("Description Tab View isn't Correctly Displayed", response);
    }
}