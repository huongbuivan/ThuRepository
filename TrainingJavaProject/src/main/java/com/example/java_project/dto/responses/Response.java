package com.example.java_project.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private T data;
    private String error;

    public Response(T data, String error) {
        this.data = data;
        this.error = error;
    }
}
