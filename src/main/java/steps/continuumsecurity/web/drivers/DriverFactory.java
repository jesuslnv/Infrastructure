package steps.continuumsecurity.web.drivers;

import org.apache.logging.log4j.LogManager;
import steps.continuumsecurity.Config;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class DriverFactory {
    private final static String CHROME = "chrome";
    private final static String FIREFOX = "firefox";
    private final static String HTMLUNIT = "htmlunit";

    private static DriverFactory dm;
    private static WebDriver driver;
    private static WebDriver proxyDriver;
    static Logger log = LogManager.getLogger();


    public static DriverFactory getInstance() {
        if (dm == null)
            dm = new DriverFactory();
        return dm;
    }

    public static WebDriver getProxyDriver(String name) {
        return getDriver(name, true);
    }

    public static WebDriver getDriver(String name) {
        return getDriver(name, false);
    }


    // Return the desired driver and clear all its cookies
    private static WebDriver getDriver(String type, boolean isProxyDriver) {
        WebDriver retVal = getInstance().findOrCreate(type, isProxyDriver);
        try {
            if (!retVal.getCurrentUrl().equals("about:blank")) {
                retVal.manage().deleteAllCookies();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return retVal;
    }


    public static void quitAll() {
        log.debug("closing all webDrivers");
        try {
            if (driver != null) driver.quit();
            if (proxyDriver != null) proxyDriver.quit();
        } catch (Exception e) {
            log.error("Error quitting webDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
 * Re-use drivers to reduce startup times
 */
    private WebDriver findOrCreate(String type, boolean isProxyDriver) {
        if (isProxyDriver) {
            if (proxyDriver != null) return proxyDriver;
            proxyDriver = createProxyDriver(type);
            return proxyDriver;
        } else {
            if (driver != null) return driver;
            driver = createDriver(type);
            return driver;
        }
    }

    private WebDriver createDriver(String type) {
        if (type.equalsIgnoreCase(CHROME)) return createChromeDriver(new DesiredCapabilities());
        else if (type.equalsIgnoreCase(FIREFOX)) return createFirefoxDriver(null);
        throw new RuntimeException("Unsupported WebDriver browser: "+type);
    }
    private WebDriver createProxyDriver(String type) {
        if (type.equalsIgnoreCase(CHROME)) return createChromeDriver(createProxyCapabilities(CHROME));
        else if (type.equalsIgnoreCase(FIREFOX)) return createFirefoxDriver(createProxyCapabilities(FIREFOX));
	throw new RuntimeException("Unsupported WebDriver browser: "+type);
    }

    public WebDriver createChromeDriver(DesiredCapabilities capabilities) {
        System.setProperty("webdriver.chrome.driver", Config.getInstance().getDefaultDriverPath());
        if (capabilities != null) {
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type");
            capabilities.setCapability(ChromeOptions.CAPABILITY,options);
            return new ChromeDriver(capabilities);
        } else return new ChromeDriver();

    }

    public WebDriver createFirefoxDriver(DesiredCapabilities capabilities) {

        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile("WebDriver");
        if (myProfile == null) {
            File ffDir = new File(System.getProperty("user.dir")+ File.separator+"ffProfile");
            if (!ffDir.exists()) {
                ffDir.mkdir();
            }
            myProfile = new FirefoxProfile(ffDir);
        }
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(true);
        myProfile.setPreference("webdriver.load.strategy", "unstable");
	    String noProxyHosts = Config.getInstance().getNoProxyHosts();
	    if (! noProxyHosts.isEmpty()) {
	        myProfile.setPreference("network.proxy.no_proxies_on", noProxyHosts);
	    }
        if (capabilities == null) {
            capabilities = new DesiredCapabilities();
        }
        capabilities.setCapability(FirefoxDriver.PROFILE, myProfile);
        System.setProperty("webdriver.gecko.driver", Config.getInstance().getDefaultDriverPath());
        return new FirefoxDriver(capabilities);
    }

    public DesiredCapabilities createProxyCapabilities(String type) {
        DesiredCapabilities capabilities = null;
	switch (type) {
	case CHROME:
	    capabilities = DesiredCapabilities.chrome();
	    break;
	case FIREFOX:
	    capabilities = DesiredCapabilities.firefox();
	    break;
	case HTMLUNIT:
	    capabilities = DesiredCapabilities.htmlUnit();
	    break;
	default:
	    break;
	}
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(Config.getInstance().getProxyHost() + ":" + Config.getInstance().getProxyPort());
        proxy.setSslProxy(Config.getInstance().getProxyHost() + ":" + Config.getInstance().getProxyPort());
        capabilities.setCapability("proxy", proxy);
        return capabilities;
    }

}
