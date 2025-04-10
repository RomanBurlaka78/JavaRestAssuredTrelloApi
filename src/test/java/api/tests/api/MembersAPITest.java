package api.tests.api;

import api.steps.MembersSteps;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MembersAPITest {

    private final MembersSteps membersSteps = new MembersSteps();

    private final String bordName = "Board for Members";
    private String boardId;
    private String firstMemberId;

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
    public void testGetAMember(){

        Response response = membersSteps.getAMember(firstMemberId);
        String memberIdReceivedBack = response.jsonPath().getString("id");

        Assert.assertEquals(memberIdReceivedBack, firstMemberId);
    }



}
