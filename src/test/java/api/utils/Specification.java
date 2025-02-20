package api.utils;

import groovyjarjarpicocli.CommandLine;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.utils.ConfigLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.expect;


public class Specification {

    private  String key;
    private String token;

    {
        try {
            key = ConfigLoader.getProperty("key");
            token = ConfigLoader.getProperty("token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Specification() {
    }

    public  RequestSpecification installRequest() {
        Map<String, String> authoriazing = new HashMap<>();
        authoriazing.put("key", key);
        authoriazing.put("token", token);

        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .addQueryParams(authoriazing)
                .setBaseUri("https://api.trello.com/1/")
                .build();
    }

    public static ResponseSpecification responseSpec(int statusCode) {
        return expect()
                .statusCode(statusCode)
                .log().all();
    }

    public void installSpec() {
        RestAssured.requestSpecification =installRequest();
    }




}


