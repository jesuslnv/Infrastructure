package pages.Belatrix;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import components.control.ButtonControl;
import components.control.SelectControl;
import components.control.TextControl;
import pages.BelatrixPage;

public class MyFilesPage extends BelatrixPage {
    public MyFilesPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public boolean MyFilesPage_CorrectlyDisplayed() {
        SelectControl selectControl = new SelectControl(webDriver, "//script[contains(.,'PAGEID = \"myfiles\"')]");
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