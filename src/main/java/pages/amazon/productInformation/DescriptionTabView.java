package pages.amazon.productInformation;

import components.control.SelectControl;
import org.openqa.selenium.WebDriver;
import pages.amazon.ProductInformationView;

public class DescriptionTabView extends ProductInformationView {
    private SelectControl selectControl;

    public DescriptionTabView(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean descriptionTabViewCorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//div[@data-feature-name='productDescription']");
        return selectControl.isControlExist();
    }
}