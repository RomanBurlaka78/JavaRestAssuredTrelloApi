package api.tests.ui;

import api.base.BaseUiTest;
import api.controllers.BoardSteps;
import api.controllers.ui.UiBoardSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.base.*;
import java.io.IOException;

import static api.base.TestData.*;

@Epic("UI Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
public class BoardsUiTest extends BaseUiTest {

    private BoardSteps boardSteps = new BoardSteps();
    private UiBoardSteps uiBoardSteps = new UiBoardSteps();


    @Test(priority = 112, description = "Create Board Validation", groups = "Created_Board_and_List")
    @Story("Board")
    @Description("Create Board")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateBoard() throws IOException {
        Response response = boardSteps.createBoard(bordName);
        boardId = response.path("id").toString();

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
        uiBoardSteps.verifyLoginInUI();
    }


    @Test(priority = 113, dependsOnMethods = "testCreateBoard")
    @Story("Bord")
    @Description("Delete board")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteBoard() {
        Response response = boardSteps.deleteABoardFromService(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        uiBoardSteps.closeBrowserAndDriver();
    }

}
