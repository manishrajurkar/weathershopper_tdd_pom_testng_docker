package com.automation.test;

import com.automation.develop.pages.*;
import com.automation.develop.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC001_ShopForSpecificSeasonalProductTest extends BaseClass {
    HomePage homePage;
    ConfirmationPage confirmationPage;
    CartPage cartPage;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName) {
        log4j();
        initializePropertiesFile();
        initializeBrowserUsingDriverManager(browserName, false);
        initializeExplicitWebDriverWait();

        homePage = new HomePage();
        confirmationPage = new ConfirmationPage();
    }

    @Test(priority = 0)
    public void shopForSeasonalProducts() throws InterruptedException {
//        homePage.opeUrl(confProp.getProperty("url"));
        logger.info("One Step complete");
//        homePage.navigateToSeasonalProductCatalog();
//        System.out.println(getURL());
       logger.info("Second Step complete");
//        //System.out.println(CartPage.getTheTotalCartValue());
//        Assert.assertEquals(confirmationPage.paymentIsSuccess(),"PAYMENT SUCCESS");
        System.out.println(getURL());

        // Assert user is on Moisturiser page or Sunscreen page
        // verify initially cart is empty
        // Verify there are two products shown on the cart button
        // Verify Checkout cart page has two rows
        // verify added products contains Aloe and Almond or SPF-50 and SPF-30
        // Print the Total Cart value.
    }

    @AfterTest
    public void teardown() {
        driver.close();
        //driver.quit();
    }


}
