package api.steps;

import api.base.PathParameters;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;

public class MembersSteps extends BaseService {

    @Step("Get a member with id - {firstMemberId}")
    public Response getAMember(String firstMemberId) {
        Response response = apiClient.get(PathParameters.MEMBERS_BASE_PATH + firstMemberId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Update a member: memberId = {memberId}")
    public Response updateMember(String memberId) {
        Response response = apiClient.put(PathParameters.MEMBERS_BASE_PATH + memberId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get a Member's Actions: memberId = {memberId}")
    public Response getMemberActions(String memberId) {
        Response response = apiClient.get(PathParameters.MEMBERS_BASE_PATH + memberId + "/actions", requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get Member's custom Board backgrounds: memberID = {memberId}")
    public Response getMemberCustomBackgrounds(String memberId) {
        Response response = apiClient.get(PathParameters.MEMBERS_BASE_PATH + memberId + "/boardBackgrounds", requestSpecification);
        initRequestSpecification();
        System.out.println(response.body().asString());
        return response;
    }

    @Step("Get a boardBackground of a Member: memberId = {memberId}, backgroundId = {backgroundId}")
    public Response getGetBoardBackgroundMember(String memberId, String backgroundId) {
        Response response = apiClient.get(PathParameters.MEMBERS_BASE_PATH + memberId + "/boardBackgrounds/" + backgroundId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Create Star for Board: memberId = {memberId}, boardId = {boardId}, pos = {pos}")
    public Response getCreateStarBoard(String memberId, String boardId, String pos) {
        requestSpecification.queryParam("idBoard", boardId);
        requestSpecification.queryParam("pos", pos);
        Response response = apiClient.post(PathParameters.MEMBERS_BASE_PATH + memberId + "/boardStars", requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Get a boardStar of Member: memberId = {memberId}, starId = {starId}")
    public Response getBoardStarMember(String memberId, String starId) {
        Response response = apiClient.get(PathParameters.MEMBERS_BASE_PATH + memberId + "/boardStars/" + starId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Update the position of a boardStar of Member: memberId = {memberId}, starId = {starId}, pos = {pos}")
    public Response updatePositionBoardStarMember(String memberId, String starId, String pos) {
        requestSpecification.queryParam("pos", pos);
        Response response = apiClient.put(PathParameters.MEMBERS_BASE_PATH + memberId + "/boardStars/" + starId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("Unstar a board: memberId = {memberId}, starId = {starId}")
    public Response deleteStarBoard(String memberId, String starId) {
        Response response = apiClient.delete(PathParameters.MEMBERS_BASE_PATH + memberId + "/boardStars/" + starId, requestSpecification);
        initRequestSpecification();
        return response;
    }

    @Step("List a member's board stars: memberId = {memberId}")
    public Response getMemberBoardStars(String memberId) {
        Response response = apiClient.get(PathParameters.MEMBERS_BASE_PATH + memberId + "/boardStars", requestSpecification);
        initRequestSpecification();
        return response;
    }
}
