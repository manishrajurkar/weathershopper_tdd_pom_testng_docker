package com.automation.develop.base;

import com.automation.develop.utilities.GenericConfigs;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static org.openqa.selenium.Platform.*;


/**
 * ----------------------------------------------------------------------------------------------------
 * Write all the common methods in the BaseClass which will be used in the test cases
 * Ex. Initiate browser driver, property files, logger file.
 * Extend this base class into test class to use the static methods.
 * -----------------------------------------------------------------------------------------------------
 */

public class BaseClass implements RulesForBaseClass {
    public static WebDriver driver;
    public static Properties confProp;
    public static FileInputStream fis;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;
    public static Logger logger;//= LogManager.getLogger(checkLog4j.class.getName());
    public static ExtentReports extentReports;

    /*-------------------------------------------------------
    @Comment: Initialize Property File
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
------------------------------------------------------- */
    @Override
    public void initializePropertiesFile() {
        //if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\configuration.properties");
                confProp = new Properties();
                confProp.load(fis);
                logger.info("Property file loaded successfully");
            } catch (Exception e) {
                logger.error("Could Not find the file at the location:");
                e.printStackTrace();
            }
//        } else {
//            logger.info("Property File Already in use");
//        }
    }


    public void initializeRemoteWebDriver(String browser, boolean headless ) throws MalformedURLException {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            DesiredCapabilities capabilities = new DesiredCapabilities();

            options.addArguments("start-maximized");

            capabilities.setPlatform(ANY);
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("94");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            options.merge(capabilities);

            URL url = new URL("http://172.28.223.1:4444");
            driver = new RemoteWebDriver(url, capabilities);
            System.out.println("Remote Driver ready");
        }

    }

    /*-------------------------------------------------------
    @Comment: Explicit wait.
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
            driver = new ChromeDriver(chromeOptions);
            logger.info("Chrome Driver initiated");

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (headless) firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("start-maximized");
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOptions);
            logger.info("Firefox Driver initiated");

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            if (headless) edgeOptions.addArguments("--headless");
            edgeOptions.addArguments("start-maximized");
            edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
            logger.info("Edge Driver initiated");

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GenericConfigs.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(GenericConfigs.PAGE_LOAD_TIMEOUT));
        js = (JavascriptExecutor) driver;
    }

    /*-------------------------------------------------------
    @Comment: Initialize WebDriver File using WebDriverManager
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
    ------------------------------------------------------- */
    public void initializeExplicitWebDriverWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GenericConfigs.WEBDRIVER_WAIT));
        logger.info("Explicit Wait initialized for " + GenericConfigs.WEBDRIVER_WAIT + " Seconds");
        // wait = new WebDriverWait(driver, GenericConfigs.WEBDRIVER_WAIT);
        //wait = new WebDriverWait(driver, 30);

    }

    /*-------------------------------------------------------
    @Comment: get the url from address bar
    @developer : Manish Rajurkar
    @Date   : 15.12.2021
    ------------------------------------------------------- */
    public static String getURL() {
        return driver.getCurrentUrl();
    }


    /*-----------------------------------------------------------------------


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

    public void extentReport() throws IOException {
        extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./target/Reports/weathershopperSpark.html");

        // WAY 1 - XMLLOAD
        extentSparkReporter.loadXMLConfig(new File("src/test/resources/spark-config.xml"));
        extentReports.attachReporter(extentSparkReporter);

//        //WAY 2  - Json Load
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
