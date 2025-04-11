package api.steps;

import api.base.PathParameters;
import api.utils.ApiClient;
import api.utils.Specification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class CardsSteps extends BaseService{

    @Step("Get a card: id card = {cardId}")
    public Response getCard(String cardId) {

        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Update a card: id card = {cardID}, name = {name}")
    public Response updateCard(String cardId, String newCardName) {

        requestSpecification.queryParam("name", newCardName);
        Response response = apiClient.put(PathParameters.CARDS_BASE_PATH + cardId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Delete a card: id card = {cardID}")
    public Response deleteCard(String cardID) {
        Response response = apiClient.delete(PathParameters.CARDS_BASE_PATH + cardID, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get custom fields on card: {cardId}, field = {field}")
    public Response getFieldCard(String cardId) {
       Objects.requireNonNull(cardId, "ID карточки не может быть null");
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.CUSTOM_FIELDS_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get actions on card: {cardId}, actions = {actions}")
    public Response getActionsCard(String cardId, String actions) {
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.ACTIONS_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get Attachment on card: {cardId}, attachments = {attachments}")
    public Response getAttachmentsCard(String cardId) {
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.ATTACHMENTS_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get Attachment on card: {cardId}, attachments = {attachments}, idAttachment = {idAttachment} ")
    public Response getSpecificAttachmentsCard(String cardId, String field) {
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.ATTACHMENTS_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Delete a Attachment: id card = {cardID}")
    public Response deleteAttachmentCard(String cardID, String attachmentId) {
        Response response = apiClient.delete(PathParameters.CARDS_BASE_PATH + cardID + PathParameters.ATTACHMENTS_BASE_PATH + "/" + attachmentId, requestSpecification);
        initRequestSpecification();
        return response;
    }
    @Step("Get custom fields on card: {cardId}, field = {field}")
    public Response getBoardCard(String cardId) {
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get checkItems on card: {cardId}, field = {field}")
    public Response getCheckItemsCard(String cardId) {
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + "/checkItemStates", requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get Checklists on card: {cardId}")
    public Response getChecklistsCard(String cardId) {
        requestSpecification.queryParam("checkItems", "all");
        requestSpecification.queryParam("checkItem_fields", "id,name,state");
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + "/checklists", requestSpecification);
        initRequestSpecification();
        return response;
    }


}



