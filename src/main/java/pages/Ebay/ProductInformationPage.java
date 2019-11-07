package pages.Ebay;

import components.control.ButtonControl;
import components.control.SelectControl;
import org.openqa.selenium.WebDriver;
import pages.EbayPage;

public class ProductInformationPage extends EbayPage {
    private SelectControl selectControl;
    private ButtonControl buttonControl;

    public ProductInformationPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean productInformationPage_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[@id='LeftSummaryPanel']");
        return selectControl.isControlExist();
    }

    public void clickOn_DescriptionTab() {
        buttonControl = new ButtonControl(webDriver, "//a[@aria-controls='desc_panel']");
        buttonControl.click();
    }
}