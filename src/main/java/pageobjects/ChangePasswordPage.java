package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangePasswordPage {

    //Location
    private final By _txtCurrentPass = By.xpath("//input[@id='currentPassword']");
    private final By _txtNewPass = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPass = By.xpath("//input[@id='confirmPassword']");
    private final By _btnChangePass = By.xpath("//input[@value='Change Password']");
    private final By _lblChangePassError = By.xpath("//p[@class='message error']");
    private final By _lnlChangePassSuccess = By.xpath("//p[@class='message success']");
    private final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']/..");

    //Elements
    public WebElement getTxtCurrentPass() {
        return Constant.WEBDRIVER.findElement(_txtCurrentPass);
    }

    public WebElement getTxtNewPass() {
        return Constant.WEBDRIVER.findElement(_txtNewPass);
    }

    public WebElement getTxtConfirmPass() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPass);
    }

    public WebElement getBtnChangePass() {
        return Constant.WEBDRIVER.findElement(_btnChangePass);
    }

    public WebElement getLblChangePassError() {
        return Constant.WEBDRIVER.findElement(_lblChangePassError);
    }

    public WebElement getLnlChangePassSuccess() {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(_lnlChangePassSuccess));
    }

    //Method
    public boolean isChangePasswordTabActive() {

        String tabClass = Constant.WEBDRIVER.findElement(tabChangePassword).getAttribute("class");
        return tabClass != null && tabClass.contains("selected");
    }

    public ChangePasswordPage change(String password, String newPass) {
        this.getTxtCurrentPass().sendKeys(password);
        this.getTxtNewPass().sendKeys(newPass);
        this.getTxtConfirmPass().sendKeys(newPass);
        this.getBtnChangePass().click();

        return new ChangePasswordPage();
    }
}
