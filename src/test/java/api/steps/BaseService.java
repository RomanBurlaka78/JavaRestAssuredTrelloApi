package api.steps;

import api.base.PathParameters;
import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

public abstract class BaseService {

    protected static final Specification specification = new Specification();
    protected RequestSpecification requestSpecification;
    protected ApiClient apiClient = ApiClient.getInstance();

    {
        initRequestSpecification();
    }

    @Step("Create a board with a name {'boardName'}")
    public String createABord(String boardName){

        requestSpecification.queryParam("name", boardName);

        Response response = apiClient.post(PathParameters.BOARD_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response.jsonPath().getString("id");
    }

    @Step("Delete a board with id = {boardId}")
    public void deleteBoard(String boardId) {

        apiClient.delete(PathParameters.BOARD_BASE_PATH + boardId, requestSpecification);
    }

    @Step("Get id of the first list on a board")
    public String getIdOfTheFirstListOnABoard(String boardId){

        Response resp = apiClient.get(PathParameters.BOARD_BASE_PATH + boardId + PathParameters.LISTS_BASE_PATH, requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
        initRequestSpecification();
        return (String) arrayList.get(0);
    }

    @Step("Get id of the first action on a board with id = {boardId}")
    public String getIdOfTheFirestActionOnABoard(String boardId) {
        Response response = apiClient.get(PathParameters.BOARD_BASE_PATH + boardId + PathParameters.ACTIONS_BASE_PATH, requestSpecification);

        List list = response.jsonPath().getList("id");
        return list.get(0).toString();
    }

    protected void initRequestSpecification(){
        requestSpecification = RestAssured.given().spec(specification.installRequest());

    }

    @Step("Create a card for list with id = {listId}")
    public Response createACard(Map queryParamMap) {
        requestSpecification.queryParams(queryParamMap);
        Response response = apiClient.post(PathParameters.CARDS_BASE_PATH, requestSpecification);

        initRequestSpecification();
        return response;
    }

    @Step("Add a comment {'commentForAnAction'} to a card with id ={cardId}")
    public Response addNewComentToACard(String cardId, String commentForAnAction, String commentsEnpoint) {
        requestSpecification.queryParams("text", commentForAnAction);
        Response response = apiClient.post(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.ACTIONS_BASE_PATH + commentsEnpoint, requestSpecification);
        initRequestSpecification();
        return response;
    }


}
