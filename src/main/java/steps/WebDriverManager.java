package steps;

import cucumber.api.Scenario;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.junit.ExecutionUnitRunner;
import cucumber.runtime.junit.JUnitReporter;
import cucumber.runtime.model.CucumberFeature;
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
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

class WebDriverManager {
	private static final Logger logger = LogManager.getLogger(WebDriverManager.class);
	private WebDriver webDriver = null;
	private ChromeOptions chromeOptions;
	private FirefoxOptions firefoxOptions;
	private static int numberOfScenarios = 0;
	private boolean owasp = false, seleniumGrid = false, chrome = false;

	WebDriver getDriver(Scenario scenario) {
		// OWASP ZAP Configuration
		if (owasp) {
			configOwasp();
		}
		// Selenium Grid configuration
		if (seleniumGrid) {
			configSeleniumGrid();
			return webDriver;
		} else {
			// Normal WebDriver Configuration
			if (chrome) {
				configChromeDriver();
			} else {
				configFirefoxDriver();
			}
		}
		webDriver.manage().window().maximize();
		webDriver.manage().deleteAllCookies();
		webDriver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		readTagsRunner(scenario);
		logger.info("-------------------------------------------------------------------------");
		logger.info("Pending Scenarios: " + numberOfScenarios);
		return webDriver;
	}

