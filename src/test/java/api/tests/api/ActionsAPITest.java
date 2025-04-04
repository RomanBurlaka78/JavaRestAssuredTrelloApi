package api.tests.api;

import api.steps.ActionsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

@Epic("API Tests")
@Feature("Actions Validation")
@Owner("Group JavaForwardToOffer")
public class ActionsAPITest {

    private ActionsSteps actionsSteps = new ActionsSteps();

    private String commentsEnpoint = "comments";
    private String bordName = "Board for Actions";
    private String boardId;
    private String toDoListId;
    private String actiontId;
    private String cardId;
    private String actiondIdAfterCreatingACard;
    private final String[] anActionFields = {"/id", "/idMemberCreator", "/data", "/type",
                                            "/date", "/limits", "/display", "/memberCreator"};

    @BeforeClass
    public void setUp(){
        boardId = actionsSteps.createABord(bordName);
        toDoListId = actionsSteps.getIdOfTheFirstListOnABoard(boardId);
        actiontId = actionsSteps.getIdOfTheFirestActionOnABoard(boardId);
    }

//    @AfterClass
//    public void tearDown(){
//        actionsSteps.deleteBoard(boardId);
//    }

    @Test(priority = 0)
    @Story("Actions")
    @Description("Get the action from a board")
    @Severity(SeverityLevel.NORMAL)
    public void getAnAction(){
        Response response = actionsSteps.getAnAction(actiontId);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.jsonPath().getString("id"), actiontId);
    }

    @Test(priority = 1)
    @Story("Actions")
    @Description("Update a comment of the action")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateACommentOfTheAction(){
        String commentForAnAction = "Some comment, that was send from JavaRestAssured project";
        String updatedCommentForAnAction = "Comment has been successfully updated";
        Map<String, String> queryParametersForRequestSpec = new HashMap<>();
        queryParametersForRequestSpec.put("idList",toDoListId);
        queryParametersForRequestSpec.put("name","card for actions");

        cardId = actionsSteps.createACard(queryParametersForRequestSpec).jsonPath().getString("id");

        actiondIdAfterCreatingACard = actionsSteps.addNewComentToACard(cardId, commentForAnAction, commentsEnpoint).jsonPath().getString("id");

        Response response = actionsSteps.updateACommentOfTheAction(actiondIdAfterCreatingACard, updatedCommentForAnAction);
        //Для ассерта надо достать обновлённый комент респонса и сверить с updatedCommentForAnAction
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get a field 'date' of an action")
    @Severity(SeverityLevel.NORMAL)
    public void testGetASpecificFieldOnAnAction(){

        LocalDate currentDateTime = LocalDate.now();
        Response response = actionsSteps.getASpecificFieldOnAnAction(actiondIdAfterCreatingACard, anActionFields[4]);

        String recivedDateOfAnAction = response.jsonPath().getString("_value").substring(0,10);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(recivedDateOfAnAction, currentDateTime.toString());
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get the board to which an action refers to")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheBoardForAnAction(){

        Response response = actionsSteps.getTheBoardForAnAction(actiondIdAfterCreatingACard);
        String boardNameRecivedFromApiCall = response.jsonPath().getString("name");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(boardNameRecivedFromApiCall, bordName);
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get the card the action belong to")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheCardForAnAction(){

        Response response = actionsSteps.getTheCardForAnAction(actiondIdAfterCreatingACard);
        String cardIdRecivedFromApiCall = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(cardIdRecivedFromApiCall, cardId);
    }

    @Test(priority = 3)
    @Story("Actions")
    @Description("Delete an action via id, and make sure it is deleted by trying to get the same action back")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteAnAction(){
        String responseMessageForDeletedAction = "The requested resource was not found.";
        Response response = actionsSteps.deleteAnAction(actiondIdAfterCreatingACard);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(actionsSteps.getAnAction(actiondIdAfterCreatingACard).asPrettyString(), responseMessageForDeletedAction);

    }
}
