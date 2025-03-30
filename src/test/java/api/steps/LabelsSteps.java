package api.steps;

import api.base.PathParameters;
import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LabelsSteps {
    private final Specification specification = new Specification();
    private RequestSpecification requestSpecification;
    private ApiClient apiClient = ApiClient.getInstance();

    {
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }

    public String createBoard(String name) {
        requestSpecification.queryParam("name", name);

        Response response = apiClient.post(PathParameters.BOARD_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());

        return response.jsonPath().getString("id");
    }

    public void deleteBoard(String boardId) {
        apiClient.delete(PathParameters.BOARD_BASE_PATH + boardId, requestSpecification);
    }

    @Step("Create a new Label: name = {name}, color = {color}, board id = {idBoard}")
    public Response createLabel(String labelName, String color, String boardId) {
        requestSpecification.queryParam("name", labelName);
        requestSpecification.queryParam("color", color);
        requestSpecification.queryParam("idBoard", boardId);

        Response response = apiClient.post(PathParameters.LABLES_BASE_PATH, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());

        return response;
    }

    @Step("Get a Label: id label = {labelId}")
    public Response getLabel(String labelId) {
        return apiClient.get(PathParameters.LABLES_BASE_PATH + "/" + labelId, requestSpecification);
    }

    @Step("Update Label: label id = {labelId}, new name = {newName}, new color = {newColor}")
    public Response updateLabel(String labelId, String newName, String newColor) {
        requestSpecification.queryParam("name", newName);
        requestSpecification.queryParam("color", newColor);

        Response response = apiClient.put(PathParameters.LABLES_BASE_PATH + "/" + labelId, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());

        return response;
    }

    @Step("Update field Label: label id = {labelId}, field = {field}, value = {value]")
    public Response updateFieldLabel(String labelId, String field, String value) {
        requestSpecification.queryParam("value", value);

        Response response = apiClient.put(PathParameters.LABLES_BASE_PATH + "/" + labelId + "/" + field, requestSpecification);
        requestSpecification = RestAssured.given().spec(specification.installRequest());

        return response;
    }

    @Step("Delete Label: label id = {labelId}")
    public Response deleteLabel(String labelId) {

        return apiClient.delete(PathParameters.LABLES_BASE_PATH + "/" + labelId, requestSpecification);
    }
}
