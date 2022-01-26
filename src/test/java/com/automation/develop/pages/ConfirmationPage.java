package com.automation.develop.pages;
import com.automation.develop.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage extends BaseClass {
    public WebDriver ldriver;
    public WebDriverWait lwait;

    /**
     * -----------------------------------------------------------------------------------------------------------
     * Object Repository for ConfirmationPage elements contains WebElements and Actions methods.
     * control is passed to next page based on the action.
     * Maintainable solution for changing locators xpath
     * author Manish Rajurkar
     * Date 16/12/2021
     * ----------------------------------------------------------------------------------------------------------------
     */

    public ConfirmationPage(WebDriver driver, WebDriverWait wait) {
        ldriver = driver;
        lwait = wait;
        PageFactory.initElements(driver, this);
    }


    boolean status;

    @FindBy(xpath = "//h2[text()='PAYMENT SUCCESS']")
    @CacheLookup
    private static WebElement successHeader;

    @FindBy(xpath = "h2['PAYMENT FAILED']")
    @CacheLookup
    private static WebElement failureHeader;

    @FindBy(xpath = "//p[@class='text-justify']")
    @CacheLookup
    private static WebElement successMessage;


    public String paymentIsSuccessorFailed()  {

        lwait.until(ExpectedConditions.visibilityOfAllElements(successHeader));
        String value = null;
        try {
            if (successHeader.isDisplayed()){
                logger.info("Payment is Success");
               value = successHeader.getText();
            }
            else if(failureHeader.isDisplayed()){
                logger.info("Payment is Failed");
                value = failureHeader.getText();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
