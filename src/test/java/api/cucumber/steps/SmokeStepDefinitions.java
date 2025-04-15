package api.cucumber.steps;

import api.base.TestData;
import api.controllers.BoardSteps;
import api.utils.ApiClient;
import api.utils.LoggerUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class SmokeStepDefinitions extends BoardSteps {
    private static final Logger logger = LogManager.getLogger(SmokeStepDefinitions.class);

    BoardSteps boardSteps = new BoardSteps();

    @Given("I have valid Trello API credentials")
    public void i_have_valid_trello_api_credentials() {
        logger.info("I have valid Trello API credentials");
    }
    @And("I set up the API request with base url {string}")
    public void i_set_up_the_api_request_with_base_url(String string) {
        logger.info("I set up the API request with base url {string}");
    }

    @When("I create a new board with name {string}")
    public void i_create_a_new_board_with_name(String string) {
        Response response = boardSteps.createBoard(TestData.BoardTestData.BOARD_NAME);
        TestData.BoardTestData.boardId = response.jsonPath().getString("id");

        Assert.assertTrue(!response.jsonPath().getString("id").isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Then("I delete the created board")
    public void i_delete_the_created_board() {
        Response response = boardSteps.deleteABoardFromService(TestData.BoardTestData.boardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
