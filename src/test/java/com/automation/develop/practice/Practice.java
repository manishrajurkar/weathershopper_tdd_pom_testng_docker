package com.automation.develop.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {
    public static WebDriver driver;
    public static String[] arrayofPrice;


    public static void main(String[] args) throws InterruptedException {
        List<String> newListPrice = new ArrayList<String>();
        List<Integer> newIntListPrice = new ArrayList<Integer>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://weathershopper.pythonanywhere.com/");
        Thread.sleep(2000);

        WebElement temprature = driver.findElement(By.xpath("//span[@id ='temperature']"));
        String todaysTemprature = temprature.getText();

        String[] temparray = todaysTemprature.split(" ");
        int extractedtemp = Integer.parseInt(temparray[0]);
        // int extractedtemp = Integer.parseInt(String.valueOf(todaysTemprature.split(" ")));
        System.out.println(extractedtemp); //39
        if (extractedtemp < 19) {
            System.out.println("Buy Moisturizer");
            driver.findElement(By.xpath("//button[text()='Buy moisturizers']")).click();
            Thread.sleep(1000);
            List<WebElement> moisturiserPrices = driver.findElements(By.xpath("//p[contains(text(),'Aloe')]/following-sibling::p"));
            moisturiserPrices.size();
            //moisturiserPrices.get(0);
            String value = (moisturiserPrices.get(0).getText());
            System.out.println("VAlue is" + value);
            for (int i = 0; i < moisturiserPrices.size(); i++) {
                String mos = moisturiserPrices.get(i).getText();
                arrayofPrice = mos.split(". ");
                newListPrice.add(arrayofPrice[arrayofPrice.length - 1]);
                System.out.println(newListPrice);
            }
            // List converted to Integer
            newIntListPrice = newListPrice.stream().map(Integer::parseInt).collect(Collectors.toList());
            //extracted minimum value
            int min = Collections.min(newIntListPrice);
            System.out.println("Chepest Product Price Is ----->>>>" + min);


        } else if (extractedtemp > 34) {

            System.out.println("Buy Sunscreen");
            driver.findElement(By.xpath("//button[text()='Buy sunscreens']")).click();
            Thread.sleep(1000);
            List<WebElement> sunscreemPrices = driver.findElements(By.xpath("//p[contains(text(),'SPF-50')]/following-sibling::p"));
            sunscreemPrices.size();
            //String value = (sunscreemPrices.get(0).getText());
            // System.out.println("VAlue is" +value);
            for (int i = 0; i < sunscreemPrices.size(); i++) {
                String mos = sunscreemPrices.get(i).getText();
                arrayofPrice = mos.split(". ");
                newListPrice.add(arrayofPrice[arrayofPrice.length - 1]);
                System.out.println(newListPrice);
            }
            // List converted to Integer
            newIntListPrice = newListPrice.stream().map(Integer::parseInt).collect(Collectors.toList());
            //extracted minimum value from the integer Array list
            int min = Collections.min(newIntListPrice);
            System.out.println("MINUMUM VALUE IS ----->>>>" + min);

        } else {
            System.out.println("Invalid Temp");
        }
        driver.close();
        driver.quit();


    }
}
