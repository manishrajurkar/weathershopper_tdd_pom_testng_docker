package com.automation.develop.utilities;

import com.automation.develop.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class GenericMethods extends BaseClass {
    public  WebDriver ldriver;
    public  WebDriverWait lwait;



    public static void enterCardNumbers(String num, WebElement element) {

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
        }
    }

//    public void waitForURL(String url, WebDriver driver) {
//        //String capturedUrl= driver.getCurrentUrl();
//
//        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().equals(url);
//        wait.until(urlIsCorrect);
//        waitForPageLoadComplete(driver, GenericConfigs.WEBDRIVER_WAIT);
//
//    }
//    public void waitForUrl(String url, WebDriver driver, Duration duration) {
//        waitForUrl(url, driver, Duration.ofMinutes(GenericConfigs.WEBDRIVER_WAIT));
//    }
//    public void waitForWebElement(WebDriver driver, WebElement element) {
//        boolean flag;
//        try {
//            wait.until(ExpectedConditions.visibilityOf(element));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        flag = true;
//    }


    public void switchTOFrame(WebDriver driver, int index) {
        //Using Frame Index
        this.ldriver = driver;
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        int size = elements.size();
        driver.switchTo().frame(0);
        logger.info("Navigated to frame" + index);
    }

    public static void clickOnElement() {

    }

    public static void selectElement() {

    }

    public static void selectRadioButton() {
        //

    }

    public static void selectCheckBox() {

    }

    public static void select() {

    }

}
