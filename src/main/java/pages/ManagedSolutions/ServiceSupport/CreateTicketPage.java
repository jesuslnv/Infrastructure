package pages.ManagedSolutions.ServiceSupport;

import components.control.ButtonControl;
import components.control.SelectControl;
import components.control.TextControl;
import org.openqa.selenium.WebDriver;
import pages.ManagedSolutions.ServiceSupportPage;

public class CreateTicketPage extends ServiceSupportPage {
    public CreateTicketPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean CreateTicketPage_CorrectlyDisplayed() {
        SelectControl selectControl = new SelectControl(webDriver, "//h2[text()='Create Ticket']");
        return selectControl.isControlExist();
    }

    public void ClickOn_TicketType(String value) {
        ButtonControl buttonControl = new ButtonControl(webDriver, "//label[contains(text(),'" + value + "')]");
        buttonControl.click();
    }

    public void Select_Location(String value) {
        SelectControl selectControl = new SelectControl(webDriver, "(//label[text()='Location']/..//ul[contains(.,'N/A')])[1]");
        selectControl.selectButtonElement("//a[text()='" + value + "']");
    }

    public void Select_CustomerExpedite(String value) {
        SelectControl selectControl = new SelectControl(webDriver, "(//label[text()='Customer Expedite']/..//ul[contains(.,'N/A')])[1]");
        selectControl.selectButtonElement("//a[text()='" + value + "']");
    }

    public void Set_ShortDescription(String value) {
        TextControl textControl = new TextControl(webDriver, "//input[@id='txtShortDescription']");
        textControl.setText(value);
    }

    public void Set_Description(String value) {
        TextControl textControl = new TextControl(webDriver, "//textarea[@id='txtDescription']");
        textControl.setText(value);
    }

    public void ClickOn_SubmitButton() {
        ButtonControl buttonControl = new ButtonControl(webDriver, "//button[text()='Submit']");
        buttonControl.click();
        waitForPageLoad(60);
    }
}