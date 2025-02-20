package api.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTestUi {
    private static WebDriver driver;
    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;
    private  static ChromeOptions chromeOptions;

    protected static WebDriver getDriver() {
        return driver;
    }

    static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        chromeOptions  = new ChromeOptions();
        chromeOptions.getBrowserName();
        chromeOptions.addArguments("window-size= 1920, 1080");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        return driver;
    }

    static void getWeb(){
        driver.get("");

    }

    static void login() {

    }

    static File takeScreenshot(WebDriver driver, String methodName, String className) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(String.format("screenshots/%s.%s.png", className, methodName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    private void closeDriver() {
        if (driver != null) {
            driver.quit();

            driver = null;
            wait2 = null;
            wait5 = null;
            wait10 = null;

            System.out.println("Browser closed");
        }
    }
    protected WebDriverWait getWait2 () {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }

        return wait2;
    }

    protected WebDriverWait getWait5 () {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }

        return wait5;
    }

    protected WebDriverWait getWait10 () {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }

        return wait10;
    }
    public String logf(String message, long l) {
        System.out.println(message);
        return message;
    }


    @BeforeMethod
    protected void beforeMethod(Method method) {

        try {
            createDriver();
            getWeb();

        } catch (Exception e) {
            closeDriver();
            throw new RuntimeException(e);
        }

    }
    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {

        if (!testResult.isSuccess()) {
            takeScreenshot(driver, method.getName(), this.getClass().getName());

        }
        if (testResult.isSuccess()) {
            closeDriver();
        }

        logf("Execution time is %o sec\n\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000);
    }
}
