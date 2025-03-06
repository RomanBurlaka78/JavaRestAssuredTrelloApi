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

    @Test(priority = 2, dependsOnMethods = "testCreateBoard")
    @Story("Verify get board")
    @Description("Get board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoard() {
        Response response = boardSteps.getBoard(TestData.boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().get("id").toString(), TestData.boardId);
        Assert.assertEquals(response.body().jsonPath().get("name").toString(), "Api Board");
    }

    @Test(priority = 3, dependsOnMethods = "testCreateBoard")
    @Story("Verify update board")
    @Description("Update board")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateBoard() {
        Response response = boardSteps.updateBoard(TestData.boardId, "New Api Board");

        Assert.assertEquals(response.body().jsonPath().get("id").toString(), TestData.boardId);
        Assert.assertEquals(response.body().jsonPath().get("name").toString(), "New Api Board");
    }

    @Test(priority = 4, dependsOnMethods = "testCreateBoard")
    @Story("Verify created a lable on a board")
    @Description("Create a Label on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateLabelOnBoard() {
        Response response = boardSteps.createLabelOnBoard(TestData.boardId, "Api_Label", "red");
        TestData.labelId = response.path("id").toString();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().path("name"), "Api_Label");
        Assert.assertEquals(response.body().path("color"), "red");
    }

    @Test(priority = 5, dependsOnMethods = "testCreateLabelOnBoard")
    @Story("Verify get labels on a board")
    @Description("Get Labels on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetLabelsOnBoard() {
        Response response = boardSteps.getLabelOnBoard(TestData.boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
