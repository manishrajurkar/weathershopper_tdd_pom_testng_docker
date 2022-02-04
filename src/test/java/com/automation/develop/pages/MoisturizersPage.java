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

public class MoisturizersPage extends BaseClass {
    private final WebDriver ldriver;
    private final WebDriverWait lwait;

    /**
     * -----------------------------------------------------------------------------------------------------------
     * Object Repository for MoisturizersPage UI elements contains WebElements and Actions methods.
     * control is passed to next page based on the action.
     * Maintainable solution for changing locators xpath
     * author Manish Rajurkar
     * Date 14/12/2021
     * ----------------------------------------------------------------------------------------------------------------
     */

    public MoisturizersPage(WebDriver driver, WebDriverWait wait) {
        ldriver=driver;
        lwait=wait;
        PageFactory.initElements(ldriver, this);

    }

    // Page Web elements locators (Object Repository)

    List<String> newListPrice = new ArrayList<String>();
    List<Integer> newIntListPrice = new ArrayList<Integer>();
    public static String[] arrayOfPrice;

    @FindBy(xpath = "//p[contains(text(),'SPF-50')]/following-sibling::p")
    @CacheLookup
    private static List<WebElement> list_moisturisers;

    @FindBy(xpath = "//span[@id ='cart']")
    @CacheLookup
    private WebElement cartButton;

    @FindBy(xpath = "//span[@id ='cart'] [text()='Empty']")
    @CacheLookup
    private WebElement cartEmpty;

    @FindBy(xpath = "//span[@id ='cart'] [text()='1 item(s)']")
    @CacheLookup
    private WebElement cartWitSingleItem;

    @FindBy(xpath = "//span[@id ='cart'] [text()='2 item(s)']")
    @CacheLookup
    private WebElement cartWitTwoItem;


    public BaseClass addProducts() throws InterruptedException {
        findTheLeastExpensiveProductAndAddToCart("Aloe");
        findTheLeastExpensiveProductAndAddToCart("Almond");
        lwait.until(ExpectedConditions.visibilityOf(cartButton));
        cartButton.click();
        logger.info("Navigating to Cart page");
        return new CartPage(ldriver,lwait);//.enterPaymentDetails();

    }

    public void findTheLeastExpensiveProductAndAddToCart(String productShortName) {
        List<WebElement> moisturizerPrices = ldriver.findElements(By.xpath("//p[contains(text(),'" + productShortName + "')]/following-sibling::p"));
        logger.info("Found " + moisturizerPrices.size() + " Products matching name " +productShortName );
        //iterate the found products and add it to the arraylist
        for (int i = 0; i < moisturizerPrices.size(); i++) {
            String moisturizers = moisturizerPrices.get(i).getText();
            arrayOfPrice = moisturizers.split(". ");
            newListPrice.add(arrayOfPrice[arrayOfPrice.length - 1]);
        }
        logger.info("Price List of all the matching Moisturizers " +newListPrice);
        // List converted to Integer arrayList
        newIntListPrice = newListPrice.stream().map(Integer::parseInt).collect(Collectors.toList());
        //extracted minimum value
        int min = Collections.min(newIntListPrice);
        logger.info("Cheapest among " +moisturizerPrices.size()+ " Found Sunscreen Is ----->>>>" + min);
        //Prepared dynamic xpath for the product which needs to be added to the cart
        ldriver.findElement(By.xpath("//p[contains(text(),'" + productShortName + "')]/..//p[contains(text(),'" + min + "')]/following::button[1]")).click();
        newListPrice.clear();
        newIntListPrice.clear();
    }


    public boolean verifyCartIsEmpty() {
        return cartEmpty.isDisplayed();
    }

}


