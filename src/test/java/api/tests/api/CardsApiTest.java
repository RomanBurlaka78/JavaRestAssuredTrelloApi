package api.tests.api;

import api.base.BaseTest;
import api.base.ApiPathData;
import api.steps.CardsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

@Epic("API Tests")
@Feature("Cards Validation")
@Owner("Group JavaForwardToOffer")
public class CardsApiTest extends BaseTest {

    private CardsSteps cardsSteps = new CardsSteps();
    private String cardId;
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
}
