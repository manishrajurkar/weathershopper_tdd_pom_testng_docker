package com.automation.test;

import com.automation.develop.pages.ConfirmationPage;
import com.automation.develop.pages.HomePage;
import com.automation.develop.utilities.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ShopForSpecificSeasonalProductTest extends BaseClass {
    HomePage homePage;
    ConfirmationPage confirmationPage;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browser) {
        initializePropertiesFile();
        initializeBrowserUsingDriverManager(browser, false);
        homePage = new HomePage();
        confirmationPage = new ConfirmationPage();

    }

    @Test(priority = 0)
    public void shopForSeasonalProducts() throws InterruptedException {
        homePage.opeUrl(confProp.getProperty("url"));
        homePage.navigateToSeasonalProductCatalog();
        System.out.println(getURL());
        //System.out.println(CartPage.getTheTotalCartValue());
        Assert.assertEquals(confirmationPage.paymentIsSuccess(),"PAYMENT SUCCESS");

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
