package pages;

import org.openqa.selenium.WebDriver;
import components.Page;
import components.control.SelectControl;

public class GooglePage extends Page {
    public GooglePage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public void NavigateTo_GooglePage() {
        webDriver.navigate().to("https://www.google.com/");
        // Wait for page Load	
        waitForPageLoad(60);
    }

    public boolean GooglePage_CorrectlyDisplayed() {
        SelectControl selectControl = new SelectControl(webDriver, "//img[contains(@src,'googlelogo')]");
        return selectControl.isControlExist();
    }
}