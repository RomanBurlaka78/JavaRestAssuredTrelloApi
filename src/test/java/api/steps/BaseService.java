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

    public String createABord(String boardName){

        requestSpecification.queryParam("name", boardName);

        Response response = apiClient.post(PathParameters.BOARD_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response.jsonPath().getString("id");
    }

    public void deleteBoard(String boardId) {

        apiClient.delete(PathParameters.BOARD_BASE_PATH + boardId, requestSpecification);

    }

    public String getTheFirstListsId(String boardId){

        Response resp = apiClient.get(PathParameters.BOARD_BASE_PATH + boardId + PathParameters.LISTS_BASE_PATH, requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
        initRequestSpecification();
        return (String) arrayList.get(0);
    }

    public String getTheFirestActionOnABoard(String boardId) {
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


}
