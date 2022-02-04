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
        PageFactory.initElements(ldriver, this);
    }


    boolean status;

    @FindBy(xpath = "//h2[text()='PAYMENT SUCCESS']")
    @CacheLookup
    private static WebElement successHeaderMessage;

    @FindBy(xpath = "//h2")
    @CacheLookup
    private static WebElement HeaderMessageSection;

    @FindBy(xpath = "h2['PAYMENT FAILED']")
    @CacheLookup
    private static WebElement failureHeaderMessage;

    @FindBy(xpath = "//p[@class='text-justify']")
    @CacheLookup
    private static WebElement successMessage;

    String url = "https://weathershopper.pythonanywhere.com/confirmation";

    public String paymentIsSuccessorFailed() {
        WaitForTitle("Confirmation",lwait);
        String message = null;
        checkJsStatus(ldriver);




        try {
            lwait.until(ExpectedConditions.visibilityOfAllElements(HeaderMessageSection));
            message = HeaderMessageSection.getText();
           logger.info("___>>>>>>_____"+message);

            if (message == "PAYMENT SUCCESS") {
                successHeaderMessage.isDisplayed();
                logger.info("Payment is Success");
            } else if (message == "PAYMENT FAILED ") {
                failureHeaderMessage.isDisplayed();
                logger.info("Payment is Failed");
            }
        } catch (Exception e) {
            logger.error("Failure Header not found" + e);
        }
        return message;
    }

}
