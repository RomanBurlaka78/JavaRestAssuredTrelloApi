package api.steps;

import api.base.TestData;
import api.utils.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CardsSteps {

    private ApiClient apiClient = ApiClient.getInstance();

    @Step("Create a new Card: id list = {idList}")
    public Response createCard(String idList) {

        return apiClient.post("cards/?idList=" + idList, "");
    }

    @Step("Get a card: id card = {cardId}")
    public Response getCard(String cardId) {

        return apiClient.get("cards/" + cardId);
    }

    @Step("Update a card: id card = {cardID}, name = {name}")
    public Response updateCard(String cardID, String name) {

        String body = String.format("""
                {"name" : "%s"}
                """, name);

        return apiClient.put("cards/" + TestData.cardId, body);
    }

    @Step("Delete a card: id card = {cardID}")
    public Response deleteCard(String cardID) {
        return apiClient.delete("cards/" + TestData.cardId);
    }
}
