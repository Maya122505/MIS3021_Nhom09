package tests;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.*;

public class AccountTests {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("--- Pre-condition: Mở trình duyệt ---");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("--- Post-condition: Đóng trình duyệt ---");
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

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "Lỗi: Tab 'My ticket' không hiển thị sau khi login");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Lỗi: Tab 'Change password' không hiển thị sau khi login");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "Lỗi: Tab 'Logout' không hiển thị sau khi login");

        String actualLogoutText = homePage.getLogoutTabText();

        String expectedLogoutText = "Logout";
        Assert.assertEquals(actualLogoutText, expectedLogoutText, "Issue: Text Mismatch on \"Logout\" tab.");

        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        Assert.assertTrue(myTicketPage.isMyTicketTabActive(), "Lỗi: Tab My ticket không active");

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        Assert.assertTrue(changePasswordPage.isChangePasswordTabActive(), "Lỗi: Tab Change password không active");
    }

    @Test
    public void TC07() {
        System.out.println("TC07 - User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        String dynamicEmail = "test" + System.currentTimeMillis() + "@gmail.com";
        registerPage.register(dynamicEmail, "123456789", "123456789", "123456789");

        String actualMessage = registerPage.getRegisterSuccessMessage().trim();

        String expectedMessage = "Thank you for registering your account";
        Assert.assertEquals(actualMessage, expectedMessage, "Lỗi: Thông báo đăng ký thành công không khớp mong đợi!");
    }

    @Test
    public void TC08() {
        System.out.println("TC08 - User can't login with an account hasn't been activated");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME_NOT, Constant.PASSWORD_NOT);
        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC09() {
        System.out.println("TC09 - User can change password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        ChangePasswordPage changePasswordPage = homePage.gotoChange();
        changePasswordPage.change(Constant.PASSWORD, Constant.PASSWORD);

        String actualMsg = changePasswordPage.getLnlChangePassSuccess().getText().trim();

        String expectedMsg = "Your password has been updated";
        Assert.assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");
    }

}
