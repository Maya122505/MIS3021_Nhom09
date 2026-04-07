package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeneralPage {
    // Wait
    protected final WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    
    // Additional Locators for missing pages (assuming basic structure based on your project)
    private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    private final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");

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
    
    // Additional Elements
    protected WebElement getTabMyTicket() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabMyTicket));
    }
    protected WebElement getTabChangePassword() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabChangePassword));
    }
    protected WebElement getTabRegister() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabRegister));
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
    
    // Additional Methods for Navigation (fixes the red lines in AccountTests.java)
    public MyTicketPage gotoMyTicketPage()
    {
        this.getTabMyTicket().click();
        return new MyTicketPage();
    }
    
    public ChangePasswordPage gotoChangePasswordPage()
    {
        this.getTabChangePassword().click();
        return new ChangePasswordPage();
    }
    
    // In AccountTests TC09 you used gotoChange() instead of gotoChangePasswordPage()
    public ChangePasswordPage gotoChange()
    {
        this.getTabChangePassword().click();
        return new ChangePasswordPage();
    }
    
    public RegisterPage gotoRegisterPage()
    {
        this.getTabRegister().click();
        return new RegisterPage();
    }
}