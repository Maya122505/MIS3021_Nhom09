package pageobjects;

import common.Constant;
import org.openqa.selenium.By;

public class SchedulePage extends GeneralPage {
    
    public void clickCheckPrice(String departFrom, String arriveAt) {
        String xpath = String.format("//td[text()='%s']/following-sibling::td[text()='%s']/..//a[text()='check price']", departFrom, arriveAt);
        Constant.WEBDRIVER.findElement(By.xpath(xpath)).click();
    }

    public void clickBookTicket(String departFrom, String arriveAt) {
        String xpath = String.format("//td[text()='%s']/following-sibling::td[text()='%s']/..//a[text()='book ticket']", departFrom, arriveAt);
        Constant.WEBDRIVER.findElement(By.xpath(xpath)).click();
    }
}
