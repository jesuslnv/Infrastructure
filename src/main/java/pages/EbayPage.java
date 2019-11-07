package pages;

import components.control.ButtonControl;
import components.control.TextControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import components.Page;
import components.control.SelectControl;
import org.openqa.selenium.interactions.Actions;

public class EbayPage extends Page {
    private static final Logger LOGGER = LogManager.getLogger();
    private TextControl textControl;
    private ButtonControl buttonControl;
    private SelectControl selectControl;

    public EbayPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public void loginTo_EbayPage(String user, String pass) {
        webDriver.navigate().to("https://www.ebay.com/");
        LOGGER.info("Here can be used the User '{}' and Password '{}' to set in a specific field to Login", user, pass);
        // Wait for page Load
        waitForPageLoad(60);
    }

    public boolean ebayPage_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//a/img[@role='presentation']");
        return selectControl.isControlExist();
    }

    public void set_SearchValue_EbayPage(String value) {
        textControl = new TextControl(webDriver, "//table[@role='presentation']//div/div/input");
        textControl.setText(value);
    }

    public void press_EnterKey() {
        selectControl = new SelectControl(webDriver, "//table[@role='presentation']//div/div/input");
        selectControl.sendkeyToElement(Keys.ENTER);
    }

    public boolean searchResult_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "(//span/a[contains(text(),'shoes')])[1]");
        return selectControl.isControlExist();
    }

    public void clickOn_FirstProductInList() {
        buttonControl = new ButtonControl(webDriver, "(//div/div[1]/div/a[1]/div/img)[1]");
        buttonControl.click();
    }
}