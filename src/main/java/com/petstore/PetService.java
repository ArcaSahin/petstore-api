package com.petstore;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;


import java.util.*;

import static io.restassured.RestAssured.given;

public class PetService extends BaseApiService {

    public ValidatableResponse createPet(String endPoint, Map<String, Object> body) {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endPoint)
                .then();
        response.extract().response().prettyPrint();
        return response;
    }
}

