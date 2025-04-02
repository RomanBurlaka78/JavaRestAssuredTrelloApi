package api.steps;

import api.base.PathParameters;
import api.utils.ApiClient;
import api.utils.Specification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ActionsSteps {

    private final Specification specification = new Specification();
    private RequestSpecification requestSpecification;
    private final ApiClient apiClient = ApiClient.getInstance();
    private final String archiveEndPoint = "/archiveAllCards";

    {
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }

    public String createABord(String boardName){

        requestSpecification.queryParam("name", boardName);

        Response response = apiClient.post(PathParameters.BOARD_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());
        return response.jsonPath().getString("id");
    }

    public void deleteBoard(String boardId) {
        apiClient.delete(PathParameters.BOARD_BASE_PATH + boardId, requestSpecification);

    }
}
