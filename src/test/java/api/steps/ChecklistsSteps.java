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
}
