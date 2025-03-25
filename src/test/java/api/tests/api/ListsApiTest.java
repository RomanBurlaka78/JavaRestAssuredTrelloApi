package api.tests.api;

import api.steps.ListsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("API Tests")
@Feature("Lists Validation")
@Owner("Group JavaForwardToOffer")
public class ListsApiTest {

    ListsSteps listsSteps = new ListsSteps();

    @Test(priority = 4, dependsOnGroups = "Created_Board_and_List")
    @Story("Verify lists")
    @Description("Create a new List on a Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewList() {
        String nameList = "List from API";
        Response response = listsSteps.createList(nameList);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("name"), nameList);
    }
}
