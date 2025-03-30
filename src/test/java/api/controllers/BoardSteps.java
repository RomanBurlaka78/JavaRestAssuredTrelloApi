package api.controllers;

import api.utils.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class BoardSteps {

    private final String basePath = "boards/";
    private ApiClient apiClient = ApiClient.getInstance();

    @Step("Create board with name: {name}")
    public Response createBoard(String name) {
        return apiClient.postWithOutBody("boards", name);
    }

    @Step("Delete board {name}")
    public Response deleteBoardStep(String id) {
        return ApiClient.getInstance().delete(basePath + id);
    }

    @Step("Get Board: id board = {boardId}")
    public Response getBoard(String boardId) {
        return apiClient.get(basePath + boardId);
    }

    @Step("Update Board: id board = {boardId}, new name board = {name}")
    public Response updateBoard(String boardId, String body) {
        return apiClient.put(basePath + boardId, "{\"name\" : \"" + body + "\" }");
    }

    @Step("Create a Label on a Board: id board = {boardId}, label name = {name}, label color = {color}")
    public Response createLabelOnBoard(String boardId, String name, String color) {
        return apiClient.post(basePath + boardId + "/labels?name=" + name + "&color=" + color, "");
    }

    @Step("Get Labels on a Board: id board = {boardId}")
    public Response getLabelOnBoard(String boardId) {
        return apiClient.get(basePath + boardId + "/labels");
    }

    @Step("Create a List on a Board: id board = {boardId}, list name = {name}")
    public Response createListOnBoard(String boardId, String name) {

        String body = String.format("""
                {"name" : "%s" }
                """, name);

        return apiClient.post(basePath + boardId + "/lists", body);
    }

    @Step("getting a field - {field}, from a bord which id is - {boardId}")
    public Response getAField(String boardId, String field) {

        return apiClient.get(basePath +boardId + "/" + field);
    }

    @Step("Get all actions existed on a board")
    public Response getActions(String boardId, String actions) {

        return apiClient.get(basePath + boardId + actions);
    }

    @Step("Get checklists presented on a board")
    public Response getChecklists(String boardId, String checklistsEndPoint) {
        return apiClient.get(basePath + boardId + checklistsEndPoint);
    }

    @Step("Get cards presented on a board")
    public Response getCards(String boardId, String cardsEndPoint) {

        return apiClient.get(basePath + boardId + cardsEndPoint);
    }

    @Step("Get filtered cards presented on a board")
    public Response getFilteredCards(String boardId, String filtereCardsEndPoint, String filterName) {

        return apiClient.get(basePath + boardId + filtereCardsEndPoint + filterName);
    }

    @Step("Get custom fields presented on a board")
    public Response getCustomFieldsForABoard(String boardId, String customFieldsEndPoint) {

        return apiClient.get(basePath + boardId + customFieldsEndPoint);
    }

    @Step("Get lists presented on a board")
    public Response getListsOfABoard(String boardId, String listsEndPoint) {

        return apiClient.get(basePath + boardId + listsEndPoint);
    }

    public Response getSpecificListOfABoard(String boardId, String listsEndPoint, String filter) {

        return apiClient.get(basePath + boardId + listsEndPoint + filter);
    }

    public Response getMembersOfABoard(String boardId, String membersEndPoint) {

        return apiClient.get(basePath + boardId + membersEndPoint);
    }

    @Step("Get boardStars on a Board: id board = {boardId}")
    public Response getBoardStarsOnBoard(String boardId) {
        return apiClient.get(basePath + boardId + "/boardStars");
    }

    @Step("Get memberships on a Board: id board = {boardId}")
    public Response getMembershipsOnBoard(String boardId) {
        return apiClient.get(basePath + boardId + "/memberships");
    }

    @Step("Add member to Board: boardId = {boardId}, email = {email}, memberType = {memberType}")
    public Response addMemberToBoard(String boardId, String email, String memberType) {
        String requestBody = String.format("""
                {
                "email": "%s", 
                "type": "%s"
                }
                """, email, memberType);
        return apiClient.put(basePath + boardId + "/members", requestBody);
    }

    @Step("Remove member from Board: boardId = {boardId}, memberId = {memberId}")
    public Response removeMemberFromBoard(String boardId, String memberId) {
        return ApiClient.getInstance().delete(basePath + boardId + "/members/" + memberId);

    }
}
