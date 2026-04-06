package tests;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.*;

public class AccountTests {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test
    public void TC06() {
        System.out.println("TC06 - Additional pages display once user logged in");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        // Sửa lại dòng này cho hoàn chỉnh:
        Assert.assertTrue(myTicketPage.isMyTicketTabActive(), "Lỗi: Tab My ticket không sáng màu xanh");

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        // Câu này của bạn thì đã đúng rồi:
        Assert.assertTrue(changePasswordPage.isChangePasswordTabActive(), "Lỗi: Tab Change password không sáng màu xanh");
    }

    @Test
    public void TC07() {


        System.out.println("TC07 - User can create new account");


        HomePage homePage = new HomePage();
        homePage.open();


        RegisterPage registerPage = homePage.gotoRegisterPage();


        registerPage.register(
                "test" + System.currentTimeMillis() + "@gmail.com",
                "123456789",
                "123456789",
                "123456789"
        );


        String actualMessage = registerPage.getRegisterSuccessMessage().trim();
        String expectedMessage = "Thank you for registering your account";


        Assert.assertEquals(actualMessage, expectedMessage);
    }


    @Test
    public void TC08(){
        System.out.println("User can't login with an account hasn't been activated");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME_NOT,Constant.PASSWORD_NOT);

        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC09(){
        System.out.println("User can change password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        ChangePasswordPage changePasswordPage;
        changePasswordPage = homePage.gotoChange();

        changePasswordPage.change(Constant.PASSWORD,Constant.NEWPASS);

        String actualMsg = changePasswordPage.getLnlChangePassSuccess().getText();
        String expectedMsg = "Your password has been updated!";

        Assert.assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");

    }
}
