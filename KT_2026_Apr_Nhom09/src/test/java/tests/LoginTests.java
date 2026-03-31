package tests;

import common.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
}