	void closeDriver(Scenario scenario) {
		if (webDriver != null) {
			// Takes an ScreenShot
			byte[] screenShot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenShot, "image/png");
			// -------------------------------------------------------
			// Quit the Driver
			logger.info("FAILED SCENARIO: " + scenario.isFailed());
			logger.info("Trying to quit the Scenario (" + scenario.getId() + ")");
			logger.info("-------------------------------------------------------------------------");
			webDriver.quit();
		}
		webDriver = null;
		numberOfScenarios--;
	}

	// <editor-fold desc="DRIVER CONFIG">
	private void configChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		String fileMimeTypes = "application/msword," + "text/plain," + "text/html," + "image/jpeg," + "image/png," + "application/csv," + "text/csv," + "application/octet-stream," + "application/csv," + "pdf,"
				+ "application/pdf," + "application/x-pdf," + "application/acrobat," + "application/vnd.pdf," + "text/pdf," + "text/x-pdf," + "application/vnd.adobe.xfdf," + "application/vnd.fdf,"
				+ "application/vnd.adobe.xdp+xml," + "application/xml," + "text/xml," + "application/excel," + "application/vnd.ms-excel," + "application/msexcel," + "application/x-msexcel," + "application/x-ms-excel,"
				+ "application/x-excel," + "application/x-dos_ms_excel," + "application/xls," + "application/x-xls," + "text/xls," + "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--browser.download.manager.showWhenStarting=false");
		chromeOptions.addArguments("--browser.download.folderList=2");
		chromeOptions.addArguments("--browser.helperApps.neverAsk.saveToDisk=" + fileMimeTypes);
		chromeOptions.addArguments("--browser.helperApps.neverAsk.openFile=" + fileMimeTypes);
		chromeOptions.addArguments("--browser.download.dir=C:\\Temp\\");
		logger.info("Starting a normal Chrome WebDriver");
		webDriver = new ChromeDriver(chromeOptions);
	}

	private void configFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
		String fileMimeTypes = "application/msword," + "text/plain," + "text/html," + "image/jpeg," + "image/png," + "application/csv," + "text/csv," + "application/octet-stream," + "application/csv," + "pdf,"
				+ "application/pdf," + "application/x-pdf," + "application/acrobat," + "application/vnd.pdf," + "text/pdf," + "text/x-pdf," + "application/vnd.adobe.xfdf," + "application/vnd.fdf,"
				+ "application/vnd.adobe.xdp+xml," + "application/xml," + "text/xml," + "application/excel," + "application/vnd.ms-excel," + "application/msexcel," + "application/x-msexcel," + "application/x-ms-excel,"
				+ "application/x-excel," + "application/x-dos_ms_excel," + "application/xls," + "application/x-xls," + "text/xls," + "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		firefoxOptions = new FirefoxOptions();
		firefoxOptions.setLogLevel(FirefoxDriverLogLevel.ERROR);
		firefoxOptions.addArguments("--browser.download.manager.showWhenStarting=false");
		firefoxOptions.addArguments("--browser.download.folderList=2");
		firefoxOptions.addArguments("--browser.helperApps.neverAsk.saveToDisk=" + fileMimeTypes);
		firefoxOptions.addArguments("--browser.helperApps.neverAsk.openFile=" + fileMimeTypes);
		firefoxOptions.addArguments("--browser.download.dir=C:\\Temp\\");
		logger.info("Starting a normal Firefox WebDriver");
		webDriver = new FirefoxDriver(firefoxOptions);
	}
	// </editor-fold>

	// <editor-fold desc="READ TAGS">
	private void readTagsRunner(Scenario scenario) {
		try {
			Field f = scenario.getClass().getDeclaredField("reporter");
			f.setAccessible(true);
			JUnitReporter reporter = (JUnitReporter) f.get(scenario);
			Field executionRunnerField = reporter.getClass().getDeclaredField("executionUnitRunner");
			executionRunnerField.setAccessible(true);
			ExecutionUnitRunner executionUnitRunner = (ExecutionUnitRunner) executionRunnerField.get(reporter);
			Field runtimeField = executionUnitRunner.getClass().getDeclaredField("runtime");
			runtimeField.setAccessible(true);
			Runtime runtime = (Runtime) runtimeField.get(executionUnitRunner);
			Field resourceLoaderField = runtime.getClass().getDeclaredField("resourceLoader");
			resourceLoaderField.setAccessible(true);
			ResourceLoader resourceLoader = (ResourceLoader) resourceLoaderField.get(runtime);
			Field runtimeOptionsField = runtime.getClass().getDeclaredField("runtimeOptions");
			runtimeOptionsField.setAccessible(true);
			RuntimeOptions runtimeOptions = (RuntimeOptions) runtimeOptionsField.get(runtime);
			for (Object obj : runtimeOptions.getFilters()) {
				switch (obj.toString().trim()) {
				case "~@Chrome":
					chrome = true;
				case "~@SeleniumGrid":
					seleniumGrid = true;
					break;
				case "~@OWASP":
					owasp = true;
					break;
				}
			}
			// Only set the variable at the beginning
			if (numberOfScenarios <= 0) {
				List<CucumberFeature> features = runtimeOptions.cucumberFeatures(resourceLoader);
				for (CucumberFeature cucumberFeature : features) {
					numberOfScenarios = numberOfScenarios + cucumberFeature.getFeatureElements().size();
				}
			}
		} catch (NoSuchFieldException | IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}
	// </editor-fold>

	// <editor-fold desc="OWASP ZAP">
	private void configOwasp() {
		String httpProxy = "10.1.57.165:9090";
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(httpProxy).setFtpProxy(httpProxy).setSslProxy(httpProxy);
		chromeOptions.setCapability(CapabilityType.PROXY, proxy);
	}
	// </editor-fold>

	// <editor-fold desc="SELENIUM GRID">
	private void configSeleniumGrid() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		// Server where the HUB is Running
		String Hub = "http://MIA38747JEN001.am.tmrk.corp:4444/wd/hub";
		logger.info("HUB: " + Hub);
		try {
			WebDriver webDriver = new RemoteWebDriver(new URL(Hub), capabilities);
			webDriver.manage().window().maximize();
			webDriver.manage().deleteAllCookies();
			webDriver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
			webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			// ---------- Getting Selenium Node -----------
			String nodeIP = getNodeIP((RemoteWebDriver) webDriver);
			logger.info("NODE: " + nodeIP);
			// --------------------------------------------
		} catch (MalformedURLException e) {
			logger.info("Error Starting Remote Driver: " + e.getMessage());
		}
	}

	private String getNodeIP(RemoteWebDriver remoteDriver) {
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
	// </editor-fold>
}