package com.joutvhu.training.rest.model.view;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class RestResponse<T> implements Serializable {
    private static final long serialVersionUID = -8496394042500151133L;

    private Integer status;
    private String message;
    private Boolean success;
    private T data;

    public RestResponse(HttpStatus status) {
        this.status = status.value();
        this.success = status.is2xxSuccessful();
    }

    public RestResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
        this.success = status.is2xxSuccessful();
    }

    public RestResponse(T data) {
        this.data = data;
        this.success = true;
    }

    public RestResponse(T data, HttpStatus status) {
        this.status = status.value();
        this.data = data;
        this.success = status.is2xxSuccessful();
    }
}
