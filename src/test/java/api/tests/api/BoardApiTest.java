package api.tests.api;

import api.base.BaseTest;
import api.base.PathParameters;
import api.steps.BoardSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

@Epic("API Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
public class BoardApiTest extends BaseTest {

    private BoardSteps boardSteps = new BoardSteps();
    private String bordName = "Api Board";
    private String boardId;
    private String labelId;
    private String listId;
    private String memberId;
    private String boardStarsEnPoint = "/boardStars";

    @Test(priority = 1, description = "Create Board Validation", groups = "Created_Board_and_List")
    @Story("Bord")
    @Description("Get list of user")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateBoard() {
        Response response = boardSteps.createBoard(bordName);
        boardId =  response.jsonPath().getString("id");

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 111, dependsOnMethods = "testCreateBoard")
    @Story("Bord")
    @Description("Delete board")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteBoard() {
        Response response = boardSteps.deleteABoardFromService(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateBoard")
    @Story("Bord")
    @Description("Get board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoard() {
        Response response = boardSteps.getBoard(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().get("id").toString(), boardId);
        Assert.assertEquals(response.body().jsonPath().get("name").toString(), bordName);
    }

    @Test(priority = 6, dependsOnMethods = "testCreateBoard")
    @Story("Bord")
    @Description("Update board")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateBoard() {
        String newNameForABoard = "New Api Board";
        Response response = boardSteps.updateBoard(boardId, newNameForABoard);

        Assert.assertEquals(response.body().jsonPath().get("id").toString(), boardId);
        Assert.assertEquals(response.body().jsonPath().get("name").toString(), newNameForABoard);
    }

    @Test(priority = 4, dependsOnMethods = "testCreateBoard")
    @Story("Bord")
    @Description("Create a Label on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateLabelOnBoard() {
        String nameForALabel = "Api_Label";
        String colorOfALabel = "red";
        Response response = boardSteps.createLabelOnBoard(boardId, nameForALabel, colorOfALabel);
        labelId = response.path("id").toString();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().path("name"), nameForALabel);
        Assert.assertEquals(response.body().path("color"), colorOfALabel);
    }

    @Test(priority = 5, dependsOnMethods = "testCreateLabelOnBoard")
    @Story("Bord")
    @Description("Get Labels on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetLabelsOnBoard() {
        Response response = boardSteps.getLabelOnBoard(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateBoard", groups = "Created_Board_and_List")
    @Story("Bord")
    @Description("Create a List on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateListOnBoard() {
        String nameForList = "List test API";
        Response response = boardSteps.createListOnBoard(boardId, nameForList);
        listId = response.path("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5,description = "get specified field from a board")
    @Story("Bord")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAFieldOnABord() {
        String fieldName = "/name";
        Response response = boardSteps.getAField( boardId, fieldName);

        Assert.assertEquals(response.jsonPath().getString("_value"), bordName);

    }

    @Test(priority = 5, description = "get actions from a board")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetActionsFromABoard() {

        Response response = boardSteps.getActions( boardId,  PathParameters.ACTIONS_BASE_PATH);
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(arrayList.size(), 3);

        //Любое действие произведённое на доске щитается actions и имеет свои cridentials, изначально
        //количество actions=3, но если например добавить карточку то actions будет уже не 3. Actions - это любое
        //действие на доске
    }

    @Test(priority = 5, description = "get actions from a board")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetChecklistsOnABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getChecklists(boardId, PathParameters.CHECKLISTS_BASE_PATH);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed cards from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCardsOnABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getCards(boardId, PathParameters.CARDS_BASE_PATH);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed filtered cards from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetFilteredCardsOnABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getFilteredCards(boardId, PathParameters.CARDS_BASE_PATH, "all");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed custom fields from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCustomFieldsForABoard() {

        String expectedResult = "[]";
        Response response = boardSteps.getCustomFieldsForABoard(boardId, PathParameters.CUSTOM_FIELDS_BASE_PATH);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed lists from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetListsOfABoard() {

        Response response = boardSteps.getListsOfABoard(boardId, PathParameters.LISTS_BASE_PATH);
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(arrayList.size(), 4);
    }

    @Test(priority = 5, description = "Get closed lists from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetClosedListOfABoard() {

        String expectedResult = "[]";
        String nameOfAFilter = "closed";
        Response response = boardSteps.getSpecificListOfABoard(boardId, PathParameters.LISTS_BASE_PATH, nameOfAFilter);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get members of a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheMembersOfABoard() {

        Response response = boardSteps.getTheMembersOfABoard(boardId);
        System.out.println(response.asPrettyString());
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(arrayList.size(), 1);
    }

    @Test(priority = 5,description = "Invite Member to Board via email")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testInviteMembertoBoardViaEmail() {

        Response response = boardSteps.putWithSpecification(boardId, PathParameters.MEMBERS_BASE_PATH);

        List listOfMembers = response.jsonPath().getList("members.id");
        Assert.assertTrue(listOfMembers.size()==2);
    }

    @Test(priority = 6)
    @Story("Bord")
    @Description("Get boardStars on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoardStarsOnBoard() {
        Response response = boardSteps.getBoardStarsOnBoard(boardId, boardStarsEnPoint);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 6, groups = "Created_Board_and_List")
    @Story("Bord")
    @Description("Get memberships on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetMembershipsOnBoard() {
        Response response = boardSteps.getMembershipsOnBoard(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 7, groups = "Created_Board_and_List")
    @Story("Bord")
    @Description("Remove member from board")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveMemberFromBoard() {
        Response membersResponse = boardSteps.getTheMembersOfABoard(boardId);
        List<String> memberIds = membersResponse.jsonPath().getList("id"); // ИЛИ "members.id"
        System.out.println(memberIds.size());
        String memberIdToRemove = memberIds.get(0);
        Response response = boardSteps.removeMemberFromBoard(boardId, memberIdToRemove);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
