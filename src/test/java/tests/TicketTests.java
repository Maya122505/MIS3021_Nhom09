package tests;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.BookingPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyTicketPage;

import java.time.Duration;

public class TicketTests {

    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void afterMethod() {
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test
    public void TC14() throws Exception {
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookingPage bookingPage = homePage.gotoBookingPage();
        
        // Define test data based on requirements
        String departFrom = "Sài Gòn";
        String arriveAt = "Nha Trang";
        String seatType = "Soft bed with air conditioner";
        String ticketAmount = "1";

        bookingPage.selectDepartDate();
        bookingPage.selectDepartFrom(departFrom);
        bookingPage.selectArriveAt(arriveAt);
        bookingPage.selectSeatType(seatType);
        bookingPage.selectTicketAmount(ticketAmount);
        
        // Capture selected values if needed for assertion (or assume they match inputs)
        // But for assertion, we need to know what was selected for comparison.
        
        bookingPage.bookTicket();

        SoftAssert softAssert = new SoftAssert();

        // Check success message
        String actualMsg = bookingPage.getSuccessMessage().trim();
        String expectedMsg = "Ticket booked successfully!";
        softAssert.assertEquals(actualMsg, expectedMsg, "Lỗi: Thông báo đặt vé thành công không khớp mong đợi!");

        // Check booked ticket information
        softAssert.assertEquals(bookingPage.getBookedDepartStation(), departFrom, "Depart Station mismatch!");
        softAssert.assertEquals(bookingPage.getBookedArriveStation(), arriveAt, "Arrive Station mismatch!");
        softAssert.assertEquals(bookingPage.getBookedSeatType(), seatType, "Seat Type mismatch!");
        softAssert.assertEquals(bookingPage.getBookedTicketAmount(), ticketAmount, "Ticket Amount mismatch!");

        softAssert.assertAll();
    }

    @Test
    public void TC15() {
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        Assert.assertTrue(myTicketPage.isMyTicketPageDisplayed(), "Lỗi: Trang My Ticket không hiển thị!");
    }
}
