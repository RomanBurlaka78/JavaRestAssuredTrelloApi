package api.tests.api;

import api.controllers.ListsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Epic("API Tests")
@Feature("Lists Validation")
@Owner("Group JavaForwardToOffer")
public class ListsApiTest {

    private final ListsSteps listsSteps = new ListsSteps();
    private String bordName = "Board for lists";
    private String boardId;
    private String toDoListId;
    private String newCreatedListId;
    private final String newNameForTheList = "List with Updated name";

    @BeforeClass
    public void setUp(){
        boardId = listsSteps.createABord(bordName);
        toDoListId = listsSteps.getIdOfTheFirstListOnABoard(boardId);
    }

    @AfterClass
    public void tearDown(){
        listsSteps.deleteBoard(boardId);
    }

    @Test(priority = 0)
    @Story("lists")
    @Description("Create a new List on a Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewList() {

        String nameOfTheList = "List from API";
        Response response = listsSteps.createList(nameOfTheList, boardId);
        newCreatedListId = listsSteps.getIdOfTheFirstListOnABoard(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("name"), nameOfTheList);
    }

    @Test(priority = 1)
    @Story("lists")
    @Description("Update a name of the list")
    @Severity(SeverityLevel.CRITICAL)
    public void tesUpdateANameForToDoList() {

        Response response = listsSteps.updateANameForList(toDoListId, newNameForTheList);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), newNameForTheList);
    }

    @Test(priority = 2)
    @Story("lists")
    @Description("Get a list from a board with updated name (before 'ToDO' now it is 'Updated name for the list')")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAList() {

        Response response = listsSteps.getAList(toDoListId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), newNameForTheList);
    }

    @Test(priority = 2)
    @Story("lists")
    @Description("Create a new List on a Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testArchiveAllCardsOnTheList() {

        Map <String, String> queryParametersForRequestSpec = new HashMap<>();
        queryParametersForRequestSpec.put("idList", toDoListId);
        queryParametersForRequestSpec.put("name", "nameForCard");

        listsSteps.createACard(queryParametersForRequestSpec);

        Response response = listsSteps.archiveAllCardOnTheList(toDoListId);

        Assert.assertEquals(response.getStatusCode(), 200);
        //Проверить, что кард был заархивирован можно только в UI -> Menu -> Archived items. Но для этого не удаляй доску.
    }

    @Test(priority = 2)
    @Story("lists")
    @Description("Move all cards from one list to another")
    @Severity(SeverityLevel.CRITICAL)
    public void testMoveAllCardsFromOneListToAnother() {
        Map <String, String> queryParametersForRequestSpec = new HashMap<>();
        queryParametersForRequestSpec.put("idList", newCreatedListId);
        queryParametersForRequestSpec.put("name", "nameForCard");

        listsSteps.createACard(queryParametersForRequestSpec);
        listsSteps.createACard(queryParametersForRequestSpec);

        Response response = listsSteps.moveAllCardsFromOneListToAnother(newCreatedListId, boardId, toDoListId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    @Story("lists")
    @Description("Archive a list on a board")
    @Severity(SeverityLevel.CRITICAL)
    public void testArchiveAList() {

        Response response = listsSteps.archiveAList(toDoListId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    @Story("lists")
    @Description("Unarchived a list on a board")
    @Severity(SeverityLevel.CRITICAL)
    public void testUnArchiveAList() {

        Response response = listsSteps.unArchiveAList(toDoListId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    @Story("lists")
    @Description("Get all cards available on a list")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetCardsInAList() {

        Response response = listsSteps.getCardsOnAList(toDoListId);

        System.out.println(response.asPrettyString());
        List arrayList = response.jsonPath().getList("id");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(arrayList.size(),2);
    }

    @Test(priority = 5)
    @Story("lists")
    @Description("Move list from one board to another")
    @Severity(SeverityLevel.CRITICAL)
    public void testMoveListFromOneBoardToAnother() {
        String nameForSecondBoard = "Board_for_moving_lists";
        String idOfTheSecondBoard = listsSteps.createABord(nameForSecondBoard);

        Response response = listsSteps.moveListFromOneBoardToAnother(toDoListId, idOfTheSecondBoard);

        Assert.assertEquals(response.getStatusCode(), 200);
        //Assert can be done with -> Get the Board a List is on
        listsSteps.deleteBoard(idOfTheSecondBoard);
    }

    @Test(priority = 5)
    @Story("lists")
    @Description("Update subscribed field of a list")
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdateASubscribedFieldOfAList() {
        boolean subscribeValue = true;

        Response response = listsSteps.updateSubscribedFieldOfAList(newCreatedListId, subscribeValue);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 6)
    @Story("lists")
    @Description("Update subscribed field of a list")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetActionsOfAList() {

        Response response = listsSteps.getActionsofAList(newCreatedListId);
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(arrayList.size(), 3);

    }

    @Test(priority = 6)
    @Story("lists")
    @Description("Get the Board a List is on")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetABoardAListIsOn() {

        Response response = listsSteps.getABoardAListIsOn(newCreatedListId);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("id"), boardId);

    }


}
