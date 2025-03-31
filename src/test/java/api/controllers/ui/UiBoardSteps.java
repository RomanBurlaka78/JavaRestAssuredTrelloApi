package api.controllers.ui;

import api.base.BaseUiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class UiBoardSteps extends BaseUiTest {
    @Step("Verify board exists in Trello UI")
    public void verifyLoginInUI() throws IOException {
        login();
        WebElement board = getDriver().findElement(By.xpath("//*[contains(text(),'Api Board')]"));

        Assert.assertTrue(board.isDisplayed(), "Created board is not displayed in UI");
    }

    @Step("Close browser Trello UI and delete driver")
    public void closeBrowserAndDriver() {
        logOut();
        closeDriver();
        Assert.assertEquals(getDriver(), null);
    }

    @Step("Verify board name in Trello UI")
    public void getBoardNameUI() {
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
//        board.click();

    }


}
