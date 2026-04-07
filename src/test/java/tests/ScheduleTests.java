package tests;


import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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


        System.out.println("TC10 - Confirm password different");


        HomePage homePage = new HomePage();
        homePage.open();


        RegisterPage registerPage = homePage.gotoRegisterPage();


        registerPage.register(
                "test" + System.currentTimeMillis() + "@gmail.com",
                "123456789",
                "987654321",
                "123456789"
        );


        String actualMessage = registerPage.getRegisterErrorMessage();
        String expectedMessage =
                "There're errors in the form. Please correct the errors and try again.";


        Assert.assertEquals(actualMessage, expectedMessage);
    }


    @Test
    public void TC11() {


        System.out.println("TC11 - Empty password and PID");


        HomePage homePage = new HomePage();
        homePage.open();


        RegisterPage registerPage = homePage.gotoRegisterPage();


        registerPage.register(
                "test" + System.currentTimeMillis() + "@gmail.com",
                "",
                "",
                ""
        );


        String actualFormMessage = registerPage.getRegisterErrorMessage();
        String actualPasswordMessage = registerPage.getPasswordErrorMessage();
        String actualPIDMessage = registerPage.getPIDErrorMessage();


        Assert.assertEquals(actualFormMessage,
                "There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(actualPasswordMessage,
                "Invalid password length.");
        Assert.assertEquals(actualPIDMessage,
                "Invalid ID length.");
    }
}



