package tests;

import common.Constant;
import common.Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.*;

import java.time.Duration;

public class ManageTests {
    private static final Logger logger = LogManager.getLogger(ManageTests.class);

    @BeforeMethod
    public void beforeMethod() {
        logger.info("Mở trình duyệt Chrome và phóng to màn hình");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Đóng trình duyệt");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test(description = "TC12 - Errors display when password reset token is blank")
    public void TC12() {
        String testEmail = "14253647lnbt@gmail.com";

        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();

        FogotPassWordPage forgotPwdPage = loginPage.clickForgotPasswordLink();

        forgotPwdPage.submitEmailForReset(testEmail);

        String resetLink = Utilities.getResetPasswordLinkFromEmail(testEmail);
        Constant.WEBDRIVER.get(resetLink);

        ResetPasswordPage resetPage = new ResetPasswordPage();

        resetPage.enterNewPasswords("NewPass@123");
        resetPage.clearResetToken();

        resetPage.clickResetPasswordButton();

        String expectedFormError = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
        String expectedTokenError = "The password reset token is invalid.";

        Assert.assertEquals(resetPage.getFormErrorMessage(), expectedFormError, "Form error message không khớp!");
        Assert.assertEquals(resetPage.getTokenErrorMessage(), expectedTokenError, "Token error message không khớp!");
    }

    @Test(description = "TC13 - Errors display if password and confirm password don't match when resetting password")
    public void TC13() {
        String testEmail = "14253647lnbt@gmail.com";

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        FogotPassWordPage forgotPwdPage = loginPage.clickForgotPasswordLink();

        forgotPwdPage.submitEmailForReset(testEmail);

        String resetLink = Utilities.getResetPasswordLinkFromEmail(testEmail);
        Constant.WEBDRIVER.get(resetLink);

        ResetPasswordPage resetPage = new ResetPasswordPage();

        resetPage.enterNewPasswords("NewPass@123", "WrongConfirm@123");

        resetPage.clickResetPasswordButton();

        String expectedFormError = "Could not reset password. Please correct the errors and try again.";
        String expectedConfirmPwdError = "The password confirmation did not match the new password.";

        Assert.assertEquals(resetPage.getFormErrorMessage(), expectedFormError, "Lỗi: Form error message không khớp!");
        Assert.assertEquals(resetPage.getConfirmPasswordErrorMessage(), expectedConfirmPwdError, "Lỗi: Confirm Password error message không khớp!");
    }

    @Test
    public void TC16() {
        String departFrom = "Huế";
        String arriveAt = "Sài Gòn";

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookingPage bookingPage = homePage.gotoBookingPage();
        
        bookingPage.selectDepartDate(); 
        bookingPage.selectDepartFrom(departFrom);
        bookingPage.selectArriveAt(arriveAt);

        bookingPage.bookTicket();

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("id="));

        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        String ticketId = currentUrl.split("id=")[1];

        MyTicketPage myTicketPage = bookingPage.gotoMyTicketPage();
        myTicketPage.cancelTicketById(ticketId);

        boolean isTicketExist = myTicketPage.isTicketDisplayed(ticketId);

        Assert.assertFalse(isTicketExist, "Lỗi: Vé ID " + ticketId + " vẫn còn hiển thị trong danh sách sau khi hủy!");
    }
}
