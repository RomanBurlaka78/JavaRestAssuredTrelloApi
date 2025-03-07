package api.tests.api;

import api.base.BaseTest;
import api.steps.CardsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("API Tests")
@Feature("Cards Validation")
@Owner("Group JavaForwardToOffer")
public class CardsApiTest extends BaseTest {

    CardsSteps cardsSteps = new CardsSteps();

    @Test(priority = 4, description = "Create Board Validation", dependsOnGroups = "Created_Board_and_List")
    @Story("Verify cards")
    @Description("Create a new Card")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewCard() {
        Response response = cardsSteps.createCard();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
