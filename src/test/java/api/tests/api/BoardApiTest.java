package api.tests.api;

import api.base.BaseTest;
import api.base.TestData;
//import api.steps.BoardSteps;
import api.steps.BoardSteps;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


@Epic("API Tests")
@Feature("Board Validation")
@Owner("Group JavaForwardToOffer")
public class BoardApiTest extends BaseTest {

    private BoardSteps boardSteps = new BoardSteps();




    @Test(priority = 1, description = "Create Board Validation")
    @Story("Verify created board")
    @Description("Get list of user")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateBoard() {
        Response response = boardSteps.createBoard("Api Board");
        TestData.boardId =  response.path("id").toString();

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);

    }


    @Test(priority = 111, dependsOnMethods = "testCreateBoard")
    @Story("Verify delete board")
    @Description("Delete board")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteBoard() {
        Response response = boardSteps.deleteBoardStep(TestData.boardId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
