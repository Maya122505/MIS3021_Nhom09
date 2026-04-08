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
import pageobjects.SchedulePage;

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
    public void TC14() {
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);


        homePage.gotoBookingPage();
        BookingPage bookingPage = new BookingPage().openWithRoute("1", "3");


        String departFrom = "Sài Gòn";
        String arriveAt = "Nha Trang";
        String seatType = "Soft bed with air conditioner";
        String ticketAmount = "1";


        Assert.assertEquals(bookingPage.getSelectedDepartFrom(), departFrom, "Depart Station preselected incorrectly.");
        Assert.assertEquals(bookingPage.getSelectedArriveAt(), arriveAt, "Arrive Station preselected incorrectly.");
        bookingPage.selectDepartDate();
        String departDate = bookingPage.getSelectedDepartDate();
        bookingPage.selectSeatType(seatType);
        bookingPage.selectTicketAmount(ticketAmount);
        bookingPage.bookTicket();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(bookingPage.getSuccessMessage().trim(), "Ticket booked successfully!", "Success message mismatch!");
        softAssert.assertEquals(bookingPage.getBookedDepartDate(), departDate, "Depart Date mismatch!");
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


        String departFrom = "Huế";
        String arriveAt = "Sài Gòn";


        SchedulePage schedulePage = homePage.gotoSchedulePage();
        schedulePage.clickBookTicketByStationIds("5", "1");


        BookingPage bookingPage = new BookingPage();
        Assert.assertEquals(bookingPage.getSelectedDepartFrom(), departFrom, "Depart from value mismatch!");
        Assert.assertEquals(bookingPage.getSelectedArriveAt(), arriveAt, "Arrive at value mismatch!");
    }
}



