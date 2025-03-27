package api.steps;

import api.base.ApiPathData;
import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ListsSteps {

    private final Specification specification = new Specification();
    private RequestSpecification requestSpecification;
    private ApiClient apiClient = ApiClient.getInstance();
    private String temporaryBoardId;
    private String tepmoraryListId;


    {
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }

    public void createABord(String boardName){

        requestSpecification.queryParam("name", boardName);

        Response response = apiClient.post(ApiPathData.BOARD_BASE_PATH, requestSpecification);
        temporaryBoardId =  response.jsonPath().getString("id");
        requestSpecification = RestAssured.given().spec(specification.installRequest());


        Response resp = apiClient.get(ApiPathData.BOARD_BASE_PATH + temporaryBoardId+ "/lists", requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
        tepmoraryListId = (String) arrayList.get(0);
        requestSpecification = given(specification.installRequest());

    }

    public void deleteBoard() {
        apiClient.delete(ApiPathData.BOARD_BASE_PATH + temporaryBoardId, requestSpecification);

    }

    @Step("Create a new List: name = {name}")
    public Response createList(String nameOfTheList) {
        requestSpecification.queryParam("name", nameOfTheList);
        requestSpecification.queryParam("idBoard", temporaryBoardId);
        Response response = apiClient.post(ApiPathData.LISTS_BASE_PATH, requestSpecification );
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }
}