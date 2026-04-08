package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends GeneralPage{
    // Wait
    private final WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

    // Elements
    public WebElement getTxtUsername()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(_txtUsername));
    }
    public WebElement getTxtPassword()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(_txtPassword));
    }
    public WebElement getBtnLogin()
    {
        return wait.until(ExpectedConditions.elementToBeClickable(_btnLogin));
    }
    public WebElement getLblLoginErrorMsg()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblLoginErrorMsg));
    }

    // Methods
    public HomePage login(String username, String password)
    {
        // Submit login credentials
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        // Land on Home page
        return new HomePage();
    }
    private final By lnkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
    public WebElement getLnkForgotPassword()
    {
        return wait.until(ExpectedConditions.elementToBeClickable(lnkForgotPassword));
    }
    public FogotPassWordPage clickForgotPasswordLink() {
        // Sử dụng hàm getLnkForgotPassword() thay vì gọi thẳng driver.findElement
        this.getLnkForgotPassword().click();
        return new FogotPassWordPage();
    }

}
