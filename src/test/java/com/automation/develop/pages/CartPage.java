package com.automation.develop.pages;

import com.automation.develop.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseClass {

    /**
     * -----------------------------------------------------------------------------------------------------------
     * Object Repository for CART page UI elements
     * This Page class will identify the WebElements of the web page and also contains Page methods which perform operations on those WebElements.
     * control is passed to next page based on the action.
     * Maintainable solution for changing locators xpath
     *
     * author Manish Rajurkar
     * Date 15/12/2021
     * ----------------------------------------------------------------------------------------------------------------
     */

// Page Web elements locators (Object Repository)

    @FindBy(xpath = "//h2[text()='Checkout']")
    @CacheLookup
    WebElement checkOutHeader;

    @FindBy(xpath = "//span[text()='Pay with Card']")
    @CacheLookup
    WebElement payWithCardLink;

    @FindBy(css = "p[id='total']")
    @CacheLookup

    private static WebElement totalAmount;

    @FindBy(xpath = "//h1[text()='Stripe.com']")
    @CacheLookup
    private WebElement stripeHeader;

    //@FindBy(css = "input[id='email']")
    @FindBy(xpath = "//input[@id='email']")
    @CacheLookup
    private WebElement email;

    @FindBy(css = "input[id='card_number']")
    @CacheLookup
    private WebElement cardNumber;

    @FindBy(css = "input[id='cc-exp']")
    @CacheLookup
    private WebElement cardExpiry;

    @FindBy(css = "input[id='cc-csc']")
    @CacheLookup
    private WebElement cardCVC;

    @FindBy(xpath = "//input[@x-autocompletetype='postal-code']")
    @CacheLookup
    private WebElement zipCode;

    @FindBy(css = "span[class='iconTick']")
    @CacheLookup
    private WebElement payLink;


    @FindBy(css = "a[class='close right']")
    @CacheLookup
    private WebElement closeButton;

    @FindBy(xpath = "//table[@class='table table-striped']//tr")
    @CacheLookup
    private List<WebElement> tableRow;

    //  constructor which Initializing the page using Page Factory
    public CartPage() {
        PageFactory.initElements(driver, this);
        System.out.println("In the Cart Page");
    }

    //Action methods

    public void clickPayWithCard() {
        payWithCardLink.click();
    }

    public void clickPayLink() {
        payLink.click();
    }

    public void closeStripePopUpPage() {
        closeButton.click();
    }

    public boolean verifyCheckOutHeading() {
        return checkOutHeader.isDisplayed();

    }

    public boolean verifyStripePopUpPageOpen() {
        //WebElement stripeHeader_com = driver.findElement(By.xpath("stripeHeader"));
        return stripeHeader.isDisplayed();

    }

    public Integer verifyNumberOfItemsInTheCart() {
        int rowCount = tableRow.size();
        return rowCount - 1;
    }

    public static int getTheTotalCartValue() {
        //https://stackoverflow.com/questions/18838781/converting-string-array-to-an-integer-array
        String amount = totalAmount.getText();  // Total: Rupees 344
        String[] listOfValues = amount.split(" "); // "Total","Rupees" ,"344"  [0,1,2]
        List<String> splitValues = new ArrayList<>();
        splitValues.add(listOfValues[listOfValues.length - 1]); //"344" [3-1 =2]
        int cartValue = Integer.parseInt(splitValues.get(0)); //it has only single value at index 0
        System.out.println("Your Total Shopping cart values is " + cartValue);
        return cartValue;
    }

    public BaseClass enterPaymentDetails() throws InterruptedException {
        payWithCardLink.click();
        driver.switchTo().frame(0);
        email.click();
        email.sendKeys(confProp.getProperty("email"),Keys.TAB);
        cardNumber.sendKeys(Keys.NUMPAD4, Keys.NUMPAD2, Keys.NUMPAD4, Keys.NUMPAD2);
        cardNumber.sendKeys(Keys.NUMPAD4, Keys.NUMPAD2, Keys.NUMPAD4, Keys.NUMPAD2);
        cardNumber.sendKeys(Keys.NUMPAD4, Keys.NUMPAD2, Keys.NUMPAD4, Keys.NUMPAD2);
        cardNumber.sendKeys(Keys.NUMPAD4, Keys.NUMPAD2, Keys.NUMPAD4, Keys.NUMPAD2);
        cardExpiry.sendKeys(confProp.getProperty("card_expiry1"));
        cardExpiry.sendKeys(confProp.getProperty("card_expiry2"));
        System.out.println("Expiry entered");
        cardCVC.sendKeys(confProp.getProperty("card_cvc"));
        System.out.println("cvv entered");
        zipCode.sendKeys(confProp.getProperty("zip_code"));
        System.out.println("Zip Code entered");
        payLink.click();
        System.out.println("Clicked on pay button");
       // Thread.sleep(4000);
        return new ConfirmationPage();


    }


}