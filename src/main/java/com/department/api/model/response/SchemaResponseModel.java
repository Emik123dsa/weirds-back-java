package com.department.api.model.response;

import java.io.Serializable;

public class SchemaResponseModel implements Serializable {
    private final Integer code;
    private final String msg;
    private final Object details;

    public SchemaResponseModel(Integer code, String msg, Object details) {
        this.code = code;
        this.msg = msg;
        this.details = details;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getDetails() {
        return details;
    }
}
