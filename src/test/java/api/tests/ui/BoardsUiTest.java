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

@Epic("API Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
public class BoardsUiTest extends BaseUiTest {

    private BoardSteps boardSteps = new BoardSteps();
    private UiBoardSteps uiBoardSteps = new UiBoardSteps();


    @Test(priority = 112, description = "Create Board Validation", groups = "Created_Board_and_List")
    @Story("Verify created board")
    @Description("Get list of user")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateBoard() throws IOException {
        Response response = boardSteps.createBoard(bordName);
        boardId = response.path("id").toString();

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
        uiBoardSteps.verifyLoginInUI();

    }


}
