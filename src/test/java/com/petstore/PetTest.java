package com.petstore;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetTest{
    Map<String, Object> body;
    PetService petService;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";

        petService = new PetService();

        body = new HashMap<>();
        Map<String, Object> category = new HashMap<>();
        List<String> photoUrl  = new LinkedList<>();
        List<Map<String, Object>> photos = new LinkedList<>();

        Map<String, Object> photoMap = new HashMap<>();
        photoMap.put("id", 0);
        photoMap.put("name", "Doberman");
        photos.add(photoMap);

        category.put("id", 0);
        category.put("name", "DOG");
        body.put("name", "Rocky");

        body.put("category", category);
        body.put("photoUrls", photoUrl);
        body.put("tags", photoUrl);
        body.put("status", "available");
    }

    @Test
    void createPet(){
        ValidatableResponse response = petService.createPet("/pet", body);

        response.extract().response().prettyPrint();
        assertEquals(200, response.extract().statusCode());
    }
}
