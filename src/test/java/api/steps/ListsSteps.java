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
    private String archiveEndPoint = "/archiveAllCards";
    private String moveAllCardsEndPoint = "/moveAllCards";
    private String archiveAListEndPoint = "/closed";

    {
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }

    public String createABord(String boardName){

        requestSpecification.queryParam("name", boardName);

        Response response = apiClient.post(PathParameters.BOARD_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response.jsonPath().getString("id");
    }

    public String getTheFirstListsId(String boardId){
        Response resp = apiClient.get(PathParameters.BOARD_BASE_PATH + boardId + PathParameters.LISTS_BASE_PATH, requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
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

    @Step("Update a neme for the list with value = {newNameForTheList}")
    public Response updateANameForList(String listId, String newNameForTheList) {

        requestSpecification.queryParam("name", newNameForTheList);
        Response response = apiClient.put(PathParameters.LISTS_BASE_PATH + listId, requestSpecification );
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    @Step("Get the list with id = {listId}")
    public Response getAList(String listId) {

        return apiClient.get(PathParameters.LISTS_BASE_PATH + listId, requestSpecification);
    }

    @Step("Create a card for list with id = {listId}")
    public void createACard(String listId) {
        requestSpecification.queryParam("idList", listId);
        requestSpecification.queryParam("name", "nameForCard");

        apiClient.post(PathParameters.CARDS_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }
    @Step("Archive all existed cards on a list with id = {listId}")
    public Response archiveAllCardOnTheList(String listId) {

        return apiClient.post(PathParameters.LISTS_BASE_PATH + listId + archiveEndPoint, requestSpecification);
    }

    @Step("Move all cards from the list with id = {newCreatedListId} to the list with id = {IdOfTheListThatTheCardsShouldBeMovedTo}")
    public Response moveAllCardsFromOneListToAnother(String newCreatedListId, String boardId, String IdOfTheListThatTheCardsShouldBeMovedTo) {
        requestSpecification.queryParam("idBoard", boardId);
        requestSpecification.queryParam("idList",IdOfTheListThatTheCardsShouldBeMovedTo);

        Response response = apiClient.post(PathParameters.LISTS_BASE_PATH + newCreatedListId + moveAllCardsEndPoint, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    @Step("Archive a list with id = {idOfTheListToArchive}")
    public Response archiveAList(String idOfTheListToArchive) {
        requestSpecification.queryParam("value", "true");

        Response response = apiClient.put(PathParameters.LISTS_BASE_PATH + idOfTheListToArchive + archiveAListEndPoint, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    @Step("Unarchive a list with id = {idOfTheListToUnArchive}")
    public Response unArchiveAList(String idOfTheListToUnArchive) {
        requestSpecification.queryParam("value", "false");

        Response response = apiClient.put(PathParameters.LISTS_BASE_PATH + idOfTheListToUnArchive + archiveAListEndPoint, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }


}