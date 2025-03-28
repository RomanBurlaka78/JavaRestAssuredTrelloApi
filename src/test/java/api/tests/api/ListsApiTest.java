package api.tests.api;

import api.steps.ListsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("API Tests")
@Feature("Lists Validation")
@Owner("Group JavaForwardToOffer")
public class ListsApiTest {

    private ListsSteps listsSteps = new ListsSteps();
    private String bordName = "Board for lists";
    private String boardId;
    private String listId;
    private String newNameForTheList = "Updated name for the list";


    @BeforeClass
    public void setUp(){
        boardId = listsSteps.createABord(bordName);
        listId = listsSteps.getListsId(boardId);
    }

//    @AfterClass
//    public void tearDown(){
//        listsSteps.deleteBoard(boardId);
//    }

    @Test(priority = 0)
    @Story("lists")
    @Description("Create a new List on a Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewList() {

        String nameOfTheList = "List from API";
        Response response = listsSteps.createList(nameOfTheList, boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("name"), nameOfTheList);
    }

    @Test(priority = 1)
    @Story("lists")
    @Description("Update a name of the list")
    @Severity(SeverityLevel.CRITICAL)
    public void tesUpdateANameForList() {

        Response response = listsSteps.updateANameForList(listId, newNameForTheList);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), newNameForTheList);
    }

    @Test(priority = 2)
    @Story("lists")
    @Description("Create a new List on a Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAList() {

        Response response = listsSteps.getAList(listId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), newNameForTheList);
    }

    @Test(priority = 2)
    @Story("lists")
    @Description("Create a new List on a Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testArchiveAllCardOnTheList() {

        listsSteps.createACard(listId);

        Response response = listsSteps.archiveACardOnTheList(listId);

        Assert.assertEquals(response.getStatusCode(), 200);
        //Проверить, что кард был заархивирован можно только в UI -> Menu -> Archived items. Но для этого не удаляй доску.
    }

}
