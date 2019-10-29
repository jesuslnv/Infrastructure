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
        selectControl = new SelectControl(webDriver, "//div[@data-hp='imgrc']");
        return selectControl.isControlExist();
    }
}