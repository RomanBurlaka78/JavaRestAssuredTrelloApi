package api.steps;

import api.base.TestData;
import api.utils.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

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
