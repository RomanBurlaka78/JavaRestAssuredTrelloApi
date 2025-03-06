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

    @Step("Get boar {boardId}")
    public Response getBoard(String boardId) {
        return apiClient.get("boards/" + boardId);
    }

    @Step("Update boar {boardId}")
    public Response updateBoard(String boardId, String body) {
        return apiClient.put("boards/" + boardId, """
                {
                    "name" : "New Api Board"
                }
                """);
    }

    @Step("Create a Label on a Board: {boardId}")
    public Response createLabelOnBoard(String boardId, String name, String color) {
        return apiClient.post("boards/" + boardId + "/labels?name=" + name + "&color=" + color, "");
    }

    @Step("Get Labels on a Board: {boardId}")
    public Response getLabelOnBoard(String boardId) {
        return apiClient.get("boards/" + boardId + "/labels");
    }
}
