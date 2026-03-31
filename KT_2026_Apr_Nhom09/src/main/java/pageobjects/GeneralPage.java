package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeneralPage {
    // Wait
    private final WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

    // Elements
    protected WebElement getTabLogin() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabLogin));
    }
    protected WebElement getTabLogout() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabLogout));
    }
    protected WebElement getLblWelcomeMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblWelcomeMessage));
    }

    // Methods
    public String getWelcomeMessage()
    {
        return this.getLblWelcomeMessage().getText();
    }
    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return new LoginPage();
    }
}
