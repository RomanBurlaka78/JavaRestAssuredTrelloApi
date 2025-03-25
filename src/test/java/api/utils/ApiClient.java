package api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static ApiClient instance;
    private static final Logger LOGGER = LogManager.getLogger(ApiClient.class);

    static {
        RestAssured.requestSpecification = Specification.installRequest();
        RestAssured.responseSpecification = Specification.installResponse();
    }

    private ApiClient() {
        LOGGER.info("Creating ApiClient instance");
    }

    public static ApiClient getInstance() {

        if (instance == null) {
            LOGGER.info("Initializing new ApiClient instance");
            instance = new ApiClient();
        }

        return instance;
    }

    public Response get(String path) {
        LOGGER.info("Executing GET request to: " + path);

        return given().get(path);

    }

    public Response post(String path, String body) {
        LOGGER.info("Executing POST request to: " + path);

        return given().body(body).post(path);
    }

    public Response postWithOutBody(String path, String name) {
        LOGGER.info("Executing POST request to: " + path);

        return given().queryParam("name", name).post(path);
    }

    public Response put(String path, String body) {
        LOGGER.info("Executing PUT request to: " + path);

        return given().body(body).put(path);
    }

    public Response patch(String path, String body) {
        LOGGER.info("Executing PUT request to: " + path);

        return given().body(body).patch(path);
    }

    public Response delete(String path) {
        LOGGER.info("Executing DELETE request to: " + path);

        return given().delete(path);
    }
}