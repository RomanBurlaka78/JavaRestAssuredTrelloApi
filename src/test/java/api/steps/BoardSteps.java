package api.steps;

import api.base.ApiPathData;
import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.sql.ResultSet;

public class BoardSteps {

    private final Specification specification = new Specification();
    private RequestSpecification requestSpecification;
    private ApiClient apiClient = ApiClient.getInstance();


    {
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }

    @Step("Create board with name: {nameOfTheBoard}")
    public Response createBoard(String nameOfTheBoard) {

        requestSpecification.queryParam("name", nameOfTheBoard);
        Response response = apiClient.post(ApiPathData.BOARD_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    @Step("Delete board {boardId}")
    public Response deleteBoard(String boardId) {

        return ApiClient.getInstance().delete(ApiPathData.BOARD_BASE_PATH + boardId, requestSpecification);
    }

    @Step("Get Board: id board = {boardId}")
    public Response getBoard(String boardId) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId, requestSpecification);
    }

    @Step("Update Board: id board = {boardId}, new name board = {bordName}")
    public Response updateBoard(String boardId, String bordName) {
        requestSpecification.param("name", bordName);
        Response response = apiClient.putWithSpecification(ApiPathData.BOARD_BASE_PATH + boardId, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    @Step("Create a Label on a Board: id board = {boardId}, label name = {nameOfLabel}, label color = {color}")
    public Response createLabelOnBoard(String boardId, String nameOfLabel, String color) {
        requestSpecification.queryParam("name", nameOfLabel);
        requestSpecification.queryParam("color", color);
        Response respone = apiClient.post(ApiPathData.BOARD_BASE_PATH + boardId + ApiPathData.LABLES_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return respone;
    }

    @Step("Get Labels on a Board: id board = {boardId}")
    public Response getLabelOnBoard(String boardId) {
        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + ApiPathData.LABLES_BASE_PATH, requestSpecification);
    }

    @Step("Create a List on a Board: id board = {boardId}, list name = {nameForList}")
    public Response createListOnBoard(String boardId, String nameForList) {
        requestSpecification.queryParam("name", nameForList);
        Response response = apiClient.post(ApiPathData.BOARD_BASE_PATH + boardId + ApiPathData.LISTS_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    @Step("getting a field - {field}, from a bord which id is - {boardId}")
    public Response getAField(String boardId, String fieldName) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH +boardId + fieldName, requestSpecification);
    }

    @Step("Get all actions existed on a board")
    public Response getActions(String boardId, String actions) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + actions, requestSpecification);
    }

    @Step("Get checklists presented on a board")
    public Response getChecklists(String boardId, String checklistsEndPoint) {
        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + checklistsEndPoint, requestSpecification);
    }

    @Step("Get cards presented on a board")
    public Response getCards(String boardId, String cardsEndPoint) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + cardsEndPoint, requestSpecification);
    }

    @Step("Get filtered cards presented on a board")
    public Response getFilteredCards(String boardId, String filtereCardsEndPoint, String filterName) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + filtereCardsEndPoint + filterName, requestSpecification);
    }

    @Step("Get custom fields presented on a board")
    public Response getCustomFieldsForABoard(String boardId, String customFieldsEndPoint) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + customFieldsEndPoint, requestSpecification);
    }

    @Step("Get lists presented on a board")
    public Response getListsOfABoard(String boardId, String listsEndPoint) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + listsEndPoint, requestSpecification);
    }

    public Response getSpecificListOfABoard(String boardId, String listsEndPoint, String filter) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + listsEndPoint + filter, requestSpecification);
    }

    public Response getMembersOfABoard(String boardId, String membersEndPoint) {

        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + membersEndPoint, requestSpecification);
    }

    public Response putWithSpecification(String boardId, String membersEndPoint) {
        requestSpecification.param("email", "ironman-968-privet-test@ya.ru");
        return apiClient.putWithSpecification(ApiPathData.BOARD_BASE_PATH + boardId + membersEndPoint, requestSpecification);
    }

    @Step("Get boardStars on a Board: id board = {boardId}")
    public Response getBoardStarsOnBoard(String boardId, String boardStarsEnPoint) {
        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + "/boardStars", requestSpecification);
    }

    @Step("Get memberships on a Board: id board = {boardId}")
    public Response getMembershipsOnBoard(String boardId) {
        return apiClient.get(ApiPathData.BOARD_BASE_PATH + boardId + "/memberships", requestSpecification);
    }

    @Step("Add member to Board: boardId = {boardId}, email = {email}, memberType = {memberType}")
    public Response addMemberToBoard(String boardId, String memBerId, String memberType) {
        requestSpecification.queryParam("type", memberType);
        Response response = apiClient.putWithSpecification(ApiPathData.BOARD_BASE_PATH + boardId + ApiPathData.MEMBERS_BASE_PATH + memBerId, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response;
    }

    @Step("Remove member from Board: boardId = {boardId}, memberId = {memberId}")
    public Response removeMemberFromBoard(String boardId, String memberId) {

        return ApiClient.getInstance().delete(ApiPathData.BOARD_BASE_PATH + boardId +
                ApiPathData.MEMBERS_BASE_PATH + memberId, requestSpecification);

    }
}
