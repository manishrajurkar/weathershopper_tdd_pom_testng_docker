package com.automation.develop.base;

import com.automation.develop.utilities.GenericConfigs;
import com.automation.develop.utilities.GenericMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;


/**
 * ----------------------------------------------------------------------------------------------------
 * Write all the common methods in the BaseClass which will be used in the test cases
 * Ex. Initiate browser driver, property files, logger file.
 * Extend this base class into test class to use the static methods.
 * -----------------------------------------------------------------------------------------------------
 */

public class BaseClass implements RulesForBaseClass {
    public RemoteWebDriver driver;
    // public WebDriver driver;
    public static Properties confProp;
    public static FileInputStream fis;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    public static Logger logger;//= LogManager.getLogger(checkLog4j.class.getName());
    public static ExtentReports extentReports;
    public static DesiredCapabilities capabilities;
    public static DesiredCapabilities capabilities1;
    public static DesiredCapabilities capabilities2;
    public static SoftAssert softAssert;
    public static GenericMethods genericMethods = new GenericMethods();
    //protected static ThreadLocal<RemoteWebDriver> driver1 = new ThreadLocal<>();

    @BeforeSuite
    public void suiteSetup() throws IOException, InterruptedException {
        log4j();
        initializePropertiesFile();
        extentReport();
        softAssertion();
        startDocker();
    }

    @AfterSuite
    public void suiteTearDown() throws IOException, InterruptedException {
        extentReports.flush();
        stopDocker();
    }

    /*-------------------------------------------------------
       Comment: SoftAssertionsTestNG
       Author : Manish Rajurkar
       Date   : 26.1.2021
    ------------------------------------------------------- */
     public void softAssertion(){
         softAssert = new SoftAssert();

     }

