package pages.amazon;

import components.control.ButtonControl;
import components.control.SelectControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.AmazonPage;

import java.util.ArrayList;

public class ProductInformationView extends AmazonPage {
    private static final Logger LOGGER = LogManager.getLogger();
    private SelectControl selectControl;
    private ButtonControl buttonControl;

    public ProductInformationView(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean productInformationPageCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[@id='detailBullets']");
        return selectControl.isControlExist();
    }

    public void clickOnDescriptionTab() {
        buttonControl = new ButtonControl(webDriver, "//div[@data-feature-name='productDescription']");
        buttonControl.click();
    }
}