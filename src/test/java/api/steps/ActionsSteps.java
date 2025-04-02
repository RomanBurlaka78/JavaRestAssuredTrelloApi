package api.steps;

import api.base.PathParameters;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ActionsSteps extends BaseService{

    private String textEndPoint ="/text";
    private String commentsEnpoint = "comments";

    @Step("Get an action with id = {actiontId} from a board")
    public Response getAnAction(String actiontId) {
        Response response = apiClient.get(PathParameters.ACTIONS_BASE_PATH + actiontId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Add a comment {'commentForAnAction'} to a card with id ={cardId}")
    public Response addNewComentToCard(String cardId, String commentForAnAction) {
        requestSpecification.queryParams("text", commentForAnAction);
        Response response = apiClient.post(PathParameters.CARDS_BASE_PATH + cardId + PathParameters.ACTIONS_BASE_PATH + commentsEnpoint, requestSpecification);
        initRequestSpecification();
        return response;
    }

    public Response updateACommentOfTheAction(String actiontId, String commentForAnAction) {
        requestSpecification.queryParam("value", commentForAnAction);
        Response response = apiClient.put(PathParameters.ACTIONS_BASE_PATH + actiontId + textEndPoint, requestSpecification);
        initRequestSpecification();
        return response;
    }
}
