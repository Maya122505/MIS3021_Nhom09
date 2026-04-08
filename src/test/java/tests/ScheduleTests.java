package tests;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.HomePage;
import pageobjects.RegisterPage;

public class ScheduleTests {
    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with different confirm password");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.register("test" + System.currentTimeMillis() + "@gmail.com", "12345678", "87654321", "12345678");
        String actualMessage = registerPage.getRegisterErrorMessage();
        String expectedMessage = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMessage.trim(), expectedMessage);
    }

    @Test
    public void TC11() {
        System.out.println("TC11 - User can't create account while password and PID fields are empty");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.register("test" + System.currentTimeMillis() + "@gmail.com", "", "", "");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(registerPage.getRegisterErrorMessage().trim(), "There're errors in the form. Please correct the errors and try again.");
        softAssert.assertEquals(registerPage.getPasswordErrorMessage().trim(), "Invalid password length.");
        softAssert.assertEquals(registerPage.getPIDErrorMessage().trim(), "Invalid ID length.");
        softAssert.assertAll();
    }
}