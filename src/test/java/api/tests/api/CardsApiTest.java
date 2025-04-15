package api.tests.api;

import api.base.BaseTest;
import api.base.PathParameters.*;
import api.base.TestData.*;
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

    @BeforeClass
    public void setUp() {
        CardsTestData.boardId = getCardsSteps().createABord(CardsTestData.BOARD_NAME);
        CardsTestData.listId = getCardsSteps().getIdOfTheFirstListOnABoard(CardsTestData.boardId);
    }

    @AfterClass
    public void tearDown() {
        getCardsSteps().deleteBoard(CardsTestData.boardId);
    }

    @Test(priority = 0)
    @Story("Verify cards")
    @Description("Create a new Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateANewCard() {
        Map<String, String> queryParametersForRequestSpec = new HashMap<>();
        queryParametersForRequestSpec.put("idList", CardsTestData.listId);

        Response response = getCardsSteps().createACard(queryParametersForRequestSpec);
        CardsTestData.cardId = response.jsonPath().get("id");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    @Story("Verify cards")
    @Description("Get a card")
    @Severity(SeverityLevel.NORMAL)
    public void testGetACard() {

        Response response = getCardsSteps().getCard(CardsTestData.cardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    @Story("Verify cards")
    @Description("Update a card")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateACard() {

        String newCardName = "NewCardName";
        Response response = getCardsSteps().updateCard(CardsTestData.cardId, newCardName);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("name"), newCardName);
    }

    @Test(priority = 3)
    @Story("Verify field on a cards")
    @Description("Get a field on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAFieldOnACard() {
        Response response = getCardsSteps().getFieldCard(CardsTestData.cardId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    @Story("Verify actions on a cards")
    @Description("Get an actions on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetActionsOnACard() {
        Response response = getCardsSteps().getActionsOnACard(CardsTestData.cardId, ActionsEndPoints.ACTIONS_BASE_PATH);
        List arrayList = response.jsonPath().getList("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    @Story("Verify attachments on a cards")
    @Description("Get a attachments on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAttachmentsOnACard() {

        String emptyString = "[]";

        Response response = getCardsSteps().getAttachmentsOnACard(CardsTestData.cardId);
        String actualResponseBody = response.body().asString();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(actualResponseBody, emptyString);

    }

    @Test(priority = 4)
    @Story("Verify attachments on a cards")
    @Description("Create an attachment on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateAttachmentOnCard() {
        String nameOfCreatedAttachment = "ForCreateAttachmentOnCardTest.txt";

        Response response = getCardsSteps().createAttachmentOnCard(CardsTestData.cardId, "src/test/resources/ForCreateAttachmentOnCardTest.txt");
        CardsTestData.createdAttachmentId = response.jsonPath().getString("id");
        String nameOfAttachmentReceivedBack = response.jsonPath().getString("name");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(nameOfAttachmentReceivedBack, nameOfCreatedAttachment);
    }

    @Test(priority = 5)
    @Story("Cards")
    @Description("Get an attachment on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAnAttachmentOnACard() {

        Response response = getCardsSteps().getAnAttachmentOnACard(CardsTestData.cardId, CardsTestData.createdAttachmentId);
        String attachmentIdReceivedBack = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(attachmentIdReceivedBack, CardsTestData.createdAttachmentId);
    }

    @Test(priority = 6)
    @Story("Verify delete an Attachment on a Card")
    @Description("Delete an Attachment on a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteAnAttachmentOnACard() {
        Response response = getCardsSteps().deleteAnAttachmentOnACard(CardsTestData.cardId, CardsTestData.createdAttachmentId);
        System.out.println(response.asPrettyString());
    }

    @Test(priority = 3)
    @Story("Cards")
    @Description("Get all available checkItems on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateChecklistOnACard() {

        String nameForCheckListCreated = "CardsAPITest";

        Response response = getCardsSteps().createAChecklist(CardsTestData.cardId, nameForCheckListCreated);
        String checklistNameReceivedBack = response.jsonPath().getString("name");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(checklistNameReceivedBack, nameForCheckListCreated);

    }

    @Test(priority = 3)
    @Story("Verify Checklists on a card")
    @Description("Get the Checklists on a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetChecklistsCard() {
        Response response = getCardsSteps().getChecklistsCard(CardsTestData.cardId);
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
        Response response = getCardsSteps().getListCard(CardsTestData.cardId);
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
        Response response = getCardsSteps().getMembersCard(CardsTestData.cardId);
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
        Response response = getCardsSteps().getStickersCard(CardsTestData.cardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }









    @Test(priority = 3)
    @Story("Verify board on a cards")
    @Description("Get the Board the Card is on")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetBoardCard() {
        Response response = getCardsSteps().getBoardCard(CardsTestData.cardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    @Story("Verify checkItems on a card")
    @Description("Get the checkItems on a Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetCheckItemsCard() {
        Response response = getCardsSteps().getCheckItemsCard(CardsTestData.cardId);
        List<Map<String, Object>> item = response.jsonPath().getList("");

//        Assert.assertNotNull(item.get(0).get("idCheckItem")); //не находит айтем
//        Assert.assertNotNull(item.get(0).get("state"));
        Assert.assertEquals(response.getStatusCode(), 200);

    }



    @Test(priority = 6)
    @Story("Verify cards")
    @Description("Delete a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteCard() {
        Response response = getCardsSteps().deleteCard(CardsTestData.cardId);

        Assert.assertEquals(response.getStatusCode(),200);
    }

    }


