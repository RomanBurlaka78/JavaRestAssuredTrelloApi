package api.tests.api;

import api.base.BaseTest;
import api.base.BaseUiTest;
import api.base.TestData;
import api.controllers.CardsSteps;
import api.controllers.ui.UiCardSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Epic("API Tests")
@Feature("Cards Validation")
@Owner("Group JavaForwardToOffer")
public class CardsApiTest extends BaseTest {

    CardsSteps cardsSteps = new CardsSteps();
    UiCardSteps uiCardSteps = new UiCardSteps();

    @Test(priority = 51, dependsOnGroups = "Created_Board_and_List")
    @Story("Verify cards")
    @Description("Create a new Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewCard() {
        Response response = cardsSteps.createCard(TestData.idList);
        TestData.cardId = response.path("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 52, dependsOnMethods = "testCreateNewCard")
    @Story("Verify cards")
    @Description("Get a card")
    @Severity(SeverityLevel.NORMAL)
    public void testGetCard() {
        Response response = cardsSteps.getCard(TestData.cardId);

        Assert.assertEquals(response.getStatusCode(), 200);


    }

    @Test(priority = 53, dependsOnMethods = "testCreateNewCard")
    @Story("Verify cards")
    @Description("Update a card")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateCard() {
        String nameCard = "NewCardName";
        Response response = cardsSteps.updateCard(TestData.cardId, nameCard);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("name"), nameCard);
//        uiCardSteps.verifyCardUpdatedName();
    }


    @Test(priority = 54, dependsOnMethods = "testCreateNewCard")
    @Story("Verify cards")
    @Description("Delete a card")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteCard() {
        Response response = cardsSteps.deleteCard(TestData.cardId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
