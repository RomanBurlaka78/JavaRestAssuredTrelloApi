package api.tests.api;

import api.base.BaseTest;
import api.base.TestData;
import api.controllers.LabelsSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static api.base.TestData.LabelsTestData.*;

@Epic("API Tests")
@Feature("Labels Validation")
@Owner("Group JavaForwardToOffer")
public class LabelsApiTest extends BaseTest {

    private LabelsSteps labelsSteps = new LabelsSteps();

    @BeforeClass
    public void setUp() {
        boardId = labelsSteps.createABord(boardName);
    }

    @AfterClass
    public void tearDown() {
        labelsSteps.deleteBoard(boardId);
    }

    @Test()
    @Story("Verify created label")
    @Description("Create a new Label on a Board")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateLabel() {
        Response response = labelsSteps.createLabel(labelName, color, boardId);

        labelId = response.body().jsonPath().get("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().get("name"), labelName);
        Assert.assertEquals(response.body().jsonPath().get("color"), color);
    }

    @Test(priority = 1)
    @Story("Verify get label")
    @Description("Get label")
    @Severity(SeverityLevel.NORMAL)
    public void testGetLabel() {
        Response response = labelsSteps.getLabel(labelId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().getString("id"), labelId);
    }

    @Test(priority = 1)
    @Story("Verify update label")
    @Description("Update label")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateLabel() {
        Response response = labelsSteps.updateLabel(labelId, newName, newColor);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().getString("name"), newName);
        Assert.assertEquals(response.body().jsonPath().getString("color"), newColor);
    }

    @Test(priority = 2)
    @Story("Verify delete label")
    @Description("Delete label")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteLabel() {
        Response response = labelsSteps.deleteLabel(labelId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1, dataProvider = "createUpdateFieldLabel", dataProviderClass = TestData.LabelsTestData.class)
    @Story("Verify update field label")
    @Description("Update field label")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateFieldLabel(String field, String value) {
        Response response = labelsSteps.updateFieldLabel(labelId, field, value);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().getString(field), value);
    }
}
