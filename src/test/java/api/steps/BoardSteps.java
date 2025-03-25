package api.steps;

import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;

public class BoardSteps {

    private final Specification specification = new Specification();
    private RequestSpecification requestSpecification;
    private final String basePath = "boards/";
    private final String lableEndPoint = "/labels";
    private ApiClient apiClient = ApiClient.getInstance();

    {
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }

    @Step("Create board with name: {name}")
    public Response createBoard(String nameOfTheBoard) {
        requestSpecification.queryParam("name", nameOfTheBoard);
        Response response = apiClient.post("boards", requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    @Step("Delete board {name}")
    public Response deleteBoardStep(String id) {
        return ApiClient.getInstance().delete(basePath + id, requestSpecification);
    }

    @Step("Get Board: id board = {boardId}")
    public Response getBoard(String boardId) {
        return apiClient.get(basePath + boardId, requestSpecification);
    }

    @Step("Update Board: id board = {boardId}, new name board = {name}")
    public Response updateBoard(String boardId, String bordName) {
        requestSpecification.param("name", bordName);
        return apiClient.putWithSpecification(basePath + boardId, requestSpecification);
    }

    @Step("Create a Label on a Board: id board = {boardId}, label name = {name}, label color = {color}")
    public Response createLabelOnBoard(String boardId, String nameOfLabel, String color) {
        requestSpecification.queryParam("name", nameOfLabel);
        requestSpecification.queryParam("color", color);
        Response respone = apiClient.post(basePath + boardId + lableEndPoint, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return respone;
    }

    @Step("Get Labels on a Board: id board = {boardId}")
    public Response getLabelOnBoard(String boardId) {
        return apiClient.get(basePath + boardId + "/labels", requestSpecification);
    }

//    @Step("Create a List on a Board: id board = {boardId}, list name = {name}")
//    public Response createListOnBoard(String boardId, String name) {
//
//        String body = String.format("""
//                {"name" : "%s" }
//                """, name);
//
//        return apiClient.post(basePath + boardId + "/lists", body);
//    }

    @Step("getting a field - {field}, from a bord which id is - {boardId}")
    public Response getAField(String boardId, String field) {

        return apiClient.get(basePath +boardId + "/" + field, requestSpecification);
    }

    @Step("Get all actions existed on a board")
    public Response getActions(String boardId, String actions) {

        return apiClient.get(basePath + boardId + actions, requestSpecification);
    }

    @Step("Get checklists presented on a board")
    public Response getChecklists(String boardId, String checklistsEndPoint) {
        return apiClient.get(basePath + boardId + checklistsEndPoint, requestSpecification);
    }

    @Step("Get cards presented on a board")
    public Response getCards(String boardId, String cardsEndPoint) {

        return apiClient.get(basePath + boardId + cardsEndPoint, requestSpecification);
    }

    @Step("Get filtered cards presented on a board")
    public Response getFilteredCards(String boardId, String filtereCardsEndPoint, String filterName) {

        return apiClient.get(basePath + boardId + filtereCardsEndPoint + filterName, requestSpecification);
    }

    @Step("Get custom fields presented on a board")
    public Response getCustomFieldsForABoard(String boardId, String customFieldsEndPoint) {

        return apiClient.get(basePath + boardId + customFieldsEndPoint, requestSpecification);
    }

    @Step("Get lists presented on a board")
    public Response getListsOfABoard(String boardId, String listsEndPoint) {

        return apiClient.get(basePath + boardId + listsEndPoint, requestSpecification);
    }

    public Response getSpecificListOfABoard(String boardId, String listsEndPoint, String filter) {

        return apiClient.get(basePath + boardId + listsEndPoint + filter, requestSpecification);
    }

    public Response getMembersOfABoard(String boardId, String membersEndPoint) {

        return apiClient.get(basePath + boardId + membersEndPoint, requestSpecification);
    }

    public Response putWithSpecification(String boardId, String membersEndPoint) {
        requestSpecification.param("email", "ironman-968-privet-test@ya.ru");
        return apiClient.putWithSpecification(basePath + boardId + membersEndPoint, requestSpecification);
    }
}
