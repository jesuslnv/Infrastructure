package steps.continuumsecurity.web;

import org.apache.logging.log4j.LogManager;
import steps.continuumsecurity.Config;
import steps.continuumsecurity.UnexpectedContentException;
import steps.continuumsecurity.clients.AuthTokenManager;
import steps.continuumsecurity.clients.Browser;
import steps.continuumsecurity.web.drivers.DriverFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class WebApplication extends Application {
    protected Browser browser;
    protected WebDriver driver;

    public WebApplication() {
        log = LogManager.getLogger();
        setImplicitWait(3, TimeUnit.SECONDS);
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
        this.driver = browser.getWebDriver();
    }

    public void verifyTextPresent(String text) {
        if (!this.browser.getWebDriver().getPageSource().contains(text))
            throw new UnexpectedContentException("Expected text: [" + text + "] was not found.");
    }

    public void setImplicitWait(long time, TimeUnit unit) {
        DriverFactory.getDriver(Config.getInstance().getDefaultDriver()).manage().timeouts().implicitlyWait(time, unit);
        DriverFactory.getProxyDriver(Config.getInstance().getDefaultDriver()).manage().timeouts().implicitlyWait(time, unit);
    }

    public WebElement findAndWaitForElement(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(browser.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            throw new NoSuchElementException(e.getMessage());
        }
        return browser.getWebDriver().findElement(by);
    }

    public void navigate() {
        browser.getWebDriver().get(Config.getInstance().getBaseUrl());
    }

    @Override
    public void enableHttpLoggingClient() {
        setBrowser(new Browser(DriverFactory.getProxyDriver(Config.getInstance().getDefaultDriver())));
    }

    @Override
    public void enableDefaultClient() {
        setBrowser(new Browser(DriverFactory.getDriver(Config.getInstance().getDefaultDriver())));
    }

    @Override
    public AuthTokenManager getAuthTokenManager() {
        return browser;
    }

}