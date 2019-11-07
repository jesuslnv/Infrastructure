package pages.Ebay.ProductInformation;

import components.control.SelectControl;
import org.openqa.selenium.WebDriver;
import pages.Ebay.ProductInformationPage;

public class DescriptionTabView extends ProductInformationPage {
    private SelectControl selectControl;

    public DescriptionTabView(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean descriptionTabView_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[@class='itemAttr']");
        return selectControl.isControlExist();
    }
}