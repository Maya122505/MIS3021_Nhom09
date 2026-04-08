package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingPage extends GeneralPage {

    private final By ddlDepartDate = By.name("Date");
    private final By ddlDepartFrom = By.name("DepartStation");
    private final By ddlArriveAt = By.name("ArriveStation");
    private final By ddlSeatType = By.name("SeatType");
    private final By ddlTicketAmount = By.name("TicketAmount");

    private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
    private final By lblSuccess = By.xpath("//div[@id='content']//h1");

    // Locators for booked ticket information - using contains to be more flexible with ":" or spaces
    private final By lblDepartDateBooked = By.xpath("//td[contains(text(),'Depart Date')]/following-sibling::td");
    private final By lblDepartStationBooked = By.xpath("//td[contains(text(),'Depart Station')]/following-sibling::td");
    private final By lblArriveStationBooked = By.xpath("//td[contains(text(),'Arrive Station')]/following-sibling::td");
    private final By lblSeatTypeBooked = By.xpath("//td[contains(text(),'Seat Type')]/following-sibling::td");
    private final By lblTicketAmountBooked = By.xpath("//td[contains(text(),'Amount')]/following-sibling::td");


    public void selectDepartDate() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlDepartDate)));
        select.selectByIndex(5);
    }

    public void selectDepartFrom(String departStation) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlDepartFrom)));
        select.selectByVisibleText(departStation);
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(ddlArriveAt, "disabled", "true")));
        } catch (Exception e) {
            // Ignore if not disabled
        }
    }

    public void selectArriveAt(String arriveStation) {
        wait.until(ExpectedConditions.elementToBeClickable(ddlArriveAt));
        Select select = new Select(Constant.WEBDRIVER.findElement(ddlArriveAt));
        select.selectByVisibleText(arriveStation);
    }

    public void selectSeatType(String seatType) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlSeatType)));
        select.selectByVisibleText(seatType);
    }

    public void selectTicketAmount(String amount) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlTicketAmount)));
        select.selectByVisibleText(amount);
    }

    public void bookTicket() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(btnBookTicket));
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblSuccess)).getText();
    }

    public String getBookedDepartDate() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblDepartDateBooked)).getText();
    }

    public String getBookedDepartStation() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblDepartStationBooked)).getText();
    }

    public String getBookedArriveStation() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblArriveStationBooked)).getText();
    }

    public String getBookedSeatType() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblSeatTypeBooked)).getText();
    }

    public String getBookedTicketAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblTicketAmountBooked)).getText();
    }
}
