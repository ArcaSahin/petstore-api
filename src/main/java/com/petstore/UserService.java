package com.petstore;

import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

public class UserService extends BaseApiService {
    public ValidatableResponse createUser(Map<String, Object> body) {
        return crud(
                "post",
                null, //
                body,
                "/user"
        );
    }

    public ValidatableResponse getUser(String username) {
        return crud(
                "get",
                null,
                null,
                "/user/" + username + ""
        );
    }

    public ValidatableResponse updateUser(String username, Map<String, Object> body) {
        return crud(
                "put",
                null,
                body,
                "/user/" + username + ""
        );
    }

    public ValidatableResponse deleteUser(String username) {
        return crud(
                "delete",
                null,
                null,
                "/user/" + username + ""
        );
    }
}
