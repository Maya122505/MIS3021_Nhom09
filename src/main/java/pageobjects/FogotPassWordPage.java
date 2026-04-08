package pageobjects;

import common.Constant;
import org.openqa.selenium.By;

public class FogotPassWordPage extends GeneralPage {

    private final By txtEmail = By.xpath("//input[@id='email']");

    private final By btnSendInstructions = By.xpath("//input[@value='Send Instructions']");

    public void submitEmailForReset(String emailAddress) {
        Constant.WEBDRIVER.findElement(txtEmail).sendKeys(emailAddress);
        Constant.WEBDRIVER.findElement(btnSendInstructions).click();
    }
}