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
        captureScreenshotAndReturnPath(status, testName);
    }
    
    // Thêm phương thức mới trả về đường dẫn file để Extent Report có thể sử dụng
    public static String captureScreenshotAndReturnPath(String status, String testName) {
        if (Constant.WEBDRIVER == null) {
            System.out.println("WebDriver is null. Cannot capture screenshot for: " + testName);
            return null;
        }
        
        try {
            // Tạo tên file kèm thời gian để không bị ghi đè
            String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File source = ((TakesScreenshot) Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);

            // Lưu vào thư mục evidence theo trạng thái Pass/Fail
            String filePath = System.getProperty("user.dir") + "/Evidence/" + status + "_" + testName + "_" + date + ".png";
            File destFile = new File(filePath);
            FileUtils.copyFile(source, destFile);
            System.out.println("Screenshot saved: " + filePath);
            
            // Trả về đường dẫn tuyệt đối để TestListener có thể đọc được ảnh
            return destFile.getAbsolutePath();
        } catch (IOException | org.openqa.selenium.WebDriverException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }
}