package com.automation.develop.pages;

import com.automation.develop.base.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseClass {
    WebDriver ldriver ;
    WebDriverWait lwait;

    /**
     * -----------------------------------------------------------------------------------------------------------
     * Object Repository for CART page UI elements
     * This Page class will identify the WebElements of the web page and also contains Page methods which perform operations on those WebElements.
     * control is passed to next page based on the action.
     * Maintainable solution for changing locators xpath
     * <p>
     * author Manish Rajurkar
     * Date 15/12/2021
     * ----------------------------------------------------------------------------------------------------------------
     */

    //  constructor which Initializing the page using Page Factory
    public CartPage(WebDriver driver,WebDriverWait wait) {

        ldriver=driver;
        lwait=wait;
        PageFactory.initElements(driver, this);

    }

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
        String[] listOfValues = totalAmount.getText().split(" "); // "Total: Rupees 344" &&// "Total","Rupees" ,"344"  [0,1,2]
        List<String> splitValues = new ArrayList<>();
        splitValues.add(listOfValues[listOfValues.length - 1]); //"344" [3-1 =2]
        int cartValue = Integer.parseInt(splitValues.get(0)); //it has only single value at index 0
        logger.info("Your Total Shopping cart values is " + cartValue);
        return cartValue;
    }
    public BaseClass enterPaymentDetails() throws InterruptedException {

        try {
            payWithCardLink.click();
            genericMethods.switchTOFrame(ldriver,0);
            lwait.until(ExpectedConditions.visibilityOfAllElements(email));
            genericMethods.enterCardNumbers(confProp.getProperty("email"),email);
            genericMethods.enterCardNumbers(confProp.getProperty("card_number"), cardNumber);
            genericMethods.enterCardNumbers(confProp.getProperty("card_expiry"), cardExpiry);
            genericMethods.enterCardNumbers(confProp.getProperty("card_cvc"), cardCVC);
            genericMethods.enterCardNumbers(confProp.getProperty("zip_code"), zipCode);
            payLink.click();
            logger.info("Payment Details entered and clicked on pay button");
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return new ConfirmationPage(ldriver,lwait);
    }


}