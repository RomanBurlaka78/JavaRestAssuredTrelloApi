package api.tests.ui;

import api.base.BaseTestUi;
import api.models.HomePage;
import api.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class FirstUiTest extends BaseTestUi {
//
//    {
//        getDriver().get("https://trello.com/login");
//
//        try {
//            WebElement login =  getDriver().findElement(By.xpath("//input[@id = 'username']"));
//            WebElement password =  getDriver().findElement(By.xpath("//input[@id = 'password']"));
//            WebElement submit =  getDriver().findElement(By.xpath("//button[@id = 'login-submit']"));
//            login.sendKeys(ConfigLoader.getProperty("login"));
//            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//            password.sendKeys(ConfigLoader.getProperty("password"));
//            submit.click();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Test
    public void testVerifydBoard() {
        getDriver().get("https://trello.com/login");

        try {
            WebElement login =  getDriver().findElement(By.xpath("//input[@id = 'username']"));
            WebElement password =  getDriver().findElement(By.xpath("//input[@id = 'password']"));
            WebElement submit =  getDriver().findElement(By.xpath("//button[@id = 'login-submit']"));
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            login.sendKeys(ConfigLoader.getProperty("login"));
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            password.sendKeys(ConfigLoader.getProperty("password"));
            submit.click();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println((getDriver().getCurrentUrl()));



    }
    @Test
    public void testVerify() {

    }


}
