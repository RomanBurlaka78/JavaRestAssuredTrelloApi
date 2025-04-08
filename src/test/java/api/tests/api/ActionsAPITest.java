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
    private String actionIdAfterCreatingACard;
    private String idMemberCreator;
    private String idOrganizationThatBelongToAnAction;

    private final String anActionFieldDateResource = "/date";
    private final String anActionBoardResource ="/board";
    private final String anActionCardResource ="/card";
    private final String anActionListResource ="/list";
    private final String anActionMemberCreatorResource ="/memberCreator";
    private final String anActionOrganizationResource ="/organization";
    private final String anActionReactionsResource ="/reactions";


    @BeforeClass
    public void setUp(){
        boardId = actionsSteps.createABord(bordName);
        toDoListId = actionsSteps.getIdOfTheFirstListOnABoard(boardId);
        actiontId = actionsSteps.getIdOfTheFirestActionOnABoard(boardId);
        idMemberCreator = actionsSteps.getAnAction(actiontId).jsonPath().getString("idMemberCreator");
        idOrganizationThatBelongToAnAction = actionsSteps.getAnAction(actiontId).jsonPath().getString("data.organization.id");
    }

    @AfterClass
    public void tearDown(){
        actionsSteps.deleteBoard(boardId);
    }

    @Test(priority = 0)
    @Story("Actions")
    @Description("Get the action from a board")
    @Severity(SeverityLevel.NORMAL)
    public void getAnAction(){
        Response response = actionsSteps.getAnAction(actiontId);
        System.out.println(response.jsonPath().getString("idMemberCreator"));
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

        actionIdAfterCreatingACard = actionsSteps.addNewComentToACard(cardId, commentForAnAction, commentsEnpoint).jsonPath().getString("id");

        Response response = actionsSteps.updateACommentOfTheAction(actionIdAfterCreatingACard, updatedCommentForAnAction);
        //Для ассерта надо достать обновлённый комент респонса и сверить с updatedCommentForAnAction
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get a field 'date' of an action")
    @Severity(SeverityLevel.NORMAL)
    public void testGetASpecificFieldOnAnAction(){

        LocalDate currentDateTime = LocalDate.now();
        Response response = actionsSteps.getTheResourceOfAnAction(actionIdAfterCreatingACard, anActionFieldDateResource);

        String recivedDateOfAnAction = response.jsonPath().getString("_value").substring(0,10);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(recivedDateOfAnAction, currentDateTime.toString());
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get the board to which an action refers to")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheBoardForAnAction(){

        Response response = actionsSteps.getTheResourceOfAnAction(actionIdAfterCreatingACard, anActionBoardResource);
        String boardNameRecivedFromApiCall = response.jsonPath().getString("name");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(boardNameRecivedFromApiCall, bordName);
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get the card the action belong to")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheCardForAnAction(){

        Response response = actionsSteps.getTheResourceOfAnAction(actionIdAfterCreatingACard, anActionCardResource);
        String cardIdRecivedFromApiCall = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(cardIdRecivedFromApiCall, cardId);
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get the list the action belong to")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheListForAnAction(){

        Response response = actionsSteps.getTheResourceOfAnAction(actionIdAfterCreatingACard, anActionListResource);
        String listIdRecivedFromApiCall = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(listIdRecivedFromApiCall, toDoListId);
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get the member creator the action belong to")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheMemberCreatorOfAnAction(){

        Response response = actionsSteps.getTheResourceOfAnAction(actiontId, anActionMemberCreatorResource);
        String memberCreatorIdRecivedFromApiCall = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(memberCreatorIdRecivedFromApiCall, idMemberCreator);
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get the organization that belong to action ")
    @Severity(SeverityLevel.NORMAL)
    public void testGetTheOrganizationOfAnAction(){

        Response response = actionsSteps.getTheResourceOfAnAction(actiontId,anActionOrganizationResource );

        String idOfOrganizationRecivedFromApiCall = response.jsonPath().getString("id");

        Assert.assertEquals(idOfOrganizationRecivedFromApiCall, idOrganizationThatBelongToAnAction);
    }

    @Test(priority = 2)
    @Story("Actions")
    @Description("Get the reactions related to the specific action ")
    @Severity(SeverityLevel.NORMAL)
    public void testGetActions_Reactions(){

        Response response = actionsSteps.getTheResourceOfAnAction(actiontId,anActionReactionsResource );

        System.out.println(response.body().toString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().asString(), "[]");
    }

    @Test(priority = 3)
    @Story("Actions")
    @Description("Create reaction for specific action ")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateReactionForAction(){

        String expectedEmojiName = "GRINNING FACE";
        Response response = actionsSteps.createReactionForSpecificAction(actionIdAfterCreatingACard, anActionReactionsResource);
        String actualEmojiName = response.jsonPath().getString("emoji.name");

        Assert.assertEquals(actualEmojiName, expectedEmojiName);
    }

    @Test(priority = 4)
    @Story("Actions")
    @Description("Delete an action via id, and make sure it is deleted by trying to get the same action back")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteAnAction(){
        String responseMessageForDeletedAction = "The requested resource was not found.";
        Response response = actionsSteps.deleteAnAction(actionIdAfterCreatingACard);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(actionsSteps.getAnAction(actionIdAfterCreatingACard).asPrettyString(), responseMessageForDeletedAction);

    }
}
