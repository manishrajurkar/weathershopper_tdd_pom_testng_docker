package com.automation.develop.practice;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webDriverManagerpath {
    public static void main(String[] args) {

        WebDriverManager.chromedriver()
                .cachePath("C:\\ManishMain\\Automation\\weathershopper\\weathershopper\\src\\test\\resources\\Executables")
                .setup();

        WebDriverManager.firefoxdriver()
                .cachePath("C:\\ManishMain\\Automation\\weathershopper\\weathershopper\\src\\test\\resources\\Executables")
                .setup();

        WebDriverManager.edgedriver()
                .cachePath("C:\\ManishMain\\Automation\\weathershopper\\weathershopper\\src\\test\\resources\\Executables")
                .setup();
    }

}


