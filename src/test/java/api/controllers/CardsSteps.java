package api.controllers;

import api.base.PathParameters;
import api.base.PathParameters.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;
import java.util.Objects;

public class CardsSteps extends BaseService {



    @Step("Get a card: id card = {cardId}")
    public Response getCard(String cardId) {

        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Update a card: id card = {cardID}, name = {name}")
    public Response updateCard(String cardId, String newCardName) {

        requestSpecification.queryParam("name", newCardName);
        Response response = apiClient.put(CardsEndPoints.CARDS_BASE_PATH + cardId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Delete a card: id card = {cardID}")
    public Response deleteCard(String cardID) {
        Response response = apiClient.delete(CardsEndPoints.CARDS_BASE_PATH + cardID, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get custom fields on card: {cardId}, field = {field}")
    public Response getFieldCard(String cardId) {
        Objects.requireNonNull(cardId, "ID карточки не может быть null");
        return apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + PathParameters.CUSTOM_FIELDS_BASE_PATH, requestSpecification);
    }

    @Step("Get actions on card: {cardId}, actions = {actions}")
    public Response getActionsOnACard(String cardId, String actions) {
        return apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.ACTIONS_ENDPOINT, requestSpecification);
    }

    @Step("Get all attachments from a card with id - {'cardId'}}")
    public Response getAttachmentsOnACard(String cardId) {
        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.ATTACHMENTS_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Add attachment that is located - {'filePathOfAnAttachment'}, on a card with id - {'cardId'}")
    public Response createAttachmentOnCard(String cardId, String filePathOfAnAttachment) {

        requestSpecification.multiPart(new File(filePathOfAnAttachment));
        requestSpecification.contentType("multipart/form-data");

        Response response = apiClient.post(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.ATTACHMENTS_ENDPOINT, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get an attachment with id - {'createdAttachmentId'}, from a card with id - {'cardId'} ")
    public Response getAnAttachmentOnACard(String cardId, String createdAttachmentId) {
        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.ATTACHMENTS_ENDPOINT + createdAttachmentId, requestSpecification);
        initRequestSpecification();
        return response;
    }

//    @Step("Get checkItems on a card with id - {'cardId'}")
//    public Response getCheckItemsOnACard(String cardId) {
//        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + checkItemsEndPoint, requestSpecification);
//        initRequestSpecification();
//        return response;
//    }
//
//    @Step("Get checklists on a card with id - {'cardId'}")
//    public Response getChecklistsOnACard(String cardId) {
//        Response response = apiClient.get(PathParameters.CARDS_BASE_PATH + cardId + checklistsEndPoint, requestSpecification);
//        initRequestSpecification();
//        return response;
//    }

    @Step("Delete an Attachment with id - {'attachmentId'}, from a card with id - {cardID}")
    public Response deleteAnAttachmentOnACard(String cardID, String attachmentId) {
        Response response = apiClient.delete(CardsEndPoints.CARDS_BASE_PATH + cardID + CardsEndPoints.ATTACHMENTS_BASE_PATH  + attachmentId, requestSpecification);
        initRequestSpecification();
        return response;
    }
    @Step("Get custom fields on card: {cardId}, field = {field}")
    public Response getBoardCard(String cardId) {
        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get checkItems on card: {cardId}, field = {field}")
    public Response getCheckItemsCard(String cardId) {
        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.CHECKITEM_ENDPOINT, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get Checklists on card: {cardId}")
    public Response getChecklistsCard(String cardId) {
        requestSpecification.queryParam("checkItems", "all");
        requestSpecification.queryParam("checkItem_fields", "id,name,state");
        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.CHECKLISTS_ENDPOINT, requestSpecification);
        initRequestSpecification();
        return response;
    }


    @Step("Get list on card: {cardId}")
    public Response getListCard(String cardId) {
        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.LISTS_ENDPOINT, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get list on card: {cardId}")
    public Response getMembersCard(String cardId) {
        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.MEMBERS_ENDPOINT, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get list on card: {cardId}")
    public Response getStickersCard(String cardId) {
        Response response = apiClient.get(CardsEndPoints.CARDS_BASE_PATH + cardId + CardsEndPoints.STICKERS_ENDPOINT, requestSpecification);
        initRequestSpecification();
        return response;
    }


}



