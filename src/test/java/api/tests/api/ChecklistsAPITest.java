package api.tests.api;

import api.steps.ChecklistsSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
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
    public void CreateAChecklist(){

        String actualNameofChecklistReceived =
                checklistsSteps.createAChecklist(cardId, nameOfAChecklistCreated).jsonPath().getString("name");

        Assert.assertEquals(actualNameofChecklistReceived, nameOfAChecklistCreated);
    }

}
