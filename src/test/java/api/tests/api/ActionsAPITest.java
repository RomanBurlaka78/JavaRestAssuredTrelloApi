package api.tests.api;

import api.steps.ActionsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Epic("API Tests")
@Feature("Actions Validation")
@Owner("Group JavaForwardToOffer")
public class ActionsAPITest {

    private ActionsSteps actionsSteps = new ActionsSteps();

    private String bordName = "Board for Actions";
    private String boardId;
    private String toDoListId;
    private String actiontId;
    private String cardId;
    private String actiondIdAfterCreatingACard;

    @BeforeClass
    public void setUp(){
        boardId = actionsSteps.createABord(bordName);
        toDoListId = actionsSteps.getTheFirstListsId(boardId);
        actiontId = actionsSteps.getTheFirestActionOnABoard(boardId);
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
    @Description("Get the action from a board")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateACommentOfTheAction(){
        String commentForAnAction = "Some comment, that was send from JavaRestAssured project";
        String updatedCommentForAnAction = "Comment has been successfully updated";
        Map<String, String> queryParametersForRequestSpec = new HashMap<>();
        queryParametersForRequestSpec.put("idList",toDoListId);
        queryParametersForRequestSpec.put("name","card for actions");

        cardId = actionsSteps.createACard(queryParametersForRequestSpec).jsonPath().getString("id");

        actiondIdAfterCreatingACard = actionsSteps.addNewComentToCard(cardId, commentForAnAction).jsonPath().getString("id");

        Response response = actionsSteps.updateACommentOfTheAction(actiondIdAfterCreatingACard, updatedCommentForAnAction);
        System.out.println(response.asPrettyString());
    }


}
