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
    private final By tabMyTicket = By.xpath("//*[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    private final By tabChangePassword = By.xpath("//*[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By tabChange = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");

    // Elements
    protected WebElement getTabLogin() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabLogin));
    }
    protected WebElement getTabLogout() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabLogout));
    }
    protected WebElement getLblWelcomeMessage() {return wait.until(ExpectedConditions.visibilityOfElementLocated(lblWelcomeMessage));}
    protected WebElement getTabMyTicket() {return Constant.WEBDRIVER.findElement(tabMyTicket);}
    protected WebElement getTabChangePassword() {
        return Constant.WEBDRIVER.findElement(tabChangePassword);
    }
    protected WebElement getTabRegister() {return Constant.WEBDRIVER.findElement(tabRegister);}
    //    protected WebElement getTabChange(){
//        return Constant.WEBDRIVER.findElement(tabChange);
//    }
    protected WebElement getTabChange() {
        // Thay vì dùng Constant.WEBDRIVER.findElement...
        // Hãy dùng wait để đợi cho đến khi tab xuất hiện trên màn hình
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']")));
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

    public MyTicketPage gotoMyTicketPage() {
        this.getTabMyTicket().click();
        return new MyTicketPage();
    }

    public ChangePasswordPage gotoChangePasswordPage() {
        this.getTabChangePassword().click();
        return new ChangePasswordPage();
    }

    public ChangePasswordPage gotoChange()
    {
        this.getTabChange().click();
        return new ChangePasswordPage();
    }

    public RegisterPage gotoRegisterPage() {
        this.getTabRegister().click();
        return new RegisterPage();
    }
}
