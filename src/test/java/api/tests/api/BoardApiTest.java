package api.tests.api;

import api.base.BaseTest;
import api.base.TestData;
import api.steps.BoardSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

@Epic("API Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
public class BoardApiTest extends BaseTest {

    private BoardSteps boardSteps = new BoardSteps();

    @Test(priority = 1, description = "Create Board Validation")
    @Story("Verify created board")
    @Description("Get list of user")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateBoard() {
        Response response = boardSteps.createBoard("Api Board");
        TestData.boardId =  response.path("id").toString();

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 111, dependsOnMethods = "testCreateBoard")
    @Story("Verify delete board")
    @Description("Delete board")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteBoard() {
        Response response = boardSteps.deleteBoardStep(TestData.boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
