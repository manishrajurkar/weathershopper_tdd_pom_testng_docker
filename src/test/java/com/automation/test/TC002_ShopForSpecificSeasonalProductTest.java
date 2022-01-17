package com.automation.test;

import com.automation.develop.base.BaseClass;
import com.automation.develop.pages.CartPage;
import com.automation.develop.pages.ConfirmationPage;
import com.automation.develop.pages.HomePage;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.*;


public class TC002_ShopForSpecificSeasonalProductTest extends BaseClass {
    HomePage homePage;
    ConfirmationPage confirmationPage;
    CartPage cartPage;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName) throws Exception {
        log4j();
        extentReport();
        initializePropertiesFile();
        initializeBrowserUsingDriverManager(browserName, false);
        //initializeRemoteWebDriver(browserName, false);
        initializeExplicitWebDriverWait();

        homePage = new HomePage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }

    @Test(priority = 0)
    public void shopForSeasonalProducts() throws InterruptedException {
        ExtentTest test = extentReports.createTest("Test2").assignAuthor("Manish")
                .assignCategory("WebAutomation");
        test.info("Started execution");


        homePage.opeUrl(confProp.getProperty("url"));
        homePage.navigateToSeasonalProductCatalog();
        Assert.assertEquals(getURL(),"https://weathershopper.pythonanywhere.com/cart");
        Assert.assertEquals(confirmationPage.paymentIsSuccessorFailed(),"PAYMENT SUCCESS");
        System.out.println(getURL());
        test.pass("Test pass");

        // Assert user is on Moisturiser page or Sunscreen page
        // verify initially cart is empty
        // Verify there are two products shown on the cart button
        // Verify Checkout cart page has two rows
        // verify added products contains Aloe and Almond or SPF-50 and SPF-30
        // Print the Total Cart value.
    }

    @AfterMethod
    public void testdownfirst() {
        driver.close();
    }
    @AfterTest
    public void teardown() {
        driver.quit();
        extentReports.flush();
        //driver.quit();
    }


}
