package com.glofox.glofox.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseModel {
    private String message;
    private Object data;

    public ResponseModel(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
