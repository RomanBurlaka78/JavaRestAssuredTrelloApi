package api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static ApiClient instance;
    private static RequestSpecification requestSpec;
    private static Specification specification = new Specification();
    private static final Logger logger = LogManager.getLogger(ApiClient.class);

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

    public void getRequestSpec() {

        requestSpec = RestAssured.given()
                .spec(specification.installRequest());

        specification.installResponse();
    }

    public Response get(String path, RequestSpecification requestSpecification) {
        logger.info("Executing GET request to: " + path);

        return requestSpecification.get(path);
    }

    public Response post(String path, RequestSpecification requestSpecification) {
        logger.info("Executing POST request to: " + path);

        return requestSpecification.post(path);
    }

    public Response put(String path, RequestSpecification requestSpecification) {
        logger.info("Executing PUT request to: " + path);

        return requestSpecification.put(path);
    }

    public Response patch(String path, String body) {
        logger.info("Executing PUT request to: " + path);

        return requestSpec.body(body).patch(path);
    }

    public Response delete(String path, RequestSpecification requestSpecification) {
        logger.info("Executing DELETE request to: " + path);

        return requestSpecification.delete(path);
    }
}