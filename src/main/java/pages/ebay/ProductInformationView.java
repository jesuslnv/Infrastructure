package pages.ebay;

import components.control.ButtonControl;
import components.control.SelectControl;
import org.openqa.selenium.WebDriver;
import pages.EbayPage;

public class ProductInformationView extends EbayPage {
    private SelectControl selectControl;
    private ButtonControl buttonControl;

    public ProductInformationView(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean productInformationPageCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[@id='LeftSummaryPanel']");
        return selectControl.isControlExist();
    }

    public void clickOnDescriptionTab() {
        buttonControl = new ButtonControl(webDriver, "//a[@aria-controls='desc_panel']");
        buttonControl.click();
    }
}