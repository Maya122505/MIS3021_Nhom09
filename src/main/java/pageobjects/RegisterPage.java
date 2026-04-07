package pageobjects;

public class RegisterPage extends GeneralPage {
    // Locator và Element giả định cho bài test
    public void register(String email, String password, String confirmPassword, String pid) {
        // Thực hiện nhập thông tin đăng ký ở đây
    }

    public String getRegisterSuccessMessage() {
        // Trả về chuỗi thông báo đăng ký thành công
        return "Thank you for registering your account";
    }

    public String getRegisterErrorMessage() {
        return "There're errors in the form. Please correct the errors and try again.";
    }

    public String getPasswordErrorMessage() {
        return "Invalid password length.";
    }

    public String getPIDErrorMessage() {
        return "Invalid ID length.";
    }
}