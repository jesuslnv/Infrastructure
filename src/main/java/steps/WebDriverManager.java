package steps;

import io.cucumber.core.api.Scenario;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

class WebDriverManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static WebDriver webDriver = null;
    private static ChromeOptions chromeOptions;
    private static FirefoxOptions firefoxOptions;
    private static int numberOfScenarios = 0;
    private static boolean owasp = false, seleniumGrid = false, chrome = false;

    static WebDriver getWebDriver(Scenario scenario) {
        //If the current WebDriver is NULL it will be settled
        if (webDriver == null) {
            String strTags = System.getProperty("cucumber.options").split(" datafile/features/")[0];
            readTagsRunner(strTags);
            //Selenium Grid configuration
            if (seleniumGrid) {
                configSeleniumGrid();
                return webDriver;
            } else {
                //Normal WebDriver Configuration
                generalWebDriverConfiguration();
            }
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            LOGGER.info("-------------------------------------------------------------------------");
            LOGGER.info("Scenario Number: " + numberOfScenarios);
            LOGGER.info("Scenario ID: " + scenario.getId());
        }
        return webDriver;
    }

    static void closeDriver(Scenario scenario) {
        if (webDriver != null) {
            //Takes an ScreenShot
            byte[] screenShot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot, "image/png");
            //-------------------------------------------------------
            //Quit the Driver
            LOGGER.info("SCENARIO STATUS: " + (scenario.isFailed() ? "FAILED" : "OK"));
            LOGGER.info("Trying to quit the Scenario");
            LOGGER.info("-------------------------------------------------------------------------");
            webDriver.quit();
        }
        webDriver = null;
    }

    //<editor-fold desc="DRIVER CONFIG">
    private static void generalWebDriverConfiguration() {
        //------- Configure Webdriver Executable Location -------
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        //--------------- Remove Unnecessary Logs ---------------
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        //---------------- Define Driver Options ----------------
        chromeOptions = new ChromeOptions();
        firefoxOptions = new FirefoxOptions();
        for (String tmpArgument : Parameters.SELENIUM_BROWSER_ARGUMENTS) {
            chromeOptions.addArguments(tmpArgument);
            firefoxOptions.addArguments(tmpArgument);
        }
        //----------------- Configure OWASP ZAP -----------------
        if (owasp) {
            LOGGER.info("Setting up OWASP ZAP");
            waitForSuccessfulConnectionToZap();
            Proxy proxy = new Proxy();
            String httpProxy = Parameters.OWASP_ZAP_HTTP_IP + ":" + Parameters.OWASP_ZAP_HTTP_PORT;
            proxy.setHttpProxy(httpProxy).setFtpProxy(httpProxy).setSslProxy(httpProxy);
            chromeOptions.setCapability(CapabilityType.PROXY, proxy);
            firefoxOptions.setCapability(CapabilityType.PROXY, proxy);
        }
        //-------------------------------------------------------
        if (chrome) {
            LOGGER.info("-------------------------------------------------------------------------");
            LOGGER.info("Starting a normal Chrome WebDriver");
            webDriver = new ChromeDriver(chromeOptions);
        } else {
            LOGGER.info("-------------------------------------------------------------------------");
            LOGGER.info("Starting a normal Firefox WebDriver");
            webDriver = new FirefoxDriver(firefoxOptions);
        }
    }
    //</editor-fold>

    //<editor-fold desc="OWASP ZAP CONFIG">
    private static void waitForSuccessfulConnectionToZap() {
        int timeoutInMs = 15000;
        int connectionTimeoutInMs = timeoutInMs;
        boolean connectionSuccessful = false;
        long startTime = System.currentTimeMillis();
        Socket socket = null;
        while (!connectionSuccessful) {
            try {
                LOGGER.info("Attempting to connect to ZAP API on: " + Parameters.OWASP_ZAP_HTTP_IP + " port: " + Parameters.OWASP_ZAP_HTTP_PORT);
                socket = new Socket();
                socket.connect(new InetSocketAddress(Parameters.OWASP_ZAP_HTTP_IP, Parameters.OWASP_ZAP_HTTP_PORT), connectionTimeoutInMs);
                connectionSuccessful = true;
                LOGGER.info("Successfully connected to ZAP");
            } catch (IOException ignore) {
                //Wait 1 second before trying to connect again
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    LOGGER.error(ex.getMessage());
                }
                //Verify if the elapsed time didn't exceed the limit
                long elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime >= timeoutInMs) {
                    LOGGER.error("Unable to connect to ZAP's proxy after " + timeoutInMs + " milliseconds.");
                    throw new RuntimeException("Unable to connect to ZAP's proxy after " + timeoutInMs + " milliseconds.");
                }
                connectionTimeoutInMs = (int) (timeoutInMs - elapsedTime);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="READ TAGS">
    private static void readTagsRunner(String strTags) {
        //Detect Chrome Browser
        if (strTags.contains("~@Chrome") || strTags.contains("not @Chrome")) {
            chrome = true;
        }
        //Detect Selenium Grid
        if (strTags.contains("~@SeleniumGrid") || strTags.contains("not @SeleniumGrid")) {
            seleniumGrid = true;
        }
        //Detect OWASP ZAP
        if (strTags.contains("~@OWASP") || strTags.contains("not @OWASP")) {
            owasp = true;
        }
        numberOfScenarios++;
    }
    //</editor-fold>

    //<editor-fold desc="SELENIUM GRID">
    private static void configSeleniumGrid() {
        DesiredCapabilities desiredCapabilities = null;
        if (chrome) {
            desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        } else {
            desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        }
        //Server where the HUB is Running
        String Hub = "http://MIA38747JEN001.am.tmrk.corp:4444/wd/hub";
        LOGGER.info("HUB: " + Hub);
        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(Hub), desiredCapabilities);
            remoteWebDriver.manage().window().maximize();
            remoteWebDriver.manage().deleteAllCookies();
            remoteWebDriver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
            remoteWebDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            //---------- Getting Selenium Node -----------
            String nodeIP = getNodeIP(remoteWebDriver);
            LOGGER.info("NODE: " + nodeIP);
            //--------------------------------------------
        } catch (MalformedURLException e) {
            LOGGER.info("Error Starting Remote Driver: " + e.getMessage());
        }
    }

    private static String getNodeIP(RemoteWebDriver remoteDriver) {
        String hostFound = null;
        try {
            HttpCommandExecutor ce = (HttpCommandExecutor) remoteDriver.getCommandExecutor();
            String hostName = ce.getAddressOfRemoteServer().getHost();
            int port = ce.getAddressOfRemoteServer().getPort();
            HttpHost host = new HttpHost(hostName, port);
            HttpClient client = HttpClientBuilder.create().build();
            URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + remoteDriver.getSessionId());
            BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", sessionURL.toExternalForm());
            HttpResponse response = client.execute(host, r);
            InputStream contents = response.getEntity().getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(contents, writer, "UTF8");
            JSONObject object = new JSONObject(writer.toString());
            URL myURL = new URL(object.getString("proxyId"));
            if ((myURL.getHost() != null) && (myURL.getPort() != -1)) {
                hostFound = myURL.getHost();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return hostFound;
    }
    //</editor-fold>
}