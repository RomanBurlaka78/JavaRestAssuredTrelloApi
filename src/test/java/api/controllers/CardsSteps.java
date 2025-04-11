package api.controllers;

import api.base.PathParameters;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;
import java.util.Objects;

public class CardsSteps extends BaseService {

    private final String attachmentsEndPoint = "/attachments/";
    private final String checkItemsEndPoint = "/checkItemStates";
    private final String checklistsEndPoint = "/checklists";

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
        return apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.CUSTOM_FIELDS_BASE_PATH, requestSpecification);
    }

    @Step("Get actions on card: {cardId}, actions = {actions}")
    public Response getActionsCard(String cardId, String actions) {
        return apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.ACTIONS_BASE_PATH, requestSpecification);
    }

    @Step("Get actions on card: {cardId}, attachments = {attachments}")
    public Response getAttachmentsCard(String cardId) {
        return apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.ATTACHMENTS_BASE_PATH, requestSpecification);
    }

    @Step("Add attachment that is located - {'filePathOfAnAttachment'}, on a card with id - {'cardId'}")
    public Response createAttachmentOnCard(String cardId, String filePathOfAnAttachment) {

        requestSpecification.multiPart(new File(filePathOfAnAttachment));
        requestSpecification.contentType("multipart/form-data");

        Response response = apiClient.post(PathParameters.CARDS_BASE_PATH + cardId + attachmentsEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get an attachment with id - {'createdAttachmentId'}, from a card with id - {'cardId'} ")
    public Response getAnAttachmentOnACard(String cardId, String createdAttachmentId) {
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + attachmentsEndPoint + createdAttachmentId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get checkItems on a card with id - {'cardId'}")
    public Response getCheckItemsOnACard(String cardId) {
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + checkItemsEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get checklists on a card with id - {'cardId'}")
    public Response getChecklistsOnACard(String cardId) {
        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + checklistsEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }
}



