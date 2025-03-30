package api.tests;

import api.base.BaseTest;
import api.controllers.ListsSteps;
import api.controllers.ui.UiBoardSteps;
import api.controllers.ui.UiListSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("API Tests")
@Feature("Lists Validation")
@Owner("Group JavaForwardToOffer")
public class ListsApiTest extends BaseTest {

    ListsSteps listsSteps = new ListsSteps();
    UiBoardSteps uiBoardSteps = new UiBoardSteps();
    UiListSteps uiListSteps = new UiListSteps();

    @Test(priority = 7, dependsOnGroups = "Created_Board_and_List")
    @Story("Verify lists")
    @Description("Create a new List on a Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewList() throws InterruptedException {
        String nameList = "List from API";
        Response response = listsSteps.createList(nameList);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("name"), nameList);

//        uiListSteps.getNameOfList();
    }
}
