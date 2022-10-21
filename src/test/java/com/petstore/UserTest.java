package com.petstore;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {
    UserService userService;
    Map<String, Object> body;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        body = new HashMap<>();
        body.put("id", 0);
        body.put("username", "arcasahin");
        body.put("firstName", "arca");
        body.put("lastName", "sahin");
        body.put("email", "arca.sahin@paycore.com");
        body.put("password", "Passw0rd");
        body.put("phone", "555 555 5555");
        body.put("userStatus", 0);
    }


    @Test
        //If using JUnit5, you don't need to specify access modifier like public
    void createUserTest() {
        ValidatableResponse response = userService.createUser(body);

        response.extract().response().prettyPrint();
        assertEquals(200, response.extract().statusCode());
    }

    @Test
    void getCreatedUserTest() {
        userService.createUser(body);

        String username = (String) body.get("username");
        ValidatableResponse response = userService.getUser(username);

        response.extract().response().prettyPrint();
        assertEquals(200, response.extract().statusCode());

        assertEquals(username, response.extract().path("username"));
    }

    @Test
    void updateCreatedUserTest() {
        userService.createUser(body);

        String oldUserName = (String) body.get("username");

        body.put("username", "sahinarca"); // Re set username attribute of body as sahinarca
        String newUserName = (String) body.get("username");

        ValidatableResponse response = userService.updateUser(oldUserName, body);

        response.extract().response().prettyPrint();
        assertEquals(200, response.extract().statusCode());
    }

    @Test
    void deleteCreatedUserTest() {
        userService.createUser(body);
        String userName = (String) body.get("username");

        ValidatableResponse response = userService.deleteUser(userName);

        response.extract().response().prettyPrint();
        assertEquals(200, response.extract().statusCode());
    }
}
