package pages.Google.Images;

import components.control.SelectControl;
import org.openqa.selenium.WebDriver;
import pages.Google.ImagesPage;

public class ImagePreviewPage extends ImagesPage {
    private SelectControl selectControl;

    public ImagePreviewPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean imagesPreviewPage_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[2]/div[contains(@jsaction,'contextmenu')]");
        return selectControl.isControlExist();
    }
}