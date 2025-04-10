package api.tests.api;

import api.controllers.ChecklistsSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ChecklistsAPITest {

    private final ChecklistsSteps checklistsSteps = new ChecklistsSteps();

    private String toDoListId;
    private final String bordName = "Board for Checklists";
    private final String nameOfAChecklistCreated = "First Checklist";
    private String boardId;
    private String cardId;
    private String checklistId;
    private String checkItemId;

    @BeforeClass
    public void setUp(){
        boardId = checklistsSteps.createABord(bordName);
        toDoListId = checklistsSteps.getIdOfTheFirstListOnABoard(boardId);
        cardId = checklistsSteps.
                createACard(new HashMap<>()
                {{put("idList",toDoListId);put("name", "card");}})
                .jsonPath().getString("id");
    }

    @AfterClass
    public void tearDown(){
        checklistsSteps.deleteBoard(boardId);
    }

    @Test(priority = 0)
    @Story("Checklists")
    @Description("Create a checklist on a card")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateAChecklist(){

        Response response = checklistsSteps.createAChecklist(cardId, nameOfAChecklistCreated);

        String actualNameOfChecklistReceived = response.jsonPath().getString("name");
        checklistId = response.jsonPath().getString("id");

        Assert.assertEquals(actualNameOfChecklistReceived, nameOfAChecklistCreated);
    }

    @Test(priority = 1)
    @Story("Checklists")
    @Description("Get a checklist on a card")
    @Severity(SeverityLevel.NORMAL)
    public void testGetAChecklist(){

        Response response = checklistsSteps.getCheckList(checklistId);
        String actualIdOfChecklistReceived = response.jsonPath().getString("id");

        Assert.assertEquals(actualIdOfChecklistReceived, checklistId);
    }

    @Test(priority = 2)
    @Story("Checklists")
    @Description("Update a name of a checklist")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateAChecklist(){

        String nameOfAFieldToBeUpdated = "name";
        String valueForAFieldToBeUpdated = "New name for checklist";

        Response response = checklistsSteps.updateAFieldOfCheckList(checklistId, nameOfAFieldToBeUpdated, valueForAFieldToBeUpdated);

        String actualNameOfChecklistReceived = response.jsonPath().getString("name");

        Assert.assertEquals(actualNameOfChecklistReceived, valueForAFieldToBeUpdated);
    }

    @Test(priority = 3)
    @Story("Checklists")
    @Description("Get a 'pos' field on a checklist")
    @Severity(SeverityLevel.NORMAL)
    public void testGetFieldOnAChecklist(){

        String expectedPosOfAChecklist = "16384";
        String fieldToGetBackFromTheChecklist = "/pos";

        Response response = checklistsSteps.getFieldOnAChecklist(checklistId, fieldToGetBackFromTheChecklist);
        String actualPosOfAChecklistReceivedBack = response.jsonPath().getString("_value");

        Assert.assertEquals(actualPosOfAChecklistReceivedBack, expectedPosOfAChecklist);

    }

    @Test(priority = 3)
    @Story("Checklists")
    @Description("Get a board checklist is on")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheBoardTheChecklistIsOn(){

        Response response = checklistsSteps.getTheBoardTheChecklistIsOn(checklistId);
        String actualIdOfABoardReceived = response.jsonPath().getString("id");

        Assert.assertEquals(actualIdOfABoardReceived, boardId);
    }

    @Test(priority = 3)
    @Story("Checklists")
    @Description("Get the card checklist is on")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheCardAChecklistIsOn(){

        Response response = checklistsSteps.getTheCardAChecklistIsOn(checklistId);
        String actualIdOfACardReceived = response.jsonPath().getString("id");
        actualIdOfACardReceived = actualIdOfACardReceived.substring(1,actualIdOfACardReceived.length()-1);  //have to remove square brackets

        Assert.assertEquals(actualIdOfACardReceived, cardId);
    }

    @Test(priority = 3)
    @Story("Checklists")
    @Description("Get all checkItems presented on a checklist")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCheckitemsOnAChecklist(){

        String emptyString = "[]";

        Response response = checklistsSteps.getCheckitemsOnAChecklist(checklistId);
        String adtualCheckItemsOnAChecklist = response.body().asString();

        Assert.assertEquals(adtualCheckItemsOnAChecklist, emptyString);
    }

    @Test(priority = 4)
    @Story("Checklists")
    @Description("Create new checkItem on a checklist")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateCheckitemOnChecklist(){
        String nameForNewCheckItem = "Mark";

        Response response = checklistsSteps.createCheckitemOnChecklist(checklistId, nameForNewCheckItem);
        String actualNameOfNewCheckItem = response.jsonPath().getString("name");
        checkItemId = response.jsonPath().getString("id");

        Assert.assertEquals(actualNameOfNewCheckItem, nameForNewCheckItem);
    }

    @Test(priority = 5)
    @Story("Checklists")
    @Description("Get specific checkItem on a checklist")
    @Severity(SeverityLevel.NORMAL)
    public void testGetACheckitemOnAChecklist(){

        Response response = checklistsSteps.getACheckitemOnAChecklist(checklistId, checkItemId);
        String actualCheckItemIdReceived = response.jsonPath().getString("id");

        Assert.assertEquals(actualCheckItemIdReceived, checkItemId);
    }

    @Test(priority = 6)
    @Story("Checklists")
    @Description("Delete specific checkItem from checklist")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteCheckitemFromChecklist(){

        String expectedStringResult = "[:]";

        Response response = checklistsSteps.deleteCheckitemFromChecklist(checklistId, checkItemId);
        String emptyBody = response.jsonPath().getString("limits");

        Assert.assertEquals(emptyBody, expectedStringResult);
    }

    @Test(priority = 7)
    @Story("Checklists")
    @Description("Delete specific checkItem from checklist")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteAChecklist(){

        String expectedStringResult = "[:]";

        Response response = checklistsSteps.deleteAChecklist(checklistId);
        String emptyBody = response.jsonPath().getString("limits");

        Assert.assertEquals(emptyBody, expectedStringResult);
    }

}
