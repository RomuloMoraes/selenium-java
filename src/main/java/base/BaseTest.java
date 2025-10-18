package base;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;


    @BeforeMethod
    public void setUp() throws IOException {
        basePage = new BasePage();
        driver = basePage.getDriver();
        driver.get(basePage.getUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        //capture the error screenshot
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        //close browser
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void takeScreenshot(String testName) {
        try {
            //create screenshot folder
            File screenshotDir = new File("screenshot");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            //create file name with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + " " + timestamp + ".png";

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File("screenshot/" + fileName);
            FileUtils.copyFile(source, destination);

            System.out.println("Screenshot saved: " + destination.getAbsolutePath());


        } catch (IOException e) {
            System.out.println("Error handling for screenshot capture");

        }
    }

    //method to support capture via manual during tests
    protected void captureScreenshot(String screenshotName) {
        takeScreenshot(screenshotName);
    }
}
