package api.tests.api;

import api.steps.ListsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("API Tests")
@Feature("Lists Validation")
@Owner("Group JavaForwardToOffer")
public class ListsApiTest {

    private ListsSteps listsSteps = new ListsSteps();
    private String bordName = "Board for lists";

    @BeforeClass
    public void setUp(){
        listsSteps.createABord(bordName);
    }

    @AfterClass
    public void tearDown(){
        listsSteps.deleteBoard();
    }

    @Test(priority = 0)
    @Story("lists")
    @Description("Create a new List on a Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewList() {
        String nameOfTheList = "List from API";
        Response response = listsSteps.createList(nameOfTheList);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("name"), nameOfTheList);
    }
}
