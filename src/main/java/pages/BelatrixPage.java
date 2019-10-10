package pages;

import components.control.ButtonControl;
import components.control.TextControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import components.Page;
import components.control.SelectControl;
import services.ParameterService;

public class BelatrixPage extends Page {
    private static final Logger logger = LogManager.getLogger();
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
        textControl.setText(ParameterService.decryptString(pass));
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