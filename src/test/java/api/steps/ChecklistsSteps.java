package api.steps;

import api.base.PathParameters;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ChecklistsSteps extends BaseService{

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
}
