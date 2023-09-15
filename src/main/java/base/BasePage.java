package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static WebDriver driver;
    private String url;
    private Properties prop;

    public BasePage() throws IOException {
        prop = new Properties();
        FileInputStream data = new FileInputStream("src/main/resources/config.properties");
        prop.load(data);
    }

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public String getUrl() {
        url = prop.getProperty("url");
        return url;
    }


}
