package com.automation.develop.utilities;

import com.automation.develop.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class Listeners extends BaseClass implements WebDriverListener {


    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Before navigating to: '" + url + "'");
    }

    public void beforeGetWindowHandle(WebDriver driver) {
        logger.info("Before WindowHandle to: ");

    }

    public void afterGetWindowHandle(WebDriver driver, String result) {
       logger.info("AFterGetWindow Handle");

    }
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigated to:'" + url + "'");
    }


}
