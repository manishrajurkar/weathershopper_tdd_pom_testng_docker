package com.automation.develop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Wait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


/**----------------------------------------------------------------------------------------------------
 * Write all the common methods in the BaseClass which will be used in the test cases
 * Ex. Initiate browser driver, property files, logger file.
 * Extend this base class into test class to use the static methods.
 * -----------------------------------------------------------------------------------------------------*/

public class BaseClass implements RulesForBaseClass {
    public static WebDriver driver ;
    public static Properties confProp ;
    public static FileInputStream fis;
    public static Wait wait ;
    public static JavascriptExecutor js ;

    /*-------------------------------------------------------
    @Comment: Intialize Property File
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
    @Purpose : It will initialize property and keep it ready for test to read the required data.
    ------------------------------------------------------- */

    @Override
    public void initializePropertiesFile()  {
        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\configuration.properties");
                confProp = new Properties();
                confProp.load(fis);
                System.out.println("Property file loaded successfully");
            } catch (Exception e){
                System.out.println("Could Not find the file at the location:" );
                e.printStackTrace();
            }
        }else {
            System.out.println("Property File Already in use");
        }
    }

    /*-------------------------------------------------------
    @Comment: Initialize WebDriver File using WebDriverManager
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
    @Purpose : Based on the input to the methods different browser driver (Chrome, Firefox & Edge)
               can be initialized.Also, headless mode can be activated
    ------------------------------------------------------- */
    @Override
    public void initializeBrowserUsingDriverManager(String browser, boolean headless) {
         if(browser.equalsIgnoreCase("chrome")){
             ChromeOptions chromeoptions = new ChromeOptions();
             chromeoptions.addArguments("start-maximized");
             //set the option to start the browser in headless mode.
               if (headless == true){
                 chromeoptions.addArguments("headless");
                 System.out.println("Chrome browser will be run in Headless mode");
               }
             // Browser Driver initiated with WebDriver Manager . Thanks to @BoniGarcia :)
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver(chromeoptions);
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
             js = (JavascriptExecutor)driver;
             System.out.println("Test will run on Chrome Browser ");
         }
         if(browser.equalsIgnoreCase("firefox")){
             FirefoxOptions firefoxOptions = new FirefoxOptions();
             firefoxOptions.addArguments("start-maximized");
             if (headless == true) {
                 firefoxOptions.addArguments("headless");
                 System.out.println("Firefox Browser will run in Headless mode");
             }
             WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver(firefoxOptions);
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
             js = (JavascriptExecutor)driver;
             System.out.println("Test will run on Firefox Browser ");
         }
         if (browser.equalsIgnoreCase("edge")){
             EdgeOptions edgeOptions = new EdgeOptions();
             edgeOptions.addArguments("start-maximized");
             if(headless == true){
                 edgeOptions.addArguments("headless");
                 System.out.println("Edge Browser will run in Headless mode");
             }
             WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver(edgeOptions);
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
             js = (JavascriptExecutor)driver;
             System.out.println("Test will run on Edge Browser");
          }
    }
    
     /*-------------------------------------------------------
    @Comment: Explicit wait.
    @developer : Manish Rajurkar
    @Date   : 26.12.2021
    ------------------------------------------------------- */

    public void initializeExplicitWebDriverWait (){
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));

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
