package api.steps;

import api.utils.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class BoardSteps {
    private ApiClient apiClient = ApiClient.getInstance();

    @Step("Create board with name: {name}")
    public Response createBoard(String name) {
        return apiClient.postWithOutBody("boards", name);
    }

    @Step("Delete board {name}")
    public Response deleteBoardStep(String id) {
        return ApiClient.getInstance().delete("boards/" + id);
    }

    @Step("Get Board: id board = {boardId}")
    public Response getBoard(String boardId) {
        return apiClient.get("boards/" + boardId);
    }

    @Step("Update Board: id board = {boardId}, new name board = {name}")
    public Response updateBoard(String boardId, String body) {
        return apiClient.put("boards/" + boardId, "{\"name\" : \"" + body + "\" }");
    }

    @Step("Create a Label on a Board: id board = {boardId}, label name = {name}, label color = {color}")
    public Response createLabelOnBoard(String boardId, String name, String color) {
        return apiClient.post("boards/" + boardId + "/labels?name=" + name + "&color=" + color, "");
    }

    @Step("Get Labels on a Board: id board = {boardId}")
    public Response getLabelOnBoard(String boardId) {
        return apiClient.get("boards/" + boardId + "/labels");
    }

    @Step("Create a List on a Board: id board = {boardId}, list name = {name}")
    public Response createListOnBoard(String boardId, String name) {

        String body = String.format("""
                {"name" : "%s" }
                """, name);

        return apiClient.post("boards/" + boardId + "/lists", body);
    }
}
