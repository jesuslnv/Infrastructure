package steps;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

class WebDriverManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static WebDriver webDriver = null;
    private static ChromeOptions chromeOptions;
    private static int numberOfScenarios = 0;

    //Private constructor to hide the implicit public one
    private WebDriverManager() {
    }

    static WebDriver getWebDriver() {
        //If the current WebDriver is NULL it will be settled
        if (webDriver == null) {
            //Count Scenarios
            numberOfScenarios++;
            //Normal WebDriver Configuration
            generalWebDriverConfiguration();
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            LOGGER.info("-------------------------------------------------------------------------");
            LOGGER.info("Scenario Number: {}", numberOfScenarios);
        }
        return webDriver;
    }

    static void closeDriver(Scenario scenario) {
        if (webDriver != null) {
            //--------------------------------------------------------------------------------------
            //Takes an ScreenShot
            byte[] screenShot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", "Image for Scenario: " + scenario.getName());
            //--------------------------------------------------------------------------------------
            //Quit the Driver
            LOGGER.info("SCENARIO STATUS: {}", (scenario.isFailed() ? "FAILED" : "OK"));
            LOGGER.info("Trying to quit the Scenario");
            LOGGER.info("-------------------------------------------------------------------------");
            webDriver.quit();
        }
        webDriver = null;
    }

    //<editor-fold desc="DRIVER CONFIG">
    private static void generalWebDriverConfiguration() {
        //------- Configure Webdriver Executable Location -------
        System.getenv();
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        //--------------- Remove Unnecessary Logs ---------------
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        //---------------- Define Driver Options ----------------
        chromeOptions = new ChromeOptions();
        for (String tmpArgument : Parameters.SELENIUM_BROWSER_ARGUMENTS) {
            chromeOptions.addArguments(tmpArgument);
        }
        //-------------------------------------------------------
        LOGGER.info("-------------------------------------------------------------------------");
        LOGGER.info("Starting a normal Chrome WebDriver");
        webDriver = new ChromeDriver(chromeOptions);
    }
    //</editor-fold>
}