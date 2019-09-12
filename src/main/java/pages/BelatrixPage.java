package pages;

import components.control.ButtonControl;
import components.control.TextControl;
import org.openqa.selenium.WebDriver;
import components.Page;
import components.control.SelectControl;

public class BelatrixPage extends Page {
    private TextControl textControl;
    private ButtonControl buttonControl;
    private SelectControl selectControl;

    public BelatrixPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public void LoginTo_BelatrixPage(String user, String pass) {
        webDriver.navigate().to("https://intranet.belatrixsf.com/");
        textControl = new TextControl(webDriver, "//input[@id='username']");
        textControl.setText(user);
        textControl = new TextControl(webDriver, "//input[@id='password']");
        textControl.setText(pass);
        buttonControl = new ButtonControl(webDriver, "//button[@type='submit']");
        buttonControl.setWaitForClick(1);
        buttonControl.click();
        // Wait for page Load
        waitForPageLoad(60);
    }

    public boolean BelatrixPage_CorrectlyDisplayed() {
        selectControl = new SelectControl(webDriver, "//a[@title='INTRANET']");
        return selectControl.isControlExist();
    }

    public void ClickOn_MyFilesButton() {
        buttonControl = new ButtonControl(webDriver, "//div[@id='HEADER_MY_FILES']");
        buttonControl.click();
    }
}