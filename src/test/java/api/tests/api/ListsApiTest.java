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
    private String boardId;
    private String toDoListId;
    private String newCreatedListId;
    private String newNameForTheList = "List with Updated name";


    @BeforeClass
    public void setUp(){
        boardId = listsSteps.createABord(bordName);
        toDoListId = listsSteps.getTheFirstListsId(boardId);
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
        newCreatedListId = listsSteps.getTheFirstListsId(boardId);

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

        listsSteps.createACard(toDoListId);

        Response response = listsSteps.archiveAllCardOnTheList(toDoListId);

        Assert.assertEquals(response.getStatusCode(), 200);
        //Проверить, что кард был заархивирован можно только в UI -> Menu -> Archived items. Но для этого не удаляй доску.
    }

    @Test(priority = 2)
    @Story("lists")
    @Description("Move all cards from one list to another")
    @Severity(SeverityLevel.CRITICAL)
    public void testMoveAllCardsFromOneListToAnother() {

        listsSteps.createACard(newCreatedListId);
        listsSteps.createACard(newCreatedListId);

        Response response = listsSteps.moveAllCardsFromOneListToAnother(newCreatedListId, boardId, toDoListId);

        Assert.assertEquals(response.getStatusCode(), 200);
        //можно добавить ассерт из теста "Get Cards in a List" который в данный момент не реализован. (последний в API разделе)
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
}
