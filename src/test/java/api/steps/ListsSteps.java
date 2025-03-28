package api.steps;

import api.base.PathParameters;
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

    {
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }

    public String createABord(String boardName){

        requestSpecification.queryParam("name", boardName);

        Response response = apiClient.post(PathParameters.BOARD_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response.jsonPath().getString("id");
    }

    public String getListsId(String boardId){
        Response resp = apiClient.get(PathParameters.BOARD_BASE_PATH + boardId+ PathParameters.LISTS_BASE_PATH, requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
//        tepmoraryListId = (String) arrayList.get(0);
        requestSpecification = given(specification.installRequest());
        return (String) arrayList.get(0);
    }

    public void deleteBoard(String boardId) {
        apiClient.delete(PathParameters.BOARD_BASE_PATH + boardId, requestSpecification);

    }

    @Step("Create a new List: name = {name}")
    public Response createList(String nameOfTheList, String boardId) {
        requestSpecification.queryParam("name", nameOfTheList);
        requestSpecification.queryParam("idBoard", boardId);

        Response response = apiClient.post(PathParameters.LISTS_BASE_PATH, requestSpecification );
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    public Response updateANameForList(String listId, String newNameForTheList) {

        requestSpecification.queryParam("name", newNameForTheList);
        Response response = apiClient.put(PathParameters.LISTS_BASE_PATH + listId, requestSpecification );
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }
}