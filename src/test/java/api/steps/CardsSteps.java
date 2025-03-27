package api.steps;

import api.base.ApiPathData;
import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class CardsSteps {

    private final ApiClient apiClient = ApiClient.getInstance();
    private final Specification specification = new Specification();
    private RequestSpecification requestSpecification;
    private String temporaryBoardId;
    private String tepmoraryListId;

    {
        requestSpecification = RestAssured.given().spec(specification.installRequest());
    }

    public void createABord(String boardName){

        requestSpecification.queryParam("name", boardName);

        Response response = apiClient.post(ApiPathData.BOARD_BASE_PATH, requestSpecification);
        temporaryBoardId =  response.jsonPath().getString("id");
        requestSpecification = RestAssured.given().spec(specification.installRequest());


        Response resp = apiClient.get(ApiPathData.BOARD_BASE_PATH + temporaryBoardId+ "/lists", requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
        tepmoraryListId = (String) arrayList.get(0);
        requestSpecification = given(specification.installRequest());

    }

    public void deleteBoard() {
        apiClient.delete(ApiPathData.BOARD_BASE_PATH + temporaryBoardId, requestSpecification);

    }

    @Step("Create a new Card: id list = {idList}")
    public Response createCard() {

        requestSpecification.queryParam("idList", tepmoraryListId);
        Response response = apiClient.post(ApiPathData.CARDS_BASE_PATH, requestSpecification);

        requestSpecification = given(specification.installRequest());
        return response;
    }

    @Step("Get a card: id card = {cardId}")
    public Response getCard(String cardId) {

        Response response = apiClient.get(ApiPathData.CARDS_BASE_PATH + cardId, requestSpecification);
        return response;
    }

    @Step("Update a card: id card = {cardID}, name = {name}")
    public Response updateCard(String cardId, String newCardName) {

        requestSpecification.queryParam("name", newCardName);
        Response response = apiClient.putWithSpecification(ApiPathData.CARDS_BASE_PATH + cardId, requestSpecification);
        requestSpecification = given(specification.installRequest());
        return response;
    }

    @Step("Delete a card: id card = {cardID}")
    public Response deleteCard(String cardID) {
        Response response = apiClient.delete(ApiPathData.CARDS_BASE_PATH + cardID, requestSpecification);
        requestSpecification = given(specification.installRequest());
        return response;
    }
}
