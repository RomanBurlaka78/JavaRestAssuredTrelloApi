package api.controllers.ui;

import api.base.BaseUiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UiBoardSteps extends BaseUiTest {

    @Step("Verify board exists in Trello UI")
    public void verifyLoginInUI() throws IOException {
        login();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement board = getDriver().findElement(By.xpath("//div[@data-testid='TeamTabSection']//parent::ul/li[3]/a"));
        board.click();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement boardApi = getDriver().findElement(By.xpath("//div[@title='Api Board']/div"));


        Assert.assertEquals(boardApi.getText(), "Api Board");
    }

    @Step("Close browser Trello UI and delete driver")
    public void closeBrowserAndDriver() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logOut();

        Assert.assertEquals(getDriver(), null);
    }

    @Step("Verify board name in Trello UI")
    public void getBoardNameUI() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement board = getDriver().findElement(By.xpath("//*[contains(text(),'Api Board')]"));
        Assert.assertEquals(board.getText(),"Api Board");
        takeScreenshot(getDriver(),"testGetBoard", "BoardApiTest");
        Allure.addAttachment("Trello UI board Screenshot", "image/png",
                Arrays.toString(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));

    }
    @Step("Verify updated board name in Trello UI")
    public void getUINewBoardName() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement board = getDriver().findElement(By.xpath("//*[contains(text(),'New Api Board')]"));
        Assert.assertEquals(board.getText(),"New Api Board");


    }


}
