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

import java.time.Duration;

import static org.testng.Assert.assertEquals;


public class ConfirmationPage extends BaseClass {
    public WebDriver ldriver;

    /**
     * -----------------------------------------------------------------------------------------------------------
     * Object Repository for ConfirmationPage elements contains WebElements and Actions methods.
     * control is passed to next page based on the action.
     * Maintainable solution for changing locators xpath
     * author Manish Rajurkar
     * Date 16/12/2021
     * ----------------------------------------------------------------------------------------------------------------
     */

    public ConfirmationPage(WebDriver driver) {
        ldriver =driver;
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

    //



    public String paymentIsSuccessorFailed() throws InterruptedException {
         //Thread.sleep(7000);

//        for (int i=0;i<10;i++){
//            String status = (String) js.executeScript("return document.readyState");
//            if (status.equals("completed")) {
                wait.until(ExpectedConditions.visibilityOfAllElements(successHeader));
//                break;
//            }
//        }
        // wait.until(driver -> driver.findElement(By.xpath("//h2[text()='PAYMENT SUCCESS']")));
        //assertEquals(foo.getText(), "PAYMENT SUCCESS");

        try {
            if (successHeader.isDisplayed()){

                logger.info("Payment is Success");
            }
            else if(failureHeader.isDisplayed()){
                logger.info("Payment is Failed");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return successHeader.getText();

    }
}
