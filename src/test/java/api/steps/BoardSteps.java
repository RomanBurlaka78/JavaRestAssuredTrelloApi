package api.steps;

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

    @Step("getting a field - {field}, from a bord which id is - {idOfABord}")
    public Response getAField(String boardId, String field) {

        return apiClient.get(basePath +boardId + "/" + field);
    }

    public Response getActions(String boardId, String actions) {

        return apiClient.get(basePath + boardId + actions);
    }

    public Response getChecklists(String boardId, String checklistsEndPoint) {
        return apiClient.get(basePath + boardId + checklistsEndPoint);
    }

    public Response getCards(String boardId, String cardsEndPoint) {

        return apiClient.get(basePath + boardId + cardsEndPoint);
    }

    public Response getFilteredCards(String boardId, String cardsEndPoint, String filterName) {

        return apiClient.get(basePath + boardId + cardsEndPoint + filterName);
    }
}
