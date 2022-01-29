package com.automation.test;

import com.automation.develop.pages.*;
import com.automation.develop.base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.*;


/**
 *
 */

public class TC001_ShopForSpecificSeasonalProductTest extends BaseClass {
    HomePage homePage;
    ConfirmationPage confirmationPage;
    CartPage cartPage;


    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browser) throws Exception {
        //initializeBrowserUsingDriverManager(browser, false);
        initializeRemoteWebDriver(browser, false);
        initializeExplicitWebDriverWait(driver);

        homePage = new HomePage(driver,wait);
        confirmationPage = new ConfirmationPage(driver,wait);
        cartPage = new CartPage(driver,wait);

    }

    @Test(priority = 0, groups = {"group1"})
    public void shopForSeasonalProducts() throws InterruptedException {
        ExtentTest test = extentReports.createTest("E2EFunctional").assignAuthor("Manish")
                .assignCategory("WebAutomation");
        test.info("Test execution started");
        homePage.opeUrl(confProp.getProperty("url"));
        test.info("Application URL open");
        homePage.navigateToSeasonalProductCatalog();
        softAssert.assertEquals(getURL(),"https://weathershopper.pythonanywhere.com/cart");
        softAssert.assertEquals(confirmationPage.paymentIsSuccessorFailed(),"PAYMENT SUCCESS");
        test.info("Payment is successful");
        softAssert.assertEquals(getURL(),"https://weathershopper.pythonanywhere.com/confirmation");
        test.info("Test Case Completed");

    }

    @AfterTest
    public void teardown() {
        softAssert.assertAll();
        driver.close();
        driver.quit();
    }


}
