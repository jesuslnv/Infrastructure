package pages;

import components.Page;
import components.control.ButtonControl;
import components.control.SelectControl;
import components.control.TextControl;
import org.openqa.selenium.WebDriver;

import java.util.Base64;

public class ManagedSolutionsPage extends Page {
    public ManagedSolutionsPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().defaultContent();
    }

    public void LoginToWeb_Ebay(String user, String pass) {
        webDriver.navigate().to("https://www.ebay.com/");
        // Set User
//        TextControl textControl = new TextControl(webDriver, "//input[@id='username']");
//        textControl.setText(user);
//        // Click on Continue Button
//        ButtonControl buttonControl = new ButtonControl(webDriver, "//a[@id='continuebutton']");
//        buttonControl.click();
//        // Set Desktop Username
//        textControl = new TextControl(webDriver, "//input[@id='desktop']");
//        textControl.setText(user);
//        // Set Desktop Password
//        textControl = new TextControl(webDriver, "//input[@name='password']");
//        byte[] decodedPass = Base64.getDecoder().decode(pass.getBytes());
//        String encodedPass = Base64.getEncoder().encodeToString("Curtedz@111".getBytes());
//        System.out.println("WAWA: " + encodedPass);
//        System.out.println("WEWE: " + new String(Base64.getDecoder().decode(encodedPass.getBytes())));
//        textControl.setText(new String(decodedPass));
//        // Click on Sign In Button
//        buttonControl = new ButtonControl(webDriver, "//button[@id='btn_signin']");
//        buttonControl.click();
        // Wait for page Load
        waitForPageLoad(60);
    }

    public boolean IBMCloud_CorrectlyDisplayed() {
        SelectControl selectControl = new SelectControl(webDriver, "//h4[contains(.,'IBM Cloud')]");
        return selectControl.isControlExist();
    }

    public void NavigateToTheURL(String url) {
        webDriver.navigate().to(url);
        waitForPageLoad(60);
    }
}