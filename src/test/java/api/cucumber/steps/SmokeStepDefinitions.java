package api.cucumber.steps;

import api.base.PathParameters;
import api.base.TestData;
import api.controllers.BoardSteps;
import api.controllers.ListsSteps;
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
    ListsSteps listsSteps = new ListsSteps();
    private String boardId;
    private Response responseBord;
    private  Response responseList;
    private String  newCreatedListId;

//    @board
    @Given("I have valid Trello API credentials")
    public void i_have_valid_trello_api_credentials() {
        logger.info("I have valid Trello API credentials");
    }



    @When("I create a new board with name {string}")
    public void i_create_a_new_board_with_name(String string) {
        responseBord = boardSteps.createBoard(string);
        boardId = responseBord.jsonPath().getString("id");

    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer status) {
        Assert.assertEquals(responseBord.getStatusCode(), 200);
    }

    @Then("the response should contain a valid board id")
    public void the_response_should_contain_a_valid_board_id() {
        Assert.assertTrue(!responseBord.jsonPath().getString("id").isEmpty());
    }

    @Then("I delete the created board")
    public void i_delete_the_created_board() {
        responseBord = boardSteps.deleteABoardFromService(boardId);
    }
   // @list

    @When("I create a list with name {string} on the board")
    public void i_create_a_list_with_name_on_the_board(String listName) {
        responseList = listsSteps.createList(listName, boardId);

    }
    @Then("the response should contain a valid list id")
    public void the_response_should_contain_a_valid_list_id() {


    }
    @Then("the list name should be {string}")
    public void the_list_name_should_be(String listName) {
        Assert.assertEquals(responseList.getStatusCode(), 200);
        Assert.assertEquals(responseList.path("name"), listName );

    }






}
