package com.automation.develop.pages;

import com.automation.develop.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    WebDriver ldriver;
    WebDriverWait lwait;


    public SunscreensPage(WebDriver driver,WebDriverWait wait) {
        ldriver=driver;
        lwait=wait;
        PageFactory.initElements(driver, this);


    }

// Page Web elements locators (Object Repository)

    List<String> newListPrice = new ArrayList<String>();
    List<Integer> newIntListPrice = new ArrayList<Integer>();
    public static String[] arrayOfPrice;


    @FindBy(xpath = "//span[@id ='cart']")
    @CacheLookup
    private WebElement cartButton;



    public BaseClass addProducts() throws InterruptedException {
        findTheLeastExpensiveProductAndAddToCart("SPF-50");
        findTheLeastExpensiveProductAndAddToCart("SPF-30");
        //Thread.sleep(1000);

        lwait.until(ExpectedConditions.visibilityOf(cartButton));

        //Worked
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@id ='cart']")));
        cartButton.click();
        logger.info("CART BUTTON CLICKED");
        //js.executeScript("document.getElementById('cart').click;");
        return new CartPage(ldriver,lwait).enterPaymentDetails();
    }

    public void findTheLeastExpensiveProductAndAddToCart(String productShortName) {
        List<WebElement> sunscreenPrices = ldriver.findElements(By.xpath("//p[contains(text(),'" + productShortName + "')]/following-sibling::p"));
        logger.info("Found " + sunscreenPrices.size() + " Products matching name " +productShortName );

        //iterate the found products and add it to the arraylist
        for (WebElement sunscreenPrice : sunscreenPrices) {
            String mos = sunscreenPrice.getText();
            arrayOfPrice = mos.split(". ");
            newListPrice.add(arrayOfPrice[arrayOfPrice.length - 1]);
        }
        logger.info("Price List of all the matching Sunscreen " +newListPrice);
        // List converted to Integer arrayList
        newIntListPrice = newListPrice.stream().map(Integer::parseInt).collect(Collectors.toList());
        //extracted minimum value
        int min = Collections.min(newIntListPrice);
        logger.info("Cheapest among " +sunscreenPrices.size()+ " Found Sunscreen Is ----->>>>" + min);
        //Prepared dynamic xpath for the product which needs to be added to the cart
        ldriver.findElement(By.xpath("//p[contains(text(),'" + productShortName + "')]/..//p[contains(text(),'" + min + "')]/following::button[1]")).click();
        newListPrice.clear();
        newIntListPrice.clear();
    }

}
