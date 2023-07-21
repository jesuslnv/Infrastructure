package pages;

import components.Page;
import components.control.ButtonControl;
import components.control.SelectControl;
import components.control.TextControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class EbayPage extends Page {
    private static final Logger LOGGER = LogManager.getLogger();
    private TextControl textControl;
    private ButtonControl buttonControl;
    private SelectControl selectControl;

    public EbayPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public void loginToEbayPage(String user, String pass) {
        webDriver.navigate().to("https://www.ebay.com/");
        // Wait for page Load
        waitForPageLoad(30);
    }

    public boolean ebayPageCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//a/img[contains(@alt,'Logo')]");
        return selectControl.isControlExist();
    }

    public void setSearchValueEbayPage(String value) {
        textControl = new TextControl(webDriver, "//table[@role='presentation']//div/div/input");
        textControl.setText(value);
    }

    public void pressEnterKey() {
        selectControl = new SelectControl(webDriver, "//table[@role='presentation']//div/div/input");
        selectControl.sendkeyToElement(Keys.ENTER);
    }

    public boolean searchResultCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "(//span/a[contains(text(),'shoes')])[1]");
        return selectControl.isControlExist();
    }

    public void clickOnFirstProductInList() {
        buttonControl = new ButtonControl(webDriver, "(//div[@id='srp-river-results']/ul/li//div[contains(@class,'s-item__image')])[1]");
        buttonControl.click();
    }
}