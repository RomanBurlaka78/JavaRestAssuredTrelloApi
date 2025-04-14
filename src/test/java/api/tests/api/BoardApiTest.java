package api.tests.api;

import api.base.BaseTest;
import api.base.PathParameters;
import api.base.PathParameters.*;

import static api.base.TestData.*;

import api.controllers.BoardSteps;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
/**
 * This is a example of  class comment
 *
 */

@Epic("API Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
@Tag("api")
public class BoardApiTest extends BaseTest {

    private String boardId;
    private String labelId;
    private String listId;
    private String memberId;
    private String boardStarsEnPoint = "/boardStars";

    @Test(priority = 1, groups = "Created_Board_and_List")
    @Story("Bord")
    @Description("Get list of user")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateBoard() {
        /**
         * This is example of  test public method comment
         */
        Response response = getBoardSteps().createBoard(BoardTestData.BOARD_NAME);
        boardId = response.jsonPath().getString("id");

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 111, dependsOnMethods = "testCreateBoard")
    @Story("Bord")
    @Description("Delete board")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteBoard() {
        Response response = getBoardSteps().deleteABoardFromService(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateBoard", groups = "api")
    @Story("Bord")
    @Description("Get board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoard() {
        Response response = getBoardSteps().getBoard(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().get("id").toString(), boardId);
        Assert.assertEquals(response.body().jsonPath().get("name").toString(), BoardTestData.BOARD_NAME);
    }

    @Test(priority = 6, dependsOnMethods = "testCreateBoard")
    @Story("Bord")
    @Description("Update board")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateBoard() {
        String newNameForABoard = "New Api Board";
        Response response = getBoardSteps().updateBoard(boardId, newNameForABoard);

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
        Response response = getBoardSteps().createLabelOnBoard(boardId, nameForALabel, colorOfALabel);
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
        Response response = getBoardSteps().getLabelOnBoard(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateBoard", groups = "Created_Board_and_List")
    @Story("Bord")
    @Description("Create a List on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateListOnBoard() {
        String nameForList = "List test API";
        Response response = getBoardSteps().createListOnBoard(boardId, nameForList);
        listId = response.path("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5, description = "get specified field from a board")
    @Story("Bord")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAFieldOnABord() {
        String fieldName = "/name";
        Response response = getBoardSteps().getAField(boardId, fieldName);

        Assert.assertEquals(response.jsonPath().getString("_value"), BoardTestData.BOARD_NAME);

    }

    @Test(priority = 5, description = "get actions from a board")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetActionsFromABoard() {

        Response response = getBoardSteps().getActions(boardId, ActionsEndPoints.ACTIONS_BASE_PATH);
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(arrayList.size(), 3);

        /* TODO: (This is examle of block comments)
         * Любое действие произведённое на доске считается actions и имеет свои credentials, изначально
         * количество actions=3, но если например добавить карточку то actions будет уже не 3. Actions - это любое
         * действие на доске
         *
         */
    }

    @Test(priority = 5, description = "get actions from a board")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetChecklistsOnABoard() {

        String expectedResult = "[]";
        Response response = getBoardSteps().getChecklists(boardId, PathParameters.CHECKLISTS_BASE_PATH);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed cards from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCardsOnABoard() {

        String expectedResult = "[]";
        Response response = getBoardSteps().getCards(boardId, PathParameters.CARDS_BASE_PATH);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed filtered cards from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetFilteredCardsOnABoard() {

        String expectedResult = "[]";
        Response response = getBoardSteps().getFilteredCards(boardId, PathParameters.CARDS_BASE_PATH, "all");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed custom fields from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCustomFieldsForABoard() {

        String expectedResult = "[]";
        Response response = getBoardSteps().getCustomFieldsForABoard(boardId, PathParameters.CUSTOM_FIELDS_BASE_PATH);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get all existed lists from a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetListsOfABoard() {

        Response response = getBoardSteps().getListsOfABoard(boardId, PathParameters.LISTS_BASE_PATH);
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
        Response response = getBoardSteps().getSpecificListOfABoard(boardId, PathParameters.LISTS_BASE_PATH, nameOfAFilter);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResult);
    }

    @Test(priority = 5, description = "Get members of a bord")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheMembersOfABoard() {

        Response response = getBoardSteps().getTheMembersOfABoard(boardId);
        System.out.println(response.asPrettyString());
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(arrayList.size(), 1);
    }

    @Test(priority = 5, description = "Invite Member to Board via email")
    @Story("Bord")
    @Severity(SeverityLevel.NORMAL)
    public void testInviteMembertoBoardViaEmail() {

        Response response = getBoardSteps().putWithSpecification(boardId, PathParameters.MEMBERS_BASE_PATH);

        List listOfMembers = response.jsonPath().getList("members.id");
        Assert.assertTrue(listOfMembers.size() == 2);
    }

    @Test(priority = 6)
    @Story("Bord")
    @Description("Get boardStars on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoardStarsOnBoard() {
        Response response = getBoardSteps().getBoardStarsOnBoard(boardId, boardStarsEnPoint);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 6, groups = "Created_Board_and_List")
    @Story("Bord")
    @Description("Get memberships on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetMembershipsOnBoard() {
        Response response = getBoardSteps().getMembershipsOnBoard(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 7, groups = "Created_Board_and_List")
    @Story("Bord")
    @Description("Remove member from board")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveMemberFromBoard() {
        Response membersResponse = getBoardSteps().getTheMembersOfABoard(boardId);
        List<String> memberIds = membersResponse.jsonPath().getList("id"); // ИЛИ "members.id"
        System.out.println(memberIds.size());
        String memberIdToRemove = memberIds.get(0);
        Response response = getBoardSteps().removeMemberFromBoard(boardId, memberIdToRemove);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
