package api.controllers;

import api.base.PathParameters;
import api.controllers.BaseService;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ChecklistsSteps extends BaseService {

    private final String boardEndPoint = "/board";
    private final String cardsEndPoint = "/cards";
    private final String checkItemsEndPoint = "/checkItems/";

    @Step("Create a checklist on a card with id - {'idCard'}, with a name - {'nameOfAChecklistBeingCreated'}")
    public Response createAChecklist(String idCard, String nameOfAChecklistBeingCreated) {
        requestSpecification.queryParam("idCard", idCard);
        requestSpecification.queryParam("name", nameOfAChecklistBeingCreated);
        Response response = apiClient.post(PathParameters.CHECKLISTS_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get all fields of a checklist with id - {'checklistId'}")
    public Response getCheckList(String checklistId) {
        Response response = apiClient.get(PathParameters.CHECKLISTS_BASE_PATH + checklistId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Update a field - {'nameOfAField'}, on a checklist with id - {checklistId}, new value is 'newValueOfAField'")
    public Response updateAFieldOfCheckList(String checklistId, String nameOfAField, String newValueOfAField) {
        requestSpecification.queryParam(nameOfAField, newValueOfAField);
        Response response = apiClient.put(PathParameters.CHECKLISTS_BASE_PATH + checklistId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get a field - {'fieldToGetBackFromTheChecklist'}, from a checklist with id - {checklistId}")
    public Response getFieldOnAChecklist(String checklistId, String fieldToGetBackFromTheChecklist) {
        Response response = apiClient.get(PathParameters.CHECKLISTS_BASE_PATH + checklistId + fieldToGetBackFromTheChecklist, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get the board the checklist with id - {'checklistId'} is on")
    public Response getTheBoardTheChecklistIsOn(String checklistId) {
        Response response = apiClient.get(PathParameters.CHECKLISTS_BASE_PATH + checklistId + boardEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get the card the checklist with id - {'checklistId'}, is on.")
    public Response getTheCardAChecklistIsOn(String checklistId) {
        Response response = apiClient.get(PathParameters.CHECKLISTS_BASE_PATH + checklistId + cardsEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Create checkItem with the name - {'nameOfNewCheckItem'}, on a checklist with id - {'checklistId'}")
    public Response createCheckitemOnChecklist(String checklistId, String nameOfNewCheckItem) {
        requestSpecification.queryParam("name", nameOfNewCheckItem);
        Response response = apiClient.post(PathParameters.CHECKLISTS_BASE_PATH + checklistId + checkItemsEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get all checkItems that are currently available on a checklist")
    public Response getCheckitemsOnAChecklist(String checklistId) {
        Response response = apiClient.get(PathParameters.CHECKLISTS_BASE_PATH + checklistId + checkItemsEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get a checkItem with id - {'checkItemId'}, on a checklist with id - {'checklistId'}")
    public Response getACheckitemOnAChecklist(String checklistId, String checkItemId) {
        Response response = apiClient.get(PathParameters.CHECKLISTS_BASE_PATH + checklistId + checkItemsEndPoint + checkItemId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Delete checkItem with id - {'checkItemId'}, from checklist with id - {'checklistId'}")
    public Response deleteCheckitemFromChecklist(String checklistId, String checkItemId) {
        Response response = apiClient.delete(PathParameters.CHECKLISTS_BASE_PATH + checklistId + checkItemsEndPoint + checkItemId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Delete a checklist")
    public Response deleteAChecklist(String checklistId) {
        Response response = apiClient.delete(PathParameters.CHECKLISTS_BASE_PATH + checklistId, requestSpecification);
        initRequestSpecification();
        return response;
    }
}
