package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class TestListener implements ITestListener {

    private static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");


            String customCss = ".test-content-detail { max-width: 100%; width: 100%; padding-right: 20px; } " + ".detail-body { max-width: 100% !important; } " + "pre, code { white-space: pre-wrap; word-wrap: break-word; font-size: 14px; max-height: none !important; } " + ".test-wrapper .test-content { overflow-y: auto !important; }";

            spark.config().setCss(customCss);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Railway Automation Report");
            spark.config().setReportName("Railway Regression Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Team", "09");
            extent.setSystemInfo("Project Name", "Railway Automation");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestClass().getName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Case Passed: <b>" + result.getName() + "</b>");

        String screenshotPath = Utilities.captureScreenshotAndReturnPath("PASS", result.getName());
        if (screenshotPath != null) {
            String base64Image = encodeFileToBase64Binary(new File(screenshotPath));
            if (base64Image != null) {
                test.get().info("Screenshot", com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Case Failed: <b>" + result.getName() + "</b>");

        if (result.getThrowable() != null) {
            test.get().log(Status.FAIL, "<pre style='color:red;'>" + result.getThrowable().getMessage() + "</pre>");
            test.get().log(Status.FAIL, result.getThrowable());
        }

        String screenshotPath = Utilities.captureScreenshotAndReturnPath("FAIL", result.getName());
        if (screenshotPath != null) {
            String base64Image = encodeFileToBase64Binary(new File(screenshotPath));
            if (base64Image != null) {
                test.get().fail("Screenshot at failure", com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Case Skipped: " + result.getName());
        if (result.getThrowable() != null) {
            test.get().log(Status.SKIP, result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Ghi dữ liệu vào file HTML sau khi kết thúc một khối <test>
        if (extent != null) {
            extent.flush();
        }
    }

    private String encodeFileToBase64Binary(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.err.println("Could not read screenshot file: " + e.getMessage());
            return null;
        }
    }
}