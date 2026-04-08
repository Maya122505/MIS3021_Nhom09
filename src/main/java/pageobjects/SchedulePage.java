package pageobjects;


import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class SchedulePage extends GeneralPage {

    public void clickCheckPrice(String departFrom, String arriveAt) {
        String xpath = String.format("//td[text()='%s']/following-sibling::td[text()='%s']/..//a[text()='check price']", departFrom, arriveAt);
        Constant.WEBDRIVER.findElement(By.xpath(xpath)).click();
    }


    public void clickBookTicket(String departFrom, String arriveAt) {
        String xpath = String.format("//td[text()='%s']/following-sibling::td[text()='%s']/..//a[text()='book ticket']", departFrom, arriveAt);
        Constant.WEBDRIVER.findElement(By.xpath(xpath)).click();
    }


    public void clickBookTicketByStationIds(String departStationId, String arriveStationId) {
        String xpath = String.format("//a[contains(@href,'BookTicketPage.cshtml?id1=%s&id2=%s')]", departStationId, arriveStationId);
        WebElement bookTicketLink = Constant.WEBDRIVER.findElement(By.xpath(xpath));
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", bookTicketLink);
    }
}
