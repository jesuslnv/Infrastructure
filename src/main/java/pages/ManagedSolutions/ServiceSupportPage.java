package pages.ManagedSolutions;

import components.control.ButtonControl;
import components.control.SelectControl;
import components.control.TextControl;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ManagedSolutionsPage;

public class ServiceSupportPage extends ManagedSolutionsPage {
    public ServiceSupportPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean ServiceSupportPage_CorrectlyDisplayed() {
        SelectControl selectControl = new SelectControl(webDriver, "//label[text()='Service Support']");
        return selectControl.isControlExist();
    }

    public void ClickOn_CreateTicketButton() {
        ButtonControl buttonControl = new ButtonControl(webDriver, "//button[text()='Create Ticket']");
        buttonControl.click();
    }

    public void Set_FilterBy(String value) {
        TextControl textControl = new TextControl(webDriver, "//input[@id='search__input-1']");
        textControl.setText(value);
        webDriver.findElement(By.xpath("//input[@id='search__input-1']")).sendKeys(Keys.RETURN);
        waitForModal("",30);
    }

    public boolean ResultedTicketList_CorrectlyDisplayed(){
        SelectControl selectControl = new SelectControl(webDriver, "(//table/thead[contains(.,'Ticket')]/../tbody/tr)[2]");
        return selectControl.isControlExist();
    }
}