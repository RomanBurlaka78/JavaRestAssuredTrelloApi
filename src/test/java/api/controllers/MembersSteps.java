package api.controllers;

import api.base.PathParameters;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class MembersSteps extends BaseService{

    @Step("Get a member with id - {'firstMemberId'}")
    public Response getAMember(String firstMemberId) {
        Response response = apiClient.get(PathParameters.MEMBERS_BASE_PATH + firstMemberId, requestSpecification);
        initRequestSpecification();
        return response;
    }
}
