package pages.Belatrix;

import components.control.SelectControl;
import org.openqa.selenium.WebDriver;
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