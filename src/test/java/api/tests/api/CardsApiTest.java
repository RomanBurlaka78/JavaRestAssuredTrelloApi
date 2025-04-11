package api.tests.api;

import api.base.BaseTest;
import api.base.PathParameters;
import api.controllers.CardsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

@Epic("API Tests")
@Feature("Cards Validation")
@Owner("Group JavaForwardToOffer")
public class CardsApiTest extends BaseTest {

    private CardsSteps cardsSteps = new CardsSteps();
    private String cardId;
    private String bordName = "Board for cards";
    private String boardId;
    private String listId;
    private  String attachmentId;



    @BeforeClass
    public void setUp() {
        boardId = cardsSteps.createABord(bordName);
        listId = cardsSteps.getIdOfTheFirstListOnABoard(boardId);
    }

    @AfterClass
    public void tearDown() {
        cardsSteps.deleteBoard(boardId);
    }

    @Test(priority = 0)
    @Story("Verify cards")
    @Description("Create a new Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewCard() {
        Map<String, String> queryParametersForRequestSpec = new HashMap<>();
        queryParametersForRequestSpec.put("idList", listId);

        Response response = cardsSteps.createACard(queryParametersForRequestSpec);
        cardId = response.jsonPath().get("id");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    @Story("Verify cards")
    @Description("Get a card")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCard() {
        Response response = cardsSteps.getCard(cardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    @Story("Verify cards")
    @Description("Update a card")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateCard() {
        String newCardName = "NewCardName";
        Response response = cardsSteps.updateCard(cardId, newCardName);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("name"), newCardName);
    }

    @Test(priority = 6)
    @Story("Verify cards")
    @Description("Delete a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteCard() {
        Response response = cardsSteps.deleteCard(cardId);

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    @Story("Verify field on a cards")
    @Description("Get a field on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetFieldCard() {
        Response response = cardsSteps.getFieldCard(cardId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 5)
    @Story("Verify actions on a cards")
    @Description("Get a actions on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetActionsCard() {
        Response response = cardsSteps.getActionsCard(cardId, PathParameters.ACTIONS_BASE_PATH);
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    @Story("Verify attachments on a cards")
    @Description("Get a attachments on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAttachmentsCard() {
        Response response = cardsSteps.getAttachmentsCard(cardId);
        List<Map<String, Object>> attachments = response.jsonPath().getList("");
        if (!attachments.isEmpty()) {
            String firstAttachmentId = (String) attachments.get(0).get("id");

            Assert.assertNotNull(firstAttachmentId);
            Assert.assertNotNull(attachments.get(0).get("name"));
        }
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    @Story("Verify specific attachments on a cards")
    @Description("Get a specific Attachment on a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetSpecificAttachmentsCard() {
        Response allAttachmentsResponse = cardsSteps.getSpecificAttachmentsCard(cardId, attachmentId);
        List<Map<String, Object>> attachments = allAttachmentsResponse.jsonPath().getList("");
        if (!attachments.isEmpty()) {
            String attachmentId = (String) attachments.get(0).get("id");
            Response response = cardsSteps.getSpecificAttachmentsCard(cardId, attachmentId);

            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertNotNull(response.jsonPath().getString("id"));
            Assert.assertEquals(response.jsonPath().getString("id"), attachmentId);
        }
    }

    @Test(priority = 6)
    @Story("Verify delete an Attachment on a Card")
    @Description("Delete an Attachment on a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testAttachmentDeleteCard() {
        Response allAttachmentsResponse = cardsSteps.getAttachmentsCard(cardId);
        List<Map<String, Object>> attachments = allAttachmentsResponse.jsonPath().getList("");
        if (!attachments.isEmpty()) {
            String attachmentId = (String) attachments.get(0).get("id");
            Response deleteResponse = cardsSteps.deleteAttachmentCard(cardId, attachmentId);

            Assert.assertEquals(deleteResponse.getStatusCode(), 200);

            Response res = cardsSteps.getSpecificAttachmentsCard(cardId, attachmentId);
            Assert.assertEquals(res.getStatusCode(), 404);
        }
    }

    @Test(priority = 3)
    @Story("Verify board on a cards")
    @Description("Get the Board the Card is on")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetBoardCard() {
        Response response = cardsSteps.getBoardCard(cardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    @Story("Verify checkItems on a card")
    @Description("Get the checkItems on a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetCheckItemsCard() {
        Response response = cardsSteps.getCheckItemsCard(cardId);
        List<Map<String, Object>> item = response.jsonPath().getList("");

//        Assert.assertNotNull(item.get(0).get("idCheckItem")); //не находит айтем
//        Assert.assertNotNull(item.get(0).get("state"));
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    @Story("Verify Checklists on a card")
    @Description("Get the Checklists on a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetChecklistsCard() {
        Response response = cardsSteps.getChecklistsCard(cardId);
        List<Map<String, Object>> checklists = response.jsonPath().getList("");
            if (!checklists.isEmpty()) {
                Map<String, Object> firstChecklist = checklists.get(0);

                Assert.assertNotNull(firstChecklist.get("id"));
                Assert.assertNotNull(firstChecklist.get("name"));
                Assert.assertEquals(response.getStatusCode(), 200);

        List<Map<String, String>> checkItems = (List<Map<String, String>>) firstChecklist.get("checkItems");
            if (!checkItems.isEmpty()) {
               Assert.assertNotNull(checkItems.get(0).get("id"));
               Assert.assertNotNull(checkItems.get(0).get("name"));
               Assert.assertNotNull(checkItems.get(0).get("state")); // для теста с items
               }
            }
        }

    @Test(priority = 3)
    @Story("Verify list of a cards")
    @Description("Get the List of a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetListCard() {
        Response response = cardsSteps.getListCard(cardId);
        List<Map<String, Object>> list = response.jsonPath().getList("");
        Map<String, Object> firstlist = list.get(0);

        Assert.assertNotNull(firstlist.get("id"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    @Story("Verify Members of a cards")
    @Description("Get the Members of a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetMembersCard() {
        Response response = cardsSteps.getMembersCard(cardId);
        List<Map<String, Object>> members = response.jsonPath().getList("");
        Map<String, Object> firstMember = members.get(0);

        Assert.assertNotNull(firstMember.get("id"));
        Assert.assertNotNull(firstMember.get("username"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    @Story("Verify stickers of a cards")
    @Description("Get the stickers on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetStickersCard() {
        Response response = cardsSteps.getStickersCard(cardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    }


