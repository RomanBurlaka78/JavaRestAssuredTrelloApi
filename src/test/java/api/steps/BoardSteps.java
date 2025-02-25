package api.steps;

import api.base.TestData;
import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.notNullValue;

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

    @Step("Get labels on a board")
    public Response getLabelsOnBoard() {
        return apiClient.get("boards/" + TestData.boardId + "/labels");
    }

    @Step("Update board")
    public Response updateBoard(String name) {
        String body = String.format("""
                {
                "name":%s
                }
                """, name);
        return apiClient.put("boards/" + TestData.boardId, body);
    }

    @Step("Get field on a board")
    public Response getField(String field) {
        return apiClient.get(String.format("boards/%s/%s", TestData.boardId, field));
    }

    @Step("Get filtered cards on a board")
    public Response getFilteredCards(String filteredCards) {
        return apiClient.get(String.format("boards/%s/cards/%s", TestData.boardId, filteredCards));
    }

    @Step("Create label on a board")
    public Response createLabel(String name) {
        String body = String.format("""
                {
                 "name":"%s", 
                 "color":"red"          
                      }
                """, name);
        return apiClient.post("boards/" + TestData.boardId + "/labels", body);

    }

    @Step("Get lists on a board")
    public Response getListsOnBoard() {
        return apiClient.get("boards/" + TestData.boardId + "/lists");
    }

    @Step("Create list on a board")
    public Response createListOnBoard(String name) {
        String body = String.format("""
                {
                 "name":"%s"            
                      }
                """, name);
        return apiClient.post("boards/" + TestData.boardId + "/lists", body);

    }


}
