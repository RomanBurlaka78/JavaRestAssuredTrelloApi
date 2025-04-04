package api.steps;

import api.base.PathParameters;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ActionsSteps extends BaseService{

    private String textEndPoint ="/text";
    private String boardEndPoint ="/board";
    private String cardEndPoint ="/card";

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

    @Step("Get a field {'anActionField'} from an action with id = {idOfAnAction}")
    public Response getASpecificFieldOnAnAction(String idOfAnAction, String anActionField) {
        Response response = apiClient.get(PathParameters.ACTIONS_BASE_PATH + idOfAnAction + anActionField, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get the board, the action with id {actionId}, belong to")
    public Response getTheBoardForAnAction(String idOfAnAction) {
        Response response = apiClient.get(PathParameters.ACTIONS_BASE_PATH + idOfAnAction + boardEndPoint, requestSpecification );
        initRequestSpecification();
        return response;
    }

    @Step("Get the card the action with id {actionId}, belong to")
    public Response getTheCardForAnAction(String actionId) {
        Response response = apiClient.get(PathParameters.ACTIONS_BASE_PATH + actionId + cardEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }
}
