package api.tests;

import api.base.BaseTest;
import api.base.TestData;
import api.controllers.BoardSteps;
import api.controllers.ui.UiBoardSteps;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.*;

@Epic("API Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
public class BoardApiTest extends BaseTest {
    private BoardSteps boardSteps = new BoardSteps();
    private UiBoardSteps uiBoardSteps = new UiBoardSteps();


    @Test(priority = 1, description = "Create Board Validation", groups = "Created_Board_and_List")
    @Story("Verify created board")
    @Description("Get list of user")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateBoard() throws IOException, InterruptedException {
        Response response = boardSteps.createBoard(TestData.bordName);
        TestData.boardId =  response.path("id").toString();

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
        uiBoardSteps.verifyLoginInUI();

    }


    @Test(priority = 111, dependsOnMethods = "testCreateBoard")
    @Story("Verify delete board")
    @Description("Delete board")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteBoard() throws InterruptedException {
        Response response = boardSteps.deleteBoardStep(TestData.boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        uiBoardSteps.closeBrowserAndDriver();
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

        uiBoardSteps.getBoardNameUI();
    }

    @Test(priority = 14, dependsOnMethods = "testCreateBoard")
    @Story("Verify update board")
    @Description("Update board")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateBoard() {
        Response response = boardSteps.updateBoard(TestData.boardId, "New Api Board");

        Assert.assertEquals(response.body().jsonPath().get("id").toString(), TestData.boardId);
        Assert.assertEquals(response.body().jsonPath().get("name").toString(), "New Api Board");

        uiBoardSteps.getUINewBoardName();
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

    @Test(priority = 3, dependsOnMethods = "testCreateBoard", groups = "Created_Board_and_List")
    @Story("Verify List on a Board")
    @Description("Create a List on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateListOnBoard() {
        Response response = boardSteps.createListOnBoard(TestData.boardId, "List test API");
        TestData.idList = response.path("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5, description = "get specified field from a board")
    @Story("Bord")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAFieldOnABord() {
        Response response = boardSteps.getAField(TestData.boardId, "name");

        Assert.assertEquals(response.jsonPath().getString("_value"), TestData.bordName);

    }

    @Test(priority = 5, description = "get actions from a board")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetActionsFromABoard() {

        Response response = boardSteps.getActions(TestData.boardId, "/actions");
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(arrayList.size(), 3);

        //Любое действие произведённое на доске считается actions и имеет свои cridentials, изначально
        //количество actions=3, но если например добавить карточку то actions будет уже не 3. Actions - это любое
        //действие на доске
    }

    @Test(priority = 5, description = "get actions from a board")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetChecklistsOnABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getChecklists(TestData.boardId, "/checklists");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed cards from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCardsOnABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getCards(TestData.boardId, "/cards");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed filtered cards from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetFilteredCardsOnABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getFilteredCards(TestData.boardId, "/cards/", "all");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed custom fields from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCustomFieldsForABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getCustomFieldsForABoard(TestData.boardId, "/customFields");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed lists from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetListsOfABoard() {

        Response response = boardSteps.getListsOfABoard(TestData.boardId, "/lists");
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(arrayList.size(), 4);
    }

    @Test(priority = 5, description = "Get closed lists from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetClosedListOfABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getSpecificListOfABoard(TestData.boardId, "/lists", "/closed");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get members of a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheMembersOfABoard() {

        Response response = boardSteps.getMembersOfABoard(TestData.boardId, "/members");

        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(arrayList.size(), 1);
    }

    @Test(priority = 12, dependsOnMethods = "testCreateBoard")
    @Story("Verify get boardStars on a board")
    @Description("Get boardStars on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoardStarsOnBoard() {
        Response response = boardSteps.getBoardStarsOnBoard(TestData.boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 9, dependsOnMethods = "testCreateBoard", groups = "Created_Board_and_List")
    @Story("Verify get memberships on a board")
    @Description("Get memberships on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetMembershipsOnBoard() {
        Response response = boardSteps.getMembershipsOnBoard(TestData.boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test(priority = 9, dependsOnMethods = "testCreateBoard", groups = "Created_Board_and_List")
    @Story("Verify add member from board")
    @Description("Add member from board")
    @Severity(SeverityLevel.NORMAL)
    public void testAddMemberFromBoard() {
        Response response = boardSteps.addMemberToBoard(TestData.boardId, "user@gmail.com", "normal");
        TestData.memberId = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 15, dependsOnMethods = "testAddMemberFromBoard", groups = "Created_Board_and_List")
    @Story("Verify remove member from board")
    @Description("Remove member from board")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveMemberFromBoard() {
        Response membersResponse = boardSteps.getMembersOfABoard(TestData.boardId, "/members");
        List<String> memberIds = membersResponse.jsonPath().getList("id"); // ИЛИ "members.id"
        String memberIdToRemove = memberIds.get(0);
        Response response = boardSteps.removeMemberFromBoard(TestData.boardId, memberIdToRemove);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
