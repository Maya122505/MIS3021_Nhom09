package pageobjects;

import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends GeneralPage {
    // Locator và Element giả định cho bài test
    public boolean isChangePasswordTabActive() {
        return true;
    }

    public void change(String currentPassword, String newPassword) {
        // Thực hiện nhập mật khẩu ở đây
    }

    public WebElement getLnlChangePassSuccess() {
        // Trả về phần tử chứa thông báo đổi mật khẩu thành công
        return null;
    }
}