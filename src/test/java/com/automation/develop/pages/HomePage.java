package com.automation.develop.pages;

import com.automation.develop.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass {
    String season = "No season";
    String temperature_string;

    /**
     * -----------------------------------------------------------------------------------------------------------
     * Object Repository for HomePage UI elements contains WebElements and Actions methods.
     * control is passed to next page based on the action.
     * Maintainable solution for changing locators xpath
     * author Manish Rajurkar
     * Date 14/12/2021
     * ----------------------------------------------------------------------------------------------------------------
     */

// Page Web elements locators (Object Repository)

    @FindBy(xpath = "//span[@id ='temperature']")
    @CacheLookup
    WebElement current_temperature;

    @FindBy(xpath = "//button[text()='Buy moisturizers']")
    @CacheLookup
    private WebElement buy_moisturiserButton;

    @FindBy(xpath = "//button[text()='Buy sunscreens']")
    @CacheLookup
    private WebElement buy_sunscreensButton;

    // constructor will initialize the page using PageFactory
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // Action Methods
    /*-------------------------------------------------------
    @Comment: Based on the Temperature choose weather to buy Moisturisers or Sunscreen
    @Author : Manish Rajurkar
    @Date   : 15.12.2021
    ------------------------------------------------------- */
//    public BaseClass navigateToSeasonalProductCatalog() throws InterruptedException {
//
//        String temperature_string = current_temperature.getText();
//        String[] temperature_array = temperature_string.split(" ");
//        int temperature_value = Integer.parseInt(temperature_array[0]);
//
//        if (temperature_value < 19) {
//            season = "WINTER";
//            System.out.println("It's " + season + " Season here & Today's Temperature is " + temperature_string + " Opening page to Buy Moisturiser");
//            buy_moisturiserButton.click();
//            //driver.findElement(By.xpath("//button[text()='Buy moisturizers']")).click();
//            return new MoisturizersPage().addProducts();
//
//        } else if (temperature_value > 34) {
//            season = "SUMMER";
//            System.out.println("It's " + season + " Season here & Today's Temperature is " + temperature_string + " Opening page  to Buy Sunscreens");
//            buy_sunscreensButton.click();
//            //driver.findElement(By.xpath("//button[text()='Buy sunscreens']")).click();
//            return new SunscreensPage().addProducts();
//        } else {
//
//            return null;
//        }
//    }
    public void navigateToSeasonalProductCatalog() throws InterruptedException {

    temperature_string = current_temperature.getText();
    String[] temperature_array = temperature_string.split(" ");
    int temperature_value = Integer.parseInt(temperature_array[0]);

    if (temperature_value < 19) {
        season = "WINTER";
        navigateToMoisturiserPage();
    } else if (temperature_value > 34) {
        season = "SUMMER";
        navigateToSunscreenPage();
    } else {
        System.out.println("Something is wrong");
    }
}

    public BaseClass navigateToMoisturiserPage() throws InterruptedException {

        System.out.println("It's " + season + " Season here & Today's Temperature is " + temperature_string + " Opening page to Buy Moisturiser");
        buy_moisturiserButton.click();
        return new MoisturizersPage().addProducts();
    }

    public BaseClass navigateToSunscreenPage() throws InterruptedException {

        System.out.println("It's " + season + " Season here & Today's Temperature is " + temperature_string + " Opening page  to Buy Sunscreens");
        buy_sunscreensButton.click();
        return new SunscreensPage().addProducts();
    }
    /*-------------------------------------------------------
    @Comment: Open Given http url
    @developer : Manish Rajurkar
    @Date   : 15.12.2021
    ------------------------------------------------------- */
    public void opeUrl(String url) {
        try {
            if (url != null) {
                System.out.println("Opening application URL  " + url);
                driver. get(url);
            } else {
                System.out.println("Entered URL is null");
            }
        } catch (Exception e) {
            System.out.println("Please check the URL");
            e.printStackTrace();
        }
    }
}
