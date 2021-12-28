package com.automation.develop.pages;

import com.automation.develop.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ConfirmationPage extends BaseClass {
    /**
     * -----------------------------------------------------------------------------------------------------------
     * Object Repository for ConfirmationPage elements contains WebElements and Actions methods.
     * control is passed to next page based on the action.
     * Maintainable solution for changing locators xpath
     * author Manish Rajurkar
     * Date 16/12/2021
     * ----------------------------------------------------------------------------------------------------------------
     */
    boolean status;

    @FindBy(xpath = "//h2[text()='PAYMENT SUCCESS']")
    @CacheLookup
    private static WebElement successHeader;

    @FindBy(xpath = "//p[@class='text-justify']")
    @CacheLookup
    private static WebElement successMessage;


    public ConfirmationPage() {
        PageFactory.initElements(driver, this);
        System.out.println("Inside Confirmation page");
    }

    public static String paymentIsSuccess() throws InterruptedException {
        //Thread.sleep(3000);

        try {wait.until(ExpectedConditions.visibilityOf(successHeader));

        }catch (Exception e){
          e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()='PAYMENT SUCCESS']")));
        return successHeader.getText();

    }
}
