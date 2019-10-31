package pages.Google;

import components.control.ButtonControl;
import components.control.SelectControl;
import org.openqa.selenium.WebDriver;
import pages.GooglePage;

public class ImagesPage extends GooglePage {
    private SelectControl selectControl;
    private ButtonControl buttonControl;

    public ImagesPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean imagesPage_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "(//a[1]/div[1]/img)[1]");
        return selectControl.isControlExist();
    }

    public void clickOn_FirstImageFound() {
        buttonControl = new ButtonControl(webDriver, "(//div[2]/div/div/a/img[@jsaction='load:str.tbn'])[1]");
        buttonControl.click();
    }
}