package pages.ebay;

import components.control.ButtonControl;
import components.control.SelectControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.EbayPage;

import java.util.ArrayList;

public class ProductInformationView extends EbayPage {
    private static final Logger LOGGER = LogManager.getLogger();
    private SelectControl selectControl;
    private ButtonControl buttonControl;

    public ProductInformationView(WebDriver webDriver) {
        super(webDriver);
        //Move to new Tab for this View (Wait 4 seconds for the Tab be opened) (Example with Thread Sleep)
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            LOGGER.error("ERROR: " + e.getMessage());
        }
        ArrayList<String> wid = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(wid.get(1));
    }

    public boolean productInformationPageCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[@id='LeftSummaryPanel']");
        return selectControl.isControlExist();
    }

    public void clickOnDescriptionTab() {
        buttonControl = new ButtonControl(webDriver, "//div[@id='desc_panel']");
        buttonControl.click();
    }
}