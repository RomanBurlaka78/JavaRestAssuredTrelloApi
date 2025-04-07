package api.tests.api;

import api.base.BaseTest;
import api.base.PathParameters;
import api.steps.CardsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

@Epic("API Tests")
@Feature("Cards Validation")
@Owner("Group JavaForwardToOffer")
public class CardsApiTest extends BaseTest {

    private CardsSteps cardsSteps = new CardsSteps();
    private String cardId;
    private String listId;
    private String bordName = "Board for cards";

    @BeforeClass
    public void setUp(){
        cardsSteps.createABord(bordName);
    }

    @AfterClass
    public void tearDown(){
        cardsSteps.deleteBoard();
    }

    @Test(priority = 0)
    @Story("Verify cards")
    @Description("Create a new Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewCard() {
        Response response = cardsSteps.createCard();
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
}
