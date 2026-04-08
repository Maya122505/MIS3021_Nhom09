package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BookingPage extends GeneralPage {
    private static final String BOOK_TICKET_URL = "http://railwayb1.somee.com/Page/BookTicketPage.cshtml";
    private final By ddlDepartDate = By.name("Date");
    private final By ddlDepartFrom = By.name("DepartStation");
    private final By ddlArriveAt = By.name("ArriveStation");
    private final By ddlSeatType = By.name("SeatType");
    private final By ddlTicketAmount = By.name("TicketAmount");

    private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
    private final By lblSuccess = By.xpath("//div[@id='content']//h1");


    private final By bookedDepartStationCell = By.xpath("//div[@class='DivTable']//table[contains(@class,'MyTable')]//tr[contains(@class,'OddRow') or contains(@class,'EvenRow')][1]/td[1]");
    private final By bookedArriveStationCell = By.xpath("//div[@class='DivTable']//table[contains(@class,'MyTable')]//tr[contains(@class,'OddRow') or contains(@class,'EvenRow')][1]/td[2]");
    private final By bookedSeatTypeCell = By.xpath("//div[@class='DivTable']//table[contains(@class,'MyTable')]//tr[contains(@class,'OddRow') or contains(@class,'EvenRow')][1]/td[3]");
    private final By bookedDepartDateCell = By.xpath("//div[@class='DivTable']//table[contains(@class,'MyTable')]//tr[contains(@class,'OddRow') or contains(@class,'EvenRow')][1]/td[4]");
    private final By bookedTicketAmountCell = By.xpath("//div[@class='DivTable']//table[contains(@class,'MyTable')]//tr[contains(@class,'OddRow') or contains(@class,'EvenRow')][1]/td[7]");


    public void selectDepartDate() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlDepartDate)));
        select.selectByIndex(5);
    }


    public BookingPage openWithRoute(String departStationId, String arriveStationId) {
        Constant.WEBDRIVER.navigate().to(BOOK_TICKET_URL + "?id1=" + departStationId + "&id2=" + arriveStationId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ddlDepartFrom));
        return this;
    }


    public String getSelectedDepartDate() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlDepartDate)));
        return select.getFirstSelectedOption().getText().trim();
    }


    public void selectDepartFrom(String departStation) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlDepartFrom)));
        select.selectByVisibleText(departStation);
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(ddlArriveAt, "disabled", "true")));
        } catch (Exception e) {

        }
    }


    public void selectDepartFromByValue(String departStationValue) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlDepartFrom)));
        select.selectByValue(departStationValue);
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(ddlArriveAt, "disabled", "true")));
        } catch (Exception e) {

        }
    }


    public void selectArriveAt(String arriveStation) {
        wait.until(ExpectedConditions.elementToBeClickable(ddlArriveAt));
        Select select = new Select(Constant.WEBDRIVER.findElement(ddlArriveAt));
        select.selectByVisibleText(arriveStation);
    }


    public void selectArriveAtByValue(String arriveStationValue) {
        wait.until(ExpectedConditions.elementToBeClickable(ddlArriveAt));
        Select select = new Select(Constant.WEBDRIVER.findElement(ddlArriveAt));
        select.selectByValue(arriveStationValue);
    }


    public String getSelectedDepartFrom() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlDepartFrom)));
        return select.getFirstSelectedOption().getText().trim();
    }


    public String getSelectedArriveAt() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(ddlArriveAt)));
        return select.getFirstSelectedOption().getText().trim();
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bookedDepartDateCell)).getText();
    }


    public String getBookedDepartStation() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bookedDepartStationCell)).getText();
    }


    public String getBookedArriveStation() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bookedArriveStationCell)).getText();
    }


    public String getBookedSeatType() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bookedSeatTypeCell)).getText();
    }


    public String getBookedTicketAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bookedTicketAmountCell)).getText();
    }
}
