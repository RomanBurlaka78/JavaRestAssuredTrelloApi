package api.controllers.ui;

import api.base.BaseUiTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;

public class UiCardSteps extends BaseUiTest {
    @Step("Verify name of card in Trello UI")
    public void verifyCardUpdatedName() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement card = getDriver().findElement(By.xpath("//a[contains(text(), 'NewCardName')]"));
        getWait5().until(ExpectedConditions.visibilityOf(card));

        Assert.assertEquals(card.getText(), "NewCardName", "Created card is not displayed in UI");


    }
}
