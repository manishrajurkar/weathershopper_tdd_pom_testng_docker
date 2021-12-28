package com.automation.develop.base;

import com.automation.develop.utilities.GenericConfigs;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
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
    public static WebDriver driver;
    public static Properties confProp;
    public static FileInputStream fis;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;

    /*-------------------------------------------------------
    @Comment: Initialize Property File
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
------------------------------------------------------- */
    @Override
    public void initializePropertiesFile() {
        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\configuration.properties");
                confProp = new Properties();
                confProp.load(fis);
                System.out.println("Property file loaded successfully");
            } catch (Exception e) {
                System.out.println("Could Not find the file at the location:");
                e.printStackTrace();
            }
        } else {
            System.out.println("Property File Already in use");
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
            while (headless) chromeOptions.addArguments("--headless");
                             chromeOptions.addArguments("start-maximized");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            while (headless) firefoxOptions.addArguments("--headless");
                             firefoxOptions.addArguments("start-maximized");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOptions);

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            while (headless) edgeOptions.addArguments("--headless");
                             edgeOptions.addArguments("start-maximized");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GenericConfigs.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(GenericConfigs.PAGE_LOAD_TIMEOUT));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3000));

        js = (JavascriptExecutor) driver;
    }

    /*-------------------------------------------------------
    @Comment: Initialize WebDriver File using WebDriverManager
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
    ------------------------------------------------------- */
    public void initializeExplicitWebDriverWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GenericConfigs.WEBDRIVER_WAIT));
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


}
