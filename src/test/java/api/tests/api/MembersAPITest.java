package api.tests.api;

import api.controllers.MembersSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.rootPath;

@Epic("API Tests")
@Feature("Members Validation")
@Owner("Group JavaForwardToOffer")
public class MembersAPITest {

    private final MembersSteps membersSteps = new MembersSteps();

    private final String bordName = "Board for Members";
    private final String pos = "top";
    private final String updatePos = "bottom";
    private String boardId;
    private String firstMemberId;
    private String backgroundId;
    private String starId;

    @BeforeClass
    public void setUp(){
        boardId = membersSteps.createABord(bordName);
        firstMemberId = membersSteps.getTheMembersOfABoard(boardId).jsonPath().getString("id");
        firstMemberId = firstMemberId.substring(1, firstMemberId.length()-1);
    }

    @AfterClass
    public void tearDown(){
        membersSteps.deleteBoard(boardId);
    }

    @Test
    @Story("Verify member")
    @Description("Get a member")
    @Severity(SeverityLevel.NORMAL)
    public void testGetAMember(){

        Response response = membersSteps.getAMember(firstMemberId);
        String memberIdReceivedBack = response.jsonPath().getString("id");

        Assert.assertEquals(memberIdReceivedBack, firstMemberId);
    }

    @Test(priority = 1)
    @Story("Verify update member")
    @Description("Update member")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateMember() {
        Response response = membersSteps.updateMember(firstMemberId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    @Story("Verify actions member")
    @Description("Get list the actions for a member")
    @Severity(SeverityLevel.NORMAL)
    public void testGetMemberActions() {
        Response response = membersSteps.getMemberActions(firstMemberId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    @Story("Verify member custom backgrounds")
    @Description("Get a member's custom board backgrounds")
    @Severity(SeverityLevel.NORMAL)
    public void testGetMemberCustomBackgrounds() {
        Response response = membersSteps.getMemberCustomBackgrounds(firstMemberId);

        backgroundId = response.body().jsonPath().getString(rootPath + "[0].id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Ignore
    @Test(dependsOnMethods = "testGetMemberCustomBackgrounds")
    @Story("Verify member backgrounds")
    @Description("Get a member's board background")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoardBackgroundMember() {
        Response response = membersSteps.getGetBoardBackgroundMember(firstMemberId, backgroundId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Story("Verify Create Star for Board")
    @Description("Star a new board on behalf of a Member")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateStarBoard() {
        Response response = membersSteps.getCreateStarBoard(firstMemberId, boardId, pos);
        starId = response.body().jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "testCreateStarBoard")
    @Story("Verify Get a boardStar of Member")
    @Description("Get a specific boardStar")
    @Severity(SeverityLevel.NORMAL)
    public void testGetBoardStarMember() {
        Response response = membersSteps.getBoardStarMember(firstMemberId, starId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "testCreateStarBoard")
    @Story("Verify Update the position of a boardStar of Member")
    @Description("Update the position of a starred board")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdatePositionBoardStarMember() {
        Response response = membersSteps.updatePositionBoardStarMember(firstMemberId, starId, updatePos);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    @Story("Verify Delete Star for Board")
    @Description("Unstar a board")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteStarBoard() {
        Response response = membersSteps.deleteStarBoard(firstMemberId, starId);


}
