package api.steps;

import api.base.PathParameters;
import io.restassured.response.Response;

public class ChecklistsSteps extends BaseService{


    public Response createAChecklist(String idCard, String nameOfAChecklistCreated) {
        requestSpecification.queryParam("idCard", idCard);
        requestSpecification.queryParam("name", nameOfAChecklistCreated);
        Response response = apiClient.post(PathParameters.CHECKLISTS_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response;
    }

    public Response getCheckList(String checklistId) {
        Response response = apiClient.get(PathParameters.CHECKLISTS_BASE_PATH + checklistId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    public Response updateAFieldOfCheckList(String checklistId, String nameOfAField, String newValueOfAField) {
        requestSpecification.queryParam(nameOfAField, newValueOfAField);
        Response response = apiClient.put(PathParameters.CHECKLISTS_BASE_PATH + checklistId, requestSpecification);
        initRequestSpecification();
        return response;
    }
}
