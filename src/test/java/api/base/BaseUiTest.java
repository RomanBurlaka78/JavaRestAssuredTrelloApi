package api.base;

import api.utils.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

public abstract class BaseUiTest {
    private static WebDriver driver;
    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;
    private static ChromeOptions chromeOptions;

    protected static WebDriver getDriver() {
        return driver;
    }

    protected static WebDriver createDriver() {

//        WebDriverManager.chromedriver().setup();
//        chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments(
//                "--headless",
//                "--disable-gpu",
//                "--window-size=1920,1080",
//                "--no-sandbox",
//                "--disable-dev-shm-usage",
//                "--remote-allow-origins=*",
//                "--user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID()
//        );
//        chromeOptions.getBrowserName();
//        chromeOptions.addArguments("window-size= 1920, 1080");
//        driver = new ChromeDriver(chromeOptions);
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments(
                    "--headless",
                    "--disable-gpu",
                    "--window-size=1920,1080",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--remote-allow-origins=*",
                    "--user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID()
            );

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        }
        return driver;
    }


    protected static File takeScreenshot(WebDriver driver, String methodName, String className) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(String.format("target/screenshots/%s.%s.png", className, methodName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();

            driver = null;
            wait2 = null;
            wait5 = null;
            wait10 = null;

            System.out.println("Browser closed");
        }
    }

    protected WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }

        return wait2;
    }

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }

        return wait5;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }

        return wait10;
    }

    public String logf(String message, long l) {
        System.out.println(message);
        return message;
    }

    @BeforeClass
    public void login() throws IOException {
        createDriver();
        getDriver().get("https://trello.com/login");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        WebElement login = getDriver().findElement(By.id("username"));
        login.sendKeys(ConfigLoader.getProperty("login"));
        login.submit();
        try {
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        WebElement password = getDriver().findElement(By.id("password"));
        password.sendKeys(ConfigLoader.getProperty("password"));
        WebElement submit = getDriver().findElement(By.id("login-submit"));
        submit.click();

    }

    public void logOut() {
        try {
            getDriver().findElement(By.xpath("//button[@data-testid='header-member-menu-button']")).click();

            getDriver().findElement(By.xpath("//button[@data-testid='account-menu-logout']")).click();
            getDriver().findElement(By.xpath("//button[@data-testid='logout-button']")).click();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        closeDriver();
    }

}
