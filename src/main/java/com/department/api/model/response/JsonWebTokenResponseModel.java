package com.department.api.model.response;

public class JsonWebTokenResponseModel {

    public String getJwt() {
        return jwt;
    }

    private final String jwt;

    public JsonWebTokenResponseModel(String jwt) {
        this.jwt = jwt;
    }
}
