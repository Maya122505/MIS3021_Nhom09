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




    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(txtNewPassword);
    }


    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtConfirmPassword);
    }


    public WebElement getTxtResetToken() {
        return Constant.WEBDRIVER.findElement(txtResetToken);
    }


    public WebElement getBtnResetPassword() {
        return Constant.WEBDRIVER.findElement(btnResetPassword);
    }




    public void enterNewPasswords(String newPassword, String confirmPassword) {
        getTxtNewPassword().clear();
        getTxtNewPassword().sendKeys(newPassword);


        getTxtConfirmPassword().clear();
        getTxtConfirmPassword().sendKeys(confirmPassword);
    }


    public void enterResetToken(String token) {
        getTxtResetToken().clear();
        getTxtResetToken().sendKeys(token);
    }


    public void clearResetToken() {
        getTxtResetToken().clear();
    }


    public void clickResetPasswordButton() {
        getBtnResetPassword().click();
    }


    public String getFormErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblFormErrorMsg).getText().trim();
    }


    public String getTokenErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblTokenErrorMsg).getText().trim();
    }


    public String getConfirmPasswordErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblConfirmPwdErrorMsg).getText().trim();
    }
}

