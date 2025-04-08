package api.tests.ui;

import api.base.BaseUiTest;
import api.controllers.BoardSteps;
import api.controllers.ui.UiBoardSteps;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.base.*;
import java.io.IOException;

import static api.base.TestData.*;

@Epic("UI Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
@Tag("api")
public class BoardsUiTest extends BaseUiTest {

    private BoardSteps boardSteps = new BoardSteps();
    private UiBoardSteps uiBoardSteps = new UiBoardSteps();


    @Test(priority = 112)
    @Story("Board")
    @Description("Create Board")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("api")
    public void testCreateBoard() throws IOException {
        Response response = boardSteps.createBoard(bordName);
        boardId = response.path("id").toString();

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
        uiBoardSteps.verifyLoginInUI();
    }


    @Test(priority = 300)
    @Story("Bord")
    @Description("Delete board")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteBoard() {
        Response response = boardSteps.deleteABoardFromService(boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        uiBoardSteps.closeBrowserAndDriver();
    }

    @Test(priority = 113)
    @Story("Board")
    @Description("Get board")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoard() {
        Response response = boardSteps.getBoard(TestData.boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().get("id").toString(), TestData.boardId);
        Assert.assertEquals(response.body().jsonPath().get("name").toString(), "Api Board");

        uiBoardSteps.getBoardNameUI();
    }

}
