package pageobjects;

import common.Constant;
import org.openqa.selenium.By;

public class MyTicketPage {

    // Locator trỏ thẳng vào thẻ <li> chứa tab My ticket
    private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']/..");

    public boolean isMyTicketTabActive() {
        // Lấy thuộc tính class và kiểm tra xem có chứa chữ "selected" không
        String tabClass = Constant.WEBDRIVER.findElement(tabMyTicket).getAttribute("class");
        return tabClass != null && tabClass.contains("selected"); // Đây chính là câu lệnh return bị thiếu!
    }
}