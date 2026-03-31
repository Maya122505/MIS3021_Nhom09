package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static void captureScreenshot(String status, String testName) {
        try {
            // Tạo tên file kèm thời gian để không bị ghi đè
            String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File source = ((TakesScreenshot) Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);

            // Lưu vào thư mục evidence theo trạng thái Pass/Fail
            String filePath = System.getProperty("user.dir") + "/evidence/" + status + "_" + testName + "_" + date + ".png";
            FileUtils.copyFile(source, new File(filePath));
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}