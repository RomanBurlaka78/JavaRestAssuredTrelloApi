package api.steps;

import api.base.PathParameters;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ActionsSteps extends BaseService{

    private String textEndPoint ="/text";

    @Step("Get an action with id = {actiontId} from a board")
    public Response getAnAction(String actiontId) {
        Response response = apiClient.get(PathParameters.ACTIONS_BASE_PATH + actiontId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Update a comment of an action with value = {updatedCommentForAnAction}")
    public Response updateACommentOfTheAction(String actiontId, String updatedCommentForAnAction) {
        requestSpecification.queryParam("value", updatedCommentForAnAction);
        Response response = apiClient.put(PathParameters.ACTIONS_BASE_PATH + actiontId + textEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Delete an action with id = {idOfAnActionToDelete}")
    public Response deleteAnAction(String idOfAnActionToDelete) {
        Response response = apiClient.delete(PathParameters.ACTIONS_BASE_PATH + idOfAnActionToDelete, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get the resource {resourceEndPoint}, the action with id {actionId}, belong to")
    public Response getTheResourceOfAnAction(String idOfAnAction, String resourceEndPoint) {
        Response response = apiClient.get(PathParameters.ACTIONS_BASE_PATH + idOfAnAction + resourceEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }


    public Response createReactionForAction(String actiontId, String reactionsEndPoint) {
        requestSpecification.queryParams("shortName", "grinning");
        Response response = apiClient.post(PathParameters.ACTIONS_BASE_PATH + actiontId + reactionsEndPoint, requestSpecification );
        initRequestSpecification();
        return response;
    }

    public Response getActionsReaction(String actionIdAfterCreatingACard, String reactionsEnPoint, String idOfReaction) {
        Response response = apiClient.get(PathParameters.ACTIONS_BASE_PATH + actionIdAfterCreatingACard +
                                            reactionsEnPoint + idOfReaction, requestSpecification);
        initRequestSpecification();
        return response;
    }
}
