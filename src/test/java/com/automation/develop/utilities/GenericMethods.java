package com.automation.develop.utilities;

import com.automation.develop.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class GenericMethods extends BaseClass {
    public WebDriver driver;


    public static void enterCardNumbers(String num, WebElement element) {

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);

        }
    }

    public void switchTOFrame( WebDriver driver,int index){
        //Using Frame Index
        this.driver=driver;
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        int size= elements.size();
        driver.switchTo().frame(0);
        logger.info("Navigated to frame" +index);



    }
    public static void clickOnElement(){

    }
    public static void selectElement(){

    }
    public static void selectRadioButton(){
        //

    }
    public static void selectCheckBox(){

    }
    public static void select(){

    }

}
