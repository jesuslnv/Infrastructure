package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.EbayPage;
import pages.ebay.ProductInformationView;
import pages.ebay.productInformation.DescriptionTabView;

public class ProjectSteps {
    EbayPage ebayPage;
    ProductInformationView productInformationView;
    DescriptionTabView descriptionTabView;

    @Given("^I Login To Ebay Page with (.*) as User and (.*) as Password$")
    public void loginTo_EbayPage(String user, String pass) {
        ebayPage = new EbayPage(WebDriverManager.getWebDriver());
        ebayPage.loginToEbayPage(user, pass);
    }

    @Then("^Ebay Page is Correctly Displayed$")
    public void ebayPage_CorrectlyDisplayed() {
        ebayPage = new EbayPage(WebDriverManager.getWebDriver());
        boolean response = ebayPage.ebayPageCorrectlyDisplayed();
        Assert.assertTrue("Ebay Page isn't Correctly Displayed", response);
    }

    @When("^I Set (.*) as search value; in Ebay Page$")
    public void set_SearchValue_in_EbayPage(String value) {
        ebayPage = new EbayPage(WebDriverManager.getWebDriver());
        ebayPage.setSearchValueEbayPage(value);
    }

    @When("^I Press Enter Key; in Ebay Page$")
    public void pressEnter_in_EbayPage() {
        ebayPage = new EbayPage(WebDriverManager.getWebDriver());
        ebayPage.pressEnterKey();
    }

    @Then("^Search Result Correctly Displayed; in Ebay Page$")
    public void searchResult_CorrectlyDisplayed_in_EbayPage() {
        ebayPage = new EbayPage(WebDriverManager.getWebDriver());
        boolean response = ebayPage.searchResultCorrectlyDisplayed();
        Assert.assertTrue("Search result isn't Correctly Displayed", response);
    }

    @When("^I Click on First Product in List; in Ebay Page$")
    public void clickOn_FirstProductInList_in_EbayPage() {
        ebayPage = new EbayPage(WebDriverManager.getWebDriver());
        ebayPage.clickOnFirstProductInList();
    }

    @Then("^Product Information Page is Correctly Displayed; in Ebay Page$")
    public void imagePreviewPage_CorrectlyDisplayed_in_ImagesPage_in_GooglePage() {
        productInformationView = new ProductInformationView(WebDriverManager.getWebDriver());
        boolean response = productInformationView.productInformationPageCorrectlyDisplayed();
        Assert.assertTrue("Product Information Page isn't Correctly Displayed", response);
    }

    @When("^I Click on Description Tab; in Product Information Page; in Ebay Page$")
    public void clickOn_DescriptionTab_in_ProductInformationPage_in_EbayPage() {
        productInformationView = new ProductInformationView(WebDriverManager.getWebDriver());
        productInformationView.clickOnDescriptionTab();
    }

    @Then("^Description Tab View is Correctly Displayed; in Product Information Page; in Ebay Page$")
    public void descriptionTabView_CorrectlyDisplayed_in_ProductInformationPage_in_EbayPage() {
        descriptionTabView = new DescriptionTabView(WebDriverManager.getWebDriver());
        boolean response = descriptionTabView.descriptionTabViewCorrectlyDisplayed();
        Assert.assertTrue("Description Tab View isn't Correctly Displayed", response);
    }
}