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

public class GooglePage extends Page {
    private static final Logger LOGGER = LogManager.getLogger();
    private TextControl textControl;
    private ButtonControl buttonControl;
    private SelectControl selectControl;

    public GooglePage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public void loginTo_GooglePage(String user, String pass) {
        webDriver.navigate().to("https://www.google.com/");
        LOGGER.info("Here can be used the User '{}' and Password '{}' to set in a specific field to Login", user, pass);
        // Wait for page Load
        waitForPageLoad(60);
    }

    public boolean googlePage_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//img[@id='hplogo']");
        return selectControl.isControlExist();
    }

    public void set_SearchValue_GooglePage(String value) {
        textControl = new TextControl(webDriver, "//form[@action='/search']/div[2]/div[1]/div[1]/div/div[2]/input");
        textControl.setText(value);
    }

    public void press_EnterKey() {
        webDriver.findElement(By.xpath("//form[@action='/search']/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys(Keys.ENTER);
    }

    public boolean searchResult_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[@id='resultStats']");
        return selectControl.isControlExist();
    }

    public void clickOn_ImagesButton() {
        buttonControl = new ButtonControl(webDriver, "//div[@role='tab'][2]/a");
        buttonControl.click();
    }
}