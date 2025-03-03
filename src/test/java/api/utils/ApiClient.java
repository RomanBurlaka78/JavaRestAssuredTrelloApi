package api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiClient {

    private static ApiClient instance;
    private RequestSpecification requestSpec;
    private static final Logger logger = LogManager.getLogger(ApiClient.class);
    Specification specification= new Specification();

    private ApiClient() {
        logger.info("Creating ApiClient instance");
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            logger.info("Initializing new ApiClient instance");
            instance = new ApiClient();
        }
        return instance;
    }

    public RequestSpecification getRequestSpec() {
        requestSpec = RestAssured.given()
                // Используем RequestSpecification, установленный в BaseTest
                .spec(specification.installRequest())
                .contentType("application/json");

        return requestSpec;
    }

    public Response get(String path) {
        logger.info("Executing GET request to: " + path);
        return requestSpec.get(path);
    }

    public Response post(String path, String body) {
        logger.info("Executing POST request to: " + path);
        return requestSpec.body(body).post(path);
    }
    public Response postWithOutBody(String path, String name) {
        logger.info("Executing POST request to: " + path);
        return requestSpec.queryParam("name", name).post(path);
    }

    public Response put(String path, String body) {
        logger.info("Executing PUT request to: " + path);
        return requestSpec.body(body).put(path);
    }

    public Response patch(String path, String body) {
        logger.info("Executing PUT request to: " + path);
        return requestSpec.body(body).patch(path);
    }

    public Response delete(String path) {
        logger.info("Executing DELETE request to: " + path);
        return requestSpec.delete(path);
    }
}