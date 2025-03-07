package api.steps;

import api.base.TestData;
import api.utils.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CardsSteps {

    private ApiClient apiClient = ApiClient.getInstance();

    @Step("Create board with name: {name}")
    public Response createBoard(String name) {
        return apiClient.postWithOutBody("boards", name);
    }

    @Step("Create a new Card: ")
    public Response createCard() {

        String body = String.format("""
                {
                "idList" = "%s"
                }
                """, TestData.idList);

        return apiClient.post("cards/?idList=" + TestData.idList, "");
    }
}
