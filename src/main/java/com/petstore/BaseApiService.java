package com.petstore;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Objects;

public class BaseApiService {

    public ValidatableResponse crud(String operation, Map<String, Object> queryParams, Map<String, Object> body, String endPoint) {
        //CRUD: Create Read Update Delete operations

        //Build request with builder
        RequestSpecBuilder builder = new RequestSpecBuilder();

        // Set base uri
        builder.setContentType(ContentType.JSON)
                .setBaseUri("https://petstore.swagger.io/v2");

        // Set query params
        if (!Objects.isNull(queryParams)) {
            for (Map.Entry<String, Object> eachQueryParam : queryParams.entrySet()) {
                builder.addQueryParam(eachQueryParam.getKey(), eachQueryParam.getValue());
            }
        }

        // Set body
        if (!Objects.isNull(body)) {
            builder.setBody(body);
        }

        // Build request
        RequestSpecification request = RestAssured.given().spec(builder.build());

        // Execute
        return switch (operation.toLowerCase()) {
            case "get" -> request.get(endPoint).then();
            case "post" -> request.post(endPoint).then();
            case "put" -> request.put(endPoint).then();
            case "delete" -> request.delete(endPoint).then();
            default -> throw new RuntimeException("Please check your operation. It passed as " + operation + "!");
        };
    }
}
