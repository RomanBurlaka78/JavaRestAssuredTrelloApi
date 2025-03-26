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
    private String temporaryBoardId;
    private String tepmoraryCardId;
    private String tepmoraryListId;

    private final String BOARD_NAME = "Board for cards";


    {
        requestSpecification = given(specification.installRequest());
    }

    public void createABord(){

        requestSpecification.queryParam("name", BOARD_NAME);

        Response response = apiClient.post(TestData.BOARD_BASE_PATH, requestSpecification);
        temporaryBoardId =  response.jsonPath().getString("id");


        Response resp = apiClient.get(TestData.BOARD_BASE_PATH + temporaryBoardId+ "/lists", requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
        tepmoraryListId = (String) arrayList.get(0);
        requestSpecification = given(specification.installRequest());

    }

    public void deleteBoard() {
        apiClient.delete(TestData.BOARD_BASE_PATH + temporaryBoardId, requestSpecification);

    }

    @Step("Create a new Card: id list = {idList}")
    public Response createCard() {

        requestSpecification.queryParam("idList", tepmoraryListId);
        Response response = apiClient.post(TestData.CARDS_BASE_PATH, requestSpecification);

        tepmoraryCardId = response.jsonPath().get("id");
        requestSpecification = given(specification.installRequest());
        return response;
    }

    @Step("Get a card: id card = {cardId}")
    public Response getCard() {

        Response response = apiClient.get(TestData.CARDS_BASE_PATH + tepmoraryCardId, requestSpecification);
        return response;
    }

    @Step("Update a card: id card = {cardID}, name = {name}")
    public Response updateCard(String newCardName) {

        requestSpecification.queryParam("name", newCardName);
        Response response = apiClient.putWithSpecification(TestData.CARDS_BASE_PATH + tepmoraryCardId, requestSpecification);
        requestSpecification = given(specification.installRequest());
        return response;
    }

    @Step("Delete a card: id card = {cardID}")
    public Response deleteCard(String cardID) {
        Response response = apiClient.delete(TestData.CARDS_BASE_PATH + tepmoraryCardId, requestSpecification);
        requestSpecification = given(specification.installRequest());
        return response;
    }
}
