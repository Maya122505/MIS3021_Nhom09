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
    private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    private final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    private final By tabSchedule = By.xpath("//div[@id='menu']//a[@href='/Page/TrainTimeListPage.cshtml']"); // Assuming

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
    protected WebElement getTabMyTicket() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabMyTicket));
    }
    protected WebElement getTabChangePassword() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabChangePassword));
    }
    protected WebElement getTabRegister() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabRegister));
    }
    protected WebElement getTabBookTicket() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabBookTicket));
    }
    protected WebElement getTabSchedule() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabSchedule));
    }

    // Methods
    public String getWelcomeMessage() {
        return this.getLblWelcomeMessage().getText();
    }
    
    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }
    
    public BookingPage gotoBookingPage() {
        this.getTabBookTicket().click();
        return new BookingPage();
    }
    
    public MyTicketPage gotoMyTicketPage() {
        this.getTabMyTicket().click();
        return new MyTicketPage();
    }
    
    public ChangePasswordPage gotoChangePasswordPage() {
        this.getTabChangePassword().click();
        return new ChangePasswordPage();
    }
    
    public ChangePasswordPage gotoChange() {
        return gotoChangePasswordPage();
    }
    
    public RegisterPage gotoRegisterPage() {
        this.getTabRegister().click();
        return new RegisterPage();
    }
    
    public SchedulePage gotoSchedulePage() {
        this.getTabSchedule().click();
        return new SchedulePage();
    }

    public HomePage gotoLogoutPage() {
        this.getTabLogout().click();
        return new HomePage();
    }

    public boolean isLogoutTabDisplayed() {
        try {
            return getTabLogout().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMyTicketTabDisplayed() {
        try {
            return getTabMyTicket().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isChangePasswordTabDisplayed() {
        try {
            return getTabChangePassword().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLogoutTabText() {
        try {
            return getTabLogout().getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
