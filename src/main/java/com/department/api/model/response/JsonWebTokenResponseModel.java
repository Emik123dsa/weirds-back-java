package com.department.api.model.response;

import java.io.Serializable;

public class JsonWebTokenResponseModel implements Serializable {

    public String getJwt() {
        return jwt;
    }

    private final String jwt;

    public JsonWebTokenResponseModel(String jwt) {
        this.jwt = jwt;
    }
}
