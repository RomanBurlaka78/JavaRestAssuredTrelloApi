package api.tests.api;

import api.steps.ActionsSteps;
import api.steps.ListsSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Epic("API Tests")
@Feature("Actions Validation")
@Owner("Group JavaForwardToOffer")
public class ActionsAPITest {

    private ActionsSteps actionsSteps = new ActionsSteps();
    private String bordName = "Board for Actions";
    private String boardId;
    private String toDoListId;
    private String newCreatedListId;
    private final String newNameForTheList = "List with Updated name";

    @BeforeClass
    public void setUp(){
        boardId = actionsSteps.createABord(bordName);
    }

    @AfterClass
    public void tearDown(){
        actionsSteps.deleteBoard(boardId);
    }


}
