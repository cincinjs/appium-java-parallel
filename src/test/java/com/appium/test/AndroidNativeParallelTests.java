package com.appium.test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidNativeParallelTests {
    public AndroidDriver<MobileElement> driver;

    @BeforeTest(alwaysRun = true)
    @Parameters({"deviceName_","udid_","platformVersion","url_"})
    public void setup(String deviceName_, String udid_, String platformVersion, String url_) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName",deviceName_);
        caps.setCapability("udid",udid_);
        caps.setCapability("platformVersion",platformVersion);
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity","com.android.calculator2.Calculator");

        driver = new AndroidDriver<MobileElement>(new URL("http://"+url_),caps);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterTest(alwaysRun = true)
    public void teardown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void basicTest1 () {

        //Click number 2
        driver.findElement(By.id("digit_2")).click();

        //Click plus (+)
        driver.findElement(By.id("op_add")).click();

        //Click number 4
        driver.findElement(By.id("digit_4")).click();

        //Click equal (=)
        driver.findElement(By.id("eq")).click();


        //locate the edit box of the calculator
        String results = driver.findElement(By.id("result")).getText();

        //Check the calculated value on the edit box
        assert results.equals("6"):"Actual value is : "+results+" did not match with expected value: 6";
    }

    @Test
    public void basicTest2 () {

        //Click number 2
        driver.findElement(By.id("digit_2")).click();

        //Click plus (+)
        driver.findElement(By.id("op_add")).click();

        //Click number 2
        driver.findElement(By.id("digit_2")).click();

        //Click equal (=)
        driver.findElement(By.id("eq")).click();


        //locate the edit box of the calculator
        String results = driver.findElement(By.id("result")).getText();

        //Check the calculated value on the edit box
        assert results.equals("4"):"Actual value is : "+results+" did not match with expected value: 4";
    }

}
