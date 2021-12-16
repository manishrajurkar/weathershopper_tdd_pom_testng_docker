package com.automation.develop.pages;

import com.automation.develop.utilities.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SunscreensPage extends BaseClass {

    /**
     * -----------------------------------------------------------------------------------------------------------
     * Object Repository for SunscreensPage UI elements contains WebElements and Actions methods.
     * control is passed to next page based on the action.
     * Maintainable solution for changing locators xpath
     * author Manish Rajurkar
     * Date 14/12/2021
     * ----------------------------------------------------------------------------------------------------------------
     */

// Page Web elements locators (Object Repository)

    List<String> newListPrice = new ArrayList<String>();
    List<Integer> newIntListPrice = new ArrayList<Integer>();
    public static String[] arrayOfPrice;


    @FindBy(xpath = "//span[@id ='cart']")
    @CacheLookup
    private WebElement cartButton;

    public void SunscreensPage() {
        PageFactory.initElements(driver, this);

    }

    public BaseClass addProducts() throws InterruptedException {
        findTheLeastExpensiveProductAndAddToCart("SPF-50");
        findTheLeastExpensiveProductAndAddToCart("SPF-30");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@id ='cart']")).click();
        System.out.println("CART BUTTON CLICKED");
        // clickOnCartButton();
        //cart_button.click();
        //js.executeScript("document.getElementById('cart').click;");
        return new CartPage().enterPaymentDetails();
    }

    public void findTheLeastExpensiveProductAndAddToCart(String productShortName) {
        List<WebElement> sunscreenPrices = driver.findElements(By.xpath("//p[contains(text(),'" + productShortName + "')]/following-sibling::p"));
        System.out.println("Found " + sunscreenPrices.size() + " Products");
        //iterate the found products and add it to the arraylist
        for (WebElement sunscreenPrice : sunscreenPrices) {
            String mos = sunscreenPrice.getText();
            arrayOfPrice = mos.split(". ");
            newListPrice.add(arrayOfPrice[arrayOfPrice.length - 1]);
            System.out.println(newListPrice);
        }
        // List converted to Integer arrayList
        newIntListPrice = newListPrice.stream().map(Integer::parseInt).collect(Collectors.toList());
        //extracted minimum value
        int min = Collections.min(newIntListPrice);
        System.out.println("Cheapest Product Price Is ----->>>>" + min);
        //Prepared dynamic xpath for the product which needs to be added to the cart
        driver.findElement(By.xpath("//p[contains(text(),'" + productShortName + "')]/..//p[contains(text(),'" + min + "')]/following::button[1]")).click();
        newListPrice.clear();
        newIntListPrice.clear();
    }

    public CartPage clickOnCartButton() {
        cartButton.click();
        return new CartPage();
    }


}
