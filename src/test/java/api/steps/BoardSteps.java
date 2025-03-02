package api.steps;

import api.utils.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class BoardSteps {
    private ApiClient apiClient = ApiClient.getInstance();

    @Step("Create board with name: {name}")
    public Response createBoard(String name) {
        return apiClient.postWithOutBody("boards",name);
    }

    @Step("Delete board {name}")
    public Response deleteBoardStep(String id) {
        return ApiClient.getInstance().delete("boards/" + id);
    }

}
