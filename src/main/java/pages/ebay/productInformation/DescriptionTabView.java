package pages.ebay.productInformation;

import components.control.SelectControl;
import org.openqa.selenium.WebDriver;
import pages.ebay.ProductInformationView;

public class DescriptionTabView extends ProductInformationView {
    private SelectControl selectControl;

    public DescriptionTabView(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean descriptionTabViewCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[@class='itemAttr']");
        return selectControl.isControlExist();
    }
}