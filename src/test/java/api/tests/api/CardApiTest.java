package api.tests.api;

import api.base.BaseTest;
import api.base.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.utils.Specification;
import io.qameta.allure.*;

import static io.restassured.RestAssured.given;



public class CardApiTest extends BaseTest {


//    @Test(priority = 7)
//    public  void testCreateCard() {
//
//        Response response = given()
//                .when()
//                .body("""
//                        {
//                        	"name": "US_000.Postman"
//                        }
//                        """)
//                .post("cards?idList={idList}", TestData.idList)
//                .then()
//                .statusCode(200)
//                .extract().response();
//
//
//    }

    @Test(priority = 8, enabled = false)
    @Feature("Create Card Validation")
    @Story("Create a card in a list")
    @Description("Create a card in the backlog list")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateCard() {
        Assert.assertNotNull(TestData.idList, "List ID is null. Ensure that testCreateBoardListBacklog ran successfully.");

        String cardName = "US_000.Postman";
        String cardId = createCardStep(TestData.idList, cardName);

        Assert.assertNotNull(cardId, "Card ID should not be null");
        System.out.println("Created Card ID: " + cardId);
    }

    @Step("Create card with name: {cardName} in list: {listId}")
    private String createCardStep(String listId, String cardName) {
        return given()
                .pathParam("idList", listId)
                .body("{\"name\": \"" + cardName + "\"}")
                .when()
                .post("cards")
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }




}
