package api.steps;

import api.base.TestData;
import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class CardsSteps {

    private final ApiClient apiClient = ApiClient.getInstance();
    private final Specification specification = new Specification();
    private RequestSpecification requestSpecification;
    private final String basePath = "/cards/";
    private String temporaryBoardId;


    {
        requestSpecification = given(specification.installRequest());
    }

    public void createABord(){
        requestSpecification.queryParam("name", "Board for cards");
        Response response = apiClient.post("boards/", requestSpecification);
//        TestData.boardId =  response.path("id").toString();
        temporaryBoardId =  response.path("id").toString();


        Response resp = apiClient.get("boards/"+temporaryBoardId+ "/lists", requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
        TestData.listId = (String) arrayList.get(0);
        requestSpecification = given(specification.installRequest());

    }

    public void deleteBoard() {
        apiClient.delete(basePath + temporaryBoardId, requestSpecification);
        requestSpecification = given(specification.installRequest());

    }

    @Step("Create a new Card: id list = {idList}")
    public Response createCard(String idList) {
        requestSpecification.queryParam("idList", idList);
        Response response = apiClient.post(basePath, requestSpecification);
//        TestData.cardId = response.jsonPath().get("id");
        requestSpecification = given(specification.installRequest());
        return response;
    }

    @Step("Get a card: id card = {cardId}")
    public Response getCard(String cardId) {

        Response response = apiClient.get(basePath + cardId, requestSpecification);
        requestSpecification = given(specification.installRequest());
        return response;
    }

    @Step("Update a card: id card = {cardID}, name = {name}")
    public Response updateCard(String newCardName) {

        requestSpecification.queryParam("name", newCardName);
        Response response = apiClient.putWithSpecification(basePath + TestData.cardId, requestSpecification);
        requestSpecification = given(specification.installRequest());
        return response;
    }

    @Step("Delete a card: id card = {cardID}")
    public Response deleteCard(String cardID) {
        Response response = apiClient.delete(basePath + TestData.cardId, requestSpecification);
        requestSpecification = given(specification.installRequest());
        return response;
    }
}
