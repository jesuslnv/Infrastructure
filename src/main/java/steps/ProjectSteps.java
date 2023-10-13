package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AmazonPage;
import pages.amazon.ProductInformationView;
import pages.amazon.productInformation.DescriptionTabView;

public class ProjectSteps {
    AmazonPage amazonPage;
    ProductInformationView productInformationView;
    DescriptionTabView descriptionTabView;

    @Given("^I Login To Amazon Page with (.*) as User and (.*) as Password$")
    public void loginTo_AmazonPage(String user, String pass) {
        amazonPage = new AmazonPage(WebDriverManager.getWebDriver());
        amazonPage.loginToAmazonPage(user, pass);
    }

    @Then("^Amazon Page is Correctly Displayed$")
    public void amazonPage_CorrectlyDisplayed() {
        amazonPage = new AmazonPage(WebDriverManager.getWebDriver());
        boolean response = amazonPage.amazonPageCorrectlyDisplayed();
        Assert.assertTrue("Amazon Page isn't Correctly Displayed", response);
    }

    @When("^I Set (.*) as search value; in Amazon Page$")
    public void set_SearchValue_in_AmazonPage(String value) {
        amazonPage = new AmazonPage(WebDriverManager.getWebDriver());
        amazonPage.setSearchValueAmazonPage(value);
    }

    @When("^I Press Enter Key; in Amazon Page$")
    public void pressEnter_in_AmazonPage() {
        amazonPage = new AmazonPage(WebDriverManager.getWebDriver());
        amazonPage.pressEnterKey();
    }

    @Then("^Search Result Correctly Displayed; in Amazon Page$")
    public void searchResult_CorrectlyDisplayed_in_AmazonPage() {
        amazonPage = new AmazonPage(WebDriverManager.getWebDriver());
        boolean response = amazonPage.searchResultCorrectlyDisplayed();
        Assert.assertTrue("Search result isn't Correctly Displayed", response);
    }

    @When("^I Click on First Product in List; in Amazon Page$")
    public void clickOn_FirstProductInList_in_AmazonPage() {
        amazonPage = new AmazonPage(WebDriverManager.getWebDriver());
        amazonPage.clickOnFirstProductInList();
    }

    @Then("^Product Information Page is Correctly Displayed; in Amazon Page$")
    public void productInformationPage_CorrectlyDisplayed_in_AmazonPage() {
        productInformationView = new ProductInformationView(WebDriverManager.getWebDriver());
        boolean response = productInformationView.productInformationPageCorrectlyDisplayed();
        Assert.assertTrue("Product Information Page isn't Correctly Displayed", response);
    }

    @When("^I Click on Description Tab; in Product Information Page; in Amazon Page$")
    public void clickOn_DescriptionTab_in_ProductInformationPage_in_AmazonPage() {
        productInformationView = new ProductInformationView(WebDriverManager.getWebDriver());
        productInformationView.clickOnDescriptionTab();
    }

    @Then("^Description Tab View is Correctly Displayed; in Product Information Page; in Amazon Page$")
    public void descriptionTabView_CorrectlyDisplayed_in_ProductInformationPage_in_AmazonPage() {
        descriptionTabView = new DescriptionTabView(WebDriverManager.getWebDriver());
        boolean response = descriptionTabView.descriptionTabViewCorrectlyDisplayed();
        Assert.assertTrue("Description Tab View isn't Correctly Displayed", response);
    }
}