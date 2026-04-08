package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResetPasswordPage extends GeneralPage {

    private final By txtNewPassword = By.id("newPassword");
    private final By txtConfirmPassword = By.id("confirmPassword");
    private final By txtResetToken = By.id("resetToken");
    private final By btnResetPassword = By.xpath("//input[@value='Reset Password']");
    private final By lblFormErrorMsg = By.xpath("//p[@class='message error']");
    private final By lblTokenErrorMsg = By.xpath("//label[@for='resetToken' and @class='validation-error']");
    private final By lblConfirmPwdErrorMsg = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");

    public void enterNewPasswords(String password) {
        Constant.WEBDRIVER.findElement(txtNewPassword).sendKeys(password);
        Constant.WEBDRIVER.findElement(txtConfirmPassword).sendKeys(password);
    }

    public void clearResetToken() {
        Constant.WEBDRIVER.findElement(txtResetToken).clear();
    }

    public void clickResetPasswordButton() {
        Constant.WEBDRIVER.findElement(btnResetPassword).click();
    }

    public String getFormErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblFormErrorMsg).getText().trim();
    }

    public String getTokenErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblTokenErrorMsg).getText().trim();
    }

    public void enterNewPasswords(String newPassword, String confirmPassword) {
        Constant.WEBDRIVER.findElement(txtNewPassword).sendKeys(newPassword);
        Constant.WEBDRIVER.findElement(txtConfirmPassword).sendKeys(confirmPassword);
    }

    public String getConfirmPasswordErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblConfirmPwdErrorMsg).getText().trim();
    }
}
