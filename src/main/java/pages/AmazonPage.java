package pages;

import components.Page;
import components.control.ButtonControl;
import components.control.SelectControl;
import components.control.TextControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AmazonPage extends Page {
    private static final Logger LOGGER = LogManager.getLogger();
    private TextControl textControl;
    private ButtonControl buttonControl;
    private SelectControl selectControl;

    public AmazonPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public void loginToAmazonPage(String user, String pass) {
        webDriver.navigate().to("https://www.Amazon.com/");
        // Wait for page Load
        waitForPageLoad(30);
    }

    public boolean amazonPageCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//header//a[contains(@aria-label,'Amazon')]");
        return selectControl.isControlExist();
    }

    public void setSearchValueAmazonPage(String value) {
        textControl = new TextControl(webDriver, "//input[@id='twotabsearchtextbox']");
        textControl.setText(value);
    }

    public void pressEnterKey() {
        selectControl = new SelectControl(webDriver, "//input[@id='twotabsearchtextbox']");
        selectControl.sendkeyToElement(Keys.ENTER);
    }

    public boolean searchResultCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//span[@data-component-type='s-result-info-bar']//span[contains(text(),'Shoes')]");
        return selectControl.isControlExist();
    }

    public void clickOnFirstProductInList() {
        buttonControl = new ButtonControl(webDriver, "(//div[@data-component-type='s-search-result']//a)[1]");
        buttonControl.click();
    }
}