package pages.Belatrix;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import components.control.ButtonControl;
import components.control.SelectControl;
import components.control.TextControl;
import pages.BelatrixPage;

public class MyFilesPage extends BelatrixPage {
    private SelectControl selectControl;

    public MyFilesPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean MyFilesPage_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//script[contains(.,'PAGEID = \"myfiles\"')]");
        return selectControl.isControlExist();
    }
}