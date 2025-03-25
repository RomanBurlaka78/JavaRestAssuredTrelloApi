package api.steps;

import api.base.TestData;
import api.utils.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ListsSteps {

    ApiClient apiClient = ApiClient.getInstance();

    @Step("Create a new List: name = {name}")
    public Response createList(String name) {
        return apiClient.post("lists?name=" + name + "&idBoard=" + TestData.boardId, "");
    }
}
