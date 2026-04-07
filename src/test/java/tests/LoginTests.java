package tests;

import common.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class LoginTests {
    private static final Logger logger = LogManager.getLogger(LoginTests.class);

    @BeforeMethod
    public void beforeMethod() {
        logger.info("Mở trình duyệt Chrome và phóng to màn hình");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Đóng trình duyệt");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC01() {
        logger.info("Bắt đầu TC01 - User can log into Railway với tài khoản hợp lệ");

        HomePage homePage = new HomePage();
        homePage.open();

        logger.info("Chuyển sang trang Login");
        LoginPage loginPage = homePage.gotoLoginPage();

        logger.info("Thực hiện đăng nhập với user: " + Constant.USERNAME);
        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();

        String expectMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg, expectMsg, "LỖI: Thông báo Welcome hiển thị không đúng hoặc User chưa đăng nhập thành công!");

        logger.info("TC01 hoàn thành thành công.");
    }
    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank Username textbox");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("", Constant.PASSWORD);
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form. ";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC03(){
        System.out.println("TC03 - User cannot log into Railway with invalid password ");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage =homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,"12345678c");
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC04(){
        System.out.println("TC04 - Login page displays when un-logged User clicks on 'Book ticket' tab");
        HomePage homePage= new HomePage();
        homePage.open();
        LoginPage loginPage =homePage.gotoLoginPage();
        WebElement changePasswordLink = Constant.WEBDRIVER.findElement(By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']"));
        changePasswordLink.click();
        WebElement loginForm = Constant.WEBDRIVER.findElement(By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']"));
        Assert.assertTrue(loginForm.isDisplayed(), "Login page did not display when un-logged user clicked on 'Book ticket' tab");
    }

    @Test
    public void TC05(){
        System.out.println("TC05 - System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage();
        homePage.open();

        for (int i = 1; i <= 4; i++) {
            LoginPage loginPage = new LoginPage();
            homePage.gotoLoginPage();
            loginPage.login(Constant.USERNAME, "wrong_password");
            System.out.println("Lần nhập sai thứ: " + i);
        }

        LoginPage finalLoginPage = new LoginPage();
        String actualErrorMsg = finalLoginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Thông báo không khớp ở lần thứ 4!");
    }
}
