package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTicketPage extends GeneralPage {
    private final WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    private final By btnCancel = By.xpath("//input[@value='Cancel']");
    private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']/..");

    public void cancelTicketById(String ticketId) {
        String cleanId = ticketId.trim();
        String dynamicXPath = String.format("//input[contains(@onclick, 'DeleteTicket(%s)')]", cleanId);
        
        WebElement btnCancelElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicXPath)));
        
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", btnCancelElement);
        
        wait.until(ExpectedConditions.elementToBeClickable(btnCancelElement));
        js.executeScript("arguments[0].click();", btnCancelElement);

        wait.until(ExpectedConditions.alertIsPresent());
        Constant.WEBDRIVER.switchTo().alert().accept();
    }

    public boolean isTicketDisplayed(String ticketId) {
        String cleanId = ticketId.trim();
        String dynamicXPath = String.format("//input[contains(@onclick, 'DeleteTicket(%s)')]", cleanId);

        int elementCount = Constant.WEBDRIVER.findElements(By.xpath(dynamicXPath)).size();
        return elementCount > 0;
    }

    public void cancelTicket() {
        WebElement btnCancelElement = Constant.WEBDRIVER.findElement(By.xpath("(//input[@value='Cancel'])[1]"));

        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", btnCancelElement);
        btnCancelElement.click();
    }

    public boolean isMyTicketPageDisplayed() {
        return Constant.WEBDRIVER.getCurrentUrl().contains("ManageTicket");
    }

    public boolean isTicketCancelled() {
        return Constant.WEBDRIVER.findElements(btnCancel).isEmpty();
    }

    public int getTicketAmount() {
        return Constant.WEBDRIVER.findElements(By.xpath("//table[@class='MyTable']//tr")).size() - 1;
    }

    public boolean isMyTicketTabActive() {
        String tabClass = Constant.WEBDRIVER.findElement(tabMyTicket).getAttribute("class");
        return tabClass != null && tabClass.contains("selected");
    }
}
