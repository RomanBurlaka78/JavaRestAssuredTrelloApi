package api.tests.api;

import api.base.BaseTest;
import api.base.PathParameters;
import api.steps.CardsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

@Epic("API Tests")
@Feature("Cards Validation")
@Owner("Group JavaForwardToOffer")
public class CardsApiTest extends BaseTest {

    private CardsSteps cardsSteps = new CardsSteps();
    private String cardId;
    private String bordName = "Board for cards";
    private String boardId;
    private String listId;
    private String createdAttachmentId;



    @BeforeClass
    public void setUp(){
        boardId = cardsSteps.createABord(bordName);
        listId = cardsSteps.getIdOfTheFirstListOnABoard(boardId);
    }

    @AfterClass
    public void tearDown(){
        cardsSteps.deleteBoard(boardId);
    }

    @Test(priority = 0)
    @Story("Verify cards")
    @Description("Create a new Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewCard() {
        Map <String, String> queryParametersForRequestSpec = new HashMap<>();
        queryParametersForRequestSpec.put("idList",listId);

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

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(arrayList.size(), 0);
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
        }

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 6)
    @Story("Verify attachments on a cards")
    @Description("Create an attachment on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateAttachmentOnCard() {
        String nameOfCreatedAttachment = "ForCreateAttachmentOnCardTest.txt";

        Response response = cardsSteps.createAttachmentOnCard(cardId, "src/test/resources/ForCreateAttachmentOnCardTest.txt");
        createdAttachmentId = response.jsonPath().getString("id");
        String nameOfAttachmentReceivedBack = response.jsonPath().getString("name");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(nameOfAttachmentReceivedBack, nameOfCreatedAttachment);
    }

    @Test(priority = 7)
    @Story("Verify attachments on a cards")
    @Description("Get an attachment on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAnAttachmentOnACard() {

        Response response = cardsSteps.getAnAttachmentOnACard(cardId, createdAttachmentId);
        String attachmentIdReceivedBack = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(attachmentIdReceivedBack, createdAttachmentId);
    }

    @Test(priority = 7)
    @Story("Verify attachments on a cards")
    @Description("Get all available checkItems on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetCheckItemsOnACard() {

        String emptyString = "[]";

        Response response = cardsSteps.getCheckItemsOnACard(cardId);

        String actualCheckItemsOnACard = response.body().asString();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(actualCheckItemsOnACard, emptyString);
    }

    @Test(priority = 7)
    @Story("Verify attachments on a cards")
    @Description("Get all available checkItems on a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetChecklistsOnACard() {

        String emptyString = "[]";

        Response response = cardsSteps.getChecklistsOnACard(cardId);

        String actualChecklistsOnACard = response.body().asString();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(actualChecklistsOnACard, emptyString);
    }

    @Test(priority = 8)
    @Story("Verify cards")
    @Description("Delete a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteCard() {
        Response response = cardsSteps.deleteCard(cardId);

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
