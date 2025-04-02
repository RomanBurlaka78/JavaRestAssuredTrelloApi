package api.steps;

import api.base.PathParameters;
import api.utils.ApiClient;
import api.utils.Specification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class BaseService {

    protected static final Specification specification = new Specification();
    protected RequestSpecification requestSpecification;
    protected ApiClient apiClient = ApiClient.getInstance();

    {
//        requestSpecification = RestAssured.given().spec(specification.installRequest());
        initRequestSpecification();
    }

    public String createABord(String boardName){

        requestSpecification.queryParam("name", boardName);

        Response response = apiClient.post(PathParameters.BOARD_BASE_PATH, requestSpecification);
        initRequestSpecification();
        return response.jsonPath().getString("id");
    }

    public void deleteBoard(String boardId) {

        apiClient.delete(PathParameters.BOARD_BASE_PATH + boardId, requestSpecification);

    }

    public String getTheFirstListsId(String boardId){

        Response resp = apiClient.get(PathParameters.BOARD_BASE_PATH + boardId + PathParameters.LISTS_BASE_PATH, requestSpecification);
        List arrayList = resp.jsonPath().getList("id");
        initRequestSpecification();
        return (String) arrayList.get(0);
    }

    protected void initRequestSpecification(){
        requestSpecification = RestAssured.given().spec(specification.installRequest());

    }



}
