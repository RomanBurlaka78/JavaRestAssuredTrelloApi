package api.controllers.ui;

import api.base.BaseUiTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class UiListSteps extends BaseUiTest {
    @Step("Verify name of list")
    public void getNameOfList() throws InterruptedException {
        Thread.sleep(5000);

        WebElement list = getDriver().findElement(By.xpath("//h2[contains(text(), 'List test API')]"));
        getWait5().until(ExpectedConditions.visibilityOf(list));

        Assert.assertEquals(list.getText(), "List test API");
    }
}