    /*-------------------------------------------------------
    @Comment: Docker start and Stop
    @Author : Manish Rajurkar
    @Date   : 18.1.2021
     ------------------------------------------------------- */
    public static void startDocker() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start dockerStart.bat");
        Thread.sleep(15000);
        logger.info("Docker Selenium HUB started");
    }

    public static void stopDocker() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start dockerStop.bat");
        Thread.sleep(15000);
        logger.info("Docker Selenium HUB stopped");
    }

    /*-------------------------------------------------------
       Comment: Clean browser.
       Author : Manish Rajurkar
       Date   : 26.1.2021
 ------------------------------------------------------- */
    public static void cleanBrowserDrivers() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start cleanBrowserDrivers.bat");
        Thread.sleep(15000);
        logger.info("Docker Selenium HUB started");
    }

    /*-------------------------------------------------------
    @Comment: Initialize Property File
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
     ------------------------------------------------------- */
    @Override
    public void initializePropertiesFile() {
//        if (driver == null) {
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\configuration.properties");
            confProp = new Properties();
            confProp.load(fis);
            logger.info("Property file loaded successfully");
        } catch (Exception e) {
            logger.error("Could Not find the file at the location:");
            e.printStackTrace();
        }
    }

    /*-------------------------------------------------------
    Initialize remote driver
    // Using selenium server 4
    ref: youtube.com/watch?v=BKQwvoMzVmg
    ------------------------------------------------------- */
    public void initializeRemoteWebDriver(String browser, boolean headless) throws Exception {
//        if (driver == null) {

        String url = "http://localhost:4444";

        if (browser.equalsIgnoreCase("chrome")) {
            capabilities = new DesiredCapabilities();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            capabilities.setCapability("browserName", "CHROME");
            capabilities.setCapability("platform", Platform.ANY);
            capabilities.merge(chromeOptions);
            //chromeOptions.merge(capabilities);
            driver = new RemoteWebDriver(new URL(url), capabilities);
            // driver.set(new RemoteWebDriver(new URL("http://10.5.53.225:4444"), capabilities));

        } else if (browser.equalsIgnoreCase("firefox")) {
            capabilities1 = new DesiredCapabilities();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("start-maximized");
            capabilities1.setCapability("browserName", "FIREFOX");
            capabilities1.setCapability("platform", Platform.ANY);
            capabilities1.merge(firefoxOptions);
            //firefoxOptions.merge(capabilities);
            driver = new RemoteWebDriver(new URL(url), capabilities1);
            //driver.set(new RemoteWebDriver(new URL("http://10.5.53.225:4444"), capabilities1));

        } else if (browser.equalsIgnoreCase("msedge")) {
            capabilities2 = new DesiredCapabilities();
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("start-maximized");
            capabilities2.setCapability("browserName", "EDGE");
            capabilities2.setCapability("platform", Platform.ANY);
            capabilities2.merge(edgeOptions);
            driver = new RemoteWebDriver(new URL(url), capabilities2);

        }

    }
    /*-------------------------------------------------------
    @Comment: Local Webdriver instance.
    @developer : Manish Rajurkar
    @Date   : 26.12.2021
    ------------------------------------------------------- */

    @Override
    public void initializeBrowserUsingDriverManager(String browser, boolean headless) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            if (headless) chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("start-maximized");
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            WebDriverManager.chromedriver().setup();
            //WebDriverManager.chromedriver().cachePath("C:\\ManishMain\\Automation\\weathershopper\\weathershopper\\src\\test\\target\\Executables").setup();
            driver = new ChromeDriver(chromeOptions);
            logger.info("Chrome Driver initiated");
            //js = (JavascriptExecutor) driver;

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (headless) firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("start-maximized");
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            //WebDriverManager.firefoxdriver().cachePath("C:\\ManishMain\\Automation\\weathershopper\\weathershopper\\src\\test\\target\\Executables").setup();
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOptions);
            logger.info("Firefox Driver initiated");
            logger.info("Explicit Wait initialized for " + GenericConfigs.WEBDRIVER_WAIT + " Seconds" + "browser name" + browser);
            //js = (JavascriptExecutor) driver;

        } else if (browser.equalsIgnoreCase("msedge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            if (headless) edgeOptions.addArguments("--headless");
            edgeOptions.addArguments("start-maximized");
            edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            //WebDriverManager.edgedriver().cachePath("C:\\ManishMain\\Automation\\weathershopper\\weathershopper\\src\\test\\targetcd \\Executables");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
            logger.info("Edge Driver initiated");
            //js = (JavascriptExecutor) driver;

        }

    }

    /*-------------------------------------------------------
    @Comment: Initialize WebDriver File using WebDriverManager
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
    ------------------------------------------------------- */
    public void initializeExplicitWebDriverWait(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GenericConfigs.WEBDRIVER_WAIT));
        logger.info("Explicit Wait initialized for " + GenericConfigs.WEBDRIVER_WAIT + " Seconds");

    }

    /*-------------------------------------------------------
    @Comment: get the url from address bar
    @developer : Manish Rajurkar
    @Date   : 15.12.2021
    ------------------------------------------------------- */
    public String getURL() {
        return driver.getCurrentUrl();
    }


    /*-----------------------------------------------------------------------
    @Comment: Log4j configuration
    @Author : Manish Rajurkar
    @Date   : 15.12.2021

    -------------------------------------------------------------------------- */
    public void log4j() {

        logger = LogManager.getLogger(BaseClass.class.getName());
        logger.info("Logger initiated successfully");
//         logger.debug("This is a debug message");
//        logger.info("This is an info message");
//        logger.warn("This is a warn message");
//        logger.error("This is an error message");
//        logger.fatal("This is a fatal message");
    }

    /*-----------------------------------------------------------------------
    @Comment: Extent Report Configuration
    @Author : Manish Rajurkar
    @Date   : 15.1.2022

    -------------------------------------------------------------------------- */
    public void extentReport() throws IOException {
        extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./target/Reports/weathershopperSpark.html");

        // WAY 1 - XML
        extentSparkReporter.loadXMLConfig(new File("src/test/resources/spark-config.xml"));
        extentReports.attachReporter(extentSparkReporter);

//        //WAY 2  - Json
//        //WAY 3 - Manual Code
//        extentSparkReporter.config(
//                ExtentSparkReporterConfig.builder()
//                        .theme(Theme.DARK)
//                        .documentTitle("Extent Report")
//                        .build());
//
//
//        // WAY 4  Manual traditional code.
//          extentSparkReporter.config().setReportName("WebAutomation Report");
//          extentSparkReporter.config().setDocumentTitle("Extent report");
//          extentSparkReporter.config().setTheme(Theme.DARK);


    }

}
