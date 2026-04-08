package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends GeneralPage {


    private final By txtEmail = By.id("email");
    private final By txtPassword = By.id("password");
    private final By txtConfirmPassword = By.id("confirmPassword");
    private final By txtPID = By.id("pid");
    private final By btnRegister = By.xpath("//input[@value='Register']");

    private final By lblRegisterSuccessMessage = By.xpath("//div[@id='content']//p");
    private final By lblRegisterError = By.xpath("//p[@class='message error']");
    private final By lblPasswordError = By.xpath("//label[@for='password' and @class='validation-error']");
    private final By lblPIDError = By.xpath("//label[@for='pid' and @class='validation-error']");


    protected WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }

    protected WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }

    protected WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtConfirmPassword);
    }

    protected WebElement getTxtPID() {
        return Constant.WEBDRIVER.findElement(txtPID);
    }

    protected WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(btnRegister);
    }

    protected WebElement getLblRegisterSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblRegisterSuccessMessage));
    }

    protected WebElement getLblRegisterError() {
        return Constant.WEBDRIVER.findElement(lblRegisterError);
    }

    protected WebElement getLblPasswordError() {
        return Constant.WEBDRIVER.findElement(lblPasswordError);
    }

    protected WebElement getLblPIDError() {
        return Constant.WEBDRIVER.findElement(lblPIDError);
    }


    public void register(String email, String password, String confirmPassword, String pid) {
        this.getTxtEmail().sendKeys(email);
        this.getTxtPassword().sendKeys(password);
        this.getTxtConfirmPassword().sendKeys(confirmPassword);
        this.getTxtPID().sendKeys(pid);

        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", getBtnRegister());

        this.getBtnRegister().click();
    }

    public String getRegisterSuccessMessage() {
        return getLblRegisterSuccessMessage().getText();
    }

    public String getRegisterErrorMessage() {
        return getLblRegisterError().getText();
    }

    public String getPasswordErrorMessage() {
        return getLblPasswordError().getText();
    }

    public String getPIDErrorMessage() {
        return getLblPIDError().getText();
    }
}
