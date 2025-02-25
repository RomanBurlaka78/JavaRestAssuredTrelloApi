package api.tests.api;

import api.base.BaseTest;
import api.base.TestData;
//import api.steps.BoardSteps;
import api.steps.BoardSteps;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


@Epic("API Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
public class BoardApiTest extends BaseTest {

    private BoardSteps boardSteps = new BoardSteps();

    @DataProvider
    public Object[][] fieldData() {
        return new Object[][]{
                {"closed"},
                {"dateLastActivity"},
                {"dateLastView"},
                {"descData"},
                {"name"},
        };
    }

    @DataProvider
    public Object[][] filteredCards() {
        return new Object[][]{
                {"all"},
                {"closed"},
                {"open"},
                {"visible"},
        };
    }


    @Test(priority = 1, description = "Create Board Validation")
    @Story("Verify created board")
    @Description("Get list of user")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateBoard() {
        Response response = boardSteps.createBoard("Api Board");
        TestData.boardId = response.path("id").toString();

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, description = "Create Board Validation")
    @Story("Verify created label")
    @Description("Get labels on board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetLabelOnBoard() {
        Response response = boardSteps.getLabelsOnBoard();
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

    @Ignore
    @Test(priority = 3)
    @Story("Verify update board")
    @Description("Update board")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateBoard() {
        Response response = boardSteps.updateBoard("Updated board");
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test(priority = 3, dataProvider = "fieldData")
    @Story("Verify  board")
    @Description("Get field on a board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetField(String fieldData) {
        Response response = boardSteps.getField(fieldData);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "filteredCards", dependsOnMethods = "testCreateBoard")
    @Story("Verify  board")
    @Description("Get filtered cards on a board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetFilteredCards(String filteredData) {
        Response response = boardSteps.getFilteredCards(filteredData);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Ignore
    @Test(priority = 3, dependsOnMethods = "testCreateBoard")
    @Story("Verify  board")
    @Description("Create label on a board")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateLabel() {
        Response response = boardSteps.createLabel("Backlog");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4, dependsOnMethods = "testCreateBoard")
    @Story("Verify  board")
    @Description("Create label on a board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetListsOnBoard() {
        Response response = boardSteps.getListsOnBoard();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateBoard")
    @Story("Verify  board")
    @Description("Create list on a board")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateListsOnBoard() {
        Response response = boardSteps.createListOnBoard("Backlog");
        TestData.idList = response.path("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